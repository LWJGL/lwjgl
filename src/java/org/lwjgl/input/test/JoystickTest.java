/*
 * JoystickTest.java
 *
 * Created on 13. november 2002, 22:49
 */

package org.lwjgl.input.test;

import java.awt.*;
import java.awt.event.*;
import org.lwjgl.input.Joystick;

/**
 *
 * @author  Brian Matzon
 */
public class JoystickTest extends Panel {
    
    private int counter = 0;
    
    public Thread animationThread;
    
    /** Creates a new instance of JoystickTest */
    public JoystickTest() {
        try {
            Joystick.create();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        animationThread = new Thread() {
            public void run() {
                while (true) {
                    paint(getGraphics());
                    
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException inte) {
                        inte.printStackTrace();
                    }
                }
            }
        };
        animationThread.setDaemon(true);
    }
    
    public void paint(Graphics g) {
        if (g == null) {
            return;
        }
        
        g.setColor(Color.white);
        g.fillRect(0, 0, 640, 480);
        
        int y = 100;
        int x = 100;
        Joystick.poll();
        
        g.setColor(Color.blue);
        g.drawString("Buttoncount: " + Joystick.buttonCount, x, y);
        y += 20;
        g.drawString("-----------------------------------------------", x, y);
        y += 20;
        g.drawString("x  : " + Joystick.x, x, y);
        y += 20;
        g.drawString("y  : " + Joystick.y, x, y);
        y += 20;
        if(Joystick.hasZAxis) {
            g.drawString("z  : " + Joystick.z, x, y);
            y += 20;
        }
        if(Joystick.hasPOV) {
            g.drawString("pov: " + Joystick.pov, x, y);
            y += 20;
        }
        
        //paint buttons
        g.drawString("btn: ", x, y);
        x += g.getFontMetrics().stringWidth("btn: ");
        for(int i=0; i<Joystick.buttonCount; i++) {
            if(Joystick.isButtonDown(i)) {
                g.drawString(i + ", ", x, y);
                x+= 15;
            }
        }
    }
    
    public void update(Graphics g) {
        paint(g);
    }
    
    public void disposing() {
        Joystick.destroy();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final JoystickTest p = new JoystickTest();
        final Frame f = new Frame();
        f.setLayout(null);
        f.setSize(640, 480);
        f.setLocation(100, 100);
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                f.hide();
                p.disposing();
                f.dispose();
            }
        });
        
        p.setSize(640, 480);
        p.setLocation(0, 0);
        p.setBackground(Color.RED);
        
        f.add(p);
        f.show();
        p.animationThread.start();
    }
}