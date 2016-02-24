package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.RobotMap.ButtonId;
import org.usfirst.frc.team1557.robot.commands.LiftClimbCommand;
import org.usfirst.frc.team1557.robot.commands.PushupCommand;
import org.usfirst.frc.team1557.robot.commands.ToggleClimbPistonCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	public static Joystick mainJoyOne = new Joystick(0);
	public static Joystick mainJoyTwo = new Joystick(1);
	public static Joystick altJoyOne = new Joystick(2);
	public static JoystickButton intakeWheelButton = new JoystickButton(altJoyOne, ButtonId.INTAKE_WHEEL.getId());
	public static JoystickButton outtakeWheelButton = new JoystickButton(altJoyOne, ButtonId.OUTTAKE_WHEEL.getId());
	public static JoystickButton liftClimbUpButton = new JoystickButton(altJoyOne, ButtonId.LIFT_CLIMB_UP.getId());
	public static JoystickButton liftClimbDownButton = new JoystickButton(altJoyOne, ButtonId.LIFT_CLIMB_DOWN.getId());
	public static JoystickButton climbButton = new JoystickButton(altJoyOne, ButtonId.EXTEND_CLIMB_PISTON.getId());
	Trigger pushupToggle = new Trigger() {

		@Override
		public boolean get() {
			boolean isInEndGame = System.currentTimeMillis() - Robot.START_TIME >= (60 + 60) * 1_000;
			return /* isInEndGame && */ mainJoyOne.getRawButton(ButtonId.PUSHUP.getId())
					&& mainJoyTwo.getRawButton(ButtonId.PUSHUP.getId());
		}
	};

	public OI() {

	}

	public void initButtonCommands() {
		pushupToggle.whenActive(new PushupCommand());
	}
}
