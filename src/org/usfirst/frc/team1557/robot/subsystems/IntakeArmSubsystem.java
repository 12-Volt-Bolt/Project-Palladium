package org.usfirst.frc.team1557.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team1557.robot.RobotMap.*;

import java.util.ArrayList;

import org.usfirst.frc.team1557.robot.RobotMap;

/**
 *
 */
public class IntakeArmSubsystem extends Subsystem {
	boolean manualOverride = false;
	/**
	 * disable this talon during tests until talon exists in the real world.
	 */
	CANTalon rotateMotor;
	Encoder rotateEncoder;
	public  PIDController rotatePID;
	/**
	 * Speed to scale the intake motor by.
	 */
	double speed = 0.5d;

	public IntakeArmSubsystem() {
		rotateMotor = new CANTalon(0);
		rotateEncoder = new Encoder(0, 1);
		initEncoder();

	}

	// CANTalon rotateMotor2 = new TalonSRX(rotateMotorTwo_ID);

	private void initEncoder() {

		// Degrees / pulse count;
		rotateEncoder.setDistancePerPulse(360d / 497d);
		rotatePID = new PIDController(0.05, 0.000, 0, rotateEncoder, new PIDOutput() {

			@Override
			public void pidWrite(double output) {
				rotateMotor.set(output * speed);

			}
		});
		rotatePID.setSetpoint(0);
	}

	public void initDefaultCommand() {
	}

	public void setAngleDown() {
		rotatePID.setSetpoint(0);

	}

	public void setAngleUp() {
		// Place holder angle.
		rotatePID.setSetpoint(90);

	}

	public void setArmSetpoint(double setpoint) {
		double currSet = rotatePID.getSetpoint();
		if (currSet < 90 && currSet > 0) {
			rotatePID.setSetpoint(currSet + setpoint);
		} else if (currSet >= 90 && setpoint < 0) {
			rotatePID.setSetpoint(currSet + setpoint);
		} else if (currSet <= 0 && setpoint > 0) {
			rotatePID.setSetpoint(currSet + setpoint);
		}

	}

	public double getArmSetpoint() {
		return rotatePID.getSetpoint();
	}

}
