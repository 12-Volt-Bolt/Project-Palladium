package org.usfirst.frc.team1557.robot.vision;

public class OpenCVVision implements VisionInterface {
	private boolean shouldRun = false;
	private Thread thread = new Thread(new Runnable() {

		@Override
		public void run() {
			while (shouldRun) {

			}

		}
	});

	@Override
	public boolean initCamera(String address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public synchronized double getAngle() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public synchronized void setAngle() {

	}

	@Override
	public synchronized void startProcessing() {

	}

	@Override
	public void stopProcessing() {
		// TODO Auto-generated method stub

	}

}
