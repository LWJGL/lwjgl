This is just the start of my callback implementation.

Unfortunatly, you cant pass a non static method as a function pointer...
So we can only have callbacks for a single GLU object. However, 
according to the OpenGL redbook... this should not be an issue. For example
the redbook states that, a single tess object should be used for an 
entire program... and reused for each tessleation.

The implementation:

JavaMethod: a data object that contains information on the method to call.

GLUQuadricCallbacks: a class for working with quadric callbacks

eventually you can expect callbacks for glu nurbs and glu tesselators.
Of course callbacks for other object types should be easy to do using this framework.


Note as elegent as I wanted, but it works.

You would write a callback like this:

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricCallback
 * Signature: (IILjava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_quadricCallback__IILjava_lang_String_2
  (JNIEnv * env, jobject obj, jint quad, jint type, jstring method)
{
    GLUQuadricCallbacks::set(quad, 
                             new JavaMethod(env, obj, env->GetStringUTFChars(method, 0)),
                             type);
    CHECK_GL_ERROR  
}

/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    quadricCallback
 * Signature: (IILjava/lang/Object;Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_quadricCallback__IILjava_lang_Object_2Ljava_lang_String_2
  (JNIEnv * env, jobject obj, jint quad, jint type, jobject target, jstring method)
{
    GLUQuadricCallbacks::set(quad, 
                             new JavaMethod(env, target, env->GetStringUTFChars(method, 0)),
                             type);
    CHECK_GL_ERROR
}


/*
 * Class:     org_lwjgl_opengl_GLU
 * Method:    deleteQuadric
 */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_deleteQuadric(JNIEnv * env, jobject obj, jint quad) 
{
    gluDeleteQuadric((GLUquadricObj *) quad);
    GLUQuadricCallbacks::clear();
    CHECK_GL_ERROR
}



and call it from java:

/* myquadric is a reference to a GLUquadricObj returned by glu.newQuadric()
 * GLU.ERROR is the callback type
 * errorCallback is the method you wish to be called */
glu.quadricCallback(myquadric, GLU.ERROR, "errorCallback");

or

glu.quadricCallback(myquadric, GLU.ERROR, someObject, "errorCallback");

