package org.lwjgl.d3d;

public class IDirect3DVolumeTexture9 {
    private long iDirect3DVolumeTexture9;
    //HRESULT AddDirtyBox(CONST D3DBOX * pDirtyBox);
    public long AddDirtyBox(D3DBox dirtyBox){
        return nAddDirtyBox(iDirect3DVolumeTexture9, dirtyBox);
    }
    //HRESULT GetLevelDesc(UINT Level, D3DVOLUME_DESC * pDesc);
    public long GetLevelDesc(int level, D3DVolumeDesc desc){
        return nGetLevelDesc(iDirect3DVolumeTexture9, level, desc);
    }
    //HRESULT GetVolumeLevel(UINT Level, IDirect3DVolume9 ** ppVolumeLevel);
    public long GetVolumeLevel(int level, IDirect3DVolume9 volumeLevel){
        return nGetVolumeLevel(iDirect3DVolumeTexture9, level, volumeLevel);
    }
    //HRESULT LockBox(UINT Level, D3DLOCKED_BOX * pLockedVolume, CONST D3DBOX * pBox, DWORD Flags);
    public long LockBox(int level, D3DLockedBox lockedVolume, D3DBox box, long flags){
        return nLockBox(iDirect3DVolumeTexture9, level, lockedVolume, box, flags);
    }
    //HRESULT UnlockBox(UINT Level);
    public long UnlockBox(int level){
        return nUnlockBox(iDirect3DVolumeTexture9, level);
    }
    
    public long getIDirect3DVolumeTexture9() {
        return iDirect3DVolumeTexture9;
    }
    public void setIDirect3DVolumeTexture9(long direct3DVolumeTexture9) {
        iDirect3DVolumeTexture9 = direct3DVolumeTexture9;
    }
    //natives
    private native long nAddDirtyBox(long iDirect3DVolumeTexture9, D3DBox dirtyBox);
    private native long nGetLevelDesc(long iDirect3DVolumeTexture9, int level, D3DVolumeDesc desc);
    private native long nGetVolumeLevel(long iDirect3DVolumeTexture9, int level, IDirect3DVolume9 volumeLevel);
    private native long nLockBox(long iDirect3DVolumeTexture9, int level, D3DLockedBox lockedVolume, D3DBox box, long flags);
    private native long nUnlockBox(long iDirect3DVolumeTexture9, int level);
}
