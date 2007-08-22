package org.lwjgl.d3d;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class IDirect3DDevice9Ex {
    private long iDirect3DDevice9Ex;
    //HRESULT CheckDeviceState(HWND hWindow);
    public long CheckDeviceState(long window){
        return nCheckDeviceState(iDirect3DDevice9Ex, window);
    }
    //HRESULT CheckResourceResidency(IDirect3DResource9 * pResourceArray, UINT32 NumResources);
    public long CheckResourceResidency(IDirect3DResource9 resourceArray, long numResources){
        return nCheckResourceResidency(iDirect3DDevice9Ex, resourceArray, numResources);
    }
    //HRESULT CreateDepthStencilSurfaceEx(UINT Width, UINT Height, D3DFORMAT Format, 
    //                                  D3DMULTISAMPLE_TYPE MultiSample, DWORD MultisampleQuality, BOOL Discard, 
    //                                  IDirect3DSurface9 **ppSurface, HANDLE *pSharedHandle, DWORD Usage);
    public long CreateDepthStencilSurfaceEx(int width, int height, int format, int multiSample, 
            long multisampleQuality, boolean discard, IDirect3DSurface9 surface, long sharedHandle, long usage){
        return nCreateDepthStencilSurfaceEx(iDirect3DDevice9Ex, width, height, format, multiSample, 
                multisampleQuality, discard, surface, sharedHandle, usage);
    }
    //HRESULT CreateOffscreenPlainSurfaceEx(UINT Width, UINT Height, D3DFORMAT Format, DWORD Pool, IDirect3DSurface9 **ppSurface, HANDLE *pSharedHandle, DWORD Usage);
    public long CreateOffscreenPlainSurfaceEx(int width, int height, int format, long pool, 
            IDirect3DSurface9 surface, long sharedHandle, long usage){
        return nCreateOffscreenPlainSurfaceEx(iDirect3DDevice9Ex, width, height, format, pool, surface, 
                sharedHandle, usage);
    }
    //HRESULT CreateRenderTargetEx(UINT Width, UINT Height, D3DFORMAT Format, D3DMULTISAMPLE_TYPE MultiSample, DWORD MultisampleQuality, BOOL Lockable, IDirect3DSurface9 **ppSurface, HANDLE *pSharedHandle, DWORD Usage);
    public long CreateRenderTargetEx(int width, int height, int format, int multiSample, long multisampleQuality, boolean lockable, IDirect3DSurface9 surface, long sharedHandle, long usage){
        return nCreateRenderTargetEx(iDirect3DDevice9Ex, width, height, format, multiSample, multisampleQuality, 
                lockable, surface, sharedHandle, usage);
    }
    //HRESULT ComposeRect(IDirect3DSurface9 *pSource, IDirect3DSurface9 *pDestination, IDirect3DVertexBuffer9 *pSrcRectDescriptors, UINT NumRects, IDirect3DVertexBuffer9 *pDstRectDescriptors, D3DCOMPOSEOP Operation, INT XOffset, INT YOffset);
    public long ComposeRect(IDirect3DSurface9 source, IDirect3DSurface9 destination, IDirect3DVertexBuffer9 srcRectDescriptors, int numRects, IDirect3DVertexBuffer9 dstRectDescriptors, int operation, int xOffset, int yOffset){
        return nComposeRect(iDirect3DDevice9Ex, source, destination, srcRectDescriptors, numRects, 
                dstRectDescriptors, operation, xOffset, yOffset);
    }
    //HRESULT GetDisplayModeEx(UINT  iSwapChain, D3DDISPLAYMODEEX *pMode, D3DDISPLAYROTATION *pRotation);
    public long GetDisplayModeEx(int swapChain, D3DDisplaymodeEx mode, int rotation){
        return nGetDisplayModeEx(iDirect3DDevice9Ex, swapChain, mode, rotation);
    }
    //HRESULT GetGPUThreadPriority(INT *pPriority);
    public long GetGPUThreadPriority(IntBuffer priority){
        return nGetGPUThreadPriority(iDirect3DDevice9Ex, priority);
    }
    //GetMaximumFrameLatency(UINT *pMaxLatency);
    public long GetMaximumFrameLatency(IntBuffer maxLatency){
        return nGetMaximumFrameLatency(iDirect3DDevice9Ex, maxLatency);
    }
    //HRESULT PresentEx(CONST RECT *pSourceRect, CONST RECT *pDestRect, HWND hDestWindowOverride, CONST RGNDATA *pDirtyRegion, DWORD dwFlags);
    public long PresentEx(Rectangle sourceRect, Rectangle destRect, long destWindowOverride, D3DRegionData dirtyRegion, long flags){
        return nPresentEx(iDirect3DDevice9Ex, sourceRect, destRect, destWindowOverride, dirtyRegion, flags);
    }
    //HRESULT ResetEx(D3DPRESENT_PARAMETERS* pPresentationParameters, D3DDISPLAYMODEEX* pFullscreenDisplayMode);
    public long ResetEx(D3DPresentParameters presentationParameters, D3DDisplaymodeEx fullscreenDisplayMode){
        return nResetEx(iDirect3DDevice9Ex, presentationParameters, fullscreenDisplayMode);
    }
    //HRESULT SetConvolutionMonoKernel(UINT Width, UINT Height, float *RowWeights, float *ColumnWeights);
    public long SetConvolutionMonoKernel(int width, int height, FloatBuffer rowWeights, FloatBuffer columnWeights){
        return nSetConvolutionMonoKernel(iDirect3DDevice9Ex, width, height, rowWeights, columnWeights);
    }
    //HRESULT SetGPUThreadPriority(INT pPriority);
    public long SetGPUThreadPriority(int priority){
        return nSetGPUThreadPriority(iDirect3DDevice9Ex, priority);
    }
    //HRESULT SetMaximumFrameLatency(UINT pMaxLatency);
    public long SetMaximumFrameLatency(int maxLatency){
        return nSetMaximumFrameLatency(iDirect3DDevice9Ex, maxLatency);
    }
    //HRESULT TestCooperativeLevel();
    public long TestCooperativeLevel(){
        return nTestCooperativeLevel(iDirect3DDevice9Ex);
    }
    //HRESULT WaitForVBlank(UINT SwapChainIndex);
    public long WaitForVBlank(int swapChainIndex){
        return nWaitForVBlank(iDirect3DDevice9Ex, swapChainIndex);
    }

    //natives
    private native long nCheckDeviceState(long iDirect3DDevice9Ex, long window);
    private native long nCheckResourceResidency(long iDirect3DDevice9Ex, IDirect3DResource9 resourceArray, long numResources);
    private native long nCreateDepthStencilSurfaceEx(long iDirect3DDevice9Ex, int width, int height, int format, int multiSample, long multisampleQuality, boolean discard, IDirect3DSurface9 surface, long sharedHandle, long usage);
    private native long nCreateOffscreenPlainSurfaceEx(long iDirect3DDevice9Ex, int width, int height, int format, long pool, IDirect3DSurface9 surface, long sharedHandle, long usage);
    private native long nCreateRenderTargetEx(long iDirect3DDevice9Ex, int width, int height, int format, int multiSample, long multisampleQuality, boolean lockable, IDirect3DSurface9 surface, long sharedHandle, long usage);
    private native long nComposeRect(long iDirect3DDevice9Ex, IDirect3DSurface9 source, IDirect3DSurface9 destination, IDirect3DVertexBuffer9 srcRectDescriptors, int numRects, IDirect3DVertexBuffer9 dstRectDescriptors, int operation, int xOffset, int yOffset);
    private native long nGetDisplayModeEx(long iDirect3DDevice9Ex, int swapChain, D3DDisplaymodeEx mode, int rotation);
    private native long nGetGPUThreadPriority(long iDirect3DDevice9Ex, IntBuffer priority);
    private native long nGetMaximumFrameLatency(long iDirect3DDevice9Ex, IntBuffer maxLatency);
    private native long nPresentEx(long iDirect3DDevice9Ex, Rectangle sourceRect, Rectangle destRect, long destWindowOverride, D3DRegionData dirtyRegion, long flags);
    private native long nResetEx(long iDirect3DDevice9Ex, D3DPresentParameters presentationParameters, D3DDisplaymodeEx fullscreenDisplayMode);
    private native long nSetConvolutionMonoKernel(long iDirect3DDevice9Ex, int width, int height, FloatBuffer rowWeights, FloatBuffer columnWeights);
    private native long nSetGPUThreadPriority(long iDirect3DDevice9Ex, int priority);
    private native long nSetMaximumFrameLatency(long iDirect3DDevice9Ex, int maxLatency);
    private native long nTestCooperativeLevel(long iDirect3DDevice9Ex);
    private native long nWaitForVBlank(long iDirect3DDevice9Ex, int swapChainIndex);
}
