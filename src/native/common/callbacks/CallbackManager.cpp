// 
// File:   CallbackManager.cc
// Author: alterself
//
// Created on November 28, 2002, 3:15 PM
//

#include "CallbackManager.h"

//
// Constructor
///
CallbackManager::CallbackManager()
{
}

//
// Destructor
//
CallbackManager::~CallbackManager()
{
        data.clear();
}

bool CallbackManager::add(jint key, CallbackContainer *value)
{
        data[key] = value;
}

bool CallbackManager::del(jint key)
{
        if (data.find(key) != data.end()) {
                delete data[key];
                data.erase(key);
        }
}

CallbackContainer * CallbackManager::get(jint key)
{
        if (data.find(key) == data.end()) {
                return NULL;
        }
        else {
                return data[key];
        }
}
