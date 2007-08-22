package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public class IDirect3DResource9 {
    private long iDirect3DResource9;
    //HRESULT FreePrivateData(REFGUID refguid);
    public long FreePrivateData(GUID refguid){
        return nFreePrivateData(iDirect3DResource9, refguid);
    }
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DResource9, device);
    }
    //DWORD GetPriority();
    public long GetPriority(){
        return nGetPriority(iDirect3DResource9);
    }
    //HRESULT GetPrivateData(REFGUID refguid, void * pData, DWORD * pSizeOfData);
    public long GetPrivateData(GUID refguid, ByteBuffer data, LongBuffer sizeOfData){
        return nGetPrivateData(iDirect3DResource9, refguid, data, sizeOfData);
    }
    //D3DRESOURCETYPE GetType();
    public int GetType(){
        return nGetType(iDirect3DResource9);
    }
    //
    public void PreLoad(){
        nPreLoad(iDirect3DResource9);
    }
    //DWORD SetPriority(DWORD PriorityNew);
    public long SetPriority(long priorityNew){
        return nSetPriority(iDirect3DResource9, priorityNew);
    }
    //HRESULT SetPrivateData(REFGUID refguid, CONST void * pData, DWORD SizeOfData, DWORD Flags);
    public long SetPrivateData(GUID refguid, ByteBuffer data, long sizeOfData, long flags){
        return nSetPrivateData(iDirect3DResource9, refguid, data, sizeOfData, flags);
    }
    
    //natives
    private native long nFreePrivateData(long iDirect3DResource9, GUID refguid); 
    private native long nGetDevice(long iDirect3DResource9, IDirect3DDevice9 device);
    private native long nGetPriority(long iDirect3DResource9);
    private native long nGetPrivateData(long iDirect3DResource9, GUID refguid, ByteBuffer data, LongBuffer sizeOfData);
    private native int nGetType(long iDirect3DResource9);
    private native void nPreLoad(long iDirect3DResource9);
    private native long nSetPriority(long iDirect3DResource9, long priorityNew);
    private native long nSetPrivateData(long iDirect3DResource9, GUID refguid, ByteBuffer data, long sizeOfData, long flags);
}
