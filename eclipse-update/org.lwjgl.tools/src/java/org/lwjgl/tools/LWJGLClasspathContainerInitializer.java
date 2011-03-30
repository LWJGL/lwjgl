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
package org.lwjgl.tools;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.ClasspathContainerInitializer;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaModel;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.launching.JavaRuntime;

/**
 * LWJGLClasspathContainerInitializer sets up LWJGL library path.
 * 
 * Parts of this code were copied and modified from 
 * {@link org.eclipse.jdt.internal.junit.buildpath.JUnitContainerInitializer},
 * which is licensed under the EPL as well.
 *
 * @author 	Jens von Pilgrim
 * @version	$Revision$
 * @since 	Dec 5, 2008
 */
public class LWJGLClasspathContainerInitializer extends
		ClasspathContainerInitializer {

	public static String LWJGL_LIBRARY_ID = "org.lwjgl.LWJGL_CONTAINER";

	public static IPath LWJGL_LIBRARY_PATH = new Path(LWJGL_LIBRARY_ID);

	private static final IStatus NOT_SUPPORTED = new Status(IStatus.ERROR,
			Activator.PLUGIN_ID,
			ClasspathContainerInitializer.ATTRIBUTE_NOT_SUPPORTED,
			new String(), null);

	private static class LWJGLClasspathContainer implements IClasspathContainer {

		private final IClasspathEntry[] fEntries;

		private final IPath fPath;

		public LWJGLClasspathContainer(IPath path, IClasspathEntry[] entries) {
			fPath = path;
			fEntries = entries;
		}

		public IClasspathEntry[] getClasspathEntries() {
			return fEntries;
		}

		public String getDescription() {
			return "LWJGL Libraries";
		}

		public int getKind() {
			return IClasspathContainer.K_APPLICATION;
		}

		public IPath getPath() {
			return fPath;
		}

	}

	public LWJGLClasspathContainerInitializer() {
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#initialize(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public void initialize(IPath containerPath, IJavaProject project)
			throws CoreException {
		if (isValidLWJGLContainerPath(containerPath)) {
			LWJGLClasspathContainer container = getNewContainer(containerPath);

			JavaCore.setClasspathContainer(containerPath,
					new IJavaProject[] { project },
					new IClasspathContainer[] { container }, null);
		}

	}

	private static boolean isValidLWJGLContainerPath(IPath path) {
		return path != null && path.segmentCount() > 0
				&& LWJGL_LIBRARY_ID.equals(path.segment(0));
	}

	private static LWJGLClasspathContainer getNewContainer(IPath containerPath) {
		IClasspathEntry[] entries = null;
		// String version = containerPath.segment(1);
		entries = BuildPathSupport.getLWJGLLibraryEntries();

		if (entries == null)
			entries = new IClasspathEntry[] {};

		return new LWJGLClasspathContainer(containerPath, entries);
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#canUpdateClasspathContainer(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public boolean canUpdateClasspathContainer(IPath containerPath,
			IJavaProject project) {
		return true;
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getAccessRulesStatus(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public IStatus getAccessRulesStatus(IPath containerPath,
			IJavaProject project) {
		return NOT_SUPPORTED;
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getSourceAttachmentStatus(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public IStatus getSourceAttachmentStatus(IPath containerPath,
			IJavaProject project) {
		return Status.OK_STATUS;
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getAttributeStatus(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject, java.lang.String)
	 */
	public IStatus getAttributeStatus(IPath containerPath,
			IJavaProject project, String attributeKey) {
		if (attributeKey
				.equals(IClasspathAttribute.JAVADOC_LOCATION_ATTRIBUTE_NAME)) {
			return Status.OK_STATUS;
		}
		if (attributeKey.equals(JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY)) {
			return Status.OK_STATUS;
		}

		return NOT_SUPPORTED;
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#requestClasspathContainerUpdate(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject, org.eclipse.jdt.core.IClasspathContainer)
	 */
	public void requestClasspathContainerUpdate(IPath containerPath,
			IJavaProject project, IClasspathContainer containerSuggestion)
			throws CoreException {
		IClasspathEntry[] entries = containerSuggestion.getClasspathEntries();
		if (entries.length == 1 && isValidLWJGLContainerPath(containerPath)) {
			// String version = containerPath.segment(1);

			// only modifiable entry in Javadoc location
			IClasspathAttribute[] extraAttributes = entries[0]
					.getExtraAttributes();
			for (int i = 0; i < extraAttributes.length; i++) {
				IClasspathAttribute attrib = extraAttributes[i];
				if (attrib.getName().equals(
						IClasspathAttribute.JAVADOC_LOCATION_ATTRIBUTE_NAME)) {
					break;
				}
			}
			rebindClasspathEntries(project.getJavaModel(), containerPath);
		}
	}

	private static void rebindClasspathEntries(IJavaModel model,
			IPath containerPath) throws JavaModelException {
		List<IJavaProject> affectedProjects = new ArrayList<IJavaProject>();

		IJavaProject[] projects = model.getJavaProjects();
		for (int i = 0; i < projects.length; i++) {
			IJavaProject project = projects[i];
			IClasspathEntry[] entries = project.getRawClasspath();
			for (int k = 0; k < entries.length; k++) {
				IClasspathEntry curr = entries[k];
				if (curr.getEntryKind() == IClasspathEntry.CPE_CONTAINER
						&& containerPath.equals(curr.getPath())) {
					affectedProjects.add(project);
				}
			}
		}
		if (!affectedProjects.isEmpty()) {
			IJavaProject[] affected = (IJavaProject[]) affectedProjects
					.toArray(new IJavaProject[affectedProjects.size()]);
			IClasspathContainer[] containers = new IClasspathContainer[affected.length];
			for (int i = 0; i < containers.length; i++) {
				containers[i] = getNewContainer(containerPath);
			}
			JavaCore.setClasspathContainer(containerPath, affected, containers,
					null);
		}
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getDescription(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public String getDescription(IPath containerPath, IJavaProject project) {
		return "LWJGL library settings, including required java libraries "
				+ "(jars) and native libraries.";
	}

	/** 
	 * {@inheritDoc}
	 * @see org.eclipse.jdt.core.ClasspathContainerInitializer#getComparisonID(org.eclipse.core.runtime.IPath, org.eclipse.jdt.core.IJavaProject)
	 */
	public Object getComparisonID(IPath containerPath, IJavaProject project) {
		return containerPath;
	}

}
