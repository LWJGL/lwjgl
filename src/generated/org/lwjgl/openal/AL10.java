/* MACHINE GENERATED FILE, DO NOT EDIT */

package org.lwjgl.openal;

import org.lwjgl.LWJGLException;
import org.lwjgl.BufferChecks;
import java.nio.*;

public final class AL10 {
	/**
	 *Bad value 
	 */
	public static final int AL_INVALID = 0xffffffff;
	/**
	 *Disable value 
	 */
	public static final int AL_NONE = 0x0;
	/**
	 *Boolean False 
	 */
	public static final int AL_FALSE = 0x0;
	/**
	 *Boolean True 
	 */
	public static final int AL_TRUE = 0x1;
	/**
	 * Indicate the type of SOURCE.
	 * Sources can be spatialized
	 */
	public static final int AL_SOURCE_TYPE = 0x200;
	/**
	 *Indicate source has absolute coordinates 
	 */
	public static final int AL_SOURCE_ABSOLUTE = 0x201;
	/**
	 *Indicate Source has listener relative coordinates 
	 */
	public static final int AL_SOURCE_RELATIVE = 0x202;
	/**
	 * Directional source, inner cone angle, in degrees
	 * Range:		[0-360]
	 * Default:	360
	 */
	public static final int AL_CONE_INNER_ANGLE = 0x1001;
	/**
	 * Directional source, outer cone angle, in degrees.
	 * Range:		[0-360]
	 * Default:	360
	 */
	public static final int AL_CONE_OUTER_ANGLE = 0x1002;
	/**
	 * Specify the pitch to be applied, either at source,
	 * or on mixer results, at listener.
	 * Range:	[0.5-2.0]
	 * Default:	1.0
	 */
	public static final int AL_PITCH = 0x1003;
	/**
	 * Specify the current location in three dimensional space.
	 * OpenAL, like OpenGL, uses a right handed coordinate system,
	 * where in a frontal default view X (thumb) points right,
	 * Y points up (index finger), and Z points towards the
	 * viewer/camera (middle finger).
	 * To switch from a left handed coordinate system, flip the
	 * sign on the Z coordinate.
	 * Listener position is always in the world coordinate system.
	 */
	public static final int AL_POSITION = 0x1004;
	/**
	 *Specify the current direction as forward vector. 
	 */
	public static final int AL_DIRECTION = 0x1005;
	/**
	 *Specify the current velocity in three dimensional space. 
	 */
	public static final int AL_VELOCITY = 0x1006;
	/**
	 * Indicate whether source has to loop infinite.
	 * Type: ALboolean
	 * Range:		[TRUE, FALSE]
	 * Default:	FALSE
	 */
	public static final int AL_LOOPING = 0x1007;
	/**
	 * Indicate the buffer to provide sound samples.
	 * Type: ALuint.
	 * Range: any valid Buffer id.
	 */
	public static final int AL_BUFFER = 0x1009;
	/**
	 * Indicate the gain (volume amplification) applied.
	 * Type:		 ALfloat.
	 * Range:		]0.0-	]
	 * A value of 1.0 means un-attenuated/unchanged.
	 * Each division by 2 equals an attenuation of -6dB.
	 * Each multiplicaton with 2 equals an amplification of +6dB.
	 * A value of 0.0 is meaningless with respect to a logarithmic
	 * scale; it is interpreted as zero volume - the channel
	 * is effectively disabled.
	 */
	public static final int AL_GAIN = 0x100a;
	/**
	 * Indicate minimum source attenuation.
	 * Type:		 ALfloat
	 * Range:	[0.0 - 1.0]
	 */
	public static final int AL_MIN_GAIN = 0x100d;
	/**
	 * Indicate maximum source attenuation.
	 * Type:	 ALfloat
	 * Range:	[0.0 - 1.0]
	 */
	public static final int AL_MAX_GAIN = 0x100e;
	/**
	 * Specify the current orientation.
	 * Type:	 ALfv6 (at/up)
	 * Range:	N/A
	 */
	public static final int AL_ORIENTATION = 0x100f;
	public static final int AL_REFERENCE_DISTANCE = 0x1020;
	/**
	 * Indicate the rolloff factor for the source.
	 * Type: ALfloat
	 * Range:		[0.0 - ]
	 * Default:	1.0
	 */
	public static final int AL_ROLLOFF_FACTOR = 0x1021;
	/**
	 * Indicate the gain (volume amplification) applied.
	 * Type:		 ALfloat.
	 * Range:		]0.0-	]
	 * A value of 1.0 means un-attenuated/unchanged.
	 * Each division by 2 equals an attenuation of -6dB.
	 * Each multiplicaton with 2 equals an amplification of +6dB.
	 * A value of 0.0 is meaningless with respect to a logarithmic
	 * scale; it is interpreted as zero volume - the channel
	 * is effectively disabled.
	 */
	public static final int AL_CONE_OUTER_GAIN = 0x1022;
	/**
	 * Specify the maximum distance.
	 * Type:	 ALfloat
	 * Range:	[0.0 - ]
	 */
	public static final int AL_MAX_DISTANCE = 0x1023;
	/**
	 * Specify the channel mask. (Creative)
	 * Type:	 ALuint
	 * Range:	[0 - 255]
	 */
	public static final int AL_CHANNEL_MASK = 0x3000;
	/**
	 *Source state information 
	 */
	public static final int AL_SOURCE_STATE = 0x1010;
	/**
	 *Source state information 
	 */
	public static final int AL_INITIAL = 0x1011;
	/**
	 *Source state information 
	 */
	public static final int AL_PLAYING = 0x1012;
	/**
	 *Source state information 
	 */
	public static final int AL_PAUSED = 0x1013;
	/**
	 *Source state information 
	 */
	public static final int AL_STOPPED = 0x1014;
	/**
	 *Buffer Queue params 
	 */
	public static final int AL_BUFFERS_QUEUED = 0x1015;
	/**
	 *Buffer Queue params 
	 */
	public static final int AL_BUFFERS_PROCESSED = 0x1016;
	/**
	 *Sound buffers: format specifier. 
	 */
	public static final int AL_FORMAT_MONO8 = 0x1100;
	/**
	 *Sound buffers: format specifier. 
	 */
	public static final int AL_FORMAT_MONO16 = 0x1101;
	/**
	 *Sound buffers: format specifier. 
	 */
	public static final int AL_FORMAT_STEREO8 = 0x1102;
	/**
	 *Sound buffers: format specifier. 
	 */
	public static final int AL_FORMAT_STEREO16 = 0x1103;
	/**
	 *Ogg Vorbis format specifier. 
	 */
	public static final int AL_FORMAT_VORBIS_EXT = 0x10003;
	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 * sample frequency marks the maximum significant
	 * frequency component.
	 */
	public static final int AL_FREQUENCY = 0x2001;
	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 * sample frequency marks the maximum significant
	 * frequency component.
	 */
	public static final int AL_BITS = 0x2002;
	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 * sample frequency marks the maximum significant
	 * frequency component.
	 */
	public static final int AL_CHANNELS = 0x2003;
	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 * sample frequency marks the maximum significant
	 * frequency component.
	 */
	public static final int AL_SIZE = 0x2004;
	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 * sample frequency marks the maximum significant
	 * frequency component.
	 */
	public static final int AL_DATA = 0x2005;
	/**
	 * Buffer state.
	 * Not supported for public use (yet).
	 */
	public static final int AL_UNUSED = 0x2010;
	/**
	 * Buffer state.
	 * Not supported for public use (yet).
	 */
	public static final int AL_PENDING = 0x2011;
	/**
	 * Buffer state.
	 * Not supported for public use (yet).
	 */
	public static final int AL_PROCESSED = 0x2012;
	/**
	 *Errors: No Error. 
	 */
	public static final int AL_NO_ERROR = 0x0;
	/**
	 *Illegal name passed as an argument to an AL call. 
	 */
	public static final int AL_INVALID_NAME = 0xa001;
	/**
	 *Illegal enum passed as an argument to an AL call. 
	 */
	public static final int AL_INVALID_ENUM = 0xa002;
	/**
	 * Illegal value passed as an argument to an AL call.
	 * Applies to parameter values, but not to enumerations.
	 */
	public static final int AL_INVALID_VALUE = 0xa003;
	/**
	 * A function was called at inappropriate time,
	 *	or in an inappropriate way, causing an illegal state.
	 * This can be an incompatible ALenum, object ID,
	 *	and/or function.
	 */
	public static final int AL_INVALID_OPERATION = 0xa004;
	/**
	 * A function could not be completed,
	 * because there is not enough memory available.
	 */
	public static final int AL_OUT_OF_MEMORY = 0xa005;
	/**
	 *Context strings: Vendor 
	 */
	public static final int AL_VENDOR = 0xb001;
	/**
	 *Context strings: Version 
	 */
	public static final int AL_VERSION = 0xb002;
	/**
	 *Context strings: Renderer 
	 */
	public static final int AL_RENDERER = 0xb003;
	/**
	 *Context strings: Extensions 
	 */
	public static final int AL_EXTENSIONS = 0xb004;
	/**
	 *Doppler scale.	Default 1.0 
	 */
	public static final int AL_DOPPLER_FACTOR = 0xc000;
	/**
	 *Doppler velocity.	Default 1.0 
	 */
	public static final int AL_DOPPLER_VELOCITY = 0xc001;
	/**
	 *Distance model.	Default INVERSE_DISTANCE_CLAMPED 
	 */
	public static final int AL_DISTANCE_MODEL = 0xd000;
	/**
	 *Distance model 
	 */
	public static final int AL_INVERSE_DISTANCE = 0xd001;
	/**
	 *Distance model 
	 */
	public static final int AL_INVERSE_DISTANCE_CLAMPED = 0xd002;

	private AL10() {
	}

	static native void initNativeStubs() throws LWJGLException;

	/**
	 * The application can temporarily disable certain AL capabilities on a per Context
	 * basis. This allows the driver implementation to optimize for certain subsets of
	 * operations. Enabling and disabling capabilities is handled using a function pair.
	 * @param capability name of a capability to enable
	 */
	public static void alEnable(int capability) {
		nalEnable(capability);
		Util.checkALError();
	}
	private static native void nalEnable(int capability);

	/**
	 * The application can temporarily disable certain AL capabilities on a per Context
	 * basis. This allows the driver implementation to optimize for certain subsets of
	 * operations. Enabling and disabling capabilities is handled using a function pair.
	 * @param capability name of a capability to disable
	 */
	public static void alDisable(int capability) {
		nalDisable(capability);
		Util.checkALError();
	}
	private static native void nalDisable(int capability);

	/**
	 * The application can also query whether a given capability is currently enabled or
	 * not.
	 * <p>
	 * If the token used to specify target is not legal, an AL_INVALID_ENUM error will be
	 * generated.
	 * </p>
	 * <p>
	 * At this time, this mechanism is not used. There are no valid targets.
	 * </p>
	 * @param capability name of a capability to check
	 * @return true if named feature is enabled
	 */
	public static boolean alIsEnabled(int capability) {
		boolean __result = nalIsEnabled(capability);
		Util.checkALError();
		return __result;
	}
	private static native boolean nalIsEnabled(int capability);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @return boolean state described by pname will be returned.
	 */
	public static boolean alGetBoolean(int pname) {
		boolean __result = nalGetBoolean(pname);
		Util.checkALError();
		return __result;
	}
	private static native boolean nalGetBoolean(int pname);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @return int state described by pname will be returned.
	 */
	public static int alGetInteger(int pname) {
		int __result = nalGetInteger(pname);
		Util.checkALError();
		return __result;
	}
	private static native int nalGetInteger(int pname);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @return float state described by pname will be returned.
	 */
	public static float alGetFloat(int pname) {
		float __result = nalGetFloat(pname);
		Util.checkALError();
		return __result;
	}
	private static native float nalGetFloat(int pname);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @return double state described by pname will be returned.
	 */
	public static double alGetDouble(int pname) {
		double __result = nalGetDouble(pname);
		Util.checkALError();
		return __result;
	}
	private static native double nalGetDouble(int pname);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @param pname state to be queried
	 * @param data Buffer to place the integers in
	 */
	public static void alGetInteger(int pname, IntBuffer data) {
		BufferChecks.checkBuffer(data, 1);
		nalGetIntegerv(pname, data, data.position());
		Util.checkALError();
	}
	private static native void nalGetIntegerv(int pname, IntBuffer data, int data_position);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @param pname state to be queried
	 * @param data Buffer to place the floats in
	 */
	public static void alGetFloat(int pname, FloatBuffer data) {
		BufferChecks.checkBuffer(data, 1);
		nalGetFloatv(pname, data, data.position());
		Util.checkALError();
	}
	private static native void nalGetFloatv(int pname, FloatBuffer data, int data_position);

	/**
	 * Like OpenGL, AL uses a simplified interface for querying global state.
	 * Legal values are e.g. AL_DOPPLER_FACTOR, AL_DOPPLER_VELOCITY,
	 * AL_DISTANCE_MODEL.
	 * <p>
	 * <code>null</code> destinations are quietly ignored. AL_INVALID_ENUM is the response to errors
	 * in specifying pName. The amount of memory required in the destination
	 * depends on the actual state requested.
	 * </p>
	 * @param pname state to be queried
	 * @param data Buffer to place the doubles in
	 */
	public static void alGetDouble(int pname, DoubleBuffer data) {
		BufferChecks.checkBuffer(data, 1);
		nalGetDoublev(pname, data, data.position());
		Util.checkALError();
	}
	private static native void nalGetDoublev(int pname, DoubleBuffer data, int data_position);

	/**
	 * The application can retrieve state information global to the current AL Context.
	 * GetString will return a pointer to a constant string. Valid values for param are
	 * VERSION, RENDERER, VENDOR, and EXTENSIONS, as well as the error codes
	 * defined by AL. The application can use GetString to retrieve a string for an error
	 * code.
	 * @param pname The property to be returned
	 * @return OpenAL String property
	 */
	public static native java.lang.String alGetString(int pname);

	/**
	 * The AL detects only a subset of those conditions that could be considered errors.
	 * This is because in many cases error checking would adversely impact the
	 * performance of an error-free program.
	 * <p>
	 * Each detectable error is assigned a numeric
	 * code. When an error is detected by AL, a flag is set and the error code is recorded.
	 * Further errors, if they occur, do not affect this recorded code. When GetError is
	 * called, the code is returned and the flag is cleared, so that a further error will again
	 * record its code. If a call to GetError returns AL_NO_ERROR then there has been no
	 * detectable error since the last call to GetError (or since the AL was initialized).
	 * </p>
	 * <p>
	 * Error codes can be mapped to strings. The GetString function returns a pointer to a
	 * constant (literal) string that is identical to the identifier used for the enumeration
	 * value, as defined in the specification.
	 * </p>
	 * <p>
	 * AL_NO_ERROR - "No Error" token.<br>
	 * AL_INVALID_NAME - Invalid Name parameter.<br>
	 * AL_INVALID_ENUM - Invalid parameter.<br>
	 * AL_INVALID_VALUE - Invalid enum parameter value.<br>
	 * AL_INVALID_OPERATION - Illegal call.<br>
	 * AL_OUT_OF_MEMORY - Unable to allocate memory.<br>
	 * </p>
	 * <p>
	 * The table summarizes the AL errors. Currently, when an error flag is set, results of
	 * AL operations are undefined only if AL_OUT_OF_MEMORY has occured. In other
	 * cases, the command generating the error is ignored so that it has no effect on AL
	 * state or output buffer contents. If the error generating command returns a value, it
	 * returns zero. If the generating command modifies values through a pointer
	 * argument, no change is made to these values. These error semantics apply only to
	 * AL errors, not to system errors such as memory access errors.
	 * </p>
	 * <p>
	 * Several error generation conditions are implicit in the description of the various AL
	 * commands. First, if a command that requires an enumerated value is passed a value
	 * that is not one of those specified as allowable for that command, the error
	 * AL_INVALID_ENUM results. This is the case even if the argument is a pointer to a
	 * symbolic constant if that value is not allowable for the given command. This will
	 * occur whether the value is allowable for other functions, or an invalid integer value.
	 * </p>
	 * <p>
	 * Integer parameters that are used as names for AL objects such as Buffers and
	 * Sources are checked for validity. If an invalid name parameter is specified in an AL
	 * command, an AL_INVALID_NAME error will be generated, and the command is
	 * ignored.
	 * </p>
	 * <p>
	 * If a negative integer is provided where an argument of type sizei is specified, the
	 * error AL_INVALID_VALUE results. The same error will result from attempts to set
	 * integral and floating point values for attributes exceeding the legal range for these.
	 * The specification does not guarantee that the implementation emits
	 * AL_INVALID_VALUE if a NaN or Infinity value is passed in for a float or double
	 * argument (as the specification does not enforce possibly expensive testing of
	 * floating point values).
	 * </p>
	 * <p>
	 * Commands can be invalid. For example, certain commands might not be applicable
	 * to a given object. There are also illegal combinations of tokens and values as
	 * arguments to a command. AL responds to any such illegal command with an
	 * AL_INVALID_OPERATION error.
	 * </p>
	 * <p>
	 * If memory is exhausted as a side effect of the execution of an AL command, either
	 * on system level or by exhausting the allocated resources at AL's internal disposal,
	 * the error AL_OUT_OF_MEMORY may be generated. This can also happen independent
	 * of recent commands if AL has to request memory for an internal task and fails to
	 * allocate the required memory from the operating system.
	 * </p>
	 * <p>
	 * Otherwise errors are generated only for conditions that are explicitely described in
	 * this specification.
	 * </p>
	 * @return current error state
	 */
	public static native int alGetError();

	/**
	 * To verify that a given extension is available for the current context and the device it
	 * is associated with, use this method.
	 * <p>
	 * A <code>null</code> name argument returns AL_FALSE, as do invalid and unsupported string
	 * tokens. A <code>null</code> deviceHandle will result in an INVALID_DEVICE error.
	 * </p>
	 * @param fname String describing the desired extension
	 * @return true if extension is available, false if not
	 */
	public static boolean alIsExtensionPresent(String fname) {
		BufferChecks.checkNotNull(fname);
		boolean __result = nalIsExtensionPresent(fname);
		Util.checkALError();
		return __result;
	}
	private static native boolean nalIsExtensionPresent(String fname);

	/**
	 * <p>
	 * To obtain enumeration values for extensions, the application has to use
	 * GetEnumValue of an extension token. Enumeration values are defined within the
	 * AL namespace and allocated according to specification of the core API and the
	 * extensions, thus they are context-independent.
	 * </p>
	 * <p>
	 * Returns 0 if the enumeration can not be found. The presence of an enum value does
	 * not guarantee the applicability of an extension to the current context. A non-zero
	 * return indicates merely that the implementation is aware of the existence of this
	 * extension. Implementations should not attempt to return 0 to indicate that the
	 * extensions is not supported for the current context.
	 * </p>
	 * @param ename String describing an OpenAL enum
	 * @return Actual int for the described enumeration name
	 */
	public static int alGetEnumValue(String ename) {
		BufferChecks.checkNotNull(ename);
		int __result = nalGetEnumValue(ename);
		Util.checkALError();
		return __result;
	}
	private static native int nalGetEnumValue(String ename);

	/**
	 * Listener attributes are changed using the Listener group of commands.
	 * @param pname name of the attribute to be set
	 * @param value value to set the attribute to
	 */
	public static void alListeneri(int pname, int value) {
		nalListeneri(pname, value);
		Util.checkALError();
	}
	private static native void nalListeneri(int pname, int value);

	/**
	 * Listener attributes are changed using the Listener group of commands.
	 * @param pname name of the attribute to be set
	 * @param value floating point value to set the attribute to
	 */
	public static void alListenerf(int pname, float value) {
		nalListenerf(pname, value);
		Util.checkALError();
	}
	private static native void nalListenerf(int pname, float value);

	/**
	 * Listener attributes are changed using the Listener group of commands.
	 * @param pname name of the attribute to be set
	 * @param value FloatBuffer containing value to set the attribute to
	 */
	public static void alListener(int pname, FloatBuffer value) {
		BufferChecks.checkBuffer(value, 1);
		nalListenerfv(pname, value, value.position());
		Util.checkALError();
	}
	private static native void nalListenerfv(int pname, FloatBuffer value, int value_position);

	/**
	 * Listener attributes are changed using the Listener group of commands.
	 * @param pname name of the attribute to be set
	 * @param v1 value value 1
	 * @param v2 value value 2
	 * @param v3 float value 3
	 */
	public static void alListener3f(int pname, float v1, float v2, float v3) {
		nalListener3f(pname, v1, v2, v3);
		Util.checkALError();
	}
	private static native void nalListener3f(int pname, float v1, float v2, float v3);

	/**
	 * Listener state is maintained inside the AL implementation and can be queried in
	 * full.
	 * @param pname name of the attribute to be retrieved
	 * @return int
	 */
	public static int alGetListeneri(int pname) {
		int __result = nalGetListeneri(pname);
		Util.checkALError();
		return __result;
	}
	private static native int nalGetListeneri(int pname);

	/**
	 * Listener state is maintained inside the AL implementation and can be queried in
	 * full.
	 * @param pname name of the attribute to be retrieved
	 * @return float
	 */
	public static float alGetListenerf(int pname) {
		float __result = nalGetListenerf(pname);
		Util.checkALError();
		return __result;
	}
	private static native float nalGetListenerf(int pname);

	/**
	 * Listener state is maintained inside the AL implementation and can be queried in
	 * full.
	 * @param pname name of the attribute to be retrieved
	 * @param floatdata Buffer to write floats to
	 */
	public static void alGetListener(int pname, FloatBuffer floatdata) {
		BufferChecks.checkBuffer(floatdata, 1);
		nalGetListenerfv(pname, floatdata, floatdata.position());
		Util.checkALError();
	}
	private static native void nalGetListenerfv(int pname, FloatBuffer floatdata, int floatdata_position);

	/**
	 * The application requests a number of Sources using GenSources.
	 * @param sources array holding sources
	 */
	public static void alGenSources(IntBuffer sources) {
		BufferChecks.checkDirect(sources);
		nalGenSources((sources.remaining()), sources, sources.position());
		Util.checkALError();
	}
	private static native void nalGenSources(int n, IntBuffer sources, int sources_position);

	/**
	 * The application requests deletion of a number of Sources by DeleteSources.
	 * @param sources Source array to delete from
	 */
	public static void alDeleteSources(IntBuffer sources) {
		BufferChecks.checkDirect(sources);
		nalDeleteSources((sources.remaining()), sources, sources.position());
		Util.checkALError();
	}
	private static native void nalDeleteSources(int n, IntBuffer sources, int sources_position);

	/**
	 * The application can verify whether a source name is valid using the IsSource query.
	 * @param id id of source to be testes for validity
	 * @return true if id is valid, false if not
	 */
	public static boolean alIsSource(int id) {
		boolean __result = nalIsSource(id);
		Util.checkALError();
		return __result;
	}
	private static native boolean nalIsSource(int id);

	/**
	 * Specifies the position and other properties as taken into account during
	 * sound processing.
	 * @param source Source to det property on
	 * @param pname property to set
	 * @param value value of property
	 */
	public static void alSourcei(int source, int pname, int value) {
		nalSourcei(source, pname, value);
		Util.checkALError();
	}
	private static native void nalSourcei(int source, int pname, int value);

	/**
	 * Specifies the position and other properties as taken into account during
	 * sound processing.
	 * @param source Source to det property on
	 * @param pname property to set
	 * @param value value of property
	 */
	public static void alSourcef(int source, int pname, float value) {
		nalSourcef(source, pname, value);
		Util.checkALError();
	}
	private static native void nalSourcef(int source, int pname, float value);

	/**
	 * Specifies the position and other properties as taken into account during
	 * sound processing.
	 * @param source Source to set property on
	 * @param pname property to set
	 * @param value FloatBuffer containing value of property
	 */
	public static void alSource(int source, int pname, FloatBuffer value) {
		BufferChecks.checkBuffer(value, 1);
		nalSourcefv(source, pname, value, value.position());
		Util.checkALError();
	}
	private static native void nalSourcefv(int source, int pname, FloatBuffer value, int value_position);

	/**
	 * Specifies the position and other properties as taken into account during
	 * sound processing.
	 * @param source Source to set property on
	 * @param pname property to set
	 * @param v1 value 1 of property
	 * @param v2 value 2 of property
	 * @param v3 value 3 of property
	 */
	public static void alSource3f(int source, int pname, float v1, float v2, float v3) {
		nalSource3f(source, pname, v1, v2, v3);
		Util.checkALError();
	}
	private static native void nalSource3f(int source, int pname, float v1, float v2, float v3);

	/**
	 * Source state is maintained inside the AL implementation, and the current attributes
	 * can be queried. The performance of such queries is implementation dependent, no
	 * performance guarantees are made.
	 * @param source source to get property from
	 * @param pname name of property
	 * @return int
	 */
	public static int alGetSourcei(int source, int pname) {
		int __result = nalGetSourcei(source, pname);
		Util.checkALError();
		return __result;
	}
	private static native int nalGetSourcei(int source, int pname);

	/**
	 * Source state is maintained inside the AL implementation, and the current attributes
	 * can be queried. The performance of such queries is implementation dependent, no
	 * performance guarantees are made.
	 * @param source source to get property from
	 * @param pname name of property
	 * @return float
	 */
	public static float alGetSourcef(int source, int pname) {
		float __result = nalGetSourcef(source, pname);
		Util.checkALError();
		return __result;
	}
	private static native float nalGetSourcef(int source, int pname);

	/**
	 * Source state is maintained inside the AL implementation, and the current attributes
	 * can be queried. The performance of such queries is implementation dependent, no
	 * performance guarantees are made.
	 * @param source Source to get property from
	 * @param pname property to get
	 * @param floatdata Buffer to write floats to
	 */
	public static void alGetSource(int source, int pname, FloatBuffer floatdata) {
		BufferChecks.checkBuffer(floatdata, 1);
		nalGetSourcefv(source, pname, floatdata, floatdata.position());
		Util.checkALError();
	}
	private static native void nalGetSourcefv(int source, int pname, FloatBuffer floatdata, int floatdata_position);

	/**
	 * Play() applied to an AL_INITIAL Source will promote the Source to AL_PLAYING, thus
	 * the data found in the Buffer will be fed into the processing, starting at the
	 * beginning. Play() applied to a AL_PLAYING Source will restart the Source from the
	 * beginning. It will not affect the configuration, and will leave the Source in
	 * AL_PLAYING state, but reset the sampling offset to the beginning. Play() applied to a
	 * AL_PAUSED Source will resume processing using the Source state as preserved at the
	 * Pause() operation. Play() applied to a AL_STOPPED Source will propagate it to
	 * AL_INITIAL then to AL_PLAYING immediately.
	 * @param sources array of sources to play
	 */
	public static void alSourcePlay(IntBuffer sources) {
		BufferChecks.checkDirect(sources);
		nalSourcePlayv((sources.remaining()), sources, sources.position());
		Util.checkALError();
	}
	private static native void nalSourcePlayv(int n, IntBuffer sources, int sources_position);

	/**
	 * Pause() applied to an AL_INITIAL Source is a legal NOP. Pause() applied to a
	 * AL_PLAYING Source will change its state to AL_PAUSED. The Source is exempt from
	 * processing, its current state is preserved. Pause() applied to a AL_PAUSED Source is a
	 * legal NOP. Pause() applied to a AL_STOPPED Source is a legal NOP.
	 * @param sources array of sources to pause
	 */
	public static void alSourcePause(IntBuffer sources) {
		BufferChecks.checkDirect(sources);
		nalSourcePausev((sources.remaining()), sources, sources.position());
		Util.checkALError();
	}
	private static native void nalSourcePausev(int n, IntBuffer sources, int sources_position);

	/**
	 * Stop() applied to an AL_INITIAL Source is a legal NOP. Stop() applied to a AL_PLAYING
	 * Source will change its state to AL_STOPPED. The Source is exempt from processing,
	 * its current state is preserved. Stop() applied to a AL_PAUSED Source will change its
	 * state to AL_STOPPED, with the same consequences as on a AL_PLAYING Source. Stop()
	 * applied to a AL_STOPPED Source is a legal NOP.
	 * @param sources array of sources to stop
	 */
	public static void alSourceStop(IntBuffer sources) {
		BufferChecks.checkDirect(sources);
		nalSourceStopv((sources.remaining()), sources, sources.position());
		Util.checkALError();
	}
	private static native void nalSourceStopv(int n, IntBuffer sources, int sources_position);

	/**
	 * Rewind() applied to an AL_INITIAL Source is a legal NOP. Rewind() applied to a
	 * AL_PLAYING Source will change its state to AL_STOPPED then AL_INITIAL. The Source is
	 * exempt from processing, its current state is preserved, with the exception of the
	 * sampling offset which is reset to the beginning. Rewind() applied to a AL_PAUSED
	 * Source will change its state to AL_INITIAL, with the same consequences as on a
	 * AL_PLAYING Source. Rewind() applied to a AL_STOPPED Source promotes the Source to
	 * AL_INITIAL, resetting the sampling offset to the beginning.
	 * @param sources array of sources to rewind
	 */
	public static void alSourceRewind(IntBuffer sources) {
		BufferChecks.checkDirect(sources);
		nalSourceRewindv((sources.remaining()), sources, sources.position());
		Util.checkALError();
	}
	private static native void nalSourceRewindv(int n, IntBuffer sources, int sources_position);

	/**
	 * Play() applied to an AL_INITIAL Source will promote the Source to AL_PLAYING, thus
	 * the data found in the Buffer will be fed into the processing, starting at the
	 * beginning. Play() applied to a AL_PLAYING Source will restart the Source from the
	 * beginning. It will not affect the configuration, and will leave the Source in
	 * AL_PLAYING state, but reset the sampling offset to the beginning. Play() applied to a
	 * AL_PAUSED Source will resume processing using the Source state as preserved at the
	 * Pause() operation. Play() applied to a AL_STOPPED Source will propagate it to
	 * AL_INITIAL then to AL_PLAYING immediately.
	 * @param source Source to play
	 */
	public static void alSourcePlay(int source) {
		nalSourcePlay(source);
		Util.checkALError();
	}
	private static native void nalSourcePlay(int source);

	/**
	 * Pause() applied to an AL_INITIAL Source is a legal NOP. Pause() applied to a
	 * AL_PLAYING Source will change its state to AL_PAUSED. The Source is exempt from
	 * processing, its current state is preserved. Pause() applied to a AL_PAUSED Source is a
	 * legal NOP. Pause() applied to a AL_STOPPED Source is a legal NOP.
	 * @param source Source to pause
	 */
	public static void alSourcePause(int source) {
		nalSourcePause(source);
		Util.checkALError();
	}
	private static native void nalSourcePause(int source);

	/**
	 * Stop() applied to an AL_INITIAL Source is a legal NOP. Stop() applied to a AL_PLAYING
	 * Source will change its state to AL_STOPPED. The Source is exempt from processing,
	 * its current state is preserved. Stop() applied to a AL_PAUSED Source will change its
	 * state to AL_STOPPED, with the same consequences as on a AL_PLAYING Source. Stop()
	 * applied to a AL_STOPPED Source is a legal NOP.
	 * @param source Source to stop
	 */
	public static void alSourceStop(int source) {
		nalSourceStop(source);
		Util.checkALError();
	}
	private static native void nalSourceStop(int source);

	/**
	 * Rewind() applied to an AL_INITIAL Source is a legal NOP. Rewind() applied to a
	 * AL_PLAYING Source will change its state to AL_STOPPED then AL_INITIAL. The Source is
	 * exempt from processing, its current state is preserved, with the exception of the
	 * sampling offset which is reset to the beginning. Rewind() applied to a AL_PAUSED
	 * Source will change its state to AL_INITIAL, with the same consequences as on a
	 * AL_PLAYING Source. Rewind() applied to a AL_STOPPED Source promotes the Source to
	 * AL_INITIAL, resetting the sampling offset to the beginning.
	 * @param source Source to rewind
	 */
	public static void alSourceRewind(int source) {
		nalSourceRewind(source);
		Util.checkALError();
	}
	private static native void nalSourceRewind(int source);

	/**
	 * The application requests a number of Buffers using GenBuffers.
	 * @param buffers holding buffers
	 */
	public static void alGenBuffers(IntBuffer buffers) {
		BufferChecks.checkDirect(buffers);
		nalGenBuffers((buffers.remaining()), buffers, buffers.position());
		Util.checkALError();
	}
	private static native void nalGenBuffers(int n, IntBuffer buffers, int buffers_position);

	/**
	 * <p>
	 * The application requests deletion of a number of Buffers by calling DeleteBuffers.
	 * </p>
	 * <p>
	 * Once deleted, Names are no longer valid for use with AL function calls. Any such
	 * use will cause an AL_INVALID_NAME error. The implementation is free to defer actual
	 * release of resources.
	 * </p>
	 * <p>
	 * IsBuffer(bname) can be used to verify deletion of a buffer. Deleting bufferName 0 is
	 * a legal NOP in both scalar and vector forms of the command. The same is true for
	 * unused buffer names, e.g. such as not allocated yet, or as released already.
	 * @param buffers Buffer to delete from
	 */
	public static void alDeleteBuffers(IntBuffer buffers) {
		BufferChecks.checkDirect(buffers);
		nalDeleteBuffers((buffers.remaining()), buffers, buffers.position());
		Util.checkALError();
	}
	private static native void nalDeleteBuffers(int n, IntBuffer buffers, int buffers_position);

	/**
	 * The application can verify whether a buffer Name is valid using the IsBuffer query.
	 * @param buffer buffer to be tested for validity
	 * @return true if supplied buffer is valid, false if not
	 */
	public static boolean alIsBuffer(int buffer) {
		boolean __result = nalIsBuffer(buffer);
		Util.checkALError();
		return __result;
	}
	private static native boolean nalIsBuffer(int buffer);

	/**
	 * <p>
	 * A special case of Buffer state is the actual sound sample data stored in asociation
	 * with the Buffer. Applications can specify sample data using BufferData.
	 * </p>
	 * <p>
	 * The data specified is copied to an internal software, or if possible, hardware buffer.
	 * The implementation is free to apply decompression, conversion, resampling, and
	 * filtering as needed. The internal format of the Buffer is not exposed to the
	 * application, and not accessible. Valid formats are AL_FORMAT_MONO8,
	 * AL_FORMAT_MONO16, AL_FORMAT_STEREO8, and AL_FORMAT_STEREO16. An
	 * implementation may expose other formats, see the chapter on Extensions for
	 * information on determining if additional formats are supported.
	 * </p>
	 * <p>
	 * Applications should always check for an error condition after attempting to specify
	 * buffer data in case an implementation has to generate an AL_OUT_OF_MEMORY or
	 * conversion related AL_INVALID_VALUE error. The application is free to reuse the
	 * memory specified by the data pointer once the call to BufferData returns. The
	 * implementation has to dereference, e.g. copy, the data during BufferData execution.
	 * </p>
	 * @param buffer Buffer to fill
	 * @param format format sound data is in
	 * @param data location of data
	 * @param freq frequency of data
	 */
	public static void alBufferData(int buffer, int format, ByteBuffer data, int freq) {
		BufferChecks.checkDirect(data);
		nalBufferData(buffer, format, data, data.position(), (data.remaining()), freq);
		Util.checkALError();
	}
	/**
	 * <p>
	 * A special case of Buffer state is the actual sound sample data stored in asociation
	 * with the Buffer. Applications can specify sample data using BufferData.
	 * </p>
	 * <p>
	 * The data specified is copied to an internal software, or if possible, hardware buffer.
	 * The implementation is free to apply decompression, conversion, resampling, and
	 * filtering as needed. The internal format of the Buffer is not exposed to the
	 * application, and not accessible. Valid formats are AL_FORMAT_MONO8,
	 * AL_FORMAT_MONO16, AL_FORMAT_STEREO8, and AL_FORMAT_STEREO16. An
	 * implementation may expose other formats, see the chapter on Extensions for
	 * information on determining if additional formats are supported.
	 * </p>
	 * <p>
	 * Applications should always check for an error condition after attempting to specify
	 * buffer data in case an implementation has to generate an AL_OUT_OF_MEMORY or
	 * conversion related AL_INVALID_VALUE error. The application is free to reuse the
	 * memory specified by the data pointer once the call to BufferData returns. The
	 * implementation has to dereference, e.g. copy, the data during BufferData execution.
	 * </p>
	 * @param buffer Buffer to fill
	 * @param format format sound data is in
	 * @param data location of data
	 * @param freq frequency of data
	 */
	public static void alBufferData(int buffer, int format, IntBuffer data, int freq) {
		BufferChecks.checkDirect(data);
		nalBufferData(buffer, format, data, data.position() << 2, (data.remaining() << 2), freq);
		Util.checkALError();
	}
	/**
	 * <p>
	 * A special case of Buffer state is the actual sound sample data stored in asociation
	 * with the Buffer. Applications can specify sample data using BufferData.
	 * </p>
	 * <p>
	 * The data specified is copied to an internal software, or if possible, hardware buffer.
	 * The implementation is free to apply decompression, conversion, resampling, and
	 * filtering as needed. The internal format of the Buffer is not exposed to the
	 * application, and not accessible. Valid formats are AL_FORMAT_MONO8,
	 * AL_FORMAT_MONO16, AL_FORMAT_STEREO8, and AL_FORMAT_STEREO16. An
	 * implementation may expose other formats, see the chapter on Extensions for
	 * information on determining if additional formats are supported.
	 * </p>
	 * <p>
	 * Applications should always check for an error condition after attempting to specify
	 * buffer data in case an implementation has to generate an AL_OUT_OF_MEMORY or
	 * conversion related AL_INVALID_VALUE error. The application is free to reuse the
	 * memory specified by the data pointer once the call to BufferData returns. The
	 * implementation has to dereference, e.g. copy, the data during BufferData execution.
	 * </p>
	 * @param buffer Buffer to fill
	 * @param format format sound data is in
	 * @param data location of data
	 * @param freq frequency of data
	 */
	public static void alBufferData(int buffer, int format, ShortBuffer data, int freq) {
		BufferChecks.checkDirect(data);
		nalBufferData(buffer, format, data, data.position() << 1, (data.remaining() << 1), freq);
		Util.checkALError();
	}
	private static native void nalBufferData(int buffer, int format, Buffer data, int data_position, int size, int freq);

	/**
	 * Buffer state is maintained inside the AL implementation and can be queried in full.<br>
	 * ALC_FREQUENCY - specified in samples per second, i.e. units of Hertz [Hz].<br>
	 * ALC_SIZE - Size in bytes of the buffer data.<br>
	 * @param buffer buffer to get property from
	 * @param pname name of property to retrieve
	 */
	public static int alGetBufferi(int buffer, int pname) {
		int __result = nalGetBufferi(buffer, pname);
		Util.checkALError();
		return __result;
	}
	private static native int nalGetBufferi(int buffer, int pname);

	/**
	 * Buffer state is maintained inside the AL implementation and can be queried in full.<br>
	 * ALC_FREQUENCY - specified in samples per second, i.e. units of Hertz [Hz].<br>
	 * ALC_SIZE - Size in bytes of the buffer data.<br>
	 * @param buffer buffer to get property from
	 * @param pname name of property to retrieve
	 * @return float
	 */
	public static float alGetBufferf(int buffer, int pname) {
		float __result = nalGetBufferf(buffer, pname);
		Util.checkALError();
		return __result;
	}
	private static native float nalGetBufferf(int buffer, int pname);

	/**
	 * <p>
	 * The application can queue up one or multiple buffer names using
	 * SourceQueueBuffers. The buffers will be queued in the sequence in which they
	 * appear in the array.
	 * </p>
	 * <p>
	 * This command is legal on a Source in any state (to allow for streaming, queueing
	 * has to be possible on a AL_PLAYING Source). Queues are read-only with exception of
	 * the unqueue operation. The Buffer Name AL_NONE (i.e. 0) can be queued.
	 * </p>
	 * @param source source to queue buffers onto
	 * @param buffers buffers to be queued
	 */
	public static void alSourceQueueBuffers(int source, IntBuffer buffers) {
		BufferChecks.checkDirect(buffers);
		nalSourceQueueBuffers(source, (buffers.remaining()), buffers, buffers.position());
		Util.checkALError();
	}
	private static native void nalSourceQueueBuffers(int source, int n, IntBuffer buffers, int buffers_position);

	/**
	 * <p>
	 * Once a queue entry for a buffer has been appended to a queue and is pending
	 * processing, it should not be changed. Removal of a given queue entry is not possible
	 * unless either the Source is AL_STOPPED (in which case then entire queue is considered
	 * processed), or if the queue entry has already been processed (AL_PLAYING or AL_PAUSED
	 * Source).
	 * </p>
	 * <p>
	 * The Unqueue command removes a number of buffers entries that have nished
	 * processing, in the order of appearance, from the queue. The operation will fail if
	 * more buffers are requested than available, leaving the destination arguments
	 * unchanged. An AL_INVALID_VALUE error will be thrown. If no error, the destination
	 * argument will have been updated accordingly.
	 * </p>
	 * @param source source to unqueue buffers from
	 * @param buffers IntBuffer containing list of names that were unqueued
	 */
	public static void alSourceUnqueueBuffers(int source, IntBuffer buffers) {
		BufferChecks.checkDirect(buffers);
		nalSourceUnqueueBuffers(source, (buffers.remaining()), buffers, buffers.position());
		Util.checkALError();
	}
	private static native void nalSourceUnqueueBuffers(int source, int n, IntBuffer buffers, int buffers_position);

	/**
	 * <p>
	 * Samples usually use the entire dynamic range of the chosen format/encoding,
	 * independent of their real world intensity. In other words, a jet engine and a
	 * clockwork both will have samples with full amplitude. The application will then
	 * have to adjust Source AL_GAIN accordingly to account for relative differences.
	 * </p>
	 * <p>
	 * Source AL_GAIN is then attenuated by distance. The effective attenuation of a Source
	 * depends on many factors, among which distance attenuation and source and
	 * Listener AL_GAIN are only some of the contributing factors. Even if the source and
	 * Listener AL_GAIN exceed 1.0 (amplification beyond the guaranteed dynamic range),
	 * distance and other attenuation might ultimately limit the overall AL_GAIN to a value
	 * below 1.0.
	 * </p>
	 * <p>
	 * AL currently supports three modes of operation with respect to distance
	 * attenuation. It supports two distance-dependent attenuation models, one which is
	 * similar to the IASIG I3DL2 (and DS3D) model. The application choses one of these
	 * two models (or can chose to disable distance-dependent attenuation effects model)
	 * on a per-context basis.
	 * </p>
	 * <p>
	 * Legal arguments are AL_NONE, AL_INVERSE_DISTANCE, and
	 * AL_INVERSE_DISTANCE_CLAMPED.
	 * <br>
	 * <br>
	 * AL_NONE bypasses all distance attenuation
	 * calculation for all Sources. The implementation is expected to optimize this
	 * situation.
	 * <br>
	 * <br>
	 * AL_INVERSE_DISTANCE_CLAMPED is the DS3D model, with
	 * AL_REFERENCE_DISTANCE indicating both the reference distance and the distance
	 * below which gain will be clamped.
	 * <br>
	 * <br>
	 * AL_INVERSE_DISTANCE is equivalent to the DS3D
	 * model with the exception that AL_REFERENCE_DISTANCE does not imply any
	 * clamping.
	 * <br>
	 * <br>
	 * The AL implementation is still free to apply any range clamping as
	 * necessary. The current distance model chosen can be queried using GetIntegerv and
	 * AL_DISTANCE_MODEL.
	 * </p>
	 * @param value distance model to be set
	 */
	public static void alDistanceModel(int value) {
		nalDistanceModel(value);
		Util.checkALError();
	}
	private static native void nalDistanceModel(int value);

	/**
	 * The Doppler Effect depends on the velocities of Source and Listener relative to the
	 * medium, and the propagation speed of sound in that medium. The application
	 * might want to emphasize or de-emphasize the Doppler Effect as physically accurate
	 * calculation might not give the desired results. The amount of frequency shift (pitch
	 * change) is proportional to the speed of listener and source along their line of sight.
	 * The application can increase or decrease that frequency shift by specifying the
	 * scaling factor AL should apply to the result of the calculation.
	 * <br>
	 * <br>
	 * The Doppler Effect as implemented by AL is described by the formula below. Effects
	 * of the medium (air, water) moving with respect to listener and source are ignored.
	 * AL_DOPPLER_VELOCITY is the propagation speed relative to which the Source
	 * velocities are interpreted.
	 * <p>
	 * <pre>
	 *	 VD: AL_DOPPLER_VELOCITY
	 *	 DF: AL_DOPPLER_FACTOR
	 *	 vl: Listener velocity (scalar, projected on source-listener vector)
	 *	 vs: Source verlocity (scalar, projected on source-listener vector)
	 *	 f: Frequency in sample
	 *	 f': effective Doppler shifted frequency
	 *	 f' = DF * f * (VD-vl)/(VD+vs)
	 *	 vl<0, vs>0 : source and listener approaching each other
	 *	 vl>0, vs<0 : source and listener moving away from each other
	 * </pre>
	 * </p>
	 * <p>
	 * The implementation has to clamp the projected Listener velocity vl, if abs(vl) is
	 * greater or equal VD. It similarly has to clamp the projected Source velocity vs if
	 * abs(vs) is greater or equal VD.
	 * </p>
	 * <p>
	 * There are two API calls global to the current context that provide control of the two
	 * related parameters.
	 * </p>
	 * <p>
	 * AL_DOPPLER_FACTOR is a simple scaling to exaggerate or
	 * deemphasize the Doppler (pitch) shift resulting from the calculation.
	 * </p>
	 * <p>
	 * A negative value will result in an AL_INVALID_VALUE error, the command is then
	 * ignored. The default value is 1. The current setting can be queried using GetFloatv
	 * and AL_DOPPLER_FACTOR. The implementation is free to optimize the case of
	 * AL_DOPPLER_FACTOR being set to zero, as this effectively disables the effect.
	 * </p>
	 * @param value Doppler scale value to set
	 */
	public static void alDopplerFactor(float value) {
		nalDopplerFactor(value);
		Util.checkALError();
	}
	private static native void nalDopplerFactor(float value);

	/**
	 * The Doppler Effect depends on the velocities of Source and Listener relative to the
	 * medium, and the propagation speed of sound in that medium. The application
	 * might want to emphasize or de-emphasize the Doppler Effect as physically accurate
	 * calculation might not give the desired results. The amount of frequency shift (pitch
	 * change) is proportional to the speed of listener and source along their line of sight.
	 * The application can increase or decrease that frequency shift by specifying the
	 * scaling factor AL should apply to the result of the calculation.
	 * <br>
	 * <br>
	 * The Doppler Effect as implemented by AL is described by the formula below. Effects
	 * of the medium (air, water) moving with respect to listener and source are ignored.
	 * AL_DOPPLER_VELOCITY is the propagation speed relative to which the Source
	 * velocities are interpreted.
	 * <p>
	 * <pre>
	 *	 VD: AL_DOPPLER_VELOCITY
	 *	 DF: AL_DOPPLER_FACTOR
	 *	 vl: Listener velocity (scalar, projected on source-listener vector)
	 *	 vs: Source verlocity (scalar, projected on source-listener vector)
	 *	 f: Frequency in sample
	 *	 f': effective Doppler shifted frequency
	 *	 f' = DF * f * (VD-vl)/(VD+vs)
	 *	 vl<0, vs>0 : source and listener approaching each other
	 *	 vl>0, vs<0 : source and listener moving away from each other
	 * </pre>
	 * </p>
	 * <p>
	 * The implementation has to clamp the projected Listener velocity vl, if abs(vl) is
	 * greater or equal VD. It similarly has to clamp the projected Source velocity vs if
	 * abs(vs) is greater or equal VD.
	 * </p>
	 * <p>
	 * There are two API calls global to the current context that provide control of the two
	 * related parameters.
	 * </p>
	 * <p>
	 * AL_DOPPLER_VELOCITY allows the application to change the reference (propagation)
	 * velocity used in the Doppler Effect calculation. This permits the application to use a
	 * velocity scale appropriate to its purposes.
	 * </p>
	 * <p>
	 * A negative or zero value will result in an AL_INVALID_VALUE error, the command is
	 * then ignored. The default value is 1. The current setting can be queried using
	 * GetFloatv and AL_DOPPLER_VELOCITY.
	 * </p>
	 * @param value Doppler velocity value to set
	 */
	public static void alDopplerVelocity(float value) {
		nalDopplerVelocity(value);
		Util.checkALError();
	}
	private static native void nalDopplerVelocity(float value);
}
