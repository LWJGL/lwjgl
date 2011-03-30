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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.taskdefs.Expand;
import org.apache.tools.ant.types.PatternSet;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.FileUtils;

/**
 * Ant task extracting package names (or generally directory names) into given
 * property.
 * 
 * Parameters:
 * - just like unzip, except dest can be null and is ignored otherwise
 * - property - name of property storing the resulting list
 * - pathsep - path separator, default ","
 * - dirsep - directory separator, default "."
 * - includeemptydirs -- whether to include empty directories, default: false
 *
 * @author 	Jens von Pilgrim
 * @since 	12.09.2007
 */
public class PackageList extends Expand {

	/**
	* The property to receive the conversion
	*/
	private String property = null;

	/**
	 * User override on path sep char
	 */
	private String pathSep = ",";

	/**
	 * User override on directory sep char
	 */
	private String dirSep = ".";
	
	
	private boolean includeemptydirs = false;
	
	class IntegerContainer {
		int value;

		public void inc() {
			value++;
		}
	};

	TreeMap<String, IntegerContainer> packagelist = new TreeMap<String, IntegerContainer>();

	/** 
	 * {@inheritDoc}
	 * @see org.apache.tools.ant.taskdefs.Expand#execute()
	 */
	@Override
	public void execute() throws BuildException {

		setDest(new File(" no file, files are only listed internaly"));
		super.execute();
		
		StringBuffer strb = new StringBuffer();
		for (String name : packagelist.keySet()) {
			
			// System.out.println("name: " + name + " (" +packagelist.get(name).value + ")" );

			if (includeemptydirs || packagelist.get(name).value > 0) {
				if (strb.length() > 0) {
					strb.append(pathSep);
				}
				StringTokenizer stDirectory = new StringTokenizer(name, "/",
						true);

				while (stDirectory.hasMoreTokens()) {
					String token = stDirectory.nextToken();
					strb.append("/".equals(token) ? dirSep : token);
				}
			}
		}

		if (property != null) {
			String value = strb.toString();
			getProject().setNewProperty(property, value);
		}

	}

	private Vector<PatternSet> patternsets = new Vector<PatternSet>();

	/**
	 * Add a patternset.
	 * @param set a pattern set
	 */
	public void addPatternset(PatternSet set) {
		super.addPatternset(set);
		patternsets.addElement(set);
	}

	/** 
	 * {@inheritDoc}
	 * @see org.apache.tools.ant.taskdefs.Expand#extractFile(org.apache.tools.ant.util.FileUtils, java.io.File, java.io.File, java.io.InputStream, java.lang.String, java.util.Date, boolean, org.apache.tools.ant.util.FileNameMapper)
	 */
	@Override
	protected void extractFile(FileUtils i_fileUtils, File i_srcF, File i_dir,
			InputStream i_compressedInputStream, String i_entryName,
			Date i_entryDate, boolean i_isDirectory, FileNameMapper i_mapper)
			throws IOException {

		if (!matchPatterns(i_entryName)) {
			//Do not process this file
			return;
		}

		String strDir = getDir(i_isDirectory, i_entryName);
		if (strDir != null && !"META-INF".equals(strDir)) {
			if (!packagelist.containsKey(strDir)) {
				packagelist.put(strDir, new IntegerContainer());
			}
			if (!i_isDirectory) {
				packagelist.get(strDir).inc();
			}
		}

	}

	/**
	 * @param i_entryName
	 */
	private boolean matchPatterns(String i_entryName) {
		if (patternsets != null && patternsets.size() > 0) {
			boolean included = false;
			String name = i_entryName.replace('/', File.separatorChar).replace(
					'\\', File.separatorChar);

			Set<String> includePatterns = new HashSet<String>();
			Set<String> excludePatterns = new HashSet<String>();
			for (int v = 0, size = patternsets.size(); v < size; v++) {
				PatternSet p = (PatternSet) patternsets.elementAt(v);
				String[] incls = p.getIncludePatterns(getProject());
				if (incls == null || incls.length == 0) {
					// no include pattern implicitly means includes="**"
					incls = new String[] { "**" };
				}

				for (int w = 0; w < incls.length; w++) {
					String pattern = incls[w].replace('/', File.separatorChar)
							.replace('\\', File.separatorChar);
					if (pattern.endsWith(File.separator)) {
						pattern += "**";
					}
					includePatterns.add(pattern);
				}

				String[] excls = p.getExcludePatterns(getProject());
				if (excls != null) {
					for (int w = 0; w < excls.length; w++) {
						String pattern = excls[w].replace('/',
								File.separatorChar).replace('\\',
								File.separatorChar);
						if (pattern.endsWith(File.separator)) {
							pattern += "**";
						}
						excludePatterns.add(pattern);
					}
				}
			}

			for (Iterator<String> iter = includePatterns.iterator(); !included
					&& iter.hasNext();) {
				String pattern = iter.next();
				included = SelectorUtils.matchPath(pattern, name);
			}

			for (Iterator<String> iter = excludePatterns.iterator(); included
					&& iter.hasNext();) {
				String pattern =  iter.next();
				included = !SelectorUtils.matchPath(pattern, name);
			}

			return included;
		}
		return true;
	}

	/**
	 * @param i_isDirectory
	 * @param i_entryName
	 * @return
	 */
	private String getDir(boolean i_isDirectory, String i_entryName) {

		if (i_entryName == null)
			return null;
		int iIndex = i_entryName.lastIndexOf('/');
		if (iIndex >= 0) {
			return i_entryName.substring(0, iIndex);
		}
		if (i_isDirectory) {
			return i_entryName;
		}
		return null;
	}

	/**
	 * Set the name of the property into which the converted path will be placed.
	 * @param p the property name.
	 */
	public void setProperty(String p) {
		property = p;
	}

	/**
	 * Set the default path separator string; defaults to current JVM
	 * {@link java.io.File#pathSeparator File.pathSeparator}.
	 * @param sep path separator string.
	 */
	public void setPathSep(String sep) {
		pathSep = sep;
	}

	/**
	 * Set the default directory separator string;
	 * defaults to current JVM {@link java.io.File#separator File.separator}.
	 * @param sep directory separator string.
	 */
	public void setDirSep(String sep) {
		dirSep = sep;
	}

	/**
	 * Simple getter for attribute includeemptydirs.
	 * @return the includeemptydirs
	 */
	public boolean isIncludeemptydirs() {
		return includeemptydirs;
	}

	/**
	 * Simple setter for attribute includeemptydirs.
	 * @param i_includeemptydirs the includeemptydirs to set
	 */
	public void setIncludeemptydirs(boolean i_includeemptydirs) {
		includeemptydirs = i_includeemptydirs;
	}

}
