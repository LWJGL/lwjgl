package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IDirect3DPixelShader9 {
    private long iDirect3DPixelShader9;
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DPixelShader9, device);
    }
    //HRESULT GetFunction(void* pData, UINT* pSizeOfData);
    public long GetFunction(ByteBuffer data, IntBuffer sizeOfData){
        return nGetFunction(iDirect3DPixelShader9, data, sizeOfData);
    }
    
    public long getIDirect3DPixelShader9() {
        return iDirect3DPixelShader9;
    }
    public void setIDirect3DPixelShader9(long direct3DPixelShader9) {
        iDirect3DPixelShader9 = direct3DPixelShader9;
    }

    //natives
    private native long nGetDevice(long iDirect3DPixelShader9, IDirect3DDevice9 device);
    private native long nGetFunction(long iDirect3DPixelShader9, ByteBuffer data, IntBuffer sizeOfData);
}
