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
 * Win32 math library.
 *
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */


#include <windows.h>
#include "org_lwjgl_Math_MatrixOpMultiply_MatrixOpSafe.h"
#include "MatrixOpCommon.h"
#include <cstring>

/*
 * Class:     org_lwjgl_Math_MatrixOpMultiply_MatrixOpSafe
 * Method:    execute
 * Signature: (IIIIIZIIIIIZIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Math_00024MatrixOpMultiply_00024MatrixOpSafe_execute
  (
	JNIEnv * env,
	jobject obj,
	jint leftSourceAddress,
	jint leftSourceStride,
	jint leftElements,
	jint leftSourceWidth,
	jint leftSourceHeight,
	jboolean transposeLeftSource,
	jint rightSourceAddress,
	jint rightSourceStride,
	jint rightElements,
	jint rightSourceWidth,
	jint rightSourceHeight,
	jboolean transposeRightSource,
	jint destAddress,
	jint destStride,
	jboolean transposeDest
  )
{
        if (transposeLeftSource && transposeRightSource)
        {
            transposeLeftSource = false;
            transposeRightSource = false;
            transposeDest = !transposeDest;
        }


        MatrixSrc left  (leftSourceAddress,  leftSourceStride, 
                        leftSourceWidth,  leftSourceHeight,  leftElements,  transposeLeftSource);
        MatrixSrc right (rightSourceAddress, leftSourceStride, 
                        rightSourceWidth, rightSourceHeight, rightElements, transposeRightSource);
        MatrixDst dest  (destAddress,        destStride,       
                        right.width, left.height, left.elements * right.elements, transposeDest);
        
        float * leftMatrix, * rightMatrix, * destMatrix;
        
        // check out discussions envolving ordering
        
        
        left.rewind();
        for (int i = 0; i < left.elements; i++)
        {
            leftMatrix = left.nextMatrix();
            
            right.rewind();
            for (int j = 0; j < right.elements; j++)
            {
                rightMatrix = right.nextMatrix();
                destMatrix  =  dest.nextMatrix();
                
                // zero the elements of the destination matrix
                for (int d = 0; d < dest.width * dest.height; d++)
                    destMatrix[d] = 0;
                
                // loop through each column of the right matrix
                int rightCell = 0;
                for (int rightCol = 0; rightCol < right.width; rightCol++)
                {
                    // [RxC] * [RxC]
                    // dest has same height as left
                    // dest has same width as  right
                    
                    int leftCell = 0;
                    for (int leftCol = 0; leftCol < left.width; leftCol++)
                    {
                        for (int leftRow = 0; leftRow < left.height; leftRow++)
                        {	
                            destMatrix[leftRow] += rightMatrix[rightCell] * leftMatrix[leftCell++];
                        }
                        rightCell ++ ;
                    }
                    
                    //rightMatrix = &rightMatrix[right.height];
                    destMatrix =  &destMatrix[dest.height];
                    
                }
                dest.writeComplete();
            }
        }
        
}


