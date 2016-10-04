package org.usfirst.frc.team1557.robot.autonoms.commands;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	public GyroTurnCommand(double angle, double tolerance, double feedforward) {
		requires(Robot.drive);

		this.angle = angle;
		this.setTimeout(5);

		gyroPID = new PIDController(0.02, 0, 0, feedforward, Robot.drive.gyro, new PIDOutput() {
			@Override
			public void pidWrite(double output) {
				gyroOutput = output;
			}
		});

		gyroPID.setAbsoluteTolerance(tolerance);

		SmartDashboard.putData("gyroid", gyroPID);
	}

	public GyroTurnCommand(double angle, double tolerance) {
		this(angle, tolerance, 0d);
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
		System.err.println("Initializing " + this);

		gyroPID.setSetpoint(Robot.drive.gyro.getAngle() + angle);
		gyroPID.enable();
	}

	@Override
	public String toString() {
		return "GyroTurnCommand [angle=" + angle + ", gyroOutput=" + gyroOutput + "]";
	}

	protected void execute() {
		// gyroTurn(angle);

		SmartDashboard.putNumber("gyro_value", Robot.drive.gyro.getAngle());

		Robot.drive.tankDrive(gyroOutput, -gyroOutput);
	}

	protected boolean isFinished() {
		// return false;
		return isGyroOnTarget() || isTimedOut();
	}

	protected void end() {
		gyroPID.reset();
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
