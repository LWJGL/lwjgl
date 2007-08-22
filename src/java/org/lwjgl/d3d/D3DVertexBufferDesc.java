package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DVertexBufferDesc {
    public int Format;  //4 D3DFORMAT
    public int Type;    //4 D3DRESOURCETYPE
    public long Usage;  //4 DWORD
    public int Pool;    //4 D3DPOOL
    public int Size;    //4 UINT
    public long FVF;    //4 DWORD

    private static final int D3D_VERTEX_BUFFER_DESC_BYTE_SIZE = 24;
    private ByteBuffer buffer;

    public D3DVertexBufferDesc() {
        buffer = ByteBuffer.allocateDirect(D3D_VERTEX_BUFFER_DESC_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(Format);
        buffer.putInt(Type);
        buffer.putInt((int)Usage);
        buffer.putInt(Pool);
        buffer.putInt(Size);
        buffer.putInt((int)FVF);
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        Format = buffer.getInt();
        Type = buffer.getInt();
        Usage = buffer.getInt();
        Pool = buffer.getInt();
        Size = buffer.getInt();
        FVF = buffer.getInt();
    }
}
