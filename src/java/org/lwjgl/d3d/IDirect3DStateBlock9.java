package org.lwjgl.d3d;

public class IDirect3DStateBlock9 {
    private long iDirect3DStateBlock9;
    //HRESULT Apply();
    public long Apply(){
        return nApply(iDirect3DStateBlock9);
    }
    //HRESULT Capture();
    public long Capture(){
        return nCapture(iDirect3DStateBlock9);
    }
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DStateBlock9, device);
    }
    
    public long getIDirect3DStateBlock9() {
        return iDirect3DStateBlock9;
    }
    public void setIDirect3DStateBlock9(long direct3DStateBlock9) {
        iDirect3DStateBlock9 = direct3DStateBlock9;
    }
    //natives
    private native long nApply(long iDirect3DStateBlock9);
    private native long nCapture(long iDirect3DStateBlock9);
    private native long nGetDevice(long iDirect3DStateBlock9, IDirect3DDevice9 device);
}
