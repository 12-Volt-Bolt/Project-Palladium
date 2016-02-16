package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimberSubsystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public static CANTalon climberMotor;
	
	public ClimberSubsystem() {
		climberMotor = new CANTalon(RobotMap.MotorId.CLIMB_ONE.getId());
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void stopMotor() {
		climberMotor.set(0);
	}
	
	public void up() {
		climberMotor.set(1);
	}
	
	public void down() {
		climberMotor.set(-1);
	}
}

