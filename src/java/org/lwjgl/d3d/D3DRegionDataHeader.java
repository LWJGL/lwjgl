package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

//From RGNDATAHEADER
public class D3DRegionDataHeader {
    public long dwSize;     //4 DWORD 
    public long iType;      //4 DWORD
    public long nCount;     //4 DWORD
    public long nRgnSize;   //4 DWORD
    Rectangle rcBound;   //16 RECT 
    private static final int RECTANGLE_BYTE_SIZE = 32;

    private ByteBuffer buffer;
    
    public D3DRegionDataHeader() {
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
        buffer.putInt((int)dwSize);
        buffer.putInt((int)iType);
        buffer.putInt((int)nCount);
        buffer.putInt((int)nRgnSize);
        buffer.put(rcBound.getBuffer());

        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        dwSize = buffer.getInt();
        iType = buffer.getInt();
        nCount = buffer.getInt();
        nRgnSize = buffer.getInt();
        rcBound.setBuffer(buffer.slice());
    }
}
