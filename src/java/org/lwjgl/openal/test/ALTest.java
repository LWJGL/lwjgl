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
import org.lwjgl.Sys;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
    protected int[] buffers                 = new int[NUM_BUFFERS];
    
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
    
    /**
     * Creates an instance of ALTest
     */
    public ALTest() {
        super();
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
        
        ByteBuffer listenerPos = ByteBuffer.allocateDirect(12);
        listenerPos.order(ByteOrder.nativeOrder());
        listenerPos.putFloat(0.0f);
        listenerPos.putFloat(0.0f);
        listenerPos.putFloat(0.0f);
        
        ByteBuffer listenerVel = ByteBuffer.allocateDirect(12);
        listenerVel.order(ByteOrder.nativeOrder());
        listenerVel.putFloat(0.0f);
        listenerVel.putFloat(0.0f);
        listenerVel.putFloat(0.0f);
        
        ByteBuffer listenerOri = ByteBuffer.allocateDirect(24);
        listenerOri.order(ByteOrder.nativeOrder());
        listenerOri.putFloat(0.0f);
        listenerOri.putFloat(0.0f);
        listenerOri.putFloat(-1.0f);
        listenerOri.putFloat(0.0f);
        listenerOri.putFloat(1.0f);
        listenerOri.putFloat(0.0f);
        
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
        al.genBuffers(NUM_BUFFERS, buffers);
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
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy footsteps.wav data into AL Buffer 0
        al.bufferData(buffers[0], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 0 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload footsteps.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Load ding.wav
        data = alut.loadWAVFile("ding.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile ding.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy ding.wav data into AL Buffer 1
        al.bufferData(buffers[1], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 1 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload footsteps.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Load wave1.wav
        data = alut.loadWAVFile("wave1.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile wave1.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy wave1.wav data into AL Buffer 2
        al.bufferData(buffers[2], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 2 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload wave1.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Load Wave2.wav
        data = alut.loadWAVFile("Wave2.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile Wave2.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy Wave2.wav data into AL Buffer 3
        al.bufferData(buffers[3], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 3 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload Wave2.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Load wave3.wav
        data = alut.loadWAVFile("wave3.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile wave3.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy wave3.wav data into AL Buffer 4
        al.bufferData(buffers[4], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 4 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload wave3.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Load wave4.wav
        data = alut.loadWAVFile("wave4.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile wave4.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy wave4.wav data into AL Buffer 5
        al.bufferData(buffers[5], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 5 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload wave4.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Load stereo.wav
        data = alut.loadWAVFile("stereo.wav");
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutLoadWAVFile stereo.wav : ", error);
            // Delete Buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Copy stereo.wav data into AL Buffer 6
        al.bufferData(buffers[6], data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alBufferData buffer 6 : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        // Unload stereo.wav
        alut.unloadWAV(data.format, data.data, data.size, data.freq);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alutUnloadWAV : ", error);
            // Delete buffers
            al.deleteBuffers(NUM_BUFFERS, buffers);
            System.exit(-1);
        }
        
        do {
            System.out.print("\n\n\nAutomated Test Series:\n\n");
            System.out.print("A) Run Fully Automated Tests\n");
            System.out.print("B) Run Semi-Automated Tests\n");
            System.out.print("\nInteractive Tests:\n\n");
            System.out.print("1 Position Test\n");
            System.out.print("2 Looping Test\n");
            System.out.print("3 EAX 2.0 Test\n");
            System.out.print("4 Queue Test\n");
            System.out.print("5 Buffer Test\n");
            System.out.print("6 Frequency Test\n");
            System.out.print("7 Stereo Test\n");
            System.out.print("8 Gain Test\n");
            System.out.print("9 Streaming Test\n");
            System.out.print("0 Multiple Sources Test\n");
            
            System.out.print("\nQ to quit\n\n\n");
            
            try {
                ch = System.in.read();
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
                    i_EAXTest();
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
        System.out.println("fullAutoTests");
        delay_ms(3000);
    }
    
    protected void semiAutoTests() {
        System.out.println("semiAutoTests");
        delay_ms(3000);
    }
    
    protected void i_PositionTest() {
        int error;
        
        int[]   source = new int[2];
        int	ch = -1;
        
        ByteBuffer source0Pos = ByteBuffer.allocateDirect(12);
        source0Pos.order(ByteOrder.nativeOrder());
        source0Pos.putFloat(-2.0f);
        source0Pos.putFloat(0.0f);
        source0Pos.putFloat(2.0f);
        
        ByteBuffer source0Vel = ByteBuffer.allocateDirect(12);
        source0Vel.order(ByteOrder.nativeOrder());
        source0Vel.putFloat(0.0f);
        source0Vel.putFloat(0.0f);
        source0Vel.putFloat(0.0f);

        ByteBuffer source1Pos = ByteBuffer.allocateDirect(12);
        source1Pos.order(ByteOrder.nativeOrder());
        source1Pos.putFloat(2.0f);
        source1Pos.putFloat(0.0f);
        source1Pos.putFloat(-2.0f);

        ByteBuffer source1Vel = ByteBuffer.allocateDirect(12);
        source1Vel.order(ByteOrder.nativeOrder());
        source1Vel.putFloat(0.0f);
        source1Vel.putFloat(0.0f);
        source1Vel.putFloat(0.0f);        

        al.genSources(2, source);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 2 : ", error);
            return;
        }
        
        al.sourcef(source[0],AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        }
        
        al.sourcef(source[0],AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source[0],AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source[0],AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source[0],AL.BUFFER, buffers[1]);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        }
        
        al.sourcei(source[0],AL.LOOPING,AL.TRUE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING true: \n", error);
        }        
        
        al.sourcef(source[1],AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        }
        
        al.sourcef(source[1],AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source[1],AL.POSITION,Sys.getDirectBufferAddress(source1Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source[1],AL.VELOCITY,Sys.getDirectBufferAddress(source1Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source[1],AL.BUFFER, buffers[1]);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        }
        
        al.sourcei(source[1],AL.LOOPING,AL.FALSE);
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
                    al.sourcePlay(source[0]);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 0 : ", error);
                    break;
                case '2':
                    al.sourcePlay(source[1]);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourcePlay source 1 : ", error);
                    break;
                case '3':
                    al.sourceStop(source[0]);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop source 0 : ", error);
                    break;
                case '4':
                    al.sourceStop(source[1]);
                    if ((error = al.getError()) != AL.NO_ERROR)
                        displayALError("alSourceStop source 1 : ", error);
                    break;
            }
        } while (ch != 'Q');
        
        // Release resources
        al.sourceStopv(2, source);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStopv 2 : ", error);
        
        al.deleteSources(2, source);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 2 : ", error);
        
        return;
    }
    
    protected void i_LoopingTest() {
        int	error;
        int[] source = new int[2];
        int	ch = -1;
        int bLooping0 = AL.FALSE;
        int bLooping1 = AL.FALSE;
        
        ByteBuffer source0Pos = ByteBuffer.allocateDirect(12);
        source0Pos.order(ByteOrder.nativeOrder());
        source0Pos.putFloat(-2.0f);
        source0Pos.putFloat(0.0f);
        source0Pos.putFloat(-2.0f);
        
        ByteBuffer source0Vel = ByteBuffer.allocateDirect(12);
        source0Vel.order(ByteOrder.nativeOrder());
        source0Vel.putFloat(0.0f);
        source0Vel.putFloat(0.0f);
        source0Vel.putFloat(0.0f);

        ByteBuffer source1Pos = ByteBuffer.allocateDirect(12);
        source1Pos.order(ByteOrder.nativeOrder());
        source1Pos.putFloat(2.0f);
        source1Pos.putFloat(0.0f);
        source1Pos.putFloat(-2.0f);

        ByteBuffer source1Vel = ByteBuffer.allocateDirect(12);
        source1Vel.order(ByteOrder.nativeOrder());
        source1Vel.putFloat(0.0f);
        source1Vel.putFloat(0.0f);
        source1Vel.putFloat(0.0f);           
        
        // Clear Error Code
        al.getError();
        
        al.genSources(2,source);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alGenSources 1 : ", error);
            return;
        }
        
        al.sourcef(source[0],AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_PITCH : \n", error);
        }
        
        al.sourcef(source[0],AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 0 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source[0],AL.POSITION,Sys.getDirectBufferAddress(source0Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source[0],AL.VELOCITY,Sys.getDirectBufferAddress(source0Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 0 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source[0],AL.BUFFER, buffers[0]);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_BUFFER buffer 0 : \n", error);
        }
        
        al.sourcei(source[0],AL.LOOPING,AL.FALSE);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 0 AL_LOOPING false : \n", error);
        }
        
        al.sourcef(source[1],AL.PITCH,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_PITCH : \n", error);
        }
        
        al.sourcef(source[1],AL.GAIN,1.0f);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcef 1 AL_GAIN : \n", error);
        }
        
        al.sourcefv(source[1],AL.POSITION,Sys.getDirectBufferAddress(source1Pos));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_POSITION : \n", error);
        }
        
        al.sourcefv(source[1],AL.VELOCITY,Sys.getDirectBufferAddress(source1Vel));
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcefv 1 AL_VELOCITY : \n", error);
        }
        
        al.sourcei(source[1],AL.BUFFER, buffers[1]);
        if ((error = al.getError()) != AL.NO_ERROR) {
            displayALError("alSourcei 1 AL_BUFFER buffer 1 : \n", error);
        }
        
        al.sourcei(source[1],AL.LOOPING,AL.FALSE);
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
                    al.sourcePlay(source[0]);
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
                    al.sourcei(source[0], AL.LOOPING, bLooping0);
                    break;
                case '3':
                    al.sourcePlay(source[1]);
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
                    al.sourcei(source[1], AL.LOOPING, bLooping1);
                    break;
            }
        } while (ch != 'Q');
        
        System.out.print("\n");
        
        // Release resources
        al.sourceStop(source[0]);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alSourceStop source 1 : ", error);
        
        al.deleteSources(2, source);
        if ((error = al.getError()) != AL.NO_ERROR)
            displayALError("alDeleteSources 1 : ", error);
        
        return;
    }
    
    protected void i_EAXTest() {
        System.out.println("i_EAXTest");
        delay_ms(3000);
    }
    
    protected void i_QueueTest() {
        System.out.println("i_QueueTest");
        delay_ms(3000);
    }
    
    protected void i_BufferTest() {
        System.out.println("i_BufferTest");
        delay_ms(3000);
    }
    
    protected void i_FreqTest() {
        System.out.println("i_FreqTest");
        delay_ms(3000);
    }
    
    protected void i_StereoTest() {
        System.out.println("i_StereoTest");
        delay_ms(3000);
    }
    
    protected void i_GainTest() {
        System.out.println("i_GainTest");
        delay_ms(3000);
    }
    
    protected void i_StreamingTest() {
        System.out.println("i_StreamingTest");
        delay_ms(3000);
    }
    
    protected void i_MultipleSourcesTest() {
        System.out.println("i_MultipleSourcesTest");
        delay_ms(3000);
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