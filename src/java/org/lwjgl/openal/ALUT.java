package org.lwjgl.openal;

/**
 * $Id$
 *
 * This is the utility class for OpenAL. This class implements functions
 * in alut.h
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ALUT {
    
    static {
        try {
            System.loadLibrary(org.lwjgl.Sys.LIBRARY_NAME);
        } catch (UnsatisfiedLinkError ule) {
            System.out.println("Failed to load OpenAL library: " + org.lwjgl.Sys.LIBRARY_NAME);
            ule.printStackTrace();
        }
    }
    
    /** Creates a new instance of ALUT */
    public ALUT() {
    }
    
    /**
     * Initializes the OpenAL engine
     *
     * @param args String array of arguments to engine
     */
    public native void                  alutInit(String[] args);
    
    /**
     * Loads a wave file into memory
     *
     * @param file name of file to load (in current working directory)
     * @return ALUTLoadWAVFile object containing information regarding file loaded
     */
    public native ALUTLoadWAVFile       alutLoadWAVFile(String file);
    
    /**
     * Unloads the specified file from memory
     *
     * @param format OpenAL format specifier
     * @param data address of data (pointer)
     * @param size size of the data in bytes
     * @param freq frequency of the data
     */
    public native void                  alutUnloadWAV(int format, int data, int size, int freq);
    
    /**
     * Deinitializes the OpenAL engine
     */
    public native void                  alutExit();
}