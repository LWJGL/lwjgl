/*
 * Copyright (c) 2002-2010 LWJGL Project
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * Utility for working with the xrandr commmand-line utility. Assumes
 * xrandr v1.2 or higher.
 * 
 * @author ryanm
 */
public class XRandR {

	private static Screen[]	current;

	private static Map		/* <String, Screen[]> */screens;

	private static void populate() {
		if (screens == null) {
			screens = new HashMap/* <String, Screen[]> */();

			// ProcessBuilder pb = new ProcessBuilder( "xrandr", "-q" );
			// pb.redirectErrorStream();
			try {
				// Process p= pb.start();
				Process p = Runtime.getRuntime().exec(new String[] { "xrandr", "-q"});

				List/* <Screen> */currentList = new ArrayList/* <Screen> */();
				List/* <Screen> */possibles = new ArrayList/* <Screen> */();
				String name = null;

				BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String line;
				while ((line = br.readLine()) != null) {
					line = line.trim();
					String[] sa = line.split("\\s+");

					if (sa[1].equals("connected")) {
						// found a new screen block
						if (name != null) {
							screens.put(name, possibles.toArray(new Screen[possibles.size()]));
							possibles.clear();
						}
						name = sa[0];

						// record the current config
						currentList.add(new Screen(name, sa[2]));
					} else if (Pattern.matches("\\d*x\\d*", sa[0])) {
						// found a new mode line
						possibles.add(new Screen(name, sa[0]));
					}
				}

				screens.put(name, possibles.toArray(new Screen[possibles.size()]));

				current = (Screen[]) currentList.toArray(new Screen[currentList.size()]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @return The current screen configuration, or an empty array if
	 *         xrandr is not supported
	 */
	public static Screen[] getConfiguration() {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				populate();
				return null;
			}
		});

		return (Screen[]) current.clone();
	}

	/**
	 * @param screens
	 *           The desired screen set, may not be <code>null</code>
	 */
	public static void setConfiguration(final Screen[]/* ... */screens) {
		if (screens.length == 0) {
			throw new IllegalArgumentException("Must specify at least one screen");
		}
		
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				setScreen(screens);
				return null;
			}
		});
	}
	
	private static void setScreen(Screen[] screens) {
		List/* <String> */cmd = new ArrayList/* <String> */();
		cmd.add("xrandr");

		// switch off those in the current set not in the new set
		for (int i = 0; i < current.length; i++) {
			boolean found = false;
			for (int j = 0; j < screens.length; j++) {
				if (screens[j].name.equals(current[i].name)) {
					found = true;
					break;
				}
			}

			if (!found) {
				cmd.add("--output");
				cmd.add(current[i].name);
				cmd.add("--off");
			}
		}

		// set up new set
		for (int i = 0; i < screens.length; i++) {
			screens[i].getArgs(cmd);
		}

		try {
			// ProcessBuilder pb = new ProcessBuilder( cmd );
			// pb.redirectErrorStream();
			// Process p = pb.start();
			Process p = Runtime.getRuntime().exec((String[]) cmd.toArray(new String[cmd.size()]));
			// no output is expected, but check anyway
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String line;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			current = screens;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return the name of connected screens, or an empty array if
	 *         xrandr is not supported
	 */
	public static String[] getScreenNames() {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				populate();
				return null;
			}
		});
		
		return (String[]) screens.keySet().toArray(new String[screens.size()]);
	}

	/**
	 * @param name
	 * @return the possible resolutions of the named screen, or
	 *         <code>null</code> if there is no such screen
	 */
	public static Screen[] getResolutions(String name) {
		AccessController.doPrivileged(new PrivilegedAction() {
			public Object run() {
				populate();
				return null;
			}
		});
		
		// clone the array to prevent held copies being altered
		return (Screen[]) ((Screen[]) screens.get(name)).clone();
	}

	/**
	 * Encapsulates the configuration of a monitor. Resolution is
	 * fixed, position is mutable
	 * 
	 * @author ryanm
	 */
	public static class Screen implements Cloneable {

		/**
		 * Name for this output
		 */
		public final String	name;

		/**
		 * Width in pixels
		 */
		public final int	width;

		/**
		 * Height in pixels
		 */
		public final int	height;

		/**
		 * Position on the x-axis, in pixels
		 */
		public int			xPos	= 0;

		/**
		 * Position on the y-axis, in pixels
		 */
		public int			yPos	= 0;

		/**
		 * @param name
		 *           name of the screen
		 * @param conf
		 *           config string, format either widthxheight or
		 *           widthxheight+xPos+yPos
		 */
		private Screen(String name, String conf) {
			this.name = name;

			String[] sa = conf.split("\\D");
			width = Integer.parseInt(sa[0]);
			height = Integer.parseInt(sa[1]);

			if (sa.length > 2) {
				xPos = Integer.parseInt(sa[2]);
				yPos = Integer.parseInt(sa[3]);
			}
		}

		private void getArgs(List/* <String> */argList) {
			argList.add("--output");
			argList.add(name);
			argList.add("--mode");
			argList.add(width + "x" + height);
			argList.add("--pos");
			argList.add(xPos + "x" + yPos);
		}

		// @Override
		public String toString() {
			return name + " " + width + "x" + height + " @ " + xPos + "x" + yPos;
		}
	}
}
