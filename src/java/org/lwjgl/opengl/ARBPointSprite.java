/**
 * Created by zDimensions.
 * User: spasi
 * Date: 29 …бн 2004
 * Time: 1:54:00 рм
 */

package org.lwjgl.opengl;

public interface ARBPointSprite {

	/*
	 * Accepted by the <cap> parameter of Enable, Disable, and IsEnabled, by
	 * the <pname> parameter of GetBooleanv, GetIntegerv, GetFloatv, and
	 * GetDoublev, and by the <target> parameter of TexEnvi, TexEnviv,
	 * TexEnvf, TexEnvfv, GetTexEnviv, and GetTexEnvfv:
	*/
	public static final int POINT_SPRITE_ARB = 0x8861;

	/*
	* When the <target> parameter of TexEnvf, TexEnvfv, TexEnvi, TexEnviv,
	* GetTexEnvfv, or GetTexEnviv is POINT_SPRITE_ARB, then the value of
	* <pname> may be:
	*/

	public static final int COORD_REPLACE_ARB = 0x8862;
	
}