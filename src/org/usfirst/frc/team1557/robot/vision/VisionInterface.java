package org.usfirst.frc.team1557.robot.vision;

public interface VisionInterface {
	/**
	 * FOV of the m1013
	 */
	final double FOV = 67;
	/**
	 * The resolution of the camera.
	 */
	final int[] CAMERA_RESOLUTION = new int[] { 320, 240 };

	final String URL = "http://192.168.0.90/axis-cgi/jpg/image.cgi";

	public boolean initCamera(String address);

	public double getAngle();

	public void startProcessing();

	public void stopProcessing();

	void setAngle(double d);
}
