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
/*
 * Created by LWJGL.
 * User: spasi
 * Date: 2009-04-04
 * Time: 21:20:24 pm
 */

package org.lwjgl.test.opengl;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

import java.util.StringTokenizer;
import java.util.regex.Pattern;

public final class VersionTest {

	private VersionTest() {
	}

	public static void main(String[] args) {
		initialize(args);
		cleanup();
		System.exit(0);
	}

	private static void initialize(String[] args) {
		if ( args.length < 2 )
			argsError("Insufficient number of arguments");

		int majorInput = 1;
		int minorInput = 0;

		try {
			majorInput = Integer.parseInt(args[0]);
			minorInput = Integer.parseInt(args[1]);
		} catch (NumberFormatException e) {
			argsError("Invalid number format");
		}

		ContextAttribs ca = new ContextAttribs(majorInput, minorInput);

		if ( 2 < args.length ) {
			for ( int i = 2; i < args.length; i++ ) {
				if ( Pattern.matches("[0-9]+", args[i]) )
					ca = ca.withLayer(Integer.parseInt(args[i]));
				else if ( "debug".equalsIgnoreCase(args[i]) )
					ca = ca.withDebug(true);
				else if ( "fc".equalsIgnoreCase(args[i]) )
					ca = ca.withForwardCompatible(true);
				else if ( "core".equalsIgnoreCase(args[i]) )
					ca = ca.withProfileCore(true);
				else if ( "compatibility".equalsIgnoreCase(args[i]) )
					ca = ca.withProfileCompatibility(true);
				else
					argsError("Unknown argument: \'" + args[i] + "\'");
			}
		}

		try {
			DisplayMode[] modes = Display.getAvailableDisplayModes();

			DisplayMode displayMode;

			displayMode = chooseMode(modes, 1024, 768);
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

		final String version = GL11.glGetString(GL11.GL_VERSION);

		System.out.print("GL Version requested: " + majorInput + '.' + minorInput);
		if ( ca.isProfileCore() )
			System.out.print(" - Core Profile");
		else if ( ca.isProfileCompatibility() )
			System.out.print(" - Compatibility Profile");
		System.out.println("\nGL Version returned : " + version);

		final StringTokenizer version_tokenizer = new StringTokenizer(version, ". ");

		int majorVersion = Integer.parseInt(version_tokenizer.nextToken());
		int minorVersion = Integer.parseInt(version_tokenizer.nextToken());

		if ( majorVersion != majorInput || minorVersion != minorInput ) {
			if ( majorInput == 1 && minorInput == 0 )
				System.out.println("\tThe maximum supported version has been returned. The driver is well-behaved. :)");
			else if ( majorInput < 3 && majorVersion < 3 )
				System.out.println("\tThe maximum supported version pre-3.0 has been returned. The driver is well-behaved. :)");
			else
				System.out.println("\tThe requested version was not returned. The driver is buggy! :(");
		} else
			System.out.println("\tThe requested version was returned. :)");

		if ( ca.isProfileCompatibility() && !GLContext.getCapabilities().GL_ARB_compatibility )
			System.out.println("\tThe driver does not support the Compatibility Profile.");

		System.out.println("\n---------\n");

		System.out.println("Debug mode: " + ca.isDebug());
		System.out.println("Forward compatible mode: " + ca.isForwardCompatible());
		System.out.println("ARB_compatibility: " + GLContext.getCapabilities().GL_ARB_compatibility);
		try {
			GL11.glVertex3f(0.0f, 0.0f, 0.0f);
			System.out.println("Deprecated functionality present: " + true);
		} catch (Throwable t) {
			System.out.println("Deprecated functionality present: " + false);
			if ( GLContext.getCapabilities().GL_ARB_compatibility ) {
				System.out.println("\tARB_compatibility is present, but LWJGL has enabled pseudo-forward compatible mode.");
			}
		}
	}

	private static DisplayMode chooseMode(DisplayMode[] modes, int width, int height) {
		DisplayMode bestMode = null;

		for ( int i = 0; i < modes.length; i++ ) {
			DisplayMode mode = modes[i];
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
		System.out.println("\nUsage: VersionTest <majorVersion> <minorVersion> {<layer>, 'debug', 'fc'}:\n");
		System.out.println("majorVersion\t- Major OpenGL version.");
		System.out.println("majorVersion\t- Minor OpenGL version.");
		System.out.println("layer\t- Layer plane (optional).");
		System.out.println("debug\t- Enables debug mode (optional).");
		System.out.println("fc\t- Enables forward compatibility mode (optional).");

		cleanup();
		System.exit(-1);
	}

	static void kill(String reason) {
		System.out.println("The VersionTest program was terminated because an error occured.\n");
		System.out.println("Reason: " + (reason == null ? "Unknown" : reason));

		cleanup();
		System.exit(-1);
	}

}