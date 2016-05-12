package org.usfirst.frc.team1557.robot.vision;

public interface VisionInterface {
	/**
	 * FOV of the m1013
	 */
	final double FOV = 60;
	/**
	 * The resolution of the camera.
	 */
	final int[] CAMERA_RESOLUTION = new int[] { 320, 240 };

	final String URL = "http://10.15.57.90/axis-cgi/jpg/image.cgi";
	// final String URL = "http://10.15.57.90/mjpg/video.mjpg";

	public boolean initCamera(String address);

	public double getAngle();

	public void startProcessing();

	public void stopProcessing();

	void setAngle(double d);
}
