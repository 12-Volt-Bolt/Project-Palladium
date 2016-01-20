package org.usfirst.frc.team1557.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CatapultSubsystem extends Subsystem {
    Solenoid solenoidLeft = new Solenoid(0, 0);
    Solenoid solenoidRight = new Solenoid(0, 0);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void toggle(){
    	solenoidLeft.set(!solenoidLeft.get());
    }
}

