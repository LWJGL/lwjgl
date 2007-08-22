package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DRasterStatus {
    public boolean InVBlank;
    public int ScanLine;
    
    private static final int D3D_RASTER_STATUS_BYTE_SIZE = 3;
    private ByteBuffer buffer;

    public D3DRasterStatus() {
        buffer = ByteBuffer.allocateDirect(D3D_RASTER_STATUS_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.put((byte)(InVBlank ? 1 : 0));
        buffer.putShort((short)ScanLine);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        InVBlank = buffer.get() == 1;
        ScanLine = buffer.getShort();
    }
}
