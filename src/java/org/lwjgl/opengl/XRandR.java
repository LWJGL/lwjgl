/*
 * Copyright (c) 2002-2010 LWJGL Project All rights reserved.
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met: * Redistributions of source code must retain the above
 * copyright notice, this list of conditions and the following
 * disclaimer. * Redistributions in binary form must reproduce the
 * above copyright notice, this list of conditions and the following
 * disclaimer in the documentation and/or other materials provided
 * with the distribution. * Neither the name of 'LWJGL' nor the names
 * of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written
 * permission. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND
 * CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS
 * BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 * OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE
 * USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH
 * DAMAGE.
 */

package org.lwjgl.opengl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.lwjgl.LWJGLUtil;

/**
 * Utility for working with the xrandr commmand-line utility. Assumes
 * xrandr v1.2 or higher.
 *
 * @author ryanm
 */
public class XRandR
{
	private static Screen[] current;

        /**
         * Either the screen marked as "primary" (if it is turned on)
         * or the one with the largest (current) resolution.
         */
        private static String primaryScreenIdentifier;

        /**
         * Used to save the configuration of all output devices to
         * restore it on exit or in case of crash.
         */
        private static Screen[] savedConfiguration;

	private static Map<String, Screen[]> screens;

	private static void populate()
	{
		if( screens == null )
		{
			screens = new HashMap<String, Screen[]>();

			// ProcessBuilder pb = new ProcessBuilder( "xrandr", "-q" );
			// pb.redirectErrorStream();
			try
			{
				// Process p= pb.start();
				Process p = Runtime.getRuntime().exec( new String[] { "xrandr", "-q" } );

				List<Screen> currentList = new ArrayList<Screen>();
				List<Screen> possibles = new ArrayList<Screen>();
				String name = null;
                                // saves the position of the current screen. this is specified in the header of the screen block,
                                // but required later when parsing the screen modelines
                                int[] currentScreenPosition = new int[2];

				BufferedReader br = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
				String line;
				while( ( line = br.readLine() ) != null )
				{
					line = line.trim();
					String[] sa = line.split( "\\s+" );

					if( "connected".equals(sa[1]) )
					{
						// found a new screen block
						if( name != null )
						{
							screens.put( name, possibles.toArray( new Screen[ possibles.size() ] ) );
							possibles.clear();
						}
						name = sa[ 0 ];

                                                // save position of this screen, will be used later when current modeline is parsed
                                                if ("primary".equals(sa[ 2 ])) {
                                                    parseScreenHeader(currentScreenPosition, sa[ 3 ]);
                                                    // save primary
                                                    primaryScreenIdentifier = name;
                                                } else {
                                                    parseScreenHeader(currentScreenPosition, sa[ 2 ]);
                                                }
					}
					else if( Pattern.matches( "\\d*x\\d*", sa[ 0 ] ) )
					{
						// found a new mode line
                                                // current mode contains a star (*)
                                                if (sa[1].contains("*")) {
                                                    parseScreenModeline( currentList, name, sa[ 0 ], Arrays.copyOfRange(sa, 1, sa.length), currentScreenPosition);
                                                }
                                                // normal parsing
						parseScreenModeline( possibles, name, sa[ 0 ], Arrays.copyOfRange(sa, 1, sa.length), null);
					}
				}

				screens.put( name, possibles.toArray( new Screen[ possibles.size() ] ) );

				current = currentList.toArray(new Screen[currentList.size()]);

                                // set primary to largest screen if not set yet
                                if (primaryScreenIdentifier == null) {
                                    long totalPixels = Long.MIN_VALUE;
                                    for (Screen screen : current) {
                                        if (1l * screen.width * screen.height > totalPixels) {
                                            primaryScreenIdentifier = screen.name;
                                            totalPixels = 1l * screen.width * screen.height;
                                        }
                                    }
                                }
			}
			catch( Throwable e )
			{
				LWJGLUtil.log( "Exception in XRandR.populate(): " + e.getMessage() );
				screens.clear();
				current = new Screen[ 0 ];
			}
		}
	}

	/**
	 * @return The current screen configuration of the primary device,
         * or an empty array if xrandr is not supported
	 */
	public static Screen[] getConfiguration()
	{
		populate();

                // find and return primary
                for (Screen screen : current) {
                    if (screen.name.equals(primaryScreenIdentifier)) {
                        return new Screen[]{screen};
                    }
                }

                // problem with primary device, fall back to old behaviour
		return current.clone();
	}

	/**
         * @param disableOthers
         *           if screens not included in screens should be turned off (true) or left alone (false)
	 * @param screens
	 *           The desired screen set, may not be <code>null</code>
	 * @throws IllegalArgumentException
	 *            if no screens are specified
	 */
	public static void setConfiguration(boolean disableOthers, Screen... screens)
	{
		if( screens.length == 0 )
			throw new IllegalArgumentException( "Must specify at least one screen" );

		List<String> cmd = new ArrayList<String>();
		cmd.add( "xrandr" );

                if (disableOthers) {
                    // switch off those in the current set not in the new set
                    for ( Screen screen : current ) {
                            boolean found = false;
                            for ( Screen screen1 : screens ) {
                                    if ( screen1.name.equals(screen.name) ) {
                                            found = true;
                                            break;
                                    }
                            }

                            if ( !found ) {
                                    cmd.add("--output");
                                    cmd.add(screen.name);
                                    cmd.add("--off");
                            }
                    }
                }

		// set up new set
		for ( Screen screen : screens )
			screen.getArgs(cmd);

		try
		{
			// ProcessBuilder pb = new ProcessBuilder( cmd );
			// pb.redirectErrorStream();
			// Process p = pb.start();
			Process p =
					Runtime.getRuntime().exec( cmd.toArray( new String[ cmd.size() ] ) );
			// no output is expected, but check anyway
			BufferedReader br = new BufferedReader( new InputStreamReader( p.getInputStream() ) );
			String line;
			while( ( line = br.readLine() ) != null )
			{
				LWJGLUtil.log( "Unexpected output from xrandr process: " + line );
			}
			current = screens;
		}
		catch( IOException e )
		{
			LWJGLUtil.log( "XRandR exception in setConfiguration(): " + e.getMessage() );
		}
	}

        /**
         * Saves the current configuration for all connected display devices.
         * This configuration can be restored on exit/crash by calling
         * restoreConfiguration()
         */
        public static void saveConfiguration() {
                savedConfiguration = current.clone();
        }

        /**
         * Restores the configuration for all connected display devices.
         * Used on exit or in case of a crash to reset all devices.
         */
        public static void restoreConfiguration() {
                if (savedConfiguration != null) {
                        setConfiguration(true, savedConfiguration);
                }
        }

	/**
	 * @return the name of connected screens, or an empty array if
	 *         xrandr is not supported
	 */
	public static String[] getScreenNames()
	{
		populate();
		return screens.keySet().toArray( new String[ screens.size() ] );
	}

	/**
	 * @param name
	 * @return the possible resolutions of the named screen, or
	 *         <code>null</code> if there is no such screen
	 */
	public static Screen[] getResolutions( String name )
	{
		populate();
		// clone the array to prevent held copies being altered
		return screens.get(name).clone();
	}

	private static final Pattern SCREEN_HEADER_PATTERN =
			Pattern.compile( "^(\\d+)x(\\d+)\\+(\\d+)\\+(\\d+)$" );

	private static final Pattern SCREEN_MODELINE_PATTERN = Pattern.compile( "^(\\d+)x(\\d+)$" );

        private static final Pattern FREQ_PATTERN = Pattern.compile("^(\\d+).(\\d+)\\*?\\+?$");

        /**
	 * Parses a screen configuration and adds it to the list if it's
	 * valid.
	 *
	 * @param list
	 *           the list to add the Screen to if it's valid
	 * @param name
	 *           the name of this screen
	 * @param res
	 *           config string, format widthxheight
         * @param freqs
         *           config strings, frequency as float, with optional * and +
         * @param screenPosition
         *           position of this screen, null defaults to 0,0
	 */
	private static void parseScreenModeline( List<Screen> list, String name, String res, String[] freqs, int[] screenPosition)
	{
		Matcher m = SCREEN_MODELINE_PATTERN.matcher( res );
                if( !m.matches() )
                {
                        LWJGLUtil.log( "Did not match: " + res );
                        return;
                }
		int width = Integer.parseInt( m.group( 1 ) );
		int height = Integer.parseInt( m.group( 2 ) );
		int xpos = 0;
                int ypos = 0;
                if (screenPosition != null) {
                    xpos = screenPosition[0];
                    ypos = screenPosition[1];
                }

                for (String freqS : freqs) {
                    m = FREQ_PATTERN.matcher(freqS);
                    if( !m.matches() )
                    {
                        LWJGLUtil.log( "Did not match: " + res );
                        return;
                    }
                    int freq = Integer.parseInt(m.group(1));
                    list.add( new Screen( name, width, height, freq, xpos, ypos ) );
                }
	}

        /**
         * Parses a screen configuration header and extracts information about the position of the screen.
         *
         * @param screenPosition the int-array to write the position into
         * @param resPos String containing resolution and position, from xrandr
         */
        private static void parseScreenHeader(int[] screenPosition, String resPos) {
            Matcher m = SCREEN_HEADER_PATTERN.matcher(resPos);
            if (!m.matches()) {
                // screen not active!
                screenPosition[0] = 0;
                screenPosition[1] = 0;
                return;
            }
            screenPosition[0] = Integer.parseInt(m.group(3));
            screenPosition[1] = Integer.parseInt(m.group(4));
        }

        static Screen DisplayModetoScreen(DisplayMode mode) {
                populate();
                Screen primary = findPrimary(current);
                return new Screen(primary.name, mode.getWidth(), mode.getHeight(), mode.getFrequency(), primary.xPos, primary.yPos);
        }

        static DisplayMode ScreentoDisplayMode(Screen... screens) {
            populate();
            Screen primary = findPrimary(screens);
            return new DisplayMode(primary.width, primary.height, 24, primary.freq);
        }

        private static Screen findPrimary(Screen... screens) {
            for (Screen screen : screens) {
                if (screen.name.equals(primaryScreenIdentifier)) {
                    return screen;
                }
            }
            // fallback
            return screens[0];
        }

	/**
	 * Encapsulates the configuration of a monitor.
         * Resolution and freq are fixed, position is mutable
	 *
	 * @author ryanm
	 */
	public static class Screen implements Cloneable
	{
		/**
		 * Name for this output
		 */
		public final String name;

		/**
		 * Width in pixels
		 */
		public final int width;

		/**
		 * Height in pixels
		 */
		public final int height;

                /**
                 * Frequency in Hz
                 */
                public final int freq;

		/**
		 * Position on the x-axis, in pixels
		 */
		public int xPos;

		/**
		 * Position on the y-axis, in pixels
		 */
		public int yPos;

		Screen( String name, int width, int height, int freq, int xPos, int yPos )
		{
			this.name = name;
			this.width = width;
			this.height = height;
                        this.freq = freq;
			this.xPos = xPos;
			this.yPos = yPos;
		}

		private void getArgs( List<String> argList )
		{
			argList.add( "--output" );
			argList.add( name );
			argList.add( "--mode" );
			argList.add( width + "x" + height );
                        argList.add( "--rate" );
                        argList.add( freq + "");//"" autoboxes freq as String
			argList.add( "--pos" );
			argList.add( xPos + "x" + yPos );
		}

		//@Override
		public String toString()
		{
			return name + " " + width + "x" + height + " @ " + xPos + "x" + yPos + " with " + freq + "Hz";
		}
	}
}