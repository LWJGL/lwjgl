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

class CallbackManager {
public:
	CallbackManager();
	~CallbackManager();

        static bool add(jint, CallbackContainer);
        static bool del(jint);
        static CallbackContainer get(jint);
        
protected:
private:
        static map<jint, CallbackContainer> data;
};

#endif	/* _CallbackManager_H */

