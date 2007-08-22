package org.lwjgl.d3d;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import java.nio.ShortBuffer;

public class IDirect3DDevice9 {
    private long iDirect3DDevice9;
    /**
     * TODO: done
     * HRESULT BeginScene();
     * 
     * @return
     */
    public final long beginScene(){
        return nBeginScene(iDirect3DDevice9);
    }
    /**
     * TODO: test native
     * HRESULT BeginStateBlock();
     * 
     * @return
     */
    public final long beginStateBlock(){
        return nBeginStateBlock(iDirect3DDevice9);
    }
    /**
     * TODO: done
     * HRESULT Clear(DWORD Count, CONST D3DRECT * pRects, DWORD Flags, D3DCOLOR Color, float Z, DWORD Stencil);
     * 
     * @param count
     * @param rects
     * @param flags
     * @param color
     * @param z
     * @param stencil
     * @return
     */
    public final long clear(long count, LongBuffer rects, long flags, int color, float z, long stencil){
        return nClear(iDirect3DDevice9, count, rects, flags, color, z, stencil);
    }
    /**
     * TODO: not tested
     * HRESULT ColorFill(IDirect3DSurface9 * pSurface, CONST RECT * pRect, D3DCOLOR color);
     * 
     * @param surface
     * @param rect
     * @param color
     * @return
     */
    public final long colorFill(IDirect3DSurface9 surface, Rectangle rect, int color){
        return nColorFill(iDirect3DDevice9, surface.getIDirect3DSurface9(), rect.getBuffer(), color);
    }
    /**
     * TODO: not tested
     * HRESULT CreateAdditionalSwapChain(D3DPRESENT_PARAMETERS* pPresentationParameters, 
     *                                   IDirect3DSwapChain9** ppSwapChain);
     * 
     * @param presentationParameters
     * @param swapChain
     * @return
     */
    public final long createAdditionalSwapChain(D3DPresentParameters presentationParameters, 
            IDirect3DSwapChain9 swapChain){
        return nCreateAdditionalSwapChain(iDirect3DDevice9, presentationParameters.getBuffer(), swapChain);
    }
    /**
     * TODO: not tested
     * HRESULT CreateCubeTexture(UINT EdgeLength, UINT Levels, DWORD Usage, D3DFORMAT Format, D3DPOOL Pool,
     *                           IDirect3DCubeTexture9 ** ppCubeTexture, HANDLE* pSharedHandle);
     * 
     * @param edgeLength
     * @param levels
     * @param usage
     * @param format
     * @param pool
     * @param cubeTexture
     * @param sharedHandle
     * @return
     */
    public final long createCubeTexture(int edgeLength, int levels, long usage, int format, int pool, 
            IDirect3DCubeTexture9 cubeTexture, long sharedHandle){
        return nCreateCubeTexture(iDirect3DDevice9, edgeLength, levels, usage, format, pool, cubeTexture, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreateDepthStencilSurface(UINT Width, UINT Height, D3DFORMAT Format, 
     *                                   D3DMULTISAMPLE_TYPE MultiSample, DWORD MultisampleQuality, BOOL Discard, 
     *                                   IDirect3DSurface9** ppSurface, HANDLE* pSharedHandle);
     * 
     * @param width
     * @param height
     * @param format
     * @param multiSample
     * @param multiSampleQuality
     * @param discard
     * @param surface
     * @param sharedHandle
     * @return
     */
    public final long createDepthStencilSurface(int width, int height, int format, 
            int multiSample, long multiSampleQuality, boolean discard, IDirect3DSurface9 surface, long sharedHandle){
        return nCreateDepthStencilSurface(iDirect3DDevice9, width, height, format, 
                multiSample, multiSampleQuality, discard, surface, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreateIndexBuffer(UINT Length, DWORD Usage, D3DFORMAT Format, D3DPOOL Pool, 
     *                           IDirect3DIndexBuffer9** ppIndexBuffer, HANDLE* pSharedHandle);
     * 
     * @param length
     * @param usage
     * @param format
     * @param pool
     * @param indexBuffer
     * @param sharedHandle
     * @return
     */
    public final long createIndexBuffer(int length, long usage, int format, int pool, 
            IDirect3DIndexBuffer9 indexBuffer, long sharedHandle){
        return nCreateIndexBuffer(iDirect3DDevice9, length, usage, format, pool, indexBuffer, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreateOffscreenPlainSurface(UINT Width, UINT Height, D3DFORMAT Format, DWORD Pool, 
     *                                       IDirect3DSurface9** ppSurface, HANDLE* pSharedHandle);
     * 
     * @param width
     * @param height
     * @param format
     * @param pool
     * @param surface
     * @param sharedHandle
     * @return
     */
    public final long createOffscreenPlainSurface(int width, int height, int format, long pool, 
            IDirect3DSurface9 surface, long sharedHandle){
        return nCreateOffscreenPlainSurface(iDirect3DDevice9, width, height, format, pool, surface, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreatePixelShader(CONST DWORD * pFunction, IDirect3DPixelShader9** ppShader);
     * 
     * @param function
     * @param shader
     * @return
     */
    public final long createPixelShader(long function, IDirect3DPixelShader9 shader){
        return nCreatePixelShader(iDirect3DDevice9, function, shader);
    }
    /**
     * TODO: not tested
     * HRESULT CreateQuery(D3DQUERYTYPE Type, IDirect3DQuery9** ppQuery);
     * 
     * @param type
     * @param query
     * @return
     */
    public final long createQuery(int type, IDirect3DQuery9 query){
        return nCreateQuery(iDirect3DDevice9, type, query);
    }
    /**
     * TODO: not tested
     * HRESULT CreateRenderTarget(UINT Width, UINT Height, D3DFORMAT Format, D3DMULTISAMPLE_TYPE MultiSample, 
     *                           DWORD MultisampleQuality, BOOL Lockable, IDirect3DSurface9** ppSurface, 
     *                           HANDLE* pSharedHandle);
     * 
     * @param width
     * @param height
     * @param format
     * @param multiSample
     * @param multiSampleQuality
     * @param lockable
     * @param surface
     * @param sharedHandle
     * @return
     */
    public final long createRenderTarget(int width, int height, int format, 
            int multiSample, long multiSampleQuality, boolean lockable, 
            IDirect3DSurface9 surface, long sharedHandle){
        return nCreateRenderTarget(iDirect3DDevice9, width, height, format, multiSample, multiSampleQuality, lockable, surface, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreateStateBlock(D3DSTATEBLOCKTYPE Type, IDirect3DStateBlock9** ppSB);
     * 
     * @param type
     * @param stateBlock
     * @return
     */
    public final long createStateBlock(int type, IDirect3DStateBlock9 stateBlock){
        return nCreateStateBlock(iDirect3DDevice9, type, stateBlock);
    }
    /**
     * TODO: not tested
     * HRESULT CreateTexture(UINT Width, UINT Height, UINT Levels, DWORD Usage, D3DFORMAT Format, D3DPOOL Pool, 
     *                       IDirect3DTexture9** ppTexture, HANDLE* pSharedHandle);
     * 
     * @param width
     * @param height
     * @param levels
     * @param usage
     * @param format
     * @param pool
     * @param texture
     * @param sharedHandle
     * @return
     */
    public final long createTexture(int width, int height, int levels, long usage, int format, 
            int pool, IDirect3DTexture9 texture, long sharedHandle){
        return nCreateTexture(iDirect3DDevice9, width, height, levels, usage, format, pool, texture, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreateVertexBuffer(UINT Length, DWORD Usage, DWORD FVF, D3DPOOL Pool, 
     *                           IDirect3DVertexBuffer9** ppVertexBuffer, HANDLE* pSharedHandle);
     * 
     * @param length
     * @param usage
     * @param FVF
     * @param pool
     * @param vertexBuffer
     * @param sharedHandle
     * @return
     */
    public final long createVertexBuffer(int length, int usage, int FVF, int pool, 
            IDirect3DVertexBuffer9 vertexBuffer, long sharedHandle){
        return nCreateVertexBuffer(iDirect3DDevice9, length, usage, FVF, pool, vertexBuffer, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT CreateVertexDeclaration(CONST D3DVERTEXELEMENT9* pVertexElements, IDirect3DVertexDeclaration9** ppDecl);
     * 
     * @param vertexElements
     * @param decl
     * @return
     */
    public final long createVertexDeclaration(D3DVertexElement9 vertexElements, IDirect3DVertexDeclaration9 decl){
        return nCreateVertexDeclaration(iDirect3DDevice9, vertexElements.getBuffer(), decl);
    }
    /**
     * TODO: not tested
     * HRESULT CreateVertexShader(CONST DWORD * pFunction, IDirect3DVertexShader9** ppShader);
     * 
     * @param function
     * @param shader
     * @return
     */
    public final long createVertexShader(long function, IDirect3DVertexShader9 shader){
        return nCreateVertexShader(iDirect3DDevice9, function, shader);
    }
    /**
     * TODO: not tested
     * HRESULT CreateVolumeTexture(UINT Width, UINT Height, UINT Depth, UINT Levels, DWORD Usage, D3DFORMAT Format, 
     *                             D3DPOOL Pool, IDirect3DVolumeTexture9** ppVolumeTexture, HANDLE* pSharedHandle);
     * 
     * @param width
     * @param height
     * @param depth
     * @param levels
     * @param usage
     * @param format
     * @param pool
     * @param volumeTexture
     * @param sharedHandle
     * @return
     */
    public final long createVolumeTexture(int width, int height, int depth, int levels, long usage, int format, 
            int pool, IDirect3DVolumeTexture9 volumeTexture, long sharedHandle){
        return nCreateVolumeTexture(iDirect3DDevice9, width, height, depth, levels, usage, format, pool, volumeTexture, sharedHandle);
    }
    /**
     * TODO: not tested
     * HRESULT DeletePatch(UINT Handle);
     * 
     * @param handle
     * @return
     */
    public final long deletePatch(int handle){
        return nDeletePatch(iDirect3DDevice9, handle);
    }
    /**
     * TODO: not tested
     * HRESULT DrawIndexedPrimitive(D3DPRIMITIVETYPE Type, INT BaseVertexIndex, UINT MinIndex, 
     *                               UINT NumVertices, UINT StartIndex, UINT PrimitiveCount);
     * 
     * @param type
     * @param baseVertexIndex
     * @param minIndex
     * @param numVertices
     * @param startIndex
     * @param primitiveCount
     * @return
     */
    public final long drawIndexedPrimitive(int type, int baseVertexIndex, int minIndex, 
            int numVertices, int startIndex, int primitiveCount){
        return nDrawIndexedPrimitive(iDirect3DDevice9, type, baseVertexIndex, minIndex, numVertices, startIndex, primitiveCount);
    }
    /**
     * TODO: not tested
     * HRESULT DrawIndexedPrimitiveUP(D3DPRIMITIVETYPE PrimitiveType, UINT MinVertexIndex, UINT NumVertices, 
     *                               UINT PrimitiveCount, CONST void * pIndexData, D3DFORMAT IndexDataFormat, 
     *                               CONST void* pVertexStreamZeroData, UINT VertexStreamZeroStride);
     * 
     * @param primitiveType
     * @param minVertexIndex
     * @param numVertices
     * @param primitiveCount
     * @param indexData
     * @param indexDataFormat
     * @param vertexStreamZeroData
     * @param vertexStreamZeroStride
     * @return
     */
    public final long drawIndexedPrimitiveUP(int primitiveType, int minVertexIndex, int numVertices, 
            int primitiveCount, ByteBuffer indexData, int indexDataFormat, ByteBuffer vertexStreamZeroData, 
            int vertexStreamZeroStride){
        return nDrawIndexedPrimitiveUP(iDirect3DDevice9, primitiveType, minVertexIndex, numVertices, primitiveCount, indexData, 
                indexDataFormat, vertexStreamZeroData, vertexStreamZeroStride);
    }
    /**
     * TODO: not tested
     * HRESULT DrawPrimitive(D3DPRIMITIVETYPE PrimitiveType, UINT StartVertex, UINT PrimitiveCount);
     * 
     * @param primitiveType
     * @param startVertex
     * @param primitiveCount
     * @return
     */
    public final long drawPrimitive(int primitiveType, int startVertex, int primitiveCount){
        return nDrawPrimitive(iDirect3DDevice9, primitiveType, startVertex, primitiveCount);
    }
    /**
     * TODO: done
     * Ensure the the ByteBuffer vertexStreamZeroData is set to ByteOrder.nativeOrder() before putting
     * any data into it.
     * 
     * HRESULT DrawPrimitiveUP(D3DPRIMITIVETYPE PrimitiveType, UINT PrimitiveCount, 
     *                       CONST void* pVertexStreamZeroData, UINT VertexStreamZeroStride);
     * 
     * @param primitiveType
     * @param primitiveCount
     * @param vertexStreamZeroData
     * @param vertexStreamZeroStride
     * @return
     */
    public final long drawPrimitiveUP(int primitiveType, int primitiveCount, ByteBuffer vertexStreamZeroData, 
            int vertexStreamZeroStride){
        return nDrawPrimitiveUP(iDirect3DDevice9, primitiveType, primitiveCount, vertexStreamZeroData, vertexStreamZeroStride);
    }
    /**
     * TODO: not tested
     * HRESULT DrawRectPatch(UINT Handle, const float* pNumSegs, const D3DRECTPATCH_INFO* pRectPatchInfo);
     * 
     * @param handle
     * @param numSegs
     * @param rectPatchInfo
     * @return
     */
    public final long drawRectPatch(int handle, FloatBuffer numSegs, D3DRectPatchInfo rectPatchInfo){
        return nDrawRectPatch(iDirect3DDevice9, handle, numSegs, rectPatchInfo.getBuffer());
    }
    /**
     * TODO: not tested
     * HRESULT DrawTriPatch(UINT Handle, const float* pNumSegs, const D3DTRIPATCH_INFO* pTriPatchInfo);
     * 
     * @param handle
     * @param numSegs
     * @param triPatchInfo
     * @return
     */
    public final long drawTriPatch(int handle, FloatBuffer numSegs, D3DTriPatchInfo triPatchInfo){
        return nDrawTriPatch(iDirect3DDevice9, handle, numSegs, triPatchInfo.getBuffer());
    }
    /**
     * TODO: done
     * HRESULT EndScene();
     * 
     * @return
     */
    public final long endScene(){
        return nEndScene(iDirect3DDevice9);
    }
    /**
     * TODO: not tested
     * HRESULT EndStateBlock(IDirect3DStateBlock9 ** ppSB);
     * 
     * @param stateBlock
     * @return
     */
    public final long endStateBlock(IDirect3DStateBlock9 stateBlock){
        return nEndStateBlock(iDirect3DDevice9, stateBlock);
    }
    /**
     * TODO: not tested
     * HRESULT EvictManagedResources();
     * 
     * @return
     */
    public final long evictManagedResources(){
        return nEvictManagedResources(iDirect3DDevice9);
    }
    /**
     * TODO: not tested
     * UINT GetAvailableTextureMem();
     * 
     * @return
     */
    public final int getAvailableTextureMem(){
        return nGetAvailableTextureMem(iDirect3DDevice9);
    }
    /**
     * TODO: not tested
     * HRESULT GetBackBuffer(UINT  iSwapChain, UINT BackBuffer, D3DBACKBUFFER_TYPE Type, 
     *                       IDirect3DSurface9 ** ppBackBuffer);
     * 
     * @param swapChain
     * @param backBuffer
     * @param type
     * @param backBufferSurface
     * @return
     */
    public final long getBackBuffer(int swapChain, int backBuffer, int type, IDirect3DSurface9 backBufferSurface){
        return nGetBackBuffer(iDirect3DDevice9, swapChain, backBuffer, type, backBufferSurface);
    }
    /**
     * TODO: not tested
     * HRESULT GetClipPlane(DWORD Index, float * pPlane);
     * 
     * @param index
     * @param plane
     * @return
     */
    public final long getClipPlane(long index, FloatBuffer plane){
        return nGetClipPlane(iDirect3DDevice9, index, plane);
    }
    /**
     * TODO: not tested
     * HRESULT GetClipStatus(D3DCLIPSTATUS9 * pClipStatus);
     * 
     * @param clipStatus
     * @return
     */
    public final long getClipStatus(D3DClipStatus9 clipStatus){
        return nGetClipStatus(iDirect3DDevice9, clipStatus.getBuffer());
    }
    /**
     * TODO: not tested
     * HRESULT GetCreationParameters(D3DDEVICE_CREATION_PARAMETERS * pParameters);
     * 
     * @param parameters
     * @return
     */
    public final long getCreationParameters(D3DDeviceCreationParameters parameters){
        ByteBuffer buffer = parameters.getEmptyBuffer();
        long hResult = nGetCreationParameters(iDirect3DDevice9, buffer);
        parameters.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT GetCurrentTexturePalette(UINT * pPaletteNumber);
     * 
     * @param paletteNumber
     * @return
     */
    public final long getCurrentTexturePalette(IntBuffer paletteNumber){
        return nGetCurrentTexturePalette(iDirect3DDevice9, paletteNumber);
    }
    /**
     * TODO: not tested
     * HRESULT GetDepthStencilSurface(IDirect3DSurface9 ** ppZStencilSurface);
     * 
     * @param zStencilSurface
     * @return
     */
    public final long getDepthStencilSurface(IDirect3DSurface9 zStencilSurface){
        return nGetDepthStencilSurface(iDirect3DDevice9, zStencilSurface);
    }
    /**
     * TODO: not tested
     * HRESULT GetDeviceCaps(D3DCAPS9 * pCaps);
     * 
     * @param caps
     * @return
     */
    public final long getDeviceCaps(D3DCaps9 caps){
        ByteBuffer buffer = caps.getEmptyBuffer();
        long hResult = nGetDeviceCaps(iDirect3DDevice9, buffer);
        caps.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT GetDirect3D(IDirect3D9 ** ppD3D9);
     * 
     * @param D3D9
     * @return
     */
    public final long getDirect3D(IDirect3D9 D3D9){
        return nGetDirect3D(iDirect3DDevice9, D3D9);
    }
    /**
     * TODO: not tested
     * HRESULT GetDisplayMode(UINT  iSwapChain,D3DDISPLAYMODE * pMode);
     * 
     * @param swapChain
     * @param mode
     * @return
     */
    public final long getDisplayMode(int swapChain, D3DDisplaymode mode){
        ByteBuffer buffer = mode.getEmptyBuffer();
        long hResult = nGetDisplayMode(iDirect3DDevice9, swapChain, buffer);
        mode.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT GetFrontBufferData(UINT  iSwapChain, IDirect3DSurface9 * pDestSurface);
     * 
     * @param swapChain
     * @param destSurface
     * @return
     */
    public final long getFrontBufferData(int swapChain, IDirect3DSurface9 destSurface){
        return nGetFrontBufferData(iDirect3DDevice9, swapChain, destSurface.getIDirect3DSurface9());
    }
    /**
     * TODO: not tested
     * HRESULT GetFVF(DWORD * pFVF);
     * 
     * @param FVF
     * @return
     */
    public final long getFVF(IntBuffer FVF){
        return nGetFVF(iDirect3DDevice9, FVF);
    }
    /**
     * TODO: not tested
     * void GetGammaRamp(UINT iSwapChain, D3DGAMMARAMP * pRamp);
     * 
     * @param swapChain
     * @param ramp
     * @return
     */
    public void getGammaRamp(int swapChain, D3DGammaRamp ramp){
        ByteBuffer buffer = ramp.getBuffer();
        nGetGammaRamp(iDirect3DDevice9, swapChain, buffer);
        ramp.setBuffer(buffer);
    }
    /**
     * TODO: not tested
     * HRESULT GetIndices(IDirect3DIndexBuffer9 ** ppIndexData);
     * 
     * @param indexData
     * @param baseVertexIndex
     * @return
     */
    public final long getIndices(IDirect3DIndexBuffer9 indexData){
        return nGetIndices(iDirect3DDevice9, indexData);
    }
    /**
     * TODO: not tested
     * HRESULT GetLight(DWORD Index, D3DLIGHT9 * pLight);
     * 
     * @param index
     * @param light
     * @return
     */
    public final long getLight(long index, D3DLight9 light){
        return nGetLight(iDirect3DDevice9, index, light);
    }
    /**
     * TODO: not tested
     * 
     * TODO revisit to see if there is a better way to pass back a boolean value through pointers
     * HRESULT GetLightEnable(DWORD Index, BOOL * pEnable);
     * 
     * @param index
     * @param enable
     * @return
     */
    public final long getLightEnable(long index, ByteBuffer enable){
        return nGetLightEnable(iDirect3DDevice9, index, enable);
    }
    /**
     * TODO: not tested
     * HRESULT GetMaterial(D3DMATERIAL9 * pMaterial);
     * 
     * @param material
     * @return
     */
    public final long getMaterial(D3DMaterial9 material){
        ByteBuffer buffer = material.getBuffer();
        long hResult = nGetMaterial(iDirect3DDevice9, buffer);
        material.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * FLOAT GetNPatchMode();
     * 
     * @return
     */
    public float GetNPatchMode(){
        return nGetNPatchMode(iDirect3DDevice9);
    }
    /**
     * TODO: not tested
     * UINT GetNumberOfSwapChains();
     * 
     * @return
     */
    public final int getNumberOfSwapChains(){
        return nGetNumberOfSwapChains(iDirect3DDevice9);
    }
    /**
     * TODO: not tested
     * HRESULT GetPaletteEntries(UINT PaletteNumber, PALETTEENTRY * pEntries);
     * 
     * @param paletteNumber
     * @param entries
     * @return
     */
    public final long getPaletteEntries(int paletteNumber, PaletteEntry entries){
        ByteBuffer buffer = entries.getBuffer();
        long hResult = nGetPaletteEntries(iDirect3DDevice9, paletteNumber, buffer);
        entries.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT GetPixelShader(IDirect3DPixelShader9** ppShader);
     * 
     * @param shader
     * @return
     */
    public final long getPixelShader(IDirect3DPixelShader9 shader){
        return nGetPixelShader(iDirect3DDevice9, shader);
    }
    /**
     * TODO: not tested
     * 
     * TODO revisit to see if there is a better way to pass back a boolean value through pointers
     * HRESULT GetPixelShaderConstantB(UINT StartRegister, BOOL * pConstantData, UINT BoolCount);
     * 
     * @param startRegister
     * @param constantData
     * @param boolCount
     * @return
     */
    public final long getPixelShaderConstantB(int startRegister, ByteBuffer constantData, int boolCount){
        return nGetPixelShaderConstantB(iDirect3DDevice9, startRegister, constantData, boolCount);
    }
    /**
     * TODO: not tested
     * HRESULT GetPixelShaderConstantF(UINT StartRegister, float * pConstantData, UINT Vector4fCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4fCount
     * @return
     */
    public final long getPixelShaderConstantF(int startRegister, FloatBuffer constantData, int vector4fCount){
        return nGetPixelShaderConstantF(iDirect3DDevice9, startRegister, constantData, vector4fCount);
    }
    /**
     * TODO: not tested
     * HRESULT GetPixelShaderConstantI(UINT StartRegister, int * pConstantData, UINT Vector4iCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4iCount
     * @return
     */
    public final long getPixelShaderConstantI(int startRegister, ShortBuffer constantData, int vector4iCount){
        return nGetPixelShaderConstantI(iDirect3DDevice9, startRegister, constantData, vector4iCount);
    }
    /**
     * TODO: not tested
     * HRESULT GetRasterStatus(UINT  iSwapChain, D3DRASTER_STATUS * pRasterStatus);
     * 
     * @param swapChain
     * @param rasterStatus
     * @return
     */
    public final long getRasterStatus(int swapChain, D3DRasterStatus rasterStatus){
        ByteBuffer buffer = rasterStatus.getBuffer();
        long hResult = nGetRasterStatus(iDirect3DDevice9, swapChain, buffer);
        rasterStatus.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT GetRenderState(D3DRENDERSTATETYPE State, DWORD * pValue);
     * 
     * @param state
     * @param value
     * @return
     */
    public final long getRenderState(int state, IntBuffer value){
        return nGetRenderState(iDirect3DDevice9, state, value);
    }
    /**
     * TODO: not tested
     * HRESULT GetRenderTarget(DWORD RenderTargetIndex, IDirect3DSurface9 ** ppRenderTarget);
     * 
     * @param renderTargetIndex
     * @param renderTarget
     * @return
     */
    public final long getRenderTarget(long renderTargetIndex, IDirect3DSurface9 renderTarget){
        return nGetRenderTarget(iDirect3DDevice9, renderTargetIndex, renderTarget);
    }
    /**
     * TODO: not tested
     * HRESULT GetRenderTargetData(IDirect3DSurface9* pRenderTarget, IDirect3DSurface9* pDestSurface);
     * 
     * @param renderTarget
     * @param destSurface
     * @return
     */
    public final long getRenderTargetData(IDirect3DSurface9 renderTarget, IDirect3DSurface9 destSurface){
        return nGetRenderTargetData(iDirect3DDevice9, renderTarget.getIDirect3DSurface9(), 
                destSurface.getIDirect3DSurface9());
    }
    /**
     * TODO: not tested
     * HRESULT GetSamplerState(DWORD Sampler, D3DSAMPLERSTATETYPE Type, DWORD* pValue);
     * 
     * @param sampler
     * @param type
     * @param value
     * @return
     */
    public final long getSamplerState(long sampler, int type, LongBuffer value){
        return nGetSamplerState(iDirect3DDevice9, sampler, type, value);
    }
    /**
     * TODO: not tested
     * HRESULT GetScissorRect(RECT * pRect);
     * 
     * @param rect
     * @return
     */
    public final long getScissorRect(Rectangle rect){
        ByteBuffer buffer = rect.getBuffer();
        long hResult = nGetScissorRect(iDirect3DDevice9, buffer);
        rect.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * BOOL GetSoftwareVertexProcessing();
     * 
     * @return
     */
    public boolean GetSoftwareVertexProcessing(){
        return nGetSoftwareVertexProcessing(iDirect3DDevice9);
    }
    /**
     * TODO: not tested
     * HRESULT GetStreamSource(UINT StreamNumber, IDirect3DVertexBuffer9 ** ppStreamData, UINT * pOffsetInBytes, 
     *                      UINT * pStride);
     * 
     * @param streamNumber
     * @param streamData
     * @param offsetInBytes
     * @param stride
     * @return
     */
    public final long getStreamSource(int streamNumber, IDirect3DVertexBuffer9 streamData, ShortBuffer offsetInBytes, 
            ShortBuffer stride){
        return nGetStreamSource(iDirect3DDevice9, streamNumber, streamData, offsetInBytes, stride);
    }
    /**
     * TODO: not tested
     * HRESULT GetStreamSourceFreq(UINT StreamNumber, UINT* pDivider);
     * 
     * @param streamNumber
     * @param divider
     * @return
     */
    public final long getStreamSourceFreq(int streamNumber, ShortBuffer divider){
        return nGetStreamSourceFreq(iDirect3DDevice9, streamNumber, divider);
    }
    /**
     * TODO: not tested
     * HRESULT GetSwapChain(UINT  iSwapChain, IDirect3DSwapChain9 ** ppSwapChain);
     * 
     * @param swapChainOrdinal
     * @param swapChain
     * @return
     */
    public final long getSwapChain(int swapChainOrdinal, IDirect3DSwapChain9 swapChain){
        return nGetSwapChain(iDirect3DDevice9, swapChainOrdinal, swapChain);
    }
    /**
     * TODO: not tested
     * HRESULT GetTexture(DWORD Stage, IDirect3DBaseTexture9 ** ppTexture);
     * 
     * @param stage
     * @param texture
     * @return
     */
    public final long getTexture(long stage, IDirect3DBaseTexture9 texture){
        return nGetTexture(iDirect3DDevice9, stage, texture);
    }
    /**
     * TODO: not tested
     * HRESULT GetTextureStageState(DWORD Stage, D3DTEXTURESTAGESTATETYPE Type, DWORD * pValue);
     * 
     * @param stage
     * @param type
     * @param value
     * @return
     */
    public final long getTextureStageState(long stage, int type, IntBuffer value){
        return nGetTextureStageState(iDirect3DDevice9, stage, type, value);
    }
    /**
     * TODO: not tested
     * //HRESULT GetTransform(D3DTRANSFORMSTATETYPE State, D3DMATRIX * pMatrix);
     * 
     * @param state
     * @param matrix
     * @return
     */
    public final long getTransform(int state, D3DMatrix matrix){
        ByteBuffer buffer = matrix.getBuffer();
        long hResult = nGetTransform(iDirect3DDevice9, state, buffer);
        matrix.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT GetVertexDeclaration(IDirect3DVertexDeclaration9** ppDecl);
     * 
     * @param vertexDecleration
     * @return
     */
    public final long getVertexDeclaration(IDirect3DVertexDeclaration9 vertexDecleration){
        return nGetVertexDeclaration(iDirect3DDevice9, vertexDecleration);
    }
    /**
     * TODO: not tested
     * HRESULT GetVertexShader(IDirect3DVertexShader9** ppShader);
     * 
     * @param shader
     * @return
     */
    public final long getVertexShader(IDirect3DVertexShader9 shader){
        return nGetVertexShader(iDirect3DDevice9, shader);
    }
    /**
     * TODO: not tested
     * HRESULT GetVertexShaderConstantB(UINT StartRegister, BOOL * pConstantData, UINT BoolCount);
     * 
     * @param startRegister
     * @param constantData
     * @param boolCount
     * @return
     */
    public final long getVertexShaderConstantB(int startRegister, ByteBuffer constantData, int boolCount){
        return nGetVertexShaderConstantB(iDirect3DDevice9, startRegister, constantData, boolCount);
    }
    /**
     * TODO: not tested
     * HRESULT GetVertexShaderConstantF(UINT StartRegister, float * pConstantData, UINT Vector4fCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4fCount
     * @return
     */
    public final long getVertexShaderConstantF(int startRegister, FloatBuffer constantData, int vector4fCount){
        return nGetVertexShaderConstantF(iDirect3DDevice9, startRegister, constantData, vector4fCount);
    }
    /**
     * TODO: not tested
     * HRESULT GetVertexShaderConstantI(UINT StartRegister, int * pConstantData, UINT Vector4iCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4iCount
     * @return
     */
    public final long getVertexShaderConstantI(int startRegister, IntBuffer constantData, int vector4iCount){
        return nGetVertexShaderConstantI(iDirect3DDevice9, startRegister, constantData, vector4iCount);
    }
    /**
     * TODO: not tested
     * HRESULT GetViewport(D3DVIEWPORT9 * pViewport);
     * 
     * @param viewport
     * @return
     */
    public final long getViewport(D3DViewport9 viewport){
        ByteBuffer buffer = viewport.getBuffer();
        long hResult = nGetViewport(iDirect3DDevice9, buffer);
        viewport.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: not tested
     * HRESULT LightEnable(DWORD LightIndex, BOOL bEnable);
     * 
     * @param lightIndex
     * @param enable
     * @return
     */
    public final long lightEnable(long lightIndex, boolean enable){
        return nLightEnable(iDirect3DDevice9, lightIndex, enable);
    }
    /**
     * TODO: not tested
     * HRESULT MultiplyTransform(D3DTRANSFORMSTATETYPE State, CONST D3DMATRIX * pMatrix);
     * 
     * @param state
     * @param matrix
     * @return
     */
    public final long multiplyTransform(int state, D3DMatrix matrix){
        ByteBuffer buffer = matrix.getBuffer();
        long hResult = nMultiplyTransform(iDirect3DDevice9, state, buffer);
        matrix.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * TODO: fix native side
     * HRESULT Present(CONST RECT * pSourceRect, CONST RECT * pDestRect, HWND hDestWindowOverride, 
     *               CONST RGNDATA * pDirtyRegion);
     * 
     * @param sourceRect
     * @param destRect
     * @param destWindowOverride
     * @param dirtyRegion
     * @return
     */
    public final long present(Rectangle sourceRect, Rectangle destRect, long destWindowOverride, 
            D3DRegionData dirtyRegion){
        ByteBuffer sourceRectBuffer = null;
        if(sourceRect != null) {
            sourceRectBuffer = sourceRect.getBuffer();
        }
        ByteBuffer destRectBuffer = null;
        if(destRect != null) {
            destRectBuffer = destRect.getBuffer();
        }
        ByteBuffer dirtyRegionBuffer = null;
        if(dirtyRegion != null) {
            dirtyRegionBuffer = dirtyRegion.getBuffer();
        }
        return nPresent(iDirect3DDevice9, sourceRectBuffer, destRectBuffer, destWindowOverride, 
                dirtyRegionBuffer);
    }
    /**
     * HRESULT ProcessVertices(UINT SrcStartIndex, UINT DestIndex, UINT VertexCount, IDirect3DVertexBuffer9 * pDestBuffer, 
     *                           IDirect3DVertexDeclaration9* pVertexDecl, DWORD Flags);
     * 
     * @param srcStartIndex
     * @param destIndex
     * @param vertexCount
     * @param destBuffer
     * @param vertexDecl
     * @param flags
     * @return
     */
    public final long processVertices(int srcStartIndex, int destIndex, int vertexCount, 
            IDirect3DVertexBuffer9 destBuffer, IDirect3DVertexDeclaration9 vertexDecl, long flags){
        return nProcessVertices(iDirect3DDevice9, srcStartIndex, destIndex, vertexCount, 
                destBuffer.getIDirect3DVertexBuffer9(), vertexDecl.getIDirect3DVertexDeclaration9(), flags);
    }
    /**
     * HRESULT Reset(D3DPRESENT_PARAMETERS* pPresentationParameters);
     * 
     * @param presentationParameters
     * @return
     */
    public final long reset(D3DPresentParameters presentationParameters){
        ByteBuffer buffer = presentationParameters.getBuffer();
        long hResult = nReset(iDirect3DDevice9, presentationParameters);
        presentationParameters.setBuffer(buffer);
        
        return hResult;
    }
    /**
     * HRESULT SetClipPlane(DWORD Index, CONST float * pPlane);
     * 
     * @param index
     * @param plane
     * @return
     */
    public final long setClipPlane(long index, FloatBuffer plane){
        return nSetClipPlane(iDirect3DDevice9, index, plane);
    }
    /**
     * HRESULT SetClipStatus(CONST D3DCLIPSTATUS9 * pClipStatus);
     * 
     * @param clipStatus
     * @return
     */
    public final long setClipStatus(D3DClipStatus9 clipStatus){
        return nSetClipStatus(iDirect3DDevice9, clipStatus.getBuffer());
    }
    /**
     * HRESULT SetCurrentTexturePalette(UINT PaletteNumber);
     * 
     * @param paletteNumber
     * @return
     */
    public final long setCurrentTexturePalette(int paletteNumber){
        return nSetCurrentTexturePalette(iDirect3DDevice9, paletteNumber);
    }
    /**
     * void SetCursorPosition(INT X, INT Y, DWORD Flags);
     * 
     * @param x
     * @param y
     * @param flags
     */
    public final void SetCursorPosition(int x, int y, long flags){
        nSetCursorPosition(iDirect3DDevice9, x, y, flags);
    }
    /**
     * HRESULT SetCursorProperties(UINT XHotSpot, UINT YHotSpot, IDirect3DSurface9 * pCursorBitmap);
     * 
     * @param xHotSpot
     * @param yHotSpot
     * @param cursorBitmap
     * @return
     */
    public final long setCursorProperties(int xHotSpot, int yHotSpot, IDirect3DSurface9 cursorBitmap){
        return nSetCursorProperties(iDirect3DDevice9, xHotSpot, yHotSpot, cursorBitmap.getIDirect3DSurface9());
    }
    /**
     * HRESULT SetDepthStencilSurface(IDirect3DSurface9 * pNewZStencil);
     * 
     * @param newZStencil
     * @return
     */
    public final long setDepthStencilSurface(IDirect3DSurface9 newZStencil){
        return nSetDepthStencilSurface(iDirect3DDevice9, newZStencil.getIDirect3DSurface9());
    }
    /**
     * HRESULT SetDialogBoxMode(BOOL bEnableDialogs);
     * 
     * @param enableDialogs
     * @return
     */
    public final long setDialogBoxMode(boolean enableDialogs){
        return nSetDialogBoxMode(iDirect3DDevice9, enableDialogs);
    }
    /**
     * TODO: done
     * HRESULT SetFVF(DWORD FVF);
     * 
     * @param FVF
     * @return
     */
    public final long setFVF(long FVF){
        return nSetFVF(iDirect3DDevice9, FVF);
    }
    /**
     * void SetGammaRamp(UINT  iSwapChain, DWORD Flags, CONST D3DGAMMARAMP * pRamp);
     * 
     * @param swapChain
     * @param flags
     * @param ramp
     */
    public final void SetGammaRamp(int swapChain, long flags, D3DGammaRamp ramp){
        nSetGammaRamp(iDirect3DDevice9, swapChain, flags, ramp.getBuffer());
    }
    /**
     * HRESULT SetIndices(IDirect3DIndexBuffer9 * pIndexData);
     * 
     * @param indexData
     * @return
     */
    public final long setIndices(IDirect3DIndexBuffer9 indexData){
        return nSetIndices(iDirect3DDevice9, indexData.getIDirect3DIndexBuffer9());
    }
    /**
     * HRESULT SetLight(DWORD Index, CONST D3DLIGHT9 * pLight);
     * 
     * @param index
     * @param light
     * @return
     */
    public final long setLight(long index, D3DLight9 light){
        return nSetLight(iDirect3DDevice9, index, light.getBuffer());
    }
    /**
     * HRESULT SetMaterial(CONST D3DMATERIAL9 * pMaterial);
     * 
     * @param material
     * @return
     */
    public final long setMaterial(D3DMaterial9 material){
        return nSetMaterial(iDirect3DDevice9, material.getBuffer());
    }
    /**
     * HRESULT SetNPatchMode(float nSegments);
     * 
     * @param segments
     * @return
     */
    public final long setNPatchMode(float segments){
        return nSetNPatchMode(iDirect3DDevice9, segments);
    }
    /**
     * //HRESULT SetPaletteEntries(UINT PaletteNumber, CONST PALETTEENTRY * pEntries);
     * 
     * @param paletteNumber
     * @param entries
     * @return
     */
    public final long setPaletteEntries(int paletteNumber, PaletteEntry entries){
        return nSetPaletteEntries(iDirect3DDevice9, paletteNumber, entries.getBuffer());
    }
    /**
     * HRESULT SetPixelShader(IDirect3DPixelShader9* pShader);
     * 
     * @param shader
     * @return
     */
    public final long setPixelShader(IDirect3DPixelShader9 shader){
        return nSetPixelShader(iDirect3DDevice9, shader.getIDirect3DPixelShader9());
    }
    /**
     * HRESULT SetPixelShaderConstantB(UINT StartRegister, CONST BOOL * pConstantData, UINT BoolCount);
     * 
     * @param startRegister
     * @param constantData
     * @param boolCount
     * @return
     */
    public final long setPixelShaderConstantB(int startRegister, ByteBuffer constantData, int boolCount){
        return nSetPixelShaderConstantB(iDirect3DDevice9, startRegister, constantData, boolCount);
    }
    /**
     * HRESULT SetPixelShaderConstantF(UINT StartRegister, CONST float * pConstantData, UINT Vector4fCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4fCount
     * @return
     */
    public final long setPixelShaderConstantF(int startRegister, FloatBuffer constantData, int vector4fCount){
        return nSetPixelShaderConstantF(iDirect3DDevice9, startRegister, constantData, vector4fCount);
    }
    /**
     * HRESULT SetPixelShaderConstantI(UINT StartRegister, CONST int * pConstantData, UINT Vector4iCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4iCount
     * @return
     */
    public final long setPixelShaderConstantI(int startRegister, IntBuffer constantData, int vector4iCount){
        return nSetPixelShaderConstantI(iDirect3DDevice9, startRegister, constantData, vector4iCount);
    }
    /**
     * HRESULT SetRenderState(D3DRENDERSTATETYPE State, DWORD Value);
     * 
     * @param state
     * @param value
     * @return
     */
    public final long setRenderState(int state, long value){
        return nSetRenderState(iDirect3DDevice9, state, value);
    }
    /**
     * HRESULT SetRenderTarget(DWORD RenderTargetIndex, IDirect3DSurface9 * pRenderTarget);
     * 
     * @param renderTargetIndex
     * @param renderTarget
     * @return
     */
    public final long setRenderTarget(long renderTargetIndex, IDirect3DSurface9 renderTarget){
        return nSetRenderTarget(iDirect3DDevice9, renderTargetIndex, renderTarget.getIDirect3DSurface9());
    }
    /**
     * HRESULT SetSamplerState(DWORD Sampler, D3DSAMPLERSTATETYPE Type, DWORD Value);
     * 
     * @param sampler
     * @param type
     * @param value
     * @return
     */
    public final long setSamplerState(long sampler, int type, long value){
        return nSetSamplerState(iDirect3DDevice9, sampler, type, value);
    }
    /**
     * HRESULT SetScissorRect(CONST RECT * pRect);
     * 
     * @param rect
     * @return
     */
    public final long setScissorRect(Rectangle rect){
        return nSetScissorRect(iDirect3DDevice9, rect.getBuffer());
    }
    /**
     * HRESULT SetSoftwareVertexProcessing(BOOL bSoftware);
     * 
     * @param software
     * @return
     */
    public final long setSoftwareVertexProcessing(boolean software){
        return nSetSoftwareVertexProcessing(iDirect3DDevice9, software);
    }
    /**
     * HRESULT SetStreamSource(UINT StreamNumber, IDirect3DVertexBuffer9 * pStreamData, UINT OffsetInBytes, 
     *                       UINT Stride);
     * 
     * @param streamNumber
     * @param streamData
     * @param offsetInBytes
     * @param stride
     * @return
     */
    public final long setStreamSource(int streamNumber, IDirect3DVertexBuffer9 streamData, int offsetInBytes, 
            int stride){
        return nSetStreamSource(iDirect3DDevice9, streamNumber, streamData.getIDirect3DVertexBuffer9(), offsetInBytes, stride);
    }
    /**
     * HRESULT SetStreamSourceFreq(UINT StreamNumber, UINT FrequencyParameter);
     * 
     * @param streamNumber
     * @param frequencyParameter
     * @return
     */
    public final long setStreamSourceFreq(int streamNumber, int frequencyParameter){
        return nSetStreamSourceFreq(iDirect3DDevice9, streamNumber, frequencyParameter);
    }
    /**
     * HRESULT SetTexture(DWORD Sampler, IDirect3DBaseTexture9 * pTexture);
     * 
     * @param sampler
     * @param texture
     * @return
     */
    public final long setTexture(long sampler, IDirect3DBaseTexture9 texture){
        return nSetTexture(iDirect3DDevice9, sampler, texture.getIDirect3DBaseTexture9());
    }
    /**
     * HRESULT SetTextureStageState(DWORD Stage, D3DTEXTURESTAGESTATETYPE Type, DWORD Value);
     * 
     * @param stage
     * @param type
     * @param value
     * @return
     */
    public final long setTextureStageState(long stage, int type, long value){
        return nSetTextureStageState(iDirect3DDevice9, stage, type, value);
    }
    /**
     * HRESULT SetTransform(D3DTRANSFORMSTATETYPE State, CONST D3DMATRIX * pMatrix);
     * 
     * @param state
     * @param matrix
     * @return
     */
    public final long setTransform(int state, D3DMatrix matrix){
        return nSetTransform(iDirect3DDevice9, state, matrix.getBuffer());
    }
    /**
     * HRESULT SetVertexDeclaration(IDirect3DVertexDeclaration9 * pDecl);
     * 
     * @param vertexDeclaration
     * @return
     */
    public final long setVertexDeclaration(IDirect3DVertexDeclaration9 vertexDeclaration){
        return nSetVertexDeclaration(iDirect3DDevice9, vertexDeclaration.getIDirect3DVertexDeclaration9());
    }
    /**
     * HRESULT SetVertexShader(IDirect3DVertexShader9* pShader);
     * 
     * @param shader
     * @return
     */
    public final long setVertexShader(IDirect3DVertexShader9 shader){
        return nSetVertexShader(iDirect3DDevice9, shader.getIDirect3DVertexShader9());
    }
    /**
     * HRESULT SetVertexShaderConstantB(UINT StartRegister, CONST BOOL * pConstantData, UINT BoolCount);
     * 
     * @param startRegister
     * @param constantData
     * @param boolCount
     * @return
     */
    public final long setVertexShaderConstantB(int startRegister, ByteBuffer constantData, int boolCount){
        return nSetVertexShaderConstantB(iDirect3DDevice9, startRegister, constantData, boolCount);
    }
    /**
     * HRESULT SetVertexShaderConstantF(UINT StartRegister, CONST float * pConstantData, UINT Vector4fCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4fCount
     * @return
     */
    public final long setVertexShaderConstantF(int startRegister, FloatBuffer constantData, int vector4fCount){
        return nSetVertexShaderConstantF(iDirect3DDevice9, startRegister, constantData, vector4fCount);
    }
    /**
     * HRESULT SetVertexShaderConstantI(UINT StartRegister, CONST int * pConstantData, UINT Vector4iCount);
     * 
     * @param startRegister
     * @param constantData
     * @param vector4iCount
     * @return
     */
    public final long setVertexShaderConstantI(int startRegister, IntBuffer constantData, int vector4iCount){
        return nSetVertexShaderConstantI(iDirect3DDevice9, startRegister, constantData, vector4iCount);
    }
    /**
     * HRESULT SetViewport(CONST D3DVIEWPORT9 * pViewport);
     * 
     * @param viewport
     * @return
     */
    public final long setViewport(D3DViewport9 viewport){
        return nSetViewport(iDirect3DDevice9, viewport.getBuffer());
    }
    /**
     * BOOL ShowCursor(BOOL bShow);
     * 
     * @param show
     * @return
     */
    public boolean ShowCursor(boolean show){
        return nShowCursor(iDirect3DDevice9, show);
    }
    /**
     * HRESULT StretchRect(IDirect3DSurface9 * pSourceSurface, CONST RECT * pSourceRect, 
     *                   IDirect3DSurface9 * pDestSurface, CONST RECT * pDestRect, D3DTEXTUREFILTERTYPE Filter);
     * 
     * @param sourceSurface
     * @param sourceRect
     * @param destSurface
     * @param destRect
     * @param filter
     * @return
     */
    public final long stretchRect(IDirect3DSurface9 sourceSurface, Rectangle sourceRect, 
            IDirect3DSurface9 destSurface, Rectangle destRect, int filter){
        return nStretchRect(iDirect3DDevice9, sourceSurface.getIDirect3DSurface9(), sourceRect.getBuffer(), 
                destSurface.getIDirect3DSurface9(), destRect.getBuffer(), filter);
    }
    /**
     * HRESULT TestCooperativeLevel();
     * 
     * @return
     */
    public final long tstCooperativeLevel(){
        return nTestCooperativeLevel(iDirect3DDevice9);
    }
    /**
     * HRESULT UpdateSurface(IDirect3DSurface9* pSourceSurface, CONST RECT* pSourceRect, 
     *                        IDirect3DSurface9* pDestinationSurface, CONST POINT* pDestinationPoint);
     * 
     * @param sourceSurface
     * @param sourceRect
     * @param destinationSurface
     * @param destinationPoint
     * @return
     */
    public final long updateSurface(IDirect3DSurface9 sourceSurface, Rectangle sourceRect, IDirect3DSurface9 destinationSurface, Point destinationPoint){
        return nUpdateSurface(iDirect3DDevice9, sourceSurface.getIDirect3DSurface9(), sourceRect.getBuffer(), 
                destinationSurface.getIDirect3DSurface9(), destinationPoint.getBuffer());
    }
    /**
     * HRESULT UpdateTexture(IDirect3DBaseTexture9 * pSourceTexture, IDirect3DBaseTexture9 * pDestinationTexture);
     * 
     * @param sourceTexture
     * @param destinationTexture
     * @return
     */
    public final long updateTexture(IDirect3DBaseTexture9 sourceTexture, IDirect3DBaseTexture9 destinationTexture){
        return nUpdateTexture(iDirect3DDevice9, sourceTexture.getIDirect3DBaseTexture9(), destinationTexture.getIDirect3DBaseTexture9());
    }
    /**
     * HRESULT ValidateDevice(DWORD * pNumPasses);
     * 
     * @param numPasses
     * @return
     */
    public final long validateDevice(IntBuffer numPasses){
        return nValidateDevice(iDirect3DDevice9, numPasses);
    }
    
    /**
     * TODO: done
     * Release the native resources associated with this interface.
     * A new call to org.lwjgl.d3d.IDirect3D9.createDevice() will have to be made to get a new interface.
     *
     */
    public final void release() {
        nRelease(iDirect3DDevice9);
    }

    //setters/getters
    /**
     * Get the pointer to the native interface.
     *  
     * @return The pointer to the native interface.
     */
    public final long getIDirect3DDevice9() {
        return iDirect3DDevice9;
    }
    /**
     * Set the pointer to the native interface.
     * 
     * @param direct3DDevice9 The pointer to the native interface.
     */
    public final void setIDirect3DDevice9(long direct3DDevice9) {
        iDirect3DDevice9 = direct3DDevice9;
    }
    
    //natives
    private final native long nBeginScene(long iDirect3DDevice9);
    private final native long nBeginStateBlock(long iDirect3DDevice9);
    private final native long nClear(long iDirect3DDevice9, long count, LongBuffer rects, long flags, int color, float z, long stencil);
    private final native long nColorFill(long iDirect3DDevice9, long surface, ByteBuffer rect, int color);
    private final native long nCreateAdditionalSwapChain(long iDirect3DDevice9, ByteBuffer presentationParameters, IDirect3DSwapChain9 swapChain);
    private final native long nCreateCubeTexture(long iDirect3DDevice9, int edgeLength, int levels, long usage, int format, int pool, IDirect3DCubeTexture9 cubeTexture, long sharedHandle);
    private final native long nCreateDepthStencilSurface(long iDirect3DDevice9, int width, int height, int format, int multiSample, long multiSampleQuality, boolean discard, IDirect3DSurface9 surface, long sharedHandle);
    private final native long nCreateIndexBuffer(long iDirect3DDevice9, int length, long usage, int format, int pool, IDirect3DIndexBuffer9 indexBuffer, long sharedHandle);
    private final native long nCreateOffscreenPlainSurface(long iDirect3DDevice9, int width, int height, int format, long pool, IDirect3DSurface9 surface, long sharedHandle);
    private final native long nCreatePixelShader(long iDirect3DDevice9, long function, IDirect3DPixelShader9 shader);
    private final native long nCreateQuery(long iDirect3DDevice9, int type, IDirect3DQuery9 query);
    private final native long nCreateRenderTarget(long iDirect3DDevice9, int width, int height, int format, int multiSample, long multiSampleQuality, boolean lockable, IDirect3DSurface9 surface, long sharedHandle);
    private final native long nCreateStateBlock(long iDirect3DDevice9, int type, IDirect3DStateBlock9 stateBlock);
    private final native long nCreateTexture(long iDirect3DDevice9, int width, int height, int levels, long usage, int format, int pool, IDirect3DTexture9 texture, long sharedHandle);
    private final native long nCreateVertexBuffer(long iDirect3DDevice9, int length, int usage, int FVF, int pool, IDirect3DVertexBuffer9 vertexBuffer, long sharedHandle);
    private final native long nCreateVertexDeclaration(long iDirect3DDevice9, ByteBuffer vertexElements, IDirect3DVertexDeclaration9 decl);
    private final native long nCreateVertexShader(long iDirect3DDevice9, long function, IDirect3DVertexShader9 shader);
    private final native long nCreateVolumeTexture(long iDirect3DDevice9, int width, int height, int depth, int levels, long usage, int format, int pool, IDirect3DVolumeTexture9 volumeTexture, long sharedHandle);
    private final native long nDeletePatch(long iDirect3DDevice9, int handle);
    private final native long nDrawIndexedPrimitive(long iDirect3DDevice9, int type, int baseVertexIndex, int minIndex, int numVertices, int startIndex, int primitiveCount);
    private final native long nDrawIndexedPrimitiveUP(long iDirect3DDevice9, int primitiveType, int minVertexIndex, int numVertices, int primitiveCount, ByteBuffer indexData, int indexDataFormat, ByteBuffer vertexStreamZeroData, int vertexStreamZeroStride);
    private final native long nDrawPrimitive(long iDirect3DDevice9, int primitiveType, int startVertex, int primitiveCount);
    private final native long nDrawPrimitiveUP(long iDirect3DDevice9, int primitiveType, int primitiveCount, ByteBuffer vertexStreamZeroData, int vertexStreamZeroStride);
    private final native long nDrawRectPatch(long iDirect3DDevice9, int handle, FloatBuffer numSegs, ByteBuffer rectPatchInfo);
    private final native long nDrawTriPatch(long iDirect3DDevice9, int handle, FloatBuffer numSegs, ByteBuffer triPatchInfo);
    private final native long nEndScene(long iDirect3DDevice9);
    private final native long nEndStateBlock(long iDirect3DDevice9, IDirect3DStateBlock9 stateBlock);
    private final native long nEvictManagedResources(long iDirect3DDevice9);
    private final native int nGetAvailableTextureMem(long iDirect3DDevice9);
    private final native long nGetBackBuffer(long iDirect3DDevice9, int swapChain, int backBuffer, int type, IDirect3DSurface9 backBufferSurface);
    private final native long nGetClipPlane(long iDirect3DDevice9, long index, FloatBuffer plane);
    private final native long nGetClipStatus(long iDirect3DDevice9, ByteBuffer clipStatus);
    private final native long nGetCreationParameters(long iDirect3DDevice9, ByteBuffer parameters);
    private final native long nGetCurrentTexturePalette(long iDirect3DDevice9, IntBuffer paletteNumber);
    private final native long nGetDepthStencilSurface(long iDirect3DDevice9, IDirect3DSurface9 zStencilSurface);
    private final native long nGetDeviceCaps(long iDirect3DDevice9, ByteBuffer caps);
    private final native long nGetDirect3D(long iDirect3DDevice9, IDirect3D9 D3D9);
    private final native long nGetDisplayMode(long iDirect3DDevice9, int swapChain, ByteBuffer mode);
    private final native long nGetFrontBufferData(long iDirect3DDevice9, int swapChain, long destSurface);
    private final native long nGetFVF(long iDirect3DDevice9, IntBuffer FVF);
    private final native void nGetGammaRamp(long iDirect3DDevice9, int swapChain, ByteBuffer ramp);
    private final native long nGetIndices(long iDirect3DDevice9, IDirect3DIndexBuffer9 indexData);
    private final native long nGetLight(long iDirect3DDevice9, long index, D3DLight9 light);
    private final native long nGetLightEnable(long iDirect3DDevice9, long index, ByteBuffer enable);
    private final native long nGetMaterial(long iDirect3DDevice9, ByteBuffer material);
    private final native float nGetNPatchMode(long iDirect3DDevice9);
    private final native int nGetNumberOfSwapChains(long iDirect3DDevice9);
    private final native long nGetPaletteEntries(long iDirect3DDevice9, int paletteNumber, ByteBuffer entries);
    private final native long nGetPixelShader(long iDirect3DDevice9, IDirect3DPixelShader9 shader);
    private final native long nGetPixelShaderConstantB(long iDirect3DDevice9, int startRegister, ByteBuffer constantData, int boolCount);
    private final native long nGetPixelShaderConstantF(long iDirect3DDevice9, int startRegister, FloatBuffer constantData, int vector4fCount);
    private final native long nGetPixelShaderConstantI(long iDirect3DDevice9, int startRegister, ShortBuffer constantData, int vector4iCount);
    private final native long nGetRasterStatus(long iDirect3DDevice9, int swapChain, ByteBuffer rasterStatus);
    private final native long nGetRenderState(long iDirect3DDevice9, int state, IntBuffer value);
    private final native long nGetRenderTarget(long iDirect3DDevice9, long renderTargetIndex, IDirect3DSurface9 renderTarget);
    private final native long nGetRenderTargetData(long iDirect3DDevice9, long renderTarget, long destSurface);
    private final native long nGetSamplerState(long iDirect3DDevice9, long sampler, int type, LongBuffer value);
    private final native long nGetScissorRect(long iDirect3DDevice9, ByteBuffer rect);
    private final native boolean nGetSoftwareVertexProcessing(long iDirect3DDevice9);
    private final native long nGetStreamSource(long iDirect3DDevice9, int streamNumber, IDirect3DVertexBuffer9 streamData, ShortBuffer offsetInBytes, ShortBuffer stride);
    private final native long nGetStreamSourceFreq(long iDirect3DDevice9, int streamNumber, ShortBuffer divider);
    private final native long nGetSwapChain(long iDirect3DDevice9, int swapChainOrdinal, IDirect3DSwapChain9 swapChain);
    private final native long nGetTexture(long iDirect3DDevice9, long stage, IDirect3DBaseTexture9 texture);
    private final native long nGetTextureStageState(long iDirect3DDevice9, long stage, int type, IntBuffer value);
    private final native long nGetTransform(long iDirect3DDevice9, int state, ByteBuffer matrix);
    private final native long nGetVertexDeclaration(long iDirect3DDevice9, IDirect3DVertexDeclaration9 vertexDecleration);
    private final native long nGetVertexShader(long iDirect3DDevice9, IDirect3DVertexShader9 shader);
    private final native long nGetVertexShaderConstantB(long iDirect3DDevice9, int startRegister, ByteBuffer constantData, int boolCount);
    private final native long nGetVertexShaderConstantF(long iDirect3DDevice9, int startRegister, FloatBuffer constantData, int vector4fCount);
    private final native long nGetVertexShaderConstantI(long iDirect3DDevice9, int startRegister, IntBuffer constantData, int vector4iCount);
    private final native long nGetViewport(long iDirect3DDevice9, ByteBuffer viewport);
    private final native long nLightEnable(long iDirect3DDevice9, long lightIndex, boolean enable);
    private final native long nMultiplyTransform(long iDirect3DDevice9, int state, ByteBuffer matrix);
    private final native long nPresent(long iDirect3DDevice9, ByteBuffer sourceRect, ByteBuffer destRect, long destWindowOverride, ByteBuffer dirtyRegion);
    private final native long nProcessVertices(long iDirect3DDevice9, int srcStartIndex, int destIndex, int vertexCount, long destBuffer, long vertexDecl, long flags);
    private final native long nReset(long iDirect3DDevice9, D3DPresentParameters presentationParameters);
    private final native long nSetClipPlane(long iDirect3DDevice9, long index, FloatBuffer plane);
    private final native long nSetClipStatus(long iDirect3DDevice9, ByteBuffer clipStatus);
    private final native long nSetCurrentTexturePalette(long iDirect3DDevice9, int paletteNumber);
    private final native void nSetCursorPosition(long iDirect3DDevice9, int x, int y, long flags);
    private final native long nSetCursorProperties(long iDirect3DDevice9, int xHotSpot, int yHotSpot, long cursorBitmap);
    private final native long nSetDepthStencilSurface(long iDirect3DDevice9, long newZStencil);
    private final native long nSetDialogBoxMode(long iDirect3DDevice9, boolean enableDialogs);
    private final native long nSetFVF(long iDirect3DDevice9, long FVF);
    private final native void nSetGammaRamp(long iDirect3DDevice9, int swapChain, long flags, ByteBuffer ramp);
    private final native long nSetIndices(long iDirect3DDevice9, long indexData);
    private final native long nSetLight(long iDirect3DDevice9, long index, ByteBuffer light);
    private final native long nSetMaterial(long iDirect3DDevice9, ByteBuffer material);
    private final native long nSetNPatchMode(long iDirect3DDevice9, float segments);
    private final native long nSetPaletteEntries(long iDirect3DDevice9, int paletteNumber, ByteBuffer entries);
    private final native long nSetPixelShader(long iDirect3DDevice9, long shader);
    private final native long nSetPixelShaderConstantB(long iDirect3DDevice9, int startRegister, ByteBuffer constantData, int boolCount);
    private final native long nSetPixelShaderConstantF(long iDirect3DDevice9, int startRegister, FloatBuffer constantData, int vector4fCount);
    private final native long nSetPixelShaderConstantI(long iDirect3DDevice9, int startRegister, IntBuffer constantData, int vector4iCount);
    private final native long nSetRenderState(long iDirect3DDevice9, int state, long value);
    private final native long nSetRenderTarget(long iDirect3DDevice9, long renderTargetIndex, long renderTarget);
    private final native long nSetSamplerState(long iDirect3DDevice9, long sampler, int type, long value);
    private final native long nSetScissorRect(long iDirect3DDevice9, ByteBuffer rect);
    private final native long nSetSoftwareVertexProcessing(long iDirect3DDevice9, boolean software);
    private final native long nSetStreamSource(long iDirect3DDevice9, int streamNumber, long streamData, int offsetInBytes, int stride);
    private final native long nSetStreamSourceFreq(long iDirect3DDevice9, int streamNumber, int frequencyParameter);
    private final native long nSetTexture(long iDirect3DDevice9, long sampler, long texture);
    private final native long nSetTextureStageState(long iDirect3DDevice9, long stage, int type, long value);
    private final native long nSetTransform(long iDirect3DDevice9, int state, ByteBuffer matrix);
    private final native long nSetVertexDeclaration(long iDirect3DDevice9, long vertexDeclaration);
    private final native long nSetVertexShader(long iDirect3DDevice9, long shader);
    private final native long nSetVertexShaderConstantB(long iDirect3DDevice9, int startRegister, ByteBuffer constantData, int boolCount);
    private final native long nSetVertexShaderConstantF(long iDirect3DDevice9, int startRegister, FloatBuffer constantData, int vector4fCount);
    private final native long nSetVertexShaderConstantI(long iDirect3DDevice9, int startRegister, IntBuffer constantData, int vector4iCount);
    private final native long nSetViewport(long iDirect3DDevice9, ByteBuffer viewport);
    private final native boolean nShowCursor(long iDirect3DDevice9, boolean show);
    private final native long nStretchRect(long iDirect3DDevice9, long sourceSurface, ByteBuffer sourceRect, long destSurface, ByteBuffer destRect, int filter);
    private final native long nTestCooperativeLevel(long iDirect3DDevice9);
    private final native long nUpdateSurface(long iDirect3DDevice9, long sourceSurface, ByteBuffer sourceRect, long destinationSurface, ByteBuffer destinationPoint);
    private final native long nUpdateTexture(long iDirect3DDevice9, long sourceTexture, long destinationTexture);
    private final native long nValidateDevice(long iDirect3DDevice9, IntBuffer numPasses);
    private final native void nRelease(long iDirect3DDevice9);
}