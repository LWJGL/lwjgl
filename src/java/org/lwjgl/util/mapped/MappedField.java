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
public @interface MappedField {
   long byteOffset();

   long byteLength() default -1;
}
