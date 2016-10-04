package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlPistonArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.FireCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.GyroTurnCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 * An autonomous routine that uses timed movements <b>TESTED</b> Lowers the arm,
 * drives forwards, and then retreats slightly.
 */
public class TimedAuto extends CommandGroup {

	public TimedAuto() {
		// addSequential(new AssistedDriveCommand(0.5, 1, 0));
		// addSequential(new DriveCommand(0.5, 0.6));
		// addSequential(new AssistedDriveCommand(0.5, 1.2, 0));
		// addSequential(new GyroTurnCommand(27.6));
		// addSequential(new AssistedDriveCommand(1, .75, 27.6));
		// addSequential(new DriveCommand(0.5, .34));
		// addSequential(new FireCommand());
		// addSequential(new DriveCommand(-0.5, .34));
		// addSequential(new AssistedDriveCommand(-1, .75, 27.6));
		// addSequential(new GyroTurnCommand(180));
		// addSequential(new AssistedDriveCommand(0.5, 1.2, 180));

		// this is a temporary custom drive which may or may not work
		addSequential(new Command() {
			{
				requires(Robot.climbPiston);
			}

			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			protected void interrupted() {
				// TODO Auto-generated method stub

			}

			@Override
			protected void initialize() {

			}

			@Override
			protected void execute() {
				// Or maybe it should be set to kOff?
				Robot.climbPiston.setProdigious(Value.kReverse);

			}

			@Override
			protected void end() {
				// TODO Auto-generated method stub

			}
		}, 1);
		addSequential(new ControlArmCommand(.75, 1));
		// addSequential(new WaitCommand(2));
		// Lower ICBMs!
		addSequential(new ControlPistonArmCommand(true, 1.5));

		addSequential(new DriveCommand(0.61, 0.61, 2.5));
		// addSequential(new DriveCommand(-0.61, -0.61, 0.25));
		addSequential(new GyroTurnCommand(45, 5), 5);
		addSequential(new DriveCommand(0.61, 0.61, 1));
		// addSequential(new DriveCommand(0.5, 0.6));
		// addSequential(new DriveCommand(0.5, 1.2));
		// addSequential(new GyroTurnCommand(27.6));
		// addSequential(new DriveCommand(1, .75));
		// addSequential(new DriveCommand(0.5, .34));
		// addSequential(new FireCommand());
		// addSequential(new DriveCommand(-0.5, .34));
		// addSequential(new DriveCommand(-1, .75));
		// addSequential(new GyroTurnCommand(180));
		// addSequential(new DriveCommand(0.5, 1.2));

	}
}
