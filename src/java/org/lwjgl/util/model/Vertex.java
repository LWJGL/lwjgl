/* 
 * Copyright (c) 2003 Shaven Puppy Ltd
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
 * * Neither the name of 'Shaven Puppy' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
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
package org.lwjgl.util.model;

import java.io.Serializable;

/**
 * $Id$
 * 
 * A single vertex in a Triangle
 * 
 * @author $Author$
 * @version $Revision$
 */
public class Vertex implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	/** Coordinates */
	private final float x, y, z;
	
	/** Normal */
	private final float nx, ny, nz;
	
	/** Texture coordinates */
	private final float u, v;
	
	/** Bone indices: these look up into the current Frame's bone array */
	private final int[] bone;
	
	/** Bone weights (always sum to 1.0f) */
	private final float[] weight;
	
	/**
	 * C'tor
	 * @param x
	 * @param y
	 * @param z
	 * @param nx
	 * @param ny
	 * @param nz
	 * @param u
	 * @param v
	 * @param bone
	 * @param weight
	 */
	public Vertex(float x, float y, float z, float nx, float ny, float nz,
			float u, float v, int[] bone, float[] weight) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.nx = nx;
		this.ny = ny;
		this.nz = nz;
		this.u = u;
		this.v = v;
		this.bone = bone;
		this.weight = weight;
	}
}
