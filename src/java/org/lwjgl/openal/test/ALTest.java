/*
 * Copyright (c) 2002 Light Weight Java Game Library Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'Light Weight Java Game Library' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.openal.test;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.ALCcontext;
import org.lwjgl.openal.ALCdevice;
import org.lwjgl.openal.ALUT;
import org.lwjgl.openal.ALUTLoadWAVData;
import org.lwjgl.openal.eax.EAX;
import org.lwjgl.openal.eax.EAXBufferProperties;
import org.lwjgl.openal.eax.EAXListenerProperties;
import org.lwjgl.Sys;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.FloatBuffer;

import java.io.IOException;
/**
 * $Id$
 *
 * This class tests al functionality, much like altest.c
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class ALTest extends BasicTest {
    
    /** number of buffers used */
    public static final int NUM_BUFFERS     = 7;
    
    /**  buffers used */
    protected IntBuffer buffers;
    
    /** enumerations string */
    protected String[] enumerationString    = new String[]{
        "AL_INVALID",
        "AL_INVALID",
        "ALC_INVALID",
        "AL_NONE",
        "AL_FALSE",
        "ALC_FALSE",
        "AL_TRUE",
        "ALC_TRUE",
        "AL_SOURCE_RELATIVE",
        "AL_CONE_INNER_ANGLE",
        "AL_CONE_OUTER_ANGLE",
        "AL_PITCH",
        "AL_POSITION",
        "AL_DIRECTION",
        "AL_VELOCITY",
        "AL_LOOPING",
        "AL_BUFFER",
        "AL_GAIN",
        "AL_MIN_GAIN",
        "AL_MAX_GAIN",
        "AL_ORIENTATION",
        "AL_REFERENCE_DISTANCE",
        "AL_ROLLOFF_FACTOR",
        "AL_CONE_OUTER_GAIN",
        "AL_MAX_DISTANCE",
        "AL_SOURCE_STATE",
        "AL_INITIAL",
        "AL_PLAYING",
        "AL_PAUSED",
        "AL_STOPPED",
        "AL_BUFFERS_QUEUED",
        "AL_BUFFERS_PROCESSED",
        "AL_FORMAT_MONO8",
        "AL_FORMAT_MONO16",
        "AL_FORMAT_STEREO8",
        "AL_FORMAT_STEREO16",
        "AL_FREQUENCY",
        "AL_SIZE",
        "AL_UNUSED",
        "AL_PENDING",
        "AL_PROCESSED",
        "ALC_MAJOR_VERSION",
        "ALC_MINOR_VERSION",
        "ALC_ATTRIBUTES_SIZE",
        "ALC_ALL_ATTRIBUTES",
        "ALC_DEFAULT_DEVICE_SPECIFIER",
        "ALC_DEVICE_SPECIFIER",
        "ALC_EXTENSIONS",
        "ALC_FREQUENCY",
        "ALC_REFRESH",
        "ALC_SYNC",
        "AL_NO_ERROR",
        "AL_INVALID_NAME",
        "AL_INVALID_ENUM",
        "AL_INVALID_VALUE",
        "AL_INVALID_OPERATION",
        "AL_OUT_OF_MEMORY",
        "ALC_NO_ERROR",
        "ALC_INVALID_DEVICE",
        "ALC_INVALID_CONTEXT",
        "ALC_INVALID_ENUM",
        "ALC_INVALID_VALUE",
        "ALC_OUT_OF_MEMORY",
        "AL_VENDOR",
        "AL_VERSION",
        "AL_RENDERER",
        "AL_EXTENSIONS",
        "AL_DOPPLER_FACTOR",
        "AL_DOPPLER_VELOCITY",
        "AL_DISTANCE_MODEL",
        "AL_INVERSE_DISTANCE",
        "AL_INVERSE_DISTANCE_CLAMPED"
    };
    
    /** enumerations string */
    protected int[] enumeration    = new int[]{
        AL.INVALID,
        AL.INVALID,
        ALC.INVALID,
        AL.NONE,
        AL.FALSE,
        ALC.FALSE,
        AL.TRUE,
        ALC.TRUE,
        AL.SOURCE_RELATIVE,
        AL.CONE_INNER_ANGLE,
        AL.CONE_OUTER_ANGLE,
        AL.PITCH,
        AL.POSITION,
        AL.DIRECTION,
        AL.VELOCITY,
        AL.LOOPING,
        AL.BUFFER,
        AL.GAIN,
        AL.MIN_GAIN,
        AL.MAX_GAIN,
        AL.ORIENTATION,
        AL.REFERENCE_DISTANCE,
        AL.ROLLOFF_FACTOR,
        AL.CONE_OUTER_GAIN,
        AL.MAX_DISTANCE,
        AL.SOURCE_STATE,
        AL.INITIAL,
        AL.PLAYING,
        AL.PAUSED,
        AL.STOPPED,
        AL.BUFFERS_QUEUED,
        AL.BUFFERS_PROCESSED,
        AL.FORMAT_MONO8,
        AL.FORMAT_MONO16,
        AL.FORMAT_STEREO8,
        AL.FORMAT_STEREO16,
        AL.FREQUENCY,
        AL.SIZE,
        AL.UNUSED,
        AL.PENDING,
        AL.PROCESSED,
        ALC.MAJOR_VERSION,
        ALC.MINOR_VERSION,
        ALC.ATTRIBUTES_SIZE,
        ALC.ALL_ATTRIBUTES,
        ALC.DEFAULT_DEVICE_SPECIFIER,
        ALC.DEVICE_SPECIFIER,
        ALC.EXTENSIONS,
        ALC.FREQUENCY,
        ALC.REFRESH,
        ALC.SYNC,
        AL.NO_ERROR,
        AL.INVALID_NAME,
        AL.INVALID_ENUM,
        AL.INVALID_VALUE,
        AL.INVALID_OPERATION,
        AL.OUT_OF_MEMORY,
        ALC.NO_ERROR,
        ALC.INVALID_DEVICE,
        ALC.INVALID_CONTEXT,
        ALC.INVALID_ENUM,
        ALC.INVALID_VALUE,
        ALC.OUT_OF_MEMORY,
        AL.VENDOR,
        AL.VERSION,
        AL.RENDERER,
        AL.EXTENSIONS,
        AL.DOPPLER_FACTOR,
        AL.DOPPLER_VELOCITY,
        AL.DISTANCE_MODEL,
        AL.INVERSE_DISTANCE,
        AL.INVERSE_DISTANCE_CLAMPED
    };
    
    /** Whether or not EAX is supported */
    protected boolean eaxAvailable = false;
    
    /**
     * Creates an instance of ALTest
     */
    public ALTest() {
        super();
        
        buffers = createIntBuffer(NUM_BUFFERS);
    }
    
    /**
     * Creates an integer buffer to hold specified ints
     * - strictly a utility method
     *
     * @param size how many int to contain
     * @return created IntBuffer
     */
    protected IntBuffer createIntBuffer(int size) {
        ByteBuffer temp = ByteBuffer.allocateDirect(4*size);
        temp.order(ByteOrder.nativeOrder());
        
        return temp.asIntBuffer();
    }    
    
    /**
     * Creates a float buffer to hold specified floats
     * - strictly a utility method
     *
     * @param size how many floats to contain
     * @return created FloatBuffer
     */
    protected FloatBuffer createFloatBuffer(int size) {
        ByteBuffer temp = ByteBuffer.allocateDirect(4*size);
        temp.order(ByteOrder.nativeOrder());
        
        return temp.asFloatBuffer();
    }    
    
    /**
     * Sleeeeeep
     */
    protected void delay_ms(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException inte) {
            inte.printStackTrace();
        }
    }
    
    /**
     * Display error
     */
    protected void displayALError(String text, int errorcode) {
        System.out.println(text + " - " + al.getString(errorcode));
    }
    
    /**
     * Runs the actual test, using supplied arguments
     */
    protected void execute(String[] args) {
        String szEAX = "EAX";
        String szFnName;
        int	ch = -1;
        int error;
        ALCcontext context;
        ALCdevice device;
        
        FloatBuffer listenerPos = createFloatBuffer(3);
        listenerPos.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer listenerVel = createFloatBuffer(3);
        listenerVel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f});
        
        System.out.print("OpenAL Test Application (Java!)\n");
        System.out.print("=======================\n\n");
        
        // Initialize Open AL manually
        //Open device
        device = alc.openDevice("DirectSound3D");
        //Create context(s)
        context = alc.createContext(device, 0);
        //Set active context
        alc.makeContextCurrent(context);
        
        // Clear Error Code
        al.getError();
        
        // Set Listener attributes
        
        // Position ...
        al.listenerfv(AL.POSITION, Sys.getDirectBufferAddress(listenerPos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alListenerfv POSITION : ", error);
            System.exit(-1);
        }
        
        // Velocity ...
        al.listenerfv(AL.VELOCITY, Sys.getDirectBufferAddress(listenerVel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alListenerfv VELOCITY : ", error);
            System.exit(-1);
        }
        
        // Orientation ...
        al.listenerfv(AL.ORIENTATION, Sys.getDirectBufferAddress(listenerOri));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alListenerfv ORIENTATION : ", error);
            System.exit(-1);
        }
        
        // Generate Buffers
        al.genBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
        
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenBuffers :", error);
            System.exit(-1);
        }
        
        // Load in samples to be used by Test functions
        // Load footsteps.wav
        ALUTLoadWAVData data = alut.loadWAVFile("footsteps.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile footsteps.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy footsteps.wav data into AL Buffer 0
        al.bufferData(buffers.get(0), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 0 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload footsteps.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Load ding.wav
        data = alut.loadWAVFile("ding.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile ding.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy ding.wav data into AL Buffer 1
        al.bufferData(buffers.get(1), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 1 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload footsteps.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Load wave1.wav
        data = alut.loadWAVFile("wave1.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile wave1.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy wave1.wav data into AL Buffer 2
        al.bufferData(buffers.get(2), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 2 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload wave1.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Load Wave2.wav
        data = alut.loadWAVFile("Wave2.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile Wave2.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy Wave2.wav data into AL Buffer 3
        al.bufferData(buffers.get(3), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 3 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload Wave2.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Load wave3.wav
        data = alut.loadWAVFile("wave3.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile wave3.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy wave3.wav data into AL Buffer 4
        al.bufferData(buffers.get(4), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 4 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload wave3.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Load wave4.wav
        data = alut.loadWAVFile("wave4.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile wave4.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy wave4.wav data into AL Buffer 5
        al.bufferData(buffers.get(5), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 5 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload wave4.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Load stereo.wav
        data = alut.loadWAVFile("stereo.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile stereo.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Copy stereo.wav data into AL Buffer 6
        al.bufferData(buffers.get(6), data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 6 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        // Unload stereo.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, Sys.getDirectBufferAddress(buffers));
            System.exit(-1);
        }
        
        //do EAX check (can only be performed after device / context creation
        eaxAvailable = al.isExtensionPresent("EAX");
        
        do {
            System.out.print("\n\n\nAutomated Test Series:\n\n");
            System.out.print("A) Run Fully Automated Tests\n");
            System.out.print("*B) Run Semi-Automated Tests\n");
            System.out.print("\nInteractive Tests:\n\n");
            System.out.print("1 Position Test\n");
            System.out.print("2 Looping Test\n");
            System.out.print("3 EAX 2.0 Test\n");
            System.out.print("4 Queue Test\n");
            System.out.print("5 Buffer Test\n");
            System.out.print("6 Frequency Test\n");
            System.out.print("7 Stereo Test\n");
            System.out.print("8 Gain Test\n");
            System.out.print("*9 Streaming Test\n");
            System.out.print("0 Multiple Sources Test\n");
            
            System.out.print("\nQ to quit\n\n\n");
            
            try {
                ch = System.in.read();
                System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case 'A':
                    fullAutoTests();
                    break;
                case 'B':
                    semiAutoTests();
                    break;
                case '1':
                    i_PositionTest();
                    break;
                case '2':
                    i_LoopingTest();
                    break;
                case '3':
                    if(eaxAvailable) {
                        i_EAXTest();
                    } else {
                        System.out.println("EAX not supported");
                    }
                    break;
                case '4':
                    i_QueueTest();
                    break;
                case '5':
                    i_BufferTest();
                    break;
                case '6':
                    i_FreqTest();
                    break;
                case '7':
                    i_StereoTest();
                    break;
                case '8':
                    i_GainTest();
                    break;
                case '9':
                    i_StreamingTest();
                    break;
                case '0':
                    i_MultipleSourcesTest();
                    break;
                default:
                    break;
            }
        } while (ch != 'Q');
    }
    
    protected void fullAutoTests() {
        fA_RequestObjectNames();  // Request Object Names
        fA_ReleaseObjectNames();  // Release Object Names
        fA_ValidateObjectNames();  // Validating Object Names
        fA_StateTransition();  // State Transistion Testing
        fA_VectorStateTransition();  // Vector State Transistion Testing
        fA_GetBufferProperties();  // Get Buffer Properties
        fA_EnumerationValue(); // Enumeration Value Test
        fA_QueuingUnderrunStates(); // test underrun while queuing

        System.out.print("\n\n");
        delay_ms(1000);
    }
    
    protected void fA_RequestObjectNames() {
        boolean localResultOK;
        IntBuffer testBuffers = createIntBuffer(1);
        IntBuffer testSources = createIntBuffer(1);

        int error;
        
        System.out.print("\nRequest Object Names Test. ");
        al.getError(); // clear error state
        localResultOK = true;
        al.genBuffers(0, Sys.getDirectBufferAddress(testBuffers)); // should be equivalent to NOP
        error = al.getError();
        if (error != AL.NO_ERROR) {
            localResultOK = false;
        }
        al.genSources(0, Sys.getDirectBufferAddress(testSources)); // should be equivalent to NOP
        error = al.getError();
        if (error != AL.NO_ERROR) {
            localResultOK = false;
        }
        al.genBuffers(-1, Sys.getDirectBufferAddress(testBuffers)); // invalid -- make sure error code comes back
        error = al.getError();
        if (error == AL.NO_ERROR) {
            localResultOK = false;
        }
        al.genSources(-1, Sys.getDirectBufferAddress(testSources)); // invalid -- make sure error code comes back
        error = al.getError();
        if (error == AL.NO_ERROR) {
            localResultOK = false;
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
    }
    
    protected void fA_ReleaseObjectNames() {
        boolean localResultOK;
        IntBuffer testBuffers = createIntBuffer(1);
        IntBuffer testSources = createIntBuffer(1);
        int error;
        
        System.out.print("\nReleasing Object Names Test. ");
        al.getError();
        localResultOK = true;
        al.deleteBuffers(-1, Sys.getDirectBufferAddress(testBuffers)); // invalid -- make sure error code comes back
        error = al.getError();
        if (error == AL.NO_ERROR) {
            localResultOK = false;
        }
        al.deleteSources(-1, Sys.getDirectBufferAddress(testSources)); // invalid -- make sure error code comes back
        error = al.getError();
        if (error == AL.NO_ERROR) {
            localResultOK = false;
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
    }
    
    protected void fA_ValidateObjectNames() {
        boolean localResultOK;
        IntBuffer testBuffers = createIntBuffer(1);
        IntBuffer testSources = createIntBuffer(1);
        int error;
        
        System.out.print("\nValidating Object Names Test. ");
        al.getError();
        localResultOK = true;
        al.genBuffers(1, Sys.getDirectBufferAddress(testBuffers));
        al.genSources(1, Sys.getDirectBufferAddress(testSources));
        error = al.getError();
        if (error != AL.NO_ERROR) {
            localResultOK = false;
        } else {
            if (al.isBuffer(testBuffers.get(0)) == false) // this buffer should test as valid
            {
                localResultOK = false;
            }
            if (al.isSource(testSources.get(0)) == false) // this source should test as valid
            {
                localResultOK = false;
            }
            if (al.isBuffer(testBuffers.get(0) + 1) == true) // this buffer should be invalid
            {
                localResultOK = false;
            }
            if (al.isSource(testSources.get(0) + 1) == true) // this source should be invalid
            {
                localResultOK = false;
            }
            al.deleteBuffers(1, Sys.getDirectBufferAddress(testBuffers));
            al.deleteSources(1, Sys.getDirectBufferAddress(testSources));
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
    }
    
    protected void fA_StateTransition() {
        boolean localResultOK;
        IntBuffer testSources = createIntBuffer(1);        

        System.out.print("\nState Transition Test. ");
        al.getError();
        localResultOK = true;
        al.genSources(1, Sys.getDirectBufferAddress(testSources));
        al.sourcei(testSources.get(0), AL.BUFFER, buffers.get(0));
        al.sourcei(testSources.get(0), AL.LOOPING, AL.TRUE);
        
        IntBuffer sourceState = createIntBuffer(1);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.INITIAL) {
            localResultOK = false;
        }
        al.sourcePlay(testSources.get(0));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PLAYING) {
            localResultOK = false;
        }
        al.sourcePause(testSources.get(0));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PAUSED) {
            localResultOK = false;
        }
        al.sourcePlay(testSources.get(0));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PLAYING) {
            localResultOK = false;
        }
        al.sourceStop(testSources.get(0));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.STOPPED) {
            localResultOK = false;
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
        al.deleteSources(1, Sys.getDirectBufferAddress(testSources));
    }
    
    protected void fA_VectorStateTransition() {
        boolean localResultOK;

        IntBuffer testSources = createIntBuffer(4);

        IntBuffer sourceState = createIntBuffer(1);        
        
        System.out.print("\nVector State Transition Test. ");
        al.getError();
        localResultOK = true;
        al.genSources(2, Sys.getDirectBufferAddress(testSources));
        al.sourcei(testSources.get(0), AL.BUFFER, buffers.get(0));
        al.sourcei(testSources.get(1), AL.BUFFER, buffers.get(1));
        al.sourcei(testSources.get(0), AL.LOOPING, AL.TRUE);
        al.sourcei(testSources.get(1), AL.LOOPING, AL.TRUE);
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));

        if (sourceState.get(0) != AL.INITIAL) {
            localResultOK = false;
            System.out.print("FAILED -- AL_INITIAL 0");
        }
        
        al.getSourcei(testSources.get(1), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.INITIAL) {
            localResultOK = false;
            System.out.print("FAILED -- AL_INITIAL 1");
        }
        al.sourcePlay(testSources.get(0));
        al.sourcePlay(testSources.get(1));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 0");
        }
        
        al.getSourcei(testSources.get(1), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 1");
        }
        al.sourcePause(testSources.get(0));
        al.sourcePause(testSources.get(1));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PAUSED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PAUSED 0");
        }
        
        al.getSourcei(testSources.get(1), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PAUSED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PAUSED 1");
        }
        al.sourcePlay(testSources.get(0));
        al.sourcePlay(testSources.get(1));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 0A");
        }
        
        al.getSourcei(testSources.get(1), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 1A");
        }
        al.sourceStop(testSources.get(0));
        al.sourceStop(testSources.get(1));
        delay_ms(500);
        
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.STOPPED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_STOPPED 0");
        }
        
        al.getSourcei(testSources.get(1), AL.SOURCE_STATE, Sys.getDirectBufferAddress(sourceState));
        if (sourceState.get(0) != AL.STOPPED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_STOPPED 1");
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
        al.deleteSources(2, Sys.getDirectBufferAddress(testSources));
    }  
    
    protected void fA_GetBufferProperties() {
        IntBuffer data = createIntBuffer(4);
        boolean passNULL;
        
        System.out.print("\nGet Buffer Properties Test. ");
        al.getBufferi(buffers.get(0), AL.FREQUENCY, Sys.getDirectBufferAddress(data));
        al.getBufferi(buffers.get(0), AL.BITS, Sys.getDirectBufferAddress(data)+4);
        al.getBufferi(buffers.get(0), AL.CHANNELS, Sys.getDirectBufferAddress(data)+8);
        al.getBufferi(buffers.get(0), AL.SIZE, Sys.getDirectBufferAddress(data)+12);
        
        passNULL = !(al.isBuffer(0));  // the NULL buffer should cause alIsBuffer to be FALSE
        
        data.rewind();
        
        //       FREQ                         BITS                      CH                         SIZE
        if ((data.get(0) == 44100) && (data.get(1) == 16) && (data.get(2) == 1) && (data.get(3) == 282626) && (passNULL == true)) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
    }
    
    protected void fA_EnumerationValue() {
        boolean result = true;
        int i = 0;
        int getVal;
        
        System.out.print("\nEnumeration Value Test. ");

        while (i < enumerationString.length) {
            getVal = al.getEnumValue(enumerationString[i]);
            if (getVal != enumeration[i]) {
                System.out.print("\n" + enumerationString[i] + " has an invalid enum value.");
                result = false;
            }
            i++;
        }
        
        if(result == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
    }
    
    protected void fA_QueuingUnderrunStates() {
        int error;
        IntBuffer testSources   = createIntBuffer(1); 
        IntBuffer tempInt       = createIntBuffer(1);
        IntBuffer bufferName    = createIntBuffer(1);
        
        FloatBuffer listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f});        
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f});               
        
        boolean localResultOK;
        
        System.out.print("\nQueuing Underrun States Test. ");
        localResultOK = true;
        al.getError();
        al.genSources(1, Sys.getDirectBufferAddress(testSources));
        al.sourcei(testSources.get(0), AL.BUFFER, 0);
        al.sourcei(testSources.get(0), AL.LOOPING, AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("Init error : ", error);
        al.sourceQueueBuffers(testSources.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4));
        if ((error = al.getError()) != AL.NO_ERROR) localResultOK = false;
        al.sourcePlay(testSources.get(0));
        delay_ms(1000);
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(tempInt));
        if (tempInt.get(0) != AL.STOPPED) localResultOK = false;
        al.getSourcei(testSources.get(0), AL.BUFFERS_PROCESSED, Sys.getDirectBufferAddress(tempInt));
        if (tempInt.get(0) != 1) {
            localResultOK = false;
        } else {
            al.sourceUnqueueBuffers(testSources.get(0), tempInt.get(0), Sys.getDirectBufferAddress(bufferName));
        }
        al.sourceQueueBuffers(testSources.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4*1));
        if ((error = al.getError()) != AL.NO_ERROR) localResultOK = false;
        al.sourcePlay(testSources.get(0));
        delay_ms(100);
        al.getSourcei(testSources.get(0), AL.SOURCE_STATE, Sys.getDirectBufferAddress(tempInt));
        if (tempInt.get(0) != AL.PLAYING) localResultOK = false;
        
        // cleanup
        al.sourcei(testSources.get(0), AL.BUFFER, 0);
        al.deleteSources(1, Sys.getDirectBufferAddress(testSources));
        
        // display result
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
    }

    protected void semiAutoTests() {
        sA_StringQueries();  // String Queries Test
        sA_SourceGain();  // Source Gain Test
        //SA_ListenerGain();  // Listener Gain Test
        //SA_Position();  // Position Test
        //SA_SourceRelative();  // Source Relative Test
        //SA_ListenerOrientation();  // Listener Orientation Test
        //SA_SourceCone();  // Source Cone Test
        //SA_MinMaxGain();  // MIN/MAX Gain Test
        //SA_ReferenceDistance();  // Reference Distance Test
        //SA_RolloffFactor();  // Rolloff Factor Test
        //SA_DistanceModel();  // Distance Model Test
        sA_Doppler();  // Doppler Test
        //SA_Frequency();  // Frequency Test
        //SA_Stereo();  // Stereo Test
        //SA_Streaming(); // Streaming Test
        //SA_QueuingUnderrunPerformance(); // test underrun performance
        
        System.out.print("\nDone with this series of tests. Going back to the main menu...");
        delay_ms(1000);
    }
    
    protected int ContinueOrSkip() {
        int ch = -1;
        
        System.out.print("\nPress Return to run this test, or 'S' to skip:\n");
        
        while (true) {
            ch = CRToContinue();
            if ((ch == 'S') || (ch == 's')) {
                return 0;
            }
            if (ch == 0) {
                return 1;
            }
        }
    }  
    
    protected int CRToContinue() {
        int ch = 0;
        int lastchar = 0;
        
        do {
            lastchar = ch;
            try {
                ch = System.in.read();
                System.in.read();
            } catch (IOException ioe) {
            }
        } while (ch != 10);
        
        return lastchar;
    }
    
    protected void CRForNextTest() {
        System.out.print("\nPress Return to continue on to the next test.\n");
        
        CRToContinue();
    }
    
    protected void sA_StringQueries() {
        System.out.print("String Queries Test:");
        if (ContinueOrSkip() == 1) {
            System.out.print("Check that the following values are reasonable:\n");
            
            String tempString;
            
            ALCcontext pContext;
            ALCdevice pDevice;
            pContext = alc.getCurrentContext();
            pDevice = alc.getContextsDevice(pContext);
            tempString = alc.getString(pDevice, ALC.DEVICE_SPECIFIER);
            System.out.print("OpenAL Context Device Specifier is '" + tempString + "'\n");
            tempString = al.getString(AL.RENDERER);
            System.out.print("OpenAL Renderer is '" + tempString + "'\n");
            tempString = al.getString(AL.VERSION);
            System.out.print("OpenAL Version is '" + tempString + "'\n");
            tempString = al.getString(AL.VENDOR);
            System.out.print("OpenAL Vendor is '" + tempString + "'\n");
            tempString = al.getString(AL.EXTENSIONS);
            System.out.print("OpenAL Extensions supported are :\n" + tempString + "\n");
            System.out.print("\nError Codes are :-\n");
            System.out.print("AL_NO_ERROR : '" + al.getString(AL.NO_ERROR) + "'\n");
            
            System.out.print("AL_INVALID_ENUM : '" + al.getString(AL.INVALID_ENUM) + "'\n");
            System.out.print("AL_INVALID_VALUE : '" + al.getString(AL.INVALID_VALUE) + "'\n");
            
            System.out.print("AL_INVALID_OPERATION : '" + al.getString(AL.INVALID_OPERATION) + "'\n");
            System.out.print("AL_OUT_OF_MEMORY : '" + al.getString(AL.OUT_OF_MEMORY) + "'\n");
            CRForNextTest();
        }
    }
    
    protected void sA_SourceGain() {
        IntBuffer testSources = createIntBuffer(1);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[]{0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer	sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[]{1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Source Gain Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            al.genSources(1, Sys.getDirectBufferAddress(testSources));
            al.sourcei(testSources.get(0), AL.BUFFER, buffers.get(1));
            
            System.out.print("The following sound effect will be played at full source gain (Press Return):\n");
            CRToContinue();
            al.sourcef(Sys.getDirectBufferAddress(testSources),AL.GAIN,1.0f);
            al.sourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at half source gain (Press Return):\n");
            CRToContinue();
            al.sourcef(testSources.get(0),AL.GAIN,0.5f);
            al.sourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at quarter source gain (Press Return):\n");
            CRToContinue();
            al.sourcef(testSources.get(0),AL.GAIN,0.25f);
            al.sourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at 1/20th source gain (Press Return):\n");
            CRToContinue();
            al.sourcef(testSources.get(0),AL.GAIN,0.05f);
            al.sourcePlay(testSources.get(0));
            CRForNextTest();
            al.sourcef(testSources.get(0),AL.GAIN,1.0f);
            
            // dispose of sources
            al.sourcei(testSources.get(0), AL.BUFFER, 0);
            al.deleteSources(1, Sys.getDirectBufferAddress(testSources));
        }
    }    
    
    protected void sA_Doppler() {
        IntBuffer testSources = createIntBuffer(2);
        int i;
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Doppler Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            al.genSources(1, Sys.getDirectBufferAddress(testSources));
            al.sourcei(testSources.get(0), AL.BUFFER, buffers.get(1));
            
            System.out.print("Trying Left-to-Right sweep with doppler shift (Press Return):\n");
            CRToContinue();
            al.listenerfv(AL.ORIENTATION, Sys.getDirectBufferAddress(listenerOri));
            al.sourcei(testSources.get(0), AL.LOOPING, AL.TRUE);
            al.source3f(testSources.get(0), AL.POSITION, -100.0f, 0.0f, 0.0f);
            al.source3f(testSources.get(0), AL.VELOCITY, 10.0f, 0.0f, 0.0f);
            al.sourcefv(testSources.get(0), AL.ORIENTATION, Sys.getDirectBufferAddress(sourceOri));
            al.sourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                al.source3f(testSources.get(0), AL.POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            al.sourceStop(testSources.get(0));
            System.out.print("Trying Left-to-Right sweep with DopplerFactor set to 4.0 -- should be more extreme (Press Return):\n");
            CRToContinue();
            al.source3f(testSources.get(0), AL.POSITION, -100.0f, 0.0f, 0.0f);
            al.source3f(testSources.get(0), AL.VELOCITY, 10.0f, 0.0f, 0.0f);
            al.dopplerFactor(4.0f);
            if (al.getFloat(AL.DOPPLER_FACTOR) != 4.0f) { System.out.print(" alGetFloat(AL_DOPPLER_FACTOR) error.\n"); }
            al.sourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                al.source3f(testSources.get(0), AL.POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            al.sourceStop(testSources.get(0));
            al.dopplerFactor(1.0f);
            System.out.print("Trying Left-to-Right sweep with DopplerVelocity set to 86 -- should remain extreme (Press Return):\n");
            CRToContinue();
            al.source3f(testSources.get(0), AL.POSITION, -100.0f, 0.0f, 0.0f);
            al.source3f(testSources.get(0), AL.VELOCITY, 10.0f, 0.0f, 0.0f);
            al.dopplerVelocity(86);
            if (al.getFloat(AL.DOPPLER_VELOCITY) != 86) { System.out.print(" alGetFloat(AL_DOPPLER_VELOCITY) error.\n"); }
            al.sourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                al.source3f(testSources.get(0), AL.POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            al.dopplerVelocity(343);
            al.source3f(testSources.get(0), AL.POSITION, 0.0f, 0.0f, 0.0f);
            al.sourceStop(testSources.get(0));
            CRForNextTest();
            
            // dispose of sources
            al.sourcei(testSources.get(0), AL.BUFFER, 0);
            al.deleteSources(1, Sys.getDirectBufferAddress(testSources));
        }
    }
    
    
    protected void i_PositionTest() {
        int error;
        
        IntBuffer source = createIntBuffer(2);
        int	ch = -1;
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {-2.0f, 0.0f, 2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer source1Pos = createFloatBuffer(3);
        source1Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source1Vel = createFloatBuffer(3);
        source1Vel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        al.genSources(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        }
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source.get(0),AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source.get(0),AL.BUFFER, buffers.get(1));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        }
        
        al.sourcei(source.get(0),AL.LOOPING,AL.TRUE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        }
        
        al.sourcef(source.get(1),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        }
        
        al.sourcef(source.get(1),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source.get(1),AL.POSITION,Sys.getDirectBufferAddress(source1Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source.get(1),AL.VELOCITY,Sys.getDirectBufferAddress(source1Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source.get(1),AL.BUFFER, buffers.get(1));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        }
        
        al.sourcei(source.get(1),AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 1 AL_LOOPING false: \n", error);
        }
        
        System.out.print("Position Test\n");
        System.out.print("Press '1' to play source 0 (looping) rear left of listener\n");
        System.out.print("Press '2' to play source 1 once (single shot) front right of listener\n");
        System.out.print("Press '3' to stop source 0\n");
        System.out.print("Press '4' to stop source 1\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    al.sourcePlay(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    al.sourcePlay(source.get(1));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '3':
                    al.sourceStop(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop source 0 : ", error);
                    break;
                case '4':
                    al.sourceStop(source.get(1));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop source 1 : ", error);
                    break;
            }
        } while (ch != 'Q');
        
        // Release resources
        al.sourceStopv(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        al.deleteSources(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
        
        return;
    }
    
    protected void i_LoopingTest() {
        int	error;
        IntBuffer source = createIntBuffer(2);
        int	ch = -1;
        int bLooping0 = AL.FALSE;
        int bLooping1 = AL.FALSE;
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {-2.0f, 0.0f, -2.0f});

        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        FloatBuffer source1Pos = createFloatBuffer(3);
        source1Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source1Vel = createFloatBuffer(3);
        source1Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        // Clear Error Code
        al.getError();
        
        al.genSources(2,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        }
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source.get(0),AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source.get(0),AL.BUFFER, buffers.get(0));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        }
        
        al.sourcei(source.get(0),AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING false : \n", error);
        }
        
        al.sourcef(source.get(1),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        }
        
        al.sourcef(source.get(1),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source.get(1),AL.POSITION,Sys.getDirectBufferAddress(source1Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source.get(1),AL.VELOCITY,Sys.getDirectBufferAddress(source1Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source.get(1),AL.BUFFER, buffers.get(1));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        }
        
        al.sourcei(source.get(1),AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 1 AL_LOOPING false: \n", error);
        }
        
        System.out.print("Looping Test\n");
        System.out.print("Press '1' to play source 0 once (single shot)\n");
        System.out.print("Press '2' to toggle looping on source 0\n");
        System.out.print("Press '3' to play source 1 once (single shot)\n");
        System.out.print("Press '4' to toggle looping on source 1\n");
        System.out.print("Press 'Q' to quit\n");
        System.out.print("\nSource 0 : Not looping Source 1 : Not looping\n");
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    al.sourcePlay(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    if (bLooping0 == AL.FALSE) {
                        bLooping0 = AL.TRUE;
                        if (bLooping1 == AL.TRUE)
                            System.out.print("Source 0 : Looping     Source 1 : Looping    \n");
                        else
                            System.out.print("Source 0 : Looping     Source 1 : Not looping\n");
                    }
                    else {
                        bLooping0 = AL.FALSE;
                        if (bLooping1 == AL.TRUE)
                            System.out.print("Source 0 : Not looping Source 1 : Looping    \n");
                        else
                            System.out.print("Source 0 : Not looping Source 1 : Not looping\n");
                    }
                    al.sourcei(source.get(0), AL.LOOPING, bLooping0);
                    break;
                case '3':
                    al.sourcePlay(source.get(1));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '4':
                    if (bLooping1 == AL.FALSE) {
                        bLooping1 = AL.TRUE;
                        if (bLooping0 == AL.TRUE)
                            System.out.print("Source 0 : Looping     Source 1 : Looping    \n");
                        else
                            System.out.print("Source 0 : Not looping Source 1 : Looping    \n");
                    }
                    else {
                        bLooping1 = AL.FALSE;
                        if (bLooping0 == AL.TRUE)
                            System.out.print("Source 0 : Looping     Source 1 : Not looping\n");
                        else
                            System.out.print("Source 0 : Not looping Source 1 : Not looping\n");
                    }
                    al.sourcei(source.get(1), AL.LOOPING, bLooping1);
                    break;
            }
        } while (ch != 'Q');
        
        System.out.print("\n");
        
        // Release resources
        al.sourceStop(source.get(0));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStop source 1 : ", error);
        
        al.deleteSources(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 1 : ", error);
        
        return;
    }
    
    protected void i_EAXTest() {
        
        int	error;
        int	ch = -1;
        IntBuffer source = createIntBuffer(2);
        
        IntBuffer Env           = createIntBuffer(1);
        IntBuffer Room          = createIntBuffer(1);
        IntBuffer Occlusion     = createIntBuffer(1);
        IntBuffer Obstruction   = createIntBuffer(1);
        EAXBufferProperties eaxBufferProp0 = new EAXBufferProperties();
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {-2.0f, 0.0f, 2.0f});
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer source1Pos = createFloatBuffer(3);
        source1Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        FloatBuffer source1Vel = createFloatBuffer(3);
        source1Vel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        EAX eax = new EAX();
        try {
            eax.create();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        // Clear Error Code
        al.getError();
        
        al.genSources(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        al.sourcefv(source.get(0),AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        al.sourcei(source.get(0),AL.BUFFER, buffers.get(0));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        
        al.sourcei(source.get(0),AL.LOOPING,AL.TRUE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        
        al.sourcef(source.get(1),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        
        al.sourcef(source.get(1),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        
        al.sourcefv(source.get(1),AL.POSITION,Sys.getDirectBufferAddress(source1Pos));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        
        al.sourcefv(source.get(1),AL.VELOCITY,Sys.getDirectBufferAddress(source1Vel));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        
        al.sourcei(source.get(1),AL.BUFFER, buffers.get(1));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        
        al.sourcei(source.get(1),AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 1 AL_LOOPING false: \n", error);
        
        System.out.print("EAX Test\n\n");
        System.out.print("Press '1' to play source 0 (looping)\n");
        System.out.print("Press '2' to play source 1 once (single shot)\n");
        System.out.print("Press '3' to stop source 0\n");
        System.out.print("Press '4' to stop source 1\n");
        System.out.print("Press '5' to add Hangar reverb (DEFERRED)\n");
        System.out.print("Press '6' to remove reverb (DEFERRED)\n");
        System.out.print("Press '7' to occlude source 0 (DEFERRED)\n");
        System.out.print("Press '8' to remove occlusion from source 0 (DEFERRED)\n");
        System.out.print("Press '9' to obstruct source 1 (IMMEDIATE)\n");
        System.out.print("Press '0' to remove obstruction from source 1 (IMMEDIATE)\n");
        System.out.print("Press 'c' to COMMIT EAX settings\n");
        System.out.print("Press 'q' to quit\n\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            switch (ch) {
                case '1':
                    al.sourcePlay(source.get(0));
                    break;
                case '2':
                    al.sourcePlay(source.get(1));
                    break;
                case '3':
                    al.sourceStop(source.get(0));
                    break;
                case '4':
                    al.sourceStop(source.get(1));
                    break;
                case '5':
                    Env.put(0, EAX.ENVIRONMENT_HANGAR);
                    eax.eaxSet(EAX.LISTENER_GUID, EAXListenerProperties.ENVIRONMENT | EAXListenerProperties.DEFERRED,
                    	0, Sys.getDirectBufferAddress(Env), 4);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXLISTENER_ENVIRONMENT | EAXLISTENER_DEFERRED : \n", error);
                    break;
                    
                case '6':
                    Room.put(-10000);
                    eax.eaxSet(EAX.LISTENER_GUID, EAXListenerProperties.ROOM | EAXListenerProperties.DEFERRED, 0,
                    	Sys.getDirectBufferAddress(Room), 4);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXLISTENER_ROOM | EAXLISTENER_DEFERRED : \n", error);
                    break;
                    
                case '7':
                    eax.eaxGet(EAX.BUFFER_GUID, EAXBufferProperties.ALLPARAMETERS, source.get(0),
                        eaxBufferProp0.getAddress(), eaxBufferProp0.getSize());
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxGet EAXBUFFER_ALLPARAMETERS : \n", error);
                    eaxBufferProp0.setOcclusion(-5000);
                    eax.eaxSet(EAX.BUFFER_GUID, EAXBufferProperties.ALLPARAMETERS | EAXBufferProperties.DEFERRED, source.get(0),
                    	eaxBufferProp0.getAddress(), eaxBufferProp0.getSize());
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_ALLPARAMETERS | EAXBUFFER_DEFERRED : \n", error);
                    break;
                    
                case '8':
                    Occlusion.put(0, 0);
                    eax.eaxSet(EAX.BUFFER_GUID, EAXBufferProperties.OCCLUSION | EAXBufferProperties.DEFERRED, source.get(0),
                    	Sys.getDirectBufferAddress(Occlusion), 4);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_OCCLUSION | EAXBUFFER_DEFERRED : \n", error);
                    break;
                    
                case '9':
                    Obstruction.put(0, -5000);
                    eax.eaxSet(EAX.BUFFER_GUID, EAXBufferProperties.OBSTRUCTION, source.get(1),
                    	Sys.getDirectBufferAddress(Obstruction), 4);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_OBSTRUCTION : \n", error);
                    break;
                    
                case '0':
                    Obstruction.put(0, 0);
                    eax.eaxSet(EAX.BUFFER_GUID, EAXBufferProperties.OBSTRUCTION, source.get(1),
                    	Sys.getDirectBufferAddress(Obstruction), 4);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_OBSTRUCTION : \n", error);
                    break;
                    
                case 'C':
                    // Commit settings on source 0
                    eax.eaxSet(EAX.BUFFER_GUID, EAXBufferProperties.COMMITDEFERREDSETTINGS,
                    	source.get(0), 0, 0);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_COMMITDEFERREDSETTINGS : \n", error);
                    
                    // Commit Listener settings
                    eax.eaxSet(EAX.LISTENER_GUID, EAXListenerProperties.COMMITDEFERREDSETTINGS,
                    	0, 0, 0);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("eaxSet EAXLISTENER_COMMITDEFERREDSETTINGSENVIRONMENT : \n", error);
                    break;
            }
        } while (ch != 'Q');
        
        // reset EAX level
        Room.put(0, -10000);
        eax.eaxSet(EAX.LISTENER_GUID, EAXListenerProperties.ROOM, 0, Sys.getDirectBufferAddress(Room), 4);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("eaxSet EAXLISTENER_ROOM : \n", error);
        
        // Release resources
        al.sourceStopv(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        al.deleteSources(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
    }
    
    protected void i_QueueTest() {
        int     error;
        int     ch = -1;
        int bLooping;
        IntBuffer source = createIntBuffer(1);
        IntBuffer tbuffers = createIntBuffer(5);
        IntBuffer buffersremoved = createIntBuffer(5);
        IntBuffer BuffersInQueue = createIntBuffer(1);
        
        IntBuffer BuffersProcessed = createIntBuffer(1);
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {0.0f, 0.0f, -2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        IntBuffer Buffer = createIntBuffer(1);
        int	i;
        
        // Clear Error Code
        al.getError();
        
        al.genSources(1,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : ", error);
        }
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : ", error);
        }
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : ", error);
        }
        
        al.sourcefv(source.get(0),AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : ", error);
        }
        
        al.sourcei(source.get(0),AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING false: ", error);
        }
        
        bLooping = AL.FALSE;
        
        System.out.print("Queue Test\n\n");
        System.out.print("Press '1' to start playing source 0\n");
        System.out.print("Press '2' to stop source 0\n");
        System.out.print("Press '3' to toggle looping on source 0\n");
        System.out.print("Press '4' to queue 4 buffers on source 0\n");
        System.out.print("Press '5' to queue 1st buffer on source 0\n");
        System.out.print("Press '6' to queue 2nd buffer on source 0\n");
        System.out.print("Press '7' to queue 3rd buffer on source 0\n");
        System.out.print("Press '8' to queue 4th buffer on source 0\n");
        System.out.print("Press '9' to queue 5th buffer (Buffer 0) on source 0\n");
        System.out.print("Press '0' to display stats\n");
        
        System.out.print("Press 'A' to unqueue first Buffer\n");
        System.out.print("Press 'B' to unqueue first 2 Buffers\n");
        System.out.print("Press 'C' to unqueue first 3 Buffers\n");
        System.out.print("Press 'D' to unqueue first 4 Buffers\n");
        System.out.print("Press 'E' to unqueue first 5 Buffers\n");
        System.out.print("Press 'F' to unqueue all buffers\n");
        
        System.out.print("Press 'Q' to quit\n");
        
        System.out.print("Source 0 not looping\n");
        
        tbuffers.put(0, buffers.get(2));
        tbuffers.put(1, buffers.get(3));
        tbuffers.put(2, buffers.get(4));
        tbuffers.put(3, buffers.get(5));
        tbuffers.put(4, 0);
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            switch (ch) {
            case '1':
                al.sourcePlay(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcePlay source 0 : ", error);
                break;
            case '2':
                al.sourceStop(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
                break;
            case '3':
                if (bLooping == AL.TRUE)
                {
                    bLooping = AL.FALSE;
                    System.out.print("Source 0 not looping\n");
                }
                else
                {
                    bLooping = AL.TRUE;
                    System.out.print("Source 0 looping    \n");
                }
                al.sourcei(source.get(0), AL.LOOPING, bLooping);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei AL_LOOPING : ", error);
                break;
            case '4':
                al.sourceQueueBuffers(source.get(0), 4, Sys.getDirectBufferAddress(buffers));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 4 : ", error);
                break;
            case '5':
                al.sourceQueueBuffers(source.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4*0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '6':
                al.sourceQueueBuffers(source.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4*1));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '7':
                al.sourceQueueBuffers(source.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4*2));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '8':
                al.sourceQueueBuffers(source.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4*3));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '9':
                // Queue buffer 0
                al.sourceQueueBuffers(source.get(0), 1, Sys.getDirectBufferAddress(buffers) + (4*4));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 (buffer 0) : ", error);
                break;
            case 'A':
                // Unqueue first Buffer
                al.sourceUnqueueBuffers(source.get(0), 1, Sys.getDirectBufferAddress(buffersremoved));
                          
                if ((error = al.getError()) != AL.NO_ERROR)
                {
                    displayALError("alSourceUnqueueBuffers 1 : ", error);
                } else
                {
                    if (buffersremoved.get(0) == buffers.get(0))
                        buffersremoved.put(0, 1);
                    if (buffersremoved.get(0) == buffers.get(1))
                        buffersremoved.put(0, 2);
                    if (buffersremoved.get(0) == buffers.get(2))
                        buffersremoved.put(0, 3);
                    if (buffersremoved.get(0) == buffers.get(3))
                        buffersremoved.put(0, 4);
                    else
                        buffersremoved.put(0, 0);
                          
                    System.out.print("\nRemoved Buffer " + buffersremoved.get(0) + " from queue\n");
                }
                break;
            case 'B':
                // Unqueue first 2 Buffers
                al.sourceUnqueueBuffers(source.get(0), 2, Sys.getDirectBufferAddress(buffersremoved));
                          
                if ((error = al.getError()) != AL.NO_ERROR)
                {
                    displayALError("alSourceUnqueueBuffers 2 : ", error);
                } else
                {
                    for (i = 0; i < 2; i++)
                    {
                        if (buffersremoved.get(i) == buffers.get(0))
                            buffersremoved.put(i, 1);
                        else if (buffersremoved.get(i) == buffers.get(1))
                            buffersremoved.put(i, 2);
                        else if (buffersremoved.get(i) == buffers.get(2))
                            buffersremoved.put(i, 3);
                        else if (buffersremoved.get(i) == buffers.get(3))
                            buffersremoved.put(i, 4);
                        else
                            buffersremoved.put(i, 0);
                    }
                          
                    System.out.print("\nRemoved Buffers " + buffersremoved.get(0) + " and " + buffersremoved.get(1) + " from queue\n");
                }
                break;
            case 'C':
                // Unqueue first 3 Buffers
                al.sourceUnqueueBuffers(source.get(0), 3, Sys.getDirectBufferAddress(buffersremoved));
                if ((error = al.getError()) != AL.NO_ERROR)
                {
                    displayALError("alSourceUnqueueBuffers 3 : ", error);
                } else
                {
                    for (i = 0; i < 3; i++)
                    {
                        if (buffersremoved.get(i) == buffers.get(0))
                            buffersremoved.put(i, 1);
                        else if (buffersremoved.get(i) == buffers.get(1))
                            buffersremoved.put(i, 2);
                        else if (buffersremoved.get(i) == buffers.get(2))
                            buffersremoved.put(i, 3);
                        else if (buffersremoved.get(i) == buffers.get(3))
                            buffersremoved.put(i, 4);
                        else
                            buffersremoved.put(i, 0);
                    }
                          
                    System.out.print("\nRemoved Buffers " + buffersremoved.get(0) + 
                                     ", " + buffersremoved.get(1) + " and " +
                                     buffersremoved.get(2) +" from queue\n");
                }
                break;
            case 'D':
                // Unqueue first 4 Buffers
                al.sourceUnqueueBuffers(source.get(0), 4, Sys.getDirectBufferAddress(buffersremoved));
                          
                if ((error = al.getError()) != AL.NO_ERROR)
                {
                    displayALError("alSourceUnqueueBuffers 1 : ", error);
                } else
                {
                    for (i = 0; i < 4; i++)
                    {
                        if (buffersremoved.get(i) == buffers.get(0))
                            buffersremoved.put(i, 1);
                        else if (buffersremoved.get(i) == buffers.get(1))
                            buffersremoved.put(i, 2);
                        else if (buffersremoved.get(i) == buffers.get(2))
                            buffersremoved.put(i, 3);
                        else if (buffersremoved.get(i) == buffers.get(3))
                            buffersremoved.put(i, 4);
                        else
                            buffersremoved.put(i, 0);
                    }
                          
                    System.out.print("\nRemoved Buffers " + buffersremoved.get(0) + 
                                     ", " + buffersremoved.get(1) + 
                                     ", " + buffersremoved.get(2) + 
                                     "and " + buffersremoved.get(3) + 
                                     " from queue\n");
                }
                break;
            case 'E':
                // Unqueue first 5 Buffers
                al.sourceUnqueueBuffers(source.get(0), 5, Sys.getDirectBufferAddress(buffersremoved));
                          
                if ((error = al.getError()) != AL.NO_ERROR)
                {
                    displayALError("alSourceUnqueueBuffers 1 : ", error);
                } else
                {
                    for (i = 0; i < 5; i++)
                    {
                        if (buffersremoved.get(i) == buffers.get(0))
                            buffersremoved.put(i, 1);
                        else if (buffersremoved.get(i) == buffers.get(1))
                            buffersremoved.put(i, 2);
                        else if (buffersremoved.get(i) == buffers.get(2))
                            buffersremoved.put(i, 3);
                        else if (buffersremoved.get(i) == buffers.get(3))
                            buffersremoved.put(i, 4);
                        else
                            buffersremoved.put(i, 0);
                    }
                          
                    System.out.print("\nRemoved Buffers " + buffersremoved.get(0) + 
                                     ", " + buffersremoved.get(1) + 
                                     ", " + buffersremoved.get(2) + 
                                     ", " + buffersremoved.get(3) + 
                                     "and " + buffersremoved.get(4) + 
                                     " from queue\n");
                }
                break;
            case 'F':
                al.sourcei(source.get(0), AL.BUFFER, 0);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSource AL_BUFFER NULL : ", error);
                break;
            case '0':
                // Retrieve number of buffers in queue
                al.getSourcei(source.get(0), AL.BUFFERS_QUEUED, Sys.getDirectBufferAddress(BuffersInQueue));
                
                // Retrieve number of processed buffers
                al.getSourcei(source.get(0), AL.BUFFERS_PROCESSED, Sys.getDirectBufferAddress(BuffersProcessed));
                
                // Retrieve current buffer
                al.getSourcei(source.get(0), AL.BUFFER, Sys.getDirectBufferAddress(Buffer));
                
                int address = Buffer.get(0);
                if (address == buffers.get(0))
                    address = 1;
                else if (address == buffers.get(1))
                    address = 2;
                else if (address == buffers.get(2))
                    address = 3;
                else if (address == buffers.get(3))
                    address = 4;
                else
                    address = 0;
                          
                System.out.print("Current Buffer is " + address + ", " + BuffersInQueue.get(0) + " Buffers in queue, " + BuffersProcessed.get(0) + " Processed\n");
                Buffer.clear();
                BuffersProcessed.clear();
                BuffersInQueue.clear();                          
                break;
            }
        } while (ch != 'Q');
        
        // Release resources
        al.sourceStop(source.get(0));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStop : ", error);
        
        al.deleteSources(1, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 1 : ", error);
    }
    
    protected void i_BufferTest() {
        IntBuffer source = createIntBuffer(1);
        int	error;
        int ch = -1;
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        al.genSources(1,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        al.sourcefv(source.get(0),AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        al.sourcei(source.get(0),AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        System.out.print("Buffer Test\n");
        System.out.print("Press '1' to play buffer 0 on source 0\n");
        System.out.print("Press '2' to play buffer 1 on source 0\n");
        System.out.print("Press '3' to stop source 0\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    // Stop source
                    al.sourceStop(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop 0 : ", error);
                    // Attach buffer 0 to source
                    al.sourcei(source.get(0), AL.BUFFER, buffers.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcei AL_BUFFER 0 : ", error);
                    // Play
                    al.sourcePlay(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay 0 : ", error);
                    break;
                case '2':
                    // Stop source
                    al.sourceStop(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop 0 : ", error);
                    // Attach buffer 0 to source
                    al.sourcei(source.get(0), AL.BUFFER, buffers.get(1));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcei AL_BUFFER 1 : ", error);
                    // Play
                    al.sourcePlay(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay 0 : ", error);
                    break;
                case '3':
                    // Stop source
                    al.sourceStop(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop 0 : ", error);
                    break;
            }
        } while (ch != 'Q');
        
        // Release resources
        al.sourceStopv(1, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStopv 1 : ", error);
        
        al.deleteSources(1, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 1 : ", error);
    }
    
    protected void i_FreqTest() {
        int	error;
        IntBuffer source = createIntBuffer(1);
        int	ch = -1;

        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        al.genSources(1,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        al.sourcefv(source.get(0),AL.VELOCITY, Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        al.sourcei(source.get(0),AL.BUFFER, buffers.get(1));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_BUFFER buffer 1 : \n", error);
        
        al.sourcei(source.get(0),AL.LOOPING,AL.TRUE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        System.out.print("Frequency Test\n");
        System.out.print("Press '1' to play source 0 (looping)\n");
        System.out.print("Press '2' to Double Frequency\n");
        System.out.print("Press '3' to Halve Frequency\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    al.sourcef(source.get(0), AL.PITCH, 1.0f);
                    al.sourcePlay(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    al.sourcef(source.get(0), AL.PITCH, 2.0f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcef source 0 AL_PITCH 2.0 : ", error);
                    break;
                case '3':
                    al.sourcef(source.get(0), AL.PITCH, 0.5f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcef source 0 AL PITCH 0.5: ", error);
                    break;
            }
        } while (ch != 'Q');
        
        // Release resources
        al.sourceStopv(1, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        al.deleteSources(1, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
    }
    
    protected void i_StereoTest() {
        int	error;
        IntBuffer source = createIntBuffer(1);
        IntBuffer tbuffers = createIntBuffer(2);
        IntBuffer BuffersInQueue = createIntBuffer(1);
        IntBuffer BuffersProcessed = createIntBuffer(1);
        IntBuffer Buffer = createIntBuffer(1);

        int	ch = -1;
        int bLoop = AL.TRUE;

        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        al.genSources(1,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        tbuffers.put(0, buffers.get(6));
        tbuffers.put(1, buffers.get(6));
        
        System.out.print("Stereo Test\n");
        System.out.print("Press '1' to play a stereo buffer on source 0 (looping)\n");
        System.out.print("Press '2' to play a mono buffer on source 0 (looping)\n");
        System.out.print("Press '3' to stop source 0\n");
        System.out.print("Press '4' to queue 2 stereo buffers on source 0 and start playing\n");
        System.out.print("Press '5' to unqueue the 2 stereo buffers on source 0\n");
        System.out.print("Press '6' to toggle looping on / off\n");
        System.out.print("Press '0' to display stats\n");
        System.out.print("Press 'Q' to quit\n");
        System.out.print("Looping is on\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
            case '1':
                // Stop source
                al.sourceStop(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
             
                // Attach new buffer
                al.sourcei(source.get(0),AL.BUFFER, buffers.get(6));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei 0 AL_BUFFER buffer 6 (stereo) : \n", error);
             
                // Set volume
                al.sourcef(source.get(0),AL.GAIN,0.5f);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcef 0 AL_GAIN : \n", error);
             
                // Set looping
                al.sourcei(source.get(0),AL.LOOPING,bLoop);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING true: \n", error);
             
                // Play source
                al.sourcePlay(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcePlay source 0 : ", error);
             
                break;
            case '2':
                // Stop source
                al.sourceStop(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
             
                // Attach new buffer
                al.sourcei(source.get(0),AL.BUFFER, buffers.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei 0 AL_BUFFER buffer 0 (mono) : \n", error);
             
                // Set 3D position
                al.sourcefv(source.get(0),AL.POSITION, Sys.getDirectBufferAddress(source0Pos));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcefv 0 AL_POSITION : \n", error);
             
                // Set 3D velocity
                al.sourcefv(source.get(0),AL.VELOCITY, Sys.getDirectBufferAddress(source0Vel));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
             
                // Set volume to full
                al.sourcef(source.get(0),AL.GAIN,1.0f);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcef 0 AL_GAIN : \n", error);
             
                // Set Looping
                al.sourcei(source.get(0),AL.LOOPING,bLoop);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING : \n", error);
             
                // Play source
                al.sourcePlay(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcePlay source 0 : ", error);
                break;
            case '3':
                al.sourceStop(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
                break;
            case '4':
                al.sourceStop(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceStop Source 0 : ", error);
             
                // Attach NULL buffer to source to clear everything
                al.sourcei(source.get(0), AL.BUFFER, 0);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei AL_BUFFER (NULL) : ", error);
             
                al.sourceQueueBuffers(source.get(0), 2, Sys.getDirectBufferAddress(buffers));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceQueueBuffers 2 (stereo) : ", error);
             
                // Set Looping
                al.sourcei(source.get(0),AL.LOOPING,bLoop);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING : \n", error);
             
                al.sourcePlay(source.get(0));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcePlay Source 0 : ", error);
                break;
            case '5':
                //yes, this causes a invalid operation - so does the original :/
                al.sourceUnqueueBuffers(source.get(0), 2, Sys.getDirectBufferAddress(buffers));
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourceUnqueueBuffers 2 (stereo) : ", error);
                break;
            case '6':
                if (bLoop == AL.TRUE)
                {
                    System.out.print("Looping is off\n");
                    bLoop = AL.FALSE;
                }
                else
                {
                    System.out.print("Looping is on  \n");
                    bLoop = AL.TRUE;
                }
                al.sourcei(source.get(0), AL.LOOPING, bLoop);
                if ((error = al.getError()) != AL.NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING : \n", error);
                break;
            case '0':
                        // Retrieve number of buffers in queue
                al.getSourcei(source.get(0), AL.BUFFERS_QUEUED, Sys.getDirectBufferAddress(BuffersInQueue));
                // Retrieve number of processed buffers
                al.getSourcei(source.get(0), AL.BUFFERS_PROCESSED, Sys.getDirectBufferAddress(BuffersProcessed));
                // Retrieve current buffer
                al.getSourcei(source.get(0), AL.BUFFER, Sys.getDirectBufferAddress(Buffer));

                int address = Buffer.get(0);
                if (address == buffers.get(0))
                    address = 6;
                else if (address == buffers.get(0))
                    address = 6;
                else
                    address = 0;
             
                System.out.print("Current Buffer is " + address + ", " + BuffersInQueue.get(0) + " Buffers in queue, " + BuffersProcessed.get(0) + " Processed\n");
                Buffer.clear();
                BuffersProcessed.clear();
                BuffersInQueue.clear();
             
                break;
            }
        } while (ch != 'Q');
        
        // Release resources
        al.sourceStop(source.get(0));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStop : ", error);
        
        al.deleteSources(1, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
    }
    
    protected void i_GainTest() {
        int     error;
        int     ch = -1;

        IntBuffer source = createIntBuffer(2);
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});   
        
        FloatBuffer source1Pos = createFloatBuffer(3);
        source1Pos.put(new float[] {-2.0f, 0.0f, -2.0f});
        
        FloatBuffer source1Vel = createFloatBuffer(3);
        source1Vel.put(new float[] {0.0f, 0.0f, 0.0f});           
        
        al.genSources(2,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        al.sourcef(source.get(0),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        al.sourcef(source.get(0),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        al.sourcefv(source.get(0),AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        al.sourcefv(source.get(0),AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        al.sourcei(source.get(0),AL.BUFFER, buffers.get(0));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        
        al.sourcei(source.get(0),AL.LOOPING,AL.TRUE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        
        al.sourcef(source.get(1),AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        
        al.sourcef(source.get(1),AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        
        al.sourcefv(source.get(1),AL.POSITION,Sys.getDirectBufferAddress(source1Pos));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        
        al.sourcefv(source.get(1),AL.VELOCITY,Sys.getDirectBufferAddress(source1Vel));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        
        al.sourcei(source.get(1),AL.BUFFER, buffers.get(1));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        
        al.sourcei(source.get(1),AL.LOOPING,AL.TRUE);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourcei 1 AL_LOOPING true: \n", error);
        
        System.out.print("Gain Test\n");
        System.out.print("Press '1' to play source 0 (looping)\n");
        System.out.print("Press '2' to play source 1 (looping)\n");
        System.out.print("Press '3' to stop source 0\n");
        System.out.print("Press '4' to stop source 1\n");
        System.out.print("Press '5' to set source 0 gain to 1.0\n");
        System.out.print("Press '6' to set source 0 gain to 0.5\n");
        System.out.print("Press '7' to set source 0 gain to 0.25\n");
        System.out.print("Press '8' to set source 0 gain to 0\n");
        System.out.print("Press 'A' to set Listener Gain to 1.0\n");
        System.out.print("Press 'B' to set Listener Gain to 0.5\n");
        System.out.print("Press 'C' to set Listener Gain to 0.25\n");
        System.out.print("Press 'D' to set Listener Gain to 0.0\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    al.sourcePlay(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    al.sourcePlay(source.get(1));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '3':
                    al.sourceStop(source.get(0));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop source 0 : \n", error);
                    break;
                case '4':
                    al.sourceStop(source.get(1));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop source 1 : \n", error);
                    break;
                case '5':
                    al.sourcef(source.get(0),AL.GAIN,1.0f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 1.0 : \n", error);
                    break;
                case '6':
                    al.sourcef(source.get(0),AL.GAIN,0.5f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 0.5 : \n", error);
                    break;
                case '7':
                    al.sourcef(source.get(0),AL.GAIN,0.25f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 0.25 : \n", error);
                    break;
                case '8':
                    al.sourcef(source.get(0),AL.GAIN,0.0f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 0.0 : \n", error);
                    break;
                case 'A':
                    al.listenerf(AL.GAIN,1.0f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alListenerf AL_GAIN 1.0 : \n", error);
                    break;
                case 'B':
                    al.listenerf(AL.GAIN,0.5f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alListenerf AL_GAIN 0.5 : \n", error);
                    break;
                case 'C':
                    al.listenerf(AL.GAIN,0.25f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alListenerf AL_GAIN 0.25 : \n", error);
                    break;
                case 'D':
                    al.listenerf(AL.GAIN,0.0f);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alListenerf AL_GAIN 0.0 : \n", error);
                    break;
            }
        } while (ch != 'Q');
        
        // Reset & Release resources
        al.listenerf(AL.GAIN,1.0f);
        al.sourceStopv(2,Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStop : ", error);
        
        al.deleteSources(2, Sys.getDirectBufferAddress(source));
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
    }
    
    protected void i_StreamingTest() {
        System.out.println("i_StreamingTest");
        delay_ms(3000);
    }
    
    protected void i_MultipleSourcesTest() {
        int	numSources = 0;
        IntBuffer Sources = createIntBuffer(64);
        int	error;
        int	i;
        int	ch = -1;
        float radius;
        double anglestep;
        FloatBuffer pos = createFloatBuffer(3);
        
        // Generate as many sources as possible (up to 64)
        for (i = 0; i < 64; i++) {
            al.genSources(1, Sys.getDirectBufferAddress(Sources) + (4*i));
            if ((error = al.getError()) != AL.NO_ERROR) {
                break;
            } else {
                numSources++;
            }            
        }
        
        System.out.print("Multiple Sources Test\n\n");
        System.out.print("Generated " + numSources + " Sources\n");
        
        // Set sources to located in a circle around the listener
        
        anglestep = (2 * 3.1416) / (float)numSources;
        radius = 2.0f;
        
        for (i = 0; i < numSources; i++) {
            // Attach buffer
            al.sourcei(Sources.get(i), AL.BUFFER, buffers.get(0));
            
            // Set position
            pos.put(0, (float)(Math.cos(anglestep*i) * radius));
            pos.put(1, 0.0f);
            pos.put(2, (float)(Math.sin(anglestep*i) * radius));
            
            al.sourcefv(Sources.get(i), AL.POSITION, Sys.getDirectBufferAddress(pos));
            
            System.out.print("Source " + i + " at " + 
                            pos.get(0) + ", " +
                            pos.get(1) + ", " +
                            pos.get(2) + "\n");
            
            // Enable looping
            al.sourcei(Sources.get(i), AL.LOOPING, AL.TRUE);
            
            pos.clear();
        }
        
        
        System.out.print("Press '1' to start playing Sources seperately\n");
        System.out.print("Press '2' to stop playing Sources seperately\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
                ch = System.in.read();
            } catch (IOException ioe) {
            }
            switch (ch) {
                case '1':
                    for (i = 0; i < numSources; i++) {
                        al.sourcePlay(Sources.get(i));
                        if ((error = al.getError()) != AL.NO_ERROR)
                            displayALError("alSourcePlay : ", error);
                        
                        // Delay a little
                        delay_ms(100);
                    }
                    break;
                case '2':
                    al.sourceStopv(numSources, Sys.getDirectBufferAddress(Sources));
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStopv : ", error);
                    break;
            }
        } while (ch != 'Q');
        
        // Delete the Sources
        al.deleteSources(numSources, Sys.getDirectBufferAddress(Sources));
    }
    
    /**
     * main entry point
     *
     * @param args String array containing arguments
     */
    public static void main(String[] args) {
        ALTest alTest = new ALTest();
        alTest.execute(args);
    }
}