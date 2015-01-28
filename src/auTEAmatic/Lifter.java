package auTEAmatic;

import lejos.nxt.Motor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.TouchSensor;
import lejos.nxt.Button;
import java.util.Timer;
import java.util.TimerTask;

public class Lifter {
// This class handles the motor and related logic for the lifting mechanism
	
	// fields
	// speed in degrees per second, calibration defaults to 6 rotations
	private static int speed = 360, degrees = 2160, counter = 0;
	private static boolean first_run = true;
	private static TouchSensor UpperLimit = new TouchSensor(SensorPort.S1);
	private static Timer agitate = new Timer();
	
	// constructors
	
	// methods
	private static void move(boolean up)	{
		// control motor movement; general case
		Motor.A.setSpeed(speed);
		if(up)
			Motor.A.forward();
		else
			Motor.A.backward();
		return;
	}
	
	public static void returnToTop()	{
		move(true);
		while(true)	{
			if(UpperLimit.isPressed())	{
				stop();
				Motor.A.resetTachoCount();
				counter = 0;
				break;
			} else if(Motor.A.getTachoCount() >= 180 && first_run == false)	{
				stop();
				try {
					throw new NoButtonPressException();
				} catch (NoButtonPressException e) {
					LCD.clearDisplay();
					LCD.drawString("Limit Error:", 0, 2);
					LCD.drawString("fix and restart", 1, 3);
					LCD.drawString("Press Enter to", 0, 5);
					LCD.drawString("quit app.", 6, 6);
					Button.waitForAnyPress();
					java.lang.System.exit(1);
				}
			} else	{
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// Auto-generated catch block
					e.printStackTrace();
				}
				counter ++;
			}
		}
		if(first_run == true)
			first_run = false;
		return;
	}
	
	public static void moveDown()	{
		move(false);
		return;
	}
	
	public static void stop(){
		Motor.A.stop();
		return;
	}
	
	public static void calibrate()	{
		returnToTop();
		LCD.clearDisplay();
		LCD.drawString("Press a button", 0, 0);
		LCD.drawString("when lifter", 1, 1);
		LCD.drawString("reaches bottom", 2, 2);
		LCD.drawString("Press Enter", 0, 4);
		LCD.drawString("to continue", 5, 5);
		Button.ENTER.waitForPressAndRelease();
		LCD.drawString("Calibrating...", 0, 7);
		moveDown();
		Button.waitForAnyPress();
		stop();
		degrees = Motor.A.getTachoCount();
		LCD.clear(7);
		LCD.drawString("Calibrated!", 5, 7);
		returnToTop();
		return;
	}
	
	public static int getCalibrationDegrees()	{
		return degrees;
	}
	
	public static void agitate(int seconds)	{
		/* figure out how to agitate for duration seconds
		 * without interrupting the flow of the program;
		 * in other words, allow other processes to continue
		 * while the agitate is running.
		 * 
		 * Use the scheduling timer possibly?
		 * 
		 * Also, since this class handles the lifter,
		 * should I make a dedicated agitator class?
		 */
	}
}
