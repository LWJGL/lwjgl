package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class PaletteEntry {
    public byte peRed;
    public byte peGreen;
    public byte peBlue;
    public byte peFlags;
    private static final int D3D_VECTOR_BYTE_SIZE = 4;
    private ByteBuffer buffer;

    public PaletteEntry() {
        buffer = ByteBuffer.allocateDirect(D3D_VECTOR_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.put(peRed);
        buffer.put(peGreen);
        buffer.put(peBlue);
        buffer.put(peFlags);
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        peRed = buffer.get();
        peGreen = buffer.get();
        peBlue = buffer.get();
        peFlags = buffer.get();
    }
}
