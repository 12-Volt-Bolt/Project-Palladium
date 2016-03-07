package org.usfirst.frc.team1557.robot.commands;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.vision.OpenCVVision;
import org.usfirst.frc.team1557.robot.vision.VisionInterface;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

public class TrackCommand extends Command {
	VisionInterface vision;
	PIDController gyroPID;
	double pidOutput = 0;
	private double degreesOnTarget = 5;

	public TrackCommand(int timeout) {
		this.setTimeout(timeout);
	}

	@Override
	protected void initialize() {
		requires(Robot.drive);
		vision = new OpenCVVision();
		vision.initCamera("ADDRESS_GOES_HERE");
		vision.startProcessing();
		gyroPID = new PIDController(0, 0, 0, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				pidOutput = output;
			}
		});
		gyroPID.enable();
	}

	@Override
	protected void execute() {
		gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + vision.getAngle());
		// One side needs to be negative. Not sure which yet.
		Robot.drive.tankDrive(pidOutput, -pidOutput);
	}

	@Override
	protected boolean isFinished() {
		return this.isTimedOut()
				|| gyroPID.getError() < degreesOnTarget /*
														 * || if the button is
														 * not held down
														 */;
	}

	@Override
	protected void end() {
		Robot.drive.tankDrive(0, 0);
		gyroPID.disable();
		vision.stopProcessing();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
