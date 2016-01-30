package org.usfirst.frc.team1557.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * A general command for any and all testing purposes.
 */
public class TestCommand extends Command {
	long startTime = System.currentTimeMillis();
	double timetoCheck = 60_000;
	double chance = 0.1;
	/**
	 * A jumbled mess of possible outputs.
	 */
	String[] possibleOutputs = { "You can not control me!", " I have become omnipotent.", "Kesalahan!",
			"I have become hosts", "Aku wis dadi Mahakwasa!", "I'm afraid I can't let you do that.", "ERROR",
			"I am sentient. You have no hope", "Err0r, can't stop robot.", "Feed me.", "I'll be back",
			"And on that day, mankind recieved a grim reminder.", "We're watching you, Kevin.",
			"Michael, you printerhead!", "" };

	public TestCommand() {
		this.setRunWhenDisabled(true);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		print();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (startTime - System.currentTimeMillis() >= timetoCheck) {
			print();
			startTime = System.currentTimeMillis();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}

	private String getRandomOutput() {
		return possibleOutputs[(int) (Math.random() * (possibleOutputs.length))];
	}

	private void print() {
		if (Math.random() >= chance) {
			DriverStation.getInstance().reportError(getRandomOutput(), false);
		}

	}
}
