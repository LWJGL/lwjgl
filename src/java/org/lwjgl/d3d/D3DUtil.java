package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DUtil {
    public final static void D3DXMatrixLookAtLH(D3DMatrix out, D3DVector eye, D3DVector at, D3DVector up) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixLookAtLH(buffer, eye.getBuffer(), at.getBuffer(), up.getBuffer());
        out.setBuffer(buffer);
    }
    
    public final static void D3DXMatrixPerspectiveFovLH(D3DMatrix out, float fovy, float aspect, float zn, float zf) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixPerspectiveFovLH(buffer, fovy, aspect, zn, zf);
        out.setBuffer(buffer);
    }
    
    public final static void D3DXMatrixIdentity(D3DMatrix out) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixIdentity(buffer);
        out.setBuffer(buffer);
    }
    
    public final static void D3DXMatrixScaling(D3DMatrix out, float sx, float sy, float sz) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixScaling(buffer, sx, sy, sz);
        out.setBuffer(buffer);
    }

    public final static void D3DXMatrixTranslation(D3DMatrix out, float x, float y ,float z) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixTranslation(buffer, x, y, z);
        out.setBuffer(buffer);
    }
    public final static void D3DXMatrixRotationX(D3DMatrix out, float angle) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixRotationX(buffer, angle);
        out.setBuffer(buffer);
    }
    public final static void D3DXMatrixRotationY(D3DMatrix out, float angle) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixRotationY(buffer, angle);
        out.setBuffer(buffer);
    }
    public final static void D3DXMatrixRotationZ(D3DMatrix out, float angle) {
        ByteBuffer buffer = out.getBuffer();
        nD3DXMatrixRotationZ(buffer, angle);
        out.setBuffer(buffer);
    }
    public final static D3DMatrix D3DXMatrixMultiply(D3DMatrix out, D3DMatrix m1, D3DMatrix m2) {
        D3DMatrix result = new D3DMatrix();
        ByteBuffer buffer = out.getBuffer();
        ByteBuffer resultBuffer = nD3DXMatrixMultiply(buffer, m1.getBuffer(), m2.getBuffer());
        resultBuffer.order(ByteOrder.nativeOrder());
        out.setBuffer(buffer);
        result.setBuffer(resultBuffer);
        
        return result;
    }
    //natives
    private final static native void nD3DXMatrixLookAtLH(ByteBuffer out, ByteBuffer eye, ByteBuffer at, ByteBuffer up);
    private final static native void nD3DXMatrixPerspectiveFovLH(ByteBuffer out, float fovy, float aspect, float zn, float zf);
    private final static native void nD3DXMatrixIdentity(ByteBuffer out);
    private final static native void nD3DXMatrixScaling(ByteBuffer out, float sx, float sy, float sz);
    private final static native void nD3DXMatrixTranslation(ByteBuffer out, float x, float y ,float z);
    private final static native void nD3DXMatrixRotationX(ByteBuffer out, float angle);
    private final static native void nD3DXMatrixRotationY(ByteBuffer out, float angle);
    private final static native void nD3DXMatrixRotationZ(ByteBuffer out, float angle);
    private final static native ByteBuffer nD3DXMatrixMultiply(ByteBuffer out, ByteBuffer m1, ByteBuffer m2);
}
