package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DAdapterIdentifier9 {
    public byte Driver[] = new byte[Direct3DConstants.MAX_DEVICE_IDENTIFIER_STRING];//512 char [MAX_DEVICE_IDENTIFIER_STRING]
    public byte Description[] = new byte[Direct3DConstants.MAX_DEVICE_IDENTIFIER_STRING];//512 char [MAX_DEVICE_IDENTIFIER_STRING]
    public byte DeviceName[] = new byte[32];    //32 char [32]
    public long DriverVersion;                  //8 LARGE_INTEGER
    public long VendorId;                       //4 DWORD
    public long DeviceId;                       //4 DWORD
    public long SubSysId;                       //4 DWORD
    public long Revision;                       //4 DWORD
    public GUID DeviceIdentifier = new GUID();  //16 GUID
    public long WHQLLevel;                      //4 DWORD
    private static final int D3D_ADAPTER_IDENTIFIER_BYTE_SIZE = 1100;
    private ByteBuffer buffer;

    public D3DAdapterIdentifier9() {
        buffer = ByteBuffer.allocateDirect(D3D_ADAPTER_IDENTIFIER_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.put(Driver);
        buffer.put(Description);
        buffer.put(DeviceName);
        buffer.putLong(DriverVersion);
        buffer.putInt((int)VendorId);
        buffer.putInt((int)DeviceId);
        buffer.putInt((int)SubSysId);
        buffer.putInt((int)Revision);
        buffer.putInt((int)DeviceIdentifier.Data1);
        buffer.putShort(DeviceIdentifier.Data2);
        buffer.putShort(DeviceIdentifier.Data3);
        buffer.put(DeviceIdentifier.Data4);
        buffer.putInt((int)WHQLLevel);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        buffer.get(Driver);
        buffer.get(Description);
        buffer.get(DeviceName);
        DriverVersion = buffer.getLong();
        VendorId = buffer.getInt();
        DeviceId = buffer.getInt();
        SubSysId = buffer.getInt();
        Revision = buffer.getInt();
        DeviceIdentifier.Data1 = buffer.getInt();
        DeviceIdentifier.Data2 = buffer.getShort();
        DeviceIdentifier.Data3 = buffer.getShort();
        buffer.get(DeviceIdentifier.Data4);
        WHQLLevel = buffer.getInt();
    }
    
    public String toString() {
        return 
            "\n       Driver = " + new String(Driver) +
            "\n  Description = " + new String(Description) +
            "\n   DeviceName = " + new String(DeviceName) +
            "\nDriverVersion = " + DriverVersion +
            "\n     VendorId = " + VendorId +
            "\n     DeviceId = " + DeviceId +
            "\n     SubSysId = " + SubSysId +
            "\n     Revision = " + Revision + 
            "\n    WHQLLevel = " + WHQLLevel +
            DeviceIdentifier;
    }
}