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
        alut.init(args);
        
        /* create buffers and sources */
        al.genBuffers(1, buffers);
        al.genSources(1, sources);
        
        /* load data */
        ALUTLoadWAVFile file = alut.loadWAVFile("footsteps.wav");
        
        /* copy to buffers */
        al.bufferData(buffers[0], file.format, file.data, file.size, file.freq);
        
        /* unload file again */
        alut.unloadWAV(file.format, file.data, file.size, file.freq);
        
        /* set up source input */
        al.sourcei(sources[0], AL.BUFFER, buffers[0]);
        
        /* lets loop the sound */
        al.sourcei(sources[0], AL.LOOPING, AL.TRUE);
        
        /* play source 0 */
        al.sourcePlay(sources[0]);
        
        System.out.println("will exit in 5 seconds (so we don't crash if weird stuff has happened with file...)\n");
        for(int i=0; i<5; i++) {
            try {
                System.out.println(5-i);
                Thread.sleep(1000);
            } catch(InterruptedException inte) {
            }
        }
        
        /* stop source 0 */
        al.sourceStop(sources[0]);
        
        /* delete buffers and sources */
        al.deleteSources(1, sources);
        al.deleteBuffers(1, buffers);
        
        /* shutdown */
        alut.exit();
    }
}