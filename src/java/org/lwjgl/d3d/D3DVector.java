package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DVector {
    public float x;
    public float y;
    public float z;
    private static final int D3D_VECTOR_BYTE_SIZE = 12;
    private ByteBuffer buffer;

    public D3DVector() {
        buffer = ByteBuffer.allocateDirect(D3D_VECTOR_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putFloat(x);
        buffer.putFloat(y);
        buffer.putFloat(z);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        x = buffer.getFloat();
        y = buffer.getFloat();
        z = buffer.getFloat();
    }
}
