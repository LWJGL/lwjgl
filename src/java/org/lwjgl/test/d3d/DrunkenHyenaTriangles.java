package org.lwjgl.test.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.lwjgl.LWJGLException;
import org.lwjgl.d3d.D3DPresentParameters;
import org.lwjgl.d3d.Direct3DConstants;
import org.lwjgl.d3d.Display;
import org.lwjgl.d3d.DisplayMode;
import org.lwjgl.d3d.IDirect3D9;
import org.lwjgl.d3d.IDirect3DDevice9;
import org.lwjgl.input.Keyboard;

public class DrunkenHyenaTriangles {
    private static final int STRUCTURE_SIZE = 20;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;
    private ByteBuffer fan1;
    private ByteBuffer fan2;
    private ByteBuffer strip;
    
    public void run() {
        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].getWidth() == WIDTH && modes[i].getHeight() == HEIGHT && modes[i].getBitsPerPixel() >= 32 && modes[i].getFrequency() <= 75) {
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

        D3DPresentParameters params = new D3DPresentParameters();
        params.Windowed = true;
        params.BackBufferCount = 1;
        params.SwapEffect = Direct3DConstants.D3DSWAPEFFECT_FLIP;
        params.BackBufferFormat = Direct3DConstants.D3DFMT_UNKNOWN;
        IDirect3DDevice9 iDirect3DDevice9 = new IDirect3DDevice9();
        
        iDirect3D9.createDevice(Direct3DConstants.D3DADAPTER_DEFAULT, Direct3DConstants.D3DDEVTYPE_HAL, 
                Display.getHwnd(), Direct3DConstants.D3DCREATE_SOFTWARE_VERTEXPROCESSING, params, 
                iDirect3DDevice9);
        
        try {
            Keyboard.create();
        }
        catch(LWJGLException e) {
            e.printStackTrace();
        }
        
        createPrimitives();
        
        while(Keyboard.getEventKey() != Keyboard.KEY_ESCAPE || Display.isCloseRequested()) {
            iDirect3DDevice9.beginScene();
            iDirect3DDevice9.clear(0, null, Direct3DConstants.D3DCLEAR_TARGET, 0, 1.0f, 0);
            iDirect3DDevice9.setFVF(Direct3DConstants.D3DFVF_XYZRHW | Direct3DConstants.D3DFVF_DIFFUSE);
            iDirect3DDevice9.drawPrimitiveUP(Direct3DConstants.D3DPT_TRIANGLEFAN, 4, fan1, STRUCTURE_SIZE);
            iDirect3DDevice9.drawPrimitiveUP(Direct3DConstants.D3DPT_TRIANGLEFAN, 4, fan2, STRUCTURE_SIZE);
            iDirect3DDevice9.drawPrimitiveUP(Direct3DConstants.D3DPT_TRIANGLESTRIP, 9, strip, STRUCTURE_SIZE);
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
    
    private void createPrimitives() {
        fan1 = ByteBuffer.allocateDirect(6 * STRUCTURE_SIZE);
        fan1.order(ByteOrder.nativeOrder());
        fan1.putFloat(100.0f);
        fan1.putFloat(100.0f);
        fan1.putFloat(1.0f);
        fan1.putFloat(1.0f);
        fan1.putInt(0xff000000);
        fan1.putFloat(0.0f);
        fan1.putFloat(0.0f);
        fan1.putFloat(1.0f);
        fan1.putFloat(1.0f);
        fan1.putInt(0xffff0000);
        fan1.putFloat(200.0f);
        fan1.putFloat(0.0f);
        fan1.putFloat(1.0f);
        fan1.putFloat(1.0f);
        fan1.putInt(0xff00ff00);
        fan1.putFloat(200.0f);
        fan1.putFloat(200.0f);
        fan1.putFloat(1.0f);
        fan1.putFloat(1.0f);
        fan1.putInt(0xff0000ff);
        fan1.putFloat(0.0f);
        fan1.putFloat(200.0f);
        fan1.putFloat(1.0f);
        fan1.putFloat(1.0f);
        fan1.putInt(0xffffffff);
        fan1.putFloat(0.0f);
        fan1.putFloat(0.0f);
        fan1.putFloat(1.0f);
        fan1.putFloat(1.0f);
        fan1.putInt(0xffff0000);
        fan2 = ByteBuffer.allocateDirect(6 * STRUCTURE_SIZE);
        fan2.order(ByteOrder.nativeOrder());
        fan2.putFloat(75.0f);
        fan2.putFloat(350.0f);
        fan2.putFloat(1.0f);
        fan2.putFloat(1.0f);
        fan2.putInt(0xffffffff);
        fan2.putFloat(0.0f);
        fan2.putFloat(225.0f);
        fan2.putFloat(1.0f);
        fan2.putFloat(1.0f);
        fan2.putInt(0xffff0000);
        fan2.putFloat(50.0f);
        fan2.putFloat(215.0f);
        fan2.putFloat(1.0f);
        fan2.putFloat(1.0f);
        fan2.putInt(0xff7f7f00);
        fan2.putFloat(75.0f);
        fan2.putFloat(205.0f);
        fan2.putFloat(1.0f);
        fan2.putFloat(1.0f);
        fan2.putInt(0xff00ff00);
        fan2.putFloat(125.0f);
        fan2.putFloat(215.0f);
        fan2.putFloat(1.0f);
        fan2.putFloat(1.0f);
        fan2.putInt(0xff007f7f);
        fan2.putFloat(150.0f);
        fan2.putFloat(235.0f);
        fan2.putFloat(1.0f);
        fan2.putFloat(1.0f);
        fan2.putInt(0xff0000ff);
        strip = ByteBuffer.allocateDirect(11 * STRUCTURE_SIZE);
        strip.order(ByteOrder.nativeOrder());
        strip.putFloat(250.0f);
        strip.putFloat(150.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xffff0000);
        strip.putFloat(300.0f);
        strip.putFloat(50.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff00ff00);
        strip.putFloat(350.0f);
        strip.putFloat(150.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff0000ff);
        strip.putFloat(400.0f);
        strip.putFloat(50.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xffff0000);
        strip.putFloat(450.0f);
        strip.putFloat(150.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff7f7f00);
        strip.putFloat(450.0f);
        strip.putFloat(150.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff7f7f00);
        strip.putFloat(250.0f);
        strip.putFloat(350.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xffff0000);
        strip.putFloat(300.0f);
        strip.putFloat(250.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff00ff00);
        strip.putFloat(350.0f);
        strip.putFloat(350.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff0000ff);
        strip.putFloat(400.0f);
        strip.putFloat(250.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xffff0000);
        strip.putFloat(450.0f);
        strip.putFloat(350.0f);
        strip.putFloat(1.0f);
        strip.putFloat(1.0f);
        strip.putInt(0xff7f7f00);
    }
    public static void main(String args[]) {
        new DrunkenHyenaTriangles().run();
        System.exit(0);
    }
}
