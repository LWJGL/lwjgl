/*******************************************************************************
 * Copyright (c) 2008 Jens von Pilgrim and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Project:	de.jevopi.anttasks
 * File:  	NormalizeVersionTest.java
 * Date: 	Nov 14, 2010
 *******************************************************************************/
package org.lwjgl.ant;

import org.junit.Assert;

import org.apache.tools.ant.BuildException;
import org.junit.Test;


/**
 * NormalizeVersionTest
 * There should really be more documentation here.
 *
 * @author 	Jens von Pilgrim
 * @version	$Revision$
 * @since 	Nov 14, 2010
 */
public class NormalizeVersionTest {

	@Test
	public void testNormalizer() {
		NormalizeVersion t = new NormalizeVersion();
		t.setProperty("normalized");
		
		t.setVersion("1");
		Assert.assertEquals("1.0.0", t.doExecute());
		
		t.setVersion("1.2");
		Assert.assertEquals("1.2.0", t.doExecute());
		
		t.setVersion("1.2.3");
		Assert.assertEquals("1.2.3", t.doExecute());
		
		t.setVersion("1.2.3.beta");
		Assert.assertEquals("1.2.3.beta", t.doExecute());
		
		t.setVersion("1.2.beta");
		Assert.assertEquals("1.2.0.beta", t.doExecute());
		
	}
	
	
	@Test(expected= BuildException.class) public void tooManyDots() {
		NormalizeVersion t = new NormalizeVersion();
		t.setProperty("normalized");

		t.setVersion("1.2.3.4.5");
		t.doExecute(); 
	}
	
	@Test(expected= BuildException.class) public void qualifierWithNumber() {
		NormalizeVersion t = new NormalizeVersion();
		t.setProperty("normalized");

		t.setVersion("1.2beta");
		t.doExecute(); 
	}
	
	
	@Test
	public void testQualifier() {
		NormalizeVersion t = new NormalizeVersion();
		t.setProperty("normalized");
		t.setAddDateQualifier(true);
		
		t.setVersion("1");
		String s = t.doExecute();
		Assert.assertEquals(20, s.length());
		Assert.assertTrue(s.startsWith("1.0.0"));
		
		t.setVersion("1.2");
		s = t.doExecute();
		Assert.assertEquals(20, s.length());
		Assert.assertTrue(s.startsWith("1.2.0"));
		
		t.setVersion("1.2.3");
		s = t.doExecute();
		Assert.assertEquals(20, s.length());
		Assert.assertTrue(s.startsWith("1.2.3"));
		
		t.setVersion("2.8.2");
		s = t.doExecute();
		Assert.assertEquals(20, s.length());
		Assert.assertTrue(s.startsWith("2.8.2"));
	
	}
	
	@Test(expected= BuildException.class) public void dateQualifierWithQualifier() {
		NormalizeVersion t = new NormalizeVersion();
		t.setProperty("normalized");
		t.setAddDateQualifier(true);
		t.setVersion("1.2.beta");
		t.doExecute(); 
	}
	
	
	
}
