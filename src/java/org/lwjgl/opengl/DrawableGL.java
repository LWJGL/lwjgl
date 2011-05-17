package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.PointerBuffer;

import static org.lwjgl.opengl.GL11.*;

/** @author Spasi */
abstract class DrawableGL implements DrawableLWJGL {

	/** The PixelFormat used to create the drawable. */
	protected PixelFormat pixel_format;

	/** Handle to the native GL rendering context */
	protected PeerInfo peer_info;

	/** The OpenGL Context. */
	protected ContextGL context;

	protected DrawableGL() {
	}

	public void setPixelFormat(final PixelFormatLWJGL pf) throws LWJGLException {
		this.pixel_format = (PixelFormat)pf;
		this.peer_info = Display.getImplementation().createPeerInfo(pixel_format);
	}

	public PixelFormatLWJGL getPixelFormat() {
		return pixel_format;
	}

	public ContextGL getContext() {
		synchronized ( GlobalLock.lock ) {
			return context;
		}
	}

	public ContextGL createSharedContext() throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			checkDestroyed();
			return new ContextGL(peer_info, context.getContextAttribs(), context);
		}
	}

	public void checkGLError() {
		Util.checkGLError();
	}

	public void setSwapInterval(final int swap_interval) {
		ContextGL.setSwapInterval(swap_interval);
	}

	public void swapBuffers() throws LWJGLException {
		ContextGL.swapBuffers();
	}

	public void initContext(final float r, final float g, final float b) {
		// set background clear color
		glClearColor(r, g, b, 0.0f);
		// Clear window to avoid the desktop "showing through"
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public boolean isCurrent() throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			checkDestroyed();
			return context.isCurrent();
		}
	}

	public void makeCurrent() throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			checkDestroyed();
			context.makeCurrent();
		}
	}

	public void releaseContext() throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			checkDestroyed();
			if ( context.isCurrent() )
				context.releaseCurrent();
		}
	}

	public void destroy() {
		synchronized ( GlobalLock.lock ) {
			if ( context == null )
				return;

			try {
				releaseContext();

				context.forceDestroy();
				context = null;

				if ( peer_info != null ) {
					peer_info.destroy();
					peer_info = null;
				}
			} catch (LWJGLException e) {
				LWJGLUtil.log("Exception occurred while destroying Drawable: " + e);
			}
		}
	}

	public void setCLSharingProperties(final PointerBuffer properties) throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			checkDestroyed();
			context.setCLSharingProperties(properties);
		}
	}

	protected final void checkDestroyed() {
		if ( context == null )
			throw new IllegalStateException("The Drawable has no context available.");
	}

}
