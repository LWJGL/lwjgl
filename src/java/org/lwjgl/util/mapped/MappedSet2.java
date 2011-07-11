/*
 * Created on Jul 11, 2011
 */

package org.lwjgl.util.mapped;

public class MappedSet2
{
   private final MappedObject a, b;

   MappedSet2(MappedObject a, MappedObject b)
   {
      this.a = a;
      this.b = b;
   }

   public int view;

   void view(int view)
   {
      this.a.viewAddress = this.a.baseAddress + this.a.stride * view;
      this.b.viewAddress = this.b.baseAddress + this.b.stride * view;
   }

   public void next()
   {
      this.a.viewAddress += this.a.stride;
      this.b.viewAddress += this.b.stride;
   }
}
