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

import org.lwjgl.opengl.arb.*;
import org.lwjgl.opengl.ati.*;
import org.lwjgl.opengl.ati.ATIElementArray;
import org.lwjgl.opengl.ati.ATIEnvmapBumpmap;
import org.lwjgl.opengl.atix.ATIXPointSprites;
import org.lwjgl.opengl.atix.ATIXTextureEnvRoute;
import org.lwjgl.opengl.ext.*;
import org.lwjgl.opengl.ext.EXTAbgr;
import org.lwjgl.opengl.ext.EXTCompiledVertexArray;
import org.lwjgl.opengl.hp.HPOcclusionTest;
import org.lwjgl.opengl.nv.*;
import org.lwjgl.opengl.nv.NVCopyDepthToColor;
import org.lwjgl.opengl.nv.NVDepthClamp;
import org.lwjgl.opengl.sgis.SGISGenerateMipmap;
import org.lwjgl.opengl.sgix.SGIXDepthTexture;
import org.lwjgl.opengl.sgix.SGIXShadow;
import org.lwjgl.opengl.wgl.*;
import org.lwjgl.opengl.wgl.WGLBufferRegion;
import org.lwjgl.opengl.wgl.WGLMakeCurrentRead;

/**
 * $Id$
 *
 * All GL constants, including all supported extensions.
 * 
 * @author cix_foo <cix_foo@users.sourceforge.net>
 * @version $Revision$
 */

public interface GLConstants
	extends
		CoreGL14Constants,
		ARBCubeMap,
		ARBDepthTexture,
		ARBMatrixPalette,
		ARBMultisample,
		ARBMultitexture,
		ARBPointParameters,
		ARBShadow,
		ARBShadowAmbient,
		ARBTextureBorderClamp,
		ARBTextureCompression,
		ARBTextureEnvCombine,
		ARBTextureEnvDot3,
		ARBTextureMirroredRepeat,
		ARBTransposeMatrix,
		ARBVertexBlend,
		ARBVertexBufferObject,
		ARBVertexProgram,
		ATIElementArray,
		ATIEnvmapBumpmap,
		ATIFragmentShader,
		ATIPnTriangles,
		ATITextureMirrorOnce,
		ATIVertexArrayObject,
		ATIVertexStreams,
		ATIXPointSprites,
		ATIXTextureEnvRoute,
		EXTAbgr,
		EXTBgra,
		EXTBlendColor,
		EXTBlendMinmax,
		EXTCompiledVertexArray,
		EXTDrawRangeElements,
		EXTFogCoord,
		EXTLightMaxExponent,
		EXTPackedPixels,
		EXTPalettedTexture,
		EXTPointParameters,
		EXTRescaleNormal,
		EXTSecondaryColor,
		EXTSeparateSpecularColor,
		EXTSharedTexturePalette,
		EXTStencilTwoSide,
		EXTStencilWrap,
		EXTTextureCompressionS3TC,
		EXTTextureEnvCombine,
		EXTTextureEnvDot3,
		EXTTextureFilterAnisotropic,
		EXTTextureLODBias,
		EXTVertexArray,
		EXTVertexShader,
		EXTVertexWeighting,
		HPOcclusionTest,
		NVCopyDepthToColor,
		NVDepthClamp,
		NVEvaluators,
		NVFence,
		NVFogDistance,
		NVLightMaxExponent,
		NVOcclusionQuery,
		NVPackedDepthStencil,
		NVPointSprite,
		NVRegisterCombiners,
		NVRegisterCombiners2,
		NVTexgenReflection,
		NVTextureEnvCombine4,
		NVTextureRectangle,
		NVTextureShader,
		NVTextureShader2,
		NVTextureShader3,
		NVVertexArrayRange,
		NVVertexArrayRange2,
		NVVertexProgram,
		SGISGenerateMipmap,
		SGIXDepthTexture,
		SGIXShadow,
		WGLBufferRegion,
		WGLMakeCurrentRead,
		WGLMultisample,
		WGLPBuffer,
		WGLPixelFormat,
		WGLRenderTexture,
		CoreGL14Constants
{

}
