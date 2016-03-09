package org.usfirst.frc.team1557.robot.vision;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class GyroTracker implements TrackInterface {
	VisionInterface vision;
	PIDController gyroPID;
	double pidOutput = 0;
	private boolean hasSetSetPoint = false;

	@Override
	public void initialize() {
		vision = Robot.vision;
		vision.initCamera("ADDRESS_GOES_HERE");
		gyroPID = new PIDController(0.05, 0, 0, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				pidOutput = output;
			}
		});
	}

	@Override
	public void run() {
		gyroPID.enable();
		vision.startProcessing();	
		if (!hasSetSetPoint) {
			gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + vision.getAngle());
			hasSetSetPoint = true;
		}
		//TODO: One side needs to be negative. Not sure which yet.
		Robot.drive.tankDrive(pidOutput, -pidOutput);
	}

	@Override
	public void stopRunning() {
		gyroPID.disable();
		vision.stopProcessing();
		hasSetSetPoint = false;
	}
}
