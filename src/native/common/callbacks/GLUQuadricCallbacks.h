// 
// File:   GLUQuadricCallbacks.h
// Author: alterself
//
// Created on November 28, 2002, 8:21 PM
//

#ifndef _GLUQuadricCallbacks_H
#define	_GLUQuadricCallbacks_H

#include "extgl.h"
#include <GL/glu.h>
#include <stdlib.h>
#include <stdio.h>

#include "JavaMethod.h"

#ifndef CALLBACK
#define CALLBACK
#endif


class GLUQuadricCallbacks {
public:
	GLUQuadricCallbacks();
	~GLUQuadricCallbacks();
        
        static void CALLBACK gluError(GLenum);
        static void set(jint, JavaMethod*, jint);
        static void clear();
protected:

private:
        static JavaMethod* errorCallback;
};
#endif	/* _GLUQuadricCallbacks_H */

