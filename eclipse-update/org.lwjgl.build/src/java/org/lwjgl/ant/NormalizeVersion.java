/*******************************************************************************
 * Copyright (c) 2011 LWJGL Project and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html, and under the terms of the 
 * BSD license, see http://lwjgl.org/license.php for details.
 *
 * Contributors:
 *    Jens von Pilgrim - initial implementation
 ******************************************************************************/
package org.lwjgl.ant;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * Normalizes a version number and stores result in a property. The version
 * number is normalized as "%n.%n.%n", e.g. "2.6" becomes "2.6.0"
 *
 * @author 	Jens von Pilgrim
 * @since 	Nov 14, 2010
 * @see http://wiki.eclipse.org/index.php/Version_Numbering
 */
public class NormalizeVersion extends Task {

	
	/**
	* name of the property to set
	*/
	protected String property;

	protected String version;

	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}

	/**
	 * @param i_property the property to set
	 */
	public void setProperty(String i_property) {
		property = i_property;
	}

	/**
	 * @return the versionNumber
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param i_versionNumber the versionNumber to set
	 */
	public void setVersion(String version) {
		this.version =version;
	}

	/**
	* check for errors
	* @throws BuildException if we are not configured right
	*/
	private void validate() {
		//validation
		if (property == null) {
			throw new BuildException("attribute property missing");
		}
		if (version == null) {
			throw new BuildException("attribute version missing");
		}
	}

	/**
	 * Sets given property with normalized version number accoring to
	 * major.minor.service[.qualifier]
	 * {@inheritDoc}
	 * @see org.apache.tools.ant.Task#execute()
	 */
	@Override
	public void execute() throws BuildException {
		validate();
		//now exit here if the property is already set
		if (getProject().getProperty(property) != null) {
			return;
		}

		String normalizedVersionNumber = doExecute();

		getProject().setNewProperty(property, normalizedVersionNumber);
	}

	/**
	 * @return
	 */
	protected String doExecute() {
		String s = getVersion().trim();
		StringBuilder n = new StringBuilder();
		int snIndex = 0;
		boolean qualifier = false;

		String digits = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (Character.isDigit(c) && !qualifier) {
				digits += c;
			} else if (c == '.') {
				if (qualifier) {
					throw new BuildException(
							"Wrong format, qualifier must not contain a dot in "
									+ s);
				}
				if (snIndex < 3) {
					if (digits.length() > 0) {
						if (snIndex > 0)
							n.append('.');
						n.append(digits);
						digits = "";
						snIndex++;
					}
				} else {
					throw new BuildException(
							"Wrong format, expected digit, was " + c
									+ " at pos " + i + " of " + s);
				}
			} else if (Character.isJavaIdentifierPart(c)) {
				if (digits.length() > 0) {
					throw new BuildException(
							"Wrong format, qualifier must not start with digits in "
									+ s);
				}
				if (!qualifier) {
					switch (snIndex) {
					case 0: // e.g. "beta"
						n.append("0");
						break;
					case 1: // e.g. "1.beta
						n.append(".0");
					case 2: // e.g. "1.2.beta
						n.append(".0");
					}
					qualifier = true;
					n.append('.');
				}
				n.append(c);
			}
			

		}
		if (!qualifier) {
			if (digits.length()>0) {
				if (snIndex > 0)
					n.append('.');
				n.append(digits);
				snIndex++;
			}
			switch (snIndex) {
			case 0: // e.g. ""
				n.append("0");
				break;
			case 1: // e.g. "1.beta
				n.append(".0");
			case 2: // e.g. "1.2.beta
				n.append(".0");
			}
		}
		return n.toString();
	}

}
