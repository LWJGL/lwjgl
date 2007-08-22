package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class IDirect3DVertexShader9 {
    private long iDirect3DVertexShader9;
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DVertexShader9, device);
    }
    //HRESULT GetFunction(void* pData, UINT* pSizeOfData);
    public long GetFunction(ByteBuffer data, IntBuffer sizeOfData){
        return nGetFunction(iDirect3DVertexShader9, data, sizeOfData);
    }
    
    public long getIDirect3DVertexShader9() {
        return iDirect3DVertexShader9;
    }
    public void setIDirect3DVertexShader9(long direct3DVertexShader9) {
        iDirect3DVertexShader9 = direct3DVertexShader9;
    }

    //natives
    private native long nGetDevice(long iDirect3DVertexShader9, IDirect3DDevice9 device);
    private native long nGetFunction(long iDirect3DVertexShader9, ByteBuffer data, IntBuffer sizeOfData);
}
