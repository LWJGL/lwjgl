
//#include <iostream>
#include <jni.h>
#include <memory.h>
#include "MatrixOpCommon.h"

bool Matrix::identicalDataSpaces(Matrix & other)
{
    if (address != other.address)				
        return JNI_FALSE;
    if (stride  != other.stride)				
        return JNI_FALSE;
    if ((width * height) != (other.width * other.height)) 	 
        return JNI_FALSE;
    return JNI_TRUE;
}

bool Matrix::intersectingDataSpaces(Matrix & other)
{
    char * my_max_address    = &address[ stride * elements ];
    char * other_max_address = &other.address[ other.stride * other.elements];
    
    if (address >= other.address || address <= other_max_address)	return JNI_TRUE;
    if (other.address >= address || other.address <= my_max_address) return JNI_TRUE;
    return JNI_FALSE;
}

void Matrix::transposeMatrix(float * src, float * dst, int src_width, int src_height)
{
    // square matrix transpose
    if (src_width == src_height)
    {
        for (int i = 0; i < src_width; i++)
                for (int j = 0; j < src_width; j++)
                        dst[i + src_width * j] = src[j + i * src_width];
    }
    // non square matrix transpose
    else
    {
        for (int i = 0; i < src_width; i ++)
                for (int j = 0; j < src_height; j++)
                        dst[i + src_height * j] = src[j + i * src_height];
    }	
}

void Matrix::transposeMatrix(float * mat, int src_width, int src_height)
{
    float temp;

    // square matrix transpose
    if (src_width == src_height)
    {
            for (int col = 0; col < src_width; col++)
            {
                    for (int row = col+1; row < src_height; row++)
                    {
                            // swap the two elements
                            temp = mat [col * src_height + row];
                            mat[col * src_height + row] = mat[row * src_width + col];
                            mat[row * src_width + col] = temp;
                    }
            }
    }
    // non square matrix transpose
    else
    {
            transposeMatrix(mat, transpose_record, src_width, src_height);
            memcpy(mat, transpose_record, src_width * src_height * sizeof(float));
    }

}


MatrixSrc::MatrixSrc ( jint addr, jint s,
            jint w,    jint h, 
            jint e,	jboolean t):	
            Matrix(addr, s, e), 
            record_offset((char *) addr),
            record_size  (w*h)
{
    if (t) {	
        width = h;
        height = w;
    }
    else {
        width = w;
        height = h;
    }
    
    elements = e;
    record = new float[width * height];
    
    // vectors do not need to be transposed 
    transpose = (t == JNI_TRUE) && (w != 1) && (h != 1);
    
    if (transpose && (width != height))
        // only need temp storage for transpose if the matrix is not square
        transpose_record = new float[width*height];
    else
        transpose_record = 0;

    if (elements == 1)
    {
        // fool the nextMatrix function into returning a value
        elements = 2;
        nextMatrix();
        elements = 1;
    }
}

MatrixSrc::~MatrixSrc()
{
    //cout << "MatrixSrc destructor \n";
    
    delete [] record;
    
    if (transpose_record != 0)
       delete [] transpose_record;
}

float * MatrixSrc::nextMatrix()
{
    if (elements > 1)
    {
        //cout << "Elements: " << elements << "\n";
        //cout << "Address:  " << (unsigned int) (record_offset) << "\n";
        
        // the record is not properly aligned
        if ((unsigned int) (record_offset) & FLOAT_ALIGNMENT)
        {
            // copy the floats into a buffer so that they are aligned
            // on 4 byte margins (not necessary on intel, but a good thing)
            
            memcpy (record, record_offset, record_size * sizeof(float));

            if (transpose)
                transposeMatrix (record, height, width);

            record_offset = &record_offset[stride];
            current_record_ptr = record;
        }
        // the record is aligned but it has to be transposed
        else if (transpose)
        {
            transposeMatrix ((float *) (record_offset), record, height, width);
            record_offset = &record_offset[stride];
            current_record_ptr = record;
        }
        // nothing has to be done to the record
        else
        {
            // the floats are aligned in memory
            current_record_ptr = (float *) record_offset;
            record_offset = &record_offset[stride];
        }
    }

    return current_record_ptr;
}

MatrixDst::MatrixDst (jint addr, jint s, jint w, jint h, jint e, jboolean t): 
            Matrix(addr, s, e)	
{
    width = w;
    height = h;
    record_size = width * height;
    record = new float[record_size];

    // vectors do not need to be transposed 
    transpose = (t) && (w != 1) && (h != 1);
    
    if (transpose)
        transpose_record = new float[width*height];
    else
        transpose_record = 0;
        
    data_buffered   = JNI_FALSE;
    record_buffered = JNI_FALSE;
        
    record_offset = address - stride;
}

MatrixDst::~MatrixDst()
{
    //cout << "MatrixDst destructor \n";

    delete [] record;
    if (transpose_record != 0)
        delete [] transpose_record;

    // copy back any buffered data
    if (data_buffered)
    {
        char * src  = buffer;
        char * dest = address;
        
        for (int i = 0; i < elements; i++)
        {
            memcpy(dest, src, record_size * sizeof(float));
            src  += stride;
            dest += stride;
        }
        
        delete [] buffer;
    }
}

void MatrixDst::configureBuffer(MatrixSrc & a, MatrixSrc & b)
{


    if (!a.intersectingDataSpaces(b))
    {
        // as long as the output only overlays 1 of the sources, and the other
        // source only has 1 matrix in it, only a record_buffer is required
        if (a.elements == 1 && identicalDataSpaces(b))
            record_buffered = JNI_TRUE;
        else if (b.elements == 1 && identicalDataSpaces(a))
            record_buffered = JNI_TRUE;
        else 
        // otherwise all of the output has to be buffered
            createBuffer();
    }
    else
        createBuffer();
}

void MatrixDst::configureBuffer(MatrixSrc & a)
{
        if (identicalDataSpaces(a))
            record_buffered = JNI_TRUE;
        else if (intersectingDataSpaces(a))
            createBuffer();
}
            
void MatrixDst::createBuffer()
{
        data_buffered = JNI_TRUE;
        buffer = new char[ elements * stride ];
        record_offset = buffer - stride;
}

float * MatrixDst::nextMatrix()
{
    record_offset = &record_offset[stride];
    int alignment = ((unsigned int)(record_offset)) & FLOAT_ALIGNMENT;
    
    if (transpose || record_buffered || alignment)
    {
        last_record_in_temp = JNI_TRUE;
        return record;
    }
    else
    {
        last_record_in_temp = JNI_FALSE;
        return (float *) record_offset;
    }
}


void MatrixDst::writeComplete()
{	
    if (last_record_in_temp)
    {	
        // 3 reasons why the record would be in temp
        //
        //	1. The record is not aligned
        //	2. The result will need to be transposed
        //	3. Direct Mode where result would overlay an operand
        
        if (((unsigned int)(record_offset)) & FLOAT_ALIGNMENT)
        {
            if (transpose)
                transposeMatrix(record, width, height);
            memcpy (record, record_offset,  record_size * sizeof(jfloat));
        }
        else if (transpose)
        {
            transposeMatrix(record, (float *) &record_offset[0],  width, height);
        }
        else
            memcpy (record_offset, record, record_size * sizeof(jfloat));
    }
}

///////////////////////////////////////////////////////////////////////////

void subMatrix (const float * src, int side, float * dst , int col_omit, int row_omit)
{
    int index = 0;
    int src_index = 0;

    for (int c = 0; c < side; c++)
    {
        if (c == col_omit)
        {   src_index += side;
            continue;
        }
        for (int r = 0; r < side; r++)
        {
            if (r == row_omit)
            {	src_index++;
                continue;
            }
            dst[index++] = src[src_index++];
        }
    }
} 

float determinant (const float * matrix , int side)
{


    // we are assuming for this case that the data is in column major format
    
    float det = 0;

    if (side == 2)
        // your basic cross product aka 2x2 determinant
        det = matrix[0] * matrix[3] - matrix[2] * matrix[1];	
    else
    {
        // create room to store the sub matrix
        int   temp_side  = side - 1;				// the dimensions of the sub matrix
        float * temp_matrix = new float[temp_side * temp_side];

        // keep the sign (this way we avoid an additional branch in the inner loop)
        float	sign = 1;
        
        for (int row = 0; row < side; row++)
        {
            // get a sub matrix by eliminating the 0th col and the specified row
            subMatrix(matrix, side, temp_matrix, 0, row);
            
            // add to the determinant sign * [a]i0 * [M]i0
            det += sign * matrix[row] * determinant (temp_matrix, temp_side);
            
            // alternate the sign
            sign *= -1;
        }
        
        delete [] temp_matrix;
    }
    
    return det;
}
