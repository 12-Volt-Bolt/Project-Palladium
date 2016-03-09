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

	private double getOutput() {
		double newAngle = vision.getAngle();
		if (Math.signum(lastAngle) != Math.signum(newAngle)) {
			count = 0;
		}
		lastAngle = newAngle;
		if (count < 500 * 3) {
			count++;
		}
		return (count / (500d * 3)) * Math.signum(newAngle);
	}
}
