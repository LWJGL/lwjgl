#ifdef DOUBLE_FP
    #ifdef AMD_FP
        #pragma OPENCL EXTENSION cl_amd_fp64 : enable
    #else
        #pragma OPENCL EXTENSION cl_khr_fp64 : enable
    #endif
    typedef double varfloat;
#else
    typedef float varfloat;
#endif

#ifdef USE_TEXTURE
    typedef __write_only image2d_t OUTPUT_TYPE;
#else
    typedef global uint * OUTPUT_TYPE;
#endif

/**
 * For a description of this algorithm please refer to
 * http://en.wikipedia.org/wiki/Mandelbrot_set
 * @author Michael Bien
 */
kernel void mandelbrot(
        const int width,                const int height,
        const varfloat x0,              const varfloat y0,
        const varfloat rangeX,          const varfloat rangeY,
        OUTPUT_TYPE output,             global uint *colorMap,
        const int colorMapSize,         const int maxIterations
) {
    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    varfloat r = x0 + ix * rangeX / width;
    varfloat i = y0 + iy * rangeY / height;

    varfloat x = 0;
    varfloat y = 0;

    varfloat magnitudeSquared = 0;
    int iteration = 0;

    while ( magnitudeSquared < 4 && iteration < maxIterations ) {
        varfloat x2 = x*x;
        varfloat y2 = y*y;
        y = 2 * x * y + i;
        x = x2 - y2 + r;
        magnitudeSquared = x2+y2;
        iteration++;
    }

    if ( iteration == maxIterations )  {
        #ifdef USE_TEXTURE
            write_imagef(output, (int2)(ix, iy), (float4)0);
        #else
            output[iy * width + ix] = 0;
        #endif
        } else {
        varfloat alpha = (varfloat)iteration / maxIterations;
        int colorIndex = (int)(alpha * colorMapSize);
        #ifdef USE_TEXTURE
            // We could have changed colorMap to a texture + sampler, but the
            // unpacking below has minimal overhead and it's kinda interesting.
            // We could also use an R32UI texture and do the unpacking in GLSL,
            // but then we'd require OpenGL 3.0 (GLSL 1.30).
            uint c = colorMap[colorIndex];
            float3 oc = (float3)(
                (c & 0xFF) >> 0,
                (c & 0xFF00) >> 8,
                (c & 0xFF0000) >> 16
            );
            write_imagef(output, (int2)(ix, iy), (float4)(oc / 255.0, 1.0));
        #else
            output[iy * width + ix] = colorMap[colorIndex];
        #endif
        // monochrom
        //output[iy * width + ix] = 255*iteration/maxIterations;
    }
}