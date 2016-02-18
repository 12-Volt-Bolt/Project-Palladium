package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.utils.SolenoidGroup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbPistonSubsystem extends Subsystem {

	public long timeSinceLastUsed;
	/**
	 * Two pistons total. One per side.
	 */
	public SolenoidGroup prodigious;

	public ClimbPistonSubsystem() {
		prodigious = new SolenoidGroup(new Solenoid(0, 0), new Solenoid(0, 1));
	}

	public void initDefaultCommand() {
	}

	public void setProdigious(boolean state) {
		prodigious.set(state);
		timeSinceLastUsed = System.currentTimeMillis();
	}

}