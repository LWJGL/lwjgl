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
#include "org_lwjgl_Math_MatrixOpInvert_MatrixOpDirect.h"
#include "MatrixOpCommon.h"
#ifdef _DEBUG
#include <stdio.h>
#endif
/*
 * Class:     org_lwjgl_Math_MatrixOpInvert_MatrixOpDirect
 * Method:    execute
 * Signature: (IIIIIZIIZ)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_Math_00024MatrixOpInvert_00024MatrixOpDirect_execute
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
        static float * temp_matrix = 0;
		static int temp_matrix_size = 0;

			if (transposeSource == transposeDest)
        {
            transposeSource = JNI_FALSE;
            transposeDest   = JNI_FALSE;
        }


        // We are under the assumption that sourceWidth == sourceHeight and the matrix 
        // defined within is invertable
        
        MatrixSrc source  (sourceAddress,  sourceStride, 
                sourceWidth,  sourceHeight,  numElements, transposeSource);
        MatrixDst dest  (destAddress,      destStride,   
                source.width, source.height, source.elements, transposeDest);
    
        dest.configureBuffer(source);
    
        float * srcMatrix, * destMatrix;
        
        int   temp_side = source.width-1;

		if (temp_matrix_size < temp_side) {
			if (temp_matrix)
				delete[] temp_matrix;
			temp_matrix = new float[temp_side * temp_side];
			temp_matrix_size = temp_side;
		}
 
       for (int i = 0; i < source.elements; i++)
        {
            srcMatrix    = source.nextMatrix();
            destMatrix   = dest  .nextMatrix();

            // calculate the determinant
            float det = determinant(srcMatrix, source.width);
            
#ifdef _DEBUG
            printf("Matrix Determinant: %f\n", det);
            printf("Matrix Determinant - 1 = %f\n", det -1);
#endif
                
                float sign;
                
                for (int col = 0; col < source.width; col++)
                {
                    /*
                        Maintain sign:
                        
                        + - + - ...
                        - + - + ..
                        + - + - ..
                        - + - + ..
                        : : : : \
                    */
                
                    sign = (col & 1) ? -1.0f : 1.0f;
                    
                    for (int row = 0; row < source.height; row++)
                    {
                        // get the sub matrix
                        subMatrix(srcMatrix, source.width, temp_matrix, col, row);
                        
                        // transpose the result
                        destMatrix[col + row * source.height] 
                                = (sign / det) * determinant(temp_matrix, temp_side); 
                        
                        // swap signs
                        sign *= -1.0f;
                    }
                }
        
            dest.writeComplete();
        }
}
