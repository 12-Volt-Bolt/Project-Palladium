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
		rightProdigious.set(false);
		leftProdigious.set(false);
		rightInfitesimal.set(false);
		leftInfitesimal.set(false);

	}

	public void initDefaultCommand() {
		// Set the default and for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void toggleInfitesimal() {
		rightInfitesimal.set(!rightInfitesimal.get());
		leftInfitesimal.set(leftInfitesimal.get());

	}

	public void toggleProdigious() {
		rightProdigious.set(!rightProdigious.get());
		leftProdigious.set(!leftProdigious.get());
	}

	public boolean getProdigious() {
		return rightProdigious.get();
	}

	public boolean getInfitesimal() {
		return rightInfitesimal.get();
	}

}
