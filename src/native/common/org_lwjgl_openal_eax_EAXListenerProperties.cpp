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

#include "org_lwjgl_openal_eax_EAXListenerProperties.h"
#include <stddef.h>

#ifdef _WIN32
#include <eax.h>
#endif

/*
 * Class:     org_lwjgl_openal_eax_EAXListenerProperties
 * Method:    sizeOfEaxListenerProperties
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_lwjgl_openal_eax_EAXListenerProperties_sizeOfEaxListenerProperties(JNIEnv *env, jobject obj) {
#ifdef _WIN32
	return sizeof(EAXLISTENERPROPERTIES);
#else
    return 0;
#endif
}

/*
 * Class:     org_lwjgl_openal_eax_EAXListenerProperties
 * Method:    assignOffsets
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_openal_eax_EAXListenerProperties_assignOffsets (JNIEnv *env, jobject obj) {
#ifdef _WIN32
	jclass listener_class;
	jfieldID field;

	//get class/fields
	listener_class						= env->FindClass("org/lwjgl/openal/eax/EAXListenerProperties");

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "room_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, lRoom));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "roomHF_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, lRoomHF));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "roomRolloffFactor_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flRoomRolloffFactor));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "decayTime_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flDecayTime));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "decayHFRatio_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flDecayHFRatio));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "reflections_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, lReflections));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "reflectionsDelay_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flReflectionsDelay));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "reverb_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, lReverb));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "reverbDelay_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flReverbDelay));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "environment_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, dwEnvironment));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "environmentSize_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flEnvironmentSize));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "environmentDiffusion_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flEnvironmentDiffusion));
	
	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "airAbsorptionHF_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, flAirAbsorptionHF));

	//set environmentDiffusion_offset
	field = env->GetStaticFieldID(listener_class, "flags_offset", "I");
	env->SetStaticIntField(listener_class, field, offsetof(EAXLISTENERPROPERTIES, dwFlags));
#endif
}
