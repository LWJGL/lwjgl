package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;

import org.lwjgl.BufferChecks;

/**
 * 
 * @author Mark
 *
 */
public final class IDirect3D9 {
    private long iDirect3D9;
    
    /**
     * Constructor for creating a Direct3D9 interface.
     * 
     * @param iDirect3D9 A pointer to the native interface.
     */
    private IDirect3D9(long iDirect3D9) {
        this.iDirect3D9 = iDirect3D9;
    }
    /**
     * HRESULT CheckDepthStencilMatch(UINT Adapter, D3DDEVTYPE DeviceType, D3DFORMAT AdapterFormat, 
     *                                D3DFORMAT RenderTargetFormat, D3DFORMAT DepthStencilFormat);
     * 
     * @param adapter
     * @param deviceType
     * @param adapterFormat
     * @param renderTargetFormat
     * @param depthStencilFormat
     * @return
     */
    public final long checkDepthStencilMatch(int adapter, int deviceType, int adapterFormat,
            int renderTargetFormat, int depthStencilFormat){
        return nCheckDepthStencilMatch(iDirect3D9, adapter, deviceType, adapterFormat, renderTargetFormat, depthStencilFormat);
    }
    /**
     *HRESULT CheckDeviceFormat(UINT Adapter, D3DDEVTYPE DeviceType, D3DFORMAT AdapterFormat,
     *                          DWORD Usage, D3DRESOURCETYPE RType, D3DFORMAT CheckFormat);
     * 
     * @param adapter
     * @param deviceType
     * @param adapterFormat
     * @param usage
     * @param resoruceType
     * @param checkFormat
     * @return
     */
    public final long checkDeviceFormat(int adapter, int deviceType, int adapterFormat, 
            int usage, int resoruceType, int checkFormat){
        return nCheckDeviceFormat(iDirect3D9, adapter, deviceType, adapterFormat, usage, resoruceType, checkFormat);
    }
    /**
     *HRESULT CheckDeviceFormatConversion(UINT Adapter, D3DDEVTYPE DeviceType, D3DFORMAT SourceFormat, 
     *                                    D3DFORMAT TargetFormat);
     * 
     * @param adapter
     * @param deviceType
     * @param sourceFormat
     * @param targetFormat
     * @return
     */
    public final long checkDeviceFormatConversion(int adapter, int deviceType, int sourceFormat, int targetFormat){
        return nCheckDeviceFormatConversion(iDirect3D9, adapter, deviceType, sourceFormat, targetFormat);
    }
    /**
     * HRESULT CheckDeviceMultiSampleType(UINT Adapter, D3DDEVTYPE DeviceType, D3DFORMAT SurfaceFormat, 
     *                                    BOOL Windowed, D3DMULTISAMPLE_TYPE MultiSampleType, DWORD* pQualityLevels);
     * 
     * @param adapter
     * @param deviceType
     * @param surfaceFormat
     * @param windowed
     * @param multiSampleType
     * @param qualityLevels
     * @return
     */
    public final long checkDeviceMultiSampleType(int adapter, int deviceType, int surfaceFormat, 
            boolean windowed, int multiSampleType, IntBuffer qualityLevels){
        if(qualityLevels != null) {
            BufferChecks.checkBuffer(qualityLevels, 1);
        }
        
        return nCheckDeviceMultiSampleType(iDirect3D9, adapter, deviceType, surfaceFormat, windowed, multiSampleType, qualityLevels);
    }
    /**
     * HRESULT CheckDeviceType(UINT Adapter, D3DDEVTYPE DeviceType, D3DFORMAT DisplayFormat, 
     *                         D3DFORMAT BackBufferFormat, BOOL Windowed);
     * 
     * @param adapter
     * @param deviceType
     * @param displayFormat
     * @param backBufferFormat
     * @param windowed
     * @return
     */
    public final long checkDeviceType(int adapter, int deviceType, int displayFormat, 
            int backBufferFormat, boolean windowed){
        return nCheckDeviceType(iDirect3D9, adapter, deviceType, displayFormat, backBufferFormat, windowed);
    }
    /**
     * HRESULT CreateDevice(UINT Adapter, D3DDEVTYPE DeviceType, HWND hFocusWindow, DWORD BehaviorFlags, 
     *                      D3DPRESENT_PARAMETERS * pPresentationParameters, 
     *                      IDirect3DDevice9 ** ppReturnedDeviceInterface);
     * 
     * @param adapter
     * @param deviceType
     * @param focusWindow
     * @param behaviorFlags
     * @param presentationParameters
     * @param returnedDeviceInterface
     * @return
     */
    public final long createDevice(int adapter, int deviceType, long focusWindow, int behaviorFlags, 
            D3DPresentParameters presentationParameters, IDirect3DDevice9 returnedDeviceInterface){
        return nCreateDevice(iDirect3D9, adapter, deviceType, focusWindow, behaviorFlags, presentationParameters.getBuffer(), returnedDeviceInterface);
    }
    /**
     * HRESULT EnumAdapterModes(UINT Adapter, D3DFORMAT Format, UINT Mode, D3DDISPLAYMODE* pMode);
     * 
     * @param adapter
     * @param format
     * @param modeIndex
     * @param mode
     * @return
     */
    public final long enumAdapterModes(int adapter, int format, int modeIndex, D3DDisplaymode mode){
        return nEnumAdapterModes(iDirect3D9, adapter, format, modeIndex, mode.getEmptyBuffer());
    }
    /**
     * UINT GetAdapterCount();
     * 
     * @return
     */
    public final int getAdapterCount(){
        return nGetAdapterCount(iDirect3D9);
    }
    /**
     * HRESULT GetAdapterDisplayMode(UINT Adapter, D3DDISPLAYMODE * pMode);
     * 
     * @param adapter
     * @param mode
     * @return
     */
    public final long getAdapterDisplayMode(int adapter, D3DDisplaymode mode){
        ByteBuffer buffer = mode.getEmptyBuffer();
        long result = nGetAdapterDisplayMode(iDirect3D9, adapter, buffer);
        mode.setBuffer(buffer);
        
        return result;
    }
    /**
     * HRESULT GetAdapterIdentifier(UINT Adapter, DWORD Flags, D3DADAPTER_IDENTIFIER9 * pIdentifier);
     * 
     * @param adapter
     * @param flags
     * @param identifier
     * @return
     */
    public final long getAdapterIdentifier(int adapter, int flags, D3DAdapterIdentifier9 identifier){
        ByteBuffer buffer = identifier.getEmptyBuffer();
        long result = nGetAdapterIdentifier(iDirect3D9, adapter, flags, buffer);
        identifier.setBuffer(buffer);
        
        return result;
    }
    /**
     * UINT GetAdapterModeCount(UINT Adapter, D3DFORMAT Format);
     * 
     * @param adapter
     * @param format
     * @return
     */
    public final int getAdapterModeCount(int adapter, int format){
        return nGetAdapterModeCount(iDirect3D9, adapter, format);
    }
    /**
     * HMONITOR GetAdapterMonitor(UINT Adapter);
     * 
     * @param adapter
     * @return
     */
    public final long getAdapterMonitor(int adapter){
        return nGetAdapterMonitor(iDirect3D9, adapter);
    }
    /**
     * HRESULT GetDeviceCaps(UINT Adapter, D3DDEVTYPE DeviceType, D3DCAPS9 * pCaps);
     * 
     * @param adapter
     * @param deviceType
     * @param caps
     * @return
     */
    public final long getDeviceCaps(int adapter, int deviceType, D3DCaps9 caps){
        ByteBuffer buffer = caps.getEmptyBuffer();
        long result = nGetDeviceCaps(iDirect3D9, adapter, deviceType, buffer);
        caps.setBuffer(buffer);
        
        return result;
    }
    /**
     * HRESULT RegisterSoftwareDevice(void * pInitializeFunction);
     * 
     * @return
     */
    public final long registerSoftwareDevice(){
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
     * Release the native resources associated with this interface.
     * To get another interface, a new call to create needs to be made.
     *
     */
    public final void release() {
        nRelease(iDirect3D9);
    }
    /**
     * Create the Direct3D interface and store the pointer to the native interface in this class.
     * 
     * @return A IDirect3D9 class representing the native interface.
     */
    public final static IDirect3D9 create() {
        long tempiDirect3D9 = nCreate();
        
        return new IDirect3D9(tempiDirect3D9);
    }
    
    /**
     * Get the pointer to the native interface.
     * 
     * @return The pointer to the native interface.
     */
    public final long getIDirect3D9() {
        return iDirect3D9;
    }

    //native methods
    private final native long nCheckDepthStencilMatch(long iDirect3D9, int adapter, int deviceType, int adapterFormat, int renderTargetFormat, int depthStencilFormat); 
    private final native long nCheckDeviceFormat(long iDirect3D9, int adapter, int deviceType, int adapterFormat, int usage, int resoruceType, int checkFormat);
    private final native long nCheckDeviceFormatConversion(long iDirect3D9, int adapter, int deviceType, int sourceFormat, int targetFormat); 
    private final native long nCheckDeviceMultiSampleType(long iDirect3D9, int adapter, int deviceType, int surfaceFormat, boolean windowed, int multiSampleType, IntBuffer qualityLevels);
    private final native long nCheckDeviceType(long iDirect3D9, int adapter, int deviceType, int displayFormat, int backBufferFormat, boolean windowed);
    private final native long nCreateDevice(long iDirect3D9, int adapter, int deviceType, long focusWindow, int behaviorFlags, ByteBuffer presentationParameters, IDirect3DDevice9 returnedDeviceInterface);
    private final native long nEnumAdapterModes(long iDirect3D9, int adapter, int format, int modeIndex, ByteBuffer mode);
    private final native int nGetAdapterCount(long iDirect3D9);
    private final native long nGetAdapterDisplayMode(long iDirect3D9, int adapter, ByteBuffer mode); 
    private final native long nGetAdapterIdentifier(long iDirect3D9, int adapter, int flags, ByteBuffer identifier);
    private final native int nGetAdapterModeCount(long iDirect3D9, int adapter, int format);
    private final native long nGetAdapterMonitor(long iDirect3D9, int adapter);
    private final native long nGetDeviceCaps(long iDirect3D9, int adapter, int deviceType, ByteBuffer caps);
    private final native long nRegisterSoftwareDevice(long iDirect3D9);
    private final native void nRelease(long iDirect3D9);
    private static native long nCreate();
    
}
