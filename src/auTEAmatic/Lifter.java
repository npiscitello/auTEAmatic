package auTEAmatic;

import lejos.nxt.Motor;
import java.util.Timer;
import java.util.TimerTask;

public class Lifter {
	// This class handles the motor and related logic for the lifting mechanism
	
	// fields
	private static int power = 360;
	private static boolean direction;
	Timer agitate = new Timer();
	
	// constructors
	
	// methods
	private void move(boolean up)	{
		// control motor movement; general case
		Motor.A.setSpeed(power);
		if(up)
			Motor.A.forward();
		else
			Motor.A.backward();
		return;
	}
	
	public void moveUp()	{
		move(true);
		return;
	}
	
	public void moveDown()	{
		move(false);
		return;
	}
	
	// agitate.scheduleAtFixedRate()
	
	public void agitate(int seconds)	{
		/* figure out how to agitate for duration seconds
		 * without interrupting the flow of the program;
		 * in other words, allow other processes to continue
		 * while the agitate is running.
		 * 
		 * Use the scheduling timer possibly?
		 */
	}
}
