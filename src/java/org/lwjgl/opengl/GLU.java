/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

import org.lwjgl.Sys;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.DoubleBuffer;

/**
 * $Id$
 *
 * GL Utilities library.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public abstract class GLU implements GLUConstants {

	static {
		System.loadLibrary(Sys.getLibraryName());
	}

	public static native String gluErrorString(int errCode);

	public static native String gluGetString(int name);

	public static native void gluOrtho2D(
		double left,
		double right,
		double bottom,
		double top);

	public static native void gluPerspective(
		double fovy,
		double aspect,
		double zNear,
		double zFar);

	public static void gluPickMatrix(double x, double y, double width, double height, IntBuffer viewport) {
		ngluPickMatrix(x, y, width, height, viewport, viewport.position());
	}
	private static native void ngluPickMatrix(double x, double y, double width, double height, IntBuffer viewport, int viewport_offset);

	public static native void gluLookAt(
		double eyex,
		double eyey,
		double eyez,
		double centerx,
		double centery,
		double centerz,
		double upx,
		double upy,
		double upz);

	public static int gluProject(
		double objx,
		double objy,
		double objz,
		DoubleBuffer modelMatrix,
		DoubleBuffer projMatrix,
		IntBuffer viewport,
		DoubleBuffer win
	) {
		return ngluProject(objx, objy, objz, modelMatrix, modelMatrix.position(), projMatrix, projMatrix.position(), viewport, viewport.position(), win, win.position());
	}

	private static native int ngluProject(
		double objx,
		double objy,
		double objz,
		DoubleBuffer modelMatrix,
		int modelMatrix_offset,
		DoubleBuffer projMatrix,
		int projMatrix_offset,
		IntBuffer viewport,
		int viewport_ofset,
		DoubleBuffer win,
		int win_offset
	);

	public static int gluUnProject(
		double winx,
		double winy,
		double winz,
		DoubleBuffer modelMatrix,
		DoubleBuffer projMatrix,
		IntBuffer viewport,
		DoubleBuffer obj
	) {
		return ngluUnProject(winx, winy, winz, modelMatrix, modelMatrix.position(), projMatrix, projMatrix.position(), viewport, viewport.position(), obj, obj.position());
	}

	private static native int ngluUnProject(
		double winx,
		double winy,
		double winz,
		DoubleBuffer modelMatrix,
		int modelMatrix_offset,
		DoubleBuffer projMatrix,
		int projMatrix_offset,
		IntBuffer viewport,
		int viewport_offset,
		DoubleBuffer obj,
		int obj_offset
	);

	public static int gluScaleImage(
		int format,
		int widthin,
		int heightin,
		int typein,
		ByteBuffer datain,
		int widthout, int heightout, int typeout, ByteBuffer dataout
	) {
		return ngluScaleImage(format, widthin, heightin, typein, datain, datain.position(), widthout, heightout, typeout, dataout, dataout.position());
	}

	private static native int ngluScaleImage(
		int format,
		int widthin,
		int heightin,
		int typein,
		ByteBuffer datain,
		int datain_offset,
		int widthout, int heightout, int typeout, ByteBuffer dataout, int dataout_offset
	);

	public static int gluBuild1DMipmaps(
		int target,
		int components,
		int width,
		int format,
		int type,
		ByteBuffer data
	) {
		return ngluBuild1DMipmaps(target, components, width, format, type, data, data.position());
	}

	private static native int ngluBuild1DMipmaps(
		int target,
		int components,
		int width,
		int format,
		int type,
		ByteBuffer data,
		int data_offset
	);

	public static int gluBuild2DMipmaps(
		int target,
		int components,
		int width,
		int height,
		int format,
		int type,
		ByteBuffer data
	) {
		return ngluBuild2DMipmaps(target, components, width, height, format, type, data, data.position());
	}

	private static native int ngluBuild2DMipmaps(
		int target,
		int components,
		int width,
		int height,
		int format,
		int type,
		ByteBuffer data,
		int data_offset
	);
        
}
