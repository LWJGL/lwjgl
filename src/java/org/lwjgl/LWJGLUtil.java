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
package org.lwjgl;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.*;

/**
 * <p>
 * Internal library methods
 * </p>
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 * $Id$
 */
public class LWJGLUtil {
	public static final int PLATFORM_LINUX 				= 1;
	public static final int PLATFORM_MACOSX 			= 2;
	public static final int PLATFORM_WINDOWS 			= 3;
	public static final String PLATFORM_LINUX_NAME 		= "linux";
	public static final String PLATFORM_MACOSX_NAME 	= "macosx";
	public static final String PLATFORM_WINDOWS_NAME	= "windows";

	/** LWJGL Logo - 16 by 16 pixels */
	public static final ByteBuffer	LWJGLIcon16x16		= BufferUtils.createByteBuffer(16 * 16 * 4).put(new byte[] {
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -1, -1, -1, -62, -41, -24, -1, 116, -92, -53, -1, 80, -117,
			-67, -1, 84, -114, -65, -1, -122, -81, -46, -1, -25, -17, -10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -11, -11, -11, -1,
			-115, -113, -111, -1, 118, -126, -115, -1, 125, -115, -101, -1, -124, -103, -86, -1, -108, -73, -43, -1,
			58, 125, -75, -1, 72, -122, -70, -1, -38, -25, -15, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -12, -8, -5, -1, -100, -98, -96, -1, 0, 0, 0, -1, 0, 0, 0, -1,
			0, 0, 0, -1, 0, 0, 0, -1, -28, -28, -28, -1, -124, -83, -48, -1, 58, 125, -75, -1, 91, -110, -63, -1, -4,
			-3, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -111, -74, -43,
			-1, 95, 95, 95, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 34, 34, 34, -1, -1, -1, -1, -1, -24, -16, -10,
			-1, 57, 124, -75, -1, 58, 125, -75, -1, -60, -40, -23, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -58, -39, -23, -1, -127, -85, -50, -1, 29, 29, 29, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 102, 102, 102, -1, -1, -1, -1, -1, -48, -32, -19, -1, 58, 125, -75, -1, 58, 125, -75, -1, -115, -76,
			-44, -1, -1, -1, -1, -1, -14, -14, -14, -1, -91, -91, -91, -1, -33, -33, -33, -1, -94, -63, -36, -1, -80,
			-59, -42, -1, 10, 10, 10, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -88, -88, -88, -1, -1, -1, -1, -1,
			-105, -70, -40, -1, 58, 125, -75, -1, 58, 125, -75, -1, 113, -95, -54, -1, -1, -1, -1, -1, -95, -95, -95,
			-1, 0, 0, 0, -1, 1, 1, 1, -1, 35, 35, 35, -1, -52, -52, -52, -1, -48, -48, -48, -1, -91, -91, -91, -1,
			-124, -124, -124, -1, 92, 92, 92, -1, -17, -17, -17, -1, -1, -1, -1, -1, 96, -106, -61, -1, 58, 125, -75,
			-1, 58, 125, -75, -1, 109, -98, -56, -1, -1, -1, -1, -1, 94, 94, 94, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, -49, -49, -49, -1, 31, 31, 31, -1, 3, 3, 3, -1, 43, 43, 43, -1, 108, 108, 108, -1, -58, -58, -58, -1,
			-6, -4, -3, -1, -98, -65, -38, -1, 119, -91, -52, -1, 76, -119, -68, -1, 124, -88, -50, -1, -1, -1, -1, -1,
			27, 27, 27, -1, 0, 0, 0, -1, 0, 0, 0, -1, 23, 23, 23, -1, -42, -42, -42, -1, 1, 1, 1, -1, 0, 0, 0, -1, 0,
			0, 0, -1, 0, 0, 0, -1, 82, 82, 82, -1, -27, -27, -27, -1, 61, 61, 61, -1, 104, 104, 104, -1, -90, -90, -89,
			-1, -38, -34, -31, -1, -1, -1, -1, -1, 116, 116, 116, -1, 14, 14, 14, -1, 0, 0, 0, -1, 89, 89, 89, -1,
			-107, -107, -107, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -111, -111, -111, -1, -101, -101,
			-101, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 2, 2, 2, -1, 67, 67, 67, -1, -1, -1, -1, -1, -18, -18,
			-18, -1, -116, -116, -116, -1, -65, -65, -65, -1, 86, 86, 86, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0,
			0, 0, -1, -45, -45, -45, -1, 88, 88, 88, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 51, 51,
			51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -4, -3, -2, -1, -1, -1, -1, -1, -64, -64, -64, -1, 64, 64, 64, -1,
			2, 2, 2, -1, 0, 0, 0, -1, 27, 27, 27, -1, -5, -5, -5, -1, 23, 23, 23, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 120, 120, 120, -1, -1, -1, -1, -1, -1, -1, -1, -1, -46, -31, -18, -1, 126, -87, -50,
			-1, -32, -22, -13, -1, -1, -1, -1, -1, -44, -44, -44, -1, 109, 109, 109, -1, -124, -124, -124, -1, -45,
			-45, -45, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -67, -67, -67, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -2, -2, -2, -1, 102, -102, -59, -1, 61, 127, -74, -1, -126, -84, -48, -1, -28, -19,
			-11, -1, -1, -1, -1, -1, -1, -1, -1, -1, -27, -27, -27, -1, 45, 45, 45, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 12, 12, 12, -1, -10, -10, -10, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -30, -20, -12,
			-1, 77, -119, -68, -1, 58, 125, -75, -1, 62, -128, -74, -1, -121, -80, -46, -1, -25, -17, -10, -1, -1, -1,
			-1, -1, -2, -2, -2, -1, -68, -68, -68, -1, 79, 79, 79, -1, 3, 3, 3, -1, 74, 74, 74, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -21, -14, -8, -1, -116, -77, -44, -1, 86, -112,
			-64, -1, 80, -116, -67, -1, 119, -91, -52, -1, -19, -13, -8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -33, -33, -33, -1, -26, -26, -26, -1, -1, -1, -1, -1
	});

	/** LWJGL Logo - 32 by 32 pixels */
	public static final ByteBuffer	LWJGLIcon32x32		= BufferUtils.createByteBuffer(32 * 32 * 4).put(new byte[] {
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -6, -4, -3, -1, -53, -35,
			-20, -1, -109, -73, -42, -1, 111, -96, -55, -1, 92, -109, -62, -1, 96, -106, -61, -1, 122, -89, -51, -1,
			-84, -56, -32, -1, -19, -13, -8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -4, -3, -2, -1, -75, -50, -29, -1, 90, -110, -63, -1,
			58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1,
			58, 125, -75, -1, 69, -123, -71, -1, -95, -63, -36, -1, -4, -3, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -4, -4, -4, -1, -30, -22, -15, -1, -72, -49, -29, -1, -82, -55, -32,
			-1, -93, -62, -36, -1, -104, -70, -40, -1, -116, -77, -45, -1, -127, -84, -49, -1, 110, -97, -56, -1, 61,
			127, -74, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 114, -94, -54, -1, -11, -8, -5, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -40, -40, -40, -1, 49, 49, 49, -1, 36, 36, 36, -1, 49, 49, 49, -1, 64,
			64, 64, -1, 82, 82, 82, -1, 101, 101, 101, -1, 116, 116, 116, -1, -113, -113, -113, -1, -9, -8, -8, -1,
			-84, -56, -33, -1, 59, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 121, -89, -51,
			-1, -4, -3, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -2, -2, -2, -1, 104, 104, 104, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -74, -74, -74, -1, -2, -2, -2, -1, -122, -82,
			-47, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, -82, -54, -31, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -45, -30, -18,
			-1, -31, -22, -14, -1, 40, 40, 40, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0,
			0, 0, -1, 0, 0, 0, -1, 2, 2, 2, -1, -34, -34, -34, -1, -1, -1, -1, -1, -11, -8, -5, -1, 91, -110, -63, -1,
			58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 72, -122, -70, -1, -12, -8, -5, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -20, -13, -8, -1, 100, -103, -59, -1, -41, -39, -37,
			-1, 5, 5, 5, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1,
			36, 36, 36, -1, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -64, -43, -25, -1, 57, 124, -75, -1, 58,
			125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, -85, -56, -32, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -2, -1, -1, -1, 120, -90, -52, -1, 123, -89, -51, -1, -98, -98, -98, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 101, 101, 101, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -30, -20, -12, -1, 55, 123, -76, -1, 58, 125, -75, -1, 58, 125,
			-75, -1, 58, 125, -75, -1, 102, -102, -59, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -61,
			-41, -24, -1, 57, 125, -75, -1, -78, -52, -31, -1, 88, 88, 88, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1,
			0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -87, -87, -87, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -68, -46, -27, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125,
			-75, -1, 64, -127, -73, -1, -18, -12, -7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -6, -4, -3, -1, 90, -110, -63, -1, 61,
			127, -74, -1, -35, -27, -19, -1, 28, 28, 28, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 7, 7, 7, -1, -25, -25, -25, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -123, -82, -47, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 57,
			125, -75, -1, -52, -34, -20, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -2,
			-2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -70, -46, -27, -1, 58, 125, -75, -1, 87, -112, -65,
			-1, -48, -47, -47, -1, 3, 3, 3, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 51, 51, 51, -1, -3, -3, -3, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5, -4, -3, -1, 80,
			-117, -67, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 57, 125, -75, -1,
			-79, -52, -30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -54, -54, -54, -1, 55, 55, 55, -1, 97,
			97, 97, -1, -94, -94, -94, -1, -35, -35, -35, -1, -28, -20, -13, -1, -79, -53, -31, -1, -76, -51, -30, -1,
			-26, -26, -26, -1, 35, 35, 35, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 114, 114, 114, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -40, -27, -16, -1,
			58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1,
			-96, -64, -37, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 102, 102, 102, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 6, 6, 6, -1, 39, 39, 39, -1, 102, 102, 102, -1, -44, -44, -44, -1, -1, -1, -1, -1, -10,
			-10, -10, -1, -48, -48, -48, -1, -105, -105, -105, -1, 94, 94, 94, -1, 39, 39, 39, -1, 4, 4, 4, -1, 0, 0,
			0, -1, 3, 3, 3, -1, -64, -64, -64, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -94, -63, -37, -1,
			58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1,
			-98, -65, -38, -1, -1, -1, -1, -1, -1, -1, -1, -1, -5, -5, -5, -1, 37, 37, 37, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 92, 92, 92, -1, -1, -1, -1, -1, -22, -22, -22, -1,
			-113, -113, -113, -1, -75, -75, -75, -1, -23, -23, -23, -1, -3, -3, -3, -1, -25, -25, -25, -1, -78, -78,
			-78, -1, -67, -67, -67, -1, -2, -2, -2, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 107, -99, -57,
			-1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75,
			-1, -95, -63, -36, -1, -1, -1, -1, -1, -1, -1, -1, -1, -37, -37, -37, -1, 3, 3, 3, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -125, -125, -125, -1, -1, -1, -1, -1, 95, 95,
			95, -1, 0, 0, 0, -1, 0, 0, 0, -1, 11, 11, 11, -1, 55, 55, 55, -1, 118, 118, 118, -1, -73, -73, -73, -1,
			-16, -16, -16, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -16, -11, -7, -1, 75, -120, -69, -1, 61,
			127, -74, -1, 57, 124, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1,
			-78, -52, -30, -1, -1, -1, -1, -1, -1, -1, -1, -1, -102, -102, -102, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -59, -59, -59, -1, -9, -9, -9, -1, 29, 29, 29, -1,
			0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 7, 7, 7, -1, 60, 60, 60, -1,
			-33, -33, -33, -1, -1, -1, -1, -1, -5, -4, -3, -1, -4, -3, -2, -1, -13, -9, -6, -1, -52, -35, -21, -1, -98,
			-66, -38, -1, 114, -95, -55, -1, 76, -119, -69, -1, 58, 125, -75, -1, -55, -36, -21, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, 86, 86, 86, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 15, 15, 15, -1, -7, -7, -7, -1, -47, -47, -47, -1, 3, 3, 3, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1,
			0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -114, -114, -114, -1, -1, -1, -1, -1, -7,
			-7, -7, -1, -124, -124, -124, -1, 112, 112, 112, -1, -80, -80, -80, -1, -22, -22, -22, -1, -1, -1, -1, -1,
			-7, -5, -4, -1, -36, -24, -14, -1, -10, -7, -4, -1, -1, -1, -1, -1, -1, -1, -1, -1, 21, 21, 21, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 78, 78, 78, -1, -1, -1, -1, -1,
			-111, -111, -111, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -69, -69, -69, -1, -1, -1, -1, -1, -98, -98, -98, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 4, 4, 4, -1, 47, 47, 47, -1, 112, 112, 112, -1, -80, -80, -80, -1, -26, -26, -26, -1,
			-2, -2, -2, -1, -1, -1, -1, -1, 57, 57, 57, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, -112, -112, -112, -1, -1, -1, -1, -1, 77, 77, 77, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 16, 16, 16, -1, -16, -16, -16, -1,
			-1, -1, -1, -1, 90, 90, 90, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 9, 9, 9, -1, 50, 50, 50, -1, -79, -79, -79, -1, -10, -10, -10, -1, -95, -95, -95, -1, 53,
			53, 53, -1, 2, 2, 2, -1, 0, 0, 0, -1, 0, 0, 0, -1, 2, 2, 2, -1, -47, -47, -47, -1, -11, -11, -11, -1, 19,
			19, 19, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0,
			0, 0, -1, 69, 69, 69, -1, -2, -2, -2, -1, -8, -8, -8, -1, 29, 29, 29, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 43, 43, 43, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -4, -4, -4, -1, -66, -66, -66, -1, 80, 80, 80, -1, 5, 5, 5, -1, 37, 37, 37, -1,
			-7, -7, -7, -1, -62, -62, -62, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -122, -122, -122, -1, -1, -1, -1, -1, -46, -46, -46, -1, 1,
			1, 1, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 67, 67, 67, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -2,
			-2, -1, -36, -36, -36, -1, -32, -32, -32, -1, -1, -1, -1, -1, -107, -107, -107, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 1, 1, 1, -1, -56, -56,
			-56, -1, -1, -1, -1, -1, -114, -114, -114, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, -120, -120, -120, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -33, -33, -33, -1, 41, 41, 41, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0,
			0, 0, -1, 0, 0, 0, -1, 24, 24, 24, -1, -11, -11, -11, -1, -1, -1, -1, -1, 74, 74, 74, -1, 0, 0, 0, -1, 0,
			0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, -49, -49, -49, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-15, -10, -6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -7, -7, -7, -1, -75, -75, -75, -1, 75,
			75, 75, -1, 6, 6, 6, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 82, 82, 82, -1, -1, -1, -1,
			-1, -8, -8, -8, -1, 16, 16, 16, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0,
			0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 28, 28, 28, -1, -8, -8, -8, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -122, -81, -47, -1, -112, -75, -43, -1, -22, -15, -9,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -37, -37, -37, -1, 120, 120, 120, -1,
			27, 27, 27, -1, 0, 0, 0, -1, 0, 0, 0, -1, -106, -106, -106, -1, -1, -1, -1, -1, -58, -58, -58, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1,
			0, 0, 0, -1, 90, 90, 90, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -61, -40, -23, -1, 58, 125, -75, -1, 67, -125, -72, -1, -108, -72, -42, -1, -19, -13,
			-8, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -12, -12, -12, -1, -89, -89, -89,
			-1, -123, -123, -123, -1, -12, -12, -12, -1, -1, -1, -1, -1, -122, -122, -122, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1,
			-99, -99, -99, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -6, -5, -3, -1, 93, -108, -62, -1, 58, 125, -75, -1, 58, 125, -75, -1, 70, -123, -71, -1, -104,
			-69, -40, -1, -17, -12, -7, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -101, -101, -101, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 5, 5, 5, -1, -35, -35, -35, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-59, -39, -23, -1, 59, 126, -74, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 72, -122, -70,
			-1, -98, -66, -38, -1, -14, -10, -6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -5, -5, -5, -1, -114, -114, -114, -1, 37, 37, 37, -1, 1, 1, 1, -1, 0, 0, 0, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 44, 44, 44, -1, -4, -4, -4, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-113, -75, -43, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75,
			-1, 74, -121, -69, -1, -93, -62, -36, -1, -13, -9, -6, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -6, -6, -6, -1, -80, -80, -80, -1, 69, 69, 69, -1, 4, 4, 4, -1, 0, 0, 0,
			-1, 0, 0, 0, -1, 0, 0, 0, -1, 0, 0, 0, -1, 110, 110, 110, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -4, -3,
			-2, -1, -123, -81, -47, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58,
			125, -75, -1, 58, 125, -75, -1, 77, -119, -68, -1, -88, -59, -34, -1, -10, -7, -4, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -2, -2, -2, -1, -46, -46, -46, -1, 104, 104,
			104, -1, 14, 14, 14, -1, 0, 0, 0, -1, 1, 1, 1, -1, -72, -72, -72, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -2, -2, -1, -1, -79, -53, -30, -1, 76, -119, -68, -1, 57, 125, -75, -1, 58, 125, -75, -1,
			58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 58, 125, -75, -1, 77, -119, -68, -1, -65, -44, -25,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -20, -20, -20, -1, -109, -109, -109, -1, -101, -101, -101, -1, -3, -3, -3, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -13, -9, -6, -1, -75, -50, -29, -1, -127,
			-84, -48, -1, 101, -103, -59, -1, 97, -105, -60, -1, 108, -98, -56, -1, -113, -75, -43, -1, -59, -39, -23,
			-1, -9, -6, -4, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
			-1, -1, -1
	});

	/** Debug flag. */
	public static final boolean DEBUG = getPrivilegedBoolean("org.lwjgl.util.Debug");

	public static final boolean CHECKS = !getPrivilegedBoolean("org.lwjgl.util.NoChecks");

	private static final int PLATFORM;

	static {
		LWJGLIcon16x16.flip();
		LWJGLIcon32x32.flip();

		final String osName = getPrivilegedProperty("os.name");
		if ( osName.startsWith("Windows") )
			PLATFORM = PLATFORM_WINDOWS;
		else if ( osName.startsWith("Linux") || osName.startsWith("FreeBSD") || osName.startsWith("SunOS") )
			PLATFORM = PLATFORM_LINUX;
		else if ( osName.startsWith("Mac OS X") )
			PLATFORM = PLATFORM_MACOSX;
		else
			throw new LinkageError("Unknown platform: " + osName);
	}

	/**
	 * @see #PLATFORM_WINDOWS
	 * @see #PLATFORM_LINUX
	 * @see #PLATFORM_MACOSX
	 * @return the current platform type
	 */
	public static int getPlatform() {
		return PLATFORM;
	}


	/**
	 * @see #PLATFORM_WINDOWS_NAME
	 * @see #PLATFORM_LINUX_NAME
	 * @see #PLATFORM_MACOSX_NAME
	 * @return current platform name
	 */
	public static String getPlatformName() {
		switch (LWJGLUtil.getPlatform()) {
			case LWJGLUtil.PLATFORM_LINUX:
				return PLATFORM_LINUX_NAME;
			case LWJGLUtil.PLATFORM_MACOSX:
				return PLATFORM_MACOSX_NAME;
			case LWJGLUtil.PLATFORM_WINDOWS:
				return PLATFORM_WINDOWS_NAME;
			default:
				return "unknown";
		}
	}

	/**
	 * Locates the paths required by a library.
	 *
	 * @param libname Local Library Name to search the classloader with ("openal").
	 * @param platform_lib_name The native library name ("libopenal.so")
	 * @param classloader The classloader to ask for library paths
	 * @return Paths to located libraries, if any
	 */
	public static String[] getLibraryPaths(String libname, String platform_lib_name, ClassLoader classloader) {
		return getLibraryPaths(libname, new String[]{platform_lib_name}, classloader);
	}

	/**
	 * Locates the paths required by a library.
	 *
	 * @param libname Local Library Name to search the classloader with ("openal").
	 * @param platform_lib_names The list of possible library names ("libopenal.so")
	 * @param classloader The classloader to ask for library paths
	 * @return Paths to located libraries, if any
	 */
	public static String[] getLibraryPaths(String libname, String[] platform_lib_names, ClassLoader classloader) {
		// need to pass path of possible locations of library to native side
		List<String> possible_paths = new ArrayList<String>();

		String classloader_path = getPathFromClassLoader(libname, classloader);
		if (classloader_path != null) {
			log("getPathFromClassLoader: Path found: " + classloader_path);
			possible_paths.add(classloader_path);
		}

		for ( String platform_lib_name : platform_lib_names ) {
			String lwjgl_classloader_path = getPathFromClassLoader("lwjgl", classloader);
			if ( lwjgl_classloader_path != null ) {
				log("getPathFromClassLoader: Path found: " + lwjgl_classloader_path);
				possible_paths.add(lwjgl_classloader_path.substring(0, lwjgl_classloader_path.lastIndexOf(File.separator))
				                   + File.separator + platform_lib_name);
			}

			// add Installer path
			String alternative_path = getPrivilegedProperty("org.lwjgl.librarypath");
			if ( alternative_path != null ) {
				possible_paths.add(alternative_path + File.separator + platform_lib_name);
			}

			// Add all possible paths from java.library.path
			String java_library_path = getPrivilegedProperty("java.library.path");

			StringTokenizer st = new StringTokenizer(java_library_path, File.pathSeparator);
			while ( st.hasMoreTokens() ) {
				String path = st.nextToken();
				possible_paths.add(path + File.separator + platform_lib_name);
			}

			//add current path
			String current_dir = getPrivilegedProperty("user.dir");
			possible_paths.add(current_dir + File.separator + platform_lib_name);

			//add pure library (no path, let OS search)
			possible_paths.add(platform_lib_name);
		}

		//create needed string array
		return possible_paths.toArray(new String[possible_paths.size()]);
	}

	static void execPrivileged(final String[] cmd_array) throws Exception {
		try {
			Process process = AccessController.doPrivileged(new PrivilegedExceptionAction<Process>() {
				public Process run() throws Exception {
					return Runtime.getRuntime().exec(cmd_array);
				}
			});
			// Close unused streams to make sure the child process won't hang
			process.getInputStream().close();
			process.getOutputStream().close();
			process.getErrorStream().close();
		} catch (PrivilegedActionException e) {
			throw (Exception)e.getCause();
		}
	}

	private static String getPrivilegedProperty(final String property_name) {
		return AccessController.doPrivileged(new PrivilegedAction<String>() {
			public String run() {
				return System.getProperty(property_name);
			}
		});
	}

	/**
	 * Tries to locate named library from the current ClassLoader
	 * This method exists because native libraries are loaded from native code, and as such
	 * is exempt from ClassLoader library loading rutines. It therefore always fails.
	 * We therefore invoke the protected method of the ClassLoader to see if it can
	 * locate it.
	 *
	 * @param libname Name of library to search for
	 * @param classloader Classloader to use
	 * @return Absolute path to library if found, otherwise null
	 */
	private static String getPathFromClassLoader(final String libname, final ClassLoader classloader) {
		try {
			log("getPathFromClassLoader: searching for: " + libname);
			Class<?> c = classloader.getClass();
			while (c != null) {
				final Class<?> clazz = c;
				try {
					return AccessController.doPrivileged(new PrivilegedExceptionAction<String>() {
						public String run() throws Exception {
							Method findLibrary = clazz.getDeclaredMethod("findLibrary", String.class);
							findLibrary.setAccessible(true);
							String path = (String)findLibrary.invoke(classloader, libname);
							return path;
						}
					});
				} catch (PrivilegedActionException e) {
					log("Failed to locate findLibrary method: " + e.getCause());
					c = c.getSuperclass();
				}
			}
		} catch (Exception e) {
			log("Failure locating " + e + " using classloader:" + e);
		}
		return null;
	}

	/**
	 * Gets a boolean property as a privileged action.
	 */
	private static boolean getPrivilegedBoolean(final String property_name) {
		Boolean value = AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
			public Boolean run() {
				return Boolean.getBoolean(property_name);
			}
		});
		return value;
	}

	/**
	 * Prints the given message to System.err if DEBUG is true.
	 *
	 * @param msg Message to print
	 */
	public static void log(String msg) {
		if (DEBUG) {
			System.err.println(msg);
		}
	}

	/**
	 * Method to determine if the current system is running a version of
	 * Mac OS X better than the given version. This is only useful for Mac OS X
	 * specific code and will not work for any other platform.
	 */
	public static boolean isMacOSXEqualsOrBetterThan(int major_required, int minor_required) {
		String os_version = getPrivilegedProperty("os.version");
		StringTokenizer version_tokenizer = new StringTokenizer(os_version, ".");
		int major;
		int minor;
		try {
			String major_str = version_tokenizer.nextToken();
			String minor_str = version_tokenizer.nextToken();
			major = Integer.parseInt(major_str);
			minor = Integer.parseInt(minor_str);
		} catch (Exception e) {
			LWJGLUtil.log("Exception occurred while trying to determine OS version: " + e);
			// Best guess, no
			return false;
		}
		return major > major_required || (major == major_required && minor >= minor_required);
	}

	/**
	 * Returns a map of public static final integer fields in the specified classes, to their String representations.
	 * An optional filter can be specified to only include specific fields. The target map may be null, in which
	 * case a new map is allocated and returned.
	 * <p>
	 * This method is useful when debugging to quickly identify values returned from the AL/GL/CL APIs.
	 *
	 * @param filter       the filter to use (optional)
	 * @param target       the target map (optional)
	 * @param tokenClasses an array of classes to get tokens from
	 *
	 * @return the token map
	 */

	public static Map<Integer, String> getClassTokens(final TokenFilter filter, final Map<Integer, String> target, final Class ... tokenClasses) {
		return getClassTokens(filter, target, Arrays.asList(tokenClasses));
	}

	/**
	 * Returns a map of public static final integer fields in the specified classes, to their String representations.
	 * An optional filter can be specified to only include specific fields. The target map may be null, in which
	 * case a new map is allocated and returned.
	 * <p>
	 * This method is useful when debugging to quickly identify values returned from the AL/GL/CL APIs.
	 *
	 * @param filter       the filter to use (optional)
	 * @param target       the target map (optional)
	 * @param tokenClasses the classes to get tokens from
	 *
	 * @return the token map
	 */
	public static Map<Integer, String> getClassTokens(final TokenFilter filter, Map<Integer, String> target, final Iterable<Class> tokenClasses) {
		if ( target == null )
			target = new HashMap<Integer, String>();

		final int TOKEN_MODIFIERS = Modifier.PUBLIC | Modifier.STATIC | Modifier.FINAL;

		for ( final Class tokenClass : tokenClasses ) {
			for ( final Field field : tokenClass.getDeclaredFields() ) {
				// Get only <public static final int> fields.
				if ( (field.getModifiers() & TOKEN_MODIFIERS) == TOKEN_MODIFIERS && field.getType() == int.class ) {
					try {
						final int value = field.getInt(null);
						if ( filter != null && !filter.accept(field, value) )
							continue;

						if ( target.containsKey(value) ) // Print colliding tokens in their hex representation.
							target.put(value, toHexString(value));
						else
							target.put(value, field.getName());
					} catch (IllegalAccessException e) {
						// Ignore
					}
				}
			}
		}

		return target;
	}

	/**
	 * Returns a string representation of the integer argument as an
	 * unsigned integer in base&nbsp;16. The string will be uppercase
	 * and will have a leading '0x'.
	 *
	 * @param value the integer value
	 *
	 * @return the hex string representation
	 */
	public static String toHexString(final int value) {
		return "0x" + Integer.toHexString(value).toUpperCase();
	}

	/** Simple interface for Field filtering. */
	public interface TokenFilter {

		/**
		 * Should return true if the specified Field passes the filter.
		 *
		 * @param field the Field to test
		 * @param value the integer value of the field
		 *
		 * @result true if the Field is accepted
		 */
		boolean accept(Field field, int value);

	}

}