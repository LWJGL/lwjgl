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
package org.lwjgl.openal;

/**
 * $Id$
 *
 * This class implements all basic OpenAL constants.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public interface BaseALConstants {

	/** Bad value */
	public static final int INVALID = -1;

	/** Disable value */
	public static final int NONE = 0;

	/** Boolean False */
	public static final int FALSE = 0;

	/** Boolean True */
	public static final int TRUE = 1;

	/**
	 * Indicate the type of SOURCE.
	 * Sources can be spatialized
	 */
	public static final int SOURCE_TYPE = 0x200;

	/** Indicate source has absolute coordinates */
	public static final int SOURCE_ABSOLUTE = 0x201;

	/** Indicate Source has listener relative coordinates */
	public static final int SOURCE_RELATIVE = 0x202;

	/**
	 * Directional source, inner cone angle, in degrees
	 * Range:    [0-360]
	 * Default:  360
	 */
	public static final int CONE_INNER_ANGLE = 0x1001;

	/**
	 * Directional source, outer cone angle, in degrees.
	 * Range:    [0-360]
	 * Default:  360
	 */
	public static final int CONE_OUTER_ANGLE = 0x1002;

	/**
	 * Specify the pitch to be applied, either at source,
	 *  or on mixer results, at listener.
	 * Range:	 [0.5-2.0]
	 * Default:  1.0
	 */
	public static final int PITCH = 0x1003;

	/**
	 * Specify the current location in three dimensional space.
	 * OpenAL, like OpenGL, uses a right handed coordinate system,
	 *  where in a frontal default view X (thumb) points right,
	 *  Y points up (index finger), and Z points towards the
	 *  viewer/camera (middle finger).
	 * To switch from a left handed coordinate system, flip the
	 *  sign on the Z coordinate.
	 * Listener position is always in the world coordinate system.
	 */
	public static final int POSITION = 0x1004;

	/** Specify the current direction as forward vector. */
	public static final int DIRECTION = 0x1005;

	/** Specify the current velocity in three dimensional space. */
	public static final int VELOCITY = 0x1006;

	/**
	 * Indicate whether source has to loop infinite.
	 * Type: ALboolean
	 * Range:    [TRUE, FALSE]
	 * Default:  FALSE
	 */
	public static final int LOOPING = 0x1007;

	/**
	 * Indicate the buffer to provide sound samples.
	 * Type: ALuint.
	 * Range: any valid Buffer id.
	 */
	public static final int BUFFER = 0x1009;

	/**
	 * Indicate the gain (volume amplification) applied.
	 * Type:     ALfloat.
	 * Range:    ]0.0-  ]
	 * A value of 1.0 means un-attenuated/unchanged.
	 * Each division by 2 equals an attenuation of -6dB.
	 * Each multiplicaton with 2 equals an amplification of +6dB.
	 * A value of 0.0 is meaningless with respect to a logarithmic
	 *  scale; it is interpreted as zero volume - the channel
	 *  is effectively disabled.
	 */
	public static final int GAIN = 0x100A;

	/**
	 * Indicate minimum source attenuation.
	 * Type:     ALfloat
	 * Range:	 [0.0 - 1.0]
	 */
	public static final int MIN_GAIN = 0x100D;

	/**
	 * Indicate maximum source attenuation.
	 * Type:	 ALfloat
	 * Range:	 [0.0 - 1.0]
	 */
	public static final int MAX_GAIN = 0x100E;

	/**
	 * Specify the current orientation.
	 * Type:	 ALfv6 (at/up)
	 * Range:	 N/A
	 */
	public static final int ORIENTATION = 0x100F;

	/* byte offset into source (in canon format).  -1 if source
	 * is not playing.  Don't set this, get this.
	 *
	 * Type:     ALfloat
	 * Range:    [0.0 - ]
	 * Default:  1.0
	 */
	public static final int REFERENCE_DISTANCE = 0x1020;

	/**
	 * Indicate the rolloff factor for the source.
	 * Type: ALfloat
	 * Range:    [0.0 - ]
	 * Default:  1.0
	 */
	public static final int ROLLOFF_FACTOR = 0x1021;

	/**
	 * Indicate the gain (volume amplification) applied.
	 * Type:     ALfloat.
	 * Range:    ]0.0-  ]
	 * A value of 1.0 means un-attenuated/unchanged.
	 * Each division by 2 equals an attenuation of -6dB.
	 * Each multiplicaton with 2 equals an amplification of +6dB.
	 * A value of 0.0 is meaningless with respect to a logarithmic
	 *  scale; it is interpreted as zero volume - the channel
	 *  is effectively disabled.
	 */
	public static final int CONE_OUTER_GAIN = 0x1022;

	/**
	 * Specify the maximum distance.
	 * Type:	 ALfloat
	 * Range:	 [0.0 - ]
	 */
	public static final int MAX_DISTANCE = 0x1023;

	/**
	 * Specify the channel mask. (Creative)
	 * Type:	 ALuint
	 * Range:	 [0 - 255]
	 */
	public static final int CHANNEL_MASK = 0x3000;

	/** Source state information */
	public static final int SOURCE_STATE = 0x1010;

	/** Source state information */
	public static final int INITIAL = 0x1011;

	/** Source state information */
	public static final int PLAYING = 0x1012;

	/** Source state information */
	public static final int PAUSED = 0x1013;

	/** Source state information */
	public static final int STOPPED = 0x1014;

	/** Buffer Queue params */
	public static final int BUFFERS_QUEUED = 0x1015;

	/** Buffer Queue params */
	public static final int BUFFERS_PROCESSED = 0x1016;

	/** Sound buffers: format specifier. */
	public static final int FORMAT_MONO8 = 0x1100;

	/** Sound buffers: format specifier. */
	public static final int FORMAT_MONO16 = 0x1101;

	/** Sound buffers: format specifier. */
	public static final int FORMAT_STEREO8 = 0x1102;

	/** Sound buffers: format specifier. */
	public static final int FORMAT_STEREO16 = 0x1103;

	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 *  sample frequency marks the maximum significant
	 *  frequency component.
	 */
	public static final int FREQUENCY = 0x2001;

	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 *  sample frequency marks the maximum significant
	 *  frequency component.
	 */
	public static final int BITS = 0x2002;

	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 *  sample frequency marks the maximum significant
	 *  frequency component.
	 */
	public static final int CHANNELS = 0x2003;

	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 *  sample frequency marks the maximum significant
	 *  frequency component.
	 */
	public static final int SIZE = 0x2004;

	/**
	 * Sound buffers: frequency, in units of Hertz [Hz].
	 * This is the number of samples per second. Half of the
	 *  sample frequency marks the maximum significant
	 *  frequency component.
	 */
	public static final int DATA = 0x2005;

	/**
	 * Buffer state.
	 *
	 * Not supported for public use (yet).
	 */
	public static final int UNUSED = 0x2010;

	/**
	 * Buffer state.
	 *
	 * Not supported for public use (yet).
	 */
	public static final int PENDING = 0x2011;

	/**
	 * Buffer state.
	 *
	 * Not supported for public use (yet).
	 */
	public static final int PROCESSED = 0x2012;

	/** Errors: No Error. */
	public static final int NO_ERROR = FALSE;

	/**
	 * Illegal name passed as an argument to an AL call.
	 */
	public static final int INVALID_NAME = 0xA001;

	/**
	 * Illegal enum passed as an argument to an AL call.
	 */
	public static final int INVALID_ENUM = 0xA002;

	/**
	 * Illegal value passed as an argument to an AL call.
	 * Applies to parameter values, but not to enumerations.
	 */
	public static final int INVALID_VALUE = 0xA003;

	/**
	 * A function was called at inappropriate time,
	 *  or in an inappropriate way, causing an illegal state.
	 * This can be an incompatible ALenum, object ID,
	 *  and/or function.
	 */
	public static final int INVALID_OPERATION = 0xA004;

	/**
	 * A function could not be completed,
	 * because there is not enough memory available.
	 */
	public static final int OUT_OF_MEMORY = 0xA005;

	/** Context strings: Vendor */
	public static final int VENDOR = 0xB001;

	/** Context strings: Version */
	public static final int VERSION = 0xB002;

	/** Context strings: Renderer */
	public static final int RENDERER = 0xB003;

	/** Context strings: Extensions */
	public static final int EXTENSIONS = 0xB004;

	/**
	 * Doppler scale.  Default 1.0
	 */
	public static final int DOPPLER_FACTOR = 0xC000;

	/**
	 * Doppler velocity.  Default 1.0
	 */
	public static final int DOPPLER_VELOCITY = 0xC001;

	/**
	 * Distance model.  Default INVERSE_DISTANCE_CLAMPED
	 */
	public static final int DISTANCE_MODEL = 0xD000;

	/** Distance model */
	public static final int INVERSE_DISTANCE = 0xD001;

	/** Distance model */
	public static final int INVERSE_DISTANCE_CLAMPED = 0xD002;
}