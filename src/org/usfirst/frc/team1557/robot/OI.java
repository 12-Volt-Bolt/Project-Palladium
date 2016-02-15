package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.RobotMap.ButtonId;
import org.usfirst.frc.team1557.robot.commands.ExtendClimbCommand;
import org.usfirst.frc.team1557.robot.commands.PushupCommand;
import org.usfirst.frc.team1557.robot.commands.UpperClimbCommand;

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
	public static JoystickButton intakeArmToggle = new JoystickButton(altJoyOne, 2);
	public static JoystickButton intakeWheelButton = new JoystickButton(altJoyOne, ButtonId.INTAKE_WHEEL.getId());
	public static JoystickButton outtakeWheelButton = new JoystickButton(altJoyOne, ButtonId.OUTTAKE_WHEEL.getId());
	Trigger pushupToggle = new Trigger() {

		@Override
		public boolean get() {
			boolean isInEndGame = System.currentTimeMillis() - Robot.START_TIME >= (60 + 60) * 1_000;
			return isInEndGame && altJoyOne.getRawButton(ButtonId.PUSHUP.getId()) && altJoyOne.getRawAxis(2) >= 0.95;
		}
	};
	Trigger upperClimbToggle = new Trigger() {

		@Override
		public boolean get() {
			boolean isInEndGame = System.currentTimeMillis() - Robot.START_TIME >= (60 + 60) * 1_000;
			return isInEndGame && altJoyOne.getRawButton(ButtonId.CLIMB.getId()) && Robot.climb.infinitesimal.getState()
					&& altJoyOne.getRawAxis(2) >= 0.95
					&& System.currentTimeMillis() - Robot.climb.timeSinceLastUsed >= 1_000;
		}
	};
	Trigger extendClimb = new Trigger() {

		@Override
		public boolean get() {
			boolean isInEndGame = System.currentTimeMillis() - Robot.START_TIME >= (60 + 60) * 1_000;
			return isInEndGame && altJoyOne.getRawButton(ButtonId.CLIMB.getId()) && altJoyOne.getRawAxis(2) >= 0.95
					&& System.currentTimeMillis() - Robot.climb.timeSinceLastUsed >= 1_000
					&& !Robot.climb.infinitesimal.getState();
		}
	};

	public OI() {

	}

	public void initButtonCommands() {
		extendClimb.whenActive(new ExtendClimbCommand());
		upperClimbToggle.whenActive(new UpperClimbCommand());
		pushupToggle.whenActive(new PushupCommand());
	}
}
