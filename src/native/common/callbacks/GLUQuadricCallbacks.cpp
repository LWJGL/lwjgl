// 
// File:   GLUQuadricCallback.cc
// Author: alterself
//
// Created on November 28, 2002, 8:21 PM
//

#include "GLUQuadricCallbacks.h"

JavaMethod* GLUQuadricCallbacks::errorCallback;

//
// Constructor
///
GLUQuadricCallbacks::GLUQuadricCallbacks()
{
        errorCallback = NULL;
}

//
// Destructor
//
GLUQuadricCallbacks::~GLUQuadricCallbacks()
{
    clear();
}

void GLUQuadricCallbacks::clear() {
    if (errorCallback != NULL) {
        delete errorCallback;
    }   
}

typedef void (GLAPIENTRY *callback_t)();

void GLUQuadricCallbacks::set(jint globj, JavaMethod* cb, jint type)
{   
    switch (type) {
        case GLU_ERROR:
            /* If we are already refering to a callback, get rid of it */
            if (errorCallback != NULL) {
                delete errorCallback;
            }
            if (cb == NULL) {
                gluQuadricCallback((GLUquadricObj *) globj, 
                                   (GLenum) type, 
                                   NULL);                
            }
            else {
                errorCallback = cb;
                gluQuadricCallback((GLUquadricObj *) globj, 
                                   (GLenum) type, 
                                   (callback_t) GLUQuadricCallbacks::gluError);
            }
            break;
    }
}

void CALLBACK GLUQuadricCallbacks::gluError(GLenum type) {

    if (errorCallback == NULL) {
        return;
    }
    
    JNIEnv * env = errorCallback->env;
    jobject obj = errorCallback->obj;
    
    jclass cls = (jclass) env->GetObjectClass(obj);
    
    jmethodID mid = env->GetMethodID(cls, 
                                     errorCallback->method.c_str(),
                                     "(I)V");
    
    if (mid == 0) {
        return;
    }
    /* Hopefully this will end up calling the java method for handling GLU_ERROR for this quad */
    env->CallVoidMethod(obj, mid, (jint) type);
}
