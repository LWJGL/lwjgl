/*
 * Created on 18-03-2003
 *
 * To change this generated comment go to 
 * Window>Preferences>Java>Code Generation>Code Template
 */
package org.lwjgl.test;

import org.lwjgl.*;

/**
 * @author Elias Naur
 */
public class DisplayConfigurationTest {
	private static void changeConfig(float gamma, float brightness, float contrast) {
		Display.setDisplayConfiguration(gamma, brightness, contrast);
		System.out.println("Configuration changed, gamma = " + gamma + " brightness = " + brightness + " contrast = " + contrast);
		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Testing normal setting");
		changeConfig(1.0f, 0f, 1f);
		System.out.println("Testing gamma settings");
		changeConfig(5.0f, 0f, 1f);
		changeConfig(0.5f, 0f, 1f);
		System.out.println("Testing brightness settings");
		changeConfig(1.0f, -1.0f, 1f);
		changeConfig(1.0f, -0.5f, 1f);
		changeConfig(1.0f, 0.5f, 1f);
		changeConfig(1.0f, 1.0f, 1f);
		System.out.println("Testing contrast settings");
		changeConfig(1.0f, 0f, 0f);
		changeConfig(1.0f, 0f, 0.5f);
		changeConfig(1.0f, 0f, 10000.0f);
		System.out.println("Test done - Resetting configuration");
		Display.resetDisplayMode();
	}
}
