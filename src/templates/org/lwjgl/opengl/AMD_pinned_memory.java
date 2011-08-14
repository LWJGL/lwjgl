/*
 * Copyright (c) 2002-2011 LWJGL Project
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

public interface AMD_pinned_memory {

	/**
	 * <strong>Official spec not released yet. Info from AMD developer forums:</strong><br/>
	 * Create a buffer object, bind it to the GL_EXTERNAL_VIRTUAL_MEMORY_AMD target and call glBufferData to 'allocate' space.
	 * When the driver sees you do this, it will use the pointer you supply directly rather than copying the data (that is, the
	 * GPU will access your application's memory). You can then use the buffer for other purposes such as a UBO, TBO or VBO by
	 * binding it to the appropriate targets. Synchronization is left to the application - make use of glFenceSync and glWaitSync.
	 * To release the memory, simply call glBufferData again on the buffer object on a different target, or delete the buffer
	 * object. Don't free the memory in the application until you've detached it from the buffer object or bad stuff will happen.
	 * <p/>
	 * Keep in mind that any memory you access will go over the PCIe bus which will be limited to 3-4 GB/s. This will work much
	 * better on Fusion systems (APUs). Theoretically, there isn't a limit to the amount of memory that can be pinned. However,
	 * when the OS pins memory, it removes it from the regular pagable pool and cannot swap it to disk (this is what pinning
	 * means). If you ask for too much, the OS will refuse to do it and the call will fail (the GL driver will return generate
	 * a GL_OUT_OF_MEMORY error). It is very likely that you'll hit this limit long before you run out of address space on the
	 * GPU, although in practice we do impose a moderate limit on the amount of pinned memory so as to not impact system stability
	 * and performance.
	 */
	int GL_EXTERNAL_VIRTUAL_MEMORY_AMD = 0x9160;

}