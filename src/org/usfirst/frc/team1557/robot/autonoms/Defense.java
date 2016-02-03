package org.usfirst.frc.team1557.robot.autonoms;

public enum Defense {
	PORTCULLIS('a'), CHEVAL_DE_FRISE('a'), MOAT('b'), RAMPART('b'), DRAWBRIDGE('c'), SALLY_PORT('c'), ROCK_WALL(
			'd'), ROUGH_TERRAIN('d'), LOWBAR('z');
	Character group;

	Defense(Character c) {
		group = c;
	}

	public Character getGroup() {
		return group;
	}
}
