package org.lwjgl.d3d;

import java.nio.ByteBuffer;

public class IDirect3DVertexBuffer9 {
    private long iDirect3DVertexBuffer9;
    
    public IDirect3DVertexBuffer9() {
        create(this);
    }

    /**
     * HRESULT GetDesc(D3DVERTEXBUFFER_DESC * pDesc);
     * 
     * @param desc
     * @return
     */
    public final long GetDesc(D3DVertexBufferDesc desc) {
        ByteBuffer buffer = desc.getBuffer();
        long hResult = nGetDesc(iDirect3DVertexBuffer9, buffer);
        desc.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * HRESULT Lock(UINT OffsetToLock, UINT SizeToLock, VOID ** ppbData, DWORD Flags);
     * 
     * @param offsetToLock
     * @param sizeToLock
     * @param ata
     * @param flags
     * @return
     */
    public final long Lock(int offsetToLock, int sizeToLock, ByteBuffer ata, long flags){
        return nLock(iDirect3DVertexBuffer9, offsetToLock, sizeToLock, ata, flags, ata.capacity());
    }
    /**
     * HRESULT Unlock();
     * 
     * @return
     */
    public final long Unlock(){
        return nUnlock(iDirect3DVertexBuffer9);
    }
    
    public final long getIDirect3DVertexBuffer9() {
        return iDirect3DVertexBuffer9;
    }
    public final void setIDirect3DVertexBuffer9(long direct3DVertexBuffer9) {
        iDirect3DVertexBuffer9 = direct3DVertexBuffer9;
    }

    //natives
    private final native long nGetDesc(long iDirect3DVertexBuffer9, ByteBuffer desc);
    private final native long nLock(long iDirect3DVertexBuffer9, int offsetToLock, int sizeToLock, ByteBuffer ata, long flags, int bufferSize);
    private final native long nUnlock(long iDirect3DVertexBuffer9);
    private final native void create(IDirect3DVertexBuffer9 iDirect3DVertexBuffer9);
}
