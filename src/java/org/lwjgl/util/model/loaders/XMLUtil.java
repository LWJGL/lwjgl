/* 
 * Copyright (c) 2003 Shaven Puppy Ltd
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
 * * Neither the name of 'Shaven Puppy' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
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
package org.lwjgl.util.model.loaders;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Some simple XML utilities
 * @author cas
 */
final class XMLUtil {
	
	/**
	 * Get a single child element
	 * @param child
	 * @return the single child element, or null
	 * @throws Exception if the child is present multiple times
	 */
	static Element getChild(Element element, String child) throws Exception {
		NodeList nodes = element.getChildNodes();
		Element ret = null;
		for (int i = 0; i < nodes.getLength(); i ++) {
			Node childNode = (Node) nodes.item(i);
			if (childNode.getNodeName().equals(child) && childNode.getNodeType() == Node.ELEMENT_NODE) {
				if (ret != null) {
					throw new Exception("Child element '"+child+"' present multiple times");
				} else {
					ret = (Element) childNode;
				}
			}
		}
		return ret;
	}

	/**
	 * @param name The name of the child elements you want
	 * @return a List of child Elements
	 */
	static List getChildren(Element element, String name) throws Exception {
		NodeList nodes = element.getChildNodes();
		ArrayList ret = new ArrayList(nodes.getLength()); 
		for (int i = 0; i < nodes.getLength(); i ++) {
			Node childNode = (Node) nodes.item(i);
			if (childNode.getNodeName().equals(name) && childNode.getNodeType() == Node.ELEMENT_NODE) {
				ret.add(childNode);
			}
		}
		return ret;
	}
	
	/**
	 * A convenience method for getting float values out of XML elements
	 * @param attribute The name of the attribute
	 * @throws NumberFormatException If the supplied attribute is not a number
	 * @throws Exception if the value is missing
	 * @return the parsed float value
	 */
	static float getFloat(Element element, String attribute) throws Exception {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			throw new Exception("Attribute '"+attribute+"' has not been specified for "+element.getNodeName());
		else
			return Float.parseFloat(s);
	}


	/**
	 * A convenience method for getting float values out of XML elements
	 * @param attribute The name of the attribute
	 * @param defaultValue The default value to return if no default is specified
	 * @throws NumberFormatException If the supplied attribute is not a number
	 * @return the parsed float value
	 */
	static float getFloat(Element element, String attribute, float defaultValue) throws Exception {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			return defaultValue;
		else
			return Float.parseFloat(s);
	}
	

	/**
	 * A convenience method for getting integer values out of XML elements
	 * @param attribute The name of the attribute
	 * @throws NumberFormatException If the supplied attribute is not a number
	 * @throws Exception if the value is missing
	 * @return the parsed integer value
	 */
	static int getInt(Element element, String attribute) throws Exception {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			throw new Exception("Attribute '"+attribute+"' has not been specified for "+element.getNodeName());
		else
			return Integer.parseInt(s);
	}
	

	/**
	 * A convenience method for getting integer values out of XML elements
	 * @param attribute The name of the attribute
	 * @param defaultValue The default value to return if no default is specified
	 * @throws NumberFormatException If the supplied attribute is not a number
	 * @return the parsed integer value
	 */
	static int getInt(Element element, String attribute, int defaultValue) throws Exception {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			return defaultValue;
		else
			return Integer.parseInt(s);
	}
	

	/**
	 * A convenience method for getting string values out of XML elements
	 * @param attribute The name of the attribute
	 * @return the string value, which will not be null
	 * @throws Exception the value is not specified
	 */
	static String getString(Element element, String attribute) throws Exception {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			throw new Exception("Attribute '"+attribute+"' has not been specified for "+element.getNodeName());
		else
			return s;
	}
	

	/**
	 * A convenience method for getting string values out of XML elements
	 * @param attribute The name of the attribute
	 * @param defaultValue The default value to return if no default is specified
	 * @return the string value, which will not be null
	 */
	static String getString(Element element, String attribute, String defaultValue) throws Exception {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			return defaultValue;
		else
			return s;
	}

	/**
	 * @return true if the specified attribute is present and not empty or null in the element
	 */
	static boolean hasAttribute(Element element, String attribute) {
		String s = element.getAttribute(attribute);
		if (s == null || "".equals(s))
			return false;
		else
			return true;
	}



}
