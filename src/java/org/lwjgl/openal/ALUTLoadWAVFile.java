package org.lwjgl.openal;

/**
 * $Id$
 *
 * This class is used by the alutLoadWAVFile method. Since we
 * cannot modify values supplied to the method (since JNI is pass by value)
 * we return this object, which encapsulates the file loaded. Use this class
 * when unloading using 'alutUnloadWAV'.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ALUTLoadWAVFile {
    
    /* format of file */
    public final int format;
    
    /* pointer to data allocated */
    public final int data;
    
    /* size of data allocated */
    public final int size;
    
    /* frequency of sound data */
    public final int freq;
    
    /* whether or not to loop */
    public final boolean loop;
    
    /**
     * Creates an ALUTLoadWAVFile object with specified properties
     *
     * @param format OpenAL format specifier
     * @param data address of data (pointer)
     * @param size size of the data in bytes
     * @param freq frequency of the data
     * @param loop looping indicator for the WAV data
     */
    public ALUTLoadWAVFile(int format, int data, int size, int freq, boolean loop) {
        this.format     = format;
        this.data       = data;
        this.size       = size;
        this.freq       = freq;
        this.loop       = loop;
    }
}