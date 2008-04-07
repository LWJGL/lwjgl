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
package org.lwjgl.opengl;

import java.nio.Buffer;
import java.nio.IntBuffer;
import java.util.Arrays;

class BaseReferences {

    int elementArrayBuffer;
    int arrayBuffer;
    Buffer[] glVertexAttribPointer_buffer;
    Buffer[] glTexCoordPointer_buffer;
    int glClientActiveTexture;

    BaseReferences(ContextCapabilities caps) {
        IntBuffer temp = caps.scratch_int_buffer;

		int max_vertex_attribs;
		if (caps.OpenGL20 || caps.GL_ARB_vertex_shader) {
	        GL11.glGetInteger(ARBVertexShader.GL_MAX_VERTEX_ATTRIBS_ARB, temp);
			max_vertex_attribs = temp.get(0);
		} else
			max_vertex_attribs = 0;
        glVertexAttribPointer_buffer = new Buffer[max_vertex_attribs];

		int max_texture_units;
		if (caps.OpenGL13 || caps.GL_ARB_multitexture) {
        	GL11.glGetInteger(GL13.GL_MAX_TEXTURE_UNITS, temp);
			max_texture_units = temp.get(0);
		} else
			max_texture_units = 0;
        glTexCoordPointer_buffer = new Buffer[max_texture_units];
    }

    void clear() {
        this.elementArrayBuffer = 0;
        this.arrayBuffer = 0;
        this.glClientActiveTexture = 0;
        Arrays.fill(glVertexAttribPointer_buffer, null);
        Arrays.fill(glTexCoordPointer_buffer, null);
    }

    void copy(BaseReferences references) {
        this.elementArrayBuffer = references.elementArrayBuffer;
        this.arrayBuffer = references.arrayBuffer;
        this.glClientActiveTexture = references.glClientActiveTexture;
        System.arraycopy(references.glVertexAttribPointer_buffer, 0, glVertexAttribPointer_buffer, 0, glVertexAttribPointer_buffer.length);
        System.arraycopy(references.glTexCoordPointer_buffer, 0, glTexCoordPointer_buffer, 0, glTexCoordPointer_buffer.length);
    }
}
