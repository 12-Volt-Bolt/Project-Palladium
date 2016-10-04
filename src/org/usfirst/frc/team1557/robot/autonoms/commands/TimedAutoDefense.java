package org.usfirst.frc.team1557.robot.autonoms.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutoDefense extends CommandGroup {
	public TimedAutoDefense() {
		addSequential(new ControlArmCommand(.75, 1));
		// addSequential(new WaitCommand(2));
		// Lower ICBMs!
		addSequential(new ControlPistonArmCommand(true, 1.5));

		addSequential(new ControlArmCommand(-.75, 1));

		addSequential(new DriveCommand(0.61, 0.61, 2.5));
		addSequential(new DriveCommand(-0.61, -0.61, 0.25));
	}
}
