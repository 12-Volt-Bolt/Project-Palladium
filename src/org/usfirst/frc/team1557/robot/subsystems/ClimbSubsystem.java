package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.utils.SolenoidGroup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	// 2 Pistons per side
	// 4 Solenoids total.
	public long timeSinceLastUsed;
	public SolenoidGroup prodigious;
	//public SolenoidGroup infinitesimal;

	public ClimbSubsystem() {
		prodigious = new SolenoidGroup(new Solenoid(0, 0), new Solenoid(0, 1));
		//infinitesimal = new SolenoidGroup(new Solenoid(0, 2), new Solenoid(0, 3));
	}

	public void initDefaultCommand() {
	}

	public void setProdigious(boolean state) {
		prodigious.set(state);
		timeSinceLastUsed = System.currentTimeMillis();
	}

//	public void setInfinitesimal(boolean state) {
		//infinitesimal.set(state);
//		timeSinceLastUsed = System.currentTimeMillis();
//	}

}
