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

import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.lang.reflect.Method;
import java.nio.*;
import java.util.concurrent.atomic.AtomicBoolean;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.MemoryUtil;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengles.EGL;

import javax.swing.*;

final class WindowsDisplay implements DisplayImplementation {
	private static final int GAMMA_LENGTH = 256;

	private static final int WM_WINDOWPOSCHANGED              = 0x0047;
	private static final int WM_MOVE                          = 0x0003;
	private static final int WM_CANCELMODE                    = 0x001F;
	private static final int WM_MOUSEMOVE                     = 0x0200;
	private static final int WM_LBUTTONDOWN                   = 0x0201;
	private static final int WM_LBUTTONUP                     = 0x0202;
	private static final int WM_LBUTTONDBLCLK                 = 0x0203;
	private static final int WM_RBUTTONDOWN                   = 0x0204;
	private static final int WM_RBUTTONUP                     = 0x0205;
	private static final int WM_RBUTTONDBLCLK                 = 0x0206;
	private static final int WM_MBUTTONDOWN                   = 0x0207;
	private static final int WM_MBUTTONUP                     = 0x0208;
	private static final int WM_MBUTTONDBLCLK                 = 0x0209;
	private static final int WM_XBUTTONDOWN                   = 0x020B;
	private static final int WM_XBUTTONUP                     = 0x020C;
	private static final int WM_XBUTTONDBLCLK                 = 0x020D;
	private static final int WM_MOUSEWHEEL                    = 0x020A;
	private static final int WM_CAPTURECHANGED                = 0x0215;
	private static final int WM_MOUSELEAVE                    = 0x02A3;
	private static final int WM_ENTERSIZEMOVE                 = 0x0231;
	private static final int WM_EXITSIZEMOVE                  = 0x0232;
	private static final int WM_SIZING                        = 0x0214;
	private static final int WM_KEYDOWN						  = 256;
	private static final int WM_KEYUP						  = 257;
	private static final int WM_SYSKEYUP					  = 261;
	private static final int WM_SYSKEYDOWN					  = 260;
	private static final int WM_SYSCHAR                          = 262;
	private static final int WM_CHAR                          = 258;
	private static final int WM_GETICON						  = 0x007F;
	private static final int WM_SETICON						  = 0x0080;
	private static final int WM_SETCURSOR                     = 0x0020;
	private static final int WM_MOUSEACTIVATE                 = 0x0021;

	private static final int WM_QUIT						  = 0x0012;
	private static final int WM_SYSCOMMAND					  = 0x0112;
	private static final int WM_PAINT 						  = 0x000F;
	private static final int WM_KILLFOCUS                     = 8;
	private static final int WM_SETFOCUS                      = 7;

	private static final int SC_SIZE          = 0xF000;
	private static final int SC_MOVE          = 0xF010;
	private static final int SC_MINIMIZE      = 0xF020;
	private static final int SC_MAXIMIZE      = 0xF030;
	private static final int SC_NEXTWINDOW    = 0xF040;
	private static final int SC_PREVWINDOW    = 0xF050;
	private static final int SC_CLOSE         = 0xF060;
	private static final int SC_VSCROLL       = 0xF070;
	private static final int SC_HSCROLL       = 0xF080;
	private static final int SC_MOUSEMENU     = 0xF090;
	private static final int SC_KEYMENU       = 0xF100;
	private static final int SC_ARRANGE       = 0xF110;
	private static final int SC_RESTORE       = 0xF120;
	private static final int SC_TASKLIST      = 0xF130;
	private static final int SC_SCREENSAVE    = 0xF140;
	private static final int SC_HOTKEY        = 0xF150;
	private static final int SC_DEFAULT       = 0xF160;
	private static final int SC_MONITORPOWER  = 0xF170;
	private static final int SC_CONTEXTHELP   = 0xF180;
	private static final int SC_SEPARATOR     = 0xF00F;

	static final int SM_CXCURSOR      = 13;
	static final int SM_CYCURSOR      = 14;
	static final int SM_CMOUSEBUTTONS      = 43;
	static final int SM_MOUSEWHEELPRESENT = 75;

	private static final int SIZE_RESTORED        = 0;
	private static final int SIZE_MINIMIZED       = 1;
	private static final int SIZE_MAXIMIZED       = 2;
	private static final int WM_SIZE          = 0x0005;
	private static final int WM_ACTIVATE          = 0x0006;
	private static final int     WA_INACTIVE      = 0;
	private static final int     WA_ACTIVE        = 1;
	private static final int     WA_CLICKACTIVE   = 2;
	private static final int SW_NORMAL			  = 1;
	private static final int SW_SHOWMINNOACTIVE   = 7;
	private static final int SW_SHOWDEFAULT       = 10;
	private static final int SW_RESTORE           = 9;
	private static final int SW_MAXIMIZE          = 3;

	private static final int ICON_SMALL           = 0;
	private static final int ICON_BIG           = 1;

	private static final IntBuffer rect_buffer = BufferUtils.createIntBuffer(4);
	private static final Rect rect = new Rect();

	private static final long HWND_TOP 			= 0;
	private static final long HWND_BOTTOM 		= 1;
	private static final long HWND_TOPMOST 		= -1;
	private static final long HWND_NOTOPMOST 	= -2;

	private static final int SWP_NOSIZE 		= 0x0001;
	private static final int SWP_NOMOVE 		= 0x0002;
	private static final int SWP_NOZORDER 		= 0x0004;
	private static final int SWP_FRAMECHANGED 	= 0x0020;

	private static final int GWL_STYLE = -16;
	private static final int GWL_EXSTYLE = -20;

	private static final int WS_THICKFRAME 		= 0x00040000;
	private static final int WS_MAXIMIZEBOX		= 0x00010000;

	private static final int HTCLIENT			= 0x01;

	private static final int MK_XBUTTON1		= 0x0020;
	private static final int MK_XBUTTON2		= 0x0040;
	private static final int XBUTTON1			= 0x0001;
	private static final int XBUTTON2			= 0x0002;

	private static WindowsDisplay current_display;

	private static boolean cursor_clipped;
	private WindowsDisplayPeerInfo peer_info;
	private Object current_cursor;

	private static boolean hasParent;

	private Canvas parent;
	private long parent_hwnd;
	private FocusAdapter parent_focus_tracker;
	private AtomicBoolean parent_focused;

	private WindowsKeyboard keyboard;
	private WindowsMouse mouse;

	private boolean close_requested;
	private boolean is_dirty;

	private ByteBuffer current_gamma;
	private ByteBuffer saved_gamma;
	private DisplayMode current_mode;

	private boolean mode_set;
	private boolean isMinimized;
	private boolean isFocused;
	private boolean redoMakeContextCurrent;
	private boolean inAppActivate;
	private boolean resized;
	private boolean resizable;
	private int x;
	private int y;
	private int width;
	private int height;

	private long hwnd;
	private long hdc;

	private long small_icon;
	private long large_icon;
		private boolean iconsLoaded;

	private int captureMouse = -1;
        private boolean mouseInside;

	static {
		try {
			Method windowProc = WindowsDisplay.class.getDeclaredMethod("handleMessage", long.class, int.class, long.class, long.class, long.class);
			setWindowProc(windowProc);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	WindowsDisplay() {
		current_display = this;
	}

	public void createWindow(DrawableLWJGL drawable, DisplayMode mode, Canvas parent, int x, int y) throws LWJGLException {
		this.parent = parent;
		hasParent = parent != null;
		parent_hwnd = parent != null ? getHwnd(parent) : 0;
		this.hwnd = nCreateWindow(x, y, mode.getWidth(), mode.getHeight(), Display.isFullscreen() || isUndecorated(), parent != null, parent_hwnd);
		if ( Display.isResizable() && parent == null ) {
			setResizable(true);
		}

		if (hwnd == 0) {
			throw new LWJGLException("Failed to create window");
		}
		this.hdc = getDC(hwnd);
		if (hdc == 0) {
			nDestroyWindow(hwnd);
			throw new LWJGLException("Failed to get dc");
		}

		try {
			if ( drawable instanceof DrawableGL ) {
				int format = WindowsPeerInfo.choosePixelFormat(getHdc(), 0, 0, (PixelFormat)drawable.getPixelFormat(), null, true, true, false, true);
				WindowsPeerInfo.setPixelFormat(getHdc(), format);
			} else {
				peer_info = new WindowsDisplayPeerInfo(true);
				((DrawableGLES)drawable).initialize(hwnd, hdc, EGL.EGL_WINDOW_BIT, (org.lwjgl.opengles.PixelFormat)drawable.getPixelFormat());
			}
			peer_info.initDC(getHwnd(), getHdc());
			showWindow(getHwnd(), SW_SHOWDEFAULT);

			updateWidthAndHeight();

			if ( parent == null ) {
				setForegroundWindow(getHwnd());
			} else {
				parent_focused = new AtomicBoolean(false);
				parent.addFocusListener(parent_focus_tracker = new FocusAdapter() {
					public void focusGained(FocusEvent e) {
						parent_focused.set(true);
						clearAWTFocus();
					}
				});
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						clearAWTFocus();
					}
				});
			}
			grabFocus();
		} catch (LWJGLException e) {
			nReleaseDC(hwnd, hdc);
			nDestroyWindow(hwnd);
			throw e;
		}
	}

	private void updateWidthAndHeight() {
		getClientRect(hwnd, rect_buffer);
		rect.copyFromBuffer(rect_buffer);
		width = rect.right - rect.left;
		height = rect.bottom - rect.top;
	}

	private static native long nCreateWindow(int x, int y, int width, int height, boolean undecorated, boolean child_window, long parent_hwnd) throws LWJGLException;

	private static boolean isUndecorated() {
		return Display.getPrivilegedBoolean("org.lwjgl.opengl.Window.undecorated");
	}

	private static long getHwnd(Canvas parent) throws LWJGLException {
		AWTCanvasImplementation awt_impl = AWTGLCanvas.createImplementation();
		WindowsPeerInfo parent_peer_info = (WindowsPeerInfo)awt_impl.createPeerInfo(parent, null, null);
		ByteBuffer parent_peer_info_handle = parent_peer_info.lockAndGetHandle();
		try {
			return parent_peer_info.getHwnd();
		} finally {
			parent_peer_info.unlock();
		}
	}

	public void destroyWindow() {
		if ( parent != null ) {
			parent.removeFocusListener(parent_focus_tracker);
			parent_focus_tracker = null;
		}

		nReleaseDC(hwnd, hdc);
		nDestroyWindow(hwnd);
		freeLargeIcon();
		freeSmallIcon();
		resetCursorClipping();

		// reset state
		close_requested = false;
		is_dirty = false;
		isMinimized = false;
		isFocused = false;
		redoMakeContextCurrent = false;
		resizable = false;
		mouseInside = false;
	}
	private static native void nReleaseDC(long hwnd, long hdc);
	private static native void nDestroyWindow(long hwnd);
	static void resetCursorClipping() {
		if (cursor_clipped) {
			try {
				clipCursor(null);
			} catch (LWJGLException e) {
				LWJGLUtil.log("Failed to reset cursor clipping: " + e);
			}
			cursor_clipped = false;
		}
	}

	private static void getGlobalClientRect(long hwnd, Rect rect) {
		rect_buffer.put(0, 0).put(1, 0);
		clientToScreen(hwnd, rect_buffer);
		int offset_x = rect_buffer.get(0);
		int offset_y = rect_buffer.get(1);
		getClientRect(hwnd, rect_buffer);
		rect.copyFromBuffer(rect_buffer);
		rect.offset(offset_x, offset_y);
	}

	static void setupCursorClipping(long hwnd) throws LWJGLException {
		cursor_clipped = true;
		getGlobalClientRect(hwnd, rect);
		rect.copyToBuffer(rect_buffer);
		clipCursor(rect_buffer);
	}
	private static native void clipCursor(IntBuffer rect) throws LWJGLException;

	public void switchDisplayMode(DisplayMode mode) throws LWJGLException {
		nSwitchDisplayMode(mode);
		current_mode = mode;
		mode_set = true;
	}
	private static native void nSwitchDisplayMode(DisplayMode mode) throws LWJGLException;

	/*
	 * Called when the application is alt-tabbed to or from
	 */
	private void appActivate(boolean active, long millis) {
		if (inAppActivate) {
			return;
		}
		inAppActivate = true;
		isFocused = active;
		if (active) {
			if (Display.isFullscreen()) {
				restoreDisplayMode();
			}
			if (parent == null) {
				setForegroundWindow(getHwnd());
			}
			setFocus(getHwnd());
			redoMakeContextCurrent = true;
		} else {
			if ( keyboard != null )
				keyboard.releaseAll(millis);
			if ( Display.isFullscreen() ) {
				showWindow(getHwnd(), SW_SHOWMINNOACTIVE);
				resetDisplayMode();
			}
		}
		updateCursor();
		inAppActivate = false;
	}
	private static native void showWindow(long hwnd, int mode);
	private static native void setForegroundWindow(long hwnd);
	private static native void setFocus(long hwnd);

	private void clearAWTFocus() {
		// This is needed so that the last focused component AWT remembers is NOT our Canvas
		WindowsDisplay.this.parent.setFocusable(false);
		WindowsDisplay.this.parent.setFocusable(true);

		// Clear AWT focus owner
		KeyboardFocusManager.getCurrentKeyboardFocusManager().clearGlobalFocusOwner();
	}

	private void grabFocus() {
		if ( parent == null )
			setFocus(getHwnd());
		else
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					parent.requestFocus();
				}
			});
	}

	private void restoreDisplayMode() {
		try {
			doSetGammaRamp(current_gamma);
		} catch (LWJGLException e) {
			LWJGLUtil.log("Failed to restore gamma: " + e.getMessage());
		}

		if (!mode_set) {
			mode_set = true;
			try {
				nSwitchDisplayMode(current_mode);
			} catch (LWJGLException e) {
				LWJGLUtil.log("Failed to restore display mode: " + e.getMessage());
			}
		}
	}

	public void resetDisplayMode() {
		try {
			doSetGammaRamp(saved_gamma);
		} catch (LWJGLException e) {
			LWJGLUtil.log("Failed to reset gamma ramp: " + e.getMessage());
		}
		current_gamma = saved_gamma;
		if (mode_set) {
			mode_set = false;
			nResetDisplayMode();
		}
		resetCursorClipping();
	}
	private static native void nResetDisplayMode();

	public int getGammaRampLength() {
		return GAMMA_LENGTH;
	}

	public void setGammaRamp(FloatBuffer gammaRamp) throws LWJGLException {
		doSetGammaRamp(convertToNativeRamp(gammaRamp));
	}
	private static native ByteBuffer convertToNativeRamp(FloatBuffer gamma_ramp) throws LWJGLException;
	private static native ByteBuffer getCurrentGammaRamp() throws LWJGLException;

	private void doSetGammaRamp(ByteBuffer native_gamma) throws LWJGLException {
		nSetGammaRamp(native_gamma);
		current_gamma = native_gamma;
	}
	private static native void nSetGammaRamp(ByteBuffer native_ramp) throws LWJGLException;

	public String getAdapter() {
		try {
			String maxObjNo = WindowsRegistry.queryRegistrationKey(
					WindowsRegistry.HKEY_LOCAL_MACHINE,
					"HARDWARE\\DeviceMap\\Video",
					"MaxObjectNumber");
			int maxObjectNumber = maxObjNo.charAt(0);
			String vga_driver_value = "";
			for(int i=0;i<maxObjectNumber;i++) {
				String adapter_string = WindowsRegistry.queryRegistrationKey(
						WindowsRegistry.HKEY_LOCAL_MACHINE,
						"HARDWARE\\DeviceMap\\Video",
						"\\Device\\Video" + i);
				String root_key = "\\registry\\machine\\";
				if (adapter_string.toLowerCase().startsWith(root_key)) {
					String driver_value = WindowsRegistry.queryRegistrationKey(
							WindowsRegistry.HKEY_LOCAL_MACHINE,
							adapter_string.substring(root_key.length()),
							"InstalledDisplayDrivers");
					if(driver_value.toUpperCase().startsWith("VGA")) {
						vga_driver_value = driver_value;
					} else if(!driver_value.toUpperCase().startsWith("RDP") && !driver_value.toUpperCase().startsWith("NMNDD")) {
						return driver_value;
					}
				}
			}
			if(!vga_driver_value.equals("")) {
				return vga_driver_value;
			}
		} catch (LWJGLException e) {
			LWJGLUtil.log("Exception occurred while querying registry: " + e);
		}
		return null;
	}

	public String getVersion() {
		String driver = getAdapter();
		if (driver != null) {
			String[] drivers = driver.split(",");
			if(drivers.length>0) {
				WindowsFileVersion version = nGetVersion(drivers[0] + ".dll");
				if (version != null)
					return version.toString();
			}
		}
		return null;
	}
	private native WindowsFileVersion nGetVersion(String driver);

	public DisplayMode init() throws LWJGLException {
		current_gamma = saved_gamma = getCurrentGammaRamp();
		return current_mode = getCurrentDisplayMode();
	}
	private static native DisplayMode getCurrentDisplayMode() throws LWJGLException;

	public void setTitle(String title) {
		ByteBuffer buffer = MemoryUtil.encodeUTF16(title);
		nSetTitle(hwnd, MemoryUtil.getAddress0(buffer));
	}
	private static native void nSetTitle(long hwnd, long title);

	public boolean isCloseRequested() {
		boolean saved = close_requested;
		close_requested = false;
		return saved;
	}

	public boolean isVisible() {
		return !isMinimized;
	}

	public boolean isActive() {
		return isFocused;
	}

	public boolean isDirty() {
		boolean saved = is_dirty;
		is_dirty = false;
		return saved;
	}

	public PeerInfo createPeerInfo(PixelFormat pixel_format, ContextAttribs attribs) throws LWJGLException {
		peer_info = new WindowsDisplayPeerInfo(false);
		return peer_info;
	}

	public void update() {
		nUpdate();

		if ( !isFocused && parent != null && parent_focused.compareAndSet(true, false) ) {
			setFocus(getHwnd());
		}

		if (redoMakeContextCurrent) {
			redoMakeContextCurrent = false;
			/**
			 * WORKAROUND:
			 * Making the context current (redundantly) when the window
			 * is maximized helps some gfx cards recover from fullscreen
			 */
			try {
				Context context = ((DrawableLWJGL)Display.getDrawable()).getContext();
				if (context != null && context.isCurrent())
					context.makeCurrent();
			} catch (LWJGLException e) {
				LWJGLUtil.log("Exception occurred while trying to make context current: " + e);
			}
		}
	}
	private static native void nUpdate();

	public void reshape(int x, int y, int width, int height) {
		nReshape(getHwnd(), x, y, width, height, Display.isFullscreen() || isUndecorated(), parent != null);
	}
	private static native void nReshape(long hwnd, int x, int y, int width, int height, boolean undecorated, boolean child);
	public native DisplayMode[] getAvailableDisplayModes() throws LWJGLException;

	/* Mouse */
	public boolean hasWheel() {
		return mouse.hasWheel();
	}

	public int getButtonCount() {
		return mouse.getButtonCount();
	}

	public void createMouse() throws LWJGLException {
		mouse = new WindowsMouse(getHwnd());
	}

	public void destroyMouse() {
		if (mouse != null)
			mouse.destroy();
		mouse = null;
	}

	public void pollMouse(IntBuffer coord_buffer, ByteBuffer buttons) {
		mouse.poll(coord_buffer, buttons, this);
	}

	public void readMouse(ByteBuffer buffer) {
		mouse.read(buffer);
	}

	public void grabMouse(boolean grab) {
		mouse.grab(grab);
		updateCursor();
	}

	public int getNativeCursorCapabilities() {
		return Cursor.CURSOR_ONE_BIT_TRANSPARENCY;
	}

	public void setCursorPosition(int x, int y) {
		getGlobalClientRect(getHwnd(), rect);
		int transformed_x = rect.left + x;
		int transformed_y = rect.bottom - 1 - y;
		nSetCursorPosition(transformed_x, transformed_y);
		setMousePosition(x, y);
	}
	private static native void nSetCursorPosition(int x, int y);

	public void setNativeCursor(Object handle) throws LWJGLException {
		current_cursor = handle;
		updateCursor();
	}

	private void updateCursor() {
		try {
			if (mouse != null && shouldGrab()) {
				centerCursor(hwnd);
				nSetNativeCursor(getHwnd(), mouse.getBlankCursor());
			} else
				nSetNativeCursor(getHwnd(), current_cursor);
		} catch (LWJGLException e) {
			LWJGLUtil.log("Failed to update cursor: " + e);
		}
		updateClipping();
	}
	static native void nSetNativeCursor(long hwnd, Object handle) throws LWJGLException;

	public int getMinCursorSize() {
		return getSystemMetrics(SM_CXCURSOR);
	}

	public int getMaxCursorSize() {
		return getSystemMetrics(SM_CXCURSOR);
	}

	static native int getSystemMetrics(int index);

	private static native long getDllInstance();

	private long getHwnd() {
		return hwnd;
	}

	private long getHdc() {
		return hdc;
	}

	private static native long getDC(long hwnd);
	private static native long getDesktopWindow();
	private static native long getForegroundWindow();

	static void centerCursor(long hwnd) {
		if (getForegroundWindow() != hwnd && !hasParent)
			return;
		getGlobalClientRect(hwnd, rect);
		int local_offset_x = rect.left;
		int local_offset_y = rect.top;
		/* -- This is wrong on multi-monitor setups
		getGlobalClientRect(getDesktopWindow(), rect2);
		Rect.intersect(rect, rect2, rect);
		*/
		int center_x = (rect.left + rect.right)/2;
		int center_y = (rect.top + rect.bottom)/2;
		nSetCursorPosition(center_x, center_y);
		int local_x = center_x - local_offset_x;
		int local_y = center_y - local_offset_y;
		if (current_display != null)
			current_display.setMousePosition(local_x, transformY(hwnd, local_y));
	}

	private void setMousePosition(int x, int y) {
		if (mouse != null)
			mouse.setPosition(x, y);
	}

	/* Keyboard */
	public void createKeyboard() throws LWJGLException {
		keyboard = new WindowsKeyboard();
	}

	public void destroyKeyboard() {
		keyboard = null;
	}

	public void pollKeyboard(ByteBuffer keyDownBuffer) {
		keyboard.poll(keyDownBuffer);
	}

	public void readKeyboard(ByteBuffer buffer) {
		keyboard.read(buffer);
	}

//	public native int isStateKeySet(int key);

	public static native ByteBuffer nCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, int images_offset, IntBuffer delays, int delays_offset) throws LWJGLException;

	public Object createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		return doCreateCursor(width, height, xHotspot, yHotspot, numImages, images, delays);
	}

	static Object doCreateCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		return nCreateCursor(width, height, xHotspot, yHotspot, numImages, images, images.position(), delays, delays != null ? delays.position() : -1);
	}

	public void destroyCursor(Object cursorHandle) {
		doDestroyCursor(cursorHandle);
	}
	static native void doDestroyCursor(Object cursorHandle);

	public int getPbufferCapabilities() {
		try {
		// Return the capabilities of a minimum pixel format
			return nGetPbufferCapabilities(new PixelFormat(0, 0, 0, 0, 0, 0, 0, 0, false));
		} catch (LWJGLException e) {
			LWJGLUtil.log("Exception occurred while determining pbuffer capabilities: " + e);
			return 0;
		}
	}
	private native int nGetPbufferCapabilities(PixelFormat format) throws LWJGLException;

	public boolean isBufferLost(PeerInfo handle) {
		return ((WindowsPbufferPeerInfo)handle).isBufferLost();
	}

	public PeerInfo createPbuffer(int width, int height, PixelFormat pixel_format, ContextAttribs attribs,
			IntBuffer pixelFormatCaps,
			IntBuffer pBufferAttribs) throws LWJGLException {
		return new WindowsPbufferPeerInfo(width, height, pixel_format, pixelFormatCaps, pBufferAttribs);
	}

	public void setPbufferAttrib(PeerInfo handle, int attrib, int value) {
		((WindowsPbufferPeerInfo)handle).setPbufferAttrib(attrib, value);
	}

	public void bindTexImageToPbuffer(PeerInfo handle, int buffer) {
		((WindowsPbufferPeerInfo)handle).bindTexImageToPbuffer(buffer);
	}

	public void releaseTexImageFromPbuffer(PeerInfo handle, int buffer) {
		((WindowsPbufferPeerInfo)handle).releaseTexImageFromPbuffer(buffer);
	}

	private void freeSmallIcon() {
		if (small_icon != 0) {
			destroyIcon(small_icon);
			small_icon = 0;
		}
	}

	private void freeLargeIcon() {
		if (large_icon != 0) {
			destroyIcon(large_icon);
			large_icon = 0;
		}
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
		boolean done_small = false;
		boolean done_large = false;
		int used = 0;

		int small_icon_size = 16;
		int large_icon_size = 32;
		for ( ByteBuffer icon : icons ) {
			int size = icon.limit() / 4;

			if ( (((int)Math.sqrt(size)) == small_icon_size) && (!done_small) ) {
				long small_new_icon = createIcon(small_icon_size, small_icon_size, icon.asIntBuffer());
				sendMessage(hwnd, WM_SETICON, ICON_SMALL, small_new_icon);
				freeSmallIcon();
				small_icon = small_new_icon;
				used++;
				done_small = true;
			}
			if ( (((int)Math.sqrt(size)) == large_icon_size) && (!done_large) ) {
				long large_new_icon = createIcon(large_icon_size, large_icon_size, icon.asIntBuffer());
				sendMessage(hwnd, WM_SETICON, ICON_BIG, large_new_icon);
				freeLargeIcon();
				large_icon = large_new_icon;
				used++;
				done_large = true;

				// Problem: The taskbar icon won't update until Windows sends a WM_GETICON to our window proc and we reply. But this method is usually called
				// on init and it might take a while before the next call to nUpdate (because of resources being loaded, etc). So we wait for the next
				// WM_GETICON message (usually received about 100ms after WM_SETICON) to make sure the taskbar icon has updated before we return to the user.
				// (We wouldn't need to do this if the event loop was running continuously on its own thread.)
				iconsLoaded = false;

				// Track how long the wait takes and give up at 500ms, just in case.
				long time = System.nanoTime();
				long MAX_WAIT = 500L * 1000L * 1000L;
				while ( true ) {
					nUpdate();
					if ( iconsLoaded || MAX_WAIT < System.nanoTime() - time )
						break;

					Thread.yield();
				}
			}
		}

		return used;
	}
	private static native long createIcon(int width, int height, IntBuffer icon);
	private static native void destroyIcon(long handle);
	private static native long sendMessage(long hwnd, long msg, long wparam, long lparam);
	private static native long setWindowLongPtr(long hwnd, int nindex, long longPtr);
	private static native long getWindowLongPtr(long hwnd, int nindex);
	private static native boolean setWindowPos(long hwnd, long hwnd_after, int x, int y, int cx, int cy, long uflags);

	private void handleMouseButton(int button, int state, long millis) {
		if (mouse != null) {
			mouse.handleMouseButton((byte)button, (byte)state, millis);

			// need to capture?
			if (captureMouse == -1 && button != -1 && state == 1) {
				captureMouse = button;
				nSetCapture(hwnd);
			}

			// done with capture?
			if(captureMouse != -1 && button == captureMouse && state == 0) {
				captureMouse = -1;
				nReleaseCapture();
			}
		}
	}

	private boolean shouldGrab() {
		return !isMinimized && isFocused && Mouse.isGrabbed();
	}

	private static native long nSetCapture(long hwnd);
	private static native boolean nReleaseCapture();

	private void handleMouseScrolled(int amount, long millis) {
		if (mouse != null)
			mouse.handleMouseScrolled(amount, millis);
	}

	private static native void getClientRect(long hwnd, IntBuffer rect);

	private void handleChar(long wParam, long lParam, long millis) {
		byte previous_state = (byte)((lParam >>> 30) & 0x1);
		byte state = (byte)(1 - ((lParam >>> 31) & 0x1));
		boolean repeat = state == previous_state;
		if (keyboard != null)
			keyboard.handleChar((int)(wParam & 0xFFFF), millis, repeat);
	}

	private void handleKeyButton(long wParam, long lParam, long millis) {
		if ( keyboard == null )
			return;

		byte previous_state = (byte)((lParam >>> 30) & 0x1);
		byte state = (byte)(1 - ((lParam >>> 31) & 0x1));
		boolean repeat = state == previous_state; // Repeat message
		byte extended = (byte)((lParam >>> 24) & 0x1);
		int scan_code = (int)((lParam >>> 16) & 0xFF);

		keyboard.handleKey((int)wParam, scan_code, extended != 0, state, millis, repeat);
	}

	private static int transformY(long hwnd, int y) {
		getClientRect(hwnd, rect_buffer);
		rect.copyFromBuffer(rect_buffer);
		return (rect.bottom - rect.top) - 1 - y;
	}

	private static native void clientToScreen(long hwnd, IntBuffer point);

	private static native void setWindowProc(Method windowProc);

	private static long handleMessage(long hwnd, int msg, long wParam, long lParam, long millis) {
		if (current_display != null)
			return current_display.doHandleMessage(hwnd, msg, wParam, lParam, millis);
		else
			return defWindowProc(hwnd, msg, wParam, lParam);
	}

	private static native long defWindowProc(long hwnd, int msg, long wParam, long lParam);

	private void updateClipping() {
		if ((Display.isFullscreen() || (mouse != null && mouse.isGrabbed())) && !isMinimized && isFocused && (getForegroundWindow() == getHwnd() || hasParent)) {
			try {
				setupCursorClipping(getHwnd());
			} catch (LWJGLException e) {
				LWJGLUtil.log("setupCursorClipping failed: " + e.getMessage());
			}
		} else {
			resetCursorClipping();
		}
	}

	private void setMinimized(boolean m) {
		if ( m != isMinimized ) {
			isMinimized = m;
			updateClipping();
		}
	}

	private long doHandleMessage(long hwnd, int msg, long wParam, long lParam, long millis) {
		/*switch ( msg ) {
			case 0x0:
			case 0x0020:
			case 0x0084:
			case WM_MOUSEMOVE:
				break;
			default:
				WindowsEventDebug.printMessage(msg, wParam, lParam);
		}*/

		if ( parent != null && !isFocused ) {
			switch ( msg ) {
				case WM_LBUTTONDOWN:
				case WM_RBUTTONDOWN:
				case WM_MBUTTONDOWN:
				case WM_XBUTTONDOWN:
					sendMessage(parent_hwnd, msg, wParam, lParam);
			}
		}

		switch (msg) {
			// disable screen saver and monitor power down messages which wreak havoc
			case WM_ACTIVATE:
				/*switch ((int)wParam) {
					case WA_ACTIVE:
					case WA_CLICKACTIVE:
						appActivate(true);
						break;
					case WA_INACTIVE:
						appActivate(false);
						break;
				}*/
				return 0L;
			case WM_SIZE:
				switch ((int)wParam) {
					case SIZE_RESTORED:
					case SIZE_MAXIMIZED:
						resized = true;
						updateWidthAndHeight();
						setMinimized(false);
						break;
					case SIZE_MINIMIZED:
						setMinimized(true);
						break;
				}
				break;
			case WM_SIZING:
				resized = true;
				updateWidthAndHeight();
				break;
			case WM_KILLFOCUS:
				appActivate(false, millis);
				return 0L;
			case WM_SETFOCUS:
				appActivate(true, millis);
				return 0L;
			case WM_MOUSEACTIVATE:
				if ( parent != null ) {
					if ( !isFocused )
						grabFocus();
					return 3L; // MA_NOACTIVATE
				}
				break;
			case WM_MOUSEMOVE:
				if ( mouse != null ) {
					int xPos = (short)(lParam & 0xFFFF);
					int yPos = transformY(getHwnd(), (short)(lParam >>> 16));
					mouse.handleMouseMoved(xPos, yPos, millis);
				}
				if ( !mouseInside ) {
					mouseInside = true;
					updateCursor();
					nTrackMouseEvent(hwnd);
				}
				return 0L;
			case WM_MOUSEWHEEL:
				int dwheel = (int)(short)((wParam >> 16) & 0xFFFF);
				handleMouseScrolled(dwheel, millis);
				return 0L;
			case WM_LBUTTONDOWN:
				handleMouseButton(0, 1, millis);
				return 0L;
			case WM_LBUTTONUP:
				handleMouseButton(0, 0, millis);
				return 0L;
			case WM_RBUTTONDOWN:
				handleMouseButton(1, 1, millis);
				return 0L;
			case WM_RBUTTONUP:
				handleMouseButton(1, 0, millis);
				return 0L;
			case WM_MBUTTONDOWN:
				handleMouseButton(2, 1, millis);
				return 0L;
			case WM_MBUTTONUP:
				handleMouseButton(2, 0, millis);
				return 0L;
			case WM_XBUTTONUP:
				if((wParam >> 16) == XBUTTON1) {
					handleMouseButton(3, 0, millis);
				} else {
					handleMouseButton(4, 0, millis);
				}
				return 1;
			case WM_XBUTTONDOWN:
				if((wParam & 0xFF) == MK_XBUTTON1) {
					handleMouseButton(3, 1, millis);
				} else {
					handleMouseButton(4, 1, millis);
				}
				return 1;
			case WM_SYSCHAR:
			case WM_CHAR:
				handleChar(wParam, lParam, millis);
				return 0L;
			case WM_SYSKEYUP:
				// Disable WM_SYSCOMMAND/SC_KEYMENU
				if ( wParam == WindowsKeycodes.VK_MENU || wParam == WindowsKeycodes.VK_F10 ) {
					handleKeyButton(wParam, lParam, millis);
					return 0L;
				}
				/* Fall through */
			case WM_KEYUP:
				// SysRq apparently only generates WM_KEYUP, so we'll fake a WM_KEYDOWN
				if (wParam == WindowsKeycodes.VK_SNAPSHOT && keyboard != null &&
						!keyboard.isKeyDown(org.lwjgl.input.Keyboard.KEY_SYSRQ)) {
					// Set key state to pressed
					long fake_lparam = lParam & ~(1 << 31);
					// Set key previous state to released
					fake_lparam &= ~(1 << 30);
					handleKeyButton(wParam, fake_lparam, millis);
				}
				/* Fall through */
			case WM_SYSKEYDOWN:
				/* Fall through */
			case WM_KEYDOWN:
				handleKeyButton(wParam, lParam, millis);
				break;
			case WM_QUIT:
				close_requested = true;
				return 0L;
			case WM_SYSCOMMAND:
				switch ((int)(wParam & 0xfff0)) {
					case SC_SCREENSAVE:
					case SC_MONITORPOWER:
						return 0L;
					case SC_CLOSE:
						close_requested = true;
						return 0L;
				}
				break;
			case WM_PAINT:
				is_dirty = true;
				break;
            case WM_MOUSELEAVE:
            	mouseInside = false;
	            break;
			case WM_CANCELMODE:
				nReleaseCapture();
				/* fall through */
			case WM_CAPTURECHANGED:
				if(captureMouse != -1) {
					handleMouseButton(captureMouse, 0, millis);
					captureMouse = -1;
				}
				return 0L;
			case WM_WINDOWPOSCHANGED:
				if(getWindowRect(hwnd, rect_buffer)) {
					rect.copyFromBuffer(rect_buffer);
					x = rect.left;
					y = rect.top;
				} else {
					LWJGLUtil.log("WM_WINDOWPOSCHANGED: Unable to get window rect");
				}
				break;
			case WM_GETICON:
				iconsLoaded = true;
				break;
		}

		return defWindowProc(hwnd, msg, wParam, lParam);
	}

	private native boolean getWindowRect(long hwnd, IntBuffer rectBuffer);

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private native boolean nTrackMouseEvent(long hwnd);

	public boolean isInsideWindow() {
		return mouseInside;
	}

	public void setResizable(boolean resizable) {
		if ( this.resizable == resizable )
			return;

		this.resized = false;
		this.resizable = resizable;

		int style = (int)getWindowLongPtr(hwnd, GWL_STYLE);
		int styleex = (int)getWindowLongPtr(hwnd, GWL_EXSTYLE);

		// update frame style
		setWindowLongPtr(
			hwnd,
			GWL_STYLE,
			style = resizable && !Display.isFullscreen()
			        ? (style | (WS_THICKFRAME | WS_MAXIMIZEBOX))
			        : (style & ~(WS_THICKFRAME | WS_MAXIMIZEBOX))
		);

		// from the existing client rect, determine the new window rect
		// based on the style changes - using AdjustWindowRectEx.
		getGlobalClientRect(hwnd, rect);
		rect.copyToBuffer(rect_buffer);
		adjustWindowRectEx(rect_buffer, style, false, styleex);
		rect.copyFromBuffer(rect_buffer);

		// Apply the style changes
		setWindowPos(
			hwnd, 0L,
			rect.left,
			rect.top,
			rect.right - rect.left,
			rect.bottom - rect.top,
			SWP_NOZORDER | SWP_FRAMECHANGED
		);

		updateWidthAndHeight();
	}

	private native boolean adjustWindowRectEx(IntBuffer rectBuffer, int style, boolean menu, int styleex);

	public boolean wasResized() {
		if(resized) {
			resized = false;
			return true;
		}
		return false;
	}

	public float getPixelScaleFactor() {
		return 1f;
	}

	private static final class Rect {

		public int
			left,
			top,
			right,
			bottom;

		public void copyToBuffer(IntBuffer buffer) {
			buffer
				.put(0, left)
				.put(1, top)
				.put(2, right)
				.put(3, bottom);
		}

		public void copyFromBuffer(IntBuffer buffer) {
			left = buffer.get(0);
			top = buffer.get(1);
			right = buffer.get(2);
			bottom = buffer.get(3);
		}

		public void offset(int offset_x, int offset_y) {
			left += offset_x;
			top += offset_y;
			right += offset_x;
			bottom += offset_y;
		}

		public static void intersect(Rect r1, Rect r2, Rect dst) {
			dst.left = Math.max(r1.left, r2.left);
			dst.top = Math.max(r1.top, r2.top);
			dst.right = Math.min(r1.right, r2.right);
			dst.bottom = Math.min(r1.bottom, r2.bottom);
		}

		public String toString() {
			return "Rect: left = " + left + " top = " + top + " right = " + right + " bottom = " + bottom + ", width: " + (right - left) + ", height: " + (bottom - top);
		}
	}
}