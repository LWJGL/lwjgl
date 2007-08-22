package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DVertexElement9 {
    public long Stream;     //WORD
    public long Offset;     //WORD
    public byte Type;       //BYTE
    public byte Method;     //BYTE
    public byte Usage;      //BYTE
    public byte UsageIndex; //BYTE
    private static final int D3D_VERTEX_ELEMENT_BYTE_SIZE = 12;

    private ByteBuffer buffer;
    
    public D3DVertexElement9() {
        buffer = ByteBuffer.allocateDirect(D3D_VERTEX_ELEMENT_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt((int)Stream);
        buffer.putInt((int)Offset);
        buffer.put(Type);
        buffer.put(Method);
        buffer.put(Usage);
        buffer.put(UsageIndex);
        
        return buffer;
    }

    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        Stream = buffer.getInt();
        Offset = buffer.getInt();
        Type = buffer.get();
        Method = buffer.get();
        Usage = buffer.get();
        UsageIndex = buffer.get();
    }
    
    public String toString() {
        return 
        "\n    Stream = " + Stream +
        "\n    Offset = " + Offset +
        "\n      Type = " + Type +
        "\n    Method = " + Method +
        "\n     Usage = " + Usage +
        "\nUsageIndex = " + UsageIndex;
    }
}
