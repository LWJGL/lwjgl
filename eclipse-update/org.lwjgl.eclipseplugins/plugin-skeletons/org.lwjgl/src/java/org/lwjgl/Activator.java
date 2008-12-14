package org.lwjgl;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends Plugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.lwjgl";

	public static String[] NATIVEPATH = new String[] { "win32", "macosx",
			"linux" };

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

		int iOS = -1;
		String osname = System.getProperty("os.name").toLowerCase();
		String osarch = System.getProperty("os.arch").toLowerCase();

		if (osname.startsWith("windows") && osarch.startsWith("x86")) {
			iOS = 0;
		} else if (osname.startsWith("mac os x")) {
			iOS = 1;
		} else if (osname.startsWith("linux")) {
			iOS = 2;
		}

		if (iOS >= 0) {
			try {
				Bundle fragment = context.getBundle();
				String base = "native" + File.separator + NATIVEPATH[iOS];
				URL url = FileLocator.resolve(fragment.getEntry(base));
				File fileDir = new File(url.getPath());
				System.setProperty("org.lwjgl.librarypath", fileDir.getPath());
			} catch (Throwable ex) {
				Status status = new Status(Status.ERROR, PLUGIN_ID,
						Status.ERROR, "Error loading LWJGL", ex);
				getLog().log(status);
				throw new BundleException(status.getMessage(), ex);
			}
		} else {
			Status status = new Status(Status.ERROR, PLUGIN_ID, Status.ERROR,
					"Error setting native libraries path. LWJGL not available for "
							+ osname + "(" + osarch + ")", null);
			getLog().log(status);
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
