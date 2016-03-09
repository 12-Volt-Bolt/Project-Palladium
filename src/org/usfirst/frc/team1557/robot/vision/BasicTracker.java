package org.usfirst.frc.team1557.robot.vision;

import org.usfirst.frc.team1557.robot.Robot;

public class BasicTracker implements TrackInterface {
	VisionInterface vision;
	private double count = 0.d;
	private double lastAngle = 0;

	@Override
	public void initialize() {
		vision = Robot.vision;
		vision.initCamera("ADDRESS_GOES_HERE");
	}

	@Override
	public void run() {
		vision.startProcessing();
		double output = getOutput();
		// TODO: Again, one side needs to be negative. Not sure which yet.
		Robot.drive.tankDrive(output, -output);
	}

	@Override
	public void stopRunning() {
		vision.stopProcessing();
	}

	double throttle = 0;

	private double getOutput() {
		boolean reverse = (vision.getAngle() < 0) ? true : false;
		if (reverse)
			throttle = -throttle;
		if (throttle < 0)
			throttle = 0;
		throttle += 0.33d * 0.05;
		if (reverse)
			throttle = -throttle;
		return throttle;
	}
}
