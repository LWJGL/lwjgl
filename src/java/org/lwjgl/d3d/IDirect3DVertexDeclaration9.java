package org.lwjgl.d3d;

import java.nio.IntBuffer;

public class IDirect3DVertexDeclaration9 {
    private long iDirect3DVertexDeclaration9;
    //HRESULT GetDeclaration(D3DVERTEXELEMENT9* pDecl, UINT* pNumElements);
    public long GetDeclaration(D3DVertexElement9 decl, IntBuffer numElements){
        return nGetDeclaration(iDirect3DVertexDeclaration9, decl, numElements);
    }
    //HRESULT GetDevice(IDirect3DDevice9 ** ppDevice);
    public long GetDevice(IDirect3DDevice9 device){
        return nGetDevice(iDirect3DVertexDeclaration9, device);
    }
    
    public long getIDirect3DVertexDeclaration9() {
        return iDirect3DVertexDeclaration9;
    }
    public void setIDirect3DVertexDeclaration9(long direct3DVertexDeclaration9) {
        iDirect3DVertexDeclaration9 = direct3DVertexDeclaration9;
    }

    //natives
    private native long nGetDeclaration(long iDirect3DVertexDeclaration9, D3DVertexElement9 decl, IntBuffer numElements);
    private native long nGetDevice(long iDirect3DVertexDeclaration9, IDirect3DDevice9 device);
}
