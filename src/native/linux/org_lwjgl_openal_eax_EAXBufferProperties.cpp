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

#include "org_lwjgl_openal_eax_EAXBufferProperties.h"
//#include <eax.h>
#include <stddef.h>

/*
 * Class:     org_lwjgl_openal_eax_EAXBufferProperties
 * Method:    sizeOfEaxBufferProperties
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_eax_EAXBufferProperties_sizeOfEaxBufferProperties(JNIEnv *env, jobject obj) {
//	return sizeof(EAXBUFFERPROPERTIES);
}

/*
 * Class:     org_lwjgl_openal_eax_EAXBufferProperties
 * Method:    assignOffsets
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_eax_EAXBufferProperties_assignOffsets(JNIEnv *env, jobject obj) {
/*	jclass listener_class;
	jfieldID field;

	//get class/fields
	listener_class						= env->FindClass("org/lwjgl/openal/eax/EAXBufferProperties");

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "direct_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lDirect));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "directHF_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lDirectHF));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "room_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lRoom));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "roomHF_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lRoomHF));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "roomRolloffFactor_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, flRoomRolloffFactor));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "obstruction_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lObstruction));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "obstructionLFRatio_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, flObstructionLFRatio));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "occlusion_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lOcclusion));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "occlusionLFRatio_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, flOcclusionLFRatio));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "occlusionRoomRatio_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, flOcclusionRoomRatio));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "outsideVolumeHF_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, lOutsideVolumeHF));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "airAbsorptionFactor_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, flAirAbsorptionFactor));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "flags_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXBUFFERPROPERTIES, dwFlags));*/
}
