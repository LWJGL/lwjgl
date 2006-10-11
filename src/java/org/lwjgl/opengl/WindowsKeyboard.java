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
 * This is the Windows implementation of the Keyboard.
 * @author elias_naur
 */

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.CharBuffer;

import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.BufferUtils;

final class WindowsKeyboard {
	private final static int BUFFER_SIZE = 50;

	private final WindowsDirectInput dinput;
	private final WindowsDirectInputDevice keyboard;
	private final IntBuffer temp_data_buffer;
	private final ByteBuffer keyboard_state;
	private final boolean unicode;
	private final CharBuffer unicode_buffer;
	private final ByteBuffer ascii_buffer;

	public WindowsKeyboard(WindowsDirectInput dinput, long hwnd) throws LWJGLException {
		this.dinput = dinput;
		try {
			keyboard = dinput.createDevice(WindowsDirectInput.KEYBOARD_TYPE);
			try {
				keyboard.setCooperateLevel(hwnd, WindowsDirectInputDevice.DISCL_NONEXCLUSIVE | WindowsDirectInputDevice.DISCL_FOREGROUND);
				keyboard.setDataFormat(WindowsDirectInput.KEYBOARD_TYPE);
				keyboard.setBufferSize(BUFFER_SIZE);
			} catch (LWJGLException e) {
				keyboard.release();
				throw e;
			}
		} catch (LWJGLException e) {
			dinput.release();
			throw e;
		}
		keyboard.acquire();
		temp_data_buffer = BufferUtils.createIntBuffer(BUFFER_SIZE*WindowsDirectInputDevice.DATA_SIZE);
		keyboard_state = BufferUtils.createByteBuffer(256);
		unicode = isWindowsNT();
		if (unicode) {
			unicode_buffer = BufferUtils.createCharBuffer(BUFFER_SIZE);
			ascii_buffer = null;
		} else {
			unicode_buffer = null;
			// ToAscii returns at most 2 characters
			ascii_buffer = BufferUtils.createByteBuffer(2);
		}
	}

	private static native boolean isWindowsNT();

	public void destroy() {
		keyboard.unacquire();
		keyboard.release();
		dinput.release();
	}
	
	public void poll(ByteBuffer keyDownBuffer) {
		int ret = keyboard.acquire();
		if (ret != WindowsDirectInput.DI_OK && ret != WindowsDirectInput.DI_NOEFFECT)
			return;
		keyboard.poll();
		ret = keyboard.getDeviceState(keyDownBuffer);
		switch (ret) {
			case WindowsDirectInput.DI_OK:
				break;
			case WindowsDirectInput.DI_BUFFEROVERFLOW:
				LWJGLUtil.log("Keyboard buffer overflow");
				break;
			case WindowsDirectInput.DIERR_INPUTLOST:
				break;
			case WindowsDirectInput.DIERR_NOTACQUIRED:
				break;
			default:
				LWJGLUtil.log("Failed to poll keyboard (0x" + Integer.toHexString(ret) + ")");
				break;
		}
	}
	
	private void translateData(IntBuffer src, ByteBuffer dst) {
		while (dst.hasRemaining() && src.hasRemaining()) {
			int dwOfs = src.get();
			dst.putInt(dwOfs);
			byte dwData = (byte)src.get();
			boolean key_down = (dwData & 0x80) != 0;
			dst.put(key_down ? (byte)1 : (byte)0);
			long dwTimeStamp = ((long)src.get()) & 0xFFFFFFFF;
			long nanos = dwTimeStamp*1000000;
			if (key_down) {
				int virt_key = MapVirtualKey(dwOfs, 1);
				if (virt_key != 0 && GetKeyboardState(keyboard_state) != 0) {
					// Mark key down in the scan code
					dwOfs = dwOfs & 0x7fff;
					int num_chars;
					if (unicode) {
						unicode_buffer.clear();
						num_chars = ToUnicode(virt_key, 
							dwOfs,
							keyboard_state,
							unicode_buffer,
							unicode_buffer.capacity(), 0);
					} else {
						ascii_buffer.clear();
						num_chars = ToAscii(virt_key, 
							dwOfs,
							keyboard_state,
							ascii_buffer,
							0);
					}
					if (num_chars > 0) {
						int current_char = 0;
						do {
							if (current_char >= 1) {
								dst.putInt(0);
								dst.put((byte)0);
							}
							int char_int;
							if (unicode) {
								char_int = ((int)unicode_buffer.get()) & 0xFFFF;
							} else {
								char_int = ((int)ascii_buffer.get()) & 0xFF;
							}
							dst.putInt(char_int);
							dst.putLong(nanos);
							current_char++;
						} while (dst.hasRemaining() && current_char < num_chars);
					} else {
						dst.putInt(0);
						dst.putLong(nanos);
					}
				} else {
					dst.putInt(0);
					dst.putLong(nanos);
				}
			} else {
				dst.putInt(0);
				dst.putLong(nanos);
			}
		}
	}
	private static native int MapVirtualKey(int uCode, int uMapType);
	private static native int ToUnicode(int wVirtKey, int wScanCode, ByteBuffer lpKeyState, CharBuffer pwszBuff, int cchBuff, int flags);
	private static native int ToAscii(int wVirtKey, int wScanCode, ByteBuffer lpKeyState, ByteBuffer lpChar, int flags);
	private static native int GetKeyboardState(ByteBuffer lpKeyState);

	public void read(ByteBuffer buffer) {
		int ret = keyboard.acquire();
		if (ret != WindowsDirectInput.DI_OK && ret != WindowsDirectInput.DI_NOEFFECT)
			return;
		keyboard.poll();
		temp_data_buffer.clear();
		ret = keyboard.getDeviceData(temp_data_buffer);
		switch (ret) {
			case WindowsDirectInput.DI_OK:
				break;
			case WindowsDirectInput.DI_BUFFEROVERFLOW:
				LWJGLUtil.log("Keyboard buffer overflow");
				break;
			case WindowsDirectInput.DIERR_INPUTLOST:
				break;
			case WindowsDirectInput.DIERR_NOTACQUIRED:
				break;
			default:
				LWJGLUtil.log("Failed to read keyboard (0x" + Integer.toHexString(ret) + ")");
				break;
		}
		temp_data_buffer.flip();
		translateData(temp_data_buffer, buffer);
	}
}
