/* 
 * Copyright (c) 2002 Lightweight Java Game Library Project
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
 package org.lwjgl.openal.eax;

/**
 * $Id$
 *
 * This is the OpenAL EAX class. It extends the latest core.
 *
 * @author Brian Matzon <brian@matzon.dk>
 * @version $Revision$
 */
public class EAX extends CoreEAX {
  
  /**
   * Sets an EAX Value
   *
   * @param listener EAXListenerProperties to set values from
   * @param property property being queried
   * @param source the source to be queried
   */
  public static void eaxSetProperty(EAXListenerProperties listener, int property, int source) {
    System.out.println("EAX has been disapled for this release, due to \"issues\" with the implementation");
    //CoreEAX.eaxSet(CoreEAX.LISTENER_GUID, property, source, listener.eaxListenerProperties, EAXListenerProperties.EAXLISTENERPROPERTIES_SIZE);
  }
  
  /**
   * Sets an EAX Value
   *
   * @param buffer EAXBufferProperties to set values from
   * @param property property being queried
   * @param source the source to be queried
   */
  public static void eaxSetProperty(EAXBufferProperties buffer, int property, int source) {
    System.out.println("EAX has been disapled for this release, due to \"issues\" with the implementation");
    //CoreEAX.eaxSet(CoreEAX.BUFFER_GUID, property, source, buffer.eaxBufferProperties, EAX.getSizeOfProperty(CoreEAX.BUFFER_GUID, property));
  }
  
  /**
   * Gets an EAX Value
   *
   * @param listener EAXListenerProperties to set values on
   * @param property property being queried
   * @param source the source to be queried
   */
  public static void eaxGetProperty(EAXListenerProperties listener, int property, int source) {
    System.out.println("EAX has been disapled for this release, due to \"issues\" with the implementation");
    //CoreEAX.eaxGet(CoreEAX.LISTENER_GUID, property, source, listener.eaxListenerProperties, EAX.getSizeOfProperty(CoreEAX.LISTENER_GUID, property));
  }
  
  /**
   * Sets an EAX Value
   *
   * @param buffer EAXBufferProperties to set values on
   * @param property property being queried
   * @param source the source to be queried
   */
  public static void eaxGetProperty(EAXBufferProperties buffer, int property, int source) {
    System.out.println("EAX has been disapled for this release, due to \"issues\" with the implementation");
    //CoreEAX.eaxGet(CoreEAX.BUFFER_GUID, property, source, buffer.eaxBufferProperties, EAX.getSizeOfProperty(CoreEAX.BUFFER_GUID, property));
  }  
  
  /**
   * Retrieves the size of the property
   * 
   * @param guid Listener or Source guid
   * @param property Property to determine size of
   * @return size of property
   */
  private static int getSizeOfProperty(int guid, int property) {
    if (guid == CoreEAX.LISTENER_GUID) {
      switch(property) {
          case EAXListenerProperties.EAXLISTENER_NONE:
            return 0;
      
          /* long */
          case EAXListenerProperties.EAXLISTENER_ROOM:
          case EAXListenerProperties.EAXLISTENER_ROOMHF:
          case EAXListenerProperties.EAXLISTENER_REFLECTIONS:
          case EAXListenerProperties.EAXLISTENER_REVERB:
          
          /* float */
          case EAXListenerProperties.EAXLISTENER_ROOMROLLOFFFACTOR:
          case EAXListenerProperties.EAXLISTENER_DECAYTIME:
          case EAXListenerProperties.EAXLISTENER_DECAYHFRATIO:
          case EAXListenerProperties.EAXLISTENER_REFLECTIONSDELAY:
          case EAXListenerProperties.EAXLISTENER_REVERBDELAY:
          case EAXListenerProperties.EAXLISTENER_ENVIRONMENTSIZE:
          case EAXListenerProperties.EAXLISTENER_ENVIRONMENTDIFFUSION:
          case EAXListenerProperties.EAXLISTENER_AIRABSORPTIONHF:

          /* unsigned long */
          case EAXListenerProperties.EAXLISTENER_ENVIRONMENT:
          case EAXListenerProperties.EAXLISTENER_FLAGS:
            return 4;
            
          case EAXListenerProperties.EAXLISTENER_ALLPARAMETERS:
            return EAXListenerProperties.EAXLISTENERPROPERTIES_SIZE;

          default:
            throw new IllegalArgumentException("No such property '" + property + "'");

      }
    } else {      
      switch(property) {
          case EAXBufferProperties.EAXBUFFER_NONE:
            return 0;
      
          /* long */
          case EAXBufferProperties.EAXBUFFER_DIRECT:
          case EAXBufferProperties.EAXBUFFER_DIRECTHF:
          case EAXBufferProperties.EAXBUFFER_ROOM:
          case EAXBufferProperties.EAXBUFFER_ROOMHF:
          case EAXBufferProperties.EAXBUFFER_OBSTRUCTION:
          case EAXBufferProperties.EAXBUFFER_OCCLUSION:
          case EAXBufferProperties.EAXBUFFER_OUTSIDEVOLUMEHF:
          
          /* float */
          case EAXBufferProperties.EAXBUFFER_ROOMROLLOFFFACTOR:
          case EAXBufferProperties.EAXBUFFER_OBSTRUCTIONLFRATIO:
          case EAXBufferProperties.EAXBUFFER_OCCLUSIONLFRATIO:
          case EAXBufferProperties.EAXBUFFER_OCCLUSIONROOMRATIO:
          case EAXBufferProperties.EAXBUFFER_AIRABSORPTIONFACTOR:

          /* unsigned long */
          case EAXBufferProperties.EAXBUFFER_FLAGS:
            return 4;
            
          case EAXBufferProperties.EAXBUFFER_ALLPARAMETERS:
            return EAXBufferProperties.EAXBUFFERPROPERTIES_SIZE;
            
          default:
            throw new IllegalArgumentException("No such property '" + property + "'");

      }
    }
  }
}