This is just the start of my callback implementation.

As it is, it wont compile =)

Basically... we have 3 main classes...

CallbackManager, which maintains a mapping of objects to callback containers
CallbackContainer, the base class which we extend to implement callbacks for specific objects
JavaMethod, which is a data object that contains information on method the method to call back too.

GLUQuadricCallbacks is a CallbackContainer for working with quadric callbacks
eventually you can expect containers to callbacks for glu nurbs and glu tesselators.
Of course callbacks for other object types should be easy to do using this framework.


Eventually... to add a callback you would do things like:

/* quad is the reference to our GLUquadricObj
 * type is one of the error types specified by gluQuadricCallback
 * method is the name of the java method to call */
JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_quadricCallback
  (JNIEnv * env, jobject obj, jint quad, jint type, jint method) {
    /* if this quad has no callback container, make one */
    if (CallbackManager.get(quad) == null) {
        CallbackManager.put(quad, new GLUQuadricCallbacks((GLUquadricObj *) quad);
    }

    /* get the callback container for this quad */
    CallbackManager.get(quad)->add(new JavaMethod(env, obj, (char*)method), (GLenum) type);
}

JNIEXPORT void JNICALL Java_org_lwjgl_opengl_GLU_deleteQuadric
  (JNIEnv * env, jobject obj, jint quad) {
    /* Delete the quadric from memory */
    gluDeleteQuadric((GLUquadricObj *) quad);

    /* delete any callbacks we assigned to the quadric */
    CallbackManager.del(quad);
}
