// 
// File:   Callback.cc
// Author: alterself
//
// Created on November 28, 2002, 3:37 PM
//

#include "JavaMethod.h"

//
// Constructor
///
JavaMethod::JavaMethod(JNIEnv *newEnv, jobject newObj, std::string newMethod)
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
}

