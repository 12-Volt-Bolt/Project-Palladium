package org.usfirst.frc.team1557.robot.vision;

public abstract class VisionInterface {
	private String cameraAddress = "";

	public VisionInterface(String cameraAddress) {
		this.cameraAddress = cameraAddress;
	}

	public abstract void testThing();
	public abstract double testThing2ElectricBoogaloo();
	public String getAddress(){
		return cameraAddress;
	}
	public abstract String testThing3();
}
