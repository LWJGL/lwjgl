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

import java.io.StringWriter;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IClasspathAttribute;
import org.eclipse.jdt.core.IClasspathContainer;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.internal.ui.JavaPluginImages;
import org.eclipse.jdt.internal.ui.viewsupport.BasicElementLabels;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.ui.JavaElementLabels;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage;
import org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension;
import org.eclipse.jdt.ui.wizards.NewElementWizardPage;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * LWJGLClasspathContainerPage
 * There should really be more documentation here.
 *
 * @author 	Jens von Pilgrim
 * @since 	Dec 5, 2008
 */
public class LWJGLClasspathContainerPage extends NewElementWizardPage implements
		IClasspathContainerPage, IClasspathContainerPageExtension {

	private IClasspathEntry containerEntryResult;

	private Label labelResolvedPath;

	private Label labelResolvedSourcePath;

	private Label labelResolvedDocPath;

	private Label labelNativePath;

	public LWJGLClasspathContainerPage() {
		super("LWJGLContainterPage"); //$NON-NLS-1$
		setTitle("LWJGL Library");
		setDescription("Adds LWJGL Java Libraries to build path");
		setImageDescriptor(JavaPluginImages.DESC_WIZBAN_ADD_LIBRARY);

		containerEntryResult = JavaCore
				.newContainerEntry(LWJGLClasspathContainerInitializer.LWJGL_LIBRARY_PATH);
	}

	public static IJavaProject getPlaceholderProject() {
		String name = "####internal"; //$NON-NLS-1$
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		while (true) {
			IProject project = root.getProject(name);
			if (!project.exists()) {
				return JavaCore.create(project);
			}
			name += '1';
		}
	}

	public boolean finish() {
		try {
			IJavaProject[] javaProjects = new IJavaProject[] { getPlaceholderProject() };
			IClasspathContainer[] containers = { null };
			JavaCore.setClasspathContainer(containerEntryResult.getPath(),
					javaProjects, containers, null);
		} catch (JavaModelException e) {
			perform(e, getShell(), e.getMessage());
			return false;
		}
		return true;
	}

	public IClasspathEntry getSelection() {
		return containerEntryResult;
	}

	public void setSelection(IClasspathEntry containerEntry) {
		containerEntryResult = containerEntry;
	}

	public void createControl(Composite parent) {

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setFont(parent.getFont());

		composite.setLayout(new GridLayout(2, false));

		createLabel(composite, "Path");
		labelResolvedPath = createPathLabel(composite);
		
		createLabel(composite, "Source Path");
		labelResolvedSourcePath = createPathLabel(composite);

		createLabel(composite, "JavaDoc Path");
		labelResolvedDocPath = createPathLabel(composite);

		createLabel(composite, "Native Path");
		labelNativePath = createPathLabel(composite);
		
		
		update();

		setControl(composite);
	}
	
	private void createLabel(Composite parent, String strLabel) {
		Label label = new Label(parent, SWT.NONE);
		label.setFont(parent.getFont());
		label.setText(strLabel);
		label.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, false, false, 1, 1));
	}
		
	private Label createPathLabel(Composite parent) {
		Label label  = new Label(parent, SWT.WRAP);
		label.setFont(parent.getFont());
		label.setLayoutData(new GridData(GridData.FILL, GridData.BEGINNING, true,
				true, 1, 1));
		return label;
	}

	protected void update() {

		IStatus status = null;

		IClasspathEntry[] libEntries = BuildPathSupport
				.getLWJGLLibraryEntries();

		IPath containerPath = LWJGLClasspathContainerInitializer.LWJGL_LIBRARY_PATH;
		containerEntryResult = JavaCore.newContainerEntry(containerPath);

		if (libEntries == null) {
			status = new Status(ERROR, Activator.PLUGIN_ID,
					"No LWJGL library found");
		} else

		if (labelResolvedPath != null && !labelResolvedPath.isDisposed()) {
			// implies all other labels to be created and not yet disposed
			if (libEntries != null) {
				Set<String> setLines = new TreeSet<String>();
				IPath path;
				for (IClasspathEntry entry : libEntries) {
					path = entry.getPath();
					if (path != null) {
						setLines.add(getPathLabel(path));
					}
				}
				setLabel(labelResolvedPath, setLines);

				setLines.clear();
				for (IClasspathEntry entry : libEntries) {
					path = entry.getSourceAttachmentPath();
					if (path != null) {
						setLines.add(getPathLabel(path));
					}
				}
				setLabel(labelResolvedSourcePath, setLines);

				setLines.clear();
				for (IClasspathEntry entry : libEntries) {
					if (entry.getExtraAttributes() != null) {
						for (IClasspathAttribute attr : entry
								.getExtraAttributes()) {
							if (IClasspathAttribute.JAVADOC_LOCATION_ATTRIBUTE_NAME
									.equals(attr.getName())) {
								setLines.add(attr.getValue());
								break;
							}
							if (JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY.equals(attr.getName())) {
								
							}
						}
					}
				}
				setLabel(labelResolvedDocPath, setLines);
				
				setLines.clear();
				for (IClasspathEntry entry : libEntries) {
					if (entry.getExtraAttributes() != null) {
						for (IClasspathAttribute attr : entry
								.getExtraAttributes()) {
							if (JavaRuntime.CLASSPATH_ATTR_LIBRARY_PATH_ENTRY.equals(attr.getName())) {
								setLines.add(attr.getValue());
							}
						}
					}
				}
				setLabel(labelNativePath, setLines);

			} else {
				labelResolvedPath.setText("not found");
				labelResolvedSourcePath.setText("not found");
				labelResolvedDocPath.setText("not found");
			}
		}
		if (status != null)
			updateStatus(status);
	}

	/**
	 * @param i_labelResolvedDocPath
	 * @param i_setDocPaths
	 */
	private void setLabel(Label label, Set<String> lines) {
		StringBuffer strb = new StringBuffer();
		for (String str : lines) {
			if (strb.length() > 0) {
				strb.append("\n");
			}
			strb.append(str);
		}
		label.setText(strb.toString());
	}

	private String getPathLabel(IPath path) {
		StringBuffer buf = new StringBuffer(BasicElementLabels
				.getResourceName(path.lastSegment()));
		buf.append(JavaElementLabels.CONCAT_STRING);
		buf.append(BasicElementLabels.getPathLabel(path.removeLastSegments(1),
				true));
		return buf.toString();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jdt.ui.wizards.IClasspathContainerPageExtension#initialize(org.eclipse.jdt.core.IJavaProject, org.eclipse.jdt.core.IClasspathEntry[])
	 */
	public void initialize(IJavaProject project,
			IClasspathEntry[] currentEntries) {
	}

	protected void perform(CoreException e, Shell shell, String message) {
		IStatus status = e.getStatus();
		if (status != null) {
			ErrorDialog.openError(shell, "LWJGL Library", message, status);
		} else {

			StringWriter msg = new StringWriter();
			if (message != null) {
				msg.write(message);
				msg.write("\n\n"); //$NON-NLS-1$
			}
			if (message == null || message.length() == 0)
				msg.write("Unknown error adding LWJGL library");
			else
				msg.write(message);
			MessageDialog.openError(shell, "LWJGL Library", msg.toString());

		}
	}

}
