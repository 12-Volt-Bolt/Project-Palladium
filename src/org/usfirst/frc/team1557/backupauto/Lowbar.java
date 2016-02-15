package org.usfirst.frc.team1557.backupauto;

public class Lowbar extends AutoManager {
	// lower arms & for @ 25%
	// drive forward @ 40%
	public void initialize(){
		super.initialize();
		stopTimes = new double[]		{0.5, 6.5};
		leftThrottles = new double[]	{0.25, 0.40};
		rightThrottles = new double[]	{0.25, 0.40};
		armThrottles = new double[]		{0.2, 0};
	}
}
