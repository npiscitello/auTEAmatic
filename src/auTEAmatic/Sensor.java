package auTEAmatic;

import lejos.nxt.SensorPort;

public class Sensor {
// This class handles all sensors used by the robot
	
	// fields - composed mostly of sensor values. Object creation is included here.
	private static int tempRaw;
	private static float tempAdjusted;
	
	// constructors - not needed, as objects of this class won't be necessary
	
	// methods - mostly returning sensor values and threshold decisions
	public static float getTemp()	{
		// read raw value and adjust to Fahrenheit (or Celsius) - possibly add averaging if needed
		tempRaw = SensorPort.S2.readRawValue();
		// tempAdjusted = ? --> run the numbers <--
		return tempAdjusted;
	}
	
	public void waitUntilInTea()	{
		// read temperature probe spike - returns when the probe hits the water
		while(true)	{
			tempRaw = SensorPort.S2.readRawValue();
			try {
				Thread.sleep(100);
			}
			catch (InterruptedException e) {
				// Auto-generated catch block
				e.printStackTrace();
			}
			// break loop if new sensor value is at least 25% higher than the old one
			if(SensorPort.S2.readRawValue() >= tempRaw + (tempRaw * 0.25))
			break;
		}
		return;
	}
}
