package org.usfirst.frc.team1557.backupauto;

public class RoughTerrain extends AutoManager {
	//for @ 25%
	//for @ 50%
	//for @ 70%
	public void initialize(){
		super.initialize();
		stopTimes = new double[]		{0.25, 0.5, 5};
		leftThrottles = new double[]	{0.25, 0.50, 0.70};
		rightThrottles = new double[]	{0.25, 0.50, 0.70};
		armThrottles = new double[]		{0, 0, 0};
	}
}
