package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.autonoms.commands.ControlArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlPistonArmCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class SandwichCommand extends CommandGroup {

	public SandwichCommand() {
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
		System.out.println("Scheduled Sandwiches to be ordered for lunch.");
		addSequential(new ControlArmCommand(0.5, 1));
		addSequential(new ControlPistonArmCommand(true, 2.0));
		addSequential(new ControlArmCommand(-0.5, 0.8));

	}

	@Override
	public String toString() {
		return "Sandwich Auto";
	}
}
