package org.usfirst.frc.team1557.robot.autonoms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VariableAuto extends CommandGroup {
	Position robotPosititon;
	double angleAfterBreach;
	double distanceAfterBreach;
	boolean goToRightSide;
	Defense defense;

	public VariableAuto() {

	}

	@Override
	public void execute() {
		switch (robotPosititon) {
		case LEFT:
			if (goToRightSide) {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			} else {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			}
			break;
		case LEFTMIDDLE:
			if (goToRightSide) {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			} else {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			}
			angleAfterBreach = 0;
			distanceAfterBreach = 0;
			break;
		case MIDDLE:
			if (goToRightSide) {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			} else {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			}
			break;
		case RIGHT:
			if (goToRightSide) {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			} else {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			}
			break;
		case RIGHTMIDDLE:
			if (goToRightSide) {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			} else {
				angleAfterBreach = 0;
				distanceAfterBreach = 0;
			}
			break;
		default:
			break;

		}

	}
}
