/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 * * Neither the name of 'Light Weight Java Game Library' nor the names of 
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

package org.lwjgl.opengl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.CharBuffer;
import java.nio.FloatBuffer;
import java.nio.DoubleBuffer;
import java.nio.Buffer;

/**
 * $Id$
 *
 * The GL itself, with all supported extensions, based on OpenGL 1.4.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GL extends CoreGL implements GLConstants {
	public static native void glActiveStencilFaceEXT(int face);

	public static native void glActiveTextureARB(int texture);

	public static native void glAlphaFragmentOp1ATI(
		int op,
		int dst,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod);

	public static native void glAlphaFragmentOp2ATI(
		int op,
		int dst,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod,
		int arg2,
		int arg2Rep,
		int arg2Mod);

	public static native void glAlphaFragmentOp3ATI(
		int op,
		int dst,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod,
		int arg2,
		int arg2Rep,
		int arg2Mod,
		int arg3,
		int arg3Rep,
		int arg3Mod);

	public static native boolean glAreProgramsResidentNV(
		int n,
		IntBuffer piIDs,
		ByteBuffer pbResidences);

	public static native void glArrayObjectATI(
		int array,
		int size,
		int type,
		int stride,
		int buffer,
		int offset);

	public static native void glBeginFragmentShaderATI();

	public static native void glBeginOcclusionQueryNV(int id);

	public static native void glBeginVertexShaderEXT();

	public static native void glBindFragmentShaderATI(int id);

	public static native int glBindLightParameterEXT(int light, int value);

	public static native int glBindMaterialParameterEXT(int face, int value);

	public static native int glBindParameterEXT(int value);

	public static native void glBindProgramARB(int target, int program);

	public static native void glBindProgramNV(int target, int id);

	public static native int glBindTexGenParameterEXT(int unit, int coord, int value);

	public static native int glBindTextureUnitParameterEXT(int unit, int value);

	public static native void glBindVertexShaderEXT(int id);

	public static native void glClientActiveTextureARB(int texture);

	public static native void glClientActiveVertexStreamATI(int stream);

	public static native void glColorFragmentOp1ATI(
		int op,
		int dst,
		int dstMask,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod);

	public static native void glColorFragmentOp2ATI(
		int op,
		int dst,
		int dstMask,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod,
		int arg2,
		int arg2Rep,
		int arg2Mod);

	public static native void glColorFragmentOp3ATI(
		int op,
		int dst,
		int dstMask,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod,
		int arg2,
		int arg2Rep,
		int arg2Mod,
		int arg3,
		int arg3Rep,
		int arg3Mod);

	public static native void glCombinerInputNV(
		int stage,
		int portion,
		int variable,
		int input,
		int mapping,
		int componentUsage);

	public static native void glCombinerOutputNV(
		int stage,
		int portion,
		int abOutput,
		int cdOutput,
		int sumOutput,
		int scale,
		int bias,
		boolean abDotProduct,
		boolean cdDotProduct,
		boolean muxSum);

	public static native void glCombinerParameterfNV(int pname, float param);

	public static native void glCombinerParameterfvNV(int pname, FloatBuffer pfParams);

	public static native void glCombinerParameteriNV(int pname, int param);

	public static native void glCombinerParameterivNV(int pname, IntBuffer piParams);

	public static native void glCombinerStageParameterfvNV(
		int stage,
		int pname,
		FloatBuffer pfParams);

	public static native void glCompressedTexImage1DARB(
		int target,
		int level,
		int internalformat,
		int width,
		int border,
		int imageSize,
		Buffer pData);

	public static native void glCompressedTexImage2DARB(
		int target,
		int level,
		int internalformat,
		int width,
		int height,
		int border,
		int imageSize,
		Buffer pData);

	public static native void glCompressedTexImage3DARB(
		int target,
		int level,
		int internalformat,
		int width,
		int height,
		int depth,
		int border,
		int imageSize,
		Buffer pData);

	public static native void glCompressedTexSubImage1DARB(
		int target,
		int level,
		int xoffset,
		int width,
		int format,
		int imageSize,
		Buffer pData);

	public static native void glCompressedTexSubImage2DARB(
		int target,
		int level,
		int xoffset,
		int yoffset,
		int width,
		int height,
		int format,
		int imageSize,
		Buffer pData);

	public static native void glCompressedTexSubImage3DARB(
		int target,
		int level,
		int xoffset,
		int yoffset,
		int zoffset,
		int width,
		int height,
		int depth,
		int format,
		int imageSize,
		Buffer pData);

	public static native void glCurrentPaletteMatrixARB(int index);

	public static native void glDeleteFencesNV(int n, IntBuffer piFences);

	public static native void glDeleteFragmentShaderATI(int id);

	public static native void glDeleteOcclusionQueriesNV(int n, IntBuffer piIDs);

	public static native void glDeleteProgramsARB(int n, IntBuffer piPrograms);

	public static native void glDeleteProgramsNV(int n, IntBuffer piIDs);

	public static native void glDeleteVertexShaderEXT(int id);

	public static native void glDisableVariantClientStateEXT(int id);

	public static native void glDisableVertexAttribArrayARB(int index);

	public static native void glDrawElementArrayATI(int mode, int count);

	public static native void glDrawRangeElementArrayATI(
		int mode,
		int start,
		int end,
		int count);

	public static native void glDrawRangeElementsEXT(
		int mode,
		int start,
		int end,
		int count,
		int type,
		Buffer pIndices);

	public static native void glElementPointerATI(int type, Buffer pPointer);

	public static native void glEnableVariantClientStateEXT(int id);

	public static native void glEnableVertexAttribArrayARB(int index);

	public static native void glEndFragmentShaderATI();

	public static native void glEndOcclusionQueryNV();

	public static native void glEndVertexShaderEXT();

	public static native void glEvalMapsNV(int target, int mode);

	public static native void glExecuteProgramNV(int target, int id, FloatBuffer pfParams);

	public static native void glExtractComponentEXT(int res, int src, int num);

	public static native void glFinalCombinerInputNV(
		int variable,
		int input,
		int mapping,
		int componentUsage);

	public static native void glFinishFenceNV(int fence);

	public static native void glFlushVertexArrayRangeNV();

	public static native void glFogCoorddEXT(double coord);

	public static native void glFogCoordfEXT(float coord);

	public static native void glFogCoordPointerEXT(int type, int stride, Buffer pPointer);

	public static native void glFreeObjectBufferATI(int buffer);

	public static native void glGenFencesNV(int n, IntBuffer piFences);

	public static native int glGenFragmentShadersATI(int range);

	public static native void glGenOcclusionQueriesNV(int n, IntBuffer piIDs);

	public static native void glGenProgramsARB(int n, IntBuffer piPrograms);

	public static native void glGenProgramsNV(int n, IntBuffer piIDs);

	public static native int glGenSymbolsEXT(
		int dataType,
		int storageType,
		int range,
		int components);

	public static native int glGenVertexShadersEXT(int range);

	public static native void glGetArrayObjectfvATI(int array, int pname, FloatBuffer pfParams);

	public static native void glGetArrayObjectivATI(int array, int pname, IntBuffer piParams);

	public static native void glGetCombinerInputParameterfvNV(
		int stage,
		int portion,
		int variable,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetCombinerInputParameterivNV(
		int stage,
		int portion,
		int variable,
		int pname,
		IntBuffer piParams);

	public static native void glGetCombinerOutputParameterfvNV(
		int stage,
		int portion,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetCombinerOutputParameterivNV(
		int stage,
		int portion,
		int pname,
		IntBuffer piParams);

	public static native void glGetCombinerStageParameterfvNV(
		int stage,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetCompressedTexImageARB(int target, int lod, Buffer pImg);

	public static native void glGetFenceivNV(int fence, int pname, IntBuffer piParams);

	public static native void glGetFinalCombinerInputParameterfvNV(
		int variable,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetFinalCombinerInputParameterivNV(
		int variable,
		int pname,
		IntBuffer piParams);

	public static native void glGetInvariantBooleanvEXT(int id, int value, ByteBuffer pbData);

	public static native void glGetInvariantFloatvEXT(int id, int value, FloatBuffer pfData);

	public static native void glGetInvariantIntegervEXT(int id, int value, IntBuffer piData);

	public static native void glGetLocalConstantBooleanvEXT(
		int id,
		int value,
		ByteBuffer pbData);

	public static native void glGetLocalConstantFloatvEXT(int id, int value, FloatBuffer pfData);

	public static native void glGetLocalConstantIntegervEXT(
		int id,
		int value,
		IntBuffer piData);

	public static native void glGetMapAttribParameterfvNV(
		int target,
		int index,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetMapAttribParameterivNV(
		int target,
		int index,
		int pname,
		IntBuffer piParams);

	public static native void glGetMapControlPointsNV(
		int target,
		int index,
		int type,
		int ustride,
		int vstride,
		boolean packed,
		Buffer pPoints);

	public static native void glGetMapParameterfvNV(int target, int pname, FloatBuffer pfParams);

	public static native void glGetMapParameterivNV(int target, int pname, IntBuffer piParams);

	public static native void glGetObjectBufferfvATI(
		int buffer,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetObjectBufferivATI(
		int buffer,
		int pname,
		IntBuffer piParams);

	public static native void glGetOcclusionQueryivNV(int id, int pname, IntBuffer piParams);

	public static native void glGetOcclusionQueryuivNV(int id, int pname, IntBuffer piParams);

	public static native void glGetProgramEnvParameterdvARB(
		int target,
		int index,
		DoubleBuffer pdParams);

	public static native void glGetProgramEnvParameterfvARB(
		int target,
		int index,
		FloatBuffer pfParams);

	public static native void glGetProgramivARB(int target, int pname, IntBuffer piParams);

	public static native void glGetProgramivNV(int id, int pname, IntBuffer piParams);

	public static native void glGetProgramLocalParameterdvARB(
		int target,
		int index,
		DoubleBuffer pdParams);

	public static native void glGetProgramLocalParameterfvARB(
		int target,
		int index,
		FloatBuffer pfParams);

	public static native void glGetProgramParameterdvNV(
		int target,
		int index,
		int pname,
		DoubleBuffer pdParams);

	public static native void glGetProgramParameterfvNV(
		int target,
		int index,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetProgramStringARB(int target, int pname, Buffer pString);

	public static native void glGetProgramStringNV(int id, int pname, ByteBuffer pProgram);

	public static native void glGetTexBumpParameterfvATI(int pname, FloatBuffer pfParam);

	public static native void glGetTexBumpParameterivATI(int pname, FloatBuffer piParam);

	public static native void glGetTrackMatrixivNV(
		int target,
		int address,
		int pname,
		IntBuffer piParams);

	public static native void glGetVariantArrayObjectfvATI(
		int id,
		int pname,
		FloatBuffer pfParams);

	public static native void glGetVariantArrayObjectivATI(
		int id,
		int pname,
		IntBuffer piParams);

	public static native void glGetVariantBooleanvEXT(int id, int value, ByteBuffer pbData);

	public static native void glGetVariantFloatvEXT(int id, int value, FloatBuffer pfData);

	public static native void glGetVariantIntegervEXT(int id, int value, IntBuffer piData);

	public static native ByteBuffer glGetVariantPointervEXT(int id, int value, int size);

	public static native void glGetVertexAttribdvARB(int index, int pname, DoubleBuffer pdParams);

	public static native void glGetVertexAttribdvNV(int index, int pname, DoubleBuffer pdParams);

	public static native void glGetVertexAttribfvARB(int index, int pname, FloatBuffer pfParams);

	public static native void glGetVertexAttribfvNV(int index, int pname, FloatBuffer pfParams);

	public static native void glGetVertexAttribivARB(int index, int pname, IntBuffer piParams);

	public static native void glGetVertexAttribivNV(int index, int pname, IntBuffer piParams);

	public static native ByteBuffer glGetVertexAttribPointervARB(
		int index,
		int pname,
		int size);

	public static native ByteBuffer glGetVertexAttribPointervNV(
		int index,
		int pname,
		int size);

	public static native void glInsertComponentEXT(int res, int src, int num);

	public static native boolean glIsFenceNV(int fence);

	public static native boolean glIsObjectBufferATI(int buffer);

	public static native boolean glIsOcclusionQueryNV(int id);

	public static native boolean glIsProgramARB(int program);

	// #ifdef _WIN32

	public static native boolean glIsProgramNV(int id);

	public static native boolean glIsVariantEnabledEXT(int id, int cap);

	public static native void glLoadProgramNV(int target, int id, int len, ByteBuffer pProgram);

	public static native void glLoadTransposeMatrixdARB(DoubleBuffer pdMtx); // m[16]

	public static native void glLoadTransposeMatrixfARB(FloatBuffer pfMtx); // m[16]

	public static native void glLockArraysEXT(int first, int count);

	public static native void glMapControlPointsNV(
		int target,
		int index,
		int type,
		int ustride,
		int vstride,
		int uorder,
		int vorder,
		boolean packed,
		Buffer pPoints);

	public static native void glMapParameterfvNV(int target, int pname, FloatBuffer pfParams);

	public static native void glMapParameterivNV(int target, int pname, IntBuffer piParams);

	public static native void glMatrixIndexPointerARB(
		int size,
		int type,
		int stride,
		Buffer pPointer);

	public static native void glMatrixIndexubvARB(int size, ByteBuffer pIndices);

	public static native void glMatrixIndexuivARB(int size, IntBuffer piIndices);

	public static native void glMatrixIndexusvARB(int size, CharBuffer psIndices);

	public static native void glMultiDrawArraysEXT(
		int mode,
		IntBuffer piFirst,
		IntBuffer piCount,
		int primcount);

/*	public static native void glMultiDrawElementsEXT(
		int mode,
		int piCount,
		int type,
		int pIndices,
		int primcount);
*/
	public static native void glMultiTexCoord1dARB(int target, double s);

	public static native void glMultiTexCoord1fARB(int target, float s);

	public static native void glMultiTexCoord1iARB(int target, int s);

	public static native void glMultiTexCoord1sARB(int target, short s);

	public static native void glMultiTexCoord2dARB(int target, double s, double t);

	public static native void glMultiTexCoord2fARB(int target, float s, float t);

	public static native void glMultiTexCoord2iARB(int target, int s, int t);

	public static native void glMultiTexCoord2sARB(int target, short s, short t);

	public static native void glMultiTexCoord3dARB(
		int target,
		double s,
		double t,
		double r);

	public static native void glMultiTexCoord3fARB(
		int target,
		float s,
		float t,
		float r);

	public static native void glMultiTexCoord3iARB(int target, int s, int t, int r);

	public static native void glMultiTexCoord3sARB(
		int target,
		short s,
		short t,
		short r);

	public static native void glMultiTexCoord4dARB(
		int target,
		double s,
		double t,
		double r,
		double q);

	public static native void glMultiTexCoord4fARB(
		int target,
		float s,
		float t,
		float r,
		float q);

	public static native void glMultiTexCoord4iARB(
		int target,
		int s,
		int t,
		int r,
		int q);

	public static native void glMultiTexCoord4sARB(
		int target,
		short s,
		short t,
		short r,
		short q);

	public static native void glMultTransposeMatrixdARB(DoubleBuffer pdMtx); // m[16]

	public static native void glMultTransposeMatrixfARB(FloatBuffer pfMtx); // m[16]

	public static native int glNewObjectBufferATI(int size, Buffer pPointer, int usage);

	public static native void glNormalStream3bATI(int stream, byte x, byte y, byte z);

	public static native void glNormalStream3dATI(
		int stream,
		double x,
		double y,
		double z);

	public static native void glNormalStream3fATI(int stream, float x, float y, float z);

	public static native void glNormalStream3iATI(int stream, int x, int y, int z);

	public static native void glNormalStream3sATI(int stream, short x, short y, short z);

	public static native void glPassTexCoordATI(int dst, int coord, int swizzle);

	public static native void glPNTrianglesfATI(int pname, float param);

	public static native void glPNTrianglesiATI(int pname, int param);

	public static native void glPointParameterfARB(int pname, float param);

	public static native void glPointParameterfEXT(int pname, float param);

	public static native void glPointParameterfvARB(int pname, FloatBuffer pfParams);

	public static native void glPointParameterfvEXT(int pname, FloatBuffer pfParams);

	public static native void glPointParameteriNV(int pname, int param);

	public static native void glPointParameterivNV(int pname, IntBuffer piParams);

	public static native void glProgramEnvParameter4dARB(
		int target,
		int index,
		double x,
		double y,
		double z,
		double w);

	public static native void glProgramEnvParameter4fARB(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glProgramLocalParameter4dARB(
		int target,
		int index,
		double x,
		double y,
		double z,
		double w);

	public static native void glProgramLocalParameter4fARB(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glProgramParameter4dNV(
		int target,
		int index,
		double x,
		double y,
		double z,
		double w);

	public static native void glProgramParameter4fNV(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glProgramParameters4dvNV(
		int target,
		int index,
		int num,
		DoubleBuffer pdParams);

	public static native void glProgramParameters4fvNV(
		int target,
		int index,
		int num,
		FloatBuffer pfParams);

	public static native void glProgramStringARB(
		int target,
		int format,
		int len,
		Buffer pString);

	public static native void glRequestResidentProgramsNV(int n, IntBuffer piIDs);

	public static native void glSampleCoverageARB(float value, boolean invert);

	public static native void glSampleMapATI(int dst, int interp, int swizzle);

	public static native void glSecondaryColor3bEXT(byte red, byte green, byte blue);

	public static native void glSecondaryColor3dEXT(
		double red,
		double green,
		double blue);

	public static native void glSecondaryColor3fEXT(float red, float green, float blue);

	public static native void glSecondaryColor3iEXT(int red, int green, int blue);

	public static native void glSecondaryColor3sEXT(short red, short green, short blue);

	public static native void glSecondaryColor3ubEXT(byte red, byte green, byte blue);

	public static native void glSecondaryColor3uiEXT(int red, int green, int blue);

	public static native void glSecondaryColor3usEXT(short red, short green, short blue);

	public static native void glSecondaryColorPointerEXT(
		int size,
		int type,
		int stride,
		Buffer pPointer);

	public static native void glSetFenceNV(int fence, int condition);

	public static native void glSetFragmentShaderConstantATI(int dst, FloatBuffer pfValue);

	public static native void glSetInvariantEXT(int id, int type, Buffer pAddr);

	public static native void glSetLocalConstantEXT(int id, int type, Buffer pAddr);

	public static native void glShaderOp1EXT(int op, int res, int arg1);

	public static native void glShaderOp2EXT(int op, int res, int arg1, int arg2);

	public static native void glShaderOp3EXT(
		int op,
		int res,
		int arg1,
		int arg2,
		int arg3);

	public static native void glSwizzleEXT(
		int res,
		int in,
		int outX,
		int outY,
		int outZ,
		int outW);

	public static native boolean glTestFenceNV(int fence);

	public static native void glTexBumpParameterfvATI(int pname, FloatBuffer pfParam);

	public static native void glTexBumpParameterivATI(int pname, IntBuffer piParam);

	public static native void glTrackMatrixNV(
		int target,
		int address,
		int matrix,
		int transform);

	public static native void glUnlockArraysEXT();

	public static native void glUpdateObjectBufferATI(
		int buffer,
		int offset,
		int size,
		Buffer pPointer,
		int preserve);

	public static native void glVariantArrayObjectATI(
		int id,
		int type,
		int stride,
		int buffer,
		int offset);

	public static native void glVariantbvEXT(int id, ByteBuffer pAddr);

	public static native void glVariantdvEXT(int id, DoubleBuffer pdAddr);

	public static native void glVariantfvEXT(int id, FloatBuffer pfAddr);

	public static native void glVariantivEXT(int id, IntBuffer piAddr);

	public static native void glVariantPointerEXT(
		int id,
		int type,
		int stride,
		Buffer pAddr);

	public static native void glVariantsvEXT(int id, CharBuffer psAddr);

	public static native void glVariantubvEXT(int id, ByteBuffer pAddr);

	public static native void glVariantuivEXT(int id, IntBuffer piAddr);

	public static native void glVariantusvEXT(int id, CharBuffer psAddr);

	public static native void glVertexArrayRangeNV(int size, Buffer pPointer);

	public static native void glVertexAttrib1dARB(int index, double x);

	public static native void glVertexAttrib1dNV(int index, double x);

	public static native void glVertexAttrib1fARB(int index, float x);

	public static native void glVertexAttrib1fNV(int index, float x);

	public static native void glVertexAttrib1sARB(int index, short x);

	public static native void glVertexAttrib1sNV(int index, short x);

	public static native void glVertexAttrib2dARB(int index, double x, double y);

	public static native void glVertexAttrib2dNV(int index, double x, double y);

	public static native void glVertexAttrib2fARB(int index, float x, float y);

	public static native void glVertexAttrib2fNV(int index, float x, float y);

	public static native void glVertexAttrib2sARB(int index, short x, short y);

	public static native void glVertexAttrib2sNV(int index, short x, short y);

	public static native void glVertexAttrib3dARB(
		int index,
		double x,
		double y,
		double z);

	public static native void glVertexAttrib3dNV(
		int index,
		double x,
		double y,
		double z);

	public static native void glVertexAttrib3fARB(int index, float x, float y, float z);

	public static native void glVertexAttrib3fNV(int index, float x, float y, float z);

	public static native void glVertexAttrib3sARB(int index, short x, short y, short z);

	public static native void glVertexAttrib3sNV(int index, short x, short y, short z);

	public static native void glVertexAttrib4bvARB(int index, ByteBuffer pV);

	public static native void glVertexAttrib4dARB(
		int index,
		double x,
		double y,
		double z,
		double w);

	public static native void glVertexAttrib4dNV(
		int index,
		double x,
		double y,
		double z,
		double w);

	public static native void glVertexAttrib4fARB(
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glVertexAttrib4fNV(
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glVertexAttrib4ivARB(int index, IntBuffer piV);

	public static native void glVertexAttrib4NbvARB(int index, ByteBuffer pV);

	public static native void glVertexAttrib4NivARB(int index, IntBuffer piV);

	public static native void glVertexAttrib4NsvARB(int index, CharBuffer psV);

	public static native void glVertexAttrib4NubARB(
		int index,
		byte x,
		byte y,
		byte z,
		byte w);

	public static native void glVertexAttrib4NubvARB(int index, ByteBuffer pV);

	public static native void glVertexAttrib4NuivARB(int index, IntBuffer piV);

	public static native void glVertexAttrib4NusvARB(int index, CharBuffer psV);

	public static native void glVertexAttrib4sARB(
		int index,
		short x,
		short y,
		short z,
		short w);

	public static native void glVertexAttrib4sNV(
		int index,
		short x,
		short y,
		short z,
		short w);

	public static native void glVertexAttrib4ubNV(
		int index,
		byte x,
		byte y,
		byte z,
		byte w);

	public static native void glVertexAttrib4ubvARB(int index, ByteBuffer pV);

	public static native void glVertexAttrib4ubvNV(int index, ByteBuffer pV);

	public static native void glVertexAttrib4uivARB(int index, IntBuffer piV);

	public static native void glVertexAttrib4usvARB(int index, CharBuffer psV);

	public static native void glVertexAttribPointerARB(
		int index,
		int size,
		int type,
		boolean normalized,
		int stride,
		Buffer pPointer);

	public static native void glVertexAttribPointerNV(
		int index,
		int size,
		int type,
		int stride,
		Buffer pPointer);

	public static native void glVertexAttribs1dvNV(int index, int n, DoubleBuffer pdV);

	public static native void glVertexAttribs1fvNV(int index, int n, FloatBuffer pfV);

	public static native void glVertexAttribs1svNV(int index, int n, CharBuffer psV);

	public static native void glVertexAttribs2dvNV(int index, int n, DoubleBuffer pdV);

	public static native void glVertexAttribs2fvNV(int index, int n, FloatBuffer pfV);

	public static native void glVertexAttribs2svNV(int index, int n, CharBuffer psV);

	public static native void glVertexAttribs3dvNV(int index, int n, DoubleBuffer pdV);

	public static native void glVertexAttribs3fvNV(int index, int n, FloatBuffer pfV);

	public static native void glVertexAttribs3svNV(int index, int n, CharBuffer psV);

	public static native void glVertexAttribs4dvNV(int index, int n, DoubleBuffer pdV);

	public static native void glVertexAttribs4fvNV(int index, int n, FloatBuffer pfV);

	public static native void glVertexAttribs4svNV(int index, int n, CharBuffer psV);

	public static native void glVertexAttribs4ubvNV(int index, int n, ByteBuffer pV);

	public static native void glVertexBlendARB(int count);

	public static native void glVertexBlendEnvfATI(int pname, float param);

	public static native void glVertexBlendEnviATI(int pname, int param);

	public static native void glVertexStream2dATI(int stream, double x, double y);

	public static native void glVertexStream2fATI(int stream, float x, float y);

	public static native void glVertexStream2iATI(int stream, int x, int y);

	public static native void glVertexStream2sATI(int stream, short x, short y);

	public static native void glVertexStream3dATI(
		int stream,
		double x,
		double y,
		double z);

	public static native void glVertexStream3fATI(int stream, float x, float y, float z);

	public static native void glVertexStream3iATI(int stream, int x, int y, int z);

	public static native void glVertexStream3sATI(int stream, short x, short y, short z);

	public static native void glVertexStream4dATI(
		int stream,
		double x,
		double y,
		double z,
		double w);

	public static native void glVertexStream4fATI(
		int stream,
		float x,
		float y,
		float z,
		float w);

	public static native void glVertexStream4iATI(
		int stream,
		int x,
		int y,
		int z,
		int w);

	public static native void glVertexStream4sATI(
		int stream,
		short x,
		short y,
		short z,
		short w);

	public static native void glVertexWeightfEXT(float weight);

	public static native void glVertexWeightPointerEXT(
		int size,
		int type,
		int stride,
		Buffer pPointer);

	public static native void glWeightbvARB(int size, ByteBuffer pWeights);

	public static native void glWeightdvARB(int size, DoubleBuffer pdWeights);

	public static native void glWeightfvARB(int size, FloatBuffer pfWeights);

	public static native void glWeightivARB(int size, IntBuffer piWeights);

	public static native void glWeightPointerARB(
		int size,
		int type,
		int stride,
		Buffer pPointer);

	public static native void glWeightsvARB(int size, CharBuffer psWeights);

	public static native void glWeightubvARB(int size, ByteBuffer pWeights);

	public static native void glWeightuivARB(int size, IntBuffer piWeights);

	public static native void glWeightusvARB(int size, CharBuffer psWeights);

	public static native ByteBuffer glXAllocateMemoryNV(
		int size,
		float readFrequency,
		float writeFrequency,
		float priority);

	public static native void glXFreeMemoryNV(ByteBuffer pointer);

	// #ifdef _WIN32

	public static native ByteBuffer wglAllocateMemoryNV(
		int size,
		float readFrequency,
		float writeFrequency,
		float priority);

	public static native boolean wglBindTexImageARB(int hPbuffer, int iBuffer);

/*	public static native boolean wglChoosePixelFormatARB(
		int hdc,
		int piAttribIList,
		int pfAttribFList,
		int nMaxFormats,
		int piFormats,
		int piNumFormats);
*/
	public static native int wglCreateBufferRegionARB(
		int hdc,
		int iLayerPlane,
		int uType);

/*	public static native int wglCreatePbufferARB(
		int hDC,
		int iPixelFormat,
		int iWidth,
		int iHeight,
		int piAttribList);
*/
	public static native void wglDeleteBufferRegionARB(Buffer hRegion);

/*	public static native boolean wglDestroyPbufferARB(int hPbuffer);*/

	public static native void wglFreeMemoryNV(ByteBuffer pointer);

	public static native int wglGetCurrentReadDCARB();

	public static native String wglGetExtensionsStringARB(int hdc);

	public static native String wglGetExtensionsStringEXT();

//	public static native int wglGetPbufferDCARB(int hPbuffer);

/*	public static native boolean wglGetPixelFormatAttribfvARB(
		int hdc,
		int iPixelFormat,
		int iLayerPlane,
		int nAttributes,
		int piAttributes,
		int pfValues);

	public static native boolean wglGetPixelFormatAttribivARB(
		int hdc,
		int iPixelFormat,
		int iLayerPlane,
		int nAttributes,
		int piAttributes,
		int piValues);
*/
	public static native int wglGetSwapIntervalEXT();

/*	public static native boolean wglMakeContextCurrentARB(
		int hDrawDC,
		int hReadDC,
		int hglrc);

	public static native boolean wglQueryPbufferARB(
		int hPbuffer,
		int iAttribute,
		int piValue);

	public static native int wglReleasePbufferDCARB(int hPbuffer, int hDC);
*/
	public static native boolean wglReleaseTexImageARB(
		int hPbuffer,
		int iBuffer);

	public static native boolean wglRestoreBufferRegionARB(
		Buffer hRegion,
		int x,
		int y,
		int width,
		int height,
		int xSrc,
		int ySrc);

	public static native boolean wglSaveBufferRegionARB(
		Buffer hRegion,
		int x,
		int y,
		int width,
		int height);

/*	public static native boolean wglSetPbufferAttribARB(
		int hPbuffer,
		int piAttribList);
*/
	public static native boolean wglSwapIntervalEXT(int interval);

	public static native void glWindowPos2dARB(double x, double y);

	public static native void glWindowPos2fARB(float x, float y);

	public static native void glWindowPos2iARB(int x, int y);

	public static native void glWindowPos2sARB(short x, short y);

	public static native void glWindowPos3dARB(double x, double y, double z);

	public static native void glWindowPos3fARB(float x, float y, float z);

	public static native void glWindowPos3iARB(int x, int y, int z);

	public static native void glWindowPos3sARB(short x, short y, short z);

	public static native void glWriteMaskEXT(
		int res,
		int in,
		int outX,
		int outY,
		int outZ,
		int outW);

	public static native void glBindBufferARB(int target, int buffer);
	public static native void glDeleteBuffersARB(int n, IntBuffer buffers);
	public static native void glGenBuffersARB(int n, IntBuffer buffers);
	public static native boolean glIsBufferARB(int buffer);
	public static native void glBufferDataARB(int target, int size, Buffer data, int usage);
	public static native void glBufferSubDataARB(int target, int offset, int size, Buffer data);
	public static native void glGetBufferSubDataARB(int target, int offset, int size, Buffer data);
	/**
	 * glMapBufferARB maps a gl vertex buffer buffer to a ByteBuffer. The oldBuffer argument can be null, in
	 * which case a new ByteBuffer will be created, pointing to the returned memory. If oldBuffer is non-null,
	 * it will be returned if it points to the same mapped memory, otherwise a new ByteBuffer is created.
	 * That way, an application will normally use glMapBufferARB like this:
	 *
	 * ByteBuffer mapped_buffer;
	 * mapped_buffer = glMapBufferARB(..., ..., ..., null);
	 * ...
	 * // Another map on the same buffer
	 * mapped_buffer = glMapBufferARB(..., ..., ..., mapped_buffer);
	 *
	 * @param size The size of the buffer area.
	 * @param oldBuffer A ByteBuffer. If this argument points to the same address as the new mapping, it will be returned and
	 * no new buffer will be created. In that case, size is ignored.
	 * @return A ByteBuffer representing the mapped buffer memory.
	 */
	public static native ByteBuffer glMapBufferARB(int target, int access, int size, ByteBuffer oldBuffer);
	public static native boolean glUnmapBufferARB(int target);
	public static native void glGetBufferParameterivARB(int target, int pname, IntBuffer params);
	public static native ByteBuffer glGetBufferPointervARB(int target, int pname, int size);
}
