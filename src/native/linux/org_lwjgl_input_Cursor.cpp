#include "org_lwjgl_input_Cursor.h"
#include "extxcursor.h"
#include "Window.h"

/*
 * Class:     org_lwjgl_input_Cursor
 * Method:    nCreateCursor
 * Signature: (IIIIIII)I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_input_Cursor_nCreateCursor
  (JNIEnv *env, jclass clazz, jint width, jint height, jint x_hotspot, jint y_hotspot, jint num_images, jobject image_buffer, jint images_offset, jobject delay_buffer, jint delays_offset)
{
	const int *delays = NULL;
	if (delay_buffer != NULL)
		delays = (const int *)env->GetDirectBufferAddress(delay_buffer) + delays_offset;
	XcursorPixel *pixels = (XcursorPixel *)env->GetDirectBufferAddress(image_buffer) + images_offset;
	int stride = width*height;
	XcursorImages *cursor_images = XcursorImagesCreate(num_images);
	if (cursor_images == NULL)
		throwException(env, "Could not allocate cursor.");
	cursor_images->nimage = num_images;
	for (int i = 0; i < num_images; i++) {
		XcursorImage *cursor_image = XcursorImageCreate(width, height);
		cursor_image->xhot = x_hotspot;
		cursor_image->yhot = y_hotspot;
		cursor_image->pixels = &(pixels[stride*i]);
		if (num_images > 1)
			cursor_image->delay = delays[i];
		cursor_images->images[i] = cursor_image;
	}
	Cursor cursor = XcursorImagesLoadCursor(getCurrentDisplay(), cursor_images);
	XcursorImagesDestroy(cursor_images);
	return cursor;
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
