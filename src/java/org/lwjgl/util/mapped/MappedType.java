/*
 * Created on Jun 24, 2011
 */

package org.lwjgl.util.mapped;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Riven
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface MappedType {
   int sizeof();

   int align() default 4;

   boolean autoGenerateOffsets() default true;
}
