package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class D3DCaps9 {
    public int DeviceType;                                                  //4 D3DDEVTYPE
    public int AdapterOrdinal;                                              //4 UINT
    public long Caps;                                                       //4 DWORD
    public long Caps2;                                                      //4 DWORD
    public long Caps3;                                                      //4 DWORD
    public long PresentationIntervals;                                      //4 DWORD
    public long CursorCaps;                                                 //4 DWORD
    public long DevCaps;                                                    //4 DWORD
    public long PrimitiveMiscCaps;                                          //4 DWORD
    public long RasterCaps;                                                 //4 DWORD
    public long ZCmpCaps;                                                   //4 DWORD
    public long SrcBlendCaps;                                               //4 DWORD
    public long DestBlendCaps;                                              //4 DWORD
    public long AlphaCmpCaps;                                               //4 DWORD
    public long ShadeCaps;                                                  //4 DWORD
    public long TextureCaps;                                                //4 DWORD
    public long TextureFilterCaps;                                          //4 DWORD
    public long CubeTextureFilterCaps;                                      //4 DWORD
    public long VolumeTextureFilterCaps;                                    //4 DWORD
    public long TextureAddressCaps;                                         //4 DWORD
    public long VolumeTextureAddressCaps;                                   //4 DWORD
    public long LineCaps;                                                   //4 DWORD
    public long MaxTextureWidth;                                            //4 DWORD
    public long MaxTextureHeight;                                           //4 DWORD
    public long MaxVolumeExtent;                                            //4 DWORD
    public long MaxTextureRepeat;                                           //4 DWORD
    public long MaxTextureAspectRatio;                                      //4 DWORD
    public long MaxAnisotropy;                                              //4 DWORD
    public float MaxVertexW;                                                //4 
    public float GuardBandLeft;                                             //4 
    public float GuardBandTop;                                              //4 
    public float GuardBandRight;                                            //4 
    public float GuardBandBottom;                                           //4 
    public float ExtentsAdjust;                                             //4 
    public long StencilCaps;                                                //4 DWORD
    public long FVFCaps;                                                    //4 DWORD
    public long TextureOpCaps;                                              //4 DWORD
    public long MaxTextureBlendStages;                                      //4 DWORD
    public long MaxSimultaneousTextures;                                    //4 DWORD
    public long VertexProcessingCaps;                                       //4 DWORD
    public long MaxActiveLights;                                            //4 DWORD
    public long MaxUserClipPlanes;                                          //4 DWORD
    public long MaxVertexBlendMatrices;                                     //4 DWORD
    public long MaxVertexBlendMatrixIndex;                                  //4 DWORD
    public float MaxPointSize;                                              //4 
    public long MaxPrimitiveCount;                                          //4 DWORD
    public long MaxVertexIndex;                                             //4 DWORD
    public long MaxStreams;                                                 //4 DWORD
    public long MaxStreamStride;                                            //4 DWORD
    public long VertexShaderVersion;                                        //4 DWORD
    public long MaxVertexShaderConst;                                       //4 DWORD
    public long PixelShaderVersion;                                         //4 DWORD
    public float PixelShader1xMaxValue;                                     //4 
    public long DevCaps2;                                                   //4 DWORD
    public float MaxNpatchTessellationLevel;                                //4
    public long Reserved5;                                                  //4 DWORD
    public int MasterAdapterOrdinal;                                        //4 UINT
    public int AdapterOrdinalInGroup;                                       //4 UINT
    public int NumberOfAdaptersInGroup;                                     //4 UINT
    public long DeclTypes;                                                  //4 DWORD
    public long NumSimultaneousRTs;                                         //4 DWORD
    public long StretchRectFilterCaps;                                      //4 DWORD
    public D3DVShaderCaps2_0 VS20Caps = new D3DVShaderCaps2_0();            //16 D3DVSHADERCAPS2_0
    public D3DPShaderCaps2_0 D3DPSHADERCAPS2_0 = new D3DPShaderCaps2_0();   //20 D3DPSHADERCAPS2_0
    public long VertexTextureFilterCaps;                                    //4 DWORD
    public long MaxVShaderInstructionsExecuted;                             //4 DWORD
    public long MaxPShaderInstructionsExecuted;                             //4 DWORD
    public long MaxVertexShader30InstructionSlots;                          //4 DWORD
    public long MaxPixelShader30InstructionSlots;                           //4 DWORD
    private static final int D3D_CAPS_BYTE_SIZE = 304;

    private ByteBuffer buffer;

    public D3DCaps9() {
        buffer = ByteBuffer.allocateDirect(D3D_CAPS_BYTE_SIZE);
        buffer.order(ByteOrder.nativeOrder());
        buffer.clear();
    }
    
    public ByteBuffer getEmptyBuffer() {
        buffer.rewind();
        
        return buffer;
    }

    public ByteBuffer getBuffer() {
        buffer.rewind();
        buffer.putInt(DeviceType);
        buffer.putInt(AdapterOrdinal);
        buffer.putInt((int)Caps);
        buffer.putInt((int)Caps2);
        buffer.putInt((int)Caps3);
        buffer.putInt((int)PresentationIntervals);
        buffer.putInt((int)CursorCaps);
        buffer.putInt((int)DevCaps);
        buffer.putInt((int)PrimitiveMiscCaps);
        buffer.putInt((int)RasterCaps);
        buffer.putInt((int)ZCmpCaps);
        buffer.putInt((int)SrcBlendCaps);
        buffer.putInt((int)DestBlendCaps);
        buffer.putInt((int)AlphaCmpCaps);
        buffer.putInt((int)ShadeCaps);
        buffer.putInt((int)TextureCaps);
        buffer.putInt((int)TextureFilterCaps);
        buffer.putInt((int)CubeTextureFilterCaps);
        buffer.putInt((int)VolumeTextureFilterCaps);
        buffer.putInt((int)TextureAddressCaps);
        buffer.putInt((int)VolumeTextureAddressCaps);
        buffer.putInt((int)LineCaps);
        buffer.putInt((int)MaxTextureWidth);
        buffer.putInt((int)MaxTextureHeight);
        buffer.putInt((int)MaxVolumeExtent);
        buffer.putInt((int)MaxTextureRepeat);
        buffer.putInt((int)MaxTextureAspectRatio);
        buffer.putInt((int)MaxAnisotropy);
        buffer.putFloat(MaxVertexW);
        buffer.putFloat(GuardBandLeft);
        buffer.putFloat(GuardBandTop);
        buffer.putFloat(GuardBandRight);
        buffer.putFloat(GuardBandBottom);
        buffer.putFloat(ExtentsAdjust);
        buffer.putInt((int)StencilCaps);
        buffer.putInt((int)FVFCaps);
        buffer.putInt((int)TextureOpCaps);
        buffer.putInt((int)MaxTextureBlendStages);
        buffer.putInt((int)MaxSimultaneousTextures);
        buffer.putInt((int)VertexProcessingCaps);
        buffer.putInt((int)MaxActiveLights);
        buffer.putInt((int)MaxUserClipPlanes);
        buffer.putInt((int)MaxVertexBlendMatrices);
        buffer.putInt((int)MaxVertexBlendMatrixIndex);
        buffer.putFloat(MaxPointSize);
        buffer.putInt((int)MaxPrimitiveCount);
        buffer.putInt((int)MaxVertexIndex);
        buffer.putInt((int)MaxStreams);
        buffer.putInt((int)MaxStreamStride);
        buffer.putInt((int)VertexShaderVersion);
        buffer.putInt((int)MaxVertexShaderConst);
        buffer.putInt((int)PixelShaderVersion);
        buffer.putFloat(PixelShader1xMaxValue);
        buffer.putInt((int)DevCaps2);
        buffer.putFloat(MaxNpatchTessellationLevel);
        buffer.putInt((int)Reserved5);
        buffer.putInt(MasterAdapterOrdinal);
        buffer.putInt(AdapterOrdinalInGroup);
        buffer.putInt(NumberOfAdaptersInGroup);
        buffer.putInt((int)DeclTypes);
        buffer.putInt((int)NumSimultaneousRTs);
        buffer.putInt((int)StretchRectFilterCaps);
        buffer.putInt((int)VS20Caps.Caps);
        buffer.putInt(VS20Caps.DynamicFlowControlDepth);
        buffer.putInt(VS20Caps.NumTemps);
        buffer.putInt(VS20Caps.StaticFlowControlDepth);
        buffer.putInt((int)D3DPSHADERCAPS2_0.Caps);
        buffer.putInt(D3DPSHADERCAPS2_0.DynamicFlowControlDepth);
        buffer.putInt(D3DPSHADERCAPS2_0.NumTemps);
        buffer.putInt(D3DPSHADERCAPS2_0.StaticFlowControlDepth);
        buffer.putInt(D3DPSHADERCAPS2_0.NumInstructionSlots);
        buffer.putInt((int)VertexTextureFilterCaps);
        buffer.putInt((int)MaxVShaderInstructionsExecuted);
        buffer.putInt((int)MaxPShaderInstructionsExecuted);
        buffer.putInt((int)MaxVertexShader30InstructionSlots);
        buffer.putInt((int)MaxPixelShader30InstructionSlots);
        buffer.rewind();
        
        return buffer;
    }
    
    public void setBuffer(ByteBuffer buffer) {
        buffer.rewind();
        DeviceType = buffer.getInt();
        AdapterOrdinal = buffer.getInt();
        Caps = buffer.getInt();
        Caps2 = buffer.getInt();
        Caps3 = buffer.getInt();
        PresentationIntervals = buffer.getInt();
        CursorCaps = buffer.getInt();
        DevCaps = buffer.getInt();
        PrimitiveMiscCaps = buffer.getInt();
        RasterCaps = buffer.getInt();
        ZCmpCaps = buffer.getInt();
        SrcBlendCaps = buffer.getInt();
        DestBlendCaps = buffer.getInt();
        AlphaCmpCaps = buffer.getInt();
        ShadeCaps = buffer.getInt();
        TextureCaps = buffer.getInt();
        TextureFilterCaps = buffer.getInt();
        CubeTextureFilterCaps = buffer.getInt();
        VolumeTextureFilterCaps = buffer.getInt();
        TextureAddressCaps = buffer.getInt();
        VolumeTextureAddressCaps = buffer.getInt();
        LineCaps = buffer.getInt();
        MaxTextureWidth = buffer.getInt();
        MaxTextureHeight = buffer.getInt();
        MaxVolumeExtent = buffer.getInt();
        MaxTextureRepeat = buffer.getInt();
        MaxTextureAspectRatio = buffer.getInt();
        MaxAnisotropy = buffer.getInt();
        MaxVertexW = buffer.getFloat();
        GuardBandLeft = buffer.getFloat();
        GuardBandTop = buffer.getFloat();
        GuardBandRight = buffer.getFloat();
        GuardBandBottom = buffer.getFloat();
        ExtentsAdjust = buffer.getFloat();
        StencilCaps = buffer.getInt();
        FVFCaps = buffer.getInt();
        TextureOpCaps = buffer.getInt();
        MaxTextureBlendStages = buffer.getInt();
        MaxSimultaneousTextures = buffer.getInt();
        VertexProcessingCaps = buffer.getInt();
        MaxActiveLights = buffer.getInt();
        MaxUserClipPlanes = buffer.getInt();
        MaxVertexBlendMatrices = buffer.getInt();
        MaxVertexBlendMatrixIndex = buffer.getInt();
        MaxPointSize = buffer.getFloat();
        MaxPrimitiveCount = buffer.getInt();
        MaxVertexIndex = buffer.getInt();
        MaxStreams = buffer.getInt();
        MaxStreamStride = buffer.getInt();
        VertexShaderVersion = buffer.getInt();
        MaxVertexShaderConst = buffer.getInt();
        PixelShaderVersion = buffer.getInt();
        PixelShader1xMaxValue = buffer.getFloat();
        DevCaps2 = buffer.getInt();
        MaxNpatchTessellationLevel = buffer.getFloat();
        Reserved5 = buffer.getInt();
        MasterAdapterOrdinal = buffer.getInt();
        AdapterOrdinalInGroup = buffer.getInt();
        NumberOfAdaptersInGroup = buffer.getInt();
        DeclTypes = buffer.getInt();
        NumSimultaneousRTs = buffer.getInt();
        StretchRectFilterCaps = buffer.getInt();
        VS20Caps.Caps = buffer.getInt();
        VS20Caps.DynamicFlowControlDepth = buffer.getInt();
        VS20Caps.NumTemps = buffer.getInt();
        VS20Caps.StaticFlowControlDepth = buffer.getInt();
        D3DPSHADERCAPS2_0.Caps = buffer.getInt();
        D3DPSHADERCAPS2_0.DynamicFlowControlDepth = buffer.getInt();
        D3DPSHADERCAPS2_0.NumTemps = buffer.getInt();
        D3DPSHADERCAPS2_0.StaticFlowControlDepth = buffer.getInt();
        D3DPSHADERCAPS2_0.NumInstructionSlots = buffer.getInt();
        VertexTextureFilterCaps = buffer.getInt();
        MaxVShaderInstructionsExecuted = buffer.getInt();
        MaxPShaderInstructionsExecuted = buffer.getInt();
        MaxVertexShader30InstructionSlots = buffer.getInt() & 0x00000000ffffffffL;
        MaxPixelShader30InstructionSlots = buffer.getInt();
    }
    
    public String toString() {
        return 
        "\n                       DeviceType = " + DeviceType +
        "\n                   AdapterOrdinal = " + AdapterOrdinal +
        "\n                             Caps = " + Caps +
        "\n                            Caps2 = " + Caps2 +
        "\n                            Caps3 = " + Caps3 +
        "\n            PresentationIntervals = " + PresentationIntervals +
        "\n                       CursorCaps = " + CursorCaps +
        "\n                          DevCaps = " + DevCaps +
        "\n                PrimitiveMiscCaps = " + PrimitiveMiscCaps +
        "\n                       RasterCaps = " + RasterCaps +
        "\n                         ZCmpCaps = " + ZCmpCaps +
        "\n                     SrcBlendCaps = " + SrcBlendCaps +
        "\n                    DestBlendCaps = " + DestBlendCaps +
        "\n                     AlphaCmpCaps = " + AlphaCmpCaps +
        "\n                        ShadeCaps = " + ShadeCaps +
        "\n                      TextureCaps = " + TextureCaps +
        "\n                TextureFilterCaps = " + TextureFilterCaps +
        "\n            CubeTextureFilterCaps = " + CubeTextureFilterCaps +
        "\n          VolumeTextureFilterCaps = " + VolumeTextureFilterCaps +
        "\n               TextureAddressCaps = " + TextureAddressCaps +
        "\n         VolumeTextureAddressCaps = " + VolumeTextureAddressCaps +
        "\n                         LineCaps = " + LineCaps +
        "\n                  MaxTextureWidth = " + MaxTextureWidth +
        "\n                 MaxTextureHeight = " + MaxTextureHeight +
        "\n                  MaxVolumeExtent = " + MaxVolumeExtent +
        "\n                 MaxTextureRepeat = " + MaxTextureRepeat +
        "\n            MaxTextureAspectRatio = " + MaxTextureAspectRatio +
        "\n                    MaxAnisotropy = " + MaxAnisotropy +
        "\n                       MaxVertexW = " + MaxVertexW +
        "\n                    GuardBandLeft = " + GuardBandLeft +
        "\n                     GuardBandTop = " + GuardBandTop +
        "\n                   GuardBandRight = " + GuardBandRight +
        "\n                  GuardBandBottom = " + GuardBandBottom +
        "\n                    ExtentsAdjust = " + ExtentsAdjust +
        "\n                      StencilCaps = " + StencilCaps +
        "\n                          FVFCaps = " + FVFCaps +
        "\n                    TextureOpCaps = " + TextureOpCaps +
        "\n            MaxTextureBlendStages = " + MaxTextureBlendStages +
        "\n          MaxSimultaneousTextures = " + MaxSimultaneousTextures +
        "\n             VertexProcessingCaps = " + VertexProcessingCaps +
        "\n                  MaxActiveLights = " + MaxActiveLights +
        "\n                MaxUserClipPlanes = " + MaxUserClipPlanes +
        "\n           MaxVertexBlendMatrices = " + MaxVertexBlendMatrices +
        "\n        MaxVertexBlendMatrixIndex = " + MaxVertexBlendMatrixIndex +
        "\n                     MaxPointSize = " + MaxPointSize +
        "\n                MaxPrimitiveCount = " + MaxPrimitiveCount +
        "\n                   MaxVertexIndex = " + MaxVertexIndex +
        "\n                       MaxStreams = " + MaxStreams +
        "\n                  MaxStreamStride = " + MaxStreamStride +
        "\n              VertexShaderVersion = " + VertexShaderVersion +
        "\n             MaxVertexShaderConst = " + MaxVertexShaderConst +
        "\n               PixelShaderVersion = " + PixelShaderVersion +
        "\n            PixelShader1xMaxValue = " + PixelShader1xMaxValue +
        "\n                         DevCaps2 = " + DevCaps2 +
        "\n       MaxNpatchTessellationLevel = " + MaxNpatchTessellationLevel +
        "\n                        Reserved5 = " + Reserved5 + 
        "\n             MasterAdapterOrdinal = " + MasterAdapterOrdinal +
        "\n            AdapterOrdinalInGroup = " + AdapterOrdinalInGroup +
        "\n          NumberOfAdaptersInGroup = " + NumberOfAdaptersInGroup +
        "\n                        DeclTypes = " + DeclTypes +
        "\n               NumSimultaneousRTs = " + NumSimultaneousRTs +
        "\n            StretchRectFilterCaps = " + StretchRectFilterCaps +
        VS20Caps.toString() +
        D3DPSHADERCAPS2_0.toString() +
        "\n          VertexTextureFilterCaps = " + VertexTextureFilterCaps +
        "\n   MaxVShaderInstructionsExecuted = " + MaxVShaderInstructionsExecuted +
        "\n   MaxPShaderInstructionsExecuted = " + MaxPShaderInstructionsExecuted +
        "\nMaxVertexShader30InstructionSlots = " + MaxVertexShader30InstructionSlots +
        "\n MaxPixelShader30InstructionSlots = " + MaxPixelShader30InstructionSlots;
    }
}