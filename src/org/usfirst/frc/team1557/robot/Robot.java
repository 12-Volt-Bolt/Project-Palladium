
package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.backupauto.AutoManager;
import org.usfirst.frc.team1557.backupauto.Lowbar;
import org.usfirst.frc.team1557.backupauto.RockWall;
import org.usfirst.frc.team1557.backupauto.RoughTerrain;
import org.usfirst.frc.team1557.robot.autonoms.HighSpeedAuto;
import org.usfirst.frc.team1557.robot.autonoms.SandwichCommand;
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
	private TestCommand test = new TestCommand();
	// SendableChooser chooser;
	Command autonomousCommand = null;
	SendableChooser driveChooser;

	public Robot() {

	}

	public void robotInit() {
		// chooser = new SendableChooser();
		com = new Compressor(RobotMap.PCM_ID);
		oi = new OI();
		drive = new DriveSubsystem();
		intakeArm = new IntakeArmSubsystem();
		intakeWheel = new IntakeWheelSubsystem();
		climbPiston = new ClimbPistonSubsystem();
		liftClimb = new LiftClimbSubsystem();
		pushup = new PushupSubsystem();
		oi.initButtonCommands();
		driveChooser = new SendableChooser();
		driveChooser.addDefault("Tedious Tank", new TankDriveCommand());
		SmartDashboard.putData("Drive Chooser", driveChooser);
		// chooser.addDefault("No operation autonomous", new WaitCommand(1));
		// chooser.addObject("Main Autonomous", new TimedAuto());
		// chooser.addObject("High Speed Auto", new HighSpeedAuto());
		// chooser.addObject("Sandwhich!", new SandwichCommand());
		// SmartDashboard.putData("Autonomous chooser", chooser);
		// test.start();
	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public AutoManager manager;

	@Override
	public void autonomousInit() {
		// try {
		// Robot.drive.gyro.reset();
		// } catch (Exception ex) {
		// ex.printStackTrace();
		// }

		// if (chooser.getSelected() != null) {
		autonomousCommand = new HighSpeedAuto();
		autonomousCommand.start();
		System.out.println("Running an auto...");
		System.out.println(autonomousCommand.toString());
		// }

	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		START_TIME = System.currentTimeMillis();
		if (autonomousCommand != null && autonomousCommand.isRunning()) {
			autonomousCommand.cancel();
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
