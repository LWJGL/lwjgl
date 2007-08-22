package org.lwjgl.d3d;

public class IDirect3DTexture9 {
    private long iDirect3DTexture9;
    //HRESULT AddDirtyRect(CONST RECT * pDirtyRect);
    public long AddDirtyRect(Rectangle dirtyRect){
        return nAddDirtyRect(iDirect3DTexture9, dirtyRect);
    }
    //HRESULT GetLevelDesc(UINT Level, D3DSURFACE_DESC * pDesc);
    public long GetLevelDesc(int level, D3DSurfaceDesc desc){
        return nGetLevelDesc(iDirect3DTexture9, level, desc);
    }
    //HRESULT GetSurfaceLevel(UINT Level, IDirect3DSurface9 ** ppSurfaceLevel);
    public long GetSurfaceLevel(int level, IDirect3DSurface9 surfaceLevel){
        return nGetSurfaceLevel(iDirect3DTexture9, level, surfaceLevel);
    }
    //HRESULT LockRect(UINT Level, D3DLOCKED_RECT * pLockedRect, CONST RECT * pRect, DWORD Flags);
    public long LockRect(int level, D3DLockedRect lockedRect, Rectangle rect, long flags){
        return nLockRect(iDirect3DTexture9, level, lockedRect, rect, flags);
    }
    //HRESULT UnlockRect(UINT Level);
    public long UnlockRect(int level){
        return nUnlockRect(iDirect3DTexture9, level);
    }
    
    //natives
    private native long nAddDirtyRect(long iDirect3DTexture9, Rectangle dirtyRect);
    private native long nGetLevelDesc(long iDirect3DTexture9, int level, D3DSurfaceDesc desc);
    private native long nGetSurfaceLevel(long iDirect3DTexture9, int level, IDirect3DSurface9 surfaceLevel);
    private native long nLockRect(long iDirect3DTexture9, int level, D3DLockedRect lockedRect, Rectangle rect, long flags);
    private native long nUnlockRect(long iDirect3DTexture9, int level);
}
