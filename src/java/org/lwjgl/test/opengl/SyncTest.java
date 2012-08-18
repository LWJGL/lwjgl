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
package org.lwjgl.test.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.*;

import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL32.*;

/** @author spasi <spasi@users.sourceforge.net> */
public final class SyncTest {

	private SyncTest() {
	}

	public static void main(String[] args) {
		runTest(args);
		cleanup();
		System.exit(0);
	}

	private static void runTest(String[] args) {
		if ( args.length < 2 )
			argsError("Insufficient number of arguments.");

		int clears = 1;
		int timeout = 0;

		try {
			clears = Integer.parseInt(args[0]);
			timeout = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			argsError("Invalid number format.");
		}

		ContextAttribs ca = new ContextAttribs();

		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			DisplayMode displayMode = chooseMode(modes, 1024, 768);
			if ( displayMode == null )
				displayMode = chooseMode(modes, 800, 600);
			if ( displayMode == null )
				displayMode = chooseMode(modes, 640, 480);
			if ( displayMode == null )
				kill("Failed to set an appropriate display mode.");

			System.out.println("Setting display mode to: " + displayMode);
			Display.setDisplayMode(displayMode);
			Display.create(new PixelFormat(8, 24, 0), ca);
		} catch (LWJGLException e) {
			kill(e.getMessage());
		}

		System.out.println("\n---------\n");

		final String version = glGetString(GL_VERSION);

		System.out.println("GL Version: " + version);
		System.out.println("ARB_sync: " + GLContext.getCapabilities().GL_ARB_sync);

		if ( !GLContext.getCapabilities().OpenGL32 && !GLContext.getCapabilities().GL_ARB_sync )
			kill("OpenGL3.2 or ARB_sync support is required for this test.");

		System.out.println("\n---------\n");

		System.out.println("Clearing the framebuffer a gazillion times...");

		Random rand = new Random(System.currentTimeMillis());
		for ( int i = 0; i < clears; i++ ) {
			glClearColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1.0f);
			glClear(GL_COLOR_BUFFER_BIT);
		}

		GLSync sync = glFenceSync(GL_SYNC_GPU_COMMANDS_COMPLETE, 0);

		System.out.println("\nWaiting on fence...");
		long time = Sys.getTime();
		int status = glClientWaitSync(sync, 0, timeout < 0 ? GL_TIMEOUT_IGNORED : timeout * 1000 * 1000);
		System.out.println("\nFence sync complete after: " + ((Sys.getTime() - time) / (double)Sys.getTimerResolution()) + " seconds.");
		System.out.print("\nWait Status: ");
		switch ( status ) {
			case GL_ALREADY_SIGNALED:
				System.out.println("ALREADY_SIGNALED");
				break;
			case GL_CONDITION_SATISFIED:
				System.out.println("CONDITION_SATISFIED");
				break;
			case GL_TIMEOUT_EXPIRED:
				System.out.println("TIMEOUT_EXPIRED");
				break;
			case GL_WAIT_FAILED:
				System.out.println("WAIT_FAILED");
				break;
			default:
				System.out.println("Unexpected wait status: 0x" + Integer.toHexString(status));
		}

		System.out.println("Sync Status: " + (glGetSynci(sync, GL_SYNC_STATUS) == GL_UNSIGNALED ? "UNSIGNALED" : "SIGNALED"));

		glDeleteSync(sync);

		int error = glGetError();
		if ( error != 0 )
			System.out.println("\nTest failed with OpenGL error: " + error);
		else
			System.out.println("\nTest completed successfully.");
	}

	private static DisplayMode chooseMode(DisplayMode[] modes, int width, int height) {
		DisplayMode bestMode = null;

		for ( DisplayMode mode : modes ) {
			if ( mode.getWidth() == width && mode.getHeight() == height && mode.getFrequency() <= 85 ) {
				if ( bestMode == null || (mode.getBitsPerPixel() >= bestMode.getBitsPerPixel() && mode.getFrequency() > bestMode.getFrequency()) )
					bestMode = mode;
			}
		}

		return bestMode;
	}

	private static void cleanup() {
		if ( Display.isCreated() )
			Display.destroy();
	}

	private static void argsError(final String msg) {
		System.out.println("\nInvalid arguments error: " + msg);
		System.out.println("\nUsage: SyncTest <clears> <timeout>:\n");
		System.out.println("clears\t- Number of times to clear the framebuffer.");
		System.out.println("timeout\t- WaitSync timeout in milliseconds.");

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason) {
		System.out.println("The SyncTest program was terminated because an error occured.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		cleanup();
		System.exit(-1);
	}

}