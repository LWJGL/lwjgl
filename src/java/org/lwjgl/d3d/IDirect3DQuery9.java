package org.lwjgl.d3d;

import java.nio.ByteBuffer;

public class IDirect3DQuery9 {
    private long iDirect3DQuery9;
    //HRESULT GetData(void* pData, DWORD dwSize, DWORD dwGetDataFlags);
    public long GetData(ByteBuffer data, long size, long getDataFlags){
        return nGetData(iDirect3DQuery9, data, size, getDataFlags);
    }
    //DWORD GetDataSize();
    public long GetDataSize(){
        return nGetDataSize(iDirect3DQuery9);
    }
    //HRESULT GetDevice(IDirect3DDevice9 ** pDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DQuery9, device);
    }
    //D3DQUERYTYPE GetType();
    public int GetType(){
        return nGetType(iDirect3DQuery9);
    }
    //HRESULT Issue(DWORD dwIssueFlags);
    public long Issue(long issueFlags){
        return nIssue(iDirect3DQuery9, issueFlags);
    }
    
    public long getIDirect3DQuery9() {
        return iDirect3DQuery9;
    }
    public void setIDirect3DQuery9(long direct3DQuery9) {
        iDirect3DQuery9 = direct3DQuery9;
    }
    //natives
    private native long nGetData(long iDirect3DQuery9, ByteBuffer data, long size, long getDataFlags);
    private native long nGetDataSize(long iDirect3DQuery9);
    private native long nGetDevice(long iDirect3DQuery9, IDirect3DDevice9 device);
    private native int nGetType(long iDirect3DQuery9);
    private native long nIssue(long iDirect3DQuery9, long issueFlags);
}
