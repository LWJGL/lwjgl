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
package org.lwjgl.test.opencl;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.LWJGLUtil;
import org.lwjgl.PointerBuffer;
import org.lwjgl.opencl.*;
import org.lwjgl.opencl.api.CLBufferRegion;

import java.nio.ByteBuffer;
import java.util.List;

import static org.lwjgl.opencl.CL10.*;
import static org.lwjgl.opencl.CL11.*;

/** Basic OpenCL test. */
public class HelloOpenCL {

	public HelloOpenCL() {
	}

	protected static void execute() {
		try {
			CL.create();

			final List<CLPlatform> platforms = CLPlatform.getPlatforms();
			if ( platforms == null )
				throw new RuntimeException("No OpenCL platforms found.");

			for ( CLPlatform platform : platforms ) {
				System.out.println("\n-------------------------");
				System.out.println("NEW PLATFORM: " + platform.getPointer());
				System.out.println(CLCapabilities.getPlatformCapabilities(platform));
				System.out.println("-------------------------");
				printPlatformInfo(platform, "CL_PLATFORM_PROFILE", CL_PLATFORM_PROFILE);
				printPlatformInfo(platform, "CL_PLATFORM_VERSION", CL_PLATFORM_VERSION);
				printPlatformInfo(platform, "CL_PLATFORM_NAME", CL_PLATFORM_NAME);
				printPlatformInfo(platform, "CL_PLATFORM_VENDOR", CL_PLATFORM_VENDOR);
				printPlatformInfo(platform, "CL_PLATFORM_EXTENSIONS", CL_PLATFORM_EXTENSIONS);
				System.out.println("");

				final PointerBuffer ctxProps = BufferUtils.createPointerBuffer(3);
				ctxProps.put(CL_CONTEXT_PLATFORM).put(platform.getPointer()).put(0).flip();

				final List<CLDevice> devices = platform.getDevices(CL_DEVICE_TYPE_ALL);
				for ( CLDevice device : devices ) {
					final CLDeviceCapabilities caps = CLCapabilities.getDeviceCapabilities(device);

					System.out.println("\n\tNEW DEVICE: " + device.getPointer());
					System.out.println(caps);
					System.out.println("\t-------------------------");

					System.out.println("\tCL_DEVICE_TYPE = " + device.getInfoLong(CL_DEVICE_TYPE));
					System.out.println("\tCL_DEVICE_VENDOR_ID = " + device.getInfoInt(CL_DEVICE_VENDOR_ID));
					System.out.println("\tCL_DEVICE_MAX_COMPUTE_UNITS = " + device.getInfoInt(CL_DEVICE_MAX_COMPUTE_UNITS));
					System.out.println("\tCL_DEVICE_MAX_WORK_ITEM_DIMENSIONS = " + device.getInfoInt(CL_DEVICE_MAX_WORK_ITEM_DIMENSIONS));
					//CL10.clGetDeviceInfo(device, CL10.CL_DEVICE_MAX_WORK_ITEM_SIZES, info, size_ret);
					//System.out.println("\tCL_DEVICE_MAX_WORK_ITEM_SIZES = " + info.getInt(0));
					System.out.println("\tCL_DEVICE_MAX_WORK_GROUP_SIZE = " + device.getInfoSize(CL_DEVICE_MAX_WORK_GROUP_SIZE));
					System.out.println("\tCL_DEVICE_MAX_CLOCK_FREQUENCY = " + device.getInfoInt(CL_DEVICE_MAX_CLOCK_FREQUENCY));
					System.out.println("\tCL_DEVICE_ADDRESS_BITS = " + device.getInfoInt(CL_DEVICE_ADDRESS_BITS));
					System.out.println("\tCL_DEVICE_AVAILABLE = " + device.getInfoBoolean(CL_DEVICE_AVAILABLE));
					System.out.println("\tCL_DEVICE_COMPILER_AVAILABLE = " + device.getInfoBoolean(CL_DEVICE_COMPILER_AVAILABLE));

					printDeviceInfo(device, "CL_DEVICE_NAME", CL_DEVICE_NAME);
					printDeviceInfo(device, "CL_DEVICE_VENDOR", CL_DEVICE_VENDOR);
					printDeviceInfo(device, "CL_DRIVER_VERSION", CL_DRIVER_VERSION);
					printDeviceInfo(device, "CL_DEVICE_PROFILE", CL_DEVICE_PROFILE);
					printDeviceInfo(device, "CL_DEVICE_VERSION", CL_DEVICE_VERSION);
					printDeviceInfo(device, "CL_DEVICE_EXTENSIONS", CL_DEVICE_EXTENSIONS);
					if ( caps.OpenCL11 )
						printDeviceInfo(device, "CL_DEVICE_OPENCL_C_VERSION", CL_DEVICE_OPENCL_C_VERSION);

					CLContext context = clCreateContext(ctxProps, device, new CLContextCallback() {
						protected void handleMessage(final String errinfo, final ByteBuffer private_info) {
							System.out.println("IN CLContextCallback :: " + errinfo);
						}
					}, null);

					CLMem buffer = clCreateBuffer(context, CL_MEM_READ_ONLY, 128, null);

					if ( caps.OpenCL11 ) {
						clSetMemObjectDestructorCallback(buffer, new CLMemObjectDestructorCallback() {
							protected void handleMessage(final long memobj) {
								System.out.println("FIRST Buffer destructed: " + memobj);
							}
						});

						clSetMemObjectDestructorCallback(buffer, new CLMemObjectDestructorCallback() {
							protected void handleMessage(final long memobj) {
								System.out.println("SECOND Buffer destructed: " + memobj);
							}
						});
					}

					if ( caps.OpenCL11 ) {
						CLMem subbuffer = buffer.createSubBuffer(CL_MEM_READ_ONLY, CL_BUFFER_CREATE_TYPE_REGION, new CLBufferRegion(0, 64), null);

						clSetMemObjectDestructorCallback(subbuffer, new CLMemObjectDestructorCallback() {
							protected void handleMessage(final long memobj) {
								System.out.println("Sub Buffer destructed: " + memobj);
							}
						});
					}

					clRetainMemObject(buffer);

					if ( LWJGLUtil.getPlatform() != LWJGLUtil.PLATFORM_MACOSX ) {
						// TODO: Native kernels crash on MacOSX, disable this until we can debug properly.
						final long exec_caps = device.getInfoLong(CL_DEVICE_EXECUTION_CAPABILITIES);
						if ( (exec_caps & CL_EXEC_NATIVE_KERNEL) == CL_EXEC_NATIVE_KERNEL ) {
							System.out.println("-TRYING TO EXEC NATIVE KERNEL-");
							final CLCommandQueue queue = clCreateCommandQueue(context, device, 0, null);

							final PointerBuffer ev = BufferUtils.createPointerBuffer(1);

							clEnqueueNativeKernel(queue, new CLNativeKernel() {
								protected void execute(final ByteBuffer[] memobjs) {
									System.out.println("\tmemobjs.length = " + memobjs.length);
									for ( int k = 0; k < memobjs.length; k++ ) {
										System.out.println("\tmemobjs[" + k + "].remaining() = " + memobjs[k].remaining());
										for ( int l = memobjs[k].position(); l < memobjs[k].limit(); l++ ) {
											memobjs[k].put(l, (byte)l);
										}
									}
									System.out.println("\tNative kernel done.");
								}
							}, new CLMem[] { buffer }, new long[] { 128 }, null, ev);

							final CLEvent e = queue.getCLEvent(ev.get(0));

							clSetEventCallback(e, CL_COMPLETE, new CLEventCallback() {
								protected void handleMessage(final CLEvent event, final int event_command_exec_status) {
									System.out.println("\t\tEvent callback status: " + getEventStatusName(event_command_exec_status));
								}
							});

							int status = e.getInfoInt(CL_EVENT_COMMAND_EXECUTION_STATUS);
							System.out.println("NATIVE KERNEL STATUS: " + getEventStatusName(status));
							clFlush(queue);
							do {
								int newStatus = e.getInfoInt(CL_EVENT_COMMAND_EXECUTION_STATUS);
								if ( newStatus != status ) {
									status = newStatus;
									System.out.println("NATIVE KERNEL STATUS: " + getEventStatusName(status));
								}
							} while ( status != CL_SUCCESS ); // Busy-spin until we're done

							clReleaseEvent(e);
						}
					}

					clReleaseMemObject(buffer);
					clReleaseContext(context);
				}
			}
		} catch (LWJGLException le) {
			die("Init", le.getMessage());
		}

		CL.destroy();
	}

	private static void printPlatformInfo(final CLPlatform platform, final String param_name, final int param) {
		System.out.println("\t" + param_name + " = " + platform.getInfoString(param));
	}

	private static void printDeviceInfo(final CLDevice device, final String param_name, final int param) {
		System.out.println("\t" + param_name + " = " + device.getInfoString(param));
	}

	private static String getEventStatusName(final int status) {
		switch ( status ) {
			case CL_QUEUED:
				return "CL_QUEUED";
			case CL_SUBMITTED:
				return "CL_SUBMITTED";
			case CL_RUNNING:
				return "CL_RUNNING";
			default:
				return "CL_COMPLETE";
		}
	}

	private static void die(String kind, String description) {
		System.out.println(kind + " error " + description + " occured");
	}

	/**
	 * main entry point
	 *
	 * @param args String array containing arguments
	 */
	public static void main(String[] args) {
		new HelloOpenCL().execute();
	}

}