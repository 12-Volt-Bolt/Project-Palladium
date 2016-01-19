package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.commands.SetIntakeDownCommand;
import org.usfirst.frc.team1557.robot.commands.SetIntakeUp;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	
    public static Joystick mainJoyOne = new Joystick(0);
    public static Joystick mainJoyTwo = new Joystick(1);
    public static Joystick altJoyOne = new Joystick(2);
    public static JoystickButton openButton = new JoystickButton(altJoyOne,2);
    public static JoystickButton closeButton = new JoystickButton(altJoyOne, 1);
 
    public OI(){
    	
    }
    public void initButtonCOmmands(){
    	
    }

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

