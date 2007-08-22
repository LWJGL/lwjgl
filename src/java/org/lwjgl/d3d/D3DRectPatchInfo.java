package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DRectPatchInfo {
    public int StartVertexOffsetWidth;  //4 UINT
    public int StartVertexOffsetHeight; //4 UINT
    public int Width;                   //4 UINT
    public int Height;                  //4 UINT
    public int Stride;                  //4 UINT
    public int Basis;                   //4 D3DBASISTYPE
    public int Degree;                  //4 D3DDEGREETYPE

    private static final int D3D_RECT_PATCH_INFO_BYTE_SIZE = 28;

    private ByteBuffer buffer;

    public D3DRectPatchInfo() {
        buffer = ByteBuffer.allocateDirect(D3D_RECT_PATCH_INFO_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(StartVertexOffsetWidth);
        buffer.putInt(StartVertexOffsetHeight);
        buffer.putInt(Width);
        buffer.putInt(Height);
        buffer.putInt(Stride);
        buffer.putInt(Basis);
        buffer.putInt(Degree);
        
        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        StartVertexOffsetWidth = buffer.getInt();
        StartVertexOffsetHeight = buffer.getInt();
        Width = buffer.getInt();
        Height = buffer.getInt();
        Stride = buffer.getInt();
        Basis = buffer.getInt();
        Degree = buffer.getInt();
    }
    public String toString() {
        return 
        "\n StartVertexOffsetWidth = " + StartVertexOffsetWidth +
        "\nStartVertexOffsetHeight = " + StartVertexOffsetHeight +
        "\n                  Width = " + Width +
        "\n                 Height = " + Height +
        "\n                 Stride = " + Stride +
        "\n                  Basis = " + Basis +
        "\n                 Degree = " + Degree;
    }
}
