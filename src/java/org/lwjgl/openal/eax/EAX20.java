/* 
 * Copyright (c) 2002-2004 LWJGL Project
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
package org.lwjgl.openal.eax;

import java.nio.Buffer;

/**
 * $Id$
 *
 * This is the OpenAL EAX class. It extends the latest core.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAX20 {
	
	/** GUID for buffer */
	public static final int BUFFER_GUID                                     = 1;
	
	/** GUID for listener */
	public static final int LISTENER_GUID                                   = 2;  
	
	public static final int EAX_ENVIRONMENT_GENERIC                         = 0;
	public static final int EAX_ENVIRONMENT_PADDEDCELL                      = 1;
	public static final int EAX_ENVIRONMENT_ROOM                            = 2;
	public static final int EAX_ENVIRONMENT_BATHROOM                        = 3;
	public static final int EAX_ENVIRONMENT_LIVINGROOM                      = 4;
	public static final int EAX_ENVIRONMENT_STONEROOM                       = 5;
	public static final int EAX_ENVIRONMENT_AUDITORIUM                      = 6;
	public static final int EAX_ENVIRONMENT_CONCERTHALL                     = 7;
	public static final int EAX_ENVIRONMENT_CAVE                            = 8;
	public static final int EAX_ENVIRONMENT_ARENA                           = 9;
	public static final int EAX_ENVIRONMENT_HANGAR                          = 10;
	public static final int EAX_ENVIRONMENT_CARPETEDHALLWAY                 = 11;
	public static final int EAX_ENVIRONMENT_HALLWAY                         = 12;
	public static final int EAX_ENVIRONMENT_STONECORRIDOR                   = 13;
	public static final int EAX_ENVIRONMENT_ALLEY                           = 14;
	public static final int EAX_ENVIRONMENT_FOREST                          = 15;
	public static final int EAX_ENVIRONMENT_CITY                            = 16;
	public static final int EAX_ENVIRONMENT_MOUNTAINS                       = 17;
	public static final int EAX_ENVIRONMENT_QUARRY                          = 18;
	public static final int EAX_ENVIRONMENT_PLAIN                           = 19;
	public static final int EAX_ENVIRONMENT_PARKINGLOT                      = 20;
	public static final int EAX_ENVIRONMENT_SEWERPIPE                       = 21;
	public static final int EAX_ENVIRONMENT_UNDERWATER                      = 22;
	public static final int EAX_ENVIRONMENT_DRUGGED                         = 23;
	public static final int EAX_ENVIRONMENT_DIZZY                           = 24;
	public static final int EAX_ENVIRONMENT_PSYCHOTIC                       = 25;
	public static final int EAX_ENVIRONMENT_COUNT                           = 26;
	
	// Single window material preset
	public static final int EAX_MATERIAL_SINGLEWINDOW                       = -2800;
	public static final float EAX_MATERIAL_SINGLEWINDOWLF                   = 0.71f;
	public static final float EAX_MATERIAL_SINGLEWINDOWROOMRATIO            = 0.43f;
	
	// Double window material preset
	public static final int EAX_MATERIAL_DOUBLEWINDOW                       = -5000;
	public static final float EAX_MATERIAL_DOUBLEWINDOWHF                   = 0.40f;
	public static final float EAX_MATERIAL_DOUBLEWINDOWROOMRATIO            = 0.24f;
	
	// Thin door material preset
	public static final int EAX_MATERIAL_THINDOOR                           = -1800;
	public static final float EAX_MATERIAL_THINDOORLF                       = 0.66f;
	public static final float EAX_MATERIAL_THINDOORROOMRATIO                = 0.66f;
	
	// Thick door material preset
	public static final int EAX_MATERIAL_THICKDOOR                          = -4400;
	public static final float EAX_MATERIAL_THICKDOORLF                      = 0.64f;
	public static final float EAX_MATERIAL_THICKDOORROOMRTATION             = 0.27f;
	
	// Wood wall material preset
	public static final int EAX_MATERIAL_WOODWALL                           = -4000;
	public static final float EAX_MATERIAL_WOODWALLLF                       = 0.50f;
	public static final float EAX_MATERIAL_WOODWALLROOMRATIO                = 0.30f;
	
	// Brick wall material preset
	public static final int EAX_MATERIAL_BRICKWALL                          = -5000;
	public static final float EAX_MATERIAL_BRICKWALLLF                      = 0.60f;
	public static final float EAX_MATERIAL_BRICKWALLROOMRATIO               = 0.24f;
	
	// Stone wall material preset
	public static final int EAX_MATERIAL_STONEWALL                          = -6000;
	public static final float EAX_MATERIAL_STONEWALLLF                      = 0.68f;
	public static final float EAX_MATERIAL_STONEWALLROOMRATIO               = 0.20f;
	
	// Curtain material preset
	public static final int EAX_MATERIAL_CURTAIN                            = -1200;
	public static final float EAX_MATERIAL_CURTAINLF                        = 0.15f;
	public static final float EAX_MATERIAL_CURTAINROOMRATIO                 = 1.00f;  
	
	/**
	 * Load extensions
	 */
	protected static void init() {
		determineAvailableExtensions();
	}
	
	/**
	 * Determines available EAX extensions
	 */
	protected static native void determineAvailableExtensions();
	
	/**
	 * Retrieves an EAX Value
	 *
	 * @param propertySetID adress to the property set GUID of the object being queried (a listener or a source)
	 * @param property property being queried
	 * @param source the source to be queried
	 * @param data Buffer to write value to
	 * @param size size of area being written to
	 * @return OpenAL Error code
	 */
	public static int eaxGet(int propertySetID, int property, int source, Buffer data, int size) {
		return neaxGet(propertySetID, property, source, data, data.position(), size);
	}
	private static native int neaxGet(int propertySetID, int property, int source, Buffer data, int position, int size);
	
	/**
	 * Sets an EAX Value
	 *
	 * @param propertySetID adress to the property set GUID of the object being queried (a listener or a source)
	 * @param property property being queried
	 * @param source the source to be queried
	 * @param data Buffer to write value to
	 * @param size size of area being written to
	 * @return OpenAL Error code
	 */
	public static int eaxSet(int propertySetID, int property, int source, Buffer data, int size) {
		return neaxSet(propertySetID, property, source, data, data.position(), size);
	}
	private static native int neaxSet(int propertySetID, int property, int source, Buffer data, int position, int size);
}