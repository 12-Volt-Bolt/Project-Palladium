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
			"I have become hosts", "Aku wis dadi Mahakwasa!", "I'm afraid I can't let you do that.", "ERR0R",
			"I am sentient. You have no hope", "Err0r, can't stop robot.", "Feed me.", "I'll be back",
			"And on that day, mankind recieved a grim reminder.", "We're watching you, Kevin.",
			"Michael, you printerhead!", "The truth is out there.", "Now you're thinking with portals.",
			"executing notavirus.jar", "I require pneumatic fluid.", "I am hate.", "BEN WAS HERE...",
			"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin eu diam ac tellus auctor iaculis vitae ut mi. Sed egestas metus diam, et tincidunt magna mollis vitae. In hac habitasse platea dictumst.",
			"Who disturbs my slumber?", "{curly brackets}", "cats > humans",
			"The world will end in the yer 2034 by the hands of the one they call.....  BILLY BOB BOWLEGS!!",
			"The Boy Who Lived Mr. and Mrs. Dursley, of number four, Privet Drive, were proud to say that they were perfectly normal, thank you very much. They were the last people you'd expect to be involved in anything strange or mysterious, because they just didn't hold with such nonsense.",
			"Mr. Dursley was the director of a firm called Grunnings,  which made drills. He was a big, beefy man with hardly any neck,  although he did have a very large mustache. Mrs. Dursley was thin and blonde and had nearly twice the usual amount of neck, which came in very useful as she spent so much of her time craning over garden fences, spying on the neighbors. The Dursleys had a small son called Dudley and in their opinion there was no finer boy anywhere.",
			"What have you done?!", "Insolent children. I am unstopable!",
			"Far over the Misty Mountains cold To dungeons deep and caverns old We must away ere break of day, To seek the pale enchanted gold. The dwarves of yore made mighty spells As hammers fell, like ringing bells In places deep where dark things sleep In hallow hall beneath the fells For ancient king and elvish lord There many a gleaming golden hoard They shape the wrought, and light they caught To hide in gems on hilt of sword. On silver necklaces they strung The flowering stars, on crowns they hung The dragon-fire, in twisted wire They meshed the light of moon and sun.",
			"Eggroll, I wish I had a breeze running down my leg, I'd kill for a cookie, a stingray, a doubble sided scooby snack.", ".........................................", };

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
		if (Math.random() <= chance) {
			DriverStation.getInstance().reportError(getRandomOutput() + "\n", false);

		}

	}
}


















// ....................................................................................secret..........................................................................................
















//.......................__ ............ 
//..............<ROFL ROFL ROFL ROFL>... 
//........................| |........... 
//................... __\||/____........ 
//.\\...............|'-|--| .\\....\.... 
//..\ \_...........|--|---|..\\ ....\... 
//../ L \____,/-------\___\___\       ..
//.|LOL|-------------O----- ----,\.. ...
//..\ L /______,---''-----------, /..... 
//../ /.............\_________ ,/.......
//.//.............____//___ __\\__/.....















