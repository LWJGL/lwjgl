/*
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
package org.lwjgl.test.openal;

import org.lwjgl.openal.AL;
import org.lwjgl.openal.ALC;
import org.lwjgl.openal.eax.EAX;
import org.lwjgl.openal.eax.EAXBufferProperties;
import org.lwjgl.openal.eax.EAXListenerProperties;

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
        AL.AL_INVALID,
        AL.AL_INVALID,
        ALC.ALC_INVALID,
        AL.AL_NONE,
        AL.AL_FALSE,
        ALC.ALC_FALSE,
        AL.AL_TRUE,
        ALC.ALC_TRUE,
        AL.AL_SOURCE_RELATIVE,
        AL.AL_CONE_INNER_ANGLE,
        AL.AL_CONE_OUTER_ANGLE,
        AL.AL_PITCH,
        AL.AL_POSITION,
        AL.AL_DIRECTION,
        AL.AL_VELOCITY,
        AL.AL_LOOPING,
        AL.AL_BUFFER,
        AL.AL_GAIN,
        AL.AL_MIN_GAIN,
        AL.AL_MAX_GAIN,
        AL.AL_ORIENTATION,
        AL.AL_REFERENCE_DISTANCE,
        AL.AL_ROLLOFF_FACTOR,
        AL.AL_CONE_OUTER_GAIN,
        AL.AL_MAX_DISTANCE,
        AL.AL_SOURCE_STATE,
        AL.AL_INITIAL,
        AL.AL_PLAYING,
        AL.AL_PAUSED,
        AL.AL_STOPPED,
        AL.AL_BUFFERS_QUEUED,
        AL.AL_BUFFERS_PROCESSED,
        AL.AL_FORMAT_MONO8,
        AL.AL_FORMAT_MONO16,
        AL.AL_FORMAT_STEREO8,
        AL.AL_FORMAT_STEREO16,
        AL.AL_FREQUENCY,
        AL.AL_SIZE,
        AL.AL_UNUSED,
        AL.AL_PENDING,
        AL.AL_PROCESSED,
        ALC.ALC_MAJOR_VERSION,
        ALC.ALC_MINOR_VERSION,
        ALC.ALC_ATTRIBUTES_SIZE,
        ALC.ALC_ALL_ATTRIBUTES,
        ALC.ALC_DEFAULT_DEVICE_SPECIFIER,
        ALC.ALC_DEVICE_SPECIFIER,
        ALC.ALC_EXTENSIONS,
        ALC.ALC_FREQUENCY,
        ALC.ALC_REFRESH,
        ALC.ALC_SYNC,
        AL.AL_NO_ERROR,
        AL.AL_INVALID_NAME,
        AL.AL_INVALID_ENUM,
        AL.AL_INVALID_VALUE,
        AL.AL_INVALID_OPERATION,
        AL.AL_OUT_OF_MEMORY,
        ALC.ALC_NO_ERROR,
        ALC.ALC_INVALID_DEVICE,
        ALC.ALC_INVALID_CONTEXT,
        ALC.ALC_INVALID_ENUM,
        ALC.ALC_INVALID_VALUE,
        ALC.ALC_OUT_OF_MEMORY,
        AL.AL_VENDOR,
        AL.AL_VERSION,
        AL.AL_RENDERER,
        AL.AL_EXTENSIONS,
        AL.AL_DOPPLER_FACTOR,
        AL.AL_DOPPLER_VELOCITY,
        AL.AL_DISTANCE_MODEL,
        AL.AL_INVERSE_DISTANCE,
        AL.AL_INVERSE_DISTANCE_CLAMPED
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
   * Creates a byte buffer to hold specified bytes
   * - strictly a utility method
   *
   * @param size how many bytes to contain
   * @return created ByteBuffer
   */
  protected ByteBuffer createByteBuffer(int size) {
      ByteBuffer temp = ByteBuffer.allocateDirect(4*size);
      temp.order(ByteOrder.nativeOrder());
        
      return temp;
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
        System.out.println(text + " - " + AL.alGetString(errorcode));
    }
    
    /**
     * Runs the actual test, using supplied arguments
     */
    protected void execute(String[] args) {
        String szEAX = "EAX";
        String szFnName;
        int	ch = -1;
        int error;
        
        FloatBuffer listenerPos = createFloatBuffer(3);
        listenerPos.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer listenerVel = createFloatBuffer(3);
        listenerVel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f, 0.0f, -1.0f, 0.0f, 1.0f, 0.0f});
        
        System.out.print("OpenAL Test Application (Java!)\n");
        System.out.print("=======================\n\n");
        
        // Initialize Open AL manually
        // Clear Error Code
        AL.alGetError();
        
        // Set Listener attributes
        
        // Position ...
        AL.alListenerfv(AL.AL_POSITION, listenerPos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alListenerfv POSITION : ", error);
            System.exit(-1);
        }
        
        // Velocity ...
        AL.alListenerfv(AL.AL_VELOCITY, listenerVel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alListenerfv VELOCITY : ", error);
            System.exit(-1);
        }
        
        // Orientation ...
        AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alListenerfv ORIENTATION : ", error);
            System.exit(-1);
        }
        
        // Generate Buffers
        AL.alGenBuffers(NUM_BUFFERS, buffers);
        
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenBuffers :", error);
            System.exit(-1);
        }
        
        // Load in samples to be used by Test functions
        // Load footsteps.wav
        WaveData wavefile = WaveData.create("Footsteps.wav");
        if (wavefile == null) {
            displayALError("LoadWAVFile footsteps.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy footsteps.wav data into AL Buffer 0
        AL.alBufferData(buffers.get(0), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 0 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload footsteps.wav
        wavefile.dispose();
        wavefile = null;
        
        // Load ding.wav
        wavefile = WaveData.create("ding.wav");
        if (wavefile == null) {
            displayALError("LoadWAVFile ding.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy ding.wav data into AL Buffer 1
        AL.alBufferData(buffers.get(1), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 1 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload footsteps.wav
        wavefile.dispose();
        wavefile = null;
              
        // Load wave1.wav
        wavefile = WaveData.create("Wave1.WAV");
        if (wavefile == null) {
            displayALError("LoadWAVFile wave1.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy wave1.wav data into AL Buffer 2
        AL.alBufferData(buffers.get(2), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 2 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload wave1.wav
        wavefile.dispose();
        wavefile = null;
        
        // Load Wave2.wav
        wavefile = WaveData.create("Wave2.WAV");
        if (wavefile == null) {
            displayALError("LoadWAVFile Wave2.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy Wave2.wav data into AL Buffer 3
        AL.alBufferData(buffers.get(3), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 3 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload Wave2.wav
        wavefile.dispose();
        wavefile = null;
        
        // Load wave3.wav
        wavefile = WaveData.create("Wave3.WAV");
        if (wavefile == null) {
            displayALError("LoadWAVFile wave3.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy wave3.wav data into AL Buffer 4
        AL.alBufferData(buffers.get(4), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 4 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload wave3.wav
        wavefile.dispose();
        wavefile = null;
        
        // Load wave4.wav
        wavefile = WaveData.create("Wave4.WAV");
        if (wavefile == null) {
            displayALError("LoadWAVFile wave4.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy wave4.wav data into AL Buffer 5
        AL.alBufferData(buffers.get(5), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 5 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload wave4.wav
        wavefile.dispose();
        wavefile = null;
        
        // Load stereo.wav
        wavefile = WaveData.create("stereo.wav");
        if (wavefile == null) {
            displayALError("LoadWAVFile stereo.wav : ", error);
            // Delete Buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy stereo.wav data into AL Buffer 6
        AL.alBufferData(buffers.get(6), wavefile.format, wavefile.data, wavefile.data.capacity(), wavefile.samplerate);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alBufferData buffer 6 : ", error);
            // Delete buffers
            AL.alDeleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload stereo.wav
        wavefile.dispose();
        wavefile = null;
        
        //do EAX check (can only be performed after device / context creation
        eaxAvailable = AL.alIsExtensionPresent("EAX");
        
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
                ch = Character.toLowerCase((char) System.in.read());
                eatInput();
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case 'a':
                    fullAutoTests();
                    break;
                case 'b':
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
        } while (ch != 'q');
    }
    
    protected void fullAutoTests() {
        fA_RequestObjectNames();  // Request Object Names
        fA_ReleaseObjectNames();  // Release Object Names
        fA_ValidateObjectNames();  // Validating Object Names
        fA_StateTransition();  // State Transistion Testing
        fA_VectorStateTransition();  // Vector State Transistion Testing
        fA_GetBufferProperties();  // Get Buffer Properties
        fA_EnumerationValue(); // Enumeration Value Test

        System.out.print("\n\n");
        delay_ms(1000);
    }
    
    protected void fA_RequestObjectNames() {
        boolean localResultOK;
        IntBuffer testBuffers = createIntBuffer(1);
        IntBuffer testSources = createIntBuffer(1);

        int error;
        
        System.out.print("\nRequest Object Names Test. ");
        AL.alGetError(); // clear error state
        localResultOK = true;
        AL.alGenBuffers(0, testBuffers); // should be equivalent to NOP
        error = AL.alGetError();
        if (error != AL.AL_NO_ERROR) {
            localResultOK = false;
        }
        AL.alGenSources(0, testSources); // should be equivalent to NOP
        error = AL.alGetError();
        if (error != AL.AL_NO_ERROR) {
            localResultOK = false;
        }
        AL.alGenBuffers(-1, testBuffers); // invalid -- make sure error code comes back
        error = AL.alGetError();
        if (error == AL.AL_NO_ERROR) {
            localResultOK = false;
        }
        AL.alGenSources(-1, testSources); // invalid -- make sure error code comes back
        error = AL.alGetError();
        if (error == AL.AL_NO_ERROR) {
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
        AL.alGetError();
        localResultOK = true;
        AL.alDeleteBuffers(-1, testBuffers); // invalid -- make sure error code comes back
        error = AL.alGetError();
        if (error == AL.AL_NO_ERROR) {
            localResultOK = false;
        }
        AL.alDeleteSources(-1, testSources); // invalid -- make sure error code comes back
        error = AL.alGetError();
        if (error == AL.AL_NO_ERROR) {
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
        AL.alGetError();
        localResultOK = true;
        AL.alGenBuffers(1, testBuffers);
        AL.alGenSources(1, testSources);
        error = AL.alGetError();
        if (error != AL.AL_NO_ERROR) {
            localResultOK = false;
        } else {
            if (AL.alIsBuffer(testBuffers.get(0)) == false) // this buffer should test as valid
            {
                localResultOK = false;
            }
            if (AL.alIsSource(testSources.get(0)) == false) // this source should test as valid
            {
                localResultOK = false;
            }
            if (AL.alIsBuffer(testBuffers.get(0) + 1) == true) // this buffer should be invalid
            {
                localResultOK = false;
            }
            if (AL.alIsSource(testSources.get(0) + 1) == true) // this source should be invalid
            {
                localResultOK = false;
            }
            AL.alDeleteBuffers(1, testBuffers);
            AL.alDeleteSources(1, testSources);
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
        AL.alGetError();
        localResultOK = true;
        AL.alGenSources(1, testSources);
        AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(0));
        AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
        
        IntBuffer sourceState = createIntBuffer(1);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_INITIAL) {
            localResultOK = false;
        }
        AL.alSourcePlay(testSources.get(0));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PLAYING) {
            localResultOK = false;
        }
        AL.alSourcePause(testSources.get(0));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PAUSED) {
            localResultOK = false;
        }
        AL.alSourcePlay(testSources.get(0));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PLAYING) {
            localResultOK = false;
        }
        AL.alSourceStop(testSources.get(0));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_STOPPED) {
            localResultOK = false;
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
        AL.alDeleteSources(1, testSources);
    }
    
    protected void fA_VectorStateTransition() {
        boolean localResultOK;

        IntBuffer testSources = createIntBuffer(4);

        IntBuffer sourceState = createIntBuffer(1);        
        
        System.out.print("\nVector State Transition Test. ");
        AL.alGetError();
        localResultOK = true;
        AL.alGenSources(2, testSources);
        AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(0));
        AL.alSourcei(testSources.get(1), AL.AL_BUFFER, buffers.get(1));
        AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
        AL.alSourcei(testSources.get(1), AL.AL_LOOPING, AL.AL_TRUE);
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);

        if (sourceState.get(0) != AL.AL_INITIAL) {
            localResultOK = false;
            System.out.print("FAILED -- AL_INITIAL 0");
        }
        
        AL.alGetSourcei(testSources.get(1), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_INITIAL) {
            localResultOK = false;
            System.out.print("FAILED -- AL_INITIAL 1");
        }
        AL.alSourcePlay(testSources.get(0));
        AL.alSourcePlay(testSources.get(1));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 0");
        }
        
        AL.alGetSourcei(testSources.get(1), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 1");
        }
        AL.alSourcePause(testSources.get(0));
        AL.alSourcePause(testSources.get(1));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PAUSED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PAUSED 0");
        }
        
        AL.alGetSourcei(testSources.get(1), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PAUSED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PAUSED 1");
        }
        AL.alSourcePlay(testSources.get(0));
        AL.alSourcePlay(testSources.get(1));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 0A");
        }
        
        AL.alGetSourcei(testSources.get(1), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_PLAYING) {
            localResultOK = false;
            System.out.print("FAILED -- AL_PLAYING 1A");
        }
        AL.alSourceStop(testSources.get(0));
        AL.alSourceStop(testSources.get(1));
        delay_ms(500);
        
        AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_STOPPED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_STOPPED 0");
        }
        
        AL.alGetSourcei(testSources.get(1), AL.AL_SOURCE_STATE, sourceState);
        if (sourceState.get(0) != AL.AL_STOPPED) {
            localResultOK = false;
            System.out.print("FAILED -- AL_STOPPED 1");
        }
        if (localResultOK == true) {
            System.out.print("PASSED.");
        } else {
            System.out.print("FAILED.");
        }
        AL.alDeleteSources(2, testSources);
    }  
    
    protected void fA_GetBufferProperties() {
        IntBuffer freq = createByteBuffer(16).asIntBuffer();
        IntBuffer bits = ((IntBuffer) freq.position(4)).slice();
        IntBuffer chan = ((IntBuffer) freq.position(8)).slice();
        IntBuffer size = ((IntBuffer) freq.position(12)).slice();
        boolean passNULL;
        
        System.out.print("\nGet Buffer Properties Test. ");
        AL.alGetBufferi(buffers.get(0), AL.AL_FREQUENCY, freq);
        AL.alGetBufferi(buffers.get(0), AL.AL_BITS, bits);
        AL.alGetBufferi(buffers.get(0), AL.AL_CHANNELS, chan);
        AL.alGetBufferi(buffers.get(0), AL.AL_SIZE, size);
        
        passNULL = !(AL.alIsBuffer(0));  // the NULL buffer should cause alIsBuffer to be FALSE
        
        //       FREQ                         BITS                      CH                         SIZE
        if ((freq.get(0) == 44100) && (bits.get(0) == 16) && (chan.get(0) == 1) && (size.get(0) == 282626) && (passNULL == true)) {
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
            getVal = AL.alGetEnumValue(enumerationString[i]);
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

    protected void semiAutoTests() {
        sA_StringQueries();  // String Queries Test
        sA_SourceGain();  // Source Gain Test
        sA_ListenerGain();  // Listener Gain Test
        sA_Position();  // Position Test
        sA_SourceRelative();  // Source Relative Test
        sA_ListenerOrientation();  // Listener Orientation Test
        sA_SourceCone();  // Source Cone Test
        sA_MinMaxGain();  // MIN/MAX Gain Test
        sA_ReferenceDistance();  // Reference Distance Test
        sA_RolloffFactor();  // Rolloff Factor Test
        sA_DistanceModel();  // Distance Model Test
        sA_Doppler();  // Doppler Test
        sA_Frequency();  // Frequency Test
        sA_Stereo();  // Stereo Test
        sA_Streaming(); // Streaming Test
        sA_QueuingUnderrunPerformance(); // test underrun performance
        
        System.out.print("\nDone with this series of tests. Going back to the main menu...");
        delay_ms(1000);
    }
    
    protected int ContinueOrSkip() {
        int ch = -1;
        
        System.out.print("\nPress Return to run this test, or 'S' to skip:\n");
        
        while (true) {
            ch = CRToContinue();
            if (ch == 's') {
                return 0;
            }
            if (ch == 10) {
                return 1;
            }
        }
    }  
    
    protected int CRToContinue() {
        int current = -1;
        try {
            //read one, and eat the rest
            current = Character.toLowerCase((char) System.in.read());
            eatInput();
        } catch (Exception e) {
        }
        return (current == 13) ? 10 : current;
    }
    
    protected void eatInput() {
        try {
            while(System.in.available() > 0) {
                int eaten = System.in.read();
            }
        } catch (Exception e) {
        }
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
            
            tempString = ALC.alcGetString(ALC.ALC_DEVICE_SPECIFIER);
            System.out.print("OpenAL Context Device Specifier is '" + tempString + "'\n");
            tempString = AL.alGetString(AL.AL_RENDERER);
            System.out.print("OpenAL Renderer is '" + tempString + "'\n");
            tempString = AL.alGetString(AL.AL_VERSION);
            System.out.print("OpenAL Version is '" + tempString + "'\n");
            tempString = AL.alGetString(AL.AL_VENDOR);
            System.out.print("OpenAL Vendor is '" + tempString + "'\n");
            tempString = AL.alGetString(AL.AL_EXTENSIONS);
            System.out.print("OpenAL Extensions supported are :\n" + tempString + "\n");
            System.out.print("\nError Codes are :-\n");
            System.out.print("AL_NO_ERROR : '" + AL.alGetString(AL.AL_NO_ERROR) + "'\n");
            
            System.out.print("AL_INVALID_ENUM : '" + AL.alGetString(AL.AL_INVALID_ENUM) + "'\n");
            System.out.print("AL_INVALID_VALUE : '" + AL.alGetString(AL.AL_INVALID_VALUE) + "'\n");
            
            System.out.print("AL_INVALID_OPERATION : '" + AL.alGetString(AL.AL_INVALID_OPERATION) + "'\n");
            System.out.print("AL_OUT_OF_MEMORY : '" + AL.alGetString(AL.AL_OUT_OF_MEMORY) + "'\n");
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
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The following sound effect will be played at full source gain (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0),AL.AL_GAIN,1.0f);
            AL.alSourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at half source gain (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0),AL.AL_GAIN,0.5f);
            AL.alSourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at quarter source gain (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0),AL.AL_GAIN,0.25f);
            AL.alSourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at 1/20th source gain (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0),AL.AL_GAIN,0.05f);
            AL.alSourcePlay(testSources.get(0));
            CRForNextTest();
            AL.alSourcef(testSources.get(0),AL.AL_GAIN,1.0f);
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }  
    
    protected void sA_ListenerGain() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Listener Gain Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The following sound effect will be played at full listener gain (Press Return):\n");
            CRToContinue();
            AL.alListenerf(AL.AL_GAIN,1.0f);
            AL.alSourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at half listener gain (Press Return):\n");
            CRToContinue();
            AL.alListenerf(AL.AL_GAIN,0.5f);
            AL.alSourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at quarter listener gain (Press Return):\n");
            CRToContinue();
            AL.alListenerf(AL.AL_GAIN,0.25f);
            AL.alSourcePlay(testSources.get(0));
            System.out.print("The following sound effect will be played at 1/20th listener gain (Press Return):\n");
            CRToContinue();
            AL.alListenerf(AL.AL_GAIN,0.05f);
            AL.alSourcePlay(testSources.get(0));
            CRForNextTest();
            AL.alListenerf(AL.AL_GAIN,1.0f);
            FloatBuffer f = createFloatBuffer(1);
            AL.alGetListenerf(AL.AL_GAIN, f);
            if (f.get(0) != 1.0) { System.out.print("ERROR:  alGetListenerf failed.\n"); }
            AL.alSourceStop(testSources.get(0));
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_Position() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        int i;
        FloatBuffer tempFVect = createFloatBuffer(6);
        
        System.out.print("Position Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("Trying Left-to-Right sweep by moving listener(Press Return):\n");
            CRToContinue();
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alListener3f(AL.AL_POSITION, 100.0f, 0.0f, 0.0f);
            AL.alGetListenerfv(AL.AL_POSITION, tempFVect);
            if ((tempFVect.get(0) != 100.0f) || (tempFVect.get(1) != 0.0f) || (tempFVect.get(2) != 0.0f)) {
                System.out.print("ERROR: alGetListener3f(AL_POSITION, ...).\n");
            }
            AL.alGetListenerfv(AL.AL_POSITION, tempFVect);
            if ((tempFVect.get(0) != 100.0f) || (tempFVect.get(1) != 0.0) || (tempFVect.get(2) != 0.0f)) {
                System.out.print("ERROR: alGetListenerfv(AL_POSITION, ...).\n");
            }
            AL.alSourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                AL.alListener3f(AL.AL_POSITION, (float) -i, 0.0f, 0.0f);
                delay_ms(100);
            }
            AL.alListener3f(AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourceStop(testSources.get(0));
            System.out.print("Trying Left-to-Right sweep by moving source (Press Return):\n");
            CRToContinue();
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, -100.0f, 0.0f, 0.0f);
            AL.alSourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourceStop(testSources.get(0));
            System.out.print("Trying Back-to-Front sweep (Press Return):\n");
            CRToContinue();
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -100.0f);
            AL.alGetSourcefv(testSources.get(0), AL.AL_POSITION, tempFVect);
            if ((tempFVect.get(0) != 0.0f) || (tempFVect.get(1) != 0.0f) || (tempFVect.get(2) != -100.0f)) {
                System.out.print("ERROR: alGetSourcefv(..., AL_POSITION, ...).\n");
            }
            AL.alSourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, (float) -i);
                delay_ms(100);
            }
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourceStop(testSources.get(0));
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_SourceRelative() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        int i;
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Source Relative Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("Placing Listener at (100, 0, 0) and sweeping source from (0, 0, 0) to (100, 0, 0).  The sound should pan from left to center (Press Return):\n");
            CRToContinue();
            AL.alListener3f(AL.AL_POSITION, 100.0f, 0.0f, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, -10.0f, 0.0f, 0.0f);
            AL.alSourcePlay(testSources.get(0));
            for (i = 00; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourceStop(testSources.get(0));
            
            System.out.print("Turning on source relative mode, placing Listener at (100, 0, 0), and sweeping source from (0, 0, 0) to (100, 0, 0).  The sound should pan from center to right (Press Return):\n");
            CRToContinue();
            AL.alSourcei(testSources.get(0), AL.AL_SOURCE_RELATIVE, AL.AL_TRUE);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, -100.0f, 0.0f, 0.0f);
            AL.alSourcePlay(testSources.get(0));
            for (i = 0; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            
            AL.alListener3f(AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourcei(testSources.get(0), AL.AL_SOURCE_RELATIVE, AL.AL_FALSE);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourceStop(testSources.get(0));
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_ListenerOrientation() {
        IntBuffer testSources = createIntBuffer(2);
        
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Listener Orientation Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The listener will be placed at (1, 0, 0) and will face the -X direction.  The sound should be centered. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alListenerf(AL.AL_GAIN,1.0f);
            FloatBuffer f = createFloatBuffer(1);
            AL.alGetSourcef(testSources.get(0), AL.AL_GAIN, f);
            if (f.get(0) != 1.0f) { System.out.print("ERROR: alGetSourcef(..., AL_GAIN, ...).\n"); }
            AL.alListener3f(AL.AL_POSITION, 1.0f, 0.0f, 0.0f);
            listenerOri.put(0, listenerOri.get(0)-1.0f);
            listenerOri.put(1, 0.0f);
            listenerOri.put(2, 0.0f);
            listenerOri.put(3, 0.0f);
            listenerOri.put(4, 1.0f);
            listenerOri.put(5, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alSourcePlay(testSources.get(0));
            delay_ms(4000);
            AL.alSourceStop(testSources.get(0));
            
            System.out.print("The listener will now be oriented down the -Z axis.  The sound should be to the left. (Press Return):\n");
            CRToContinue();
            listenerOri.put(0, 0.0f);
            listenerOri.put(1, 0.0f);
            listenerOri.put(2, listenerOri.get(2)-1.0f);
            listenerOri.put(3, 0.0f);
            listenerOri.put(4, 1.0f);
            listenerOri.put(5, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcePlay(testSources.get(0));
            delay_ms(4000);
            AL.alSourceStop(testSources.get(0));
            
            System.out.print("The listener will now be turned upside-down (the 'up' direction will be (0, -1, 0)).  The sound should be to the right. (Press Return):\n");
            CRToContinue();
            listenerOri.put(0, 0.0f);
            listenerOri.put(1, 0.0f);
            listenerOri.put(2, listenerOri.get(2)-1.0f);
            listenerOri.put(3, 0.0f);
            listenerOri.put(4, listenerOri.get(4)-1.0f);
            listenerOri.put(5, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcePlay(testSources.get(0));
            delay_ms(4000);
            AL.alSourceStop(testSources.get(0));
            
            System.out.print("The listener will now be oriented down the +Z axis (and the 'up' direction is now (0, 1, 0) again).  The sound should be to the right. (Press Return):\n");
            CRToContinue();
            listenerOri.put(0, 0.0f);
            listenerOri.put(1, 0.0f);
            listenerOri.put(2, 1.0f);
            listenerOri.put(3, 0.0f);
            listenerOri.put(4, 1.0f);
            listenerOri.put(5, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcePlay(testSources.get(0));
            delay_ms(4000);
            AL.alSourceStop(testSources.get(0));
            
            CRForNextTest();
            AL.alListenerf(AL.AL_GAIN,1.0f);
            AL.alListener3f(AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            listenerOri.put(0, 0.0f);
            listenerOri.put(1, 0.0f);
            listenerOri.put(2, listenerOri.get(2)-1.0f);
            listenerOri.put(3, 0.0f);
            listenerOri.put(4, 1.0f);
            listenerOri.put(5, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_SourceCone() {
        
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer	sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {0.0f,0.0f,1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer	sourceOri2 = createFloatBuffer(6);
        sourceOri2.put(new float[] {1.0f,0.0f,1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer	sourceOri3 = createFloatBuffer(6);
        sourceOri3.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Source Cone Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The listener will be at (0,0,0).  The source will be at (0,0,-1).  The source will be directly facing the listener and should be loud. (Press Return):\n");
            CRToContinue();
            AL.alListener3f(AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcef(testSources.get(0), AL.AL_CONE_INNER_ANGLE, 10.0f);
            AL.alSourcef(testSources.get(0), AL.AL_CONE_OUTER_ANGLE, 270.0f);
            AL.alSourcef(testSources.get(0), AL.AL_CONE_OUTER_GAIN, (float)0.01);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -1.0f);
            AL.alSourcefv(testSources.get(0), AL.AL_DIRECTION, sourceOri);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will now point between the inner and outer cones, and should be at medium volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcefv(testSources.get(0), AL.AL_DIRECTION, sourceOri2);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will now point behind the outer cone and will be at low volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcefv(testSources.get(0), AL.AL_DIRECTION, sourceOri3);
            AL.alSourcePlay(testSources.get(0));
            
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_MinMaxGain() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("MIN/MAX Gain Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The source will be played at GAIN 1.0 with MAX gain set to 1.0. This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_GAIN, 1.0f);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_GAIN, 1.0f);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played at GAIN 0.1 with MIN gain set to 0.6.  This should be at medium volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_GAIN, (float) 0.1);
            AL.alSourcef(testSources.get(0), AL.AL_MIN_GAIN, (float) 0.6);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played at GAIN 1.0 with MAX gain set to 0.1.  This should be low volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_GAIN, 1.0f);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_GAIN, (float) 0.1);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played at GAIN 0.1 with MIN gain set to 0.0.  This should be low volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_GAIN, (float) 0.1);
            AL.alSourcef(testSources.get(0), AL.AL_MIN_GAIN, 0.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played at GAIN 1.0 with MAX gain set to 0.0.  This should be zero volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_GAIN, (float) 1.0);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_GAIN, 0.0f);
            AL.alSourcePlay(testSources.get(0));
            
            CRForNextTest();
            AL.alSourcef(testSources.get(0), AL.AL_GAIN, 1.0f);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_GAIN, 1.0f);
            AL.alSourcef(testSources.get(0), AL.AL_MIN_GAIN, 0.0f);
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_ReferenceDistance() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Reference Distance Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The source will be placed at (0, 0, -10), and the reference distance set at 1.0. This should be low volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -10.0f);
            AL.alSourcef(testSources.get(0), AL.AL_REFERENCE_DISTANCE, 1.0f);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played with the reference distance set to 3.0.  This should be medium volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_REFERENCE_DISTANCE, 3.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played with the reference distance set to 10.0.  This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_REFERENCE_DISTANCE, 10.0f);
            AL.alSourcePlay(testSources.get(0));
            
            CRForNextTest();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourcef(testSources.get(0), AL.AL_REFERENCE_DISTANCE, 1.0f);
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }

    protected void sA_RolloffFactor() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Rolloff Factor Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The source will be played with the rolloff factor set to 0.0.  This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -10.0f);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            AL.alSourcef(testSources.get(0), AL.AL_ROLLOFF_FACTOR, 0.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -10), and the rolloff factor set at 1.0. This should be medium volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_ROLLOFF_FACTOR, 1.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played with the rolloff factor set to 3.0.  This should be low volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_ROLLOFF_FACTOR, 3.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be played with the rolloff factor set to 10.0.  This should be very low volume. (Press Return):\n");
            CRToContinue();
            AL.alSourcef(testSources.get(0), AL.AL_ROLLOFF_FACTOR, 10.0f);
            AL.alSourcePlay(testSources.get(0));
            
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_DistanceModel() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Distance Model Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("The source will be placed at (0, 0, -10). This should be low volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -10.0f);
            AL.alDistanceModel(AL.AL_INVERSE_DISTANCE);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -1). This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -1.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -10) and the distance model will be set to AL_NONE. This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -10.0f);
            AL.alDistanceModel(AL.AL_NONE);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -100) and the distance model will remain AL_NONE. This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -100.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -100) and the distance model will be AL_INVERSE_DISTANCE_CLAMPED. AL_MAX_DISTANCE will be set to 100.0.  This should be low volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -100.0f);
            AL.alDistanceModel(AL.AL_INVERSE_DISTANCE_CLAMPED);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_DISTANCE, 100.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -100) and the distance model will be AL_INVERSE_DISTANCE_CLAMPED. AL_MAX_DISTANCE will be set to 20.0.  This should be louder. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -100.0f);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_DISTANCE, 20.0f);
            AL.alSourcePlay(testSources.get(0));
            
            System.out.print("The source will be placed at (0, 0, -100) and the distance model will be AL_INVERSE_DISTANCE_CLAMPED. AL_MAX_DISTANCE will be set to 5.0.  This should be high volume. (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, -100.0f);
            AL.alSourcef(testSources.get(0), AL.AL_MAX_DISTANCE, 5.0f);
            AL.alSourcePlay(testSources.get(0));
            
            CRForNextTest();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alDistanceModel(AL.AL_INVERSE_DISTANCE);
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
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
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("Trying Left-to-Right sweep with doppler shift (Press Return):\n");
            CRToContinue();
            AL.alListenerfv(AL.AL_ORIENTATION, listenerOri);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_TRUE);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, -100.0f, 0.0f, 0.0f);
            AL.alSource3f(testSources.get(0), AL.AL_VELOCITY, 10.0f, 0.0f, 0.0f);
            AL.alSourcefv(testSources.get(0), AL.AL_ORIENTATION, sourceOri);
            AL.alSourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            AL.alSourceStop(testSources.get(0));
            System.out.print("Trying Left-to-Right sweep with DopplerFactor set to 4.0 -- should be more extreme (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, -100.0f, 0.0f, 0.0f);
            AL.alSource3f(testSources.get(0), AL.AL_VELOCITY, 10.0f, 0.0f, 0.0f);
            AL.alDopplerFactor(4.0f);
            if (AL.alGetFloat(AL.AL_DOPPLER_FACTOR) != 4.0f) { System.out.print(" alGetFloat(AL_DOPPLER_FACTOR) error.\n"); }
            AL.alSourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            AL.alSourceStop(testSources.get(0));
            AL.alDopplerFactor(1.0f);
            System.out.print("Trying Left-to-Right sweep with DopplerVelocity set to 86 -- should remain extreme (Press Return):\n");
            CRToContinue();
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, -100.0f, 0.0f, 0.0f);
            AL.alSource3f(testSources.get(0), AL.AL_VELOCITY, 10.0f, 0.0f, 0.0f);
            AL.alDopplerVelocity(86);
            if (AL.alGetFloat(AL.AL_DOPPLER_VELOCITY) != 86) { System.out.print(" alGetFloat(AL_DOPPLER_VELOCITY) error.\n"); }
            AL.alSourcePlay(testSources.get(0));
            for (i = -100; i < 100; i++) {
                AL.alSource3f(testSources.get(0), AL.AL_POSITION, (float) i, 0.0f, 0.0f);
                delay_ms(100);
            }
            AL.alDopplerVelocity(343);
            AL.alSource3f(testSources.get(0), AL.AL_POSITION, 0.0f, 0.0f, 0.0f);
            AL.alSourceStop(testSources.get(0));
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_Frequency() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        int i;
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Frequency Test:");
        if (ContinueOrSkip() == 1) {
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("A source will be played eight times -- going from one-half to double it's native frequency (Press Return):\n");
            CRToContinue();
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            for (i = 0; i < 8; i++) {
                AL.alSourcef(testSources.get(0), AL.AL_PITCH, (float) (0.5 + (float) i * 0.2));
                AL.alSourcePlay(testSources.get(0));
                delay_ms(2000);
            }
            AL.alSourceStop(testSources.get(0));
            AL.alSourcef(testSources.get(0), AL.AL_PITCH, 1.0f);
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_Stereo() {
        IntBuffer testSources = createIntBuffer(2);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        int error;
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Stereo Test:");
        if (ContinueOrSkip() == 1) {
            // clear error state
            AL.alGetError();
            
            // load up sources
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, buffers.get(1));
            
            System.out.print("A stereo buffer will play twice in succession (Press Return):\n");
            CRToContinue();
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                displayALError("Init error : ", error);
            AL.alSourceQueueBuffers(testSources.get(0), 1, ((ByteBuffer)buffers.position(4*6)).slice().asIntBuffer());
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                displayALError("alSourceQueueBuffers 1 (stereo) : ", error);
            AL.alSourceQueueBuffers(testSources.get(0), 1, ((ByteBuffer)buffers.position(4*6)).slice().asIntBuffer());
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                displayALError("alSourceQueueBuffers 1 (stereo) : ", error);
            AL.alSourcePlay(testSources.get(0));
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
        }
    }
    
    protected void sA_Streaming() {
        System.out.print("Streaming Test:");
        if (ContinueOrSkip() == 1) {
            System.out.print("A stereo audio file will now be streamed from a file (Press Return):\n");
            CRToContinue();
            //I_StreamingTest();
            CRForNextTest();
        }
    }
    
    protected void sA_QueuingUnderrunPerformance() {
        IntBuffer testSources = createIntBuffer(2);
        IntBuffer bufferName = createIntBuffer(1);
        int error;
        IntBuffer tempInt = createIntBuffer(1);
        FloatBuffer	listenerOri = createFloatBuffer(6);
        listenerOri.put(new float[] {0.0f,0.0f,-1.0f, 0.0f,1.0f,0.0f});
        
        FloatBuffer sourceOri = createFloatBuffer(6);
        sourceOri.put(new float[] {1.0f,0.0f,0.0f, 0.0f,1.0f,0.0f});
        
        System.out.print("Queuing Underrun Performance Test:");
        if (ContinueOrSkip() == 1) {
            System.out.print("A stereo buffer will play once, there will be a brief pause, and then the buffer will play again (Press Return):\n");
            CRToContinue();
            AL.alGetError();
            AL.alGenSources(1, testSources);
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alSourcei(testSources.get(0), AL.AL_LOOPING, AL.AL_FALSE);
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                displayALError("Init error : ", error);
            AL.alSourceQueueBuffers(testSources.get(0), 1, ((ByteBuffer)buffers.position(4*6)).slice().asIntBuffer());
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                displayALError("alSourceQueueBuffers 1 (stereo) : ", error);
            AL.alSourcePlay(testSources.get(0));
            delay_ms(4000);
            AL.alGetSourcei(testSources.get(0), AL.AL_SOURCE_STATE, tempInt);
            if (tempInt.get(0) != AL.AL_STOPPED)
                System.out.print("Wrong underrun state -- should be AL_STOPPED. ");
            AL.alGetSourcei(testSources.get(0), AL.AL_BUFFERS_PROCESSED, tempInt);
            if (tempInt.get(0) != 1) {
                System.out.print("Wrong underrun state -- should have one buffer processed. ");
            } else {
                AL.alSourceUnqueueBuffers(testSources.get(0), tempInt.get(0), bufferName);
            }
            AL.alSourceQueueBuffers(testSources.get(0), 1, ((ByteBuffer)buffers.position(4*6)).slice().asIntBuffer());
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                displayALError("alSourceQueueBuffers 1 (stereo) : ", error);
            AL.alSourcePlay(testSources.get(0));
            delay_ms(3000);
            System.out.print("The stereo buffer will now play twice with no pause (Press Return):\n");
            CRToContinue();
            AL.alSourceQueueBuffers(testSources.get(0), 1, ((ByteBuffer)buffers.position(4*6)).slice().asIntBuffer());
            AL.alSourcePlay(testSources.get(0));
            delay_ms(4000);
            CRForNextTest();
            
            // dispose of sources
            AL.alSourcei(testSources.get(0), AL.AL_BUFFER, 0);
            AL.alDeleteSources(1, testSources);
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
        
        AL.alGenSources(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        }
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        }
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        }
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY,source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        }
        
        AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(1));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        }
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_TRUE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        }
        
        AL.alSourcef(source.get(1),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        }
        
        AL.alSourcef(source.get(1),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        }
        
        AL.alSourcefv(source.get(1),AL.AL_POSITION,source1Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        }
        
        AL.alSourcefv(source.get(1),AL.AL_VELOCITY,source1Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        }
        
        AL.alSourcei(source.get(1),AL.AL_BUFFER, buffers.get(1));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        }
        
        AL.alSourcei(source.get(1),AL.AL_LOOPING,AL.AL_FALSE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
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
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    AL.alSourcePlay(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    AL.alSourcePlay(source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '3':
                    AL.alSourceStop(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop source 0 : ", error);
                    break;
                case '4':
                    AL.alSourceStop(source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop source 1 : ", error);
                    break;
            }
        } while (ch != 'q');
        
        // Release resources
        AL.alSourceStopv(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        AL.alDeleteSources(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
        
        return;
    }
    
    protected void i_LoopingTest() {
        int	error;
        IntBuffer source = createIntBuffer(2);
        int	ch = -1;
        int bLooping0 = AL.AL_FALSE;
        int bLooping1 = AL.AL_FALSE;
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {-2.0f, 0.0f, -2.0f});

        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        FloatBuffer source1Pos = createFloatBuffer(3);
        source1Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source1Vel = createFloatBuffer(3);
        source1Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        // Clear Error Code
        AL.alGetError();
        
        AL.alGenSources(2,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        }
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        }
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        }
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY,source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        }
        
        AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(0));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        }
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_FALSE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING false : \n", error);
        }
        
        AL.alSourcef(source.get(1),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        }
        
        AL.alSourcef(source.get(1),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        }
        
        AL.alSourcefv(source.get(1),AL.AL_POSITION,source1Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        }
        
        AL.alSourcefv(source.get(1),AL.AL_VELOCITY,source1Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        }
        
        AL.alSourcei(source.get(1),AL.AL_BUFFER, buffers.get(1));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        }
        
        AL.alSourcei(source.get(1),AL.AL_LOOPING,AL.AL_FALSE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
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
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    AL.alSourcePlay(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    if (bLooping0 == AL.AL_FALSE) {
                        bLooping0 = AL.AL_TRUE;
                        if (bLooping1 == AL.AL_TRUE)
                            System.out.print("Source 0 : Looping     Source 1 : Looping    \n");
                        else
                            System.out.print("Source 0 : Looping     Source 1 : Not looping\n");
                    }
                    else {
                        bLooping0 = AL.AL_FALSE;
                        if (bLooping1 == AL.AL_TRUE)
                            System.out.print("Source 0 : Not looping Source 1 : Looping    \n");
                        else
                            System.out.print("Source 0 : Not looping Source 1 : Not looping\n");
                    }
                    AL.alSourcei(source.get(0), AL.AL_LOOPING, bLooping0);
                    break;
                case '3':
                    AL.alSourcePlay(source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '4':
                    if (bLooping1 == AL.AL_FALSE) {
                        bLooping1 = AL.AL_TRUE;
                        if (bLooping0 == AL.AL_TRUE)
                            System.out.print("Source 0 : Looping     Source 1 : Looping    \n");
                        else
                            System.out.print("Source 0 : Not looping Source 1 : Looping    \n");
                    }
                    else {
                        bLooping1 = AL.AL_FALSE;
                        if (bLooping0 == AL.AL_TRUE)
                            System.out.print("Source 0 : Looping     Source 1 : Not looping\n");
                        else
                            System.out.print("Source 0 : Not looping Source 1 : Not looping\n");
                    }
                    AL.alSourcei(source.get(1), AL.AL_LOOPING, bLooping1);
                    break;
            }
        } while (ch != 'q');
        
        System.out.print("\n");
        
        // Release resources
        AL.alSourceStop(source.get(0));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStop source 1 : ", error);
        
        AL.alDeleteSources(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alDeleteSources 1 : ", error);
        
        return;
    }
    
    protected void i_EAXTest() {
        
        int	error;
        int	ch = -1;
        IntBuffer source = createIntBuffer(2);
        
        EAXBufferProperties eaxBufferProp = new EAXBufferProperties();
        EAXListenerProperties eaxListenerProp = new EAXListenerProperties();
        
        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {-2.0f, 0.0f, 2.0f});
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        FloatBuffer source1Pos = createFloatBuffer(3);
        source1Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        FloatBuffer source1Vel = createFloatBuffer(3);
        source1Vel.put(new float[] {0.0f, 0.0f, 0.0f});
        
        try {
            EAX.create();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        // Clear Error Code
        AL.alGetError();
        
        AL.alGenSources(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY,source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(0));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_TRUE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        
        AL.alSourcef(source.get(1),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        
        AL.alSourcef(source.get(1),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        
        AL.alSourcefv(source.get(1),AL.AL_POSITION,source1Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        
        AL.alSourcefv(source.get(1),AL.AL_VELOCITY,source1Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        
        AL.alSourcei(source.get(1),AL.AL_BUFFER, buffers.get(1));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        
        AL.alSourcei(source.get(1),AL.AL_LOOPING,AL.AL_FALSE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
        System.out.print("Press 'C' to COMMIT EAX settings\n");
        System.out.print("Press 'Q' to quit\n\n");
        
        do {
            try {
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            switch (ch) {
                case '1':
                    AL.alSourcePlay(source.get(0));
                    break;
                case '2':
                    AL.alSourcePlay(source.get(1));
                    break;
                case '3':
                    AL.alSourceStop(source.get(0));
                    break;
                case '4':
                    AL.alSourceStop(source.get(1));
                    break;
                case '5':
                    eaxListenerProp.setEnvironment(EAX.EAX_ENVIRONMENT_HANGAR);
                    EAX.eaxSetProperty(eaxListenerProp, 
                        EAXListenerProperties.EAXLISTENER_ENVIRONMENT | 
                        EAXListenerProperties.EAXLISTENER_DEFERRED,
                    	  0);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXLISTENER_ENVIRONMENT | EAXLISTENER_DEFERRED : \n", error);
                    break;
                    
                case '6':
                    eaxListenerProp.setRoom(-10000);
                    EAX.eaxSetProperty(eaxListenerProp, 
                      EAXListenerProperties.EAXLISTENER_ROOM | 
                      EAXListenerProperties.EAXLISTENER_DEFERRED, 
                      0);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXLISTENER_ROOM | EAXLISTENER_DEFERRED : \n", error);
                    break;
                    
                case '7':
                
                    EAX.eaxGetProperty(eaxBufferProp, 
                      EAXBufferProperties.EAXBUFFER_ALLPARAMETERS, 
                      source.get(0));
                      
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxGet EAXBUFFER_ALLPARAMETERS : \n", error);
                    eaxBufferProp.setOcclusion(-5000);
                    EAX.eaxSetProperty(eaxBufferProp, 
                      EAXBufferProperties.EAXBUFFER_ALLPARAMETERS | 
                      EAXBufferProperties.EAXBUFFER_DEFERRED, 
                      source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_ALLPARAMETERS | EAXBUFFER_DEFERRED : \n", error);
                    break;
                    
                case '8':
                    eaxBufferProp.setOcclusion(0);
                    EAX.eaxSetProperty(eaxBufferProp, 
                      EAXBufferProperties.EAXBUFFER_OCCLUSION | 
                      EAXBufferProperties.EAXBUFFER_DEFERRED, 
                      source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_OCCLUSION | EAXBUFFER_DEFERRED : \n", error);
                    break;
                    
                case '9':
                    eaxBufferProp.setObstruction(-5000);
                    EAX.eaxSetProperty(eaxBufferProp, 
                      EAXBufferProperties.EAXBUFFER_OBSTRUCTION, 
                      source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_OBSTRUCTION : \n", error);
                    break;
                    
                case '0':
                    eaxBufferProp.setObstruction(0);
                    EAX.eaxSetProperty(eaxBufferProp, 
                      EAXBufferProperties.EAXBUFFER_OBSTRUCTION, 
                      source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_OBSTRUCTION : \n", error);
                    break;
                    
                case 'c':
                    // Commit settings on source 0
                    EAX.eaxSetProperty(eaxBufferProp, 
                      EAXBufferProperties.EAXBUFFER_COMMITDEFERREDSETTINGS,
                    	source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXBUFFER_COMMITDEFERREDSETTINGS : \n", error);
                    
                    // Commit Listener settings
                    EAX.eaxSetProperty(eaxListenerProp, 
                      EAXListenerProperties.EAXLISTENER_COMMITDEFERREDSETTINGS,
                    	0);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("eaxSet EAXLISTENER_COMMITDEFERREDSETTINGSENVIRONMENT : \n", error);
                    break;
            }
        } while (ch != 'q');
        
        // reset EAX level
        eaxListenerProp.setRoom(-10000);
        EAX.eaxSetProperty(eaxListenerProp, 
          EAXListenerProperties.EAXLISTENER_ROOM, 
          0);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("eaxSet EAXLISTENER_ROOM : \n", error);
        
        // Release resources
        AL.alSourceStopv(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        AL.alDeleteSources(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
        AL.alGetError();
        
        AL.alGenSources(1,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : ", error);
        }
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : ", error);
        }
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : ", error);
        }
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY,source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : ", error);
        }
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_FALSE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING false: ", error);
        }
        
        bLooping = AL.AL_FALSE;
        
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
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            switch (ch) {
            case '1':
                AL.alSourcePlay(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcePlay source 0 : ", error);
                break;
            case '2':
                AL.alSourceStop(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
                break;
            case '3':
                if (bLooping == AL.AL_TRUE)
                {
                    bLooping = AL.AL_FALSE;
                    System.out.print("Source 0 not looping\n");
                }
                else
                {
                    bLooping = AL.AL_TRUE;
                    System.out.print("Source 0 looping    \n");
                }
                AL.alSourcei(source.get(0), AL.AL_LOOPING, bLooping);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei AL_LOOPING : ", error);
                break;
            case '4':
                AL.alSourceQueueBuffers(source.get(0), 4, buffers);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 4 : ", error);
                break;
            case '5':
                AL.alSourceQueueBuffers(source.get(0), 1, ((ByteBuffer)buffers.position(4*0)).slice().asIntBuffer());
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '6':
                AL.alSourceQueueBuffers(source.get(0), 1, ((ByteBuffer)buffers.position(4*1)).slice().asIntBuffer());
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '7':
                AL.alSourceQueueBuffers(source.get(0), 1, ((ByteBuffer)buffers.position(4*2)).slice().asIntBuffer());
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '8':
                AL.alSourceQueueBuffers(source.get(0), 1, ((ByteBuffer)buffers.position(4*3)).slice().asIntBuffer());
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 : ", error);
                break;
            case '9':
                // Queue buffer 0
                AL.alSourceQueueBuffers(source.get(0), 1, ((ByteBuffer)buffers.position(4*4)).slice().asIntBuffer());
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 1 (buffer 0) : ", error);
                break;
            case 'a':
                // Unqueue first Buffer
                AL.alSourceUnqueueBuffers(source.get(0), 1, buffersremoved);
                          
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
            case 'b':
                // Unqueue first 2 Buffers
                AL.alSourceUnqueueBuffers(source.get(0), 2, buffersremoved);
                          
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
            case 'c':
                // Unqueue first 3 Buffers
                AL.alSourceUnqueueBuffers(source.get(0), 3, buffersremoved);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
            case 'd':
                // Unqueue first 4 Buffers
                AL.alSourceUnqueueBuffers(source.get(0), 4, buffersremoved);
                          
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
            case 'e':
                // Unqueue first 5 Buffers
                AL.alSourceUnqueueBuffers(source.get(0), 5, buffersremoved);
                          
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
            case 'f':
                AL.alSourcei(source.get(0), AL.AL_BUFFER, 0);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSource AL_BUFFER NULL : ", error);
                break;
            case '0':
                // Retrieve number of buffers in queue
                AL.alGetSourcei(source.get(0), AL.AL_BUFFERS_QUEUED, BuffersInQueue);
                
                // Retrieve number of processed buffers
                AL.alGetSourcei(source.get(0), AL.AL_BUFFERS_PROCESSED, BuffersProcessed);
                
                // Retrieve current buffer
                AL.alGetSourcei(source.get(0), AL.AL_BUFFER, Buffer);
                
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
        } while (ch != 'q');
        
        // Release resources
        AL.alSourceStop(source.get(0));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStop : ", error);
        
        AL.alDeleteSources(1, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
        
        AL.alGenSources(1,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY,source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_FALSE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        System.out.print("Buffer Test\n");
        System.out.print("Press '1' to play buffer 0 on source 0\n");
        System.out.print("Press '2' to play buffer 1 on source 0\n");
        System.out.print("Press '3' to stop source 0\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    // Stop source
                    AL.alSourceStop(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop 0 : ", error);
                    // Attach buffer 0 to source
                    AL.alSourcei(source.get(0), AL.AL_BUFFER, buffers.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcei AL_BUFFER 0 : ", error);
                    // Play
                    AL.alSourcePlay(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay 0 : ", error);
                    break;
                case '2':
                    // Stop source
                    AL.alSourceStop(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop 0 : ", error);
                    // Attach buffer 0 to source
                    AL.alSourcei(source.get(0), AL.AL_BUFFER, buffers.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcei AL_BUFFER 1 : ", error);
                    // Play
                    AL.alSourcePlay(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay 0 : ", error);
                    break;
                case '3':
                    // Stop source
                    AL.alSourceStop(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop 0 : ", error);
                    break;
            }
        } while (ch != 'q');
        
        // Release resources
        AL.alSourceStopv(1, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStopv 1 : ", error);
        
        AL.alDeleteSources(1, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
        
        AL.alGenSources(1,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY, source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(1));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_BUFFER buffer 1 : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_TRUE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        System.out.print("Frequency Test\n");
        System.out.print("Press '1' to play source 0 (looping)\n");
        System.out.print("Press '2' to Double Frequency\n");
        System.out.print("Press '3' to Halve Frequency\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    AL.alSourcef(source.get(0), AL.AL_PITCH, 1.0f);
                    AL.alSourcePlay(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    AL.alSourcef(source.get(0), AL.AL_PITCH, 2.0f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcef source 0 AL_PITCH 2.0 : ", error);
                    break;
                case '3':
                    AL.alSourcef(source.get(0), AL.AL_PITCH, 0.5f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcef source 0 AL PITCH 0.5: ", error);
                    break;
            }
        } while (ch != 'q');
        
        // Release resources
        AL.alSourceStopv(1, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        AL.alDeleteSources(1, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
        int bLoop = AL.AL_TRUE;

        FloatBuffer source0Pos = createFloatBuffer(3);
        source0Pos.put(new float[] {2.0f, 0.0f, -2.0f});
        
        FloatBuffer source0Vel = createFloatBuffer(3);
        source0Vel.put(new float[] {0.0f, 0.0f, 0.0f});        
        
        AL.alGenSources(1,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
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
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            
            switch (ch) {
            case '1':
                // Stop source
                AL.alSourceStop(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
             
                // Attach new buffer
                AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(6));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei 0 AL_BUFFER buffer 6 (stereo) : \n", error);
             
                // Set volume
                AL.alSourcef(source.get(0),AL.AL_GAIN,0.5f);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcef 0 AL_GAIN : \n", error);
             
                // Set looping
                AL.alSourcei(source.get(0),AL.AL_LOOPING,bLoop);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING true: \n", error);
             
                // Play source
                AL.alSourcePlay(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcePlay source 0 : ", error);
             
                break;
            case '2':
                // Stop source
                AL.alSourceStop(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
             
                // Attach new buffer
                AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei 0 AL_BUFFER buffer 0 (mono) : \n", error);
             
                // Set 3D position
                AL.alSourcefv(source.get(0),AL.AL_POSITION, source0Pos);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcefv 0 AL_POSITION : \n", error);
             
                // Set 3D velocity
                AL.alSourcefv(source.get(0),AL.AL_VELOCITY, source0Vel);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
             
                // Set volume to full
                AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcef 0 AL_GAIN : \n", error);
             
                // Set Looping
                AL.alSourcei(source.get(0),AL.AL_LOOPING,bLoop);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING : \n", error);
             
                // Play source
                AL.alSourcePlay(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcePlay source 0 : ", error);
                break;
            case '3':
                AL.alSourceStop(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceStop source 0 : ", error);
                break;
            case '4':
                AL.alSourceStop(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceStop Source 0 : ", error);
             
                // Attach NULL buffer to source to clear everything
                AL.alSourcei(source.get(0), AL.AL_BUFFER, 0);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei AL_BUFFER (NULL) : ", error);
             
                AL.alSourceQueueBuffers(source.get(0), 2, buffers);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceQueueBuffers 2 (stereo) : ", error);
             
                // Set Looping
                AL.alSourcei(source.get(0),AL.AL_LOOPING,bLoop);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING : \n", error);
             
                AL.alSourcePlay(source.get(0));
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcePlay Source 0 : ", error);
                break;
            case '5':
                //yes, this causes a invalid operation - so does the original :/
                AL.alSourceUnqueueBuffers(source.get(0), 2, buffers);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourceUnqueueBuffers 2 (stereo) : ", error);
                break;
            case '6':
                if (bLoop == AL.AL_TRUE)
                {
                    System.out.print("Looping is off\n");
                    bLoop = AL.AL_FALSE;
                }
                else
                {
                    System.out.print("Looping is on  \n");
                    bLoop = AL.AL_TRUE;
                }
                AL.alSourcei(source.get(0), AL.AL_LOOPING, bLoop);
                if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                    displayALError("alSourcei 0 AL_LOOPING : \n", error);
                break;
            case '0':
                        // Retrieve number of buffers in queue
                AL.alGetSourcei(source.get(0), AL.AL_BUFFERS_QUEUED, BuffersInQueue);
                // Retrieve number of processed buffers
                AL.alGetSourcei(source.get(0), AL.AL_BUFFERS_PROCESSED, BuffersProcessed);
                // Retrieve current buffer
                AL.alGetSourcei(source.get(0), AL.AL_BUFFER, Buffer);

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
        } while (ch != 'q');
        
        // Release resources
        AL.alSourceStop(source.get(0));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStop : ", error);
        
        AL.alDeleteSources(1, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
        
        AL.alGenSources(2,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        AL.alSourcef(source.get(0),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        
        AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_POSITION,source0Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        
        AL.alSourcefv(source.get(0),AL.AL_VELOCITY,source0Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_BUFFER, buffers.get(0));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        
        AL.alSourcei(source.get(0),AL.AL_LOOPING,AL.AL_TRUE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        
        
        AL.alSourcef(source.get(1),AL.AL_PITCH,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        
        AL.alSourcef(source.get(1),AL.AL_GAIN,1.0f);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        
        AL.alSourcefv(source.get(1),AL.AL_POSITION,source1Pos);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        
        AL.alSourcefv(source.get(1),AL.AL_VELOCITY,source1Vel);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        
        AL.alSourcei(source.get(1),AL.AL_BUFFER, buffers.get(1));
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        
        AL.alSourcei(source.get(1),AL.AL_LOOPING,AL.AL_TRUE);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
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
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            
            switch (ch) {
                case '1':
                    AL.alSourcePlay(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    AL.alSourcePlay(source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '3':
                    AL.alSourceStop(source.get(0));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop source 0 : \n", error);
                    break;
                case '4':
                    AL.alSourceStop(source.get(1));
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStop source 1 : \n", error);
                    break;
                case '5':
                    AL.alSourcef(source.get(0),AL.AL_GAIN,1.0f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 1.0 : \n", error);
                    break;
                case '6':
                    AL.alSourcef(source.get(0),AL.AL_GAIN,0.5f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 0.5 : \n", error);
                    break;
                case '7':
                    AL.alSourcef(source.get(0),AL.AL_GAIN,0.25f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 0.25 : \n", error);
                    break;
                case '8':
                    AL.alSourcef(source.get(0),AL.AL_GAIN,0.0f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourcef 0 AL_GAIN 0.0 : \n", error);
                    break;
                case 'a':
                    AL.alListenerf(AL.AL_GAIN,1.0f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alListenerf AL_GAIN 1.0 : \n", error);
                    break;
                case 'b':
                    AL.alListenerf(AL.AL_GAIN,0.5f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alListenerf AL_GAIN 0.5 : \n", error);
                    break;
                case 'c':
                    AL.alListenerf(AL.AL_GAIN,0.25f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alListenerf AL_GAIN 0.25 : \n", error);
                    break;
                case 'd':
                    AL.alListenerf(AL.AL_GAIN,0.0f);
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alListenerf AL_GAIN 0.0 : \n", error);
                    break;
            }
        } while (ch != 'q');
        
        // Reset & Release resources
        AL.alListenerf(AL.AL_GAIN,1.0f);
        AL.alSourceStopv(2,source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alSourceStop : ", error);
        
        AL.alDeleteSources(2, source);
        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
    }
    
    protected void i_StreamingTest() {
        System.out.println("i_StreamingTest");
        delay_ms(3000);
    }
    
    protected void i_MultipleSourcesTest() {
        int	numSources = 0;
        IntBuffer[] Sources = new IntBuffer[64];
        int	error;
        int	i;
        int	ch = -1;
        float radius;
        double anglestep;
        FloatBuffer pos = createFloatBuffer(3);
        
        // Generate as many sources as possible (up to 64)
        for (i = 0; i < 64; i++) {
            Sources[i] = createIntBuffer(1);
            AL.alGenSources(1, Sources[i]);
            if ((error = AL.alGetError()) != AL.AL_NO_ERROR) {
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
            AL.alSourcei(Sources[i].get(0), AL.AL_BUFFER, buffers.get(0));
            
            // Set position
            pos.put(0, (float)(Math.cos(anglestep*i) * radius));
            pos.put(1, 0.0f);
            pos.put(2, (float)(Math.sin(anglestep*i) * radius));
            
            AL.alSourcefv(Sources[i].get(0), AL.AL_POSITION, pos);
            
            System.out.print("Source " + i + " at " + 
                            pos.get(0) + ", " +
                            pos.get(1) + ", " +
                            pos.get(2) + "\n");
            
            // Enable looping
            AL.alSourcei(Sources[i].get(0), AL.AL_LOOPING, AL.AL_TRUE);
            
            pos.clear();
        }
        
        
        System.out.print("Press '1' to start playing Sources seperately\n");
        System.out.print("Press '2' to stop playing Sources seperately\n");
        System.out.print("Press 'Q' to quit\n");
        
        do {
            try {
              ch = Character.toLowerCase((char) System.in.read());
            } catch (IOException ioe) {
            }
            switch (ch) {
                case '1':
                    for (i = 0; i < numSources; i++) {
                        AL.alSourcePlay(Sources[i].get(0));
                        if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                            displayALError("alSourcePlay : ", error);
                        
                        // Delay a little
                        delay_ms(100);
                    }
                    break;
                case '2':
                    for(i=0; i<numSources;i++) {
                      AL.alSourceStop(Sources[i].get(0));
                    }
                    if ((error = AL.alGetError()) != AL.AL_NO_ERROR)
                        displayALError("alSourceStopv : ", error);
                    break;
            }
        } while (ch != 'q');
        
        // Delete the Sources
        for(i=0; i<numSources;i++) {
          AL.alDeleteSources(1, Sources[i]);
        }
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
