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
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;

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
