package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

public class D3DGammaRamp {
    private ByteBuffer redForNative = ByteBuffer.allocateDirect(512).order(ByteOrder.nativeOrder());    //2 WORD
    private ByteBuffer greenForNative = ByteBuffer.allocateDirect(512).order(ByteOrder.nativeOrder());  //2 WORD
    private ByteBuffer blueForNative = ByteBuffer.allocateDirect(512).order(ByteOrder.nativeOrder());   //2 WORD
    public ShortBuffer red = redForNative.asShortBuffer();
    public ShortBuffer green = greenForNative.asShortBuffer();
    public ShortBuffer blue = blueForNative.asShortBuffer();

    private static final int D3D_GAMMA_RAMP_BYTE_SIZE = 1536;
    private ByteBuffer buffer;

    public D3DGammaRamp() {
        buffer = ByteBuffer.allocateDirect(D3D_GAMMA_RAMP_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        redForNative.rewind();
        buffer.put(redForNative);
        greenForNative.rewind();
        buffer.put(greenForNative);
        blueForNative.rewind();
        buffer.put(blueForNative);
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        byte temp[] = new byte[512];
        buffer.get(temp);
        redForNative.put(temp);
        buffer.get(temp);
        greenForNative.put(temp);
        buffer.get(temp);
        blueForNative.put(temp);
    }
}
