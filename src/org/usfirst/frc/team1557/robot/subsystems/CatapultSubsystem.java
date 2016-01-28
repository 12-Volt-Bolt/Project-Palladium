package org.usfirst.frc.team1557.robot.subsystems;

import static org.usfirst.frc.team1557.robot.RobotMap.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CatapultSubsystem extends Subsystem {
	Solenoid solenoidLeft;
	Solenoid solenoidRight;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public CatapultSubsystem() {
		solenoidLeft = new Solenoid(CATAPULT_SOLENOID_ONE_MODULE_ID, CATAPULT_SOLENOID_ONE_CHANNEL_ID);
		solenoidRight = new Solenoid(CATAPULT_SOLENOID_TWO_MODULE_ID, CATAPULT_SOLENOID_TWO_CHANNEL_ID);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void set(boolean state) {
		solenoidLeft.set(state);
		solenoidRight.set(state);
	}
}
