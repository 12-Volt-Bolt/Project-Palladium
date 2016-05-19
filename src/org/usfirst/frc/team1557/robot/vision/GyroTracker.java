package org.usfirst.frc.team1557.robot.vision;

import static org.usfirst.frc.team1557.robot.RobotMap.MAIN_JOY_AXIS_ONE_ID;
import static org.usfirst.frc.team1557.robot.RobotMap.MAIN_JOY_AXIS_TWO_ID;

import org.usfirst.frc.team1557.robot.OI;
import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GyroTracker implements TrackInterface {
	VisionInterface vision;
	PIDController gyroPID;
	double pidOutput = 0;
	private boolean hasSetSetpoint = false;
	private boolean hasInitialize = false;
	private long initTime = 0;
	private int timeToWait = 400;

	@Override
	public void initialize() {
		vision = Robot.vision;
		vision.initCamera(VisionInterface.URL);
		if (!hasInitialize) {
			gyroPID = new PIDController(0.1, 0, 0, Robot.drive.gyro, new PIDOutput() {
				@Override
				public void pidWrite(double output) {
					pidOutput = output;
				}
			});
			hasInitialize = true;
			SmartDashboard.putData("GyroTrackerPID", gyroPID);
			SmartDashboard.putNumber("timeToWait", timeToWait);
		}
		gyroPID.setSetpoint(0);
	}

	@Override
	public void run() {
		timeToWait = (int) SmartDashboard.getNumber("timeToWait");
		vision.startProcessing();
		setSetpoint();
		// TODO: One side needs to be negative. Not sure which yet.
		Robot.drive.tankDrive(pidOutput + OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_ONE_ID),
				-pidOutput + OI.mainJoyOne.getRawAxis(MAIN_JOY_AXIS_TWO_ID));
	}

	private void setSetpoint() {
		if (!hasSetSetpoint && System.currentTimeMillis() - initTime > timeToWait/* 1_000 */) {
			gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + vision.getAngle());
			gyroPID.enable();
			hasSetSetpoint = true;
			// initTime = System.currentTimeMillis();
		}
	}

	@Override
	public void stopRunning() {
		initTime = System.currentTimeMillis();
		gyroPID.reset();
		vision.stopProcessing();
		hasSetSetpoint = false;
	}
}
