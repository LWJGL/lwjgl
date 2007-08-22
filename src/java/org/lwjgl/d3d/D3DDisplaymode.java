package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DDisplaymode {
    public int Width;           //4 UINT
    public int Height;          //4 UINT
    public int RefreshRate;     //4 UINT
    public int Format;          //4 D3DFORMAT
    private static final int D3D_DISPLAYMODE_BYTE_SIZE = 16;
    private ByteBuffer buffer;

    public D3DDisplaymode() {
        buffer = ByteBuffer.allocateDirect(D3D_DISPLAYMODE_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(Width);
        buffer.putInt(Height);
        buffer.putInt(RefreshRate);
        buffer.putInt(Format);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        Width = buffer.getInt();
        Height = buffer.getInt();
        RefreshRate = buffer.getInt();
        Format = buffer.getInt();
    }
    
    public String toString() {
        return 
            "\n      width = " + Width + 
            "\n     height = " + Height +
            "\nrefreshRate = " + RefreshRate + 
            "\n     format = " + Format;
    }
}