package org.lwjgl.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.PointerBuffer;

/**
 * @author Spasi
 */
abstract class AbstractDrawable implements DrawableLWJGL {

	/** Handle to the native GL rendering context */
	protected PeerInfo peer_info;

	/** The OpenGL Context. */
	protected Context context;

	protected AbstractDrawable() {
	}

	public Context getContext() {
		synchronized ( GlobalLock.lock ) {
			return context;
		}
	}

	public Context createSharedContext() throws LWJGLException {
		synchronized ( GlobalLock.lock ) {
			checkDestroyed();
			return new Context(peer_info, context.getContextAttribs(), context);
		}
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
				Context.releaseCurrentContext();
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
