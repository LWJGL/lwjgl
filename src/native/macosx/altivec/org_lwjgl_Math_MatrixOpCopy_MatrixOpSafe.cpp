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

/* remember to turn on the -faltivec flag for gcc compilation */


/**
 * $Id$
 *
 * math library.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

#ifdef _WIN32
#include <windows.h>
#endif
#include <string.h>
#include "org_lwjgl_Math_MatrixOpCopy_MatrixOpSafe.h"
#include "MatrixOpCommon.h"


void altivec_CopyPackedSafe (char * src, char * dst, int length);

/*
 * Class:     org_lwjgl_Math_MatrixOpCopy_MatrixOpSafe
 * Method:    execute
 * Signature: (IIIIIZIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Math_00024MatrixOpCopy_00024MatrixOpSafe_execute
  (
	JNIEnv * env,
	jobject obj,
	jint sourceAddress,
	jint sourceStride,
	jint numElements,
	jint sourceWidth,
	jint sourceHeight,
	jboolean transposeSource,
	jint destAddress,
	jint destStride,
	jboolean transposeDest
  )
{
        // remove any unnecessary copying
        if (transposeSource == transposeDest)
        {
            transposeSource = false;
            transposeDest   = false;
        }
        
        /* handle all cases where the data is packed and transposition is not necessary */
        if ((transposeSource == transposeDest) 
            && ((sourceWidth * sourceHeight * 4) == sourceStride))
        {
            altivec_CopyPackedSafe((char *)sourceAddress, (char *)destAddress, numElements*sourceWidth*sourceHeight*4);
            return;
        }

        MatrixSrc source  (sourceAddress,  sourceStride, sourceWidth,  sourceHeight, numElements, transposeSource);
        MatrixDst dest  (destAddress,    destStride,   source.width, source.height,  source.elements, transposeDest);
        
        float * srcMatrix, * destMatrix;
        int matrixByteCount = source.width*source.height*sizeof(jfloat);
        
        for (int i = 0; i < source.elements; i++)
        {
            srcMatrix = source.nextMatrix();
            destMatrix = dest.nextMatrix();
            
            // just do a straight memory copy
            memcpy(destMatrix, srcMatrix, matrixByteCount);
            dest.writeComplete();
        }
}

void altivec_CopyPackedSafe (char * src, char * dst, int length)
{
    int src_a = (int)src;
    int dst_a = (int)dst;

    //std::cout << "src: " << src_a << " dst: " << dst_a <<"\n";

    if ((src_a & 0x0F) == (dst_a & 0x0F))
    {
    
        //std::cout << "same alignment\n" << "\n";
        int first_bytes = 16 - ((int)(src) & 0x0000000F);
        
        //std::cout << "first bytes" << first_bytes << "\n";
        int i = first_bytes;
        if (first_bytes > length)
            first_bytes = length;
        if (first_bytes == 16)
            first_bytes = 0;
            
        while (i--)
            dst[i] = src[i];
        
        src = &src[first_bytes];
        dst = &dst[first_bytes];
        
        length -= first_bytes;    
        //std::cout << "new length" << length << "\n";
        
        // figure out how many 16 byte chunks there are
        int middle_cycles = (length >> 4); // ignore any other bytes
        length -= (middle_cycles << 4);
        
        while (middle_cycles --) 
        {
            // load a vector, set the cache line to be LRU
            vector float a = (vector float) vec_ldl(0, (float *) src);
            src += 16;
            
            // write it back, set cache line LRU, gets flushed back to RAM (not L2 or L3)
            vec_stl(a,  0, (float *) dst);
            dst += 16;
        }

        // write back any remaining bytes
        while(length--)
            dst[length] = src[length];

    }
    else
    {	//std::cout << "different alignment\n";
        // differing offsets (byte by byte copy)
        while (length--)
            dst[length] = src[length];
    }
}
