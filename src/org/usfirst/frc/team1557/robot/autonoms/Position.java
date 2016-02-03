package org.usfirst.frc.team1557.robot.autonoms;

public enum Position {
	LEFT(0), LEFTMIDDLE(1), MIDDLE(2), RIGHTMIDDLE(3), RIGHT(4);
	int num;

	Position(int i) {
		num = i;
	}

	int getNumber() {
		return num;
	}
}
