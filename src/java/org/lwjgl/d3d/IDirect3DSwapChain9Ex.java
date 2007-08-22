package org.lwjgl.d3d;

import java.nio.IntBuffer;

public class IDirect3DSwapChain9Ex {
    private long iDirect3DSwapChain9Ex;
    //HRESULT GetDisplayModeEx(D3DDISPLAYMODEEX *pMode, D3DDISPLAYROTATION *pRotation);
    public long GetDisplayModeEx(D3DDisplaymodeEx mode, int rotation){
        return nGetDisplayModeEx(iDirect3DSwapChain9Ex, mode, rotation);
    }
    //HRESULT GetLastPresentCount(UINT * pLastPresentCount);
    public long GetLastPresentCount(IntBuffer lastPresentCount){
        return nGetLastPresentCount(iDirect3DSwapChain9Ex, lastPresentCount);
    }
    //HRESULT GetPresentStatistics(D3DPRESENTSTATS * pPresentationStatistics);
    public long GetPresentStatistics(D3DPresentStats presentationStatistics){
        return nGetPresentStatistics(iDirect3DSwapChain9Ex, presentationStatistics);
    }
    
    //natives
    private native long nGetDisplayModeEx(long iDirect3DSwapChain9Ex, D3DDisplaymodeEx mode, int rotation);
    private native long nGetLastPresentCount(long iDirect3DSwapChain9Ex, IntBuffer lastPresentCount);
    private native long nGetPresentStatistics(long iDirect3DSwapChain9Ex, D3DPresentStats presentationStatistics);
}
