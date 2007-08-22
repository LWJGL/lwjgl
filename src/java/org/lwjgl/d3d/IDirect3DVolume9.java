package org.lwjgl.d3d;

import java.nio.ByteBuffer;

public class IDirect3DVolume9 {
    private long iDirect3DVolume9;
    //HRESULT FreePrivateData(REFGUID refguid);
    public long FreePrivateData(GUID refguid){
        return nFreePrivateData(iDirect3DVolume9, refguid);
    }
    //HRESULT GetContainer(REFIID riid, void ** ppContainer);
    //TODO: find REFIID definition
    public long GetContainer(long riid, ByteBuffer container){
        return nGetContainer(iDirect3DVolume9, riid, container);
    }
    //HRESULT GetDesc(D3DVOLUME_DESC * pDesc);
    public long GetDesc(D3DVolumeDesc desc){
        return nGetDesc(iDirect3DVolume9, desc);
    }
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DVolume9, device);
    }
    //HRESULT GetPrivateData(REFGUID refguid, void * pData, DWORD * pSizeOfData);
    public long GetPrivateData(GUID refguid, ByteBuffer data, long sizeOfData){
        return nGetPrivateData(iDirect3DVolume9, refguid, data, sizeOfData);
    }
    //HRESULT LockBox(D3DLOCKED_BOX * pLockedVolume, CONST D3DBOX * pBox, DWORD Flags);
    public long LockBox(D3DLockedBox lockedVolume, D3DBox box, long flags){
        return nLockBox(iDirect3DVolume9, lockedVolume, box, flags);
    }
    //HRESULT SetPrivateData(REFGUID refguid, CONST void * pData, DWORD SizeOfData, DWORD Flags);
    public long SetPrivateData(GUID refguid, ByteBuffer data, long sizeOfData, long flags){
        return nSetPrivateData(iDirect3DVolume9, refguid, data, sizeOfData, flags);
    }
    //HRESULT UnlockBox();
    public long UnlockBox(){
        return nUnlockBox(iDirect3DVolume9);
    }
    
    //natives
    private native long nFreePrivateData(long iDirect3DVolume9, GUID refguid);
    private native long nGetContainer(long iDirect3DVolume9, long riid, ByteBuffer container);
    private native long nGetDesc(long iDirect3DVolume9, D3DVolumeDesc desc);
    private native long nGetDevice(long iDirect3DVolume9, IDirect3DDevice9 device);
    private native long nGetPrivateData(long iDirect3DVolume9, GUID refguid, ByteBuffer data, long sizeOfData);
    private native long nLockBox(long iDirect3DVolume9, D3DLockedBox lockedVolume, D3DBox box, long flags);
    private native long nSetPrivateData(long iDirect3DVolume9, GUID refguid, ByteBuffer data, long sizeOfData, long flags);
    private native long nUnlockBox(long iDirect3DVolume9);
}
