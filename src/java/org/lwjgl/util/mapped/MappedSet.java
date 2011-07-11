/*
 * Created on Jul 11, 2011
 */

package org.lwjgl.util.mapped;

public class MappedSet
{
   public static MappedSet2 create(MappedObject a, MappedObject b)
   {
      return new MappedSet2(a, b);
   }

   public static MappedSet3 create(MappedObject a, MappedObject b, MappedObject c)
   {
      return new MappedSet3(a, b, c);
   }

   public static MappedSet4 create(MappedObject a, MappedObject b, MappedObject c, MappedObject d)
   {
      return new MappedSet4(a, b, c, d);
   }
}
