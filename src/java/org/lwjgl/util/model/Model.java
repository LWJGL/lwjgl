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

package org.lwjgl.util.model;

import java.io.Serializable;
import java.util.Map;


/**
 * $Id$
 * 
 * A simple animated, skinned, boned 3D model, consisting of a single mesh
 * with a single material. The model's internal format is optimised for RAM storage;
 * no metadata is held in memory - in other words this is not a good class to use
 * for, say, a 3d modelling application.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class Model implements Serializable {
	
	public static final long serialVersionUID = 1L;
	
	/** Material */
	private final String material;
	
	/** Vertices */
	private final Vertex[] vertex;
	
	/** Triangles */
	private final Triangle[] triangle;
	
	/** The animations: a Map of string names to Frame[] arrays */
	private final Map animation;
	
	/**
	 * C'tor
	 * @param material
	 * @param vertex
	 * @param triangle
	 * @param animation
	 */
	public Model(String material, Vertex[] vertex, Triangle[] triangle, Map animation) {
		this.material = material;
		this.vertex = vertex;
		this.triangle = triangle;
		this.animation = animation;
	}
	
	/**
	 * Get a named animation from the Model
	 * @param name The name of the animation
	 * @return the Frames of an animation (or null, if no such animation exists)
	 */
	public Frame[] getAnimation(String name) {
		return (Frame[]) animation.get(name);
	}
	
	/**
	 * @return Returns the material.
	 */
	public String getMaterial() {
		return material;
	}
	
	/**
	 * @return Returns the triangles.
	 */
	public Triangle[] getTriangle() {
		return triangle;
	}
	
	/**
	 * @return Returns the vertices
	 */
	public Vertex[] getVertex() {
		return vertex;
	}
	

}
