/*
 * Copyright (c) 2002-2004 LWJGL Project
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

/**
 * @author elias_naur
 */

import java.awt.Cursor;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.input.Keyboard;

final class AWTUtil {
	public static boolean hasWheel() {
		return true;
	}

	public static int getButtonCount() {
		return MouseEventQueue.NUM_BUTTONS;
	}

	public static int getNativeCursorCapabilities() {
		if (LWJGLUtil.getPlatform() != LWJGLUtil.PLATFORM_MACOSX || LWJGLUtil.isMacOSXEqualsOrBetterThan(10, 4)) {
			int cursor_colors = Toolkit.getDefaultToolkit().getMaximumCursorColors();
			boolean supported = cursor_colors >= Short.MAX_VALUE && getMaxCursorSize() > 0;
			int caps = supported ? org.lwjgl.input.Cursor.CURSOR_8_BIT_ALPHA | org.lwjgl.input.Cursor.CURSOR_ONE_BIT_TRANSPARENCY: 0;
			return caps;
		} else {
			/* Return no capability in Mac OS X 10.3 and earlier , as there are two unsolved bugs (both reported to apple along with
			   minimal test case):
			   1. When a custom cursor (or some standard) java.awt.Cursor is assigned to a
			   Componennt, it is reset to the default pointer cursor when the window is de-
			   activated and the re-activated. The Cursor can not be reset to the custom cursor,
			   with another setCursor.
			   2. When the cursor is moving in the top pixel row (y = 0 in AWT coordinates) in fullscreen
			   mode, no mouse moved events are reported, even though mouse pressed/released and dragged
			   events are reported
			 */
			return 0;
		}
	}

	public static void setCursorPosition(final Component component, int x, int y) {
		try {
			Robot robot = (Robot)AccessController.doPrivileged(new PrivilegedExceptionAction() {
				public Object run() throws Exception {
					return new Robot(component.getGraphicsConfiguration().getDevice());
				}
			});
			int transformed_x = component.getX() + x;
			int transformed_y = component.getY() + component.getHeight() - 1 - y;
			robot.mouseMove(transformed_x, transformed_y);
		} catch (PrivilegedActionException e) {
			LWJGLUtil.log("Got exception while setting mouse cursor position: " + e);
		}
	}

	public static int getMinCursorSize() {
		Dimension min_size = Toolkit.getDefaultToolkit().getBestCursorSize(0, 0);
		return Math.max(min_size.width, min_size.height);
	}

	public static int getMaxCursorSize() {
		Dimension max_size = Toolkit.getDefaultToolkit().getBestCursorSize(10000, 10000);
		return Math.min(max_size.width, max_size.height);
	}

	/** Native cursor handles */
	public static Cursor createCursor(int width, int height, int xHotspot, int yHotspot, int numImages, IntBuffer images, IntBuffer delays) throws LWJGLException {
		BufferedImage cursor_image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		int[] pixels = new int[images.remaining()];
		int old_position = images.position();
		images.get(pixels);
		images.position(old_position);
		cursor_image.setRGB(0, 0, width, height, pixels, 0, width);
		return Toolkit.getDefaultToolkit().createCustomCursor(cursor_image, new Point(xHotspot, yHotspot), "LWJGL Custom cursor");
	}
}
