package org.usfirst.frc.team1557.robot.utils;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.SpeedController;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Use this class to group multiple motors under one object. Primarily used for controlling each side of the robot, or for
 * robot systems where multiple motors are required to work together.
 *
 * @author He Who Must Not Be Named ;)
 * @version 2016.2.26
 */
public class MotorGroup implements SpeedController {

	private List<SpeedController> motors = new ArrayList<>();
	private List<Boolean> inverts = new ArrayList<>();
	private double power = 0;
	private boolean reversed = false;

	/**
	 * Creates a MotorGroup that consists of motorArray's motors.
	 *
	 * @param motorArray The motors to be a part of this MotorGroup.
	 */
	public MotorGroup(SpeedController... motorArray) {
		super();
		addMotors(motorArray);
	}

	/**
	 * Creates a MotorGroup full of motors of class "type".
	 *
	 * @param type The class that the motor objects will be.
	 * @param ids  The IDs of all the motors.
	 * @param <T>  Any class that implements SpeedController.
	 */
	public <T extends SpeedController> MotorGroup(Class<T> type, Integer... ids) {
		super();
		addMotors(type, ids);
	}
	/**
	 * Adds motors to this MotorGroup.
	 *
	 * @param moreMotors The motors to be added.
	 * @return This MotorGroup.
	 */
	public MotorGroup addMotors(SpeedController... moreMotors) {
		for (SpeedController s : moreMotors) {
			motors.add(s);
			inverts.add(false);
		}
		return this;
	}

	/**
	 * Adds Motors of class "type".
	 *
	 * @param type The class that the Motor objects will be.
	 * @param ids  The IDs of the motors.
	 * @param <T>  Any class that extends Motor.
	 * @return This MotorGroup.
	 */
	public <T extends SpeedController> MotorGroup addMotors(Class<T> type, Integer... ids) {
		try {
            boolean addedAny = false;
			for (int i = 0; i < ids.length; i++) {
				Constructor[] constructors = type.getConstructors();
				if (constructors.length > 0) {
					for (Constructor c : constructors) {
						Class[] paramTypes = c.getParameterTypes();
						if (paramTypes.length == 1 && (paramTypes[0] == Integer.class || paramTypes[0] == int.class)) {
							for (int id : ids) {
								motors.add((SpeedController) c.newInstance(id));
                                inverts.add(false);
                                addedAny = true;
							}
							break;
						}
					}
					if (!addedAny && ids.length > 0) {
						DriverStation.reportError("The class given to MotorGroup, \"" + type.getSimpleName() + "\", does not have a constructor that accepts an Integer as its only argument!", false);
					}
				}
			}
		} catch (Exception e) {
			DriverStation.reportError("MotorGroup.addMotors() exception!", false);
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Clears the list of Motors.
	 */
	public void clearMotors() {
		motors.clear();
	}

	/**
	 * Sets the invert statuses of all the Motors in this MotorGroup.
	 *
	 * @param newInverts All the new invert statuses. Must be the same length as the motor list.
	 * @return This MotorGroup.
	 */
	public MotorGroup setInverts(Boolean... newInverts) {
		if (newInverts.length == motors.size()) {
			inverts.clear();
			Collections.addAll(inverts, newInverts);
		} else {
			DriverStation.reportError("MotorGroup.setInverts() got an array of inverts that is not the same size as the motor list!", false);
		}
		return this;
	}

	/**
	 * Calls code on each Motor in this MotorGroup.
	 * @param c The code to call.
	 */
	public void forEach(Consumer<SpeedController> c) {
		motors.forEach(c);
	}

	/**
	 * Tests each Motor in this MotorGroup, and freezes the Thread until the test is complete.
	 * @param power The speed the motor will run at while being tested.
	 * @param timeOn How long the motor will be on while being tested.
	 */
	public void testEach(double power, double timeOn) {
		for (SpeedController s : motors) {
			try {
				s.set(power);
				Thread.sleep(Math.round(timeOn * 1000));
				s.set(0);
				Thread.sleep(1000);
			} catch (Exception e) {
				DriverStation.reportError("MotorGroup.testEachWait() exception!", false);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets all the motors in this MotorGroup.
	 *
	 * @return All the motors in this MotorGroup.
	 */
	public List<SpeedController> getMotors() {
		return new ArrayList<>(motors);
	}

	/**
	 * Gets how many motors are in this MotorGroup.
	 *
	 * @return How many motors are in this MotorGroup.
	 */
	public int getMotorCount() {
		return motors.size();
	}

	@Override
	public double get() {
        return power;
	}

	@Override
	public void set(double v, byte b) {
        set(v);
	}

	@Override
	public void set(double v) {
        int index = 0;
        for (SpeedController s : motors) {
            double speed = inverts.get(index) ? -v : v; //Inverts the speed based off of that motor's invert value
            s.set(reversed ? -speed : speed); //Sets the motor's speed and possibly inverts it if the MotorGroup as a whole is inverted
            index++;
        }
        power = v;
	}

	@Override
	public void setInverted(boolean b) {
        reversed = b;
	}

	@Override
	public boolean getInverted() {
		return reversed;
	}

	@Override
	public void disable() {
        forEach(SpeedController::disable);
	}

	@Override
	public void stopMotor() {
		forEach(SpeedController::stopMotor);
	}

	@Override
	public void pidWrite(double v) {
        for (SpeedController s : motors) {
            s.pidWrite(v);
        }
	}
}

