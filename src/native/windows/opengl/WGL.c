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

/**
 * WGL extension implementations.
 *
 * @author Spasi
 */
#include "WGL.h"

/* NV_present_video functions */

jint extgl_EnumerateVideoDevicesNV(JNIEnv *env, jobject peer_info_handle, jobject devices, jint devices_position) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	HVIDEOOUTPUTDEVICENV *devices_address = ((HVIDEOOUTPUTDEVICENV *)safeGetBufferAddress(env, devices)) + devices_position;

	return peer_info->extensions.wglEnumerateVideoDevicesNV(peer_info->drawable_hdc, devices_address);
}

jboolean extgl_BindVideoDeviceNV(JNIEnv *env, jobject peer_info_handle, jint video_slot, jlong video_device, jobject attrib_list, jint attrib_list_position) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	const int *attrib_list_address = ((const int *)safeGetBufferAddress(env, attrib_list)) + attrib_list_position;

	return peer_info->extensions.wglBindVideoDeviceNV(peer_info->drawable_hdc, video_slot, video_device == 0 ? INVALID_HANDLE_VALUE : (HVIDEOOUTPUTDEVICENV)(intptr_t)video_device, attrib_list_address);
}

jboolean extgl_QueryContextNV(JNIEnv *env, jobject peer_info_handle, jobject context_handle, jint attrib, jobject value, jint value_position) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	int *value_address = ((int *)(*env)->GetDirectBufferAddress(env, value)) + value_position;

	return peer_info->extensions.wglQueryCurrentContextNV(attrib, value_address);
}

/* NV_video_capture functions */

jboolean extgl_BindVideoCaptureDeviceNV(JNIEnv *env, jobject peer_info_handle, jint video_slot, jlong device) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);

	return peer_info->extensions.wglBindVideoCaptureDeviceNV(video_slot, (HVIDEOINPUTDEVICENV)(intptr_t)device);
}

jint extgl_EnumerateVideoCaptureDevicesNV(JNIEnv *env, jobject peer_info_handle, jobject devices, jint devices_position) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	HVIDEOINPUTDEVICENV *devices_address = ((HVIDEOINPUTDEVICENV *)safeGetBufferAddress(env, devices)) + devices_position;

	return peer_info->extensions.wglEnumerateVideoCaptureDevicesNV(peer_info->drawable_hdc, devices_address);
}

jboolean extgl_LockVideoCaptureDeviceNV(JNIEnv *env, jobject peer_info_handle, jlong device) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);

	return peer_info->extensions.wglLockVideoCaptureDeviceNV(peer_info->drawable_hdc, (HVIDEOINPUTDEVICENV)(intptr_t)device);
}

jboolean extgl_QueryVideoCaptureDeviceNV(JNIEnv *env, jobject peer_info_handle, jlong device, jint attribute, jobject value, jint value_position) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);
	int *value_address = ((int *)(*env)->GetDirectBufferAddress(env, value)) + value_position;

	return peer_info->extensions.wglQueryVideoCaptureDeviceNV(peer_info->drawable_hdc, (HVIDEOINPUTDEVICENV)(intptr_t)device, attribute, value_address);
}

jboolean extgl_ReleaseVideoCaptureDeviceNV(JNIEnv *env, jobject peer_info_handle, jlong device) {
	WindowsPeerInfo *peer_info = (WindowsPeerInfo *)(*env)->GetDirectBufferAddress(env, peer_info_handle);

	return peer_info->extensions.wglReleaseVideoCaptureDeviceNV(peer_info->drawable_hdc, (HVIDEOINPUTDEVICENV)(intptr_t)device);
}