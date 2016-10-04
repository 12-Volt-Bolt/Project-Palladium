package org.usfirst.frc.team1557.robot.autonoms;

import org.usfirst.frc.team1557.robot.Robot;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlPistonArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class TimedAutoDefense extends CommandGroup {
	public TimedAutoDefense() {
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

		addSequential(new ControlArmCommand(-.75, 1));

		addSequential(new DriveCommand(0.61, 0.61, 2.5));
		addSequential(new DriveCommand(-0.61, -0.61, 0.25));
	}
}
