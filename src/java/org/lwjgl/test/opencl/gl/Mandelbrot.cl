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

/**
 * For a description of this algorithm please refer to
 * http://en.wikipedia.org/wiki/Mandelbrot_set
 * @author Michael Bien
 */
kernel void mandelbrot(
        const int width,        const int height,
        const varfloat x0,      const varfloat y0,
        const varfloat rangeX,  const varfloat rangeY,
        global uint *output,    global uint *colorMap,
        const int colorMapSize, const int maxIterations) {

    unsigned int ix = get_global_id(0);
    unsigned int iy = get_global_id(1);

    varfloat r = x0 + ix * rangeX / width;
    varfloat i = y0 + iy * rangeY / height;

    varfloat x = 0;
    varfloat y = 0;

    varfloat magnitudeSquared = 0;
    int iteration = 0;

    while (magnitudeSquared < 4 && iteration < maxIterations) {
        varfloat x2 = x*x;
        varfloat y2 = y*y;
        y = 2 * x * y + i;
        x = x2 - y2 + r;
        magnitudeSquared = x2+y2;
        iteration++;
    }

    if (iteration == maxIterations)  {
        output[iy * width + ix] = 0;
    }else {
        varfloat alpha = (varfloat)iteration / maxIterations;
        int colorIndex = (int)(alpha * colorMapSize);
        output[iy * width + ix] = colorMap[colorIndex];
      // monochrom
      //  output[iy * width + ix] = 255*iteration/maxIterations;
    }

}