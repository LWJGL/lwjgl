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
package org.lwjgl.util.jinput;

import java.io.IOException;

import net.java.games.input.AbstractComponent;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.Event;
import net.java.games.input.Mouse;
import net.java.games.input.Rumbler;

/**
 * @author elias
 */
final class LWJGLMouse extends Mouse {
	private final static int EVENT_X = 1;
	private final static int EVENT_Y = 2;
	private final static int EVENT_WHEEL = 3;
	private final static int EVENT_BUTTON = 4;
	private final static int EVENT_DONE = 5;

	private int event_state = EVENT_DONE;
	
    protected LWJGLMouse() {
        super("LWJGLMouse", createComponents(), new Controller[]{}, new Rumbler[]{});
    }

	private final static Component[] createComponents() {
		return new Component[]{new Axis(Component.Identifier.Axis.X),
			new Axis(Component.Identifier.Axis.Y),
			new Axis(Component.Identifier.Axis.Z),
			new Button(Component.Identifier.Button.LEFT),
			new Button(Component.Identifier.Button.MIDDLE),
			new Button(Component.Identifier.Button.RIGHT)};
	}

	public final synchronized void pollDevice() throws IOException {
		if (!org.lwjgl.input.Mouse.isCreated())
			return;
		org.lwjgl.input.Mouse.poll();
		for (int i = 0; i < 3; i++)
			setButtonState(i);
	}

	private final Button map(int lwjgl_button) {
		switch (lwjgl_button) {
			case 0:
				return (Button)getLeft();
			case 1:
				return (Button)getRight();
			case 2:
				return (Button)getMiddle();
			default:
				return null;
		}
	}

	private final void setButtonState(int lwjgl_button) {
		Button button = map(lwjgl_button);
		if (button != null)
			button.setValue(org.lwjgl.input.Mouse.isButtonDown(lwjgl_button) ? 1 : 0);
	}

	protected final synchronized boolean getNextDeviceEvent(Event event) throws IOException {
		if (!org.lwjgl.input.Mouse.isCreated())
			return false;
		while (true) {
			long nanos = org.lwjgl.input.Mouse.getEventNanoseconds();
			switch (event_state) {
				case EVENT_X:
					event_state = EVENT_Y;
					int dx = org.lwjgl.input.Mouse.getEventDX();
					if (dx != 0) {
						event.set(getX(), dx, nanos);
						return true;
					}
					break;
				case EVENT_Y:
					event_state = EVENT_WHEEL;
					/* We must negate the y coord since lwjgl uses the
					 * OpenGL coordinate system
					 */
					int dy = -org.lwjgl.input.Mouse.getEventDY();
					if (dy != 0) {
						event.set(getY(), dy, nanos);
						return true;
					}
					break;
				case EVENT_WHEEL:
					event_state = EVENT_BUTTON;
					int dwheel = org.lwjgl.input.Mouse.getEventDWheel();
					if (dwheel != 0) {
						event.set(getWheel(), dwheel, nanos);
						return true;
					}
					break;
				case EVENT_BUTTON:
					event_state = EVENT_DONE;
					int lwjgl_button = org.lwjgl.input.Mouse.getEventButton();
					if (lwjgl_button != -1) {
						Button button = map(lwjgl_button);
						if (button != null) {
							event.set(button, org.lwjgl.input.Mouse.getEventButtonState() ? 1f : 0f, nanos);
							return true;
						}
					}
					break;
				case EVENT_DONE:
					if (!org.lwjgl.input.Mouse.next())
						return false;
					event_state = EVENT_X;
					break;
				default:
					break;
			}
		}
	}

	final static class Axis extends AbstractComponent {
		public Axis(Component.Identifier.Axis axis_id) {
			super(axis_id.getName(), axis_id);
		}

		public final boolean isRelative() {
			return true;
		}

		protected final float poll() throws IOException {
			return 0;
		}

		public final boolean isAnalog() {
			return true;
		}
	}

	final static class Button extends AbstractComponent {
		private float value;
		
		public Button(Component.Identifier.Button button_id) {
			super(button_id.getName(), button_id);
		}

		protected final void setValue(float value) {
			this.value = value;
		}

		protected final float poll() throws IOException {
			return value;
		}

		public final boolean isRelative() {
			return false;
		}

		public final boolean isAnalog() {
			return false;
		}
	}
}
