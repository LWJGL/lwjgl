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
#include "CallbackContainer.h"

#ifndef CALLBACK
#define CALLBACK
#endif


class GLUQuadricCallbacks : public CallbackContainer {
public:
	GLUQuadricCallbacks(GLUquadricObj *quad);
	~GLUQuadricCallbacks();
        void add(JavaMethod*, GLenum);
        
        void CALLBACK gluError(GLenum);
protected:

private:
        JavaMethod* errorCallback;
        GLUquadricObj* quadric;
};

#endif	/* _GLUQuadricCallbacks_H */

