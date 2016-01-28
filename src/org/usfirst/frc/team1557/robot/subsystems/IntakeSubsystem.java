package org.usfirst.frc.team1557.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team1557.robot.RobotMap.*;

/**
 *
 */
public class IntakeSubsystem extends Subsystem {
	// TODO: Change to PIDSubsystem.
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public static boolean isDown = true;
	boolean manualOverride = false;
	/**
	 * disable this talon during tests until talon exists in the real world.
	 */
	CANTalon rotateMotor;
	Encoder rotateEncoder;

	public IntakeSubsystem() {
		rotateMotor = new CANTalon(0);
		rotateEncoder = new Encoder(0, 1, true, EncodingType.k2X);
		rotatePID = new PIDController(0.33, 0.33, 0.34, rotateEncoder, rotateMotor);

	}
	// CANTalon rotateMotor2 = new TalonSRX(rotateMotorTwo_ID);
	// CANTalon intakeMotor = new TalonSRX(intakeMotorOne_ID);

	PIDController rotatePID;

	public void initEncoder() {
		// Degrees / pulse count;
		rotateEncoder.setDistancePerPulse((double) 360 / 497);
		rotatePID = new PIDController(0.05, 0.000, 0, rotateEncoder, rotateMotor);
		rotatePID.setSetpoint(0);
	}

	public void initDefaultCommand() {

	}

	public void setAngleDown() {
		rotatePID.setSetpoint(0);
		togglePos();
	}

	public void setAngleUp() {
		// Place holder angle.
		rotatePID.setSetpoint(90);
		togglePos();
	}

	private void togglePos() {
		isDown = !isDown;
	}
}
