package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class D3DViewport9 {
    public long X;      //4 DWORD
    public long Y;      //4 DWORD
    public long Width;  //4 DWORD
    public long Height; //4 DWORD
    public float MinZ;  //4
    public float MaxZ;  //4

    private static final int D3D_VIEWPORT_BYTE_SIZE = 24;
    private ByteBuffer buffer;
    
    public D3DViewport9() {
        buffer = ByteBuffer.allocateDirect(D3D_VIEWPORT_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt((int)X);
        buffer.putInt((int)Y);
        buffer.putInt((int)Width);
        buffer.putInt((int)Height);
        buffer.putFloat(MinZ);
        buffer.putFloat(MaxZ);
        
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        X = buffer.getInt();
        Y = buffer.getInt();
        Width = buffer.getInt();
        Height = buffer.getInt();
        MinZ = buffer.getFloat();
        MaxZ = buffer.getFloat();
    }
}
