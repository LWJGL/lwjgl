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
import java.nio.ShortBuffer;
import java.nio.FloatBuffer;
import java.nio.Buffer;

/**
 * $Id$
 *
 * The GL itself, with all supported extensions, based on OpenGL 1.4.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public abstract class GL extends CoreGL14 implements GLConstants {
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

	public static boolean glAreProgramsResidentNV(IntBuffer piIDs, ByteBuffer pbResidences) {
		assert piIDs.remaining() == pbResidences.remaining(): "piIDs.remaining() != pbResidences.remaining()";
		return nglAreProgramsResidentNV(piIDs.remaining(), piIDs, piIDs.position(), pbResidences, pbResidences.position());
	}
	private static native boolean nglAreProgramsResidentNV(int n, IntBuffer piIDs, int piIDs_offset, ByteBuffer pbResidences, int pbResidences_offset);

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

	public static void glCombinerParameterNV(int pname, FloatBuffer pfParams) {
		nglCombinerParameterfvNV(pname, pfParams, pfParams.position());
	}
	private static native void nglCombinerParameterfvNV(int pname, FloatBuffer pfParams, int pfParams_offset);

	public static native void glCombinerParameteriNV(int pname, int param);

	public static void glCombinerParameterNV(int pname, IntBuffer piParams) {
		nglCombinerParameterivNV(pname, piParams, piParams.position());
	}
	private static native void nglCombinerParameterivNV(int pname, IntBuffer piParams, int piParams_offset);

	public static void glCombinerStageParameterNV(int stage, int pname, FloatBuffer pfParams) {
		nglCombinerStageParameterfvNV(stage, pname, pfParams, pfParams.position());
	}
	private static native void nglCombinerStageParameterfvNV(int stage, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ByteBuffer pData) {
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, ShortBuffer pData) {
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, IntBuffer pData) {
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, FloatBuffer pData) {
		nglCompressedTexImage1DARB(target, level, internalformat, width, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexImage1DARB(int target, int level, int internalformat, int width, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ByteBuffer pData) {
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, ShortBuffer pData) {
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, IntBuffer pData) {
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, FloatBuffer pData) {
		nglCompressedTexImage2DARB(target, level, internalformat, width, height, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexImage2DARB(int target, int level, int internalformat, int width, int height, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		nglCompressedTexImage3DARB(target, level, internalformat, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexImage3DARB(int target, int level, int internalformat, int width, int height, int depth, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ByteBuffer pData) {
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, ShortBuffer pData) {
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, IntBuffer pData) {
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, FloatBuffer pData) {
		nglCompressedTexSubImage1DARB(target, level, xoffset, width, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexSubImage1DARB(int target, int level, int xoffset, int width, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ByteBuffer pData) {
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, ShortBuffer pData) {
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, IntBuffer pData) {
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, FloatBuffer pData) {
		nglCompressedTexSubImage2DARB(target, level, xoffset, yoffset, width, height, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexSubImage2DARB(int target, int level, int xoffset, int yoffset, int width, int height, int border, int imageSize, Buffer pData, int pData_offset);

	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ByteBuffer pData) {
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position());
	}
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, ShortBuffer pData) {
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position()<<1);
	}
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, IntBuffer pData) {
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	public static void glCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, FloatBuffer pData) {
		nglCompressedTexSubImage3DARB(target, level, xoffset, yoffset, zoffset, width, height, depth, border, imageSize, pData, pData.position()<<2);
	}
	private static native void nglCompressedTexSubImage3DARB(int target, int level, int xoffset, int yoffset, int zoffset, int width, int height, int depth, int border, int imageSize, Buffer pData, int pData_offset);

	public static native void glCurrentPaletteMatrixARB(int index);

	public static void glDeleteFencesNV(IntBuffer piFences) {
		nglDeleteFencesNV(piFences.remaining(), piFences, piFences.position());
	}
	private static native void nglDeleteFencesNV(int n, IntBuffer piFences, int piFences_offset);

	public static native void glDeleteFragmentShaderATI(int id);

	public static void glDeleteOcclusionQueriesNV(IntBuffer piIDs) {
		nglDeleteOcclusionQueriesNV(piIDs.remaining(), piIDs, piIDs.position());
	}
	private static native void nglDeleteOcclusionQueriesNV(int n, IntBuffer piIDs, int piIDs_offset);

	public static void glDeleteProgramsARB(IntBuffer piPrograms) {
		nglDeleteProgramsARB(piPrograms.remaining(), piPrograms, piPrograms.position());
	}
	private static native void nglDeleteProgramsARB(int n, IntBuffer piPrograms, int piPrograms_offset);

	public static void glDeleteProgramsNV(IntBuffer piIDs) {
		nglDeleteProgramsNV(piIDs.remaining(), piIDs, piIDs.position());
	}
	private static native void nglDeleteProgramsNV(int n, IntBuffer piIDs, int piIDs_offset);

	public static native void glDeleteVertexShaderEXT(int id);

	public static native void glDisableVariantClientStateEXT(int id);

	public static native void glDisableVertexAttribArrayARB(int index);

	public static native void glDrawElementArrayATI(int mode, int count);

	public static native void glDrawRangeElementArrayATI(
		int mode,
		int start,
		int end,
		int count);

	public static void glDrawRangeElementsEXT(int mode, int start, int end, ByteBuffer pIndices) {
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), GL_UNSIGNED_BYTE, pIndices, pIndices.position());
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, ShortBuffer pIndices) {
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), GL_UNSIGNED_SHORT, pIndices, pIndices.position()<<1);
	}
	public static void glDrawRangeElementsEXT(int mode, int start, int end, IntBuffer pIndices) {
		nglDrawRangeElementsEXT(mode, start, end, pIndices.remaining(), GL_UNSIGNED_INT, pIndices, pIndices.position()<<2);
	}
	private static native void nglDrawRangeElementsEXT(int mode, int start, int end, int count, int type, Buffer pIndices, int pIndices_offset);

	public static void glElementPointerATI(ByteBuffer pPointer) {
		nglElementPointerATI(GL_UNSIGNED_BYTE, pPointer, pPointer.position());
	}
	public static void glElementPointerATI(ShortBuffer pPointer) {
		nglElementPointerATI(GL_UNSIGNED_SHORT, pPointer, pPointer.position()<<1);
	}
	public static void glElementPointerATI(IntBuffer pPointer) {
		nglElementPointerATI(GL_UNSIGNED_INT, pPointer, pPointer.position()<<2);
	}
	private static native void nglElementPointerATI(int type, Buffer pPointer, int pPointer_offset);

	public static native void glEnableVariantClientStateEXT(int id);

	public static native void glEnableVertexAttribArrayARB(int index);

	public static native void glEndFragmentShaderATI();

	public static native void glEndOcclusionQueryNV();

	public static native void glEndVertexShaderEXT();

	public static native void glEvalMapsNV(int target, int mode);

	public static void glExecuteProgramNV(int target, int id, FloatBuffer pfParams) {
		nglExecuteProgramNV(target, id, pfParams, pfParams.position());
	}
	private static native void nglExecuteProgramNV(int target, int id, FloatBuffer pfParams, int pfParams_offset);

	public static native void glExtractComponentEXT(int res, int src, int num);

	public static native void glFinalCombinerInputNV(
		int variable,
		int input,
		int mapping,
		int componentUsage);

	public static native void glFinishFenceNV(int fence);

	public static native void glFlushVertexArrayRangeNV();

	public static native void glFogCoordfEXT(float coord);

	public static void glFogCoordPointerEXT(int stride, FloatBuffer pPointer) {
		nglFogCoordPointerEXT(GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglFogCoordPointerEXT(int type, int stride, Buffer pPointer, int pPointer_offset);

	public static native void glFreeObjectBufferATI(int buffer);

	public static void glGenFencesNV(IntBuffer piFences) {
		nglGenFencesNV(piFences.remaining(), piFences, piFences.position());
	}
	private static native void nglGenFencesNV(int n, IntBuffer piFences, int piFences_offset);

	public static native int glGenFragmentShadersATI(int range);

	public static void glGenOcclusionQueriesNV(IntBuffer piIDs) {
		nglGenOcclusionQueriesNV(piIDs.remaining(), piIDs, piIDs.position());
	}
	private static native void nglGenOcclusionQueriesNV(int n, IntBuffer piIDs, int piIDs_offset);

	public static void glGenProgramsARB(IntBuffer piPrograms) {
		nglGenProgramsARB(piPrograms.remaining(), piPrograms, piPrograms.position());
	}
	private static native void nglGenProgramsARB(int n, IntBuffer piPrograms, int piPrograms_offset);

	public static void glGenProgramsNV(IntBuffer piIDs) {
		nglGenProgramsNV(piIDs.remaining(), piIDs, piIDs.position());
	}
	private static native void nglGenProgramsNV(int n, IntBuffer piIDs, int piIDs_offset);

	public static native int glGenSymbolsEXT(
		int dataType,
		int storageType,
		int range,
		int components);

	public static native int glGenVertexShadersEXT(int range);

	public static void glGetArrayObjectATI(int array, int pname, FloatBuffer pfParams) {
		nglGetArrayObjectfvATI(array, pname, pfParams, pfParams.position());
	}
	private static native void nglGetArrayObjectfvATI(int array, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetArrayObjectATI(int array, int pname, IntBuffer piParams) {
		nglGetArrayObjectivATI(array, pname, piParams, piParams.position());
	}
	private static native void nglGetArrayObjectivATI(int array, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetCombinerInputParameterNV(int stage, int portion, int variable, int pname, FloatBuffer pfParams) {
		nglGetCombinerInputParameterfvNV(stage, portion, variable, pname, pfParams, pfParams.position());
	}
	private static native void nglGetCombinerInputParameterfvNV(int stage, int portion, int variable, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetCombinerInputParameterNV(int stage, int portion, int variable, int pname, IntBuffer piParams) {
		nglGetCombinerInputParameterivNV(stage, portion, variable, pname, piParams, piParams.position());
	}
	private static native void nglGetCombinerInputParameterivNV(int stage, int portion, int variable, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetCombinerOutputParameterNV(int stage, int portion, int pname, FloatBuffer pfParams) {
		nglGetCombinerOutputParameterfvNV(stage, portion, pname, pfParams, pfParams.position());
	}
	private static native void nglGetCombinerOutputParameterfvNV(int stage, int portion, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetCombinerOutputParameterNV(int stage, int portion, int pname, IntBuffer piParams) {
		nglGetCombinerOutputParameterivNV(stage, portion, pname, piParams, piParams.position());
	}
	private static native void nglGetCombinerOutputParameterivNV(int stage, int portion, int pname, IntBuffer piParams, int pfParams_offset);

	public static void glGetCombinerStageParameterNV(int stage, int pname, FloatBuffer pfParams) {
		nglGetCombinerStageParameterfvNV(stage, pname, pfParams, pfParams.position());
	}
	private static native void nglGetCombinerStageParameterfvNV(int stage, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetCompressedTexImageARB(int target, int lod, ByteBuffer pImg) {
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position());
	}
	public static void glGetCompressedTexImageARB(int target, int lod, ShortBuffer pImg) {
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position()<<1);
	}
	public static void glGetCompressedTexImageARB(int target, int lod, IntBuffer pImg) {
		nglGetCompressedTexImageARB(target, lod, pImg, pImg.position()<<2);
	}
	private static native void nglGetCompressedTexImageARB(int target, int lod, Buffer pImg, int pImg_offset);

	public static void glGetFenceNV(int fence, int pname, IntBuffer piParams) {
		nglGetFenceivNV(fence, pname, piParams, piParams.position());
	}
	private static native void nglGetFenceivNV(int fence, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetFinalCombinerInputParameterNV(int variable, int pname, FloatBuffer pfParams) {
		nglGetFinalCombinerInputParameterfvNV(variable, pname, pfParams, pfParams.position());
	}
	private static native void nglGetFinalCombinerInputParameterfvNV(int variable, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetFinalCombinerInputParameterNV(int variable, int pname, IntBuffer piParams) {
		nglGetFinalCombinerInputParameterivNV(variable, pname, piParams, piParams.position());
	}
	private static native void nglGetFinalCombinerInputParameterivNV(int variable, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetInvariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		nglGetInvariantBooleanvEXT(id, value, pbData, pbData.position());
	}
	private static native void nglGetInvariantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_offset);

	public static void glGetInvariantFloatEXT(int id, int value, FloatBuffer pfData) {
		nglGetInvariantFloatvEXT(id, value, pfData, pfData.position());
	}
	private static native void nglGetInvariantFloatvEXT(int id, int value, FloatBuffer pfData, int pfData_offset);

	public static void glGetInvariantIntegerEXT(int id, int value, IntBuffer piData) {
		nglGetInvariantIntegervEXT(id, value, piData, piData.position());
	}
	private static native void nglGetInvariantIntegervEXT(int id, int value, IntBuffer piData, int piData_offset);

	public static void glGetLocalConstantBooleanEXT(int id, int value, ByteBuffer pbData) {
		nglGetLocalConstantBooleanvEXT(id, value, pbData, pbData.position());
	}
	private static native void nglGetLocalConstantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_offset);

	public static void glGetLocalConstantFloatEXT(int id, int value, FloatBuffer pfData) {
		nglGetLocalConstantFloatvEXT(id, value, pfData, pfData.position());
	}
	private static native void nglGetLocalConstantFloatvEXT(int id, int value, FloatBuffer pfData, int pfData_offset);

	public static void glGetLocalConstantIntegerEXT(int id, int value, IntBuffer piData) {
		nglGetLocalConstantIntegervEXT(id, value, piData, piData.position());
	}	
	private static native void nglGetLocalConstantIntegervEXT(int id, int value, IntBuffer piData, int piData_offset);

	public static void glGetMapAttribParameterNV(int target, int index, int pname, FloatBuffer pfParams) {
		nglGetMapAttribParameterfvNV(target, index, pname, pfParams, pfParams.position());
	}
	private static native void nglGetMapAttribParameterfvNV(int target, int index, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetMapAttribParameterNV(int target, int index, int pname, IntBuffer piParams) {
		nglGetMapAttribParameterivNV(target, index, pname, piParams, piParams.position());
	}
	private static native void nglGetMapAttribParameterivNV(int target, int index, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, FloatBuffer pPoints) {
		nglGetMapControlPointsNV(target, index, type, ustride, vstride, packed, pPoints, pPoints.position()<<2);
	}
	private static native void nglGetMapControlPointsNV(int target, int index, int type, int ustride, int vstride, boolean packed, Buffer pPoints, int pPoints_offset);

	public static void glGetMapParameterNV(int target, int pname, FloatBuffer pfParams) {
		nglGetMapParameterfvNV(target, pname, pfParams, pfParams.position());
	}
	private static native void nglGetMapParameterfvNV(int target, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetMapParameterNV(int target, int pname, IntBuffer piParams) {
		nglGetMapParameterivNV(target, pname, piParams, piParams.position());
	}
	private static native void nglGetMapParameterivNV(int target, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetObjectBufferATI(int buffer, int pname, FloatBuffer pfParams) {
		nglGetObjectBufferfvATI(buffer, pname, pfParams, pfParams.position());
	}
	private static native void nglGetObjectBufferfvATI(int buffer, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetObjectBufferATI(int buffer, int pname, IntBuffer piParams) {
		nglGetObjectBufferivATI(buffer, pname, piParams, piParams.position());
	}
	private static native void nglGetObjectBufferivATI(int buffer, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetOcclusionQueryNV(int id, int pname, IntBuffer piParams) {
		nglGetOcclusionQueryivNV(id, pname, piParams, piParams.position());
	}
	private static native void nglGetOcclusionQueryivNV(int id, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetOcclusionQueryuNV(int id, int pname, IntBuffer piParams) {
		nglGetOcclusionQueryuivNV(id, pname, piParams, piParams.position());
	}
	private static native void nglGetOcclusionQueryuivNV(int id, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetProgramEnvParameterARB(int target, int index, FloatBuffer pfParams) {
		nglGetProgramEnvParameterfvARB(target, index, pfParams, pfParams.position());
	}
	private static native void nglGetProgramEnvParameterfvARB(int target, int index, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetProgramARB(int target, int pname, IntBuffer piParams) {
		nglGetProgramivARB(target, pname, piParams, piParams.position());
	}
	private static native void nglGetProgramivARB(int target, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetProgramNV(int id, int pname, IntBuffer piParams) {
		nglGetProgramivNV(id, pname, piParams, piParams.position());
	}
	private static native void nglGetProgramivNV(int id, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetProgramLocalParameterARB(int target, int index, FloatBuffer pfParams) {
		nglGetProgramLocalParameterfvARB(target, index, pfParams, pfParams.position());
	}
	private static native void nglGetProgramLocalParameterfvARB(int target, int index, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetProgramParameterNV(int target, int index, int pname, FloatBuffer pfParams) {
		nglGetProgramParameterfvNV(target, index, pname, pfParams, pfParams.position());
	}
	private static native void nglGetProgramParameterfvNV(int target, int index, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetProgramStringARB(int target, int pname, ByteBuffer pString) {
		nglGetProgramStringARB(target, pname, pString, pString.position());
	}
	private static native void nglGetProgramStringARB(int target, int pname, Buffer pString, int pString_offset);

	public static void glGetProgramStringNV(int id, int pname, ByteBuffer pProgram) {
		nglGetProgramStringNV(id, pname, pProgram, pProgram.position());
	}
	private static native void nglGetProgramStringNV(int id, int pname, ByteBuffer pProgram, int pProgram_offset);

	public static void glGetTexBumpParameterATI(int pname, FloatBuffer pfParam) {
		nglGetTexBumpParameterfvATI(pname, pfParam, pfParam.position());
	}
	private static native void nglGetTexBumpParameterfvATI(int pname, FloatBuffer pfParam, int pfParam_offset);

	public static void glGetTexBumpParameterATI(int pname, IntBuffer piParam) {
		nglGetTexBumpParameterivATI(pname, piParam, piParam.position());
	}
	private static native void nglGetTexBumpParameterivATI(int pname, IntBuffer piParam, int piParam_offset);

	public static void glGetTrackMatrixNV(int target, int address, int pname, IntBuffer piParams) {
		nglGetTrackMatrixivNV(target, address, pname, piParams, piParams.position());
	}
	private static native void nglGetTrackMatrixivNV(int target, int address, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetVariantArrayObjectATI(int id, int pname, FloatBuffer pfParams) {
		nglGetVariantArrayObjectfvATI(id, pname, pfParams, pfParams.position());
	}
	private static native void nglGetVariantArrayObjectfvATI(int id, int pname, FloatBuffer pfParams, int pfParams_offset_offset);

	public static void glGetVariantArrayObjectATI(int id, int pname, IntBuffer piParams) {
		nglGetVariantArrayObjectivATI(id, pname, piParams, piParams.position());
	}
	private static native void nglGetVariantArrayObjectivATI(int id, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetVariantBooleanEXT(int id, int value, ByteBuffer pbData) {
		nglGetVariantBooleanvEXT(id, value, pbData, pbData.position());
	}
	private static native void nglGetVariantBooleanvEXT(int id, int value, ByteBuffer pbData, int pbData_offset);

	public static void glGetVariantFloatEXT(int id, int value, FloatBuffer pfData) {
		nglGetVariantFloatvEXT(id, value, pfData, pfData.position());
	}
	private static native void nglGetVariantFloatvEXT(int id, int value, FloatBuffer pfData, int pfData_offset);

	public static void glGetVariantIntegerEXT(int id, int value, IntBuffer piData) {
		nglGetVariantIntegervEXT(id, value, piData, piData.position());
	}
	private static native void nglGetVariantIntegervEXT(int id, int value, IntBuffer piData, int piData_offset);

	public static native ByteBuffer glGetVariantPointerEXT(int id, int value, int size);

	public static void glGetVertexAttribARB(int index, int pname, FloatBuffer pfParams) {
		nglGetVertexAttribfvARB(index, pname, pfParams, pfParams.position());
	}
	private static native void nglGetVertexAttribfvARB(int index, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetVertexAttribNV(int index, int pname, FloatBuffer pfParams) {
		nglGetVertexAttribfvNV(index, pname, pfParams, pfParams.position());
	}
	private static native void nglGetVertexAttribfvNV(int index, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glGetVertexAttribARB(int index, int pname, IntBuffer piParams) {
		nglGetVertexAttribivARB(index, pname, piParams, piParams.position());
	}
	private static native void nglGetVertexAttribivARB(int index, int pname, IntBuffer piParams, int piParams_offset);

	public static void glGetVertexAttribNV(int index, int pname, IntBuffer piParams) {
		nglGetVertexAttribivNV(index, pname, piParams, piParams.position());
	}
	private static native void nglGetVertexAttribivNV(int index, int pname, IntBuffer piParams, int piParams_offset);

	public static native ByteBuffer glGetVertexAttribPointerARB(
		int index,
		int pname,
		int size);

	public static native ByteBuffer glGetVertexAttribPointerNV(
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

	public static void glLoadProgramNV(int target, int id, ByteBuffer pProgram) {
		nglLoadProgramNV(target, id, pProgram.remaining(), pProgram, pProgram.position());
	}
	private static native void nglLoadProgramNV(int target, int id, int len, ByteBuffer pProgram, int pProgram_offset);

	public static void glLoadTransposeMatrixfARB(FloatBuffer pfMtx) {
		nglLoadTransposeMatrixfARB(pfMtx, pfMtx.position());
	}
	private static native void nglLoadTransposeMatrixfARB(FloatBuffer pfMtx, int pfMtx_offset);

	public static native void glLockArraysEXT(int first, int count);

	public static void glMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, FloatBuffer pPoints) {
		nglMapControlPointsNV(target, index, type, ustride, vstride, uorder, vorder, packed, pPoints, pPoints.position()<<2);
	}
	private static native void nglMapControlPointsNV(int target, int index, int type, int ustride, int vstride, int uorder, int vorder, boolean packed, Buffer pPoints, int pPoints_offset);

	public static void glMapParameterNV(int target, int pname, FloatBuffer pfParams) {
		nglMapParameterfvNV(target, pname, pfParams, pfParams.position());
	}
	private static native void nglMapParameterfvNV(int target, int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glMapParameterNV(int target, int pname, IntBuffer piParams) {
		nglMapParameterivNV(target, pname, piParams, piParams.position());
	}
	private static native void nglMapParameterivNV(int target, int pname, IntBuffer piParams, int piParams_offset);

	public static void glMatrixIndexPointerARB(int size, int stride, ByteBuffer pPointer) {
		nglMatrixIndexPointerARB(size, GL_UNSIGNED_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glMatrixIndexPointerARB(int size, int stride, ShortBuffer pPointer) {
		nglMatrixIndexPointerARB(size, GL_UNSIGNED_SHORT, stride, pPointer, pPointer.position()<<1);
	}
	public static void glMatrixIndexPointerARB(int size, int stride, IntBuffer pPointer) {
		nglMatrixIndexPointerARB(size, GL_UNSIGNED_INT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglMatrixIndexPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_offset);

	public static void glMatrixIndexuARB(ByteBuffer pIndices) {
		nglMatrixIndexubvARB(pIndices.remaining(), pIndices, pIndices.position());
	}
	private static native void nglMatrixIndexubvARB(int size, ByteBuffer pIndices, int pIndices_offset);

	public static void glMatrixIndexuARB(IntBuffer piIndices) {
		nglMatrixIndexuivARB(piIndices.remaining(), piIndices, piIndices.position());
	}
	private static native void nglMatrixIndexuivARB(int size, IntBuffer piIndices, int piIndices_offset);

	public static void glMatrixIndexuARB(ShortBuffer psIndices) {
		nglMatrixIndexusvARB(psIndices.remaining(), psIndices, psIndices.position());
	}
	private static native void nglMatrixIndexusvARB(int size, ShortBuffer psIndices, int psIndices_offset);

	public static void glMultiDrawArraysEXT(int mode, IntBuffer piFirst, IntBuffer piCount) {
		assert piFirst.remaining() == piCount.remaining(): "piFirst.remaining() != piCount.remaining()";
		nglMultiDrawArraysEXT(mode, piFirst, piFirst.position(), piCount, piCount.position(), piFirst.remaining());
	}
	private static native void nglMultiDrawArraysEXT(int mode, IntBuffer piFirst, int piFirst_offset, IntBuffer piCount, int piCount_offset, int primcount);

//	public static native void glMultiDrawElementsEXT(int mode, int piCount, int type, int pIndices, int primcount);

	public static native void glMultiTexCoord1fARB(int target, float s);

	public static native void glMultiTexCoord1iARB(int target, int s);

	public static native void glMultiTexCoord1sARB(int target, short s);

	public static native void glMultiTexCoord2fARB(int target, float s, float t);

	public static native void glMultiTexCoord2iARB(int target, int s, int t);

	public static native void glMultiTexCoord2sARB(int target, short s, short t);

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

	public static void glMultTransposeMatrixfARB(FloatBuffer pfMtx) {
		nglMultTransposeMatrixfARB(pfMtx, pfMtx.position());
	}
	private static native void nglMultTransposeMatrixfARB(FloatBuffer pfMtx, int pfMtx_offset);

	public static int glNewObjectBufferATI(int size, ByteBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position() : 0, usage);
	}
	public static int glNewObjectBufferATI(int size, ShortBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position()<<1 : 0, usage);
	}
	public static int glNewObjectBufferATI(int size, FloatBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position()<<2 : 0, usage);
	}
	public static int glNewObjectBufferATI(int size, IntBuffer pPointer, int usage) {
		return nglNewObjectBufferATI(size, pPointer, pPointer != null ? pPointer.position()<<2 : 0, usage);
	}
	private static native int nglNewObjectBufferATI(int size, Buffer pPointer, int pPointer_offset, int usage);

	public static native void glNormalStream3bATI(int stream, byte x, byte y, byte z);

	public static native void glNormalStream3fATI(int stream, float x, float y, float z);

	public static native void glNormalStream3iATI(int stream, int x, int y, int z);

	public static native void glNormalStream3sATI(int stream, short x, short y, short z);

	public static native void glPassTexCoordATI(int dst, int coord, int swizzle);

	public static native void glPNTrianglesfATI(int pname, float param);

	public static native void glPNTrianglesiATI(int pname, int param);

	public static native void glStencilOpSeparateATI(int face, int sfail, int dpfail, int dppass);

	public static native void glStencilFuncSeparateATI(int frontfunc, int backfunc, int ref, int mask);

	public static native void glPointParameterfARB(int pname, float param);

	public static native void glPointParameterfEXT(int pname, float param);

	public static void glPointParameterARB(int pname, FloatBuffer pfParams) {
		nglPointParameterfvARB(pname, pfParams, pfParams.position());
	}
	private static native void nglPointParameterfvARB(int pname, FloatBuffer pfParams, int pfParams_offset);

	public static void glPointParameterEXT(int pname, FloatBuffer pfParams) {
		nglPointParameterfvEXT(pname, pfParams, pfParams.position());
	}
	private static native void nglPointParameterfvEXT(int pname, FloatBuffer pfParams, int pfParams_offset);

	public static native void glPointParameteriNV(int pname, int param);

	public static void glPointParameterNV(int pname, IntBuffer piParams) {
		nglPointParameterivNV(pname, piParams, piParams.position());
	}
	private static native void nglPointParameterivNV(int pname, IntBuffer piParams, int piParams_offset);

	public static native void glProgramEnvParameter4fARB(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glProgramLocalParameter4fARB(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public static native void glProgramParameter4fNV(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public static void glProgramParameters4NV(int target, int index, int num, FloatBuffer pfParams) {
		nglProgramParameters4fvNV(target, index, num, pfParams, pfParams.position());
	}
	private static native void nglProgramParameters4fvNV(int target, int index, int num, FloatBuffer pfParams, int pfParams_offset);

	public static void glProgramStringARB(int target, int format, ByteBuffer pString) {
		nglProgramStringARB(target, format, pString.remaining(), pString, pString.position());
	}
	private static native void nglProgramStringARB(int target, int format, int len, Buffer pString, int pString_offset);

	public static void glRequestResidentProgramsNV(IntBuffer piIDs) {
		nglRequestResidentProgramsNV(piIDs.remaining(), piIDs, piIDs.position());
	}
	private static native void nglRequestResidentProgramsNV(int n, IntBuffer piIDs, int piIDs_offset);

	public static native void glSampleCoverageARB(float value, boolean invert);

	public static native void glSampleMapATI(int dst, int interp, int swizzle);

	public static native void glSecondaryColor3bEXT(byte red, byte green, byte blue);

	public static native void glSecondaryColor3fEXT(float red, float green, float blue);

	public static native void glSecondaryColor3ubEXT(byte red, byte green, byte blue);

	public static void glSecondaryColorPointerEXT(int size, int type, int stride, ByteBuffer pPointer) {
		nglSecondaryColorPointerEXT(size, type, stride, pPointer, pPointer.position());
	}
	public static void glSecondaryColorPointerEXT(int size, int type, int stride, FloatBuffer pPointer) {
		nglSecondaryColorPointerEXT(size, type, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglSecondaryColorPointerEXT(int size, int type, int stride, Buffer pPointer, int pPointer_offset);

	public static native void glSetFenceNV(int fence, int condition);

	public static void glSetFragmentShaderConstantATI(int dst, FloatBuffer pfValue) {
		nglSetFragmentShaderConstantATI(dst, pfValue, pfValue.position());
	}
	private static native void nglSetFragmentShaderConstantATI(int dst, FloatBuffer pfValue, int pfValue_offset);

	public static void glSetInvariantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		nglSetInvariantEXT(id, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, pAddr, pAddr.position());
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		nglSetInvariantEXT(id, unsigned ? GL_UNSIGNED_SHORT : GL_SHORT, pAddr, pAddr.position()<<1);
	}
	public static void glSetInvariantEXT(int id, FloatBuffer pAddr) {
		nglSetInvariantEXT(id, GL_FLOAT, pAddr, pAddr.position()<<2);
	}
	public static void glSetInvariantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		nglSetInvariantEXT(id, unsigned ? GL_UNSIGNED_INT : GL_INT, pAddr, pAddr.position()<<2);
	}
	private static native void nglSetInvariantEXT(int id, int type, Buffer pAddr, int pAddr_offset);

	public static void glSetLocalConstantEXT(int id, boolean unsigned, ByteBuffer pAddr) {
		nglSetLocalConstantEXT(id, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, pAddr, pAddr.position());
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, ShortBuffer pAddr) {
		nglSetLocalConstantEXT(id, unsigned ? GL_UNSIGNED_SHORT : GL_SHORT, pAddr, pAddr.position()<<1);
	}
	public static void glSetLocalConstantEXT(int id, FloatBuffer pAddr) {
		nglSetLocalConstantEXT(id, GL_FLOAT, pAddr, pAddr.position()<<2);
	}
	public static void glSetLocalConstantEXT(int id, boolean unsigned, IntBuffer pAddr) {
		nglSetLocalConstantEXT(id, unsigned ? GL_UNSIGNED_INT : GL_INT, pAddr, pAddr.position()<<2);
	}
	private static native void nglSetLocalConstantEXT(int id, int type, Buffer pAddr, int pAddr_offset);

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

	public static void glTexBumpParameterATI(int pname, FloatBuffer pfParam) {
		nglTexBumpParameterfvATI(pname, pfParam, pfParam.position());
	}
	private static native void nglTexBumpParameterfvATI(int pname, FloatBuffer pfParam, int pfParam_offset);

	public static void glTexBumpParameterATI(int pname, IntBuffer piParam) {
		nglTexBumpParameterivATI(pname, piParam, piParam.position());
	}
	private static native void nglTexBumpParameterivATI(int pname, IntBuffer piParam, int piParam_offset);

	public static native void glTrackMatrixNV(
		int target,
		int address,
		int matrix,
		int transform);

	public static native void glUnlockArraysEXT();

	public static void glUpdateObjectBufferATI(int buffer, int offset, ByteBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining(), pPointer, pPointer.position(), preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, ShortBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining()<<1, pPointer, pPointer.position()<<1, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, FloatBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining()<<2, pPointer, pPointer.position()<<2, preserve);
	}
	public static void glUpdateObjectBufferATI(int buffer, int offset, IntBuffer pPointer, int preserve) {
		nglUpdateObjectBufferATI(buffer, offset, pPointer.remaining()<<2, pPointer, pPointer.position()<<2, preserve);
	}
	private static native void nglUpdateObjectBufferATI(int buffer, int offset, int size, Buffer pPointer, int pPointer_offset, int preserve);

	public static native void glVariantArrayObjectATI(
		int id,
		int type,
		int stride,
		int buffer,
		int offset);

	public static void glVariantEXT(int id, ByteBuffer pAddr) {
		nglVariantbvEXT(id, pAddr, pAddr.position());
	}
	private static native void nglVariantbvEXT(int id, ByteBuffer pAddr, int pAddr_offset);

	public static void glVariantEXT(int id, FloatBuffer pfAddr) {
		nglVariantfvEXT(id, pfAddr, pfAddr.position());
	}
	private static native void nglVariantfvEXT(int id, FloatBuffer pfAddr, int pfAddr_offset);

	public static void glVariantEXT(int id, IntBuffer piAddr) {
		nglVariantivEXT(id, piAddr, piAddr.position());
	}
	private static native void nglVariantivEXT(int id, IntBuffer piAddr, int piAddr_offset);

	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ByteBuffer pAddr) {
		nglVariantPointerEXT(id, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, stride, pAddr, pAddr.position());
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, ShortBuffer pAddr) {
		nglVariantPointerEXT(id, unsigned ? GL_UNSIGNED_SHORT : GL_SHORT, stride, pAddr, pAddr.position()<<1);
	}
	public static void glVariantPointerEXT(int id, int stride, FloatBuffer pAddr) {
		nglVariantPointerEXT(id, GL_FLOAT, stride, pAddr, pAddr.position()<<2);
	}
	public static void glVariantPointerEXT(int id, boolean unsigned, int stride, IntBuffer pAddr) {
		nglVariantPointerEXT(id, unsigned ? GL_UNSIGNED_INT : GL_INT, stride, pAddr, pAddr.position()<<2);
	}
	private static native void nglVariantPointerEXT(int id, int type, int stride, Buffer pAddr, int pAddr_offset);

	public static void glVariantEXT(int id, ShortBuffer psAddr) {
		nglVariantsvEXT(id, psAddr, psAddr.position());
	}
	private static native void nglVariantsvEXT(int id, ShortBuffer psAddr, int psAddr_offset);

	public static void glVariantuEXT(int id, ByteBuffer pAddr) {
		nglVariantubvEXT(id, pAddr, pAddr.position());
	}
	private static native void nglVariantubvEXT(int id, ByteBuffer pAddr, int pAddr_offset);

	public static void glVariantuEXT(int id, IntBuffer piAddr) {
		nglVariantuivEXT(id, piAddr, piAddr.position());
	}
	private static native void nglVariantuivEXT(int id, IntBuffer piAddr, int piAddr_offset);

	public static void glVariantuEXT(int id, ShortBuffer psAddr) {
		nglVariantusvEXT(id, psAddr, psAddr.position());
	}
	private static native void nglVariantusvEXT(int id, ShortBuffer psAddr, int psAddr_offset);

	public static void glVertexArrayRangeNV(ByteBuffer pPointer) {
		nglVertexArrayRangeNV(pPointer.remaining(), pPointer, pPointer.position());
	}
	private static native void nglVertexArrayRangeNV(int size, Buffer pPointer, int pPointer_offset);

	public static native void glVertexAttrib1fARB(int index, float x);

	public static native void glVertexAttrib1fNV(int index, float x);

	public static native void glVertexAttrib1sARB(int index, short x);

	public static native void glVertexAttrib1sNV(int index, short x);

	public static native void glVertexAttrib2fARB(int index, float x, float y);

	public static native void glVertexAttrib2fNV(int index, float x, float y);

	public static native void glVertexAttrib2sARB(int index, short x, short y);

	public static native void glVertexAttrib2sNV(int index, short x, short y);

	public static native void glVertexAttrib3fARB(int index, float x, float y, float z);

	public static native void glVertexAttrib3fNV(int index, float x, float y, float z);

	public static native void glVertexAttrib3sARB(int index, short x, short y, short z);

	public static native void glVertexAttrib3sNV(int index, short x, short y, short z);

	public static void glVertexAttrib4ARB(int index, ByteBuffer pV) {
		nglVertexAttrib4bvARB(index, pV, pV.position());
	}
	private static native void nglVertexAttrib4bvARB(int index, ByteBuffer pV, int pV_offset);

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

	public static void glVertexAttrib4ARB(int index, IntBuffer piV) {
		nglVertexAttrib4ivARB(index, piV, piV.position());
	}
	private static native void nglVertexAttrib4ivARB(int index, IntBuffer piV, int piV_offset);

	public static void glVertexAttrib4NARB(int index, ByteBuffer pV) {
		nglVertexAttrib4NbvARB(index, pV, pV.position());
	}
	private static native void nglVertexAttrib4NbvARB(int index, ByteBuffer pV, int pV_offset);

	public static void glVertexAttrib4NARB(int index, IntBuffer piV) {
		nglVertexAttrib4NivARB(index, piV, piV.position());
	}
	private static native void nglVertexAttrib4NivARB(int index, IntBuffer piV, int piV_offset);

	public static void glVertexAttrib4NARB(int index, ShortBuffer psV) {
		nglVertexAttrib4NsvARB(index, psV, psV.position());
	}
	private static native void nglVertexAttrib4NsvARB(int index, ShortBuffer psV, int psV_offset);

	public static native void glVertexAttrib4NubARB(
		int index,
		byte x,
		byte y,
		byte z,
		byte w);

	public static void glVertexAttrib4NuARB(int index, ByteBuffer pV) {
		nglVertexAttrib4NubvARB(index, pV, pV.position());
	}
	private static native void nglVertexAttrib4NubvARB(int index, ByteBuffer pV, int pV_offset);

	public static void glVertexAttrib4NuARB(int index, IntBuffer piV) {
		nglVertexAttrib4NuivARB(index, piV, piV.position());
	}
	private static native void nglVertexAttrib4NuivARB(int index, IntBuffer piV, int piV_offset);

	public static void glVertexAttrib4NuARB(int index, ShortBuffer psV) {
		nglVertexAttrib4NusvARB(index, psV, psV.position());
	}
	private static native void nglVertexAttrib4NusvARB(int index, ShortBuffer psV, int psV_offset);

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

	public static void glVertexAttrib4uARB(int index, ByteBuffer pV) {
		nglVertexAttrib4ubvARB(index, pV, pV.position());
	}
	private static native void nglVertexAttrib4ubvARB(int index, ByteBuffer pV, int pV_offset);

	public static void glVertexAttrib4uNV(int index, ByteBuffer pV) {
		nglVertexAttrib4ubvNV(index, pV, pV.position());
	}
	private static native void nglVertexAttrib4ubvNV(int index, ByteBuffer pV, int pV_offset);

	public static void glVertexAttrib4uARB(int index, IntBuffer piV) {
		nglVertexAttrib4uivARB(index, piV, piV.position());
	}
	private static native void nglVertexAttrib4uivARB(int index, IntBuffer piV, int piV_offset);

	public static void glVertexAttrib4uARB(int index, ShortBuffer psV) {
		nglVertexAttrib4usvARB(index, psV, psV.position());
	}
	private static native void nglVertexAttrib4usvARB(int index, ShortBuffer psV, int psV_offset);

	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ByteBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVertexAttribPointerARB(index, size, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, normalized, stride, pPointer, pPointer.position());
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, ShortBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVertexAttribPointerARB(index, size, unsigned ? GL_UNSIGNED_SHORT : GL_SHORT, normalized, stride, pPointer, pPointer.position()<<1);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean normalized, int stride, FloatBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVertexAttribPointerARB(index, size, GL_FLOAT, normalized, stride, pPointer, pPointer.position()<<2);
	}
	public static void glVertexAttribPointerARB(int index, int size, boolean unsigned, boolean normalized, int stride, IntBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglVertexAttribPointerARB(index, size, unsigned ? GL_UNSIGNED_INT : GL_INT, normalized, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, Buffer pPointer, int pPointer_offset);
	public static void glVertexAttribPointerARB(int index, int size, int type, boolean normalized, int stride, int buffer_offset) {
		assert VBOTracker.getVBOArrayStack().getState() != 0: "Cannot use int offsets when VBO is disabled";
		nglVertexAttribPointerARBVBO(index, size, type, normalized, stride, buffer_offset);
	}
	private static native void nglVertexAttribPointerARBVBO(int index, int size, int type, boolean normalized, int stride, int buffer_offset);
	public static void glVertexAttribPointerNV(int index, int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		nglVertexAttribPointerNV(index, size, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glVertexAttribPointerNV(int index, int size, boolean unsigned, int stride, ShortBuffer pPointer) {
		nglVertexAttribPointerNV(index, size, unsigned ? GL_UNSIGNED_SHORT : GL_SHORT, stride, pPointer, pPointer.position()<<1);
	}
	public static void glVertexAttribPointerNV(int index, int size, int stride, FloatBuffer pPointer) {
		nglVertexAttribPointerNV(index, size, GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	public static void glVertexAttribPointerNV(int index, int size, boolean unsigned, int stride, IntBuffer pPointer) {
		nglVertexAttribPointerNV(index, size, unsigned ? GL_UNSIGNED_INT : GL_INT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglVertexAttribPointerNV(int index, int size, int type, int stride, Buffer pPointer, int pPointer_offset);

	public static void glVertexAttribs1NV(int index, FloatBuffer pfV) {
		nglVertexAttribs1fvNV(index, pfV.remaining(), pfV, pfV.position());
	}
	private static native void nglVertexAttribs1fvNV(int index, int n, FloatBuffer pfV, int pfV_offset);

	public static void glVertexAttribs1NV(int index, ShortBuffer psV) {
		nglVertexAttribs1svNV(index, psV.remaining(), psV, psV.position());
	}
	private static native void nglVertexAttribs1svNV(int index, int n, ShortBuffer psV, int psV_offset);

	public static void glVertexAttribs2NV(int index, FloatBuffer pfV) {
		nglVertexAttribs2fvNV(index, pfV.remaining()>>1, pfV, pfV.position());
	}
	private static native void nglVertexAttribs2fvNV(int index, int n, FloatBuffer pfV, int pfV_offset);

	public static void glVertexAttribs2NV(int index, ShortBuffer psV) {
		nglVertexAttribs2svNV(index, psV.remaining()>>1, psV, psV.position());
	}
	private static native void nglVertexAttribs2svNV(int index, int n, ShortBuffer psV, int psV_offset);

	public static void glVertexAttribs3NV(int index, FloatBuffer pfV) {
		nglVertexAttribs3fvNV(index, pfV.remaining()/3, pfV, pfV.position());
	}
	private static native void nglVertexAttribs3fvNV(int index, int n, FloatBuffer pfV, int pfV_offset);

	public static void glVertexAttribs3NV(int index, ShortBuffer psV) {
		nglVertexAttribs3svNV(index, psV.remaining()/3, psV, psV.position());
	}
	private static native void nglVertexAttribs3svNV(int index, int n, ShortBuffer psV, int psV_offset);

	public static void glVertexAttribs4NV(int index, FloatBuffer pfV) {
		nglVertexAttribs4fvNV(index, pfV.remaining()>>2, pfV, pfV.position());
	}
	private static native void nglVertexAttribs4fvNV(int index, int n, FloatBuffer pfV, int pfV_offset);

	public static void glVertexAttribs4NV(int index, ShortBuffer psV) {
		nglVertexAttribs4svNV(index, psV.remaining()>>2, psV, psV.position());
	}
	private static native void nglVertexAttribs4svNV(int index, int n, ShortBuffer psV, int psV_offset);

	public static void glVertexAttribs4uNV(int index, ByteBuffer pV) {
		nglVertexAttribs4ubvNV(index, pV.remaining()>>2, pV, pV.position());
	}
	private static native void nglVertexAttribs4ubvNV(int index, int n, ByteBuffer pV, int pV_offset);

	public static native void glVertexBlendARB(int count);

	public static native void glVertexBlendEnvfATI(int pname, float param);

	public static native void glVertexBlendEnviATI(int pname, int param);

	public static native void glVertexStream2fATI(int stream, float x, float y);

	public static native void glVertexStream2iATI(int stream, int x, int y);

	public static native void glVertexStream2sATI(int stream, short x, short y);

	public static native void glVertexStream3fATI(int stream, float x, float y, float z);

	public static native void glVertexStream3iATI(int stream, int x, int y, int z);

	public static native void glVertexStream3sATI(int stream, short x, short y, short z);

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

	public static void glVertexWeightPointerEXT(int size, int stride, FloatBuffer pPointer) {
		nglVertexWeightPointerEXT(size, GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglVertexWeightPointerEXT(int size, int type, int stride, Buffer pPointer, int pPointer_offset);

	public static void glWeightARB(ByteBuffer pWeights) {
		nglWeightbvARB(pWeights.remaining(), pWeights, pWeights.position());
	}
	private static native void nglWeightbvARB(int size, ByteBuffer pWeights, int pWeights_offset);

	public static void glWeightARB(int size, FloatBuffer pfWeights) {
		nglWeightfvARB(pfWeights.remaining(), pfWeights, pfWeights.position());
	}
	private static native void nglWeightfvARB(int size, FloatBuffer pfWeights, int pfWeights_offset);

	public static void glWeightARB(IntBuffer piWeights) {
		nglWeightivARB(piWeights.remaining(), piWeights, piWeights.position());
	}
	private static native void nglWeightivARB(int size, IntBuffer piWeights, int piWeights_offset);

	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ByteBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglWeightPointerARB(size, unsigned ? GL_UNSIGNED_BYTE : GL_BYTE, stride, pPointer, pPointer.position());
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, ShortBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglWeightPointerARB(size, unsigned ? GL_UNSIGNED_SHORT : GL_SHORT, stride, pPointer, pPointer.position()<<1);
	}
	public static void glWeightPointerARB(int size, int stride, FloatBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglWeightPointerARB(size, GL_FLOAT, stride, pPointer, pPointer.position()<<2);
	}
	public static void glWeightPointerARB(int size, boolean unsigned, int stride, IntBuffer pPointer) {
		assert VBOTracker.getVBOArrayStack().getState() == 0: "Cannot use Buffers when VBO is enabled";
		nglWeightPointerARB(size, unsigned ? GL_UNSIGNED_INT : GL_INT, stride, pPointer, pPointer.position()<<2);
	}
	private static native void nglWeightPointerARB(int size, int type, int stride, Buffer pPointer, int pPointer_offset);
	public static void glWeightPointerARB(int size, int type, int stride, int buffer_offset) {
		assert VBOTracker.getVBOArrayStack().getState() != 0: "Cannot use int offsets when VBO is disabled";
		nglWeightPointerARBVBO(size, type, stride, buffer_offset);
	}
	private static native void nglWeightPointerARBVBO(int size, int type, int stride, int buffer_offset);

	public static void glWeightARB(ShortBuffer psWeights) {
		nglWeightsvARB(psWeights.remaining(), psWeights, psWeights.position());
	}
	private static native void nglWeightsvARB(int size, ShortBuffer psWeights, int psWeights_offset);

	public static void glWeightuARB(ByteBuffer pWeights) {
		nglWeightubvARB(pWeights.remaining(), pWeights, pWeights.position());
	}
	private static native void nglWeightubvARB(int size, ByteBuffer pWeights, int pWeights_offset);

	public static void glWeightuARB(IntBuffer piWeights) {
		nglWeightuivARB(piWeights.remaining(), piWeights, piWeights.position());
	}
	private static native void nglWeightuivARB(int size, IntBuffer piWeights, int piWeights_offset);

	public static void glWeightuARB(ShortBuffer psWeights) {
		nglWeightusvARB(psWeights.remaining(), psWeights, psWeights.position());
	}
	private static native void nglWeightusvARB(int size, ShortBuffer psWeights, int psWeights_offset);

	public static native ByteBuffer glXAllocateMemoryNV(
		int size,
		float readFrequency,
		float writeFrequency,
		float priority);

	private static native void glXFreeMemoryNV(ByteBuffer pointer);

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
/*	public static native int wglCreateBufferRegionARB(
		int hdc,
		int iLayerPlane,
		int uType);
*/
/*	public static native int wglCreatePbufferARB(
		int hDC,
		int iPixelFormat,
		int iWidth,
		int iHeight,
		int piAttribList);
*/
//	public static native void wglDeleteBufferRegionARB(Buffer hRegion);

/*	public static native boolean wglDestroyPbufferARB(int hPbuffer);*/

	public static native void wglFreeMemoryNV(ByteBuffer pointer);

	public static native int wglGetCurrentReadDCARB();

	public static native String wglGetExtensionsStringARB();

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

/*	public static native boolean wglRestoreBufferRegionARB(
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
*/
/*	public static native boolean wglSetPbufferAttribARB(
		int hPbuffer,
		int piAttribList);
*/
	public static native boolean wglSwapIntervalEXT(int interval);

	public static native void glWindowPos2fARB(float x, float y);

	public static native void glWindowPos2iARB(int x, int y);

	public static native void glWindowPos2sARB(short x, short y);

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

	public static void glBindBufferARB(int target, int buffer) {
		switch (target) {
			case GL_ELEMENT_ARRAY_BUFFER_ARB:
				VBOTracker.getVBOElementStack().setState(buffer);
				break;
			case GL_ARRAY_BUFFER_ARB:
				VBOTracker.getVBOArrayStack().setState(buffer);
				break;
			default: assert false: "Unsupported VBO target " + target;
		}
		nglBindBufferARB(target, buffer);
	}
	private static native void nglBindBufferARB(int target, int buffer);
	public static void glDeleteBuffersARB(IntBuffer buffers) {
		for (int i = buffers.position(); i < buffers.limit(); i++) {
			int buffer_handle = buffers.get(i);
			if (VBOTracker.getVBOElementStack().getState() == buffer_handle)
				VBOTracker.getVBOElementStack().setState(0);
			if (VBOTracker.getVBOArrayStack().getState() == buffer_handle)
				VBOTracker.getVBOArrayStack().setState(0);
		}
		nglDeleteBuffersARB(buffers.remaining(), buffers, buffers.position());
	}
	private static native void nglDeleteBuffersARB(int n, IntBuffer buffers, int buffers_offset);
	public static void glGenBuffersARB(IntBuffer buffers) {
		nglGenBuffersARB(buffers.remaining(), buffers, buffers.position());
	}
	private static native void nglGenBuffersARB(int n, IntBuffer buffers, int buffers_offset);
	public static native boolean glIsBufferARB(int buffer);
	public static void glBufferDataARB(int target, int size, ByteBuffer data, int usage) {
		nglBufferDataARB(target, size, data, data != null ? data.position() : 0, usage);
	}
	public static void glBufferDataARB(int target, int size, ShortBuffer data, int usage) {
		nglBufferDataARB(target, size, data, data != null ? data.position()<<1 : 0, usage);
	}
	public static void glBufferDataARB(int target, int size, FloatBuffer data, int usage) {
		nglBufferDataARB(target, size, data, data != null ? data.position()<<2 : 0, usage);
	}
	public static void glBufferDataARB(int target, int size, IntBuffer data, int usage) {
		nglBufferDataARB(target, size, data, data != null ? data.position()<<2 : 0, usage);
	}
	private static native void nglBufferDataARB(int target, int size, Buffer data, int data_offset, int usage);
	public static void glBufferSubDataARB(int target, int offset, ByteBuffer data) {
		nglBufferSubDataARB(target, offset, data.remaining(), data, data.position());
	}
	public static void glBufferSubDataARB(int target, int offset, ShortBuffer data) {
		nglBufferSubDataARB(target, offset, data.remaining()<<1, data, data.position()<<1);
	}
	public static void glBufferSubDataARB(int target, int offset, FloatBuffer data) {
		nglBufferSubDataARB(target, offset, data.remaining()<<2, data, data.position()<<2);
	}
	public static void glBufferSubDataARB(int target, int offset, IntBuffer data) {
		nglBufferSubDataARB(target, offset, data.remaining()<<2, data, data.position()<<2);
	}
	private static native void nglBufferSubDataARB(int target, int offset, int size, Buffer data, int data_offset);
	public static void glGetBufferSubDataARB(int target, int offset, ByteBuffer data) {
		nglGetBufferSubDataARB(target, offset, data.remaining(), data, data.position());
	}
	public static void glGetBufferSubDataARB(int target, int offset, ShortBuffer data) {
		nglGetBufferSubDataARB(target, offset, data.remaining()<<1, data, data.position()<<1);
	}
	public static void glGetBufferSubDataARB(int target, int offset, IntBuffer data) {
		nglGetBufferSubDataARB(target, offset, data.remaining()<<2, data, data.position()<<2);
	}
	public static void glGetBufferSubDataARB(int target, int offset, FloatBuffer data) {
		nglGetBufferSubDataARB(target, offset, data.remaining()<<2, data, data.position()<<2);
	}
	private static native void nglGetBufferSubDataARB(int target, int offset, int size, Buffer data, int data_offset);
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
	public static void glGetBufferParameterARB(int target, int pname, IntBuffer params) {
		nglGetBufferParameterivARB(target, pname, params, params.position());
	}
	private static native void nglGetBufferParameterivARB(int target, int pname, IntBuffer params, int params_offset);
	public static native ByteBuffer glGetBufferPointerARB(int target, int pname, int size);
}
