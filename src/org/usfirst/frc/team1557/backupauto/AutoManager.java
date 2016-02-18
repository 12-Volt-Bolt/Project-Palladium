package org.usfirst.frc.team1557.backupauto;

import org.usfirst.frc.team1557.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AutoManager extends Command {

	public double[] stopTimes;
	public double[] leftThrottles;
	public double[] rightThrottles;
	public double[] armThrottles;
	public long startTime;

	public AutoManager() {
		requires(Robot.drive);
		requires(Robot.intakeArm);
	}
	
	@Override
	public void execute() {
		int i = findCurrentIndex();

		if (i == -1) {
			setMotors(0, 0, 0);
		} else {
			setMotors(leftThrottles[i], rightThrottles[i], armThrottles[i]);
		}
	}

	@Override
	public void initialize() {
		startTime = System.currentTimeMillis();
	}

	public int findCurrentIndex() {
		for (int i = 0; i < stopTimes.length; i++) {
			if (getTimer() < stopTimes[i]) {
				return i;
			}
		}
		return -1;
	}

	public double getTimer() {
		return (System.currentTimeMillis() - startTime) / 1000.0;
	}

	public void setMotors(double left, double right, double arm) {
		Robot.drive.tankDrive(left, right);
		Robot.intakeArm.set(arm);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}
}
