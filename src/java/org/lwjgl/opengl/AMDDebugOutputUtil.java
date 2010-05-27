package org.lwjgl.opengl;

import org.lwjgl.opengl.AMDDebugOutputCallback.Handler;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * This class handles AMDDebugOutputCallback.Handler registration and notification.
 * We could have put this in AMDDebugOutputCallback, but we need to compile it for
 * the generator. Registration is done reflectively in the AMDDebugOutputCallback
 * constructor.
 *
 * @author Spasi
 */
final class AMDDebugOutputUtil {

	private static final Map handlers = new WeakHashMap();

	private AMDDebugOutputUtil() {}

	public static void registerHandler(final Handler handler) {
		final Context ctx = Context.getCurrentContext();
		if ( ctx == null )
			throw new IllegalStateException("No context is current.");

		if ( !ctx.getContextAttribs().isDebug() )
			throw new IllegalStateException("The current context is not a debug context.");

		if ( !GLContext.getCapabilities().GL_AMD_debug_output  )
			throw new IllegalStateException("AMD_debug_output is not supported.");

		handlers.put(ctx, handler);
	}

	/**
	 * This method is called by native code. If finds the callback handler associated
	 * with the current Thread and calls its {@code handleMessage} method.
	 *
	 * @param id        the message ID
	 * @param category  the message category
	 * @param severity  the message severity
	 * @param message   the string representation of the message.
	 * @param userParam the user-specified data specified in glDebugMessageCallbackAMD. For the current implementation this is always null and we ignore it.
	 */
	private static void messageCallback(final int id, final int category, final int severity, final String message, final ByteBuffer userParam) {
		synchronized ( GlobalLock.lock ) {
			final Context ctx = Context.getCurrentContext();
			if ( ctx == null )
				return;

			final Handler handler = (Handler)handlers.get(ctx);
			if ( handler != null )
				handler.handleMessage(id, category, severity, message);
		}
	}

}
