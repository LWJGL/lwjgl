/*
 * Copyright (c) 2002-2010 LWJGL Project
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
package org.lwjgl.test.opencl.gl;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.PointerBuffer;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opencl.*;
import org.lwjgl.opencl.api.Filter;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.Drawable;
import org.lwjgl.util.Color;
import org.lwjgl.util.ReadableColor;

import java.io.*;
import java.nio.IntBuffer;
import java.util.List;

import static java.lang.Math.*;
import static org.lwjgl.opencl.CL10.*;
import static org.lwjgl.opencl.CL10GL.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL12.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL21.*;

/*
		THIS DEMO USES CODE PORTED FROM JogAmp.org
		Original code: http://github.com/mbien/jocl-demos
		Original author: Michael Bien

   ___         ___                      ___
	  /  /\       /  /\         ___        /  /\    http://jocl.jogamp.org/
	 /  /:/      /  /::\       /__/\      /  /::\   a http://jogamp.org/ project.
	/__/::\     /  /:/\:\      \  \:\    /  /:/\:\
	\__\/\:\   /  /:/~/::\      \  \:\  /  /:/~/::\
	   \  \:\ /__/:/ /:/\:\ ___  \__\:\/__/:/ /:/\:\
		\__\:\\  \:\/:/__\//__/\ |  |:|\  \:\/:/__\/
		/  /:/ \  \::/     \  \:\|  |:| \  \::/
	   /__/:/   \  \:\      \  \:\__|:|  \  \:\
	   \__\/     \  \:\      \__\::::/    \  \:\
				  \__\/          ~~~~      \__\/
			   ___          ___       ___          ___          ___
			  /  /\        /  /\     /  /\        /__/\        /  /\
			 /  /::\      /  /::\   /  /:/_       \  \:\      /  /:/
			/  /:/\:\    /  /:/\:\ /  /:/ /\       \  \:\    /  /:/      ___     ___
		   /  /:/  \:\  /  /:/~/://  /:/ /:/_  _____\__\:\  /  /:/  ___ /__/\   /  /\
		  /__/:/ \__\:\/__/:/ /://__/:/ /:/ /\/__/::::::::\/__/:/  /  /\\  \:\ /  /:/
		  \  \:\ /  /:/\  \:\/:/ \  \:\/:/ /:/\  \:\~~\~~\/\  \:\ /  /:/ \  \:\  /:/
		   \  \:\  /:/  \  \::/   \  \::/ /:/  \  \:\  ~~~  \  \:\  /:/   \  \:\/:/
			\  \:\/:/    \  \:\    \  \:\/:/    \  \:\       \  \:\/:/     \  \::/
			 \  \::/      \  \:\    \  \::/      \  \:\       \  \::/       \__\/
			  \__\/        \__\/     \__\/        \__\/        \__\/

		 _____          ___           ___           ___           ___
		/  /::\        /  /\         /__/\         /  /\         /  /\
	   /  /:/\:\      /  /:/_       |  |::\       /  /::\       /  /:/_
	  /  /:/  \:\    /  /:/ /\      |  |:|:\     /  /:/\:\     /  /:/ /\
	 /__/:/ \__\:|  /  /:/ /:/_   __|__|:|\:\   /  /:/  \:\   /  /:/ /::\
	 \  \:\ /  /:/ /__/:/ /:/ /\ /__/::::| \:\ /__/:/ \__\:\ /__/:/ /:/\:\
	  \  \:\  /:/  \  \:\/:/ /:/ \  \:\~~\__\/ \  \:\ /  /:/ \  \:\/:/~/:/
	   \  \:\/:/    \  \::/ /:/   \  \:\        \  \:\  /:/   \  \::/ /:/
		\  \::/      \  \:\/:/     \  \:\        \  \:\/:/     \__\/ /:/
		 \__\/        \  \::/       \  \:\        \  \::/        /__/:/
					   \__\/         \__\/         \__\/         \__\/
*/

/**
 * Computes the Mandelbrot set with OpenCL using multiple GPUs and renders the result with OpenGL.
 * A shared PBO is used as storage for the fractal image.<br/>
 * http://en.wikipedia.org/wiki/Mandelbrot_set
 * <p>
 * controls:<br/>
 * keys 1-9 control parallelism level<br/>
 * space enables/disables slice seperator<br/>
 * 'd' toggles between 32/64bit floatingpoint precision<br/>
 * mouse/mousewheel to drag and zoom<br/>
 * 'Home' to reset the viewport<br/>
 * </p>
 *
 * @author Michael Bien, Spasi
 */
public class DemoFractal {

	// max number of used GPUs
	private static final int MAX_PARALLELISM_LEVEL = 8;

	// max per pixel iterations to compute the fractal
	private static final int MAX_ITERATIONS = 500;

	private CLContext clContext;
	private CLCommandQueue[] queues;
	private CLKernel[] kernels;
	private CLProgram[] programs;

	private CLMem[] pboBuffers;
	private IntBuffer pboIDs;

	private CLMem[] colorMap;
	private IntBuffer[] colorMapBuffer;

	private final PointerBuffer kernel2DGlobalWorkSize;

	private int width = 0;
	private int height = 0;

	private double minX = -2f;
	private double minY = -1.2f;
	private double maxX = 0.6f;
	private double maxY = 1.3f;

	private boolean dragging;
	private double dragX;
	private double dragY;
	private double dragMinX;
	private double dragMinY;
	private double dragMaxX;
	private double dragMaxY;

	private int mouseX;
	private int mouseY;

	private int slices;

	private boolean drawSeparator;
	private boolean doublePrecision = true;
	private boolean buffersInitialized;
	private boolean rebuild;

	private boolean run = true;

	public DemoFractal(int width, int height) {
		kernel2DGlobalWorkSize = BufferUtils.createPointerBuffer(2);

		this.width = width;
		this.height = height;
	}

	private void run() {
		long startTime = System.currentTimeMillis() + 5000;
		long fps = 0;

		while ( run ) {
			if ( !Display.isVisible() )
				Thread.yield();

			handleIO();
			display();

			Display.update();
			if ( Display.isCloseRequested() )
				break;

			if ( startTime > System.currentTimeMillis() ) {
				fps++;
			} else {
				long timeUsed = 5000 + (startTime - System.currentTimeMillis());
				startTime = System.currentTimeMillis() + 5000;
				System.out.println(fps + " frames in 5 seconds = " + (fps / (timeUsed / 1000f)));
				fps = 0;
			}
		}

		CL.destroy();
		Display.destroy();
	}

	private void handleIO() {
		if ( Keyboard.getNumKeyboardEvents() != 0 ) {
			while ( Keyboard.next() ) {
				if ( Keyboard.getEventKeyState() )
					continue;

				final int key = Keyboard.getEventKey();

				if ( Keyboard.KEY_1 <= key && key <= Keyboard.KEY_8 ) {
					int number = key - Keyboard.KEY_1 + 1;
					slices = min(number, min(queues.length, MAX_PARALLELISM_LEVEL));
					System.out.println("NEW PARALLELISM LEVEL: " + slices);
					buffersInitialized = false;
				} else {
					switch ( Keyboard.getEventKey() ) {
						case Keyboard.KEY_SPACE:
							drawSeparator = !drawSeparator;
							System.out.println("SEPARATOR DRAWING IS NOW: " + (drawSeparator ? "ON" : "OFF"));
							break;
						case Keyboard.KEY_D:
							doublePrecision = !doublePrecision;
							System.out.println("DOUBLE PRECISION IS NOW: " + (doublePrecision ? "ON" : "OFF"));
							rebuild = true;
							break;
						case Keyboard.KEY_HOME:
							minX = -2f;
							minY = -1.2f;
							maxX = 0.6f;
							maxY = 1.3f;
							break;
						case Keyboard.KEY_ESCAPE:
							run = false;
							break;
					}
				}
			}
		}

		while ( Mouse.next() ) {
			final int eventBtn = Mouse.getEventButton();

			final int x = Mouse.getX();
			final int y = Mouse.getY();

			if ( Mouse.isButtonDown(0) && (x != mouseX || y != mouseY) ) {
				if ( !dragging ) {
					dragging = true;

					dragX = mouseX;
					dragY = mouseY;

					dragMinX = minX;
					dragMinY = minY;
					dragMaxX = maxX;
					dragMaxY = maxY;
				}

				double offsetX = (x - dragX) * (maxX - minX) / width;
				double offsetY = (y - dragY) * (maxY - minY) / height;

				minX = dragMinX - offsetX;
				minY = dragMinY - offsetY;

				maxX = dragMaxX - offsetX;
				maxY = dragMaxY - offsetY;
			} else {
				if ( dragging )
					dragging = false;

				if ( eventBtn == -1 ) {
					final int dwheel = Mouse.getEventDWheel();
					if ( dwheel != 0 ) {
						double scale = dwheel > 0 ? 0.05 : -0.05;

						double deltaX = scale * (maxX - minX);
						double deltaY = scale * (maxY - minY);

						// offset for "zoom to cursor"
						double offsetX = (x / (double)width - 0.5) * deltaX * 2.0;
						double offsetY = (y / (double)height - 0.5) * deltaY * 2.0;

						minX += deltaX + offsetX;
						minY += deltaY - offsetY;

						maxX += -deltaX + offsetX;
						maxY += -deltaY - offsetY;
					}
				}
			}

			mouseX = x;
			mouseY = y;
		}
	}

	public void init() {
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.setTitle("OpenCL Fractal Demo");
			Display.create();
			CL.create();
		} catch (LWJGLException e) {
			throw new RuntimeException(e);
		}

		try {
			initCL(Display.getDrawable());
		} catch (Exception e) {
			if ( clContext != null )
				clReleaseContext(clContext);
			Display.destroy();
			throw new RuntimeException(e);
		}

		Display.setSwapInterval(0);
		glDisable(GL_DEPTH_TEST);
		glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

		initView(Display.getDisplayMode().getWidth(), Display.getDisplayMode().getHeight());

		initPBO();
		glFinish();

		setKernelConstants();
	}

	private void initCL(Drawable drawable) throws Exception {
		// Find a platform
		List<CLPlatform> platforms = CLPlatform.getPlatforms();
		if ( platforms == null )
			throw new RuntimeException("No OpenCL platforms found.");

		final CLPlatform platform = platforms.get(0); // just grab the first one

		// Find devices with GL sharing support
		final Filter<CLDevice> glSharingFilter = new Filter<CLDevice>() {
			public boolean accept(final CLDevice device) {
				final CLDeviceCapabilities caps = CLCapabilities.getDeviceCapabilities(device);
				return caps.CL_KHR_gl_sharing;
			}
		};
		List<CLDevice> devices = platform.getDevices(CL_DEVICE_TYPE_GPU, glSharingFilter);
		if ( devices == null ) {
			devices = platform.getDevices(CL_DEVICE_TYPE_CPU, glSharingFilter);
			if ( devices == null )
				throw new RuntimeException("No OpenCL devices found.");
		}

		// Create the context
		final PointerBuffer deviceIDs = BufferUtils.createPointerBuffer(devices.size());
		for ( CLDevice device : devices )
			deviceIDs.put(device);
		deviceIDs.flip();

		final PointerBuffer contextProps = BufferUtils.createPointerBuffer(2 + 4 + 1);
		contextProps.put(CL_CONTEXT_PLATFORM).put(platform);

		drawable.setCLSharingProperties(contextProps); // Enable GL sharing

		contextProps.put(0);
		contextProps.flip();
		clContext = clCreateContext(contextProps, deviceIDs, null, null);

		slices = min(devices.size(), MAX_PARALLELISM_LEVEL);

		// create command queues for every GPU, setup colormap and init kernels
		queues = new CLCommandQueue[slices];
		kernels = new CLKernel[slices];
		colorMap = new CLMem[slices];
		colorMapBuffer = new IntBuffer[slices];

		for ( int i = 0; i < slices; i++ ) {
			colorMapBuffer[i] = BufferUtils.createIntBuffer(32 * 2);
			colorMap[i] = clCreateBuffer(clContext, CL_MEM_READ_ONLY, colorMapBuffer[i].capacity() * 4, null);
			colorMap[i].checkNull();

			initColorMap(colorMapBuffer[i], 32, Color.BLUE, Color.GREEN, Color.RED);

			// create command queue and upload color map buffer on each used device
			queues[i] = clCreateCommandQueue(clContext, devices.get(i), CL_QUEUE_PROFILING_ENABLE, null);
			queues[i].checkNull();
			clEnqueueWriteBuffer(queues[i], colorMap[i], CL_TRUE, 0, colorMapBuffer[i], null, null); // blocking upload

		}

		// check if we have 64bit FP support on all devices
		// if yes we can use only one program for all devices + one kernel per device.
		// if not we will have to create (at least) one program for 32 and one for 64bit devices.
		// since there are different vendor extensions for double FP we use one program per device.
		// (OpenCL spec is not very clear about this usecases)
		boolean all64bit = true;
		for ( CLDevice device : devices ) {
			if ( !isDoubleFPAvailable(device) ) {
				all64bit = false;
				break;
			}
		}

		// load program(s)
		programs = new CLProgram[all64bit ? 1 : slices];

		buildPrograms();
	}

	private void createPrograms() throws IOException {
		final String source = getProgramSource("Mandelbrot.cl");
		for ( int i = 0; i < programs.length; i++ )
			programs[i] = clCreateProgramWithSource(clContext, source, null);
	}

	private String getProgramSource(final String file) throws IOException {
		InputStream source = getClass().getResourceAsStream(file);
		if ( source == null ) // dev-mode
			source = new FileInputStream("src/java/org/lwjgl/test/opencl/gl/" + file);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(source));

		final StringBuilder sb = new StringBuilder();
		String line;
		try {
			while ( (line = reader.readLine()) != null )
				sb.append(line).append("\n");
		} finally {
			source.close();
		}

		return sb.toString();
	}

	private static void initColorMap(IntBuffer colorMap, int stepSize, ReadableColor... colors) {
		for ( int n = 0; n < colors.length - 1; n++ ) {
			ReadableColor color = colors[n];
			int r0 = color.getRed();
			int g0 = color.getGreen();
			int b0 = color.getBlue();

			color = colors[n + 1];
			int r1 = color.getRed();
			int g1 = color.getGreen();
			int b1 = color.getBlue();

			int deltaR = r1 - r0;
			int deltaG = g1 - g0;
			int deltaB = b1 - b0;

			for ( int step = 0; step < stepSize; step++ ) {
				float alpha = (float)step / (stepSize - 1);
				int r = (int)(r0 + alpha * deltaR);
				int g = (int)(g0 + alpha * deltaG);
				int b = (int)(b0 + alpha * deltaB);
				colorMap.put((r << 16) | (g << 8) | (b << 0));
			}
		}
		colorMap.rewind();
	}

	private static void initView(int width, int height) {
		glViewport(0, 0, width, height);

		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();

		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0.0, width, 0.0, height, 0.0, 1.0);
	}

	private void initPBO() {
		if ( pboBuffers == null ) {
			pboBuffers = new CLMem[slices];
			pboIDs = BufferUtils.createIntBuffer(slices);
		} else {
			for ( CLMem pboBuffer : pboBuffers )
				clReleaseMemObject(pboBuffer);
			glDeleteBuffers(pboIDs);
		}

		glGenBuffers(pboIDs);

		// setup one empty PBO per slice
		for ( int i = 0; i < slices; i++ ) {
			glBindBuffer(GL_PIXEL_UNPACK_BUFFER, pboIDs.get(i));
			glBufferData(GL_PIXEL_UNPACK_BUFFER, width * height * 4 / slices, GL_STREAM_DRAW);

			pboBuffers[i] = clCreateFromGLBuffer(clContext, CL_MEM_WRITE_ONLY, pboIDs.get(i), null);
		}
		glBindBuffer(GL_PIXEL_UNPACK_BUFFER, 0);

		buffersInitialized = true;
	}

	private void buildPrograms() {
		/*
		 * workaround: The driver keeps using the old binaries for some reason.
		 * to solve this we simple create a new program and release the old.
		 * however rebuilding programs should be possible -> remove when drivers are fixed.
		 * (again: the spec is not very clear about this kind of usages)
		 */
		if ( programs[0] != null ) {
			for ( CLProgram program : programs )
				clReleaseProgram(program);
		}

		try {
			createPrograms();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		// disable 64bit floating point math if not available
		for ( int i = 0; i < programs.length; i++ ) {
			final CLDevice device = queues[i].getCLDevice();

			final StringBuilder options = new StringBuilder("-cl-fast-relaxed-math");
			final CLDeviceCapabilities caps = CLCapabilities.getDeviceCapabilities(device);
			if ( doublePrecision && isDoubleFPAvailable(device) ) {
				//cl_khr_fp64
				options.append(" -D DOUBLE_FP");

				//amd's verson of double precision floating point math
				if ( !caps.CL_KHR_fp64 && caps.CL_AMD_fp64 )
					options.append(" -D AMD_FP");
			}

			System.out.println("COMPILER OPTIONS: " + options);

			clBuildProgram(programs[i], device, options, null);
		}

		rebuild = false;

		for ( int i = 0; i < kernels.length; i++ ) {
			// init kernel with constants
			kernels[i] = clCreateKernel(programs[min(i, programs.length)], "mandelbrot", null);
		}

	}

	// init kernels with constants

	private void setKernelConstants() {
		for ( int i = 0; i < slices; i++ ) {
			kernels[i]
				.setArg(6, pboBuffers[i])
				.setArg(7, colorMap[i])
				.setArg(8, colorMapBuffer[i].capacity())
				.setArg(9, MAX_ITERATIONS);
		}
	}

	// rendering cycle

	public void display() {
		// make sure GL does not use our objects before we start computeing
		glFinish();

		if ( !buffersInitialized ) {
			initPBO();
			setKernelConstants();
		}

		if ( rebuild ) {
			buildPrograms();
			setKernelConstants();
		}
		compute(doublePrecision);

		render();
	}

	// OpenCL

	private void compute(final boolean is64bit) {
		int sliceWidth = (int)(width / (float)slices);
		double rangeX = (maxX - minX) / slices;
		double rangeY = (maxY - minY);

		kernel2DGlobalWorkSize.put(0, sliceWidth).put(1, height);

		// start computation
		for ( int i = 0; i < slices; i++ ) {
			kernels[i].setArg(0, sliceWidth).setArg(1, height);
			if ( !is64bit || !isDoubleFPAvailable(queues[i].getCLDevice()) ) {
				kernels[i]
					.setArg(2, (float)(minX + rangeX * i)).setArg(3, (float)minY)
					.setArg(4, (float)rangeX).setArg(5, (float)rangeY);
			} else {
				kernels[i]
					.setArg(2, minX + rangeX * i).setArg(3, minY)
					.setArg(4, rangeX).setArg(5, rangeY);
			}

			// aquire GL objects, and enqueue a kernel with a probe from the list
			clEnqueueAcquireGLObjects(queues[i], pboBuffers[i], null, null);

			clEnqueueNDRangeKernel(queues[i], kernels[i], 2,
			                       null,
			                       kernel2DGlobalWorkSize,
			                       null,
			                       null, null);

			clEnqueueReleaseGLObjects(queues[i], pboBuffers[i], null, null);
		}

		// block until done (important: finish before doing further gl work)
		for ( int i = 0; i < slices; i++ ) {
			clFinish(queues[i]);
		}

	}

	// OpenGL

	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);

		//draw slices
		int sliceWidth = width / slices;

		for ( int i = 0; i < slices; i++ ) {
			int seperatorOffset = drawSeparator ? i : 0;

			glBindBuffer(GL_PIXEL_UNPACK_BUFFER, pboIDs.get(i));
			glRasterPos2i(sliceWidth * i + seperatorOffset, 0);

			glDrawPixels(sliceWidth, height, GL_BGRA, GL_UNSIGNED_BYTE, 0);

		}
		glBindBuffer(GL_PIXEL_UNPACK_BUFFER, 0);

		//draw info text
		/*
		textRenderer.beginRendering(width, height, false);

		textRenderer.draw("device/time/precision", 10, height - 15);

		for ( int i = 0; i < slices; i++ ) {
			CLDevice device = queues[i].getDevice();
			boolean doubleFP = doublePrecision && isDoubleFPAvailable(device);
			CLEvent event = probes.getEvent(i);
			long start = event.getProfilingInfo(START);
			long end = event.getProfilingInfo(END);
			textRenderer.draw(device.getType().toString() + i + " "
			                  + (int)((end - start) / 1000000.0f) + "ms @"
			                  + (doubleFP ? "64bit" : "32bit"), 10, height - (20 + 16 * (slices - i)));
		}

		textRenderer.endRendering();
		*/
	}

	public void reshape(int x, int y, int width, int height) {
		if ( this.width == width && this.height == height )
			return;

		this.width = width;
		this.height = height;

		initPBO();
		setKernelConstants();

		initView(width, height);

	}

	private static boolean isDoubleFPAvailable(CLDevice device) {
		final CLDeviceCapabilities caps = CLCapabilities.getDeviceCapabilities(device);
		return caps.CL_KHR_fp64 || caps.CL_AMD_fp64;
	}

	public static void main(String args[]) {
		DemoFractal demo = new DemoFractal(512, 512);
		demo.init();
		demo.run();
	}

}