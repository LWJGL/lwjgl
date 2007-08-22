/*
 * Copyright (c) 2002-2004 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package org.lwjgl.d3d;

/**
 * This class describes pixel format properties for an OpenGL context. Instances
 * of this class is used as arguments to Display.create(), Pbuffer.create() and
 * AWTGLCanvas, to indicate minimum required properties.
 *
 * WARNING: Some pixel formats are known to cause troubles on certain buggy drivers.
 *          Example: Under Windows, specifying samples != 0 will enable the ARB
 *          pixel format selection path, which could trigger a crash.
 *
 * @author elias_naur@sourceforge.net
 * @version $Revision: 2361 $
 */

public final class PixelFormat {
    /**
     * The number of bits per pixel, exluding alpha. 
     * This parameter is ignored in Display.create().
     */
    private final int bpp;
    /** The number of alpha bits. */
    private final int alpha;
    /** The number of depth buffer bits*/
    private final int depth;
    /** The number of stencil bits */
    private final int stencil;
    /**
     * The number of samples to use in anti-aliasing.
     * 0 means that anti-aliasing is disabled.
     */
    private final int samples;
    /** The number of auxilliary buffers */
    private final int num_aux_buffers;
    /** The number of bits per pixel in the accumulation buffer */
    private final int accum_bpp;
    /** The number of alpha bits in the accumulation buffer */
    private final int accum_alpha;
    /** Whether this format requires a stereo buffer */
    private final boolean stereo;
    /** Whether this format specifies a floating point format */
    private final boolean floating_point;

    /**
     * Default pixel format is minimum 8 bits depth, and no alpha
     * nor stencil requirements.
     */
    public PixelFormat() {
        this(0, 8, 0);
    }

    public PixelFormat(int alpha, int depth, int stencil) {
        this(alpha, depth, stencil, 0);
    }

    public PixelFormat(int alpha, int depth, int stencil, int samples) {
        this(0, alpha, depth, stencil, samples);
    }

    public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples) {
        this(bpp, alpha, depth, stencil, samples, 0, 0, 0, false);
    }

    public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo) {
        this(bpp, alpha, depth, stencil, samples, num_aux_buffers, accum_bpp, accum_alpha, stereo, false);
    }

    public PixelFormat(int bpp, int alpha, int depth, int stencil, int samples, int num_aux_buffers, int accum_bpp, int accum_alpha, boolean stereo, boolean floating_point) {
        this.bpp = bpp;
        this.alpha = alpha;
        this.depth = depth;
        this.stencil = stencil;
        this.samples = samples;
        this.num_aux_buffers = num_aux_buffers;
        this.accum_bpp = accum_bpp;
        this.accum_alpha = accum_alpha;
        this.stereo = stereo;
        this.floating_point = floating_point;
    }

    public int getBitsPerPixel() {
        return bpp;
    }

    public int getAlphaBits() {
        return alpha;
    }

    public int getDepthBits() {
        return depth;
    }

    public int getStencilBits() {
        return stencil;
    }

    public int getSamples() {
        return samples;
    }

    public int getAuxBuffers() {
        return num_aux_buffers;
    }

    public int getAccumulationBitsPerPixel() {
        return accum_bpp;
    }

    public int getAccumulationAlpha() {
        return accum_alpha;
    }

    public boolean isStereo() {
        return stereo;
    }
    
    public boolean isFloatingPoint() {
        return floating_point;
    }
}
