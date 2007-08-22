package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Point {
    public long x;
    public long y;

    private static final int RECTANGLE_BYTE_SIZE = 8;
    private ByteBuffer buffer;
    
    public Point() {
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
        buffer.putInt((int)x);
        buffer.putInt((int)y);

        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        x = buffer.getInt();
        y = buffer.getInt();
    }
}
