package org.lwjgl.test.vector;

import java.nio.*;

import org.lwjgl.*;

public class MathTest {
    public static void main(String[] args) {
        ByteBuffer buf = ByteBuffer.allocateDirect(100);
        buf.order(ByteOrder.nativeOrder());
        FloatBuffer float_buf = buf.asFloatBuffer();
        float f = 0f;
        while (float_buf.hasRemaining()) {
            f += 0.5f;
            float_buf.put(f);
        }
        float_buf.rewind();
        System.out.println("Src buffer:");
        while (float_buf.hasRemaining())
            System.out.print(float_buf.get() + " ");
        System.out.println("");
        int buf_address = Sys.getDirectBufferAddress(float_buf);
        org.lwjgl.Math.matrixOp(org.lwjgl.Math.MATRIXOP_NEGATE, buf_address, 0, 100, 1, 1, false, buf_address, 0, false);
        System.out.println("Negated result:");
        float_buf.rewind();
        while (float_buf.hasRemaining())
            System.out.print(float_buf.get() + " ");
        System.out.println("");
    }
}
