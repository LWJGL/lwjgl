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
 
/**
 * $Id$
 *
 * win32 mouse handling.
 *
 * @author elias_naur <elias_naur@users.sourceforge.net>
 * @version $Revision$
 */

#include <windows.h>
#include "org_lwjgl_input_Cursor.h"
#include "Window.h"
#include "common_tools.h"

/*
 * Class:     org_lwjgl_input_Cursor
 * Method:    nCreateCursor
 * Signature: (IIIIIIIII)I
 */
JNIEXPORT jlong JNICALL Java_org_lwjgl_input_Cursor_nCreateCursor
  (JNIEnv *env, jclass clazz, jint width, jint height, jint x_hotspot, jint y_hotspot, jint num_images, jobject image_buffer, jint images_offset, jobject delay_buffer, jint delays_offset)
{
	int *pixels = (int *)env->GetDirectBufferAddress(image_buffer) + images_offset;

    BITMAPINFO bitmapInfo;

    char *ptrCursorImage;
    HBITMAP colorDIB;
	HBITMAP	colorBitmap;
	int x, y;

    memset(&bitmapInfo, 0, sizeof(BITMAPINFO));
    bitmapInfo.bmiHeader.biSize              = sizeof(BITMAPINFOHEADER);
    bitmapInfo.bmiHeader.biWidth             = width;
    bitmapInfo.bmiHeader.biHeight            = -height;
    bitmapInfo.bmiHeader.biPlanes            = 1;

    bitmapInfo.bmiHeader.biBitCount          = 24;
    bitmapInfo.bmiHeader.biCompression       = BI_RGB;

    colorDIB = CreateDIBSection(GetDC(NULL), (BITMAPINFO*)&(bitmapInfo),
                                    DIB_RGB_COLORS,
                                    (void**)&(ptrCursorImage),
                                    NULL, 0);
    int  *srcPtr = pixels;
    char *dstPtr = ptrCursorImage;
    if (!dstPtr) {
		throwException(env, "Could not allocate DIB section.");
        return 0;
    }
    for (y = 0; y < height; y++ ) {
        for (x = 0; x < width; x++ ) {
            dstPtr[2] = (*srcPtr >> 0x10) & 0xFF;
            dstPtr[1] = (*srcPtr >> 0x08) & 0xFF;
            dstPtr[0] = *srcPtr & 0xFF;

            srcPtr++;
            dstPtr += 3;
        }
    }

    colorBitmap = CreateDIBitmap(GetDC(NULL),
                             (BITMAPINFOHEADER*)&bitmapInfo.bmiHeader,
                             CBM_INIT,
                             (void *)ptrCursorImage,
                             (BITMAPINFO*)&bitmapInfo,
                             DIB_RGB_COLORS);

    DeleteObject(colorDIB);

	// Convert alpha map to pixel packed mask
	int bitWidth = width >> 3;
	int scanlinePad = bitWidth & (sizeof(WORD) - 1);
	int imageSize = (bitWidth + scanlinePad)*height; // Size in bits
	unsigned char *maskPixels = new unsigned char[imageSize];
	memset(maskPixels, 0, imageSize);
	int pixelCount = 0;
	int maskCount = 0;
	for (y = 0; y < height; y++)
		for (x = 0; x < bitWidth; x++) {
			unsigned char col0 = (pixels[pixelCount++] & 0x01000000) >> 17;
			unsigned char col1 = (pixels[pixelCount++] & 0x01000000) >> 18;
			unsigned char col2 = (pixels[pixelCount++] & 0x01000000) >> 19;
			unsigned char col3 = (pixels[pixelCount++] & 0x01000000) >> 20;
			unsigned char col4 = (pixels[pixelCount++] & 0x01000000) >> 21;
			unsigned char col5 = (pixels[pixelCount++] & 0x01000000) >> 22;
			unsigned char col6 = (pixels[pixelCount++] & 0x01000000) >> 23;
			unsigned char col7 = (pixels[pixelCount++] & 0x01000000) >> 24;
			unsigned char mask = col0 | col1 | col2 | col3 | col4 | col5 | col6 | col7;
			maskPixels[maskCount++] = ~mask; // 1 is tranparant, 0 opaque
		}

	HBITMAP cursorMask = CreateBitmap(width, height, 1, 1, maskPixels);

	HCURSOR cursor = NULL;
	ICONINFO iconInfo;
	memset(&iconInfo, 0, sizeof(ICONINFO));
	iconInfo.hbmMask = cursorMask;
	iconInfo.hbmColor = colorBitmap;
	iconInfo.fIcon = FALSE;
	iconInfo.xHotspot = x_hotspot;
	iconInfo.yHotspot = y_hotspot;
	cursor = CreateIconIndirect(&iconInfo);
	DeleteObject(colorBitmap);
	DeleteObject(cursorMask);
	delete[] maskPixels;

	return (jint)cursor;
}

/*
 * Class:     org_lwjgl_input_Cursor
 * Method:    nDestroyCursor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_input_Cursor_nDestroyCursor
  (JNIEnv *env, jclass clazz, jlong cursor_handle)
{
	HCURSOR cursor = (HCURSOR)cursor_handle;
	DestroyCursor(cursor);
}
