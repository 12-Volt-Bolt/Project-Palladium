package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GyroTurnCommand extends Command {
	double angle;

	/**
	 * result created while gyroPID runs
	 */
	double gyroOutput;

	PIDController gyroPID;

	/**
	 * Attempts to turn the robot by the given relative angle, within the given
	 * tolerance
	 * 
	 * @param angle
	 *            Angle in degrees
	 * @param tolerance
	 *            Tolerance in degrees
	 */
	public GyroTurnCommand(double angle, double tolerance) {
		requires(Robot.drive);

		this.angle = angle;
		this.setTimeout(5);

		gyroPID = new PIDController(0.05, 0, 0, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				gyroOutput = output;
			}
		});

		gyroPID.setAbsoluteTolerance(tolerance);
	}

	/**
	 * Creates a GyroTurnCommand with the given relative angle and a default
	 * tolerance of 10
	 * 
	 * @param angle
	 *            The angle to turn to
	 */
	public GyroTurnCommand(double angle) {
		this(angle, 10);
	}

	protected void initialize() {
		gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + angle);
		gyroPID.enable();
	}

	protected void execute() {
		// gyroTurn(angle);

		Robot.drive.tankDrive(-gyroOutput, gyroOutput);
	}

	protected boolean isFinished() {
		return isGyroOnTarget() || isTimedOut();
	}

	protected void end() {
		gyroPID.disable();
	}

	protected void interrupted() {
		end();
	}

	// public void gyroTankDrive(double rightSpeed, double leftSpeed, double
	// angle) {
	// if (gyroPID.onTarget()) {
	// gyroPID.disable();
	// Robot.drive.tankDrive(leftSpeed, rightSpeed);
	// } else if (!gyroPID.isEnabled()) {
	// gyroPID.enable();
	// }
	// }
	//
	// public void gyroTurn(double angle) {
	// gyroPID.setSetpoint(angle);
	// if (!gyroPID.isEnabled() && !gyroPID.onTarget()) {
	// gyroPID.enable();
	// }
	// }
	//
	private boolean isGyroOnTarget() {
		return gyroPID.onTarget();
	}
	//
	// public void disableGyroPID() {
	// gyroPID.disable();
	// }

}
