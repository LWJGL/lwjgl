/*
 * Created on Jul 11, 2011
 */

package org.lwjgl.util.mapped;

public class MappedSet3
{
   private final MappedObject a, b, c;

   MappedSet3(MappedObject a, MappedObject b, MappedObject c)
   {
      this.a = a;
      this.b = b;
      this.c = c;
   }

   public int view;

   void view(int view)
   {
      this.a.viewAddress = this.a.baseAddress + this.a.stride * view;
      this.b.viewAddress = this.b.baseAddress + this.b.stride * view;
      this.c.viewAddress = this.c.baseAddress + this.c.stride * view;
   }

   public void next()
   {
      this.a.viewAddress += this.a.stride;
      this.b.viewAddress += this.b.stride;
      this.c.viewAddress += this.c.stride;
   }
}
