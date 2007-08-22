package org.lwjgl.test.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.d3d.D3DPresentParameters;
import org.lwjgl.d3d.Direct3DConstants;
import org.lwjgl.d3d.Display;
import org.lwjgl.d3d.DisplayMode;
import org.lwjgl.d3d.IDirect3D9;
import org.lwjgl.d3d.IDirect3DDevice9;
import org.lwjgl.input.Keyboard;

public class DrunkenHyenaLines {
    private static final int STRUCTURE_SIZE = 20;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    public void run() {
      try {
          DisplayMode[] modes = Display.getAvailableDisplayModes();
          for (int i = 0; i < modes.length; i++) {
              if (modes[i].getWidth() == 640 && modes[i].getHeight() == 480 && modes[i].getBitsPerPixel() >= 32 && modes[i].getFrequency() <= 75) {
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
      
      ByteBuffer sineData = ByteBuffer.allocateDirect(WIDTH * STRUCTURE_SIZE);
      sineData.order(ByteOrder.nativeOrder());
      for(int i=0;i<WIDTH;i++) {
          sineData.putFloat(i);
          float y = (float)Math.sin(i / 10.0f);
          sineData.putFloat(y * (HEIGHT / 4.0f) + (HEIGHT / 2.0f));
          sineData.putFloat(1.0f);
          sineData.putFloat(1.0f);
          int blue = ((int)(Math.ceil(((float)i / (float)WIDTH) * 200.0f) + 55)) & 0x000000ff;
          int green = ((int)(Math.ceil((((float)WIDTH - (float)i) / (float)WIDTH) * 200.0f) + 55)) & 0x000000ff;
          int red = ((int)(Math.ceil(Math.abs(y) * 200.0f) + 55.0f)) & 0x000000ff;
          sineData.putInt((red << 16) | (green << 8) | blue);
          
      }
      ByteBuffer randomData = ByteBuffer.allocateDirect(WIDTH * STRUCTURE_SIZE);
      randomData.order(ByteOrder.nativeOrder());
      Random random = new Random(System.currentTimeMillis());
      for(int i=0;i<WIDTH;i++) {
          randomData.putFloat(random.nextFloat() * WIDTH);
          randomData.putFloat(random.nextFloat() * HEIGHT);
          randomData.putFloat(1.0f);
          randomData.putFloat(1.0f);
          randomData.putInt((random.nextInt(255) << 16) | (random.nextInt(255) << 8) | random.nextInt(255));
      }
      int primitiveType = Direct3DConstants.D3DPT_LINELIST;
      int size = WIDTH / 2;
      ByteBuffer data = randomData;
      while(Keyboard.getEventKey() != Keyboard.KEY_ESCAPE || Display.isCloseRequested()) {
          iDirect3DDevice9.beginScene();
          iDirect3DDevice9.clear(0, null, Direct3DConstants.D3DCLEAR_TARGET, 0, 1.0f, 0);
          iDirect3DDevice9.setFVF(Direct3DConstants.D3DFVF_XYZRHW | Direct3DConstants.D3DFVF_DIFFUSE);
          iDirect3DDevice9.drawPrimitiveUP(primitiveType, size, data, STRUCTURE_SIZE);
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
      new DrunkenHyenaLines().run();
      System.exit(0);
  }
}
