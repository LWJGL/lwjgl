#include <windows.h>
#include "org_lwjgl_input_Cursor.h"
#include "Window.h"

/*
 * Class:     org_lwjgl_input_Cursor
 * Method:    nCreateCursor
 * Signature: (IIIIIIIII)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Cursor_nCreateCursor
  (JNIEnv *env, jclass clazz, jint width, jint height, jint x_hotspot, jint y_hotspot, jint num_images, jobject image_buffer, jobject delay_buffer)
{
	const int *pixels = (const int *)env->GetDirectBufferAddress(image_buffer);

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

    colorDIB = CreateDIBSection(hdc, (BITMAPINFO*)&(bitmapInfo),
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

    colorBitmap = CreateDIBitmap(hdc,
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
  (JNIEnv *env, jclass clazz, jint cursor_handle)
{
	HCURSOR cursor = (HCURSOR)cursor_handle;
	DestroyCursor(cursor);
}
