package org.lwjgl.openal;

/**
 * $Id$
 *
 * The base AL functionality (no actual AL methods).
 *
 * This has been provided as a base class that we can use for either the
 * full AL 1.0 specification or as a cut-down OpenAL embedded spec. (aka
 * a mini-driver).
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public abstract class BaseAL {
    static {
        try {
            System.loadLibrary(org.lwjgl.Sys.LIBRARY_NAME);
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("Failed to load OpenAL library: " + org.lwjgl.Sys.LIBRARY_NAME);
            ule.printStackTrace();
        }
    }
}