package org.lwjgl.d3d;

public class IDirect3D9Ex {
    private long iDirect3D9Ex;
    //HRESULT CreateDeviceEx(UINT Adapter, D3DDEVTYPE DeviceType, HWND hFocusWindow, DWORD BehaviorFlags, 
    //                      D3DPRESENT_PARAMETERS* pPresentationParameters, 
    //                      D3DDISPLAYMODEEX *pFullscreenDisplayMode, IDirect3DDevice9Ex **ppReturnedDeviceInterface);
    public long CreateDeviceEx(int adapter, int deviceType, long focusWindow, long behaviorFlags, 
            D3DPresentParameters presentationParameters, D3DDisplaymodeEx fullscreenDisplayMode, 
            IDirect3DDevice9Ex returnedDeviceInterface){
        return nCreateDeviceEx(iDirect3D9Ex, adapter, deviceType, focusWindow, behaviorFlags, 
                presentationParameters, fullscreenDisplayMode, returnedDeviceInterface);
    }
    //HRESULT EnumAdapterModesEx(UINT Adapter, CONST D3DDISPLAYMODEFILTER* pFilter, UINT Mode, D3DDISPLAYMODEEX* pMode);
    public long EnumAdapterModesEx(int adapter, D3DDisplaymodeFilter filter, int mode, D3DDisplaymodeEx displayMode){
        return nEnumAdapterModesEx(iDirect3D9Ex, adapter, filter, mode, displayMode);
    }
    //HRESULT GetAdapterDisplayModeEx(UINT Adapter, D3DDISPLAYMODEEX * pMode, D3DDISPLAYROTATION * pRotation);
    public long GetAdapterDisplayModeEx(int adapter, D3DDisplaymodeEx mode, int rotation){
        return nGetAdapterDisplayModeEx(iDirect3D9Ex, adapter, mode, rotation);
    } 
    //HRESULT GetAdapterLUID(UINT Adapter, LUID *pLUID);
    public long GetAdapterLUID(int adapter, LUID LUID){
        return nGetAdapterLUID(iDirect3D9Ex, adapter, LUID);
    }
    //UINT GetAdapterModeCountEx(UINT Adapter, CONST D3DDISPLAYMODEFILTER* pFilter);
    public int GetAdapterModeCountEx(int adapter, D3DDisplaymodeFilter filter){
        return nGetAdapterModeCountEx(iDirect3D9Ex, adapter, filter);
    }
    
    //natives
    private native long nCreateDeviceEx(long iDirect3D9Ex, int adapter, int deviceType, long focusWindow, long behaviorFlags, D3DPresentParameters presentationParameters, D3DDisplaymodeEx fullscreenDisplayMode, IDirect3DDevice9Ex returnedDeviceInterface);
    private native long nEnumAdapterModesEx(long iDirect3D9Ex, int adapter, D3DDisplaymodeFilter filter, int mode, D3DDisplaymodeEx displayMode);
    private native long nGetAdapterDisplayModeEx(long iDirect3D9Ex, int adapter, D3DDisplaymodeEx mode, int rotation);
    private native long nGetAdapterLUID(long iDirect3D9Ex, int adapter, LUID LUID);
    private native int nGetAdapterModeCountEx(long iDirect3D9Ex, int adapter, D3DDisplaymodeFilter filter);
}
