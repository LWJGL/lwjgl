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

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.lwjgl.util.Renderable;
import org.lwjgl.util.Timer;
import org.lwjgl.util.model.Frame;
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
 * <p>
 * To animate things, you will need to call Timer.tick() every frame to update the timers in your
 * Renderables. Then they'll just animate themselves. Hurrah!
 * 
 * @author $Author$
 * @version $Revision$
 */
public class Renderer implements Renderable {
	
	/** Material map: String name->Renderable */
	private static final Map materials = new HashMap();
	
	/** The model we're rendering */
	private Model model;
	
	/** The current material */
	private Renderable material;
	
	/** The animation currently being animated */
	private Frame[] frame;
	
	/** The current time */
	private final Timer timer = new Timer();
	
	/** Last frame rendered */
	private Frame lastFrame;
	
	/** Visibility */
	private boolean visible;
	
	/**
	 * C'tor
	 */
	public Renderer() {
	}
	
	/**
	 * @param model The model to set.
	 */
	public void setModel(Model model) {
		if (this.model == model) {
			return;
		}
		this.model = model;
		material = (Renderable) materials.get(model.getMaterial());
		frame = null;
	}
	
	/**
	 * @return the Model we're rendering with this Renderer
	 */
	public Model getModel() {
		return model;
	}
	
	/**
	 * Set the animation
	 * @param animation
	 */
	public void setAnimation(String animation) {
		if (model == null) {
			return;
		}
		frame = model.getAnimation(animation);
		timer.reset();
		lastFrame = null;
	}
	
	/**
	 * Render a Model
	 */
	public void render() {
		
		// Don't do anything if there's no model or no animation
		if (model == null || frame == null) {
			return;
		}
		
		// 1. Set up GL state from the Model's material
		if (material != null) {
			material.render();
		}
		
		// 2. Work out what frame to show
		Frame frame = findFrame();
		if (frame != lastFrame) {
			lastFrame = frame;
			processFrame();
		}
		
		// 3. Render the processed frame
		
	}
	
	/**
	 * Find the nearest frame to the current time
	 * @return the Frame nearest the current time
	 */
	private Frame findFrame() {
		float time = timer.getTime();
		
		// Use a binary search to find the frame
		int a = 0, b = 
	}
	
	/**
	 * Add a material
	 * @param name The material's name
	 * @param renderable The renderable object
	 */
	public static void putMaterial(String name, Renderable renderable) {
		materials.put(name, renderable);
	}
	
	/**
	 * Remove a material
	 * @param name The material's name
	 * @return a Renderable
	 */
	public static Renderable removeMaterial(String name) {
		return (Renderable) materials.remove(name);
	}
	
	/**
	 * Determine if this Renderer is visible
	 * @return boolean
	 */
	public boolean isVisible() {
		return visible;
	}
	
	/**
	 * Sets the visibility of this Renderer
	 * @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	/**
	 * Determines if the animation is paused
	 * @return boolean
	 */
	public boolean isPaused() {
		return timer.isPaused();
	}
	
	/**
	 * Pause the animation
	 */
	public void pause() {
		timer.pause();
	}
	
	/**
	 * Rewind the animation
	 */
	public void rewind() {
		timer.reset();
	}
	
	/**
	 * Resume a paused animation
	 */
	public void resume() {
		timer.resume();
	}
	
}
