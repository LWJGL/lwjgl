package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DLight9 {
    public int Type;                //2 D3DLIGHTTYPE
    public D3DColorValue Diffuse;   //16 D3DCOLORVALUE
    public D3DColorValue Specular;  //16 D3DCOLORVALUE
    public D3DColorValue Ambient;   //16 D3DCOLORVALUE
    public D3DVector Position;      //12 D3DVECTOR
    public D3DVector Direction;     //12 D3DVECTOR
    public float Range;             //4
    public float Falloff;           //4
    public float Attenuation0;      //4
    public float Attenuation1;      //4
    public float Attenuation2;      //4
    public float Theta;             //4
    public float Phi;               //4

    private static final int D3D_LIGHT_BYTE_SIZE = 102;
    private ByteBuffer buffer;

    public D3DLight9() {
        buffer = ByteBuffer.allocateDirect(D3D_LIGHT_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putShort((short)Type);
        buffer.put(Diffuse.getBuffer());
        buffer.put(Specular.getBuffer());
        buffer.put(Ambient.getBuffer());
        buffer.put(Position.getBuffer());
        buffer.put(Direction.getBuffer());
        buffer.putFloat(Range);
        buffer.putFloat(Falloff);
        buffer.putFloat(Attenuation1);
        buffer.putFloat(Attenuation1);
        buffer.putFloat(Attenuation2);
        buffer.putFloat(Theta);
        buffer.putFloat(Phi);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        Type = buffer.getShort();
        byte temp[] = new byte[16];
        buffer.get(temp);
        Diffuse.setBuffer(Diffuse.getEmptyBuffer().put(temp));
        buffer.get(temp);
        Specular.setBuffer(Specular.getEmptyBuffer().put(temp));
        buffer.get(temp);
        Ambient.setBuffer(Ambient.getEmptyBuffer().put(temp));
        temp = new byte[12];
        buffer.get(temp);
        Position.setBuffer(Position.getEmptyBuffer().put(temp));
        buffer.get(temp);
        Direction.setBuffer(Direction.getEmptyBuffer().put(temp));
        Range = buffer.getFloat();
        Falloff = buffer.getFloat();
        Attenuation1 = buffer.getFloat();
        Attenuation1 = buffer.getFloat();
        Attenuation2 = buffer.getFloat();
        Theta = buffer.getFloat();
        Phi = buffer.getFloat();
    }
}
