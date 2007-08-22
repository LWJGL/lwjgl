package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

//From RGNDATA
public class D3DRegionData {
    D3DRegionDataHeader regionHeaderData; 
    byte Buffer[] = new byte[1];
    private static final int RECTANGLE_BYTE_SIZE = 33;

    private ByteBuffer buffer;
    
    public D3DRegionData() {
        buffer = ByteBuffer.allocateDirect(RECTANGLE_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        buffer.clear();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.put(Buffer);

        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        regionHeaderData.setBuffer(buffer);
        buffer.get(Buffer);
    }
}
