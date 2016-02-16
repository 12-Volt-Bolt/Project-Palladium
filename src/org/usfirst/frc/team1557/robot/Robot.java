
package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.backupauto.AutoManager;
import org.usfirst.frc.team1557.backupauto.Lowbar;
import org.usfirst.frc.team1557.backupauto.RockWall;
import org.usfirst.frc.team1557.backupauto.RoughTerrain;
import org.usfirst.frc.team1557.robot.autonoms.TimedAuto;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveDistanceAtAngleCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveInAPolygonCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.GyroTurnCommand;
import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.commands.TestCommand;
import org.usfirst.frc.team1557.robot.commands.TwistyTankDriveCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.ClimberSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeArmSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeWheelSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.PushupSubsystem;

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
	public static long START_TIME;
	public static OI oi;
	public static DriveSubsystem drive;
	public static IntakeArmSubsystem intake;
	public static ClimbSubsystem climb;
	public static ClimberSubsystem climber;
	public static IntakeWheelSubsystem intakeWheel;
	public static PushupSubsystem pushup;
	private TestCommand test = new TestCommand();

	SendableChooser chooser;
	SendableChooser driveChooser;

	public void robotInit() {
		oi = new OI();
		drive = new DriveSubsystem();
		intake = new IntakeArmSubsystem();
		intakeWheel = new IntakeWheelSubsystem();
		climb = new ClimbSubsystem();
		climber = new ClimberSubsystem();
		pushup = new PushupSubsystem();
		oi.initButtonCommands();
		chooser = new SendableChooser();
		driveChooser = new SendableChooser();
		chooser.addDefault("No operation autonomous", new WaitCommand(1));
		chooser.addObject("Main Autonomous", new TimedAuto());
		chooser.addObject("12 Inches straight, 0.0 speed", new DriveDistanceAtAngleCommand(12, 0, 0.0));
		chooser.addObject("12 Inches straight, 0.4 speed", new DriveDistanceAtAngleCommand(12, 0, 0.4));
		chooser.addObject("24 Inches straight, 0.8 speed", new DriveDistanceAtAngleCommand(24, 0, 0.8));
		chooser.addObject("48 Inches straight, 0.4 speed", new DriveDistanceAtAngleCommand(48, 0, 0.4));
		chooser.addObject("Turn 30 degrees", new GyroTurnCommand(30));
		chooser.addObject("Turn 90 degrees", new GyroTurnCommand(90));
		chooser.addObject("Turn 360 degrees", new GyroTurnCommand(360));
		chooser.addObject("Turn -90 degrees", new GyroTurnCommand(-90));
		chooser.addObject("Turn -360 degrees", new GyroTurnCommand(-360));
		chooser.addObject("3 Foot Line", new DriveInAPolygonCommand(2, 3 * 12));
		chooser.addObject("3 Foot Triangle", new DriveInAPolygonCommand(3, 3 * 12));
		chooser.addObject("3 Foot Box", new DriveInAPolygonCommand(4, 3 * 12));
		chooser.addObject("3 Foot Pentagon", new DriveInAPolygonCommand(5, 3 * 12));
		chooser.addObject("3 Foot Hexagon", new DriveInAPolygonCommand(6, 3 * 12));

		chooser.addObject("Backup: Lowbar", new Lowbar());
		chooser.addObject("Backup: Rock Wall", new RockWall());
		chooser.addObject("Backup: Rough Terrain", new RoughTerrain());

		chooser.addObject("Go straight @ full for 5 seconds", new DriveCommand(1, 5));

		driveChooser.addDefault("Tedious Tank", new TankDriveCommand());
		driveChooser.addObject("Terrific Twisty", new TwistyTankDriveCommand());
		SmartDashboard.putData("Autonomous chooser", chooser);
		SmartDashboard.putData("Drive Chooser", driveChooser);
		test.start();
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
		intake.initDefaultCommand();
		intakeWheel.initDefaultCommand();

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
