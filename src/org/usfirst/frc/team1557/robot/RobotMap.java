package org.usfirst.frc.team1557.robot;

import edu.wpi.first.wpilibj.Joystick;

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

		DRIVE_ONE(0), DRIVE_TWO(1), DRIVE_THREE(2), DRIVE_FOUR(3), LIFT_CLIMB_ONE(11), LIFT_CLIMB_TWO(
				12), INTAKE_ARM_ONE(21), INTAKE_ARM_TWO(22), INTAKE_WHEEL(31), LAUNCH_LEFT(8), LAUNCH_RIGHT(9);
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

	public enum SolenoidId {
		PUSHUP_ONE(3), PUSHUP_TWO(2), CLIMB_PISTON_ONE(0), CLIMB_PISTON_TWO(1);
		int id;

		SolenoidId(int i) {
			id = i;
		}

		public int getId() {
			return id;
		}
	}

	//
	public static final int PCM_ID = 0;
	// Joystick, Button, and Axes Ids
	public static final int MAIN_JOY_AXIS_ONE_ID = 1;
	public static final int MAIN_JOY_AXIS_TWO_ID = 1;
	public static final int ALT_JOY_AXIS_ONE_ID = 1;
	public static final int MAIN_JOY_ROTATE_AXIS_ID = 3;

	public enum ButtonId {
		EXTEND_CLIMB_PISTON(4), RETRACT_CLIMB_PISTON(5), INTAKE_WHEEL(6), OUTTAKE_WHEEL(7), REVERSE(2), PUSHUP(1), ARM(
				1), LIFT_CLIMB_UP(3), LIFT_CLIMB_DOWN(2), VISION(3), FIRE(10), STAY_ON_RAMP(3);
		int id;

		ButtonId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}

	public enum JoystickId {
		MAIN_ONE(OI.mainJoyOne), MAIN_TWO(OI.mainJoyTwo), ALT_ONE(OI.altJoyOne);
		Joystick joy;

		JoystickId(Joystick joy) {
			this.joy = joy;
		}

		public Joystick getJoystick() {
			return joy;
		}
	}

	// Divisor by the wheel circumference
	public static final double ENCODER_LEFT_PULSES_PER_ROTATION = 28.0;
	public static final double ENCODER_RIGHT_PULSES_PER_ROTATION = 230.0;
	public static final double JOYSTICK_DEADZONE = 0.075;

	/**
	 * THE size of the wheel
	 */
	public static final double WHEEL_CIRCUMFERENCE_INCHES = 33.0;
}
