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
/*
 * Created by IntelliJ IDEA.
 * User: nj
 * Date: 12-08-2002
 * Time: 15:35:16
 * To change template for new interface use
 * Code Style | Class Templates options (Tools | IDE Options).
 */
package org.lwjgl.opengl;

public class ATIVertexStreams {
	public static final int GL_MAX_VERTEX_STREAMS_ATI                               = 0x876B;
	public static final int GL_VERTEX_SOURCE_ATI                                    = 0x876C;
	public static final int GL_VERTEX_STREAM0_ATI                                   = 0x876D;
	public static final int GL_VERTEX_STREAM1_ATI                                   = 0x876E;
	public static final int GL_VERTEX_STREAM2_ATI                                   = 0x876F;
	public static final int GL_VERTEX_STREAM3_ATI                                   = 0x8770;
	public static final int GL_VERTEX_STREAM4_ATI                                   = 0x8771;
	public static final int GL_VERTEX_STREAM5_ATI                                   = 0x8772;
	public static final int GL_VERTEX_STREAM6_ATI                                   = 0x8773;
	public static final int GL_VERTEX_STREAM7_ATI                                   = 0x8774;

	public static native void glVertexStream1fATI(int stream, float x);
	public static native void glVertexStream1iATI(int stream, int x);
	public static native void glVertexStream1sATI(int stream, short x);
	public static native void glVertexStream2fATI(int stream, float x, float y);
	public static native void glVertexStream2iATI(int stream, int x, int y);
	public static native void glVertexStream2sATI(int stream, short x, short y);
	public static native void glVertexStream3fATI(int stream, float x, float y, float z);
	public static native void glVertexStream3iATI(int stream, int x, int y, int z);
	public static native void glVertexStream3sATI(int stream, short x, short y, short z);
	public static native void glVertexStream4fATI(
		int stream,
		float x,
		float y,
		float z,
		float w);
	public static native void glVertexStream4iATI(
		int stream,
		int x,
		int y,
		int z,
		int w);
	public static native void glVertexStream4sATI(
		int stream,
		short x,
		short y,
		short z,
		short w);
	public static native void glNormalStream3bATI(int stream, byte x, byte y, byte z);
	public static native void glNormalStream3fATI(int stream, float x, float y, float z);
	public static native void glNormalStream3iATI(int stream, int x, int y, int z);
	public static native void glNormalStream3sATI(int stream, short x, short y, short z);
	public static native void glClientActiveVertexStreamATI(int stream);
	public static native void glVertexBlendEnvfATI(int pname, float param);
	public static native void glVertexBlendEnviATI(int pname, int param);
}
