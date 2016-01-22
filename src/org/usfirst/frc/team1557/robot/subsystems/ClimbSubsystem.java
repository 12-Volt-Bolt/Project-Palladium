package org.usfirst.frc.team1557.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbSubsystem extends Subsystem {
	// 2 Pistons per side
	// 4 Solenoids total.
	Solenoid rightProdigious;
	Solenoid leftProdigious;
	Solenoid rightInfitesimal;
	Solenoid leftInfitesimal;

	public ClimbSubsystem() {
		rightProdigious = new Solenoid(0, 0);
		leftProdigious = new Solenoid(0, 0);
		rightInfitesimal = new Solenoid(0, 0);
		leftInfitesimal = new Solenoid(0, 0);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void openInfitesimal() {
		rightInfitesimal.set(true);
		leftInfitesimal.set(true);

	}

	public void closeInfitesimal() {
		rightInfitesimal.set(false);
		leftInfitesimal.set(false);
	}
	public void closeProdigious() {
		rightProdigious.set(false);
		leftProdigious.set(false);
	}

	public void openProdigious() {
		rightProdigious.set(true);
		leftProdigious.set(true);
	}

}
