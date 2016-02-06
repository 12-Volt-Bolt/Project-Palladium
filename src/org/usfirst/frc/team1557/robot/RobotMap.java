package org.usfirst.frc.team1557.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	// IDs of the motors
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

	public static final int ALT_JOY_AXIS_ONE_ID = 1;

	public static final int CATAPULT_FIRE_BUTTON_ID = 1;
	public static final int CLIMB_BUTTON_ID = 2;

	public static final int CATAPULT_SOLENOID_ONE_MODULE_ID = 0;
	public static final int CATAPULT_SOLENOID_TWO_MODULE_ID = 0;
	public static final int CATAPULT_SOLENOID_ONE_CHANNEL_ID = 0;
	public static final int CATAPULT_SOLENOID_TWO_CHANNEL_ID = 0;

	public static final int INTAKE_WHEEL_BUTTON_ID = 0;
	
	public static final int ENCODER_LEFT_A = 0;
	public static final int ENCODER_LEFT_B = 1;
	public static final int ENCODER_RIGHT_A = 2;
	public static final int ENCODER_RIGHT_B = 3;
	
	// Divisor by the wheel circumference
	public static final double ENCODER_LEFT_PULSES_PER_ROTATION = 28.0;
	public static final double ENCODER_RIGHT_PULSES_PER_ROTATION = 230.0;
	
	/**
	 * THE size of the wheel
	 */
	public static final double WHEEL_CIRCUMFERENCE_INCHES = 33.0;
}
