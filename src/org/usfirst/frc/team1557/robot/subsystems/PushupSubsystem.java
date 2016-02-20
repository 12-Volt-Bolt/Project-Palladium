package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.utils.SolenoidGroup;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PushupSubsystem extends Subsystem {

	public SolenoidGroup pushupGroup;
	public long timeSinceLastUsed;

	public PushupSubsystem() {
		// Magic numbers
		pushupGroup = new SolenoidGroup(new Solenoid(RobotMap.PCM_ID, RobotMap.SolenoidId.PUSHUP_ONE.getId()),
				new Solenoid(RobotMap.PCM_ID, RobotMap.SolenoidId.PUSHUP_TWO.getId()));
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void set(boolean state) {
		pushupGroup.set(state);
		timeSinceLastUsed = System.currentTimeMillis();
	}
}
