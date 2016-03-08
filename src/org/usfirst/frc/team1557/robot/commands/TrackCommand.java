package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.vision.OpenCVVision;
import org.usfirst.frc.team1557.robot.vision.VisionInterface;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class TrackCommand {
	VisionInterface vision;
	PIDController gyroPID;
	double pidOutput = 0;
	private double degreesOnTarget = 5;

	public void initialize() {
		vision = new OpenCVVision();
		vision.initCamera("ADDRESS_GOES_HERE");
		gyroPID = new PIDController(0, 0, 0, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				pidOutput = output;
			}
		});
	}

	public void run() {
		gyroPID.enable();
		vision.startProcessing();
		gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + vision.getAngle());
		// One side needs to be negative. Not sure which yet.
		Robot.drive.tankDrive(pidOutput, -pidOutput);
	}

	public void notRunning() {
		gyroPID.disable();
		vision.stopProcessing();
	}
}
