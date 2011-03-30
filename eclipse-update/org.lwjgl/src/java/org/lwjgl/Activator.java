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
package org.lwjgl;

import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * The activator class controls the plug-in life cycle.
 *
 * @author 	Jens von Pilgrim (developer@jevopi.de)
 * @since 	Mar 30, 2011
 */	
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.lwjgl";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugins#start(org.osgi.framework.BundleContext)
	 */
	@Override
	public void start(BundleContext context) throws Exception {

		super.start(context);

		try {
			String path = LibraryPathUtil.getLWJGLLibraryPath(context);
			Status status = new Status(Status.INFO, PLUGIN_ID, Status.INFO,
					"Set org.lwjgl.librarypath to " + path, null);
			getLog().log(status);

		} catch (Throwable ex) {
			Status status = new Status(Status.ERROR, PLUGIN_ID, Status.ERROR,
					"Error setting native LWJGL libraries path: " + ex.toString(), ex);
			getLog().log(status);
			throw new BundleException(status.getMessage(), ex);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
	 */
	@Override
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

}
