package org.lwjgl.test.devil;

import org.lwjgl.devil.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
/**
 * @author Mark Bernard
 * date:	7-Oct-2004
 */
public class BasicTest {
    public static void main(String args[]) {
        try {
            IL.create();
        }
        catch(Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
        System.out.println("ilInit");
        IL.ilInit();
        System.out.println("ilGenImages");
        IntBuffer im =  ByteBuffer.allocateDirect(4).order(ByteOrder.nativeOrder()).asIntBuffer();
        IL.ilGenImages(1, im);
        System.out.println("ilBindImage");
        IL.ilBindImage(im.get(0));
        System.out.println("ilLoadImage");
        IL.ilLoadImage("F:/Apps/Java/eclipse/workspace/LWJGL/res/spaceinvaders/alien2.gif");
        System.out.println("ilGetError");
        int err = IL.ilGetError();
        System.out.println("err = " + err + " IL_NO_ERROR = " + IL.IL_NO_ERROR);
        ByteBuffer buf = IL.ilGetData();
        int limit = buf.limit();
        System.out.println("limit = " + limit);
        
        System.out.println("Version: " + IL.ilGetInteger(IL.IL_VERSION_NUM));
    }
}
