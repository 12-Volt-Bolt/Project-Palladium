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
	Solenoid rightInfinitesimal;
	Solenoid leftInfinitesimal;

	public ClimbSubsystem() {
		rightProdigious = new Solenoid(0, 0);
		leftProdigious = new Solenoid(0, 0);
		rightInfinitesimal = new Solenoid(0, 0);
		leftInfinitesimal = new Solenoid(0, 0);
		rightProdigious.set(false);
		leftProdigious.set(false);
		rightInfinitesimal.set(false);
		leftInfinitesimal.set(false);

	}

	public void initDefaultCommand() {
		// Set the default and for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void toggleInfinitesimal() {
		rightInfinitesimal.set(!rightInfinitesimal.get());
		leftInfinitesimal.set(!leftInfinitesimal.get());

	}

	public void toggleProdigious() {
		rightProdigious.set(!rightProdigious.get());
		leftProdigious.set(!leftProdigious.get());
	}

	/**
	 * 
	 * @return The state of the right prodigious
	 */
	public boolean getProdigious() {
		return rightProdigious.get();
	}

	/**
	 * 
	 * @return The state of the right infinitesimal.
	 */
	public boolean getInfinitesimal() {
		return rightInfinitesimal.get();
	}

}
