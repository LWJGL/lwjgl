/*
 *  Matrix.h
 *  
 *
 *  Created by tristan on Sat Aug 24 2002.
 *  Copyright (c) 2001 __MyCompanyName__. All rights reserved.
 *
 */
 


////////////////////////////////////////////////////////////////////////////////////////
// Utility Functions
////////////////////////////////////////////////////////////////////////////////////////

#define FLOAT_ALIGNMENT 0x00000003

// 23 bit mantisa on a float (we need error for checking if two nums are equal)
// for now use error of 1/2^18, this could be refined up to 1/2^22 if needed
#define FLOATING_POINT_ERROR (1.0f/262144.0f)

// check if two numbers are approximately equal, used when floating point errors
// occur.  Should NEVER check to see if two floats are identical

inline bool approxEqual(float a, float b)
{
    a -= b;
    a = (a < 0) ? -a: a;
    return (a < FLOATING_POINT_ERROR);
}

float determinant (const float * matrix , int side);
void subMatrix    (const float * src, int side, float * dst , int col_omit, int row_omit);



///////////////////////////////////////////////////////////////////////////////////////
// Matrix
//////////////////////////////////////////////////////////////////////////////////////

class Matrix
{
    protected:
        float * transpose_record;		// to use while transposing the record

    public:
        char * address;		// the start of the data
        jint stride;		// the distance between each record   
        jint 	 width, 	// the width of the matrix
                 height, 	// the height of the matrix
                 elements;	// the number of matricies
        jboolean transpose;	// whether this matrix is or will be transposed
       
        Matrix (jint a, jint s, jint e):
            address((char *)a), stride(s), elements(e) {}
        bool identicalDataSpaces   (Matrix & other);
        bool intersectingDataSpaces(Matrix & other);
        void transposeMatrix(float * src, float * dst, int src_width, int src_height);
        void transposeMatrix(float * mat, int src_width, int src_height);
};

///////////////////////////////////////////////////////////////////////////////////////
// Src Matrix
//////////////////////////////////////////////////////////////////////////////////////

class MatrixSrc: public Matrix
{
    private:
        char * record_offset;		// the offset of this record in memory
    
        float * record;			// temporary storage to store a fully aligned and transposed
                                        // copy of the record, if the one in memory is not so
        float * current_record_ptr;	// the address of the memory containing the record last
                                        // returned by the nextMatrix() function
        jint record_size;		// the total floats in each record

    public:
        MatrixSrc ( jint address, jint stride, jint width, jint height, jint elements, jboolean transpose);
        ~MatrixSrc();
        
        void rewind() {	record_offset = address; }
        float * nextMatrix();

};

///////////////////////////////////////////////////////////////////////////////////////
// Dst Matrix
//////////////////////////////////////////////////////////////////////////////////////

class MatrixDst: public Matrix
{
    private:
        char  * record_offset;	// the offset of the record in memory
    
        jboolean data_buffered;	// if all of the data has to be buffered
        char * buffer;		// a buffer used when data_buffered
        
        jboolean last_record_in_temp;
        jboolean record_buffered;	// if only a single record is buffered
        float * record;		// to store data if source is unaligned
        
        jint record_size;
        void createBuffer();

    public:
        MatrixDst (jint address, jint stride, jint width, jint height, jint elements, jboolean transpose);
        ~MatrixDst();
        void configureBuffer(MatrixSrc & a, MatrixSrc & b);        
        void configureBuffer(MatrixSrc & a);    
        
        float * nextMatrix();
        void writeComplete();

};


