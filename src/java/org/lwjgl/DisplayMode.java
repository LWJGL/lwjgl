/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * DisplayMode.java Created on Aug 1, 2002 by foo
 */
package org.lwjgl;

/**
 * (C) 2002 Shaven Puppy Ltd
 * 
 * DisplayMode.java Created on Aug 1, 2002 by foo
 */
/**
 * Describes a display mode.
 * 
 * @author foo
 */
public final class DisplayMode {
	
	public final int width, height, freq, bpp;

	/**
	 * Construct a display mode.
	 * 
	 * @see Display
	 */
	public DisplayMode(int width, int height, int freq, int bpp) {
		this.width = width;
		this.height = height;
		this.freq = freq;
		this.bpp = bpp;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof DisplayMode))
			return false;
			
		DisplayMode dm = (DisplayMode) obj;
		return dm.width == width
			&& dm.height == dm.height
			&& dm.bpp == bpp
			&& dm.freq == freq;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return width ^ height ^ freq ^ bpp;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(width);
		sb.append(" x ");
		sb.append(height);
		sb.append(" x ");
		sb.append(bpp);
		sb.append(" @");
		sb.append(freq);
		sb.append("Hz");
		return sb.toString();
	}

}
