// 
// File:   Callback.cc
// Author: alterself
//
// Created on November 28, 2002, 3:37 PM
//

#include "Callback.h"

//
// Constructor
///
JavaMethod::JavaMethod(JNIEnv *newEnv, jobject newObj, string newMethod)
{
    env = newEnv;
    obj = newObj;
    method = newMethod;
}

//
// Destructor
//
JavaMethod::~JavaMethod()
{
    delete(method);
}

