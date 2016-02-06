package org.usfirst.frc.team1557.robot.autonoms.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class DriveInAPolygonCommand extends CommandGroup {
	/**
	 * Creates a command which drives in a polygon with the given number of sides.
	 * 
	 * @param boxSize
	 *            The side of one side of the polygon, in inches
	 */
	public DriveInAPolygonCommand(int sideCount, double sideLength) {
		// add (sideCount) sets of movement and turns
		for (int i = 0; i < sideCount; i++) {
			this.addSequential(new GyroTurnCommand(360 / sideCount));
			this.addSequential(new WaitCommand(0.5));
			this.addSequential(new DriveDistanceAtAngleCommand(sideLength, 0, 0.5));
			this.addSequential(new WaitCommand(0.5));
		}
	}
}
