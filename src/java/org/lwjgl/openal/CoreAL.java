package org.lwjgl.openal;

/**
 * $Id$
 *
 * This is the core OpenAL class. This class implements 
 * AL.h version 1.0
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class CoreAL extends BaseAL implements BaseALConstants {
    
    /** Creates a new instance of CoreAL */
    public CoreAL() {
    }
    
    /**
     * Retrieve the current error state and then clears the error state.
     *
     * @return current error state
     */
    public native int           getError();
    
    /**
     * Retrieve an OpenAL string property.
     *
     * @param param The property to be returned
     * @return OpenAL String property
     */
    public native String        getString(int param);
    
    /**
     * Generate one or more buffers.
     *
     * @param n number of buffers to generate
     * @param buffers array holding buffers
     */
    public native void          genBuffers(int n, int[] buffers);
    
    /**
     * Generate one or more sources.
     *
     * @param n number of sources to generate
     * @param sources array holding sources
     */
    public native void          genSources(int n, int[] sources);
    
    /**
     * Fill a buffer with audio data.
     *
     * @param buffer Buffer to fill
     * @param format format sound data is in
     * @param data location of data (pointer)
     * @param size size of data segment
     * @param freq frequency of data
     */
    public native void          bufferData(int buffer, int format, int data, int size, int freq);
    
    /**
     * Set an integer property of a source.
     *
     * @param source Source to det property on
     * @param param property to set
     * @param value value of property
     */
    public native void          sourcei(int source, int param, int value);
    
    /**
     * Play a source.
     *
     * @param source Source to play
     */
    public native void          sourcePlay(int source);
    
    /**
     * Stops a source.
     *
     * @param source Source to stop
     */
    public native void          sourceStop(int source);
    
    /**
     * Delete one or more sources.
     *
     * @param n Number of sources to delete
     * @param source Source array to delete from
     */
    public native void          deleteSources(int n, int[] source);
    
    
    /**
     * Delete one or more buffers.
     *
     * @param n Number of buffers to delete
     * @param buffers Buffer array to delete from
     */
    public native void          deleteBuffers(int n, int[] buffers);
}
