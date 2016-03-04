package org.usfirst.frc.team1557.robot.vision;

public interface VisionInterface {
	public boolean initCamera(String address);

	public double getAngle();

	public void setAngle();

	public void startProcessing();

	public void stopProcessing();
}
