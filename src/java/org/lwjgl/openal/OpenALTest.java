package org.lwjgl.openal;

/**
 * $Id$
 *
 * This is the OpenAL Test.
 * This class will eventually test *all* apects of OpenAL...
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class OpenALTest {
    
    /**
     * Creates an instance of OpenALTest
     */
    public OpenALTest() {
    }
    
    /**
     * main entry point
     *
     * @param args String array containing arguments
     */
    public static void main(String[] args) {
        //create OpenAL instance (and util lib)
        AL al           = new AL();
        ALUT alut       = new ALUT();
        
        /* buffers */
        int[] buffers   = new int[1];
        
        /* sources */
        int[] sources   = new int[1];
        
        /* initialize */
        alut.alutInit(args);
        
        /* create buffers and sources */
        al.alGenBuffers(1, buffers);
        al.alGenSources(1, sources);
        
        /* load data */
        ALUTLoadWAVFile file = alut.alutLoadWAVFile("footsteps.wav");
        
        /* copy to buffers */
        al.alBufferData(buffers[0], file.format, file.data, file.size, file.freq);
        
        /* unload file again */
        alut.alutUnloadWAV(file.format, file.data, file.size, file.freq);
        
        /* set up source input */
        al.alSourcei(sources[0], AL.AL_BUFFER, buffers[0]);
        
        /* lets loop the sound */
        al.alSourcei(sources[0], AL.AL_LOOPING, AL.AL_TRUE);
        
        /* play source 0 */
        al.alSourcePlay(sources[0]);
        
        System.out.println("will exit in 5 seconds (so we don't crash if weird stuff has happened with file...)\n");
        for(int i=0; i<5; i++) {
            try {
                System.out.println(5-i);
                Thread.sleep(1000);
            } catch(InterruptedException inte) {
            }
        }
        
        /* stop source 0 */
        al.alSourceStop(sources[0]);
        
        /* delete buffers and sources */
        al.alDeleteSources(1, sources);
        al.alDeleteBuffers(1, buffers);
        
        /* shutdown */
        alut.alutExit();
    }
}