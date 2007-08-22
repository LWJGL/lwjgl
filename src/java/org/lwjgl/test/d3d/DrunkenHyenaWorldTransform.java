package org.lwjgl.test.d3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.lwjgl.LWJGLException;
import org.lwjgl.d3d.D3DMatrix;
import org.lwjgl.d3d.D3DPresentParameters;
import org.lwjgl.d3d.D3DUtil;
import org.lwjgl.d3d.D3DVector;
import org.lwjgl.d3d.Direct3DConstants;
import org.lwjgl.d3d.Display;
import org.lwjgl.d3d.DisplayMode;
import org.lwjgl.d3d.IDirect3D9;
import org.lwjgl.d3d.IDirect3DDevice9;
import org.lwjgl.d3d.IDirect3DVertexBuffer9;
import org.lwjgl.input.Keyboard;

public class DrunkenHyenaWorldTransform {
    private static final int STRUCTURE_SIZE = 16;
    private static final int WIDTH = 640;
    private static final int HEIGHT = 480;

    private IDirect3D9 iDirect3D9;
    private IDirect3DDevice9 iDirect3DDevice9;
    private IDirect3DVertexBuffer9 iDirect3DVertexBuffer9;

    private int g_list_count;

    float scaledTri_scale_x = 1.0f;
    float scaledTri_scale_y = 1.0f;
    float scaledTri_dir_x = 1.0f;
    float scaledTri_dir_y = 1.0f;
    
    float translatedTri_pos_x = 1.0f;
    float translatedTri_dir_x = 1.0f;

    float rotatedTri_rot_y = 0.0f;

    float rotAndTransTri_rot_z = 0.0f;
    float rotAndTransTri_pos_x = 1.0f;
    float rotAndTransTri_dir_x = 1.0f;

    public void run() {
        long curentTime = System.currentTimeMillis();
        long elapsed = 0;
        long diffTime = 0;
        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();
            for (int i = 0; i < modes.length; i++) {
                if (modes[i].getWidth() == WIDTH
                        && modes[i].getHeight() == HEIGHT
                        && modes[i].getBitsPerPixel() >= 32
                        && modes[i].getFrequency() <= 75) {
                    try {
                        Display.setDisplayMode(modes[i]);
                    }
                    catch (LWJGLException e) {
                        e.printStackTrace();
                    }
                }
            }
            Display.create();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        long hResult = Direct3DConstants.D3D_OK;

        iDirect3D9 = IDirect3D9.create();

        D3DPresentParameters params = new D3DPresentParameters();
        params.Windowed = true;
        params.BackBufferCount = 1;
        params.SwapEffect = Direct3DConstants.D3DSWAPEFFECT_FLIP;
        params.BackBufferFormat = Direct3DConstants.D3DFMT_UNKNOWN;
        iDirect3DDevice9 = new IDirect3DDevice9();

        hResult = iDirect3D9.createDevice(Direct3DConstants.D3DADAPTER_DEFAULT, Direct3DConstants.D3DDEVTYPE_HAL, 
                Display.getHwnd(), Direct3DConstants.D3DCREATE_SOFTWARE_VERTEXPROCESSING, params, iDirect3DDevice9);
        if (hResult != Direct3DConstants.D3D_OK) {
            throw new RuntimeException("Unable to create the device(" + hResult
                    + ")");
        }

        iDirect3DDevice9.setRenderState(Direct3DConstants.D3DRS_LIGHTING, 0);
        iDirect3DDevice9.setRenderState(Direct3DConstants.D3DRS_CULLMODE, Direct3DConstants.D3DCULL_NONE);
        initMatrices();

        ByteBuffer triangleList = ByteBuffer.allocateDirect(3 * STRUCTURE_SIZE);
        triangleList.order(ByteOrder.nativeOrder());
        triangleList.putFloat(0);
        triangleList.putFloat(1);
        triangleList.putFloat(0);
        triangleList.putInt(0xFFFF0000);
        triangleList.putFloat(1);
        triangleList.putFloat(-1);
        triangleList.putFloat(0);
        triangleList.putInt(0xFF00FF00);
        triangleList.putFloat(-1);
        triangleList.putFloat(-1);
        triangleList.putFloat(0);
        triangleList.putInt(0xFF0000FF);

        int vert_count = triangleList.capacity() / STRUCTURE_SIZE;
        int byte_count = vert_count * STRUCTURE_SIZE;
        g_list_count = vert_count / 3;

        iDirect3DVertexBuffer9 = new IDirect3DVertexBuffer9();
        hResult = iDirect3DDevice9.createVertexBuffer(byte_count, Direct3DConstants.D3DUSAGE_WRITEONLY,
                        Direct3DConstants.D3DFVF_XYZ | Direct3DConstants.D3DFVF_DIFFUSE,
                        Direct3DConstants.D3DPOOL_MANAGED, iDirect3DVertexBuffer9, 0);
        if (hResult != Direct3DConstants.D3D_OK) {
            throw new RuntimeException("Unable to create the vertex buffer(" + hResult + ")");
        }
        hResult = iDirect3DVertexBuffer9.Lock(0, 0, triangleList, 0);
        if (hResult != Direct3DConstants.D3D_OK) {
            throw new RuntimeException("Failed to lock the vertex buffer(" + hResult + ")");
        }
        iDirect3DVertexBuffer9.Unlock();

        try {
            Keyboard.create();
        }
        catch (LWJGLException e) {
            e.printStackTrace();
        }

        while (Keyboard.getEventKey() != Keyboard.KEY_ESCAPE || Display.isCloseRequested()) {
            diffTime = System.currentTimeMillis() - curentTime;
            curentTime = System.currentTimeMillis();
            elapsed += diffTime;
            iDirect3DDevice9.beginScene();
            iDirect3DDevice9.clear(0, null, Direct3DConstants.D3DCLEAR_TARGET, 0x00000000, 1.0f, 0);
            iDirect3DDevice9.setFVF(Direct3DConstants.D3DFVF_XYZ | Direct3DConstants.D3DFVF_DIFFUSE);
            iDirect3DDevice9.setStreamSource(0, iDirect3DVertexBuffer9, 0, STRUCTURE_SIZE);

            render_scaled_tri();

            render_translated_tri();

            render_rotated_tri();

            render_rot_and_trans_tri();

            iDirect3DDevice9.endScene();
            iDirect3DDevice9.present(null, null, 0, null);
            Display.update();
            if (elapsed > 5000) {
                break;
            }
        }

        iDirect3DDevice9.release();
        iDirect3D9.release();
        Display.destroy();
    }

    private void render_scaled_tri() {
        D3DMatrix scale_matrix = new D3DMatrix();

        D3DUtil.D3DXMatrixScaling(scale_matrix, scaledTri_scale_x,
                scaledTri_scale_y, 1.0f);

        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_WORLD,
                scale_matrix);

        iDirect3DDevice9.drawPrimitive(Direct3DConstants.D3DPT_TRIANGLELIST, // PrimitiveType
                0, // StartVertex
                g_list_count); // PrimitiveCount

        scaledTri_scale_x += 0.01f * scaledTri_dir_x;
        if (scaledTri_scale_x > 4.0f) {
            scaledTri_dir_x = -1.0f;
        }
        else if (scaledTri_scale_x < 0.25f) {
            scaledTri_dir_x = 1.0f;
        }

        scaledTri_scale_y += 0.011f * scaledTri_dir_y;
        if (scaledTri_scale_y > 4.0f) {
            scaledTri_dir_y = -1.0f;
        }
        else if (scaledTri_scale_y < 0.25f) {
            scaledTri_dir_y = 1.0f;
        }
    }

    private void render_translated_tri() {
        D3DMatrix trans_matrix = new D3DMatrix();

        D3DUtil.D3DXMatrixTranslation(trans_matrix, translatedTri_pos_x, 2.5f,
                0.0f);

        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_WORLD,
                trans_matrix);

        iDirect3DDevice9.drawPrimitive(Direct3DConstants.D3DPT_TRIANGLELIST, // PrimitiveType
                0, // StartVertex
                g_list_count); // PrimitiveCount

        translatedTri_pos_x += 0.01f * translatedTri_dir_x;
        if (translatedTri_pos_x > 3.5f) {
            translatedTri_dir_x = -1.0f;
        }
        else if (translatedTri_pos_x < -3.5f) {
            translatedTri_dir_x = 1.0f;
        }
    }

    private void render_rotated_tri() {
        D3DMatrix rot_matrix = new D3DMatrix();

        D3DUtil.D3DXMatrixRotationY(rot_matrix, rotatedTri_rot_y);

        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_WORLD, rot_matrix);

        iDirect3DDevice9.drawPrimitive(Direct3DConstants.D3DPT_TRIANGLELIST, // PrimitiveType
                0, // StartVertex
                g_list_count); // PrimitiveCount

        // 2*PI is a complete circle so we reset the value to keep the variable
        // from overflowing
        rotatedTri_rot_y += 0.01f;
        if (rotatedTri_rot_y > Direct3DConstants.D3DX_PI * 2) {
            rotatedTri_rot_y -= Direct3DConstants.D3DX_PI * 2;
        }
    }

    private void render_rot_and_trans_tri() {
        D3DMatrix rot_matrix = new D3DMatrix();
        D3DMatrix trans_matrix = new D3DMatrix();
        D3DMatrix world_matrix = new D3DMatrix();

        D3DUtil.D3DXMatrixRotationZ(rot_matrix, rotAndTransTri_rot_z);
        D3DUtil.D3DXMatrixTranslation(trans_matrix, rotAndTransTri_pos_x, -2.5f, 0.0f);
        D3DMatrix temp = D3DUtil.D3DXMatrixMultiply(world_matrix, rot_matrix, trans_matrix);   //Rot * Trans
           //D3DXMatrixMultiply(&world_matrix,&trans_matrix,&rot_matrix); //Trans * Rot

        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_WORLD, world_matrix);


        iDirect3DDevice9.drawPrimitive(Direct3DConstants.D3DPT_TRIANGLELIST, //PrimitiveType
                                       0,                  //StartVertex
                                       g_list_count);      //PrimitiveCount


       rotAndTransTri_rot_z += 0.01f;
       if(rotAndTransTri_rot_z > Direct3DConstants.D3DX_PI * 2){  
          rotAndTransTri_rot_z -= Direct3DConstants.D3DX_PI * 2;
       }

       rotAndTransTri_pos_x += 0.01f * rotAndTransTri_dir_x;
       if(rotAndTransTri_pos_x > 3.5f){
          rotAndTransTri_dir_x = -1.0f;
       }
       else if(rotAndTransTri_pos_x < -3.5f){
          rotAndTransTri_dir_x = 1.0f;
       }
    }

    private void initMatrices() {
        D3DMatrix view_matrix = new D3DMatrix();
        D3DMatrix projection_matrix = new D3DMatrix();
        D3DVector eye_vector = new D3DVector();
        D3DVector lookat_vector = new D3DVector();
        D3DVector up_vector = new D3DVector();
        D3DMatrix world_matrix = new D3DMatrix();
        float aspect;

        // Here we build our View Matrix, think of it as our camera.

        // First we specify that our viewpoint is 8 units back on the Z-axis
        eye_vector.x = 0.0f;
        eye_vector.y = 0.0f;
        eye_vector.z = -8.0f;

        // We are looking towards the origin
        lookat_vector.x = 0.0f;
        lookat_vector.y = 0.0f;
        lookat_vector.z = 0.0f;

        // The "up" direction is the positive direction on the y-axis
        up_vector.x = 0.0f;
        up_vector.y = 1.0f;
        up_vector.z = 0.0f;

        D3DUtil.D3DXMatrixLookAtLH(view_matrix, eye_vector, lookat_vector,
                up_vector);

        // Since our 'camera' will never move, we can set this once at the
        // beginning and never worry about it again
        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_VIEW, view_matrix);

        aspect = ((float) WIDTH / (float) WIDTH);

        D3DUtil.D3DXMatrixPerspectiveFovLH(projection_matrix, // Result Matrix
                (float) Math.PI / 4, // Field of View, in radians.
                aspect, // Aspect ratio
                1.0f, // Near view plane
                100.0f); // Far view plane

        // Our Projection matrix won't change either, so we set it now and never
        // touch
        // it again.
        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_PROJECTION,
                projection_matrix);

        // The World Matrix transforms Model Coordinates into World Space
        // coordinates.
        // Setting it to Identity means there is no transformation, so Model
        // Space is directly
        // mapped onto World Space.
        D3DUtil.D3DXMatrixIdentity(world_matrix);
        iDirect3DDevice9.setTransform(Direct3DConstants.D3DTS_WORLD,
                world_matrix);
    }

    public static void main(String args[]) {
        new DrunkenHyenaWorldTransform().run();
        System.exit(0);
    }
}
