package auTEAmatic;

import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;

public class Sensor {
// This class handles all sensors used by the robot
	
	// fields - composed mostly of sensor values. Object creation is included here.
	private int tempRaw;
	private float tempAdjusted, tempMultiplier;
	private boolean inTea;
	TouchSensor conductivity = new TouchSensor(SensorPort.S1);
	
	// constructors - not needed, as objects of this class won't be necessary
	
	// methods - mostly returning sensor values and threshold decisions
	public float getTemp()	{
		// read raw value and adjust to Fahrenheit (or Celsius)
		tempRaw = SensorPort.S2.readRawValue();
		// tempAdjusted = ? --> run the numbers <--
		return tempAdjusted;
	}
	
	public boolean isInTea()	{
		// read conductivity sensor
		inTea = conductivity.isPressed();
		return inTea;
	}
}
