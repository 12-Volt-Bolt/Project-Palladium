package org.usfirst.frc.team1557.robot.autonoms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VariableAuto extends CommandGroup {
	Position robotPosititon;
	Position defensePosition;
	Defense defense;

	public VariableAuto() {

	}

	public void run(){
		switch(robotPosititon){
		case LEFT:
			break;
		case LEFTMIDDLE:
			break;
		case MIDDLE:
			break;
		case RIGHT:
			break;
		case RIGHTMIDDLE:
			break;
		default:
			break;
		
		}
	}
}
