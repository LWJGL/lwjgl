/*
 *  Matrix.h
 *  
 *
 *  Created by tristan on Sat Aug 24 2002.
 *  Copyright (c) 2001 __MyCompanyName__. All rights reserved.
 *
 */
 


#define FLOAT_ALIGNMENT 0x00000003

float determinant (const float * matrix , int side);
void subMatrix (const float * src, int side, float * dst , int col_omit, int row_omit);

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

class SrcMatrix: public Matrix
{
    private:
        char * record_offset;		// the offset of this record in memory
    
        float * record;			// temporary storage to store a fully aligned and transposed
                                        // copy of the record, if the one in memory is not so
        float * current_record_ptr;	// the address of the memory containing the record last
                                        // returned by the nextRecord() function
        jint record_size;		// the total floats in each record

    public:
        SrcMatrix ( jint address, jint stride, jint width, jint height, jint elements, jboolean transpose);
        void rewind() {	record_offset = address; }
        float * nextRecord();
        ~SrcMatrix();
};

///////////////////////////////////////////////////////////////////////////////////////
// Dst Matrix
//////////////////////////////////////////////////////////////////////////////////////

class DstMatrix: public Matrix
{
    char  * record_offset;	// the offset of the record in memory

    jboolean data_buffered;	// if all of the data has to be buffered
    char * buffer;		// a buffer used when data_buffered
    
    jboolean last_record_in_temp;
    jboolean record_buffered;	// if only a single record is buffered
    float * record;		// to store data if source is unaligned
    
    jint record_size;

    public:
        DstMatrix (jint address, jint stride, jint width, jint height, jint elements, jboolean transpose);
        void configureBuffer(SrcMatrix & a, SrcMatrix & b);        
        void configureBuffer(SrcMatrix & a);    
        void createBuffer();
        float * nextRecord();
        void writeRecord();
        ~DstMatrix();
};


