package org.lwjgl.d3d;

public class IDirect3DBaseTexture9 {
    private long iDirect3DBaseTexture9;
    //VOID GenerateMipSubLevels();
    public void GenerateMipSubLevels(){
        nGenerateMipSubLevels(iDirect3DBaseTexture9);
    }
    //D3DTEXTUREFILTERTYPE GetAutoGenFilterType();
    public int GetAutoGenFilterType(){
        return nGetAutoGenFilterType(iDirect3DBaseTexture9);
    }
    //DWORD GetLevelCount();
    public long GetLevelCount(){
        return nGetLevelCount(iDirect3DBaseTexture9);
    }
    //DWORD GetLOD();
    public long GetLOD(){
        return nGetLOD(iDirect3DBaseTexture9);
    }
    //HRESULT SetAutoGenFilterType(D3DTEXTUREFILTERTYPE FilterType);
    public long SetAutoGenFilterType(int filterType){
        return nSetAutoGenFilterType(iDirect3DBaseTexture9, filterType);
    }
    //DWORD SetLOD(DWORD LODNew);
    public long SetLOD(long LODNew){
        return nSetLOD(iDirect3DBaseTexture9, LODNew);
    } 
    
    public long getIDirect3DBaseTexture9() {
        return iDirect3DBaseTexture9;
    }
    public void setIDirect3DBaseTexture9(long direct3DBaseTexture9) {
        iDirect3DBaseTexture9 = direct3DBaseTexture9;
    }

    //natives
    private native void nGenerateMipSubLevels(long iDirect3DBaseTexture9);
    private native int nGetAutoGenFilterType(long iDirect3DBaseTexture9);
    private native long nGetLevelCount(long iDirect3DBaseTexture9);
    private native long nGetLOD(long iDirect3DBaseTexture9);
    private native long nSetAutoGenFilterType(long iDirect3DBaseTexture9, int filterType);
    private native long nSetLOD(long iDirect3DBaseTexture9, long LODNew);
}
