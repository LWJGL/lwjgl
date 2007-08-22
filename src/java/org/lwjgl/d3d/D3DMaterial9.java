package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DMaterial9 {
    public D3DColorValue Diffuse;   //16
    public D3DColorValue Ambient;   //16
    public D3DColorValue Specular;  //16
    public D3DColorValue Emissive;  //16
    public float Power;             //4

    private static final int D3D_MATERIAL_BYTE_SIZE = 68;
    private ByteBuffer buffer;

    public D3DMaterial9() {
        buffer = ByteBuffer.allocateDirect(D3D_MATERIAL_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.put(Diffuse.getBuffer());
        buffer.put(Ambient.getBuffer());
        buffer.put(Specular.getBuffer());
        buffer.put(Emissive.getBuffer());
        buffer.putFloat(Power);
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        byte temp[] = new byte[16];
        buffer.get(temp);
        Diffuse.setBuffer(Diffuse.getEmptyBuffer().put(temp));
        buffer.get(temp);
        Ambient.setBuffer(Ambient.getEmptyBuffer().put(temp));
        buffer.get(temp);
        Specular.setBuffer(Specular.getEmptyBuffer().put(temp));
        buffer.get(temp);
        Emissive.setBuffer(Emissive.getEmptyBuffer().put(temp));
        Power = buffer.getFloat();
    }
}
