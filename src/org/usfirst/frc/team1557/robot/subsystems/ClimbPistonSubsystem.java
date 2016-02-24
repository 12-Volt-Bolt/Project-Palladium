package org.usfirst.frc.team1557.robot.subsystems;

import org.usfirst.frc.team1557.robot.RobotMap;
import org.usfirst.frc.team1557.robot.commands.ToggleClimbPistonCommand;
import org.usfirst.frc.team1557.robot.utils.SolenoidGroup;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ClimbPistonSubsystem extends Subsystem {

	public long lastUsedTime;
	/**
	 * Two pistons total. One per side.
	 */
	public SolenoidGroup prodigious;

	public ClimbPistonSubsystem() {
		prodigious = new SolenoidGroup(new DoubleSolenoid(RobotMap.PCM_ID, RobotMap.SolenoidId.CLIMB_PISTON_ONE.getId(),
				RobotMap.SolenoidId.CLIMB_PISTON_TWO.getId()));

	}

	public void initDefaultCommand() {
		setDefaultCommand(new ToggleClimbPistonCommand());
	}

	public void setProdigious(DoubleSolenoid.Value state) {
		prodigious.set(state);
		lastUsedTime = System.currentTimeMillis();
	}

}
