package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.commands.SetIntakeDown;
import org.usfirst.frc.team1557.robot.commands.SetIntakeUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick mainJoyOne = new Joystick(0);
	public static Joystick mainJoyTwo = new Joystick(1);
	public static Joystick altJoyOne = new Joystick(2);
	public static JoystickButton intakeArmToggle = new JoystickButton(altJoyOne, 2);
	public static JoystickButton openButton = new JoystickButton(altJoyOne, 2);
	public static JoystickButton closeButton = new JoystickButton(altJoyOne, 1);
	public static JoystickButton climbButton = new JoystickButton(altJoyOne, RobotMap.CLIMB_BUTTON_ID);

	public OI() {

	}
}
