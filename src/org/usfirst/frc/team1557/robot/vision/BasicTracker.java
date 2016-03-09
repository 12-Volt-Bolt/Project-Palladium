package org.usfirst.frc.team1557.robot.vision;

import org.usfirst.frc.team1557.robot.Robot;

public class BasicTracker implements TrackInterface {
	VisionInterface vision;
	private boolean hasInitialize = false;

	@Override
	public void initialize() {
		if (!hasInitialize) {
			vision = Robot.vision;
			vision.initCamera(VisionInterface.URL);
			hasInitialize = true;
		}
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
		throttle += 0.33d * 0.02;
		if (reverse)
			throttle = -throttle;
		return throttle;
	}
}
