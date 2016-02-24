package org.usfirst.frc.team1557.robot.utils;

import edu.wpi.first.wpilibj.CANTalon;

public class Mramp {
	private double[] speeds;
	private double mramp = 0;

	/**
	 * 
	 * @param size
	 *            The sample size to average
	 * @param deadzone
	 *            The size of the deadzone. Once the motor speed is out of the
	 *            deadzone the output is no longer averaging.
	 */
	public Mramp(int size, double deadzone) {
		speeds = new double[size];
		this.mramp = deadzone;
	}

	/**
	 * Call this before settings the motor speed.
	 * 
	 * @param currentOutput
	 *            The speed of the motor
	 */
	public void addSpeed(double currentOutput) {
		for (int i = 0; i < speeds.length - 1; i++) {
			speeds[i] = speeds[i + 1];
		}
		speeds[speeds.length - 1] = currentOutput;
	}

	/**
	 * 
	 * @param motorSpeed
	 *            The speed of the motor being used
	 * @return Returns the average speed. If the speed is within the deadzone no
	 *         averaging is done and this method simply returns the last output
	 *         value.
	 */
	public double getOutput(double motorSpeed) {
		if (Math.abs(motorSpeed) >= mramp) {
			return speeds[speeds.length - 1];
		}
		double avg = 0;
		for (double d : speeds) {
			avg += d;
		}
		avg /= speeds.length;
		return avg;
	}
}
