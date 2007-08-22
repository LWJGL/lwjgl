package org.lwjgl.d3d;

import java.nio.ByteBuffer;

public class IDirect3DIndexBuffer9 {
    private long iDirect3DIndexBuffer9;
    //HRESULT GetDesc(D3DINDEXBUFFER_DESC * pDesc);
    public long GetDesc(D3DIndexBufferDesc desc){
        return nGetDesc(iDirect3DIndexBuffer9, desc);
    }
    //HRESULT Lock(UINT OffsetToLock, UINT SizeToLock, VOID ** ppbData, DWORD Flags);
    public long Lock(int offsetToLock, int sizeToLock, ByteBuffer data, long flags){
        return nLock(iDirect3DIndexBuffer9, offsetToLock, sizeToLock, data, flags);
    }
    //HRESULT Unlock();
    public long Unlock(){
        return nUnlock(iDirect3DIndexBuffer9);
    }

    public long getIDirect3DIndexBuffer9() {
        return iDirect3DIndexBuffer9;
    }
    public void setIDirect3DIndexBuffer9(long direct3DIndexBuffer9) {
        iDirect3DIndexBuffer9 = direct3DIndexBuffer9;
    }

    //natives
    private native long nGetDesc(long iDirect3DIndexBuffer9, D3DIndexBufferDesc desc);
    private native long nLock(long iDirect3DIndexBuffer9, int offsetToLock, int sizeToLock, ByteBuffer data, long flags);
    private native long nUnlock(long iDirect3DIndexBuffer9);
}
