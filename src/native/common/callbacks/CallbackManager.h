// 
// File:   CallbackManager.h
// Author: alterself
//
// Created on November 28, 2002, 3:15 PM
//

#ifndef _CallbackManager_H
#define	_CallbackManager_H
#include <map>
#include <jni.h>

#include "CallbackContainer.h"

class CallbackManager {
public:
	CallbackManager();
	~CallbackManager();

        bool add(jint, CallbackContainer*);
        bool del(jint);
        CallbackContainer* get(jint);
        
protected:
private:
        std::map<jint, CallbackContainer*> data;
};

#endif	/* _CallbackManager_H */

