package org.usfirst.frc.team1557.robot.vision;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BasicTracker implements TrackInterface {
	VisionInterface vision;
	private boolean hasInitialize = false;
	private double rampRate = 0.2d;
	double throttle = 0;

	@Override
	public void initialize() {
		vision = Robot.vision;
		vision.initCamera(VisionInterface.URL);
		hasInitialize = true;
		SmartDashboard.putNumber("rampRate", rampRate);

	}

	@Override
	public void run() {
		rampRate = SmartDashboard.getNumber("rampRate", rampRate);
		vision.startProcessing();
		double output = getOutput();
		// TODO: Again, one side needs to be negative. Not sure which yet.
		Robot.drive.tankDrive(output, -output);
	}

	@Override
	public void stopRunning() {
		vision.stopProcessing();
		throttle = 0;
	}

	private double getOutput() {
		boolean reverse = (vision.getAngle() < 0) ? true : false;
		if (reverse)
			throttle = -throttle;
		if (throttle < 0)
			throttle = 0.35;
		throttle += rampRate * 0.02;
		if (reverse)
			throttle = -throttle;
		if (vision.getAngle() == 0.0)
			throttle = 0;
		return throttle;
	}
}
