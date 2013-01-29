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

/**
 * $Id$
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <jni.h>
#include <windows.h>
#include "org_lwjgl_opengl_WindowsRegistry.h"
#include "common_tools.h"

/*
 * Return a string containing the queried value, or NULL of the method fails.
 * In that case, a java exception is thrown.
 */
static jstring queryRegistrationKey(JNIEnv *env, HKEY root_key, LPCTSTR subkey, LPCTSTR value) {
	DWORD buf_size = 1;
	char *result;
	HKEY hKey;
	LONG lRet;
	jstring java_result;


	if(RegOpenKeyEx(root_key,
		TEXT(subkey),
		0,
		KEY_QUERY_VALUE,
		&hKey) != ERROR_SUCCESS) {
		throwException(env, "Failed to open registry key");
		return NULL;
	}

	result = (char *)malloc(buf_size);
	if (result == NULL) {
		RegCloseKey(hKey);
		throwException(env, "Failed to allocate buffer");
		return NULL;
	}

	while (1) {
		lRet = RegQueryValueEx(hKey,
						TEXT(value),
						NULL,
						NULL,
						(LPBYTE)result,
						&buf_size);
		if (lRet != ERROR_SUCCESS && lRet != ERROR_MORE_DATA) {
			RegCloseKey(hKey);
			free(result);
			throwException(env, "Failed query key value");
			return NULL;
		}
		if (lRet == ERROR_SUCCESS) {
			RegCloseKey(hKey);
			// make sure the result has a terminating null
			buf_size += 1;
			result = (char *)realloc(result, buf_size);
			if (result == NULL) {
				throwException(env, "Failed to resize buffer");
				return NULL;
			}
			result[buf_size - 1] = '\0';
			java_result = NewStringNativeWithLength(env, result, (jsize)strlen(result));
			free(result);
			return java_result;
		}
		result = (char *)realloc(result, buf_size);
		if (result == NULL) {
			RegCloseKey(hKey);
			throwException(env, "Failed to resize buffer");
			return NULL;
		}
	}
}

static HKEY enumToRootKey(jint root_key_enum) {
	switch (root_key_enum) {
		case org_lwjgl_opengl_WindowsRegistry_HKEY_CLASSES_ROOT:
			return HKEY_CLASSES_ROOT;
		case org_lwjgl_opengl_WindowsRegistry_HKEY_CURRENT_USER:
			return HKEY_CURRENT_USER;
		case org_lwjgl_opengl_WindowsRegistry_HKEY_LOCAL_MACHINE:
			return HKEY_LOCAL_MACHINE;
		case org_lwjgl_opengl_WindowsRegistry_HKEY_USERS:
			return HKEY_USERS;
		default:
			return 0;
	}
}

JNIEXPORT jstring JNICALL Java_org_lwjgl_opengl_WindowsRegistry_nQueryRegistrationKey  (JNIEnv *env, jclass unused, jint root_key, jstring subkey, jstring value) {
	HKEY root = enumToRootKey(root_key);
	char *subkey_native;
	char *value_native;
	jstring result;

	subkey_native = GetStringNativeChars(env, subkey);
	if (subkey_native == NULL) {
		throwException(env, "Failed to fetch the native string");
		return NULL;
	}
	value_native = GetStringNativeChars(env, value);
	if (value_native == NULL) {
		free(subkey_native);
		throwException(env, "Failed to fetch the native string");
		return NULL;
	}
	result = queryRegistrationKey(env, root, subkey_native, value_native);
	free(subkey_native);
	free(value_native);
	return result;
}
