package org.usfirst.frc.team1557.robot.vision;

public interface VisionInterface {
	/**
	 * According to screensteps this is the view angle of the m1013.
	 */
	final double FOV = 49;
	/**
	 * I believe this is what we set the resolution of the camera to. Need to
	 * confirm. 320width by 240 height.
	 */
	final int[] CAMERA_RESOLUTION = new int[] { 320, 240 };

	public boolean initCamera(String address);

	public double getAngle();

	public void startProcessing();

	public void stopProcessing();

	void setAngle(double d);
}
