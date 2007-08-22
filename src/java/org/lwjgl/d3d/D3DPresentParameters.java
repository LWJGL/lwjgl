package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DPresentParameters {
    public int BackBufferWidth;             //4 UINT
    public int BackBufferHeight;            //4 UINT
    public int BackBufferFormat;            //4 D3DFORMAT
    public int BackBufferCount;             //4 UINT
    public int MultiSampleType;             //4 D3DMULTISAMPLE_TYPE
    public int MultiSampleQuality;          //4 DWORD
    public int SwapEffect;                  //4 D3DSWAPEFFECT
    public long hDeviceWindow;              //4 HWND
    public boolean Windowed;                //4
    public boolean EnableAutoDepthStencil;  //4
    public int AutoDepthStencilFormat;      //4 D3DFORMAT
    public long Flags;                      //4 DWORD
    public int FullScreen_RefreshRateInHz;  //4 UINT
    public int PresentationInterval;        //4 UINT
    private ByteBuffer buffer;
    private static final int D3D_PRESENT_PARAMETERS_BYTE_SIZE = 56;
    
    public D3DPresentParameters() {
        buffer = ByteBuffer.allocateDirect(D3D_PRESENT_PARAMETERS_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(BackBufferWidth);
        buffer.putInt(BackBufferHeight);
        buffer.putInt(BackBufferFormat);
        buffer.putInt(BackBufferCount);
        buffer.putInt(MultiSampleType);
        buffer.putInt(MultiSampleQuality);
        buffer.putInt(SwapEffect);
        buffer.putInt((int)hDeviceWindow);
        buffer.putInt(Windowed ? 1 : 0);
        buffer.putInt(EnableAutoDepthStencil ? 1 : 0);
        buffer.putInt(AutoDepthStencilFormat);
        buffer.putInt((int)Flags);
        buffer.putInt(FullScreen_RefreshRateInHz);
        buffer.putInt(PresentationInterval);
        
        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        BackBufferWidth = buffer.getInt();
        BackBufferHeight = buffer.getInt();
        BackBufferFormat = buffer.getInt();
        BackBufferCount = buffer.getInt();
        MultiSampleType = buffer.getInt();
        MultiSampleQuality = buffer.getInt();
        SwapEffect = buffer.getInt();
        hDeviceWindow = buffer.getInt();
        Windowed = buffer.getInt() == 1;
        EnableAutoDepthStencil = buffer.getInt() == 1;
        AutoDepthStencilFormat = buffer.getInt();
        Flags = buffer.getInt();
        FullScreen_RefreshRateInHz = buffer.getInt();
        PresentationInterval = buffer.getInt();
    }
    public String toString() {
        return
        "\n           BackBufferWidth = " + BackBufferWidth +
        "\n          BackBufferHeight = " + BackBufferHeight +
        "\n          BackBufferFormat = " + BackBufferFormat +
        "\n           BackBufferCount = " + BackBufferCount +
        "\n           MultiSampleType = " + MultiSampleType +
        "\n        MultiSampleQuality = " + MultiSampleQuality +
        "\n                SwapEffect = " + SwapEffect +
        "\n             hDeviceWindow = " + hDeviceWindow +
        "\n                  Windowed = " + Windowed +
        "\n    EnableAutoDepthStencil = " + EnableAutoDepthStencil +
        "\n    AutoDepthStencilFormat = " + AutoDepthStencilFormat +
        "\n                     Flags = " + Flags +
        "\nFullScreen_RefreshRateInHz = " + FullScreen_RefreshRateInHz +
        "\n      PresentationInterval = " + PresentationInterval;
    }
}