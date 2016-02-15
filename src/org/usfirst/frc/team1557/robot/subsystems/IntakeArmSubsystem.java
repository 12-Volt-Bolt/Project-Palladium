package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeArmSubsystem extends Subsystem {
	boolean manualOverride = false;
	/**
	 * disable this talon during tests until talon exists in the real world.
	 */
	CANTalon rotateMotor;
	/**
	 * Speed to scale the intake motor by.
	 */
	double speed = 0.33d;

	public IntakeArmSubsystem() {
		rotateMotor = new CANTalon(RobotMap.MotorId.ROTATE_ONE.getId());

	}

	// CANTalon rotateMotor2 = new TalonSRX(rotateMotorTwo_ID);

	public void initDefaultCommand() {
		setDefaultCommand(new ControlIntakeArmCommand());
	}

	public void set(double output) {
		rotateMotor.set(output * speed);
	}
}
