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
public class CoreEAX extends BaseEAX implements BaseEAXConstants {  
    
    /** GUID for buffer */
    public static int BUFFER_GUID;
    
    /** GUID for listener */
    public static int LISTENER_GUID;    
    
    /** Creates a new instance of CoreEAX */
    public CoreEAX() {
    }
    
    /**
     * Load extensions
     */
    protected void init() {        
		determineAvailableExtensions();
        setGUID();
    }
    
    /**
     * Determines available EAX extensions
     */
    protected native void determineAvailableExtensions();
    
    /**
     * Sets the GUID's for the buffer and listener objects
     */
    protected native void setGUID();
    
    /**
     * Retrieves an EAX Value
     *
     * @param propertySetID adress to the property set GUID of the object being queried (a listener or a source)
     * @param property property being queried
     * @param source the source to be queried
     * @param data address to write value to
     * @param size size of area being written to
     * @return OpenAL Error code
     */
    public native int eaxGet(int propertySetID, int property, int source, int data, int size);
    
    /**
     * Sets an EAX Value
     *
     * @param propertySetID adress to the property set GUID of the object being queried (a listener or a source)
     * @param property property being queried
     * @param source the source to be queried
     * @param data address to write value to
     * @param size size of area being written to
     * @return OpenAL Error code
     */
    public native int eaxSet(int propertySetID, int property, int source, int data, int size);
}