// 
// File:   GLUQuadricCallback.h
// Author: alterself
//
// Created on November 28, 2002, 8:21 PM
//

#ifndef _GLUQuadricCallback_H
#define	_GLUQuadricCallback_H

#include "extgl.h"

class GLUQuadricCallbacks : public Callback {
public:
	GLUQuadricCallback(jint quad);
	~GLUQuadricCallback();
        void add(JavaMethod*, GLenum);
        
        void gluError(GLenum);
protected:

private:
        GLUquadricObj *quadric;
        JavaMethod *errorCallback;
};

#endif	/* _GLUQuadricCallback_H */

