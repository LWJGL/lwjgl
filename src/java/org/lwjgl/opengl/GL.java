/* 
 * Copyright (c) 2002 Light Weight Java Game Library Project
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

import java.lang.reflect.*;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.lwjgl.Display;
import org.lwjgl.Sys;

/**
 * $Id$
 *
 * The GL itself, with all supported extensions, based on OpenGL 1.4.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */
public class GL extends CoreGL implements GLConstants {
	public native void activeStencilFaceEXT(int face);

	public native void activeTextureARB(int texture);

	public native void alphaFragmentOp1ATI(
		int op,
		int dst,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod);

	public native void alphaFragmentOp2ATI(
		int op,
		int dst,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod,
		int arg2,
		int arg2Rep,
		int arg2Mod);

	public native void alphaFragmentOp3ATI(
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

	public native boolean areProgramsResidentNV(
		int n,
		int piIDs,
		int pbResidences);

	public native void arrayObjectATI(
		int array,
		int size,
		int type,
		int stride,
		int buffer,
		int offset);

	public native void beginFragmentShaderATI();

	public native void beginOcclusionQueryNV(int id);

	public native void beginVertexShaderEXT();

	public native void bindFragmentShaderATI(int id);

	public native int bindLightParameterEXT(int light, int value);

	public native int bindMaterialParameterEXT(int face, int value);

	public native int bindParameterEXT(int value);

	public native void bindProgramARB(int target, int program);

	public native void bindProgramNV(int target, int id);

	public native int bindTexGenParameterEXT(int unit, int coord, int value);

	public native int bindTextureUnitParameterEXT(int unit, int value);

	public native void bindVertexShaderEXT(int id);

	public native void clientActiveTextureARB(int texture);

	public native void clientActiveVertexStreamATI(int stream);

	public native void colorFragmentOp1ATI(
		int op,
		int dst,
		int dstMask,
		int dstMod,
		int arg1,
		int arg1Rep,
		int arg1Mod);

	public native void colorFragmentOp2ATI(
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

	public native void colorFragmentOp3ATI(
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

	public native void combinerInputNV(
		int stage,
		int portion,
		int variable,
		int input,
		int mapping,
		int componentUsage);

	public native void combinerOutputNV(
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

	public native void combinerParameterfNV(int pname, float param);

	public native void combinerParameterfvNV(int pname, int pfParams);

	public native void combinerParameteriNV(int pname, int param);

	public native void combinerParameterivNV(int pname, int piParams);

	public native void combinerStageParameterfvNV(
		int stage,
		int pname,
		int pfParams);

	public native void compressedTexImage1DARB(
		int target,
		int level,
		int internalformat,
		int width,
		int border,
		int imageSize,
		int pData);

	public native void compressedTexImage2DARB(
		int target,
		int level,
		int internalformat,
		int width,
		int height,
		int border,
		int imageSize,
		int pData);

	public native void compressedTexImage3DARB(
		int target,
		int level,
		int internalformat,
		int width,
		int height,
		int depth,
		int border,
		int imageSize,
		int pData);

	public native void compressedTexSubImage1DARB(
		int target,
		int level,
		int xoffset,
		int width,
		int format,
		int imageSize,
		int pData);

	public native void compressedTexSubImage2DARB(
		int target,
		int level,
		int xoffset,
		int yoffset,
		int width,
		int height,
		int format,
		int imageSize,
		int pData);

	public native void compressedTexSubImage3DARB(
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
		int pData);

	public native void currentPaletteMatrixARB(int index);

	public native void deleteFencesNV(int n, int piFences);

	public native void deleteFragmentShaderATI(int id);

	public native void deleteOcclusionQueriesNV(int n, int piIDs);

	public native void deleteProgramsARB(int n, int piPrograms);

	public native void deleteProgramsNV(int n, int piIDs);

	public native void deleteVertexShaderEXT(int id);

	public native void disableVariantClientStateEXT(int id);

	public native void disableVertexAttribArrayARB(int index);

	public native void drawElementArrayATI(int mode, int count);

	public native void drawRangeElementArrayATI(
		int mode,
		int start,
		int end,
		int count);

	public native void drawRangeElementsEXT(
		int mode,
		int start,
		int end,
		int count,
		int type,
		int pIndices);

	public native void elementPointerATI(int type, int pPointer);

	public native void enableVariantClientStateEXT(int id);

	public native void enableVertexAttribArrayARB(int index);

	public native void endFragmentShaderATI();

	public native void endOcclusionQueryNV();

	public native void endVertexShaderEXT();

	public native void evalMapsNV(int target, int mode);

	public native void executeProgramNV(int target, int id, int pfParams);

	public native void extractComponentEXT(int res, int src, int num);

	public native void finalCombinerInputNV(
		int variable,
		int input,
		int mapping,
		int componentUsage);

	public native void finishFenceNV(int fence);

	public native void flushVertexArrayRangeNV();

	public native void fogCoorddEXT(double coord);

	public native void fogCoorddvEXT(int pdCoord);

	public native void fogCoordfEXT(float coord);

	public native void fogCoordfvEXT(int pfCoord);

	public native void fogCoordPointerEXT(int type, int stride, int pPointer);

	public native void freeObjectBufferATI(int buffer);

	public native void genFencesNV(int n, int piFences);

	public native int genFragmentShadersATI(int range);

	public native void genOcclusionQueriesNV(int n, int piIDs);

	public native void genProgramsARB(int n, int piPrograms);

	public native void genProgramsNV(int n, int piIDs);

	public native int genSymbolsEXT(
		int dataType,
		int storageType,
		int range,
		int components);

	public native int genVertexShadersEXT(int range);

	public native void getArrayObjectfvATI(int array, int pname, int pfParams);

	public native void getArrayObjectivATI(int array, int pname, int piParams);

	public native void getCombinerInputParameterfvNV(
		int stage,
		int portion,
		int variable,
		int pname,
		int pfParams);

	public native void getCombinerInputParameterivNV(
		int stage,
		int portion,
		int variable,
		int pname,
		int piParams);

	public native void getCombinerOutputParameterfvNV(
		int stage,
		int portion,
		int pname,
		int pfParams);

	public native void getCombinerOutputParameterivNV(
		int stage,
		int portion,
		int pname,
		int piParams);

	public native void getCombinerStageParameterfvNV(
		int stage,
		int pname,
		int pfParams);

	public native void getCompressedTexImageARB(int target, int lod, int pImg);

	public native void getFenceivNV(int fence, int pname, int piParams);

	public native void getFinalCombinerInputParameterfvNV(
		int variable,
		int pname,
		int pfParams);

	public native void getFinalCombinerInputParameterivNV(
		int variable,
		int pname,
		int piParams);

	public native void getInvariantBooleanvEXT(int id, int value, int pbData);

	public native void getInvariantFloatvEXT(int id, int value, int pfData);

	public native void getInvariantIntegervEXT(int id, int value, int piData);

	public native void getLocalConstantBooleanvEXT(
		int id,
		int value,
		int pbData);

	public native void getLocalConstantFloatvEXT(int id, int value, int pfData);

	public native void getLocalConstantIntegervEXT(
		int id,
		int value,
		int piData);

	public native void getMapAttribParameterfvNV(
		int target,
		int index,
		int pname,
		int pfParams);

	public native void getMapAttribParameterivNV(
		int target,
		int index,
		int pname,
		int piParams);

	public native void getMapControlPointsNV(
		int target,
		int index,
		int type,
		int ustride,
		int vstride,
		boolean packed,
		int pPoints);

	public native void getMapParameterfvNV(int target, int pname, int pfParams);

	public native void getMapParameterivNV(int target, int pname, int piParams);

	public native void getObjectBufferfvATI(
		int buffer,
		int pname,
		int pfParams);

	public native void getObjectBufferivATI(
		int buffer,
		int pname,
		int piParams);

	public native void getOcclusionQueryivNV(int id, int pname, int piParams);

	public native void getOcclusionQueryuivNV(int id, int pname, int piParams);

	public native void getProgramEnvParameterdvARB(
		int target,
		int index,
		int pdParams);

	public native void getProgramEnvParameterfvARB(
		int target,
		int index,
		int pfParams);

	public native void getProgramivARB(int target, int pname, int piParams);

	public native void getProgramivNV(int id, int pname, int piParams);

	public native void getProgramLocalParameterdvARB(
		int target,
		int index,
		int pdParams);

	public native void getProgramLocalParameterfvARB(
		int target,
		int index,
		int pfParams);

	public native void getProgramParameterdvNV(
		int target,
		int index,
		int pname,
		int pdParams);

	public native void getProgramParameterfvNV(
		int target,
		int index,
		int pname,
		int pfParams);

	public native void getProgramStringARB(int target, int pname, int pString);

	public native void getProgramStringNV(int id, int pname, int pProgram);

	public native void getTexBumpParameterfvATI(int pname, int pfParam);

	public native void getTexBumpParameterivATI(int pname, int piParam);

	public native void getTrackMatrixivNV(
		int target,
		int address,
		int pname,
		int piParams);

	public native void getVariantArrayObjectfvATI(
		int id,
		int pname,
		int pfParams);

	public native void getVariantArrayObjectivATI(
		int id,
		int pname,
		int piParams);

	public native void getVariantBooleanvEXT(int id, int value, int pbData);

	public native void getVariantFloatvEXT(int id, int value, int pfData);

	public native void getVariantIntegervEXT(int id, int value, int piData);

	public native void getVariantPointervEXT(int id, int value, int pData);

	public native void getVertexAttribdvARB(int index, int pname, int pdParams);

	public native void getVertexAttribdvNV(int index, int pname, int pdParams);

	public native void getVertexAttribfvARB(int index, int pname, int pfParams);

	public native void getVertexAttribfvNV(int index, int pname, int pfParams);

	public native void getVertexAttribivARB(int index, int pname, int piParams);

	public native void getVertexAttribivNV(int index, int pname, int piParams);

	public native void getVertexAttribPointervARB(
		int index,
		int pname,
		int pPointer);

	public native void getVertexAttribPointervNV(
		int index,
		int pname,
		int pPointer);

	public native void insertComponentEXT(int res, int src, int num);

	public native boolean isFenceNV(int fence);

	public native boolean isObjectBufferATI(int buffer);

	public native boolean isOcclusionQueryNV(int id);

	public native boolean isProgramARB(int program);

	// #ifdef _WIN32

	public native boolean isProgramNV(int id);

	public native boolean isVariantEnabledEXT(int id, int cap);

	public native void loadProgramNV(int target, int id, int len, int pProgram);

	public native void loadTransposeMatrixdARB(int pdMtx); // m[16]

	public native void loadTransposeMatrixfARB(int pfMtx); // m[16]

	public native void lockArraysEXT(int first, int count);

	public native void mapControlPointsNV(
		int target,
		int index,
		int type,
		int ustride,
		int vstride,
		int uorder,
		int vorder,
		boolean packed,
		int pPoints);

	public native void mapParameterfvNV(int target, int pname, int pfParams);

	public native void mapParameterivNV(int target, int pname, int piParams);

	public native void matrixIndexPointerARB(
		int size,
		int type,
		int stride,
		int pPointer);

	public native void matrixIndexubvARB(int size, int pIndices);

	public native void matrixIndexuivARB(int size, int piIndices);

	public native void matrixIndexusvARB(int size, int psIndices);

	public native void multiDrawArraysEXT(
		int mode,
		int piFirst,
		int piCount,
		int primcount);

	public native void multiDrawElementsEXT(
		int mode,
		int piCount,
		int type,
		int pIndices,
		int primcount);

	public native void multiTexCoord1dARB(int target, double s);

	public native void multiTexCoord1dvARB(int target, int pdV);

	public native void multiTexCoord1fARB(int target, float s);

	public native void multiTexCoord1fvARB(int target, int pfV);

	public native void multiTexCoord1iARB(int target, int s);

	public native void multiTexCoord1ivARB(int target, int piV);

	public native void multiTexCoord1sARB(int target, short s);

	public native void multiTexCoord1svARB(int target, int psV);

	public native void multiTexCoord2dARB(int target, double s, double t);

	public native void multiTexCoord2dvARB(int target, int pdV);

	public native void multiTexCoord2fARB(int target, float s, float t);

	public native void multiTexCoord2fvARB(int target, int pfV);

	public native void multiTexCoord2iARB(int target, int s, int t);

	public native void multiTexCoord2ivARB(int target, int piV);

	public native void multiTexCoord2sARB(int target, short s, short t);

	public native void multiTexCoord2svARB(int target, int psV);

	public native void multiTexCoord3dARB(
		int target,
		double s,
		double t,
		double r);

	public native void multiTexCoord3dvARB(int target, int pdV);

	public native void multiTexCoord3fARB(
		int target,
		float s,
		float t,
		float r);

	public native void multiTexCoord3fvARB(int target, int pfV);

	public native void multiTexCoord3iARB(int target, int s, int t, int r);

	public native void multiTexCoord3ivARB(int target, int piV);

	public native void multiTexCoord3sARB(
		int target,
		short s,
		short t,
		short r);

	public native void multiTexCoord3svARB(int target, int psV);

	public native void multiTexCoord4dARB(
		int target,
		double s,
		double t,
		double r,
		double q);

	public native void multiTexCoord4dvARB(int target, int pdV);

	public native void multiTexCoord4fARB(
		int target,
		float s,
		float t,
		float r,
		float q);

	public native void multiTexCoord4fvARB(int target, int pfV);

	public native void multiTexCoord4iARB(
		int target,
		int s,
		int t,
		int r,
		int q);

	public native void multiTexCoord4ivARB(int target, int piV);

	public native void multiTexCoord4sARB(
		int target,
		short s,
		short t,
		short r,
		short q);

	public native void multiTexCoord4svARB(int target, int psV);

	public native void multTransposeMatrixdARB(int pdMtx); // m[16]

	public native void multTransposeMatrixfARB(int pfMtx); // m[16]

	public native int newObjectBufferATI(int size, int pPointer, int usage);

	public native void normalStream3bATI(int stream, byte x, byte y, byte z);

	public native void normalStream3bvATI(int stream, int pV);

	public native void normalStream3dATI(
		int stream,
		double x,
		double y,
		double z);

	public native void normalStream3dvATI(int stream, int pdV);

	public native void normalStream3fATI(int stream, float x, float y, float z);

	public native void normalStream3fvATI(int stream, int pfV);

	public native void normalStream3iATI(int stream, int x, int y, int z);

	public native void normalStream3ivATI(int stream, int piV);

	public native void normalStream3sATI(int stream, short x, short y, short z);

	public native void normalStream3svATI(int stream, int psV);

	public native void passTexCoordATI(int dst, int coord, int swizzle);

	public native void PNTrianglesfATI(int pname, float param);

	public native void PNTrianglesiATI(int pname, int param);

	public native void pointParameterfARB(int pname, float param);

	public native void pointParameterfEXT(int pname, float param);

	public native void pointParameterfvARB(int pname, int pfParams);

	public native void pointParameterfvEXT(int pname, int pfParams);

	public native void pointParameteriNV(int pname, int param);

	public native void pointParameterivNV(int pname, int piParams);

	public native void programEnvParameter4dARB(
		int target,
		int index,
		double x,
		double y,
		double z,
		double w);

	public native void programEnvParameter4dvARB(
		int target,
		int index,
		int pdParams);

	public native void programEnvParameter4fARB(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public native void programEnvParameter4fvARB(
		int target,
		int index,
		int pfParams);

	public native void programLocalParameter4dARB(
		int target,
		int index,
		double x,
		double y,
		double z,
		double w);

	public native void programLocalParameter4dvARB(
		int target,
		int index,
		int pdParams);

	public native void programLocalParameter4fARB(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public native void programLocalParameter4fvARB(
		int target,
		int index,
		int pfParams);

	public native void programParameter4dNV(
		int target,
		int index,
		double x,
		double y,
		double z,
		double w);

	public native void programParameter4dvNV(
		int target,
		int index,
		int pdParams);

	public native void programParameter4fNV(
		int target,
		int index,
		float x,
		float y,
		float z,
		float w);

	public native void programParameter4fvNV(
		int target,
		int index,
		int pfParams);

	public native void programParameters4dvNV(
		int target,
		int index,
		int num,
		int pdParams);

	public native void programParameters4fvNV(
		int target,
		int index,
		int num,
		int pfParams);

	public native void programStringARB(
		int target,
		int format,
		int len,
		int pString);

	public native void requestResidentProgramsNV(int n, int piIDs);

	public native void sampleCoverageARB(float value, boolean invert);

	public native void sampleMapATI(int dst, int interp, int swizzle);

	public native void secondaryColor3bEXT(byte red, byte green, byte blue);

	public native void secondaryColor3bvEXT(int pV);

	public native void secondaryColor3dEXT(
		double red,
		double green,
		double blue);

	public native void secondaryColor3dvEXT(int pdV);

	public native void secondaryColor3fEXT(float red, float green, float blue);

	public native void secondaryColor3fvEXT(int pfV);

	public native void secondaryColor3iEXT(int red, int green, int blue);

	public native void secondaryColor3ivEXT(int piV);

	public native void secondaryColor3sEXT(short red, short green, short blue);

	public native void secondaryColor3svEXT(int psV);

	public native void secondaryColor3ubEXT(byte red, byte green, byte blue);

	public native void secondaryColor3ubvEXT(int pV);

	public native void secondaryColor3uiEXT(int red, int green, int blue);

	public native void secondaryColor3uivEXT(int piV);

	public native void secondaryColor3usEXT(short red, short green, short blue);

	public native void secondaryColor3usvEXT(int psV);

	public native void secondaryColorPointerEXT(
		int size,
		int type,
		int stride,
		int pPointer);

	public native void setFenceNV(int fence, int condition);

	public native void setFragmentShaderConstantATI(int dst, int pfValue);

	public native void setInvariantEXT(int id, int type, int pAddr);

	public native void setLocalConstantEXT(int id, int type, int pAddr);

	public native void shaderOp1EXT(int op, int res, int arg1);

	public native void shaderOp2EXT(int op, int res, int arg1, int arg2);

	public native void shaderOp3EXT(
		int op,
		int res,
		int arg1,
		int arg2,
		int arg3);

	public native void swizzleEXT(
		int res,
		int in,
		int outX,
		int outY,
		int outZ,
		int outW);

	public native boolean testFenceNV(int fence);

	public native void texBumpParameterfvATI(int pname, int pfParam);

	public native void texBumpParameterivATI(int pname, int piParam);

	public native void trackMatrixNV(
		int target,
		int address,
		int matrix,
		int transform);

	public native void unlockArraysEXT();

	public native void updateObjectBufferATI(
		int buffer,
		int offset,
		int size,
		int pPointer,
		int preserve);

	public native void variantArrayObjectATI(
		int id,
		int type,
		int stride,
		int buffer,
		int offset);

	public native void variantbvEXT(int id, int pAddr);

	public native void variantdvEXT(int id, int pdAddr);

	public native void variantfvEXT(int id, int pfAddr);

	public native void variantivEXT(int id, int piAddr);

	public native void variantPointerEXT(
		int id,
		int type,
		int stride,
		int pAddr);

	public native void variantsvEXT(int id, int psAddr);

	public native void variantubvEXT(int id, int pAddr);

	public native void variantuivEXT(int id, int piAddr);

	public native void variantusvEXT(int id, int psAddr);

	public native void vertexArrayRangeNV(int size, int pPointer);

	public native void vertexAttrib1dARB(int index, double x);

	public native void vertexAttrib1dNV(int index, double x);

	public native void vertexAttrib1dvARB(int index, int pdV);

	public native void vertexAttrib1dvNV(int index, int pdV);

	public native void vertexAttrib1fARB(int index, float x);

	public native void vertexAttrib1fNV(int index, float x);

	public native void vertexAttrib1fvARB(int index, int pfV);

	public native void vertexAttrib1fvNV(int index, int pfV);

	public native void vertexAttrib1sARB(int index, short x);

	public native void vertexAttrib1sNV(int index, short x);

	public native void vertexAttrib1svARB(int index, int psV);

	public native void vertexAttrib1svNV(int index, int psV);

	public native void vertexAttrib2dARB(int index, double x, double y);

	public native void vertexAttrib2dNV(int index, double x, double y);

	public native void vertexAttrib2dvARB(int index, int pdV);

	public native void vertexAttrib2dvNV(int index, int pdV);

	public native void vertexAttrib2fARB(int index, float x, float y);

	public native void vertexAttrib2fNV(int index, float x, float y);

	public native void vertexAttrib2fvARB(int index, int pfV);

	public native void vertexAttrib2fvNV(int index, int pfV);

	public native void vertexAttrib2sARB(int index, short x, short y);

	public native void vertexAttrib2sNV(int index, short x, short y);

	public native void vertexAttrib2svARB(int index, int psV);

	public native void vertexAttrib2svNV(int index, int psV);

	public native void vertexAttrib3dARB(
		int index,
		double x,
		double y,
		double z);

	public native void vertexAttrib3dNV(
		int index,
		double x,
		double y,
		double z);

	public native void vertexAttrib3dvARB(int index, int pdV);

	public native void vertexAttrib3dvNV(int index, int pdV);

	public native void vertexAttrib3fARB(int index, float x, float y, float z);

	public native void vertexAttrib3fNV(int index, float x, float y, float z);

	public native void vertexAttrib3fvARB(int index, int pfV);

	public native void vertexAttrib3fvNV(int index, int pfV);

	public native void vertexAttrib3sARB(int index, short x, short y, short z);

	public native void vertexAttrib3sNV(int index, short x, short y, short z);

	public native void vertexAttrib3svARB(int index, int psV);

	public native void vertexAttrib3svNV(int index, int psV);

	public native void vertexAttrib4bvARB(int index, int pV);

	public native void vertexAttrib4dARB(
		int index,
		double x,
		double y,
		double z,
		double w);

	public native void vertexAttrib4dNV(
		int index,
		double x,
		double y,
		double z,
		double w);

	public native void vertexAttrib4dvARB(int index, int pdV);

	public native void vertexAttrib4dvNV(int index, int pdV);

	public native void vertexAttrib4fARB(
		int index,
		float x,
		float y,
		float z,
		float w);

	public native void vertexAttrib4fNV(
		int index,
		float x,
		float y,
		float z,
		float w);

	public native void vertexAttrib4fvARB(int index, int pfV);

	public native void vertexAttrib4fvNV(int index, int pfV);

	public native void vertexAttrib4ivARB(int index, int piV);

	public native void vertexAttrib4NbvARB(int index, int pV);

	public native void vertexAttrib4NivARB(int index, int piV);

	public native void vertexAttrib4NsvARB(int index, int psV);

	public native void vertexAttrib4NubARB(
		int index,
		byte x,
		byte y,
		byte z,
		byte w);

	public native void vertexAttrib4NubvARB(int index, int pV);

	public native void vertexAttrib4NuivARB(int index, int piV);

	public native void vertexAttrib4NusvARB(int index, int psV);

	public native void vertexAttrib4sARB(
		int index,
		short x,
		short y,
		short z,
		short w);

	public native void vertexAttrib4sNV(
		int index,
		short x,
		short y,
		short z,
		short w);

	public native void vertexAttrib4svARB(int index, int psV);

	public native void vertexAttrib4svNV(int index, int psV);

	public native void vertexAttrib4ubNV(
		int index,
		byte x,
		byte y,
		byte z,
		byte w);

	public native void vertexAttrib4ubvARB(int index, int pV);

	public native void vertexAttrib4ubvNV(int index, int pV);

	public native void vertexAttrib4uivARB(int index, int piV);

	public native void vertexAttrib4usvARB(int index, int psV);

	public native void vertexAttribPointerARB(
		int index,
		int size,
		int type,
		boolean normalized,
		int stride,
		int pPointer);

	public native void vertexAttribPointerNV(
		int index,
		int size,
		int type,
		int stride,
		int pPointer);

	public native void vertexAttribs1dvNV(int index, int n, int pdV);

	public native void vertexAttribs1fvNV(int index, int n, int pfV);

	public native void vertexAttribs1svNV(int index, int n, int psV);

	public native void vertexAttribs2dvNV(int index, int n, int pdV);

	public native void vertexAttribs2fvNV(int index, int n, int pfV);

	public native void vertexAttribs2svNV(int index, int n, int psV);

	public native void vertexAttribs3dvNV(int index, int n, int pdV);

	public native void vertexAttribs3fvNV(int index, int n, int pfV);

	public native void vertexAttribs3svNV(int index, int n, int psV);

	public native void vertexAttribs4dvNV(int index, int n, int pdV);

	public native void vertexAttribs4fvNV(int index, int n, int pfV);

	public native void vertexAttribs4svNV(int index, int n, int psV);

	public native void vertexAttribs4ubvNV(int index, int n, int pV);

	public native void vertexBlendARB(int count);

	public native void vertexBlendEnvfATI(int pname, float param);

	public native void vertexBlendEnviATI(int pname, int param);

	public native void vertexStream2dATI(int stream, double x, double y);

	public native void vertexStream2dvATI(int stream, int pdV);

	public native void vertexStream2fATI(int stream, float x, float y);

	public native void vertexStream2fvATI(int stream, int pfV);

	public native void vertexStream2iATI(int stream, int x, int y);

	public native void vertexStream2ivATI(int stream, int piV);

	public native void vertexStream2sATI(int stream, short x, short y);

	public native void vertexStream2svATI(int stream, int psV);

	public native void vertexStream3dATI(
		int stream,
		double x,
		double y,
		double z);

	public native void vertexStream3dvATI(int stream, int pdV);

	public native void vertexStream3fATI(int stream, float x, float y, float z);

	public native void vertexStream3fvATI(int stream, int pfV);

	public native void vertexStream3iATI(int stream, int x, int y, int z);

	public native void vertexStream3ivATI(int stream, int piV);

	public native void vertexStream3sATI(int stream, short x, short y, short z);

	public native void vertexStream3svATI(int stream, int psV);

	public native void vertexStream4dATI(
		int stream,
		double x,
		double y,
		double z,
		double w);

	public native void vertexStream4dvATI(int stream, int pdV);

	public native void vertexStream4fATI(
		int stream,
		float x,
		float y,
		float z,
		float w);

	public native void vertexStream4fvATI(int stream, int pfV);

	public native void vertexStream4iATI(
		int stream,
		int x,
		int y,
		int z,
		int w);

	public native void vertexStream4ivATI(int stream, int piV);

	public native void vertexStream4sATI(
		int stream,
		short x,
		short y,
		short z,
		short w);

	public native void vertexStream4svATI(int stream, int psV);

	public native void vertexWeightfEXT(float weight);

	public native void vertexWeightfvEXT(int pfWeight);

	public native void vertexWeightPointerEXT(
		int size,
		int type,
		int stride,
		int pPointer);

	public native void weightbvARB(int size, int pWeights);

	public native void weightdvARB(int size, int pdWeights);

	public native void weightfvARB(int size, int pfWeights);

	public native void weightivARB(int size, int piWeights);

	public native void weightPointerARB(
		int size,
		int type,
		int stride,
		int pPointer);

	public native void weightsvARB(int size, int psWeights);

	public native void weightubvARB(int size, int pWeights);

	public native void weightuivARB(int size, int piWeights);

	public native void weightusvARB(int size, int psWeights);

	// #ifdef _WIN32

	public static native int wglAllocateMemoryNV(
		int size,
		float readFrequency,
		float writeFrequency,
		float priority);

	public static native boolean wglBindTexImageARB(int hPbuffer, int iBuffer);

	public static native boolean wglChoosePixelFormatARB(
		int hdc,
		int piAttribIList,
		int pfAttribFList,
		int nMaxFormats,
		int piFormats,
		int piNumFormats);

	public static native int wglCreateBufferRegionARB(
		int hdc,
		int iLayerPlane,
		int uType);

	public static native int wglCreatePbufferARB(
		int hDC,
		int iPixelFormat,
		int iWidth,
		int iHeight,
		int piAttribList);

	public static native void wglDeleteBufferRegionARB(int hRegion);

	public static native boolean wglDestroyPbufferARB(int hPbuffer);

	public static native void wglFreeMemoryNV(int pointer);

	

	public static native int wglGetCurrentReadDCARB();

	public static native String wglGetExtensionsStringARB(int hdc);

	

	public static native String wglGetExtensionsStringEXT();

	public static native int wglGetPbufferDCARB(int hPbuffer);

	public static native boolean wglGetPixelFormatAttribfvARB(
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

	public static native int wglGetSwapIntervalEXT();

	public static native boolean wglMakeContextCurrentARB(
		int hDrawDC,
		int hReadDC,
		int hglrc);

	public static native boolean wglQueryPbufferARB(
		int hPbuffer,
		int iAttribute,
		int piValue);

	public static native int wglReleasePbufferDCARB(int hPbuffer, int hDC);

	public static native boolean wglReleaseTexImageARB(int hPbuffer, int iBuffer);

	public static native boolean wglRestoreBufferRegionARB(
		int hRegion,
		int x,
		int y,
		int width,
		int height,
		int xSrc,
		int ySrc);

	public static native boolean wglSaveBufferRegionARB(
		int hRegion,
		int x,
		int y,
		int width,
		int height);

	public static native boolean wglSetPbufferAttribARB(
		int hPbuffer,
		int piAttribList);

	public static native boolean wglSwapIntervalEXT(int interval);

	public native void windowPos2dARB(double x, double y);

	public native void windowPos2dvARB(int pdP);

	public native void windowPos2fARB(float x, float y);

	public native void windowPos2fvARB(int pfP);

	public native void windowPos2iARB(int x, int y);

	public native void windowPos2ivARB(int piP);

	public native void windowPos2sARB(short x, short y);

	public native void windowPos2svARB(int psP);

	public native void windowPos3dARB(double x, double y, double z);

	public native void windowPos3dvARB(int pdP);

	public native void windowPos3fARB(float x, float y, float z);

	public native void windowPos3fvARB(int pfP);

	public native void windowPos3iARB(int x, int y, int z);

	public native void windowPos3ivARB(int piP);

	public native void windowPos3sARB(short x, short y, short z);

	public native void windowPos3svARB(int psP);

	public native void writeMaskEXT(
		int res,
		int in,
		int outX,
		int outY,
		int outZ,
		int outW);

	/*
	 * Available extensions
	 */
	public boolean ARB_imaging;
	public boolean ARB_depth_texture;
	public boolean ARB_matrix_palette;
	public boolean ARB_multisample;
	public boolean ARB_multitexture;
	public boolean ARB_point_parameters;
	public boolean ARB_shadow;
	public boolean ARB_shadow_ambient;
	public boolean ARB_texture_compression;
	public boolean ARB_texture_env_add;
	public boolean ARB_texture_env_dot3;
	public boolean ARB_texture_env_combine;
	public boolean ARB_texture_env_crossbar;
	public boolean ARB_texture_border_clamp;
	public boolean ARB_texture_cube_map;
	public boolean ARB_texture_mirrored_repeat;
	public boolean ARB_transpose_matrix;
	public boolean ARB_vertex_blend;
	public boolean ARB_vertex_program;
	public boolean ARB_window_pos;
	public boolean EXT_abgr;
	public boolean EXT_bgra;
	public boolean EXT_blend_function_separate;
	public boolean EXT_compiled_vertex_array;
	public boolean EXT_cull_vertex;
	public boolean EXT_draw_range_elements;
	public boolean EXT_fog_coord;
	public boolean EXT_multi_draw_arrays;
	public boolean EXT_point_parameters;
	public boolean EXT_secondary_color  ;
	public boolean EXT_separate_specular_color;
	public boolean EXT_shadow_funcs;
	public boolean EXT_stencil_two_side;
	public boolean EXT_stencil_wrap;
	public boolean EXT_texture_compression_s3tc;
	public boolean EXT_texture_filter_anisotropic;
	public boolean EXT_texture_lod_bias;
	public boolean EXT_vertex_shader;
	public boolean EXT_vertex_weighting;
	public boolean ATI_element_array;
	public boolean ATI_envmap_bumpmap;
	public boolean ATI_fragment_shader;
	public boolean ATI_pn_triangles;
	public boolean ATI_texture_mirror_once;
	public boolean ATI_vertex_array_object;
	public boolean ATI_vertex_streams;
	public boolean ATIX_point_sprites;
	public boolean ATIX_texture_env_route;
	public boolean HP_occlusion_test;
	public boolean NV_blend_square;
	public boolean NV_copy_depth_to_color;
	public boolean NV_depth_clamp;
	public boolean NV_evaluators;
	public boolean NV_fence;
	public boolean NV_fog_distance;
	public boolean NV_light_max_exponent;
	public boolean NV_occlusion_query;
	public boolean NV_packed_depth_stencil;
	public boolean NV_point_sprite;
	public boolean NV_register_combiners;
	public boolean NV_register_combiners2;
	public boolean NV_texgen_reflection;
	public boolean NV_texture_env_combine4;
	public boolean NV_texture_rectangle;
	public boolean NV_texture_shader;
	public boolean NV_texture_shader2;
	public boolean NV_texture_shader3;
	public boolean NV_vertex_array_range;
	public boolean NV_vertex_array_range2;
	public boolean NV_vertex_program;
	public boolean NV_vertex_program1_1;
	public boolean SGIS_generate_mipmap;
	public boolean SGIX_shadow;
	public boolean SGIX_depth_texture;
	public boolean OpenGL11;
	public boolean OpenGL12;
	public boolean OpenGL13;
	public boolean OpenGL14;

	/**
	 * Constructor for GL.
	 */
	public GL(int colorBits, int alphaBits, int depthBits, int stencilBits) {
		super(colorBits, alphaBits, depthBits, stencilBits);
	}

	/**
	 * Determine which extensions are available
	 */
	private void determineAvailableExtensions() {
		
		determineAvailableWGLExtensions();
		
		// Grab all the public booleans out of this class
		Field[] fields = GL.class.getDeclaredFields();
		HashMap map = new HashMap(fields.length);
		for (int i = 0; i < fields.length; i ++) {
			if (!Modifier.isStatic(fields[i].getModifiers()) && fields[i].getType() == boolean.class)
				map.put(fields[i].getName(), fields[i]);
		}
		
		String exts = wglGetExtensionsStringEXT();
		StringTokenizer st = new StringTokenizer(exts);
		while (st.hasMoreTokens()) {
			String ext = st.nextToken();
			
			Field f = (Field) map.get(ext);
			if (f != null) {
				try {
					f.setBoolean(this, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}
			
		}
		
		// Let's see what openGL version we are too:
		String version = getString(VERSION);
		int i = version.indexOf("1.");
		if (i > -1) {
			char c = version.charAt(i + 2);
			if (c == '2') {
				OpenGL12 = true;
			} else if (c == '3') {
				OpenGL12 = true;
				OpenGL13 = true;
			} else if (c == '4') {
				OpenGL12 = true;
				OpenGL13 = true;
				OpenGL14 = true;
			}
		}
	}
	
	/*
	 * Available WGL extensions
	 */
	public static boolean WGL_ARB_buffer_region;
	public static boolean WGL_ARB_extensions_string;
	public static boolean WGL_ARB_pbuffer;
	public static boolean WGL_ARB_pixel_format;
	public static boolean WGL_ARB_render_texture; 
	public static boolean WGL_EXT_extensions_string;
	public static boolean WGL_EXT_swap_control;

	/**
	 * Checks and sets WGL_EXT_extensions_string and WGL_ARB_extensions_string
	 * if available.
	 */
	private static native void checkWGLExtensionsString();
	
	/**
	 * Determine which WGL extensions are available
	 */
	private void determineAvailableWGLExtensions() {
		
		// First we must determine if WGL_EXT_extensions_string is available
		checkWGLExtensionsString();
		if (!WGL_EXT_extensions_string && !WGL_ARB_extensions_string)
			return;
		
		// Grab all the public booleans out of this class
		Field[] fields = GL.class.getDeclaredFields();
		HashMap map = new HashMap(fields.length);
		for (int i = 0; i < fields.length; i ++) {
			if (Modifier.isStatic(fields[i].getModifiers()) && fields[i].getType() == boolean.class)
				map.put(fields[i].getName(), fields[i]);
		}
		
		final String exts;
		
		if (WGL_ARB_extensions_string)
			exts = wglGetExtensionsStringARB(Display.getHandle()); // Remember - this is an HWND not an HDC, which is what's required
		else
			exts = wglGetExtensionsStringEXT();

		System.out.println("Available WGL extensions:");			
		StringTokenizer st = new StringTokenizer(exts);
		while (st.hasMoreTokens()) {
			String ext = st.nextToken();
			
			System.out.println(ext);
			
			Field f = (Field) map.get(ext);
			if (f != null) {
				try {
					f.setBoolean(GL.class, true);
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				}
			}
			
		}		
	}

	/* (non-Javadoc)
	 * @see org.lwjgl.opengl.BaseGL#init()
	 */
	protected void init() {
		super.init();

		// Right after creation we can go and find out what extensions are
		// available. We can't actually determine this beforehand of course
		// because the available extensions can only be determined when there's
		// an actual rendering context.
		determineAvailableExtensions();
	}

}
