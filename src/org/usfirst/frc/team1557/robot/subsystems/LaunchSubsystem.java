package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap.MotorId;
import org.usfirst.frc.team1557.robot.commands.LaunchCommand;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LaunchSubsystem extends Subsystem {
	
	public static Talon fireLeft;
	public static Talon fireRight;
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public LaunchSubsystem() {
		
		fireLeft = new Talon(MotorId.LAUNCH_LEFT.getId());
		fireRight = new Talon(MotorId.LAUNCH_RIGHT.getId());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LaunchCommand());
    }
    
    public void stopMotors(){
    	fireLeft.set(0);
    	fireRight.set(0);
    }
    
    public void startMotors() {
    	fireLeft.set(1);
    	fireRight.set(1);
    }
}

