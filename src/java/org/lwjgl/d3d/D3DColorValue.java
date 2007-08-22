package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DColorValue {
    public float r;
    public float g;
    public float b;
    public float a;

    private static final int D3D_COLOR_VALUE_BYTE_SIZE = 16;
    private ByteBuffer buffer;

    public D3DColorValue() {
        buffer = ByteBuffer.allocateDirect(D3D_COLOR_VALUE_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putFloat(r);
        buffer.putFloat(g);
        buffer.putFloat(b);
        buffer.putFloat(a);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        r = buffer.getFloat();
        g = buffer.getFloat();
        b = buffer.getFloat();
        a = buffer.getFloat();
    }
}
