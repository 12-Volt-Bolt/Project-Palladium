
package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.backupauto.AutoManager;
import org.usfirst.frc.team1557.robot.autonoms.TimedAuto;
import org.usfirst.frc.team1557.robot.autonoms.VisionAuto;
import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;

import org.usfirst.frc.team1557.robot.subsystems.ClimbPistonSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.LiftClimbSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeArmSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeWheelSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.PushupSubsystem;

import org.usfirst.frc.team1557.robot.vision.BasicTracker;
import org.usfirst.frc.team1557.robot.vision.GyroTracker;
import org.usfirst.frc.team1557.robot.vision.OpenCVVision;
import org.usfirst.frc.team1557.robot.vision.TrackInterface;
import org.usfirst.frc.team1557.robot.vision.VisionInterface;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Compressor com;
	public static long START_TIME;
	public static OI oi;
	public static DriveSubsystem drive;
	public static IntakeArmSubsystem intakeArm;
	public static ClimbPistonSubsystem climbPiston;
	public static LiftClimbSubsystem liftClimb;
	public static IntakeWheelSubsystem intakeWheel;
	public static PushupSubsystem pushup;
	SendableChooser chooser;
	SendableChooser driveChooser;
	SendableChooser visionInterfaceChooser;
	SendableChooser trackInterfaceChooser;

	public static VisionInterface vision;
	public static TrackInterface track;
	double FIRST_DRIVE = 2.2, TURN = 0.4, DRIVE_TO_BATTER = 0.4, DRIVE_UP_BATTER = 1;

	public void robotInit() {
		com = new Compressor(RobotMap.PCM_ID);
		// OI must be called before subsystems
		oi = new OI();
		// Subsystems
		drive = new DriveSubsystem();
		intakeArm = new IntakeArmSubsystem();
		intakeWheel = new IntakeWheelSubsystem();
		climbPiston = new ClimbPistonSubsystem();
		liftClimb = new LiftClimbSubsystem();
		pushup = new PushupSubsystem();
		// InitButton commands. Must be called after subsystems are created.
		oi.initButtonCommands();
		// Sendable Choosers
		chooser = new SendableChooser();
		driveChooser = new SendableChooser();
		visionInterfaceChooser = new SendableChooser();
		trackInterfaceChooser = new SendableChooser();
		// Objects for auto
		chooser.addDefault("No operation autonomous", new WaitCommand(1));
		chooser.addObject("Main Autonomous", new TimedAuto());
		chooser.addObject("Vision Aided", new VisionAuto());
		// Object for drive
		driveChooser.addDefault("Tedious Tank", new TankDriveCommand());
		// Objects for Vision
		visionInterfaceChooser.addDefault("OpenCV Vision", new OpenCVVision());
		// Moved because image class crashing//
		// visionInterfaceChooser.addObject("AWT Vision", new AWTVision());
		// Objects for Tracker
		trackInterfaceChooser.addDefault("Gyro Tracker", new GyroTracker());
		trackInterfaceChooser.addObject("Basic Tracker", new BasicTracker());
		// Load choosers into DriverStation
		SmartDashboard.putData("Autonomous chooser", chooser);
		SmartDashboard.putData("Drive Chooser", driveChooser);
		SmartDashboard.putData("Vision Interface Chooser", visionInterfaceChooser);
		SmartDashboard.putData("Tracker Chooser", trackInterfaceChooser);

		// AUTOTIMES
		SmartDashboard.putNumber("FIRST_DRIVE", FIRST_DRIVE);
		SmartDashboard.putNumber("TURN", TURN);
		SmartDashboard.putNumber("DRIVE_TO_BATTER", DRIVE_TO_BATTER);
		SmartDashboard.putNumber("DRIVE_UP_BATTER", DRIVE_UP_BATTER);
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public AutoManager manager;

	public void autonomousInit() {
		try {
			Robot.drive.gyro.reset();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if (chooser.getSelected() != null) {
			((Command) chooser.getSelected()).start();
		}
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		START_TIME = System.currentTimeMillis();
		if (chooser.getSelected() != null) {
			((Command) chooser.getSelected()).cancel();
		}
		if (driveChooser.getSelected() != null) {
			((Command) driveChooser.getSelected()).start();
		}
		if (visionInterfaceChooser.getSelected() != null) {
			vision = ((VisionInterface) visionInterfaceChooser.getSelected());
		}
		if (trackInterfaceChooser.getSelected() != null) {
			track = ((TrackInterface) trackInterfaceChooser.getSelected());
		}
		intakeArm.initDefaultCommand();
		intakeWheel.initDefaultCommand();
		liftClimb.initDefaultCommand();
		climbPiston.initDefaultCommand();
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("TIME", (System.currentTimeMillis() - START_TIME) / 1000L);
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
