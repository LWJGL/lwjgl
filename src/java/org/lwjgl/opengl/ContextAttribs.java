/*
 * Copyright (c) 2002-2008 LWJGL Project
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
package org.lwjgl.opengl;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLUtil;

import java.nio.IntBuffer;

/**
 * This class represents the context attributes passed to CreateContextAttribs of the ARB_create_context and
 * ARB_create_context_profile extensions.
 * These attributes can be used to indicate at context creation which OpenGL interface will be used. This includes the
 * OpenGL version, the layer plane on which rendering takes place and also optional debug and forward combatibility modes.
 * (read the ARB_create_context spec for details)
 * <p/>
 * Use of this class is optional. If an OpenGL context is created without passing an instance of this class
 * (or ARB_create_context is not supported), the old context creation code will be used. Support for debug and forward
 * compatible mobes is not guaranteed by the OpenGL implementation. Developers may encounter debug contexts being the same
 * as non-debug contexts or forward compatible contexts having support for deprecated functionality.
 * <p/>
 * If the forwardCompatible
 * attribute is used, LWJGL will not load the deprecated functionality (as defined in the OpenGL 3.0 specification). This
 * means that developers can start working on cleaning up their applications without an OpenGL 3.0 complaint driver.
 * <p/>
 * This extension is not supported on MacOS X. However, in order to enable the GL 3.2 context on MacOS X 10.7 or newer, an
 * instance of this class must be passed to LWJGL. The only valid configuration is <code>new ContextAttribs(3, 2).withProfileCore()</code>,
 * anything else will be ignored.
 *
 * @author spasi <spasi@users.sourceforge.net>
 */
public final class ContextAttribs {

	// Same values for GLX & WGL
	private static final int CONTEXT_ES2_PROFILE_BIT_EXT = 0x00000004;

	private static final int CONTEXT_ROBUST_ACCESS_BIT_ARB = 0x00000004;
	private static final int CONTEXT_RESET_NOTIFICATION_STRATEGY_ARB = 0x8256;
	private static final int
		NO_RESET_NOTIFICATION_ARB = 0x8261,
		LOSE_CONTEXT_ON_RESET_ARB = 0x8252;

	private int majorVersion;
	private int minorVersion;

	private int layerPlane;

	private boolean debug;
	private boolean forwardCompatible;
	private boolean robustAccess;

	private boolean profileCore;
	private boolean profileCompatibility;
	private boolean profileES;

	private boolean loseContextOnReset;

	public ContextAttribs() {
		this(1, 0);
	}

	public ContextAttribs(final int majorVersion, final int minorVersion) {
		if ( majorVersion < 0 || 4 < majorVersion ||
		     minorVersion < 0 ||
		     (majorVersion == 4 && 2 < minorVersion) ||
		     (majorVersion == 3 && 3 < minorVersion) ||
		     (majorVersion == 2 && 1 < minorVersion) ||
		     (majorVersion == 1 && 5 < minorVersion) )
			throw new IllegalArgumentException("Invalid OpenGL version specified: " + majorVersion + '.' + minorVersion);

		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}

	private ContextAttribs(final ContextAttribs attribs) {
		this.majorVersion = attribs.majorVersion;
		this.minorVersion = attribs.minorVersion;

		this.layerPlane = attribs.layerPlane;

		this.debug = attribs.debug;
		this.forwardCompatible = attribs.forwardCompatible;
		this.robustAccess = attribs.robustAccess;

		this.profileCore = attribs.profileCore;
		this.profileCompatibility = attribs.profileCompatibility;
		this.profileES = attribs.profileES;

		this.loseContextOnReset = attribs.loseContextOnReset;
	}

	public int getMajorVersion() {
		return majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public int getLayerPlane() {
		return layerPlane;
	}

	public boolean isDebug() {
		return debug;
	}

	public boolean isForwardCompatible() {
		return forwardCompatible;
	}

	public boolean isProfileCore() {
		return profileCore;
	}

	public boolean isProfileCompatibility() {
		return profileCompatibility;
	}

	public boolean isProfileES() {
		return profileES;
	}

	public ContextAttribs withLayer(final int layerPlane) {
		if ( layerPlane < 0 )
			throw new IllegalArgumentException("Invalid layer plane specified: " + layerPlane);

		if ( layerPlane == this.layerPlane )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.layerPlane = layerPlane;
		return attribs;
	}

	public ContextAttribs withDebug(final boolean debug) {
		if ( debug == this.debug )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.debug = debug;
		return attribs;
	}

	public ContextAttribs withForwardCompatible(final boolean forwardCompatible) {
		if ( forwardCompatible == this.forwardCompatible )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.forwardCompatible = forwardCompatible;
		return attribs;
	}

	public ContextAttribs withProfileCore(final boolean profileCore) {
		if ( majorVersion < 3 || (majorVersion == 3 && minorVersion < 2) )
			throw new IllegalArgumentException("Profiles are only supported on OpenGL version 3.2 or higher.");

		if ( profileCore == this.profileCore )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.profileCore = profileCore;
		if ( profileCore )
			attribs.profileCompatibility = false;

		return attribs;
	}

	public ContextAttribs withProfileCompatibility(final boolean profileCompatibility) {
		if ( majorVersion < 3 || (majorVersion == 3 && minorVersion < 2) )
			throw new IllegalArgumentException("Profiles are only supported on OpenGL version 3.2 or higher.");

		if ( profileCompatibility == this.profileCompatibility )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.profileCompatibility = profileCompatibility;
		if ( profileCompatibility )
			attribs.profileCore = false;

		return attribs;
	}

	public ContextAttribs withProfileES(final boolean profileES) {
		if ( !(majorVersion == 2 && minorVersion == 0) )
			throw new IllegalArgumentException("The OpenGL ES profiles is only supported for OpenGL version 2.0.");

		if ( profileES == this.profileES )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.profileES = profileES;

		return attribs;
	}

	/**
	 * Returns a ContextAttribs instance with CONTEXT_RESET_NOTIFICATION_STRATEGY set
	 * to LOSE_CONTEXT_ON_RESET if the parameter is true or to NO_RESET_NOTIFICATION
	 * if the parameter is false.
	 *
	 * @param loseContextOnReset
	 *
	 * @return the new ContextAttribs
	 */
	public ContextAttribs withLoseContextOnReset(final boolean loseContextOnReset) {
		if ( loseContextOnReset == this.loseContextOnReset )
			return this;

		final ContextAttribs attribs = new ContextAttribs(this);
		attribs.loseContextOnReset = loseContextOnReset;
		return attribs;
	}

	private static ContextAttribsImplementation getImplementation() {
		switch ( LWJGLUtil.getPlatform() ) {
			case LWJGLUtil.PLATFORM_LINUX:
				return new LinuxContextAttribs();
			case LWJGLUtil.PLATFORM_WINDOWS:
				return new WindowsContextAttribs();
			default:
				throw new IllegalStateException("Unsupported platform");
		}
	}

	IntBuffer getAttribList() {
		if ( LWJGLUtil.getPlatform() == LWJGLUtil.PLATFORM_MACOSX )
			return null;

		ContextAttribsImplementation implementation = getImplementation();

		int attribCount = 0;

		if ( !(majorVersion == 1 && minorVersion == 0) )
			attribCount += 2;
		if ( 0 < layerPlane )
			attribCount++;

		int flags = 0;
		if ( debug )
			flags |= implementation.getDebugBit();
		if ( forwardCompatible )
			flags |= implementation.getForwardCompatibleBit();
		if ( robustAccess )
			flags |= CONTEXT_ROBUST_ACCESS_BIT_ARB;
		if ( 0 < flags )
			attribCount++;

		int profileMask = 0;
		if ( profileCore )
			profileMask |= implementation.getProfileCoreBit();
		else if ( profileCompatibility )
			profileMask |= implementation.getProfileCompatibilityBit();
		else if ( profileES )
			profileMask |= CONTEXT_ES2_PROFILE_BIT_EXT;
		if ( 0 < profileMask )
			attribCount++;

		if ( attribCount == 0 )
			return null;

		final IntBuffer attribs = BufferUtils.createIntBuffer((attribCount * 2) + 1);

		if ( !(majorVersion == 1 && minorVersion == 0) ) {
			attribs.put(implementation.getMajorVersionAttrib()).put(majorVersion);
			attribs.put(implementation.getMinorVersionAttrib()).put(minorVersion);
		}
		if ( 0 < layerPlane )
			attribs.put(implementation.getLayerPlaneAttrib()).put(layerPlane);
		if ( 0 < flags )
			attribs.put(implementation.getFlagsAttrib()).put(flags);
		if ( 0 < profileMask )
			attribs.put(implementation.getProfileMaskAttrib()).put(profileMask);
		if ( loseContextOnReset )
			attribs.put(CONTEXT_RESET_NOTIFICATION_STRATEGY_ARB).put(LOSE_CONTEXT_ON_RESET_ARB);

		attribs.put(0);
		attribs.rewind();
		return attribs;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(32);

		sb.append("ContextAttribs:");
		sb.append(" Version=").append(majorVersion).append('.').append(minorVersion);
		sb.append(" - Layer=").append(layerPlane);
		sb.append(" - Debug=").append(debug);
		sb.append(" - ForwardCompatible=").append(forwardCompatible);
		sb.append(" - RobustAccess=").append(robustAccess);
		if ( robustAccess )
			sb.append(" (").append(loseContextOnReset ? "LOSE_CONTEXT_ON_RESET" : "NO_RESET_NOTIFICATION");
		sb.append(" - Profile=");
		if ( profileCore )
			sb.append("Core");
		else if ( profileCompatibility )
			sb.append("Compatibility");
		else
			sb.append("None");

		return sb.toString();
	}

}