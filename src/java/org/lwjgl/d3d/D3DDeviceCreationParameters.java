package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DDeviceCreationParameters {
    public int AdapterOrdinal;  //4 UINT
    public int DeviceType;      //4 D3DDEVTYPE
    public long hFocusWindow;   //4 HWND
    public long BehaviorFlags;  //4 DWORD
    private static final int D3D_CREATION_PARAMETERS_STATUS_BYTE_SIZE = 16;

    private ByteBuffer buffer;

    public D3DDeviceCreationParameters() {
        buffer = ByteBuffer.allocateDirect(D3D_CREATION_PARAMETERS_STATUS_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(AdapterOrdinal);
        buffer.putInt(DeviceType);
        buffer.putInt((int)hFocusWindow);
        buffer.putInt((int)BehaviorFlags);
        
        return buffer;
    }
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        AdapterOrdinal = buffer.getInt();
        DeviceType = buffer.getInt();
        hFocusWindow = buffer.getInt();
        BehaviorFlags = buffer.getInt();
    }
    public String toString() {
        return
        "\nAdapterOrdinal = " + AdapterOrdinal +
        "\n    DeviceType = " + DeviceType +
        "\n  hFocusWindow = " + hFocusWindow +
        "\n BehaviorFlags = " + BehaviorFlags;
    }
}
