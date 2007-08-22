package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DTriPatchInfo {
    public int StartVertexOffset;   //4 UINT
    public int NumVertices;         //4 UINT
    public int Basis;               //4 D3DBASISTYPE
    public int Degree;              //4 D3DDEGREETYPE
    private static final int D3D_TRI_PATCH_INFO_BYTE_SIZE = 16;

    private ByteBuffer buffer;

    public D3DTriPatchInfo() {
        buffer = ByteBuffer.allocateDirect(D3D_TRI_PATCH_INFO_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(StartVertexOffset);
        buffer.putInt(NumVertices);
        buffer.putInt(Basis);
        buffer.putInt(Degree);
        
        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        StartVertexOffset = buffer.getInt();
        NumVertices = buffer.getInt();
        Basis = buffer.getInt();
        Degree = buffer.getInt();
    }
    public String toString() {
        return 
        "\nStartVertexOffset = " + StartVertexOffset +
        "\n      NumVertices = " + NumVertices +
        "\n            Basis = " + Basis +
        "\n           Degree = " + Degree;
    }
}
