#include "org_lwjgl_input_Cursor.h"
#include "extxcursor.h"
#include "Window.h"

/*
 * Class:     org_lwjgl_input_Cursor
 * Method:    nCreateCursor
 * Signature: (IIIIIII)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Cursor_nCreateCursor
  (JNIEnv *env, jclass clazz, jint width, jint height, jint x_hotspot, jint y_hotspot, /*jint num_cursors,*/ jint image_address/*, jint delay_addresses*/)
{
	XcursorPixel *pixels = (XcursorPixel *)image_address;
/*	int *delays = (int *)delay_addresses;
	int stride = width*height;*/
	int num_cursors = 1;
	XcursorImages *cursor_images = XcursorImagesCreate(num_cursors);
	if (cursor_images == NULL)
		throwException(env, "Could not allocate cursor.");
	cursor_images->nimage = num_cursors;
//	for (int i = 0; i < num_cursors; i++) {
		XcursorImage *cursor_image = XcursorImageCreate(width, height);
		cursor_image->xhot = x_hotspot;
		cursor_image->yhot = y_hotspot;
		cursor_image->pixels = pixels;
//		cursor_image->pixels = &(pixels[stride*i]);
		cursor_image->delay = 0/*delays[i]*/;
		cursor_images->images[0] = cursor_image;
//	}
	Cursor cursor = XcursorImagesLoadCursor(getCurrentDisplay(), cursor_images);
	XcursorImagesDestroy(cursor_images);
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
	Cursor cursor = (Cursor)cursor_handle;
	XFreeCursor(getCurrentDisplay(), cursor);
}
