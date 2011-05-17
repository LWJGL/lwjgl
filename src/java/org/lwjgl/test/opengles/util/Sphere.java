package org.lwjgl.test.opengles.util;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengles.GLES20.*;

/** VBO implementation of GLU Sphere. */
public final class Sphere {

	/* QuadricNormal */
	public static final int GLU_SMOOTH = 100000;
	public static final int GLU_FLAT   = 100001;
	public static final int GLU_NONE   = 100002;

	/* QuadricDrawStyle */
	public static final int GLU_POINT      = 100010;
	public static final int GLU_LINE       = 100011;
	public static final int GLU_FILL       = 100012;
	public static final int GLU_SILHOUETTE = 100013;

	/* QuadricOrientation */
	public static final int GLU_OUTSIDE = 100020;
	public static final int GLU_INSIDE  = 100021;

	static final float PI = (float)Math.PI;

	private int     drawStyle;
	private int     orientation;
	private boolean textureFlag;
	private int     normals;

	private BufferObjectArray bo;

	private final List<DrawCommand> drawCommands = new ArrayList<DrawCommand>(4);

	public Sphere() {
		this(GLU_FILL, GLU_OUTSIDE, false, GLU_SMOOTH);
	}

	public Sphere(final int drawStyle, final int orientation, final boolean textureFlag, final int normals) {
		setDrawStyle(drawStyle);
		setOrientation(orientation);
		setTextureFlag(textureFlag);
		setNormals(normals);
	}

	public Sphere(final float radius, final int slices, final int stacks) {
		this();
		updateGeometry(radius, slices, stacks);
	}

	public Sphere(final float radius, final int slices, final int stacks,
	              final int drawStyle, final int orientation, final boolean textureFlag, final int normals) {
		this(drawStyle, orientation, textureFlag, normals);
		updateGeometry(radius, slices, stacks);
	}

	public void updateGeometry(final float radius, final int slices, final int stacks) {
		if ( bo != null )
			destroy();

		bo = new BufferObjectArray(GL_STATIC_DRAW, createBuffer(radius, slices, stacks));
	}

	public void bind() {
		bo.enable();
	}

	public void draw() {
		for ( DrawCommand command : drawCommands )
			command.draw();
	}

	public void destroy() {
		bo.destroy();
		bo = null;

		drawCommands.clear();
	}

	/**
	 * specifies the draw style for quadrics.
	 * <p/>
	 * The legal values are as follows:
	 * <p/>
	 * GLU.FILL:       Quadrics are rendered with polygon primitives. The polygons
	 * are drawn in a counterclockwise fashion with respect to
	 * their normals (as defined with glu.quadricOrientation).
	 * <p/>
	 * GLU.LINE:       Quadrics are rendered as a set of lines.
	 * <p/>
	 * GLU.SILHOUETTE: Quadrics are rendered as a set of lines, except that edges
	 * separating coplanar faces will not be drawn.
	 * <p/>
	 * GLU.POINT:       Quadrics are rendered as a set of points.
	 *
	 * @param drawStyle The drawStyle to set
	 */
	public void setDrawStyle(int drawStyle) {
		switch ( drawStyle ) {
			case GLU_FILL:
			case GLU_LINE:
			case GLU_SILHOUETTE:
			case GLU_POINT:
				break;
			default:
				throw new IllegalArgumentException("Invalid draw style specified: " + drawStyle);
		}

		this.drawStyle = drawStyle;
	}

	/**
	 * specifies what kind	of normals are desired for quadrics.
	 * The legal values	are as follows:
	 * <p/>
	 * GLU.NONE:     No normals are generated.
	 * <p/>
	 * GLU.FLAT:     One normal is generated for every facet of a quadric.
	 * <p/>
	 * GLU.SMOOTH:   One normal is generated for every vertex of a quadric.  This
	 * is the default.
	 *
	 * @param normals The normals to set
	 */
	public void setNormals(int normals) {
		switch ( normals ) {
			case GLU_NONE:
			case GLU_FLAT:
			case GLU_SMOOTH:
				break;
			default:
				throw new IllegalArgumentException("Invalid normal kind specified: " + normals);
		}

		this.normals = normals;
	}

	/**
	 * specifies what kind of orientation is desired for.
	 * The orientation	values are as follows:
	 * <p/>
	 * GLU.OUTSIDE:  Quadrics are drawn with normals pointing outward.
	 * <p/>
	 * GLU.INSIDE:   Normals point inward. The default is GLU.OUTSIDE.
	 * <p/>
	 * Note that the interpretation of outward and inward depends on the quadric
	 * being drawn.
	 *
	 * @param orientation The orientation to set
	 */
	public void setOrientation(int orientation) {
		if ( orientation != GLU_OUTSIDE && orientation != GLU_INSIDE )
			throw new IllegalArgumentException("Invalid orientation specified: " + orientation);

		this.orientation = orientation;
	}

	/**
	 * specifies if texture coordinates should be generated for
	 * quadrics rendered with qobj. If the value of textureCoords is true,
	 * then texture coordinates are generated, and if textureCoords is false,
	 * they are not.. The default is false.
	 * <p/>
	 * The manner in which texture coordinates are generated depends upon the
	 * specific quadric rendered.
	 *
	 * @param textureFlag The textureFlag to set
	 */
	public void setTextureFlag(boolean textureFlag) {
		this.textureFlag = textureFlag;
	}

	/**
	 * Returns the drawStyle.
	 *
	 * @return int
	 */
	public int getDrawStyle() {
		return drawStyle;
	}

	/**
	 * Returns the normals.
	 *
	 * @return int
	 */
	public int getNormals() {
		return normals;
	}

	/**
	 * Returns the orientation.
	 *
	 * @return int
	 */
	public int getOrientation() {
		return orientation;
	}

	/**
	 * Returns the textureFlag.
	 *
	 * @return boolean
	 */
	public boolean getTextureFlag() {
		return textureFlag;
	}

	private static float sin(final float r) {
		return (float)Math.sin(r);
	}

	private static float cos(final float r) {
		return (float)Math.cos(r);
	}

	private int addDrawCommand(final int mode, final int first, final int count) {
		drawCommands.add(new DrawCommand(mode, first, count));
		return count;
	}

	/**
	 * draws a sphere of the given	radius centered	around the origin.
	 * The sphere is subdivided around the z axis into slices and along the z axis
	 * into stacks (similar to lines of longitude and latitude).
	 * <p/>
	 * If the orientation is set to GLU.OUTSIDE (with glu.quadricOrientation), then
	 * any normals generated point away from the center of the sphere. Otherwise,
	 * they point toward the center of the sphere.
	 * <p/>
	 * If texturing is turned on (with glu.quadricTexture), then texture
	 * coordinates are generated so that t ranges from 0.0 at z=-radius to 1.0 at
	 * z=radius (t increases linearly along longitudinal lines), and s ranges from
	 * 0.0 at the +y axis, to 0.25 at the +x axis, to 0.5 at the -y axis, to 0.75
	 * at the -x axis, and back to 1.0 at the +y axis.
	 */
	public FloatBuffer createBuffer(float radius, int slices, int stacks) {
		float rho, theta;
		float x, y, z;
		float s, t, ds, dt;
		int i, j;

		final boolean normals = this.normals != GLU_NONE;
		final float nsign = this.orientation == GLU_INSIDE ? -1.0f : 1.0f;

		final float drho = PI / stacks;
		final float dtheta = 2.0f * PI / slices;

		final ImmediateModeBuffer imb = new ImmediateModeBuffer(16 * 1024); // TODO: We can calculate this to avoid re-allocs
		int lastDrawIndex = 0;

		if ( this.drawStyle == GLU_FILL ) {
			if ( !this.textureFlag ) {
				lastDrawIndex += addDrawCommand(GL_TRIANGLE_FAN, lastDrawIndex, slices + 2);

				// draw +Z end as a triangle fan
				imb.glNormal3f(0.0f, 0.0f, 1.0f);
				imb.glVertex3f(0.0f, 0.0f, nsign * radius);
				for ( j = 0; j <= slices; j++ ) {
					theta = (j == slices) ? 0.0f : j * dtheta;
					x = -sin(theta) * sin(drho);
					y = cos(theta) * sin(drho);
					z = nsign * cos(drho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					imb.glVertex3f(x * radius, y * radius, z * radius);
				}
			}

			ds = 1.0f / slices;
			dt = 1.0f / stacks;
			t = 1.0f; // because loop now runs from 0

			final int imin, imax;
			if ( this.textureFlag ) {
				imin = 0;
				imax = stacks;
			} else {
				imin = 1;
				imax = stacks - 1;
			}

			// draw intermediate stacks as quad strips
			for ( i = imin; i < imax; i++ ) {
				lastDrawIndex += addDrawCommand(GL_TRIANGLE_STRIP, lastDrawIndex, (slices + 1) * 2);

				rho = i * drho;
				s = 0.0f;
				for ( j = 0; j <= slices; j++ ) {
					theta = (j == slices) ? 0.0f : j * dtheta;

					x = -sin(theta) * sin(rho);
					y = cos(theta) * sin(rho);
					z = nsign * cos(rho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					if ( textureFlag )
						imb.glTexCoord2f(s, t);
					imb.glVertex3f(x * radius, y * radius, z * radius);

					x = -sin(theta) * sin(rho + drho);
					y = cos(theta) * sin(rho + drho);
					z = nsign * cos(rho + drho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					if ( textureFlag )
						imb.glTexCoord2f(s, t - dt);
					s += ds;
					imb.glVertex3f(x * radius, y * radius, z * radius);
				}
				t -= dt;
			}

			if ( !this.textureFlag ) {
				lastDrawIndex += addDrawCommand(GL_TRIANGLE_FAN, lastDrawIndex, slices + 2);

				// draw -Z end as a triangle fan
				imb.glNormal3f(0.0f, 0.0f, -1.0f);
				imb.glVertex3f(0.0f, 0.0f, -radius * nsign);
				rho = PI - drho;
				s = 1.0f;
				for ( j = slices; j >= 0; j-- ) {
					theta = (j == slices) ? 0.0f : j * dtheta;
					x = -sin(theta) * sin(rho);
					y = cos(theta) * sin(rho);
					z = nsign * cos(rho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					s -= ds;
					imb.glVertex3f(x * radius, y * radius, z * radius);
				}
			}
		} else if ( this.drawStyle == GLU_LINE || this.drawStyle == GLU_SILHOUETTE ) {
			// draw stack lines
			for ( i = 1; i < stacks; i++ ) { // stack line at i==stacks-1 was missing here
				lastDrawIndex += addDrawCommand(GL_LINE_LOOP, lastDrawIndex, slices);

				rho = i * drho;
				for ( j = 0; j < slices; j++ ) {
					theta = j * dtheta;
					x = cos(theta) * sin(rho);
					y = sin(theta) * sin(rho);
					z = cos(rho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					imb.glVertex3f(x * radius, y * radius, z * radius);
				}
			}
			// draw slice lines
			for ( j = 0; j < slices; j++ ) {
				lastDrawIndex += addDrawCommand(GL_LINE_STRIP, lastDrawIndex, stacks + 1);

				theta = j * dtheta;
				for ( i = 0; i <= stacks; i++ ) {
					rho = i * drho;
					x = cos(theta) * sin(rho);
					y = sin(theta) * sin(rho);
					z = cos(rho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					imb.glVertex3f(x * radius, y * radius, z * radius);
				}
			}
		} else if ( this.drawStyle == GLU_POINT ) {
			lastDrawIndex += addDrawCommand(GL_POINTS, lastDrawIndex, 2 + (stacks - 2) * slices);

			// top and bottom-most points
			if ( normals )
				imb.glNormal3f(0.0f, 0.0f, nsign);
			imb.glVertex3f(0.0f, 0.0f, radius);
			if ( normals )
				imb.glNormal3f(0.0f, 0.0f, -nsign);
			imb.glVertex3f(0.0f, 0.0f, -radius);

			// loop over stacks
			for ( i = 1; i < stacks - 1; i++ ) {
				rho = i * drho;
				for ( j = 0; j < slices; j++ ) {
					theta = j * dtheta;
					x = cos(theta) * sin(rho);
					y = sin(theta) * sin(rho);
					z = cos(rho);
					if ( normals )
						imb.glNormal3f(x * nsign, y * nsign, z * nsign);
					imb.glVertex3f(x * radius, y * radius, z * radius);
				}
			}
		}

		return imb.getBuffer();
	}

	private static class DrawCommand {

		private int mode;
		private int first;
		private int count;

		private DrawCommand(final int mode, final int first, final int count) {
			this.mode = mode;
			this.first = first;
			this.count = count;
		}

		void draw() {
			glDrawArrays(mode, first, count);
		}

	}

}