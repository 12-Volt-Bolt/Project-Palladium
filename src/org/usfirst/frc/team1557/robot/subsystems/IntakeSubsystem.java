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
	boolean manualOverride = false;
	/**
	 * disable this talon durings tests until talon exists in the real world.
	 */
	//CANTalon rotateMotor = new CANTalon(rotateMotorOne_ID);
	//CANTalon rotateMotor2 = new TalonSRX(rotateMotorTwo_ID);
	//CANTalon intakeMotor = new TalonSRX(intakeMotorOne_ID);
	//Encoder rotateEncoder = new Encoder(rotateEncoderA_ID, rotateEncoderB_ID, true, EncodingType.k2X);
//	PIDController rotateController = new PIDController(0.33, 0.33, 0.34, rotateEncoder,rotateMotor);
	public void initEncoder(){
	//	rotateEncoder.setDistancePerPulse(51.42);
	
	}
	public void initDefaultCommand() {
		
	}

	public void setAngleUp() {
		
		// Arbitrary number: more testing and research is required.
	//	while (rotateEncoder.get() < 9001) {
			// Speed should depend on distance from goal which is acquired
			// through the use of a PID loop/subsystem
		//	rotateMotor.set(1);
	//		return;
	//	}
	
	}

	public void setAngleDown() {
	
		// Arbitrary number: more testing and research is required.
	//	while (rotateEncoder.get() > -9001) {
//			// Speed should depend on distance from goal which is acquired
			// through the use of a PID loop/subsystem
			
			//rotateMotor.set(-1);
	//		return;
	//	}
	
	}

	public void setAngleWithJoystick(double d) {
		
		//	rotateMotor.set(d);
		
	}
}
