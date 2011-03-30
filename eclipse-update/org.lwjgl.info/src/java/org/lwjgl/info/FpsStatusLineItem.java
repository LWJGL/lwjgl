/*******************************************************************************
 * Copyright (c) 2011 LWJGL Project and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html, and under the terms of the 
 * BSD license, see http://lwjgl.org/license.php for details.
 *
 * Contributors:
 *     Kristian Duske - FPS Status Line Item
 *******************************************************************************/

package org.lwjgl.info;

import java.util.logging.Logger;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.StatusLineLayoutData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;

/**
 * Displays FPS information in the Eclipse status bar.
 * 
 * @author Kristian Duske
 * @version $Revision$
 * @since 22.05.2008
 * @see $HeadURL$
 */
public class FpsStatusLineItem extends ContributionItem {

	private static final int CHAR_WIDTH = 8;

	private static final int INDENT = 3;

	@SuppressWarnings("unused")
	private static final Logger log = Logger.getLogger(FpsStatusLineItem.class
			.getName());

	private static final int NUM_FRAMES = 500;

	private static final long TIMEOUT = 300;

	private int m_fixedHeight = -1;

	private int m_fixedWidth = -1;

	private long[] m_frames = new long[NUM_FRAMES];

	private int m_index = 0;

	private int m_count = -1;

	private double avg;

	private long m_lastFrame = -1;

	private CLabel m_label;

	/**
	 * 
	 */
	public FpsStatusLineItem() {
		for (int i = 0; i < m_frames.length; i++)
			m_frames[i] = 0;
		avg = 0;
	}

	/**
	 * @param i_id
	 */
	public FpsStatusLineItem(String i_id) {
		super(i_id);
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	public void fill(Composite i_parent) {

		Label sep = new Label(i_parent, SWT.SEPARATOR);
		m_label = new CLabel(i_parent, SWT.SHADOW_NONE);

		StatusLineLayoutData data = new StatusLineLayoutData();
		data.widthHint = getWidthHint(i_parent);
		m_label.setLayoutData(data);

		data = new StatusLineLayoutData();
		data.heightHint = getHeightHint(i_parent);
		sep.setLayoutData(data);

		updateCounter();
	}

	private int getHeightHint(Composite control) {

		if (m_fixedHeight < 0) {
			GC gc = new GC(control);
			gc.setFont(control.getFont());
			m_fixedHeight = gc.getFontMetrics().getHeight();
			gc.dispose();
		}
		return m_fixedHeight;
	}

	private int getWidthHint(Composite control) {

		if (m_fixedWidth < 0) {
			GC gc = new GC(control);
			gc.setFont(control.getFont());
			m_fixedWidth = gc.getFontMetrics().getAverageCharWidth()
					* CHAR_WIDTH;
			m_fixedWidth += INDENT * 2;
			gc.dispose();
		}
		return m_fixedWidth;
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see de.feu.draw3d.RenderListener#renderPassFinished()
	 */
	public void renderPassFinished() {

		long time = System.currentTimeMillis();
		if (m_lastFrame == -1) {
			m_lastFrame = time;
			return;
		}

		long frameTime = time - m_lastFrame;
		m_lastFrame = time;

		if (frameTime <= TIMEOUT) {

			if (m_count < NUM_FRAMES)
				m_count++;

			avg -= m_frames[m_index];
			m_frames[m_index] = frameTime;
			avg += m_frames[m_index];

			m_index++;
			if (m_index >= NUM_FRAMES) {
				m_index = 0;
			}

		}

		updateCounter();
	}

	/**
	 * {@inheritDoc}
	 * 
	 * @see de.feu.draw3d.RenderListener#renderPassStarted()
	 */
	public void renderPassStarted() {

		// nothing to do
	}

	private synchronized void updateCounter() {

		long fps = 0;
		if (m_count > 0)
			fps = (long) (1000.0 / (avg / ((double) m_count)));
		//		System.out.println("i:"+m_index+", c="+m_count+", avg: " + avg);

		if (m_label != null && !m_label.isDisposed()) {
			Display display = m_label.getDisplay();

			m_label.setForeground(display
					.getSystemColor(SWT.COLOR_WIDGET_FOREGROUND));
			m_label.setText(fps + " FPS");
		}
	}
}
