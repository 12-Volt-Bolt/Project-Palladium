package org.usfirst.frc.team1557.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
	/**
	 * An enumeration of motor ids that are used throughout the robot.
	 * 
	 *
	 */
	public enum MotorId {

		DRIVE_ONE(0), DRIVE_TWO(1), DRIVE_THREE(2), DRIVE_FOUR(3), ROTATE_ONE(4), ROTATE_TWO(5), INTAKE_ONE(6);
		int id;

		MotorId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	//
	// Encoder IDs
	public static final int ROTATE_ENCODER_A_ID = 0;
	public static final int ROTATE_ENCODER_B_ID = 1;

	public enum EncoderId {
		LEFT_A(0), LEFT_B(1), RIGHT_A(2), RIGHT_B(3);
		int id;

		EncoderId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	//
	// Joystick, Button, and Axes Ids
	public static final int MAIN_JOY_AXIS_ONE_ID = 1;
	public static final int MAIN_JOY_AXIS_TWO_ID = 1;
	public static final int ALT_JOY_AXIS_ONE_ID = 1;

	public enum ButtonId {
		CLIMB(2), INTAKE_WHEEL(0), REVERSE(2), PUSHUP(3);
		int id;

		ButtonId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	//
	// Solenoid Ids

	// Divisor by the wheel circumference
	public static final double ENCODER_LEFT_PULSES_PER_ROTATION = 28.0;
	public static final double ENCODER_RIGHT_PULSES_PER_ROTATION = 230.0;
	public static final double JOYSTICK_DEADZONE = 0.05;

	/**
	 * THE size of the wheel
	 */
	public static final double WHEEL_CIRCUMFERENCE_INCHES = 33.0;
}
