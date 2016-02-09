
package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.autonoms.TimedAuto;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveDistanceAtAngleCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveInAPolygonCommand;
import org.usfirst.frc.team1557.robot.autonoms.commands.GyroTurnCommand;
import org.usfirst.frc.team1557.robot.commands.ClimbCommand;
import org.usfirst.frc.team1557.robot.commands.ControlIntakeArmCommand;
import org.usfirst.frc.team1557.robot.commands.TestCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeArmSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeWheelSubsystem;

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
	public static OI oi;
	public static DriveSubsystem drive;
	public static IntakeArmSubsystem intake;
	public static ControlIntakeArmCommand intakeControlCommand;
	public static ClimbSubsystem climb;
	public static ClimbCommand climbCommand;
	public static IntakeWheelSubsystem intakeWheel;
	private TestCommand test = new TestCommand();

	SendableChooser chooser;

	public void robotInit() {
		oi = new OI();
		drive = new DriveSubsystem();
		chooser = new SendableChooser();

		chooser.addDefault("No operation autonomous", new WaitCommand(1));
		chooser.addObject("Main Autonomous", new TimedAuto());
		chooser.addObject("12 Inches straight, 0.0 speed", new DriveDistanceAtAngleCommand(12, 0, 0.0));
		chooser.addObject("12 Inches straight, 0.4 speed", new DriveDistanceAtAngleCommand(12, 0, 0.4));
		chooser.addObject("24 Inches straight, 0.8 speed", new DriveDistanceAtAngleCommand(24, 0, 0.8));
		chooser.addObject("48 Inches straight, 0.4 speed", new DriveDistanceAtAngleCommand(48, 0, 0.4));
		chooser.addObject("Turn 90 degrees", new GyroTurnCommand(90));
		chooser.addObject("Turn 360 degrees", new GyroTurnCommand(360));
		chooser.addObject("Turn -90 degrees", new GyroTurnCommand(-90));
		chooser.addObject("Turn -360 degrees", new GyroTurnCommand(-360));
		chooser.addObject("3 Foot Line", new DriveInAPolygonCommand(2, 3 * 12));
		chooser.addObject("3 Foot Triangle", new DriveInAPolygonCommand(3, 3 * 12));
		chooser.addObject("3 Foot Box", new DriveInAPolygonCommand(4, 3 * 12));
		chooser.addObject("3 Foot Pentagon", new DriveInAPolygonCommand(5, 3 * 12));
		chooser.addObject("3 Foot Hexagon", new DriveInAPolygonCommand(6, 3 * 12));

		SmartDashboard.putData("Autonomous chooser", chooser);
		test.start();
		// intake = new IntakeSubsystem();
		// manualIntake = new IntakeCommand();
		// buttonIntakeUp = new SetIntakeUp();
		// buttonIntakeDown = new SetIntakeDownCommand();
		// catapult = new CatapultSubsystem();
		// catapultFire = new CatapultCommand();
		// climb = new ClimbSubsystem();
		// climbCommand = new ClimbCommand();

	}

	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		if (chooser.getSelected() != null) {
			((Command) chooser.getSelected()).start();
		}

	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (chooser.getSelected() != null) {
			((Command) chooser.getSelected()).cancel();
		}

		drive.initDefaultCommand();
		intake.initDefaultCommand();

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
