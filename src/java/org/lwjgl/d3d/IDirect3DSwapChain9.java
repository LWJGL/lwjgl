package org.lwjgl.d3d;

public class IDirect3DSwapChain9 {
    private long iDirect3DSwapChain9;
    //HRESULT GetBackBuffer(UINT BackBuffer, D3DBACKBUFFER_TYPE Type, IDirect3DSurface9 ** ppBackBuffer);
    public long GetBackBuffer(int backBuffer, int type, IDirect3DSurface9 backBufferSurface){
        return nGetBackBuffer(iDirect3DSwapChain9, backBuffer, type, backBufferSurface);
    }
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DSwapChain9, device);
    }
    //HRESULT GetDisplayMode(D3DDISPLAYMODE * pMode);
    public long GetDisplayMode(D3DDisplaymode mode){
        return nGetDisplayMode(iDirect3DSwapChain9, mode);
    }
    //HRESULT GetFrontBufferData(IDirect3DSurface9 * pDestSurface);
    public long GetFrontBufferData(IDirect3DSurface9 destSurface){
        return nGetFrontBufferData(iDirect3DSwapChain9, destSurface);
    }
    //HRESULT GetPresentParameters(D3DPRESENT_PARAMETERS* pPresentationParameters);
    public long GetPresentParameters(D3DPresentParameters presentationParameters){
        return nGetPresentParameters(iDirect3DSwapChain9, presentationParameters);
    }
    //HRESULT GetRasterStatus(D3DRASTER_STATUS * pRasterStatus);
    public long GetRasterStatus(D3DRasterStatus rasterStatus){
        return nGetRasterStatus(iDirect3DSwapChain9, rasterStatus);
    }
    //HRESULT Present(CONST RECT * pSourceRect, CONST RECT * pDestRect, HWND hDestWindowOverride, CONST RGNDATA * pDirtyRegion, DWORD dwFlags);
    public long Present(Rectangle sourceRect, Rectangle destRect, long destWindowOverride, RegionData dirtyRegion, long flags){
        return nPresent(iDirect3DSwapChain9, sourceRect, destRect, destWindowOverride, dirtyRegion, flags);
    }
    
    public long getIDirect3DSwapChain9() {
        return iDirect3DSwapChain9;
    }
    public void setIDirect3DSwapChain9(long direct3DSwapChain9) {
        iDirect3DSwapChain9 = direct3DSwapChain9;
    }
    //natives
    private native long nGetBackBuffer(long iDirect3DSwapChain9, int backBuffer, int type, IDirect3DSurface9 backBufferSurface);
    private native long nGetDevice(long iDirect3DSwapChain9, IDirect3DDevice9 device);
    private native long nGetDisplayMode(long iDirect3DSwapChain9, D3DDisplaymode mode);
    private native long nGetFrontBufferData(long iDirect3DSwapChain9, IDirect3DSurface9 destSurface);
    private native long nGetPresentParameters(long iDirect3DSwapChain9, D3DPresentParameters presentationParameters);
    private native long nGetRasterStatus(long iDirect3DSwapChain9, D3DRasterStatus rasterStatus);
    private native long nPresent(long iDirect3DSwapChain9, Rectangle sourceRect, Rectangle destRect, long destWindowOverride, RegionData dirtyRegion, long flags);
}
