// 
// File:   GLUQuadricCallback.cc
// Author: alterself
//
// Created on November 28, 2002, 8:21 PM
//

#include "GLUQuadricCallback.h"

//
// Constructor
///
GLUQuadricCallbacks::GLUQuadricCallbacks(GLUquadricObj *quad):
    CallbackContainer()
{
        quadric = quad;
        errorCallback = NULL;
}

//
// Destructor
//
GLUQuadricCallbacks::~GLUQuadricCallbacks()
{
    if (errorCallback != NULL) {
        delete errorCallback;
    }
    delete quad;
}

void GLUQuadricCallbacks::add(JavaMethod *cb, GLenum type)
{
    /* If we are already refering to a callback, get rid of it */
    if (gluError != NULL) {
        delete errorCallback;
    }
    switch (type) {
        case GLU_ERROR;
            errorCallback = cb;
            gluQuadricCallback(quad, type, *gluError)
            break;
    };
}

void GLUQuadricCallbacks::gluError(GLenum type) {
    jclass cls = (*errorCallback->env)->GetObjectClass(errorCallback->env, errorCallback.obj);
    jmethodID mid = (*errorCallback->env)->getMethodID(errorCallback->env, cls, errorCallback.method.c_str());
    if (mid == 0) {
        return;
    }
    /* Hopefully this will end up calling the java method for handling GLU_ERROR for this quad */
    (*errorCallback->env)->CallVoidMethod(errorCallback->env, errorCallback.obj, mid, (jint) type);
}