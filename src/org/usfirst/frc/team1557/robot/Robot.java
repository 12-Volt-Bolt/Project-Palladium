
package org.usfirst.frc.team1557.robot;

import org.usfirst.frc.team1557.robot.autonoms.MainAuto;
import org.usfirst.frc.team1557.robot.autonoms.commands.DriveCommand;
import org.usfirst.frc.team1557.robot.commands.ClimbCommand;
import org.usfirst.frc.team1557.robot.commands.IntakeWheelCommand;
import org.usfirst.frc.team1557.robot.commands.ManualIntakeCommand;
import org.usfirst.frc.team1557.robot.commands.SetIntakeDown;
import org.usfirst.frc.team1557.robot.commands.SetIntakeUp;
import org.usfirst.frc.team1557.robot.commands.TestCommand;
import org.usfirst.frc.team1557.robot.subsystems.ClimbSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeArmSubsystem;
import org.usfirst.frc.team1557.robot.subsystems.IntakeWheelSubsystem;

import com.ni.vision.NIVision.GetClassifierSampleInfoResult;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
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
	public static ManualIntakeCommand manualIntakeArm;
	public static SetIntakeUp buttonIntakeArmUp;
	public static SetIntakeDown buttonIntakeArmDown;
	public static ClimbSubsystem climb;
	public static ClimbCommand climbCommand;
	public static IntakeWheelSubsystem buttonIntakeWheel;
	private TestCommand test = new TestCommand();

	SendableChooser chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		oi = new OI();
		drive = new DriveSubsystem();
		chooser = new SendableChooser();
		chooser.addDefault("Main Autonomous", new MainAuto());
		chooser.addObject("Other Autonomous", 1);
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

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		if (chooser.getSelected() != null) {
			((CommandGroup) chooser.getSelected()).start();
		}

	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (chooser.getSelected() != null) {
			((CommandGroup) chooser.getSelected()).cancel();
		}

		drive.initDefaultCommand();

	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		CommandManager.manage();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
