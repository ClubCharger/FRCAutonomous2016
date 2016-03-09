
package org.usfirst.frc.team972.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Autonomous {


	public static boolean autonomousDelay(long start, int millis) {
		return ((System.currentTimeMillis() >= start + millis));
	}

	// TODO Uncomment at competition
//	public static boolean lowerObstacleMotor(long startTime) {
//		// If the lower limit switch is not pressed, lower the obstacle motor
//		if (Robot.obstacleMotorLowerLimitSwitch.get()) {
//			Robot.obstacleMotor.set(0.2);
//		} else {
//			Robot.obstacleMotor.set(0.0);
//		}
//		return System.currentTimeMillis() >= startTime + RobotMap.LOWER_OBSTACLE_MOTOR_TIME;
//	}

	public static boolean autonomousDrive(int distance, double speed) {
		double leftDriveSpeed, rightDriveSpeed;
		if (Robot.leftDriveEncoder.get() < distance) {
			leftDriveSpeed = speed;
		} else {
			leftDriveSpeed = 0;
		}
		if (Robot.rightDriveEncoder.get() < distance) {
			rightDriveSpeed = speed;
		} else {
			rightDriveSpeed = 0;
		}
		Robot.botDrive.tankDrive(leftDriveSpeed, rightDriveSpeed);
		SmartDashboard.putNumber("Left Speed", leftDriveSpeed);
		SmartDashboard.putNumber("Right Speed", rightDriveSpeed);
		return Robot.leftDriveEncoder.get() >= distance && Robot.rightDriveEncoder.get() >= distance;
	}
	
	public static boolean autonomousTurnClockwise(int leftDistance, int rightDistance, double speed) {
		double leftDriveSpeed, rightDriveSpeed;
		if (Robot.leftDriveEncoder.get() < leftDistance) {
			leftDriveSpeed = speed;
		} else {
			leftDriveSpeed = 0;
		}
		if (Robot.rightDriveEncoder.get() > -rightDistance) {
			rightDriveSpeed = -speed;
		} else {
			rightDriveSpeed = 0;
		}
		Robot.botDrive.tankDrive(leftDriveSpeed, rightDriveSpeed);
		SmartDashboard.putNumber("Left Speed", leftDriveSpeed);
		SmartDashboard.putNumber("Right Speed", rightDriveSpeed);
		return Robot.leftDriveEncoder.get() >= leftDistance && Robot.rightDriveEncoder.get() <= -rightDistance;
	}

	// distance and speed should be positive
	public static boolean autonomousTurnCounterclockwise(int leftDistance, int rightDistance, double speed) {
		double leftDriveSpeed, rightDriveSpeed;
		if (Robot.leftDriveEncoder.get() > -leftDistance) {
			leftDriveSpeed = -speed;
		} else {
			leftDriveSpeed = 0;
		}
		if (Robot.rightDriveEncoder.get() < rightDistance) {
			rightDriveSpeed = speed;
		} else {
			rightDriveSpeed = 0;
		}
		Robot.botDrive.tankDrive(leftDriveSpeed, rightDriveSpeed);
		SmartDashboard.putNumber("Left Speed", leftDriveSpeed);
		SmartDashboard.putNumber("Right Speed", rightDriveSpeed);
		return Robot.leftDriveEncoder.get() <= -leftDistance && Robot.rightDriveEncoder.get() >= rightDistance;
	}

	// TODO: Doesn't actually do anything
	public static boolean autonomousFlippyThing(boolean flipUp) {
		return true;
	}
	
	public static void startAutonomous(Robot r, AutonomousChooser autonomousChooserSystem) {

		// Currently no autonomous delay
		// Robot.autonomousDelayStartTime = System.currentTimeMillis();
		// boolean finishedDelaying = false;
		// while (r.isAutonomous() && r.isEnabled() && !finishedDelaying) {
		// finishedDelaying =
		// Autonomous.autonomousDelay(Robot.autonomousDelayStartTime,
		// autonomousChooserSystem.getAutonomousDelay() * 1000);
		// // Converting from seconds to milliseconds
		// }

		// botDrive.setSafetyEnabled(true); // Prevents "output not updated
		// enough" error message

		Robot.leftDriveEncoder.reset();
		Robot.rightDriveEncoder.reset();

		int distance;
		double speed;
		long startTime = System.currentTimeMillis();

		System.out.println("Autonomous State Machine Start");
		while (r.isEnabled() && r.isAutonomous()) {
			// will automatically return out of method when finished with state machine
			Robot.printEverything();
			
			
			switch (RobotMap.autonomousMode) {
				case RobotMap.LOWER_OBSTACLE_MOTOR_MODE:
					// TODO Uncomment at competition
					SmartDashboard.putString("Autonomous Mode", "Lower Obstacle Motor");
//					if (lowerObstacleMotor(startTime)) {
//						Robot.obstacleMotor.set(0);
						
						RobotMap.autonomousMode = RobotMap.FIRST_DRIVE_FORWARD_MODE;
//					}
					break;
				case RobotMap.FIRST_DRIVE_FORWARD_MODE:
					SmartDashboard.putString("Autonomous Mode", "First Drive Forward");
					distance = AutonomousChooser.getDefenseDistance(RobotMap.autonomousFirstDefenseMode);
					speed = AutonomousChooser.getDefenseSpeed(RobotMap.autonomousFirstDefenseMode);
					if (Autonomous.autonomousDrive(distance, speed)) {
						Robot.botDrive.tankDrive(0, 0);
						Robot.leftDriveEncoder.reset();
						Robot.rightDriveEncoder.reset();
						
						RobotMap.autonomousMode = RobotMap.TURN_AROUND_MODE;
					}
					break;
				case RobotMap.TURN_AROUND_MODE:
					SmartDashboard.putString("Autonomous Mode", "Turn Around");
					// Twice the distance because we want two 90 degree turns
					if (autonomousTurnClockwise(2 * RobotMap.LEFT_TURN_DISTANCE, 2 * RobotMap.RIGHT_TURN_DISTANCE,  RobotMap.AUTONOMOUS_TURN_SPEED)) {
						Robot.botDrive.tankDrive(0, 0);
						Robot.leftDriveEncoder.reset();
						Robot.rightDriveEncoder.reset();
						
						RobotMap.autonomousMode = RobotMap.FIRST_DRIVE_BACKWARD_MODE;
					}
					break;
				case RobotMap.FIRST_DRIVE_BACKWARD_MODE:
					SmartDashboard.putString("Autonomous Mode", "First Drive Backward");
					distance = AutonomousChooser.getDefenseDistance(RobotMap.autonomousFirstDefenseMode);
					speed = AutonomousChooser.getDefenseSpeed(RobotMap.autonomousFirstDefenseMode);
					if (Autonomous.autonomousDrive(distance, speed)) {
						Robot.botDrive.tankDrive(0, 0);
						Robot.leftDriveEncoder.reset();
						Robot.rightDriveEncoder.reset();
						
						RobotMap.autonomousMode = RobotMap.TURN_MODE;
					}
					break;
				case RobotMap.TURN_MODE:
					SmartDashboard.putString("Autonomous Mode", "Turn");
					// Second obstacle to the right (from driver's perspective)
					if (AutonomousChooser.getDifferenceInPosition() > 0) {
						if (autonomousTurnCounterclockwise(RobotMap.LEFT_TURN_DISTANCE, RobotMap.RIGHT_TURN_DISTANCE, RobotMap.AUTONOMOUS_TURN_SPEED)) {
							Robot.botDrive.tankDrive(0, 0);
							Robot.leftDriveEncoder.reset();
							Robot.rightDriveEncoder.reset();
							
							RobotMap.autonomousMode = RobotMap.GO_TO_NEXT_DEFENSE_MODE;
						}
					// Second obstacle to the left (from driver's perspective)
					} else if (AutonomousChooser.getDifferenceInPosition() < 0) {
						if (autonomousTurnClockwise(RobotMap.LEFT_TURN_DISTANCE, RobotMap.RIGHT_TURN_DISTANCE, RobotMap.AUTONOMOUS_TURN_SPEED)) {
							Robot.botDrive.tankDrive(0, 0);
							Robot.leftDriveEncoder.reset();
							Robot.rightDriveEncoder.reset();
							
							RobotMap.autonomousMode = RobotMap.GO_TO_NEXT_DEFENSE_MODE;
						}
					// You are done
					} else {
						return;
//						Robot.botDrive.tankDrive(0, 0);
//						Robot.leftDriveEncoder.reset();
//						Robot.rightDriveEncoder.reset();
//						RobotMap.autonomousMode = RobotMap.GO_TO_NEXT_DEFENSE_MODE;
					}
					break;
				case RobotMap.GO_TO_NEXT_DEFENSE_MODE:
					SmartDashboard.putString("Autonomous Mode", "Go To Next Defense");
					distance = RobotMap.AUTONOMOUS_DISTANCE_BETWEEN_DEFENSES * AutonomousChooser.getDifferenceInPosition();
					speed = RobotMap.AUTONOMOUS_SPEED_BETWEEN_DISTANCES;
					if (Autonomous.autonomousDrive(distance, speed)) {
						Robot.botDrive.tankDrive(0, 0);
						Robot.leftDriveEncoder.reset();
						Robot.rightDriveEncoder.reset();
						
						RobotMap.autonomousMode = RobotMap.TURN_TOWARD_DEFENSE_MODE;
					}
					break;
				case RobotMap.TURN_TOWARD_DEFENSE_MODE:
					SmartDashboard.putString("Autonomous Mode", "Turn Toward Defense");
					if (AutonomousChooser.getDifferenceInPosition() > 0) {
						if (autonomousTurnCounterclockwise(RobotMap.LEFT_TURN_DISTANCE, RobotMap.RIGHT_TURN_DISTANCE, RobotMap.AUTONOMOUS_TURN_SPEED)) {
							Robot.botDrive.tankDrive(0, 0);
							Robot.leftDriveEncoder.reset();
							Robot.rightDriveEncoder.reset();
							
							RobotMap.autonomousMode = RobotMap.SECOND_DRIVE_FORWARD_MODE;
						}
					} else if (AutonomousChooser.getDifferenceInPosition() < 0) {
						if (autonomousTurnClockwise(RobotMap.LEFT_TURN_DISTANCE, RobotMap.RIGHT_TURN_DISTANCE, RobotMap.AUTONOMOUS_TURN_SPEED)) {
							Robot.botDrive.tankDrive(0, 0);
							Robot.leftDriveEncoder.reset();
							Robot.rightDriveEncoder.reset();
							
							RobotMap.autonomousMode = RobotMap.SECOND_DRIVE_FORWARD_MODE;
						}
					}
					break;
				case RobotMap.SECOND_DRIVE_FORWARD_MODE:
					SmartDashboard.putString("Autonomous Mode", "Second Drive Forward");
					distance = AutonomousChooser.getDefenseDistance(RobotMap.autonomousSecondDefenseMode);
					speed = AutonomousChooser.getDefenseSpeed(RobotMap.autonomousSecondDefenseMode);
					if (Autonomous.autonomousDrive(distance, speed)) {
						Robot.botDrive.tankDrive(0, 0);
						Robot.leftDriveEncoder.reset();
						Robot.rightDriveEncoder.reset();
						SmartDashboard.putNumber("Left Speed", 0);
						SmartDashboard.putNumber("Right Speed", 0);
						SmartDashboard.putString("Autonomous Mode", "FINISHED");
						return;
					}
					SmartDashboard.putString("Autonomous Mode", "First Drive Forward");
					break;
				default:
					SmartDashboard.putString("Autonomous Mode", "DEFAULT");
					break;
			}
		}
	}
    
}
