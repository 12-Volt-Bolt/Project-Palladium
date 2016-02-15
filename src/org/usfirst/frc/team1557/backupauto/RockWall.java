package org.usfirst.frc.team1557.backupauto;

public class RockWall extends AutoManager {
	//forward @ 25%
	//forward @ 50%
	//forward @ 85%
	public void initialize(){
		super.initialize();
		stopTimes = new double[]		{0.25, 0.5, 4.5};
		leftThrottles = new double[]	{0.25, 0.50, 0.85};
		rightThrottles = new double[]	{0.25, 0.50, 0.85};
		armThrottles = new double[]		{0, 0, 0};
	}
}
