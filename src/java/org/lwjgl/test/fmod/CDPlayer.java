/* 
 * Copyright (c) 2004 LWJGL Project
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
package org.lwjgl.test.fmod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.lwjgl.fmod.FMOD;
import org.lwjgl.fmod.FMODException;
import org.lwjgl.fmod.FSound;

/**
 * $Id$
 * <br>
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class CDPlayer {

	public static void main(String[] args) {
		try {
			FMOD.create();
		} catch (FMODException fmode) {
			fmode.printStackTrace();
			return;
		}

		System.out.println("Initializing FMOD");
		if (!FSound.FSOUND_Init(44100, 32, 0)) {
			System.out.println("Failed to initialize FMOD");
			return;
		}

		boolean running = true;
		String token = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		do {
			System.out.println("FMOD CD Player test. Press a key corresponding to action");
			System.out.println("1: FSOUND_CD_Eject <drive>");
			System.out.println("2: FSOUND_CD_Play <drive> <track>");
			System.out.println("3: FSOUND_CD_Stop <drive>");
			System.out.println("4: FSOUND_CD_GetNumTracks <drive>");
			System.out.println("5: FSOUND_CD_GetPaused <drive>");
			System.out.println("6: FSOUND_CD_GetTrack <drive>");
			System.out.println("7: FSOUND_CD_GetTrackLength <drive> <track>");
			System.out.println("8: FSOUND_CD_GetTrackTime <drive>");
			System.out.println("9: FSOUND_CD_SetPaused <drive>");
			System.out.println("10: FSOUND_CD_SetPlayMode <drive> <mode>");
			System.out.println("11: FSOUND_CD_SetTrackTime <drive> <milliseconds>");
			System.out.println("12: FSOUND_CD_SetVolume <drive> <volume>");
			System.out.println("0: Exit");
			try {
				StringTokenizer st = new StringTokenizer(br.readLine().trim());
				token = st.nextToken();

				switch (Integer.parseInt(token)) {
					case 0:
						running = false;
						break;
					case 1:
						FSound.FSOUND_CD_OpenTray(st.nextToken().charAt(0), true);
						break;
					case 2:
						FSound.FSOUND_CD_Play(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
						break;
					case 3:
						FSound.FSOUND_CD_Stop(st.nextToken().charAt(0));
						break;
					case 4:
						System.out.println(FSound.FSOUND_CD_GetNumTracks(st.nextToken().charAt(0)));
						break;
					case 5:
            System.out.println(FSound.FSOUND_CD_GetPaused(st.nextToken().charAt(0)));
						break;
					case 6:
            System.out.println(FSound.FSOUND_CD_GetTrack(st.nextToken().charAt(0)));
						break;
					case 7:
            System.out.println(FSound.FSOUND_CD_GetTrackLength(st.nextToken().charAt(0), Integer.parseInt(st.nextToken())));
						break;
					case 8:
            System.out.println(FSound.FSOUND_CD_GetTrackTime(st.nextToken().charAt(0)));
						break;
					case 9:
						FSound.FSOUND_CD_SetPaused(st.nextToken().charAt(0), Boolean.valueOf(st.nextToken()).booleanValue());
						break;
					case 10:
						FSound.FSOUND_CD_SetPlayMode(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
						break;
					case 11:
						FSound.FSOUND_CD_SetTrackTime(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
						break;
					case 12:
						FSound.FSOUND_CD_SetVolume(st.nextToken().charAt(0), Integer.parseInt(st.nextToken()));
						break;
					default:
						System.out.println("No entry");
				}
			} catch (Exception e) {
			}
		} while (running);

		FSound.FSOUND_Close();
		FMOD.destroy();
	}
}