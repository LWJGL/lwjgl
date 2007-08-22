package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class D3DMatrix {
    public float m[][] = new float[4][4];

    private static final int D3D_MATRIX_BYTE_SIZE = 64;
    private ByteBuffer buffer;
    
    public D3DMatrix() {
        buffer = ByteBuffer.allocateDirect(D3D_MATRIX_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        FloatBuffer temp = buffer.asFloatBuffer();
        for(int i=0;i<4;i++) {
            temp.put(m[i]);
        }
        
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        FloatBuffer temp = buffer.asFloatBuffer();
        for(int i=0;i<4;i++) {
            temp.get(m[i]);
        }
    }
}
