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
package org.lwjgl.util.model.renderer;

import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.model.Model;

/**
 * $Id$
 * 
 * A simple (and very inefficient) Model renderer. This calculates the model vertices on the fly
 * and uses GL immediate mode to render the result. This is of course very slow.
 * <p>
 * Material lookups are performed by mapping the material name to a Renderable thing. You must
 * suppy appropriate Renderables - typically something that binds a 2D texture and sets up some
 * GL state.
 * 
 * @author $Author$
 * @version $Revision$
 */
public class Renderer {
	
	/** Material map: String name->Renderable */
	private final Map materials = new HashMap();
	
	/**
	 * C'tor
	 */
	public Renderer() {
	}
	
	/**
	 * Render a Model
	 * @param model The model to render
	 */
	public void render(Model model) {
	}
	
	/**
	 * Add a material
	 * @param name The material's name
	 * @param renderable The renderable object
	 */
	public void putMaterial(String name, Renderable renderable) {
		materials.put(name, renderable);
	}
	
}
