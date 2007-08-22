package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

//From RECT
public class Rectangle {
    public long left; 
    public long top; 
    public long right; 
    public long bottom; 
    private static final int RECTANGLE_BYTE_SIZE = 16;

    private ByteBuffer buffer;
    
    public Rectangle() {
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
        buffer.putInt((int)left);
        buffer.putInt((int)top);
        buffer.putInt((int)right);
        buffer.putInt((int)bottom);

        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        left = buffer.getInt();
        top = buffer.getInt();
        right = buffer.getInt();
        bottom = buffer.getInt();
    }
    public String toString() {
        return
        "  left = " + left +
        "   top = " + top +
        " right = " + right +
        "bottom = " + bottom;
    }
}
