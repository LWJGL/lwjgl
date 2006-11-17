package org.lwjgl.input;

import java.util.ArrayList;

import net.java.games.input.Component;

/**
 * A wrapper round a JInput controller that attempts to make the interface
 * more useable.
 * 
 * @author Kevin Glass
 */
class JInputController implements Controller {
	/** The JInput controller this class is wrapping */
	private net.java.games.input.Controller target;
	/** The index that has been assigned to this controller */
	private int index;
	/** The Buttons that have been detected on the JInput controller */
	private ArrayList buttons = new ArrayList();
	/** The Axes that have been detected on the JInput controller */
	private ArrayList axes = new ArrayList();
	/** The POVs that have been detected on the JInput controller */
	private ArrayList pov = new ArrayList();
	/** The state of the buttons last check */
	private boolean[] buttonState;
	/** The values that were read from the pov last check */
	private float[] povValues;
	/** The values that were read from the axes last check */
	private float[] axesValue;
	/** The maximum values read for each axis */
	private float[] axesMax;
	/** The dead zones for each axis */
	private float[] deadZones;
	/** The index of the X axis or -1 if no X axis is defined */
	private int xaxis = -1;
	/** The index of the Y axis or -1 if no Y axis is defined */
	private int yaxis = -1;
	/** The index of the X axis or -1 if no Z axis is defined */
	private int zaxis = -1;
	/** The index of the RX axis or -1 if no RX axis is defined */
	private int rxaxis = -1;
	/** The index of the RY axis or -1 if no RY axis is defined */
	private int ryaxis = -1;
	/** The index of the RZ axis or -1 if no RZ axis is defined */
	private int rzaxis = -1;
	
	/**
	 * Create a new controller that wraps round a JInput controller and hopefully
	 * makes it easier to use.
	 * 
	 * @param index The index this controller has been assigned to
	 * @param target The target JInput controller this class is wrapping
	 */
	public JInputController(int index,net.java.games.input.Controller target) {
		this.target = target;
		this.index = index;
		
		Component[] sourceAxes = target.getComponents();
		
		for (int i=0;i<sourceAxes.length;i++) {
			if (sourceAxes[i].getIdentifier() instanceof Component.Identifier.Button) {
				buttons.add(sourceAxes[i]);		
			} else if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.POV)) {
				pov.add(sourceAxes[i]);
			} else {
				axes.add(sourceAxes[i]);
			}
		}
		
		buttonState = new boolean[buttons.size()];
		povValues = new float[pov.size()];
		axesValue = new float[axes.size()];
		int buttonsCount = 0;
		int axesCount = 0;
		
		// initialise the state
		for (int i=0;i<sourceAxes.length;i++) {
			if (sourceAxes[i].getIdentifier() instanceof Component.Identifier.Button) {
				buttonState[buttonsCount] = sourceAxes[i].getPollData() != 0;
				buttonsCount++;
			} else if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.POV)) {
				// no account for POV yet
				// pov.add(sourceAxes[i]);
			} else {
				axesValue[axesCount] = sourceAxes[i].getPollData();
				if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.X)) {
					xaxis = axesCount;
				}
				if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.Y)) {
					yaxis = axesCount;
				}
				if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.Z)) {
					zaxis = axesCount;
				}
				if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.RX)) {
					rxaxis = axesCount;
				}
				if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.RY)) {
					ryaxis = axesCount;
				}
				if (sourceAxes[i].getIdentifier().equals(Component.Identifier.Axis.RZ)) {
					rzaxis = axesCount;
				}
				
				axesCount++;
			}
		}
		
		axesMax = new float[axes.size()];
		deadZones = new float[axes.size()];
		
		for (int i=0;i<axesMax.length;i++) {
			axesMax[i] = 1.0f;
			deadZones[i] = 0.05f;
		}
	}

	/*
	 * @see org.lwjgl.input.Controller#getName()
	 */
	public String getName() {
		String name = target.getName();
		return name;
	}

	/*
	 * @see org.lwjgl.input.Controller#getIndex()
	 */
	public int getIndex() {
		return index;
	}

	/*
	 * @see org.lwjgl.input.Controller#getButtonCount()
	 */
	public int getButtonCount() {
		return buttons.size();
	}

	/*
	 * @see org.lwjgl.input.Controller#getButtonName(int)
	 */
	public String getButtonName(int index) {
		Component button = (Component) buttons.get(index);
		
		return button.getName();
	}

	/*
	 * @see org.lwjgl.input.Controller#isButtonPressed(int)
	 */
	public boolean isButtonPressed(int index) {
		return buttonState[index];
	}

	/*
	 * @see org.lwjgl.input.Controller#poll()
	 */
	public void poll() {
		target.poll();
		
		// read buttons to update events
		for (int i=0;i<getButtonCount();i++) {
			Component button = (Component) buttons.get(i);
			
			float data = button.getPollData();

			if (buttonState[i] != (data != 0)) {
				// fire button pressed event
				Controllers.addEvent(new ControllerEvent(this,ControllerEvent.BUTTON,i,false,false));
			}
			
			buttonState[i] = data != 0;
		}

		// read povs to update events
		for (int i=0;i<pov.size();i++) {
			Component p = (Component) pov.get(i);
			
			float data = p.getPollData();

			if (povValues[i] != data) {
				float prevX = getPovX();
				float prevY = getPovY();
				
				povValues[i] = data;
				
				if (prevX != getPovX()) {
					Controllers.addEvent(new ControllerEvent(this,ControllerEvent.POVX,0,false,false));
				}
				if (prevY != getPovY()) {
					Controllers.addEvent(new ControllerEvent(this,ControllerEvent.POVY,0,false,false));
				}
			}
			
			povValues[i] = data;
		}
		
		// read axis to update events
		for (int i=0;i<getAxisCount();i++) {
			Component axis = (Component) axes.get(i);
			
			float value = axis.getPollData();
			
			// fixed dead zone since most axis don't report it :(
			if (Math.abs(value) < deadZones[i]) {
				value = 0;
			}
			if (Math.abs(value) < axis.getDeadZone()) {
				value = 0;
			}
			if (Math.abs(value) > axesMax[i]) {
				axesMax[i] = Math.abs(value);
			}
			
			// normalize the value based on maximum value read in the past
			value /= axesMax[i];
			
			if (axesValue[i] != value) {
				// fire event
				Controllers.addEvent(new ControllerEvent(this,ControllerEvent.AXIS,i,i == xaxis,i == yaxis));
			}
			axesValue[i] = value;
		}
	}

	/*
	 * @see org.lwjgl.input.Controller#getAxisCount()
	 */
	public int getAxisCount() {
		return axes.size();
	}

	/*
	 * @see org.lwjgl.input.Controller#getAxisName(int)
	 */
	public String getAxisName(int index) {
		Component axis = (Component) axes.get(index);
		
		return axis.getName();
	}

	/*
	 * @see org.lwjgl.input.Controller#getAxisValue(int)
	 */
	public float getAxisValue(int index) {
		return axesValue[index];
	}

	/*
	 * @see org.lwjgl.input.Controller#getXAxisValue()
	 */
	public float getXAxisValue() {
		if (xaxis == -1) {
			return 0;
		}
		
		return getAxisValue(xaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getYAxisValue()
	 */
	public float getYAxisValue() {
		if (yaxis == -1) {
			return 0;
		}
		
		return getAxisValue(yaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getXAxisDeadZone()
	 */
	public float getXAxisDeadZone() {
		if (xaxis == -1) {
			return 0;
		}
		
		return getDeadZone(xaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getYAxisDeadZone()
	 */
	public float getYAxisDeadZone() {
		if (yaxis == -1) {
			return 0;
		}
		
		return getDeadZone(yaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#setXAxisDeadZone(float)
	 */
	public void setXAxisDeadZone(float zone) {
		setDeadZone(xaxis,zone);
	}

	/*
	 * @see org.lwjgl.input.Controller#setYAxisDeadZone(float)
	 */
	public void setYAxisDeadZone(float zone) {
		setDeadZone(yaxis,zone);
	}

	/*
	 * @see org.lwjgl.input.Controller#getDeadZone(int)
	 */
	public float getDeadZone(int index) {
		return deadZones[index];
	}

	/*
	 * @see org.lwjgl.input.Controller#setDeadZone(int, float)
	 */
	public void setDeadZone(int index, float zone) {
		deadZones[index] = zone;
	}

	/*
	 * @see org.lwjgl.input.Controller#getZAxisValue()
	 */
	public float getZAxisValue() {
		if (zaxis == -1) {
			return 0;
		}
		
		return getAxisValue(zaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getZAxisDeadZone()
	 */
	public float getZAxisDeadZone() {
		if (zaxis == -1) {
			return 0;
		}
		
		return getDeadZone(zaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#setZAxisDeadZone(float)
	 */
	public void setZAxisDeadZone(float zone) {
		setDeadZone(zaxis,zone);
	}

	/*
	 * @see org.lwjgl.input.Controller#getRXAxisValue()
	 */
	public float getRXAxisValue() {
		if (rxaxis == -1) {
			return 0;
		}
		
		return getAxisValue(rxaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getRXAxisDeadZone()
	 */
	public float getRXAxisDeadZone() {
		if (rxaxis == -1) {
			return 0;
		}
		
		return getDeadZone(rxaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#setRXAxisDeadZone(float)
	 */
	public void setRXAxisDeadZone(float zone) {
		setDeadZone(rxaxis,zone);
	}

	/*
	 * @see org.lwjgl.input.Controller#getRYAxisValue()
	 */
	public float getRYAxisValue() {
		if (ryaxis == -1) {
			return 0;
		}
		
		return getAxisValue(ryaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getRYAxisDeadZone()
	 */
	public float getRYAxisDeadZone() {
		if (ryaxis == -1) {
			return 0;
		}
		
		return getDeadZone(ryaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#setRYAxisDeadZone(float)
	 */
	public void setRYAxisDeadZone(float zone) {
		setDeadZone(ryaxis,zone);
	}

	/*
	 * @see org.lwjgl.input.Controller#getRZAxisValue()
	 */
	public float getRZAxisValue() {
		if (rzaxis == -1) {
			return 0;
		}
		
		return getAxisValue(rzaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#getRZAxisDeadZone()
	 */
	public float getRZAxisDeadZone() {
		if (rzaxis == -1) {
			return 0;
		}
		
		return getDeadZone(rzaxis);
	}

	/*
	 * @see org.lwjgl.input.Controller#setRZAxisDeadZone(float)
	 */
	public void setRZAxisDeadZone(float zone) {
		setDeadZone(rzaxis,zone);
	}

	/*
	 * @see org.lwjgl.input.Controller#getPovX()
	 */
	public float getPovX() {
		if (pov.size() == 0) {
			return 0;
		}
		
		float value = povValues[0];
		
		if ((value == Component.POV.DOWN_LEFT) || 
		    (value == Component.POV.UP_LEFT) ||
		    (value == Component.POV.LEFT)) {
			return -1;
		}
		if ((value == Component.POV.DOWN_RIGHT) || 
		    (value == Component.POV.UP_RIGHT) ||
		    (value == Component.POV.RIGHT)) {
			return 1;
		}
		
		return 0;
	}

	/*
	 * @see org.lwjgl.input.Controller#getPovY()
	 */
	public float getPovY() {
		if (pov.size() == 0) {
			return 0;
		}
		
		float value = povValues[0];
		
		if ((value == Component.POV.DOWN_LEFT) || 
		    (value == Component.POV.DOWN_RIGHT) ||
		    (value == Component.POV.DOWN)) {
			return 1;
		}
		if ((value == Component.POV.UP_LEFT) || 
		    (value == Component.POV.UP_RIGHT) ||
		    (value == Component.POV.UP)) {
			return -1;
		}
		
		return 0;
	}
	
	
}
