
package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.backupauto.AutoManager;
import org.usfirst.frc.team1557.backupauto.Lowbar;
import org.usfirst.frc.team1557.backupauto.RockWall;
import org.usfirst.frc.team1557.backupauto.RoughTerrain;
import org.usfirst.frc.team1557.robot.autonoms.TimedAuto;
import org.usfirst.frc.team1557.robot.autonoms.commands.ControlArmCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveDistanceAtAngleCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveInAPolygonCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.GyroTurnCommand;
import org.usfirst.frc.team1557.robot.commands.TankDriveCommand;
import org.usfirst.frc.team1557.robot.commands.TestCommand;
import org.usfirst.frc.team1557.robot.commands.TwistyTankDriveCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClimbPistonSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.LiftClimbSubsystem;
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
	public static IntakeArmSubsystem intakeArm;
	public static ClimbPistonSubsystem climbPiston;
	public static LiftClimbSubsystem liftClimb;
	public static IntakeWheelSubsystem intakeWheel;
	public static PushupSubsystem pushup;
	private TestCommand test = new TestCommand();
	SendableChooser chooser;
	SendableChooser driveChooser;

	public void robotInit() {
		oi = new OI();
		drive = new DriveSubsystem();
		intakeArm = new IntakeArmSubsystem();
		intakeWheel = new IntakeWheelSubsystem();
		climbPiston = new ClimbPistonSubsystem();
		liftClimb = new LiftClimbSubsystem();
		pushup = new PushupSubsystem();
		oi.initButtonCommands();
		chooser = new SendableChooser();
		driveChooser = new SendableChooser();
		chooser.addDefault("No operation autonomous", new WaitCommand(1));
		chooser.addObject("Main Autonomous", new TimedAuto());
		chooser.addObject("Basic Drive Forward Autonomous", new DriveCommand(0.5, 1));
		chooser.addObject("Backup: Lowbar", new Lowbar());
		chooser.addObject("Backup: Rock Wall", new RockWall());
		chooser.addObject("Backup: Rough Terrain", new RoughTerrain());
		driveChooser.addDefault("Tedious Tank", new TankDriveCommand());
		SmartDashboard.putData("Autonomous chooser", chooser);
		SmartDashboard.putData("Drive Chooser", driveChooser);
		// test.start();
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
