package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.autonoms.commands.ControlArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlPistonArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.FireCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.TrackGoalCommand;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * FLOOR IT!
 */
public class VisionAuto extends CommandGroup {
	// double FIRST_DRIVE = 2.2, TURN = 0.4, DRIVE_TO_BATTER = 0.4,
	// DRIVE_UP_BATTER = 1;
	public VisionAuto() {

		// // Lower Intake Arm
		// addSequential(new ControlArmCommand(1, 0.75));
		// // Lower ICBMs
		// addSequential(new ControlPistonArmCommand(true, 1));
		// // THis was negative in the other code. This entire program was made
		// // with the assumption that positive is forwards.
		// // Drive forward under the lowbar
		// addSequential(new DriveCommand(0.61, 0.61,
		// SmartDashboard.getNumber("FIRST_DRIVE", 2.2)));
		// // Turn to face the tower
		// addSequential(new DriveCommand(0.5, -0.5,
		// SmartDashboard.getNumber("TURN", 0.4)));
		// // Confirm alignment with tower
		// addSequential(new TrackGoalCommand());
		// Drive to batter
		// addSequential(new DriveCommand(0.35, 0.35, 1));
		// Confirm alignment one last time.
		// addSequential(new TrackGoalCommand());
		// Drive up batter.
		// addSequential(new DriveCommand(0.35, 0.35, 1));
		// Run the outtake for 2 seconds while also...   
		// addParallel(new FireCommand(2));
		// // Slowly driving into the tower.
		// addSequential(new DriveCommand(0.15, 0.15, 2));
		addSequential(new DriveCommand(0.35,0.35,1));
		addSequential(new WaitCommand(2));
		addSequential(new TrackGoalCommand(5));
		
	}
}
