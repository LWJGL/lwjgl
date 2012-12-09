/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.opengl;

/**
 * This is the Display implementation interface. Display delegates
 * to implementors of this interface. There is one DisplayImplementation
 * for each supported platform.
 * @author elias_naur
 */

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Robot;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.MemoryUtil;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;

import com.apple.eawt.Application;
import com.apple.eawt.ApplicationAdapter;
import com.apple.eawt.ApplicationEvent;

import static org.lwjgl.opengl.GL11.*;

final class MacOSXDisplay implements DisplayImplementation {
	private static final int PBUFFER_HANDLE_SIZE = 24;
	private static final int GAMMA_LENGTH = 256;

	private MacOSXCanvasListener canvas_listener;
	private Canvas canvas;
	private Robot robot;
	//private MacOSXMouseEventQueue mouse_queue;
	private KeyboardEventQueue keyboard_queue;
	private java.awt.DisplayMode requested_mode;
    
    /* Members for native window use */
    private MacOSXNativeMouse mouse;
    private MacOSXNativeKeyboard keyboard;
    private ByteBuffer window;
    private ByteBuffer context;
    private int x;
    private int y;
    private int width;
    private int height;
    
    private boolean close_requested;

	MacOSXDisplay() {
		
	}

	private native ByteBuffer nCreateWindow(int x, int y, int width, int height, boolean fullscreen, boolean undecorated, boolean resizable, boolean parented, ByteBuffer peer_info_handle, ByteBuffer window_handle) throws LWJGLException;

	private native boolean nIsMiniaturized(ByteBuffer window_handle);
    
	private native boolean nIsFocused(ByteBuffer window_handle);
    
	private native void nSetResizable(ByteBuffer window_handle, boolean resizable);
    
	private native void nResizeWindow(ByteBuffer window_handle, int x, int y, int width, int height);
    
	private native boolean nWasResized(ByteBuffer window_handle);
	
	private native int nGetWidth(ByteBuffer window_handle);
	
	private native int nGetHeight(ByteBuffer window_handle);
    
	private static boolean isUndecorated() {
		return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
	}
    
	public void createWindow(final DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
		boolean fullscreen = Display.isFullscreen();
		boolean resizable = Display.isResizable();
		boolean parented = (parent != null);
		
		close_requested = false;
        
        DrawableGL gl_drawable = (DrawableGL)Display.getDrawable();
        PeerInfo peer_info = gl_drawable.peer_info;
		ByteBuffer peer_handle = peer_info.lockAndGetHandle();
		try {
            window = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(),
                                   fullscreen, isUndecorated(), resizable,
                                   parented, peer_handle, window);
            this.x = x;
            this.y = y;
            this.width = mode.getWidth();
            this.height = mode.getHeight();
            this.canvas = parent;
            
            if (fullscreen) {
            		// when going to fullscreen viewport is set to screen size by Cocoa, ignore this value
            		skipViewportValue = true;
            		// if starting in fullscreen then set initial viewport to displaymode size
            		if (current_viewport.get(2) == 0 && current_viewport.get(3) == 0) {
            			current_viewport.put(2, width);
            			current_viewport.put(3, height);
            		}
            }
		} catch (LWJGLException e) {
			destroyWindow();
			throw e;
        } finally {
            peer_info.unlock();
        }
	}

	public void doHandleQuit() {
		synchronized (this) {
			close_requested = true;
		}
	}

	public native void nDestroyWindow(ByteBuffer window_handle);

	public void destroyWindow() {
        nDestroyWindow(window);
	}

	public int getGammaRampLength() {
		return GAMMA_LENGTH;
	}

	public native void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException;

	public String getAdapter() {
		return null;
	}

	public String getVersion() {
		return null;
	}

	private static boolean equals(java.awt.DisplayMode awt_mode, DisplayMode mode) {
		return awt_mode.getWidth() == mode.getWidth() && awt_mode.getHeight() == mode.getHeight()
			&& awt_mode.getBitDepth() == mode.getBitsPerPixel() && awt_mode.getRefreshRate() == mode.getFrequency();
	}

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		java.awt.DisplayMode[] awt_modes = getDevice().getDisplayModes();
		for ( java.awt.DisplayMode awt_mode : awt_modes ) {
			if (equals(awt_mode, mode)) {
				requested_mode = awt_mode;
				return;
			}
		}
		throw new LWJGLException(mode + " is not supported");
	}

	private static GraphicsDevice getDevice() {
		GraphicsEnvironment g_env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice device = g_env.getDefaultScreenDevice();
		return device;
	}

	public void resetDisplayMode() {
        requested_mode = null;
		restoreGamma();
	}

	private native void restoreGamma();

	private static DisplayMode createLWJGLDisplayMode(java.awt.DisplayMode awt_mode) {
		int bit_depth;
		int refresh_rate;
		int awt_bit_depth = awt_mode.getBitDepth();
		int awt_refresh_rate = awt_mode.getRefreshRate();
		if (awt_bit_depth != java.awt.DisplayMode.BIT_DEPTH_MULTI)
			bit_depth = awt_bit_depth;
		else
			bit_depth = 32; // Assume the best bit depth
		if (awt_refresh_rate != java.awt.DisplayMode.REFRESH_RATE_UNKNOWN)
			refresh_rate = awt_refresh_rate;
		else
			refresh_rate = 0;
		return new DisplayMode(awt_mode.getWidth(), awt_mode.getHeight(), bit_depth, refresh_rate);
	}

	public DisplayMode init() throws LWJGLException {
		return createLWJGLDisplayMode(getDevice().getDisplayMode());
	}

	public DisplayMode[] getAvailableDisplayModes() throws LWJGLException {
		java.awt.DisplayMode[] awt_modes = getDevice().getDisplayModes();
		List<DisplayMode> modes = new ArrayList<DisplayMode>();
		for ( java.awt.DisplayMode awt_mode : awt_modes )
			if ( awt_mode.getBitDepth() >= 16 )
				modes.add(createLWJGLDisplayMode(awt_mode));
		return modes.toArray(new DisplayMode[modes.size()]);
	}

	private native void nSetTitle(ByteBuffer window_handle, ByteBuffer title_buffer);

	public void setTitle(String title) {
		ByteBuffer buffer = MemoryUtil.encodeUTF8(title);
		nSetTitle(window, buffer);
	}

	public boolean isCloseRequested() {
		boolean result;
		synchronized (this) {
			result = close_requested;
			close_requested = false;
		}
		return result;
	}

	public boolean isVisible() {
        return true;
	}

	public boolean isActive() {
        boolean ret = nIsFocused(window);
        return ret;
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public boolean isDirty() {
		return false;
	}

	public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
		try {
			return new MacOSXDisplayPeerInfo(pixel_format, attribs, true);
		} catch (LWJGLException e) {
			return new MacOSXDisplayPeerInfo(pixel_format, attribs, false);
		}
	}

	private boolean skipViewportValue = false;
    private static final IntBuffer current_viewport = BufferUtils.createIntBuffer(16);
	public void update() {
		boolean should_update = true;
        
		DrawableGL drawable = (DrawableGL)Display.getDrawable();
		if (should_update) {
			drawable.context.update();
			/* This is necessary to make sure the context won't "forget" about the view size */
			if (skipViewportValue) skipViewportValue = false;
			else glGetInteger(GL_VIEWPORT, current_viewport);
			glViewport(current_viewport.get(0), current_viewport.get(1), current_viewport.get(2), current_viewport.get(3));
		}
	}

	public void reshape(int x, int y, int width, int height) {
        //if (native_mode) {
        //    nResizeWindow(window, x, y, width, height);
        //}
	}

	/* Mouse */
	public boolean hasWheel() {
		return AWTUtil.hasWheel();
	}

	public int getButtonCount() {
		return AWTUtil.getButtonCount();
	}

	public void createMouse() throws LWJGLException {
        mouse = new MacOSXNativeMouse(this, window);
        mouse.register();
	}

	public void destroyMouse() {
        //MacOSXMouseEventQueue.nGrabMouse(false);
        
        if (mouse != null) {
        		mouse.unregister();
        }
        mouse = null;
	}

	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons_buffer) {
		mouse.poll(coord_buffer, buttons_buffer);
	}

	public void readMouse(ByteBuffer buffer) {
		mouse.copyEvents(buffer);
	}

	public void grabMouse(boolean grab) {
		mouse.setGrabbed(grab);
	}

	public int getNativeCursorCapabilities() {
		return AWTUtil.getNativeCursorCapabilities();
	}

	public void setCursorPosition(int x, int y) {
		if (mouse != null) {
			mouse.setCursorPosition(x, y);
		}
        //MacOSXMouseEventQueue.nWarpCursor(x, y);
	}

	public void setNativeCursor(Object handle) throws LWJGLException {
	}

	public int getMinCursorSize() {
		return AWTUtil.getMinCursorSize();
	}

	public int getMaxCursorSize() {
		return AWTUtil.getMaxCursorSize();
	}

	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
        this.keyboard = new MacOSXNativeKeyboard(window);
        keyboard.register();
	}

	public void destroyKeyboard() {
		if (keyboard != null) {
			keyboard.unregister();
		}
		keyboard = null;
	}

	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		keyboard.poll(keyDownBuffer);
	}

	public void readKeyboard(ByteBuffer buffer) {
		keyboard.copyEvents(buffer);
	}

	/** Native cursor handles */
	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		return AWTUtil.createCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
	}

	public void destroyCursor(Object cursor_handle) {
	}

	public int getPbufferCapabilities() {
		if (LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 3))
			return Pbuffer.PBUFFER_SUPPORTED;
		else
			return 0;
	}

	public boolean isBufferLost(PeerInfo handle) {
		return false;
	}

	public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs) throws LWJGLException {
		return new MacOSXPbufferPeerInfo(width, height, pixel_format, attribs);
	}

	public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
		throw new UnsupportedOperationException();
	}

	public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Sets one or more icons for the Display.
	 * <ul>
	 * <li>On Windows you should supply at least one 16x16 icon and one 32x32.</li>
	 * <li>Linux (and similar platforms) expect one 32x32 icon.</li>
	 * <li>Mac OS X should be supplied one 128x128 icon</li>
	 * </ul>
	 * The implementation will use the supplied ByteBuffers with image data in RGBA and perform any conversions nescesarry for the specific platform.
	 *
	 * @param icons Array of icons in RGBA mode
	 * @return number of icons used.
	 */
	public int setIcon(ByteBuffer[] icons) {
/*		int size = 0;
		int biggest = -1;

		for (int i=0;i<icons.length;i++) {
			if (icons[i].remaining() > size) {
				biggest = i;
				size = icons[i].remaining();
			}
		}

		if (biggest == -1) {
			return 0;
		}

		int width;
		int height;

		IntBuffer biggest_icon = icons[biggest].asIntBuffer();
		int[] imageData = new int[biggest_icon.remaining()];
		width = height = (int) Math.sqrt(imageData.length);
		biggest_icon.get(imageData);

		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		img.setRGB(0, 0, width, height, imageData, 0, width);
		frame.setIconImage(img);

		return 1;*/
		// Don't use any icon, since Mac OS X windows don't have window icons
		return 0;
	}
	
	public int getX() {
        return x;
	}

	public int getY() {
        return y;
	}

	public int getWidth() {
        return nGetWidth(window);
	}
	
	public int getHeight() {
        return nGetHeight(window);
	}

    public boolean isInsideWindow() {
    		return true;
    }

    public void setResizable(boolean resizable) {
        nSetResizable(window, resizable);
	}

	public boolean wasResized() {
        return nWasResized(window);
	}

}
