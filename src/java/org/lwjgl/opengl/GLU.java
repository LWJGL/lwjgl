/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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

/**
 * $Id$
 *
 * GL Utilities library.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GLU implements GLUConstants {

	static {
		System.loadLibrary(Sys.LIBRARY_NAME);
	}

	/** Handle to GL */
	private final GL gl;

	/**
	 * Constructor for GLU.
	 */
	public GLU(GL gl) {
		this.gl = gl;
	}

	public native String errorString(int errCode);

	public native String getString(int name);

	public native void ortho2D(
		double left,
		double right,
		double bottom,
		double top);

	public native void perspective(
		double fovy,
		double aspect,
		double zNear,
		double zFar);

	public native void pickMatrix(
		double x,
		double y,
		double width,
		double height,
		int viewport /*int*/
	);

	public native void lookAt(
		double eyex,
		double eyey,
		double eyez,
		double centerx,
		double centery,
		double centerz,
		double upx,
		double upy,
		double upz);

	public native int project(
		double objx,
		double objy,
		double objz,
		int modelMatrix /*double*/
	, int projMatrix /*double*/
	, int viewport /*int*/
	, int winx /*double*/
	, int winy /*double*/
	, int winz /*double*/
	);

	public native int unProject(
		double winx,
		double winy,
		double winz,
		int modelMatrix /*double*/
	, int projMatrix /*double*/
	, int viewport /*int*/
	, int objx /*double*/
	, int objy /*double*/
	, int objz /*double*/
	);

	public native int scaleImage(
		int format,
		int widthin,
		int heightin,
		int typein,
		int datain /*void*/
	, int widthout, int heightout, int typeout, int dataout /*void*/
	);

	public native int build1DMipmaps(
		int target,
		int components,
		int width,
		int format,
		int type,
		int data /*void*/
	);

	public native int build2DMipmaps(
		int target,
		int components,
		int width,
		int height,
		int format,
		int type,
		int data /*void*/
	);

}
