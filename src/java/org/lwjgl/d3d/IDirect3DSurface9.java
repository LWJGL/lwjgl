package org.lwjgl.d3d;

import java.nio.ByteBuffer;

public class IDirect3DSurface9 {
    private long iDirect3DSurface9;
    
    //HRESULT GetContainer(REFIID riid, void ** ppContainer);
    //TODO: find definition of REFIID
    public long GetContainer(long riid, ByteBuffer container){
        return nGetContainer(iDirect3DSurface9, riid, container);
    }
    //HRESULT GetDC(HDC* phdc);
    public long GetDC(long hdc){
        return nGetDC(iDirect3DSurface9, hdc);
    }
    //HRESULT GetDesc(D3DSURFACE_DESC * pDesc);
    public long GetDesc(D3DSurfaceDesc desc){
        return nGetDesc(iDirect3DSurface9, desc);
    }
    //HRESULT LockRect(D3DLOCKED_RECT * pLockedRect, CONST RECT * pRect, DWORD Flags);
    public long LockRect(D3DLockedRect lockedRect, Rectangle rect, long flags){
        return nLockRect(iDirect3DSurface9, lockedRect, rect, flags);
    }
    //HRESULT ReleaseDC(HDC hdc);
    public long ReleaseDC(long hdc){
        return nReleaseDC(iDirect3DSurface9, hdc);
    }
    //HRESULT UnlockRect();
    public long UnlockRect(){
        return nUnlockRect(iDirect3DSurface9);
    }
    
    public long getIDirect3DSurface9() {
        return iDirect3DSurface9;
    }
    public void setIDirect3DSurface9(long direct3DSurface9) {
        iDirect3DSurface9 = direct3DSurface9;
    }
    //natives
    private native long nGetContainer(long iDirect3DSurface9, long riid, ByteBuffer container);
    private native long nGetDC(long iDirect3DSurface9, long hdc);
    private native long nGetDesc(long iDirect3DSurface9, D3DSurfaceDesc desc);
    private native long nLockRect(long iDirect3DSurface9, D3DLockedRect lockedRect, Rectangle rect, long flags);
    private native long nReleaseDC(long iDirect3DSurface9, long hdc);
    private native long nUnlockRect(long iDirect3DSurface9);
}
