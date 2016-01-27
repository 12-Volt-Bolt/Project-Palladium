package org.usfirst.frc.team1557.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;
	public static final int DRIVE_MOTOR_ONE_ID = 0;
	public static final int DRIVE_MOTOR_TWO_ID = 1;
	public static final int DRIVE_MOTOR_THREE_ID = 2;
	public static final int DRIVE_MOTOR_FOUR_ID = 3;

	public static final int ROTATE_MOTOR_ONE_ID = 4;
	public static final int ROTATE_MOTOR_TWO_ID = 5;
	public static final int INTAKE_MOTOR_ONE_ID = 6;

	public static final int ROTATE_ENCODER_A_ID = 0;
	public static final int ROTATE_ENCODER_B_ID = 1;

	public static final int MAIN_JOY_AXIS_ONE_ID = 1;
	public static final int MAIN_JOY_AXIS_TWO_ID = 1;

	public static final int CATAPULT_FIRE_BUTTON_ID = 1;
	public static final int CLIMB_BUTTON_ID = 2;

	public static final int CATAPULT_SOLENOID_ONE_MODULE_ID = 0;
	public static final int CATAPULT_SOLENOID_TWO_MODULE_ID = 0;
	public static final int CATAPULT_SOLENOID_ONE_CHANNEL_ID = 0;
	public static final int CATAPULT_SOLENOID_TWO_CHANNEL_ID = 0;
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
