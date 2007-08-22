package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DClipStatus9 {
    public long ClipUnion;          //4 DWORD
    public long ClipIntersection;   //4 DWORD
    private static final int D3D_CLIP_STATUS_BYTE_SIZE = 8;

    private ByteBuffer buffer;

    public D3DClipStatus9() {
        buffer = ByteBuffer.allocateDirect(D3D_CLIP_STATUS_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt((int)ClipUnion);
        buffer.putInt((int)ClipIntersection);
        
        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        ClipUnion = buffer.getInt();
        ClipIntersection = buffer.getInt();
    }
    public String toString() {
        return
        "\n       ClipUnion = " + ClipUnion +
        "\nClipIntersection = " + ClipIntersection;
    }
}
