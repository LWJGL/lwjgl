package org.lwjgl.test.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.lwjgl.LWJGLException;
import org.lwjgl.d3d.D3DAdapterIdentifier9;
import org.lwjgl.d3d.D3DCaps9;
import org.lwjgl.d3d.D3DDisplaymode;
import org.lwjgl.d3d.D3DPresentParameters;
import org.lwjgl.d3d.Direct3DConstants;
import org.lwjgl.d3d.Display;
import org.lwjgl.d3d.DisplayMode;
import org.lwjgl.d3d.IDirect3D9;
import org.lwjgl.d3d.IDirect3DDevice9;
import org.lwjgl.input.Keyboard;

public class IDirect3D9Test {
    public IDirect3D9Test() {
        
    }
    
    public void run() {
        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].getWidth() == 800 && modes[i].getHeight() == 600 && modes[i].getBitsPerPixel() >= 32 && modes[i].getFrequency() <= 75) {
                    try {
                        Display.setDisplayMode(modes[i]);
                    } catch (LWJGLException e) {
                        e.printStackTrace();
                    }
                }
            }
            Display.create();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        IDirect3D9 iDirect3D9 = IDirect3D9.create();
        System.out.println("pointer to IDirect3D9 = " + iDirect3D9.getIDirect3D9());
        System.out.println("        adapter count = " + iDirect3D9.getAdapterCount());
//        
//        D3DDisplaymode displaymode = new D3DDisplaymode();
//        displaymode.Width = 1024;
//        displaymode.Height = 768;
//        displaymode.RefreshRate = 85;
//        displaymode.Format = Direct3DConstants.D3DFMT_X8R8G8B8;
//        System.out.println("enumAdapterModes = " + iDirect3D9.enumAdapterModes(
//                Direct3DConstants.D3DADAPTER_DEFAULT, Direct3DConstants.D3DFMT_X8R8G8B8, 0, displaymode));
//        displaymode = new D3DDisplaymode();
//        System.out.println("getAdapterDisplayMode = " + iDirect3D9.getAdapterDisplayMode(
//                Direct3DConstants.D3DADAPTER_DEFAULT, displaymode));
//        System.out.println(displaymode);
//        
//        D3DAdapterIdentifier9 identifier = new D3DAdapterIdentifier9();
//        System.out.println("getAdapterIdentifier = " + iDirect3D9.getAdapterIdentifier(
//                Direct3DConstants.D3DADAPTER_DEFAULT, 0, identifier));
//        System.out.println(identifier);
//        
//        System.out.println("getAdapterModeCount = " + iDirect3D9.getAdapterModeCount(
//                Direct3DConstants.D3DADAPTER_DEFAULT, Direct3DConstants.D3DFMT_X8R8G8B8));
//
//        D3DCaps9 caps = new D3DCaps9();
//        System.out.println("getDeviceCaps = " + iDirect3D9.getDeviceCaps(
//                Direct3DConstants.D3DADAPTER_DEFAULT, Direct3DConstants.D3DDEVTYPE_HAL, caps));
//        System.out.println(caps);
        
        D3DPresentParameters params = new D3DPresentParameters();
        params.Windowed = true;
        params.BackBufferCount = 1;
        params.SwapEffect = Direct3DConstants.D3DSWAPEFFECT_FLIP;
        params.BackBufferFormat = Direct3DConstants.D3DFMT_UNKNOWN;
        IDirect3DDevice9 iDirect3DDevice9 = new IDirect3DDevice9();
        
        System.out.println("createDevice = " + iDirect3D9.createDevice(Direct3DConstants.D3DADAPTER_DEFAULT, Direct3DConstants.D3DDEVTYPE_HAL, 
                Display.getHwnd(), Direct3DConstants.D3DCREATE_SOFTWARE_VERTEXPROCESSING, params, iDirect3DDevice9));
        System.out.println("pointer to iDirect3DDevice9 = " + iDirect3DDevice9.getIDirect3DDevice9());
        
        try {
            Keyboard.create();
        }
        catch(LWJGLException e) {
            e.printStackTrace();
        }
        
        ByteBuffer vertex = ByteBuffer.allocateDirect(60);
        vertex.order(ByteOrder.nativeOrder());
        vertex.putFloat(800.0f / 2.0f);
        vertex.putFloat(100.0f);
        vertex.putFloat(1.0f);
        vertex.putFloat(1.0f);
        vertex.putInt(0xffffffff);
        vertex.putFloat(800.0f * 3.0f / 4.0f);
        vertex.putFloat(350.0f);
        vertex.putFloat(1.0f);
        vertex.putFloat(1.0f);
        vertex.putInt(0xffffffff);
        vertex.putFloat(800.0f / 4.0f);
        vertex.putFloat(350.0f);
        vertex.putFloat(1.0f);
        vertex.putFloat(1.0f);
        vertex.putInt(0xffffffff);
        while(Keyboard.getEventKey() != Keyboard.KEY_ESCAPE || Display.isCloseRequested()) {
            iDirect3DDevice9.beginScene();
            iDirect3DDevice9.clear(0, null, Direct3DConstants.D3DCLEAR_TARGET, 0, 1.0f, 0);
            iDirect3DDevice9.setFVF(Direct3DConstants.D3DFVF_XYZRHW | Direct3DConstants.D3DFVF_DIFFUSE);
            iDirect3DDevice9.drawPrimitiveUP(Direct3DConstants.D3DPT_TRIANGLELIST, 1, vertex, 20);
            iDirect3DDevice9.endScene();
            iDirect3DDevice9.present(null, null, 0, null);
            Display.update();
            try {
                Thread.sleep(100);
            }
            catch(Exception e) {}
        }
        
        iDirect3DDevice9.release();
        iDirect3D9.release();
        Display.destroy();
    }
    public static void main(String args[]) {
        new IDirect3D9Test().run();
        System.exit(0);
    }
}
