package org.lwjgl.d3d;

public class IDirect3DCubeTexture9 {
    private long iDirect3DCubeTexture9;
    //HRESULT AddDirtyRect(D3DCUBEMAP_FACES FaceType, CONST RECT * pDirtyRect);
    public long AddDirtyRect(int faceType, Rectangle dirtyRect){
        return nAddDirtyRect(iDirect3DCubeTexture9, faceType, dirtyRect);
    }
    //HRESULT GetCubeMapSurface(D3DCUBEMAP_FACES FaceType, UINT Level, IDirect3DSurface9 ** ppCubeMapSurface);
    public long GetCubeMapSurface(int faceType, int level, IDirect3DSurface9 cubeMapSurface){
        return nGetCubeMapSurface(iDirect3DCubeTexture9, faceType, level, cubeMapSurface);
    }
    //HRESULT GetLevelDesc(UINT Level, D3DSURFACE_DESC * pDesc);
    public long GetLevelDesc(int level, D3DSsurfaceDesc desc){
        return nGetLevelDesc(iDirect3DCubeTexture9, level, desc);
    }
    //HRESULT LockRect(D3DCUBEMAP_FACES FaceType, UINT Level, D3DLOCKED_RECT * pLockedRect, CONST RECT * pRect, DWORD Flags);
    public long LockRect(int faceType, int level, D3DLockedRect lockedRect, Rectangle rect, long flags){
        return nLockRect(iDirect3DCubeTexture9, faceType, level, lockedRect, rect, flags);
    }
    //HRESULT UnlockRect(D3DCUBEMAP_FACES FaceType, UINT Level);
    public long UnlockRect(int faceType, int level){
        return nUnlockRect(iDirect3DCubeTexture9, faceType, level);
    }
    
    public long getIDirect3DCubeTexture9() {
        return iDirect3DCubeTexture9;
    }
    public void setIDirect3DCubeTexture9(long direct3DCubeTexture9) {
        iDirect3DCubeTexture9 = direct3DCubeTexture9;
    }
    //natives
    private native long nAddDirtyRect(long iDirect3DCubeTexture9, int faceType, Rectangle dirtyRect);
    private native long nGetCubeMapSurface(long iDirect3DCubeTexture9, int faceType, int level, IDirect3DSurface9 cubeMapSurface);
    private native long nGetLevelDesc(long iDirect3DCubeTexture9, int level, D3DSsurfaceDesc desc);
    private native long nLockRect(long iDirect3DCubeTexture9, int faceType, int level, D3DLockedRect lockedRect, Rectangle rect, long flags);
    private native long nUnlockRect(long iDirect3DCubeTexture9, int faceType, int level);
}
