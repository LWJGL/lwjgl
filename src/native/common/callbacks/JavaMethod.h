// 
// File:   Callback.h
// Author: alterself
//
// Created on November 28, 2002, 3:37 PM
//

#ifndef _JavaMethod_H
#define	_JavaMethod_H
#include <string>


class JavaMethod {
public:
	JavaMethod(JNIEnv *, jobject, string);
	~JavaMethod();

protected:

private:
    JNIEnv* env;
    jobject obj;
    string method;

};

#endif	/* _JavaMethod_H */

