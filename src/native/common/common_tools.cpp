/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

#include "common_tools.h"

bool debug = false;

void setDebugEnabled(bool enable) {
	debug = enable;
}

void printfDebug(const char *format, ...) {
	va_list ap;
	va_start(ap, format);
	if (ISDEBUGENABLED())
		vfprintf(stderr, format, ap);
	va_end(ap);
}

static void incListStart(event_queue_t *queue) {
	queue->list_start = (queue->list_start + 1)%EVENT_BUFFER_SIZE;
}

void initEventQueue(event_queue_t *event_queue) {
	event_queue->list_start = 0;
	event_queue->list_end = 0;
}

void putEventElement(event_queue_t *queue, unsigned char byte) {
	int next_index = (queue->list_end + 1)%EVENT_BUFFER_SIZE;
	if (next_index == queue->list_start) {
		printfDebug("Event buffer overflow!\n");
		return;
	}
	queue->input_event_buffer[queue->list_end] = byte;
	queue->list_end = next_index;
}

static bool hasMoreEvents(event_queue_t *queue) {
	return queue->list_start != queue->list_end;
}

static void copyEvent(event_queue_t *queue, int event_size, int event_index) {
	int output_index = event_index*event_size;
	for (int i = 0; i < event_size; i++) {
		queue->output_event_buffer[output_index] = queue->input_event_buffer[queue->list_start];
                incListStart(queue);
		output_index++;
	}
}

int copyEvents(event_queue_t *event_queue, int event_size) {
	int num_events = 0;
	while (hasMoreEvents(event_queue)) {
		copyEvent(event_queue, event_size, num_events);
		num_events++;
	}
	return num_events;
}

unsigned char *getOutputList(event_queue_t *queue) {
	return queue->output_event_buffer;
}

int getEventBufferSize(event_queue_t *event_queue) {
	return EVENT_BUFFER_SIZE;
}

static void throwGeneralException(JNIEnv * env, const char *exception_name, const char * err) {
	jclass cls = env->FindClass(exception_name);
	env->ThrowNew(cls, err);
	env->DeleteLocalRef(cls);
}

void throwOpenALException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "org/lwjgl/openal/OpenALException", err);
}

void throwException(JNIEnv * env, const char * err) {
	throwGeneralException(env, "java/lang/Exception", err);
}
