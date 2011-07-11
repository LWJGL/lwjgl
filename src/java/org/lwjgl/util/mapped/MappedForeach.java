/*
 * Created on Jul 4, 2011
 */

package org.lwjgl.util.mapped;

import java.util.Iterator;

/**
 * @author Riven
 */

public class MappedForeach<T extends MappedObject> implements Iterable<T>
{
   final T   mapped;
   final int elementCount;

   MappedForeach(T mapped, int elementCount)
   {
      this.mapped = mapped;
      this.elementCount = elementCount;
   }

   @Override
   public Iterator<T> iterator()
   {
      return new Iterator<T>()
      {
         private int index = 0;

         @Override
         public boolean hasNext()
         {
            return this.index < (MappedForeach.this.elementCount);
         }

         @Override
         public T next()
         {
            mapped.viewAddress = mapped.baseAddress + (this.index++) * mapped.stride;

            return mapped;
         }

         @Override
         public void remove()
         {
            throw new UnsupportedOperationException();
         }
      };
   }
}
