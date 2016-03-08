package org.usfirst.frc.team972.robot;

import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj.*;

public class AutonomousChooser {

	SendableChooser autonomousFirstDefenseChooser = new SendableChooser();
	SendableChooser autonomousFirstPositionChooser = new SendableChooser();
	SendableChooser autonomousSecondDefenseChooser = new SendableChooser();
	SendableChooser autonomousSecondPositionChooser = new SendableChooser();
	int autonomousDelay; // How long we delay
	long chevalDeFriseStartTime = -1; // This means the timer has not been set

	public void createChooser() {
		autonomousFirstDefenseChooser.addObject("Low Bar", new Integer(RobotMap.LOW_BAR_MODE));
		autonomousFirstDefenseChooser.addObject("Moat", new Integer(RobotMap.MOAT_MODE));
		autonomousFirstDefenseChooser.addObject("Ramparts", new Integer(RobotMap.RAMPARTS_MODE));
		autonomousFirstDefenseChooser.addObject("Rock Wall", new Integer(RobotMap.ROCK_WALL_MODE));
		autonomousFirstDefenseChooser.addObject("Rough Terrain", new Integer(RobotMap.ROUGH_TERRAIN_MODE));
		autonomousFirstDefenseChooser.addDefault("Do Nothing - First Chooser", new Integer(RobotMap.DO_NOTHING_MODE));
		SmartDashboard.putData("Autonomous First Defense Chooser", autonomousFirstDefenseChooser);

		autonomousFirstPositionChooser.addObject("First None", new Integer(RobotMap.NONE));
		autonomousFirstPositionChooser.addDefault("Position 1", new Integer(RobotMap.POSITION_1));
		autonomousFirstPositionChooser.addObject("Position 2", new Integer(RobotMap.POSITION_2));
		autonomousFirstPositionChooser.addObject("Position 3", new Integer(RobotMap.POSITION_3));
		autonomousFirstPositionChooser.addObject("Position 4", new Integer(RobotMap.POSITION_4));
		autonomousFirstPositionChooser.addObject("Position 5", new Integer(RobotMap.POSITION_5));
		SmartDashboard.putData("Autonomous First Position Chooser", autonomousFirstPositionChooser);

		autonomousSecondDefenseChooser.addObject("Low Bar", new Integer(RobotMap.LOW_BAR_MODE));
		autonomousSecondDefenseChooser.addObject("Moat", new Integer(RobotMap.MOAT_MODE));
		autonomousSecondDefenseChooser.addObject("Ramparts", new Integer(RobotMap.RAMPARTS_MODE));
		autonomousSecondDefenseChooser.addObject("Rock Wall", new Integer(RobotMap.ROCK_WALL_MODE));
		autonomousSecondDefenseChooser.addObject("Rough Terrain", new Integer(RobotMap.ROUGH_TERRAIN_MODE));
		autonomousSecondDefenseChooser.addDefault("Do Nothing - Second Chooser", new Integer(RobotMap.DO_NOTHING_MODE));
		SmartDashboard.putData("Autonomous Second Defense Chooser", autonomousSecondDefenseChooser);

		autonomousSecondPositionChooser.addObject("Second None", new Integer(RobotMap.NONE));
		autonomousSecondPositionChooser.addDefault("Position 1", new Integer(RobotMap.POSITION_1));
		autonomousSecondPositionChooser.addObject("Position 2", new Integer(RobotMap.POSITION_2));
		autonomousSecondPositionChooser.addObject("Position 3", new Integer(RobotMap.POSITION_3));
		autonomousSecondPositionChooser.addObject("Position 4", new Integer(RobotMap.POSITION_4));
		autonomousSecondPositionChooser.addObject("Position 5", new Integer(RobotMap.POSITION_5));
		SmartDashboard.putData("Autonomous Second Position Chooser", autonomousSecondPositionChooser);
	}

	public void checkChoices() {
		RobotMap.autonomousFirstDefenseMode = ((Integer) (autonomousFirstDefenseChooser.getSelected())).intValue();
		RobotMap.autonomousFirstPositionMode = ((Integer) (autonomousFirstPositionChooser.getSelected())).intValue();
		RobotMap.autonomousSecondDefenseMode = ((Integer) (autonomousSecondDefenseChooser.getSelected())).intValue();
		RobotMap.autonomousSecondPositionMode = ((Integer) (autonomousSecondPositionChooser.getSelected())).intValue();
		// These lines store the value of the Autonomous Chooser as an int

		switch (RobotMap.autonomousFirstDefenseMode) {
			case RobotMap.LOW_BAR_MODE:
				SmartDashboard.putString("Autonomous First Defense Mode", "Low Bar");
				break;
			case RobotMap.MOAT_MODE:
				SmartDashboard.putString("Autonomous First Defense Mode", "Moat");
				break;
			case RobotMap.RAMPARTS_MODE:
				SmartDashboard.putString("Autonomous First Defense Mode", "Ramparts");
				break;
			case RobotMap.ROCK_WALL_MODE:
				SmartDashboard.putString("Autonomous First Defense Mode", "Rock Wall");
				break;
			case RobotMap.ROUGH_TERRAIN_MODE:
				SmartDashboard.putString("Autonomous First Defense Mode", "Rough Terrain");
				break;
			case RobotMap.DO_NOTHING_MODE:
				SmartDashboard.putString("Autonomous First Defense Mode", "Do Nothing Mode");
			default:
				// This should never happen
				SmartDashboard.putString("Autonomous First Defense Mode", "Default error!!!");
				System.out.println("Default Autonomous First Defense Mode Error!!!");
				break;
		}

		switch (RobotMap.autonomousFirstPositionMode) {
			case RobotMap.NONE:
				SmartDashboard.putString("Autonomous First Position Mode", "None");
				break;
			case RobotMap.POSITION_1:
				SmartDashboard.putString("Autonomous First Position Mode", "Position 1");
				break;
			case RobotMap.POSITION_2:
				SmartDashboard.putString("Autonomous First Position Mode", "Position 2");
				break;
			case RobotMap.POSITION_3:
				SmartDashboard.putString("Autonomous First Position Mode", "Position 3");
				break;
			case RobotMap.POSITION_4:
				SmartDashboard.putString("Autonomous First Position Mode", "Position 4");
				break;
			case RobotMap.POSITION_5:
				SmartDashboard.putString("Autonomous First Position Mode", "Position 5");
				break;
			default:
				// This should never happen
				SmartDashboard.putString("Autonomous First Position Mode", "Default error!!!");
				System.out.println("Default Autonomous First Position Mode Error!!!");
				break;
		}

		switch (RobotMap.autonomousSecondDefenseMode) {
			case RobotMap.LOW_BAR_MODE:
				SmartDashboard.putString("Autonomous Second Defense Mode", "Low Bar");
				break;
			case RobotMap.MOAT_MODE:
				SmartDashboard.putString("Autonomous Second Defense Mode", "Moat");
				break;
			case RobotMap.RAMPARTS_MODE:
				SmartDashboard.putString("Autonomous Second Defense Mode", "Ramparts");
				break;
			case RobotMap.ROCK_WALL_MODE:
				SmartDashboard.putString("Autonomous Second Defense Mode", "Rock Wall");
				break;
			case RobotMap.ROUGH_TERRAIN_MODE:
				SmartDashboard.putString("Autonomous Second Defense Mode", "Rough Terrain");
				break;
			case RobotMap.DO_NOTHING_MODE:
				SmartDashboard.putString("Autonomous Second Defense Mode", "No Defense");
				return;
			default:
				// This should never happen
				SmartDashboard.putString("Autonomous Second Defense Mode", "Default error!!!");
				System.out.println("Default Autonomous Second Defense Mode Error!!!");
				break;
		} // switch brace

		switch (RobotMap.autonomousSecondPositionMode) {
			case RobotMap.NONE:
				SmartDashboard.putString("Autonomous Second Position Mode", "None");
				break;
			case RobotMap.POSITION_1:
				SmartDashboard.putString("Autonomous Second Position Mode", "Position 1");
				break;
			case RobotMap.POSITION_2:
				SmartDashboard.putString("Autonomous Second Position Mode", "Position 2");
				break;
			case RobotMap.POSITION_3:
				SmartDashboard.putString("Autonomous Second Position Mode", "Position 3");
				break;
			case RobotMap.POSITION_4:
				SmartDashboard.putString("Autonomous Second Position Mode", "Position 4");
				break;
			case RobotMap.POSITION_5:
				SmartDashboard.putString("Autonomous Second Position Mode", "Position 5");
				break;
			default:
				// This should never happen
				SmartDashboard.putString("Autonomous Second Position Mode", "Default error!!!");
				System.out.println("Default Autonomous Second Position Mode Error!!!");
				break;
		} // switch brace
	}

	public int getAutonomousDelay() {
		return autonomousDelay;
	}

	public static int getDefenseDistance(int defenseMode) {
		switch (defenseMode) {
			case RobotMap.LOW_BAR_MODE:
				return RobotMap.LOW_BAR_DEFENSE_DRIVE_DISTANCE;
			case RobotMap.MOAT_MODE:
				return RobotMap.MOAT_DEFENSE_DRIVE_DISTANCE;
			case RobotMap.RAMPARTS_MODE:
				return RobotMap.RAMPARTS_DEFENSE_DRIVE_DISTANCE;
			case RobotMap.ROCK_WALL_MODE:
				return RobotMap.ROCK_WALL_DEFENSE_DRIVE_DISTANCE;
			case RobotMap.ROUGH_TERRAIN_MODE:
				return RobotMap.ROUGH_TERRAIN_DEFENSE_DRIVE_DISTANCE;
			case RobotMap.DO_NOTHING_MODE:
				return 0;
			default:
				// This should never happen
				return 0;
		}
	}

	public static double getDefenseSpeed(int defenseMode) {
		switch (defenseMode) {
			case RobotMap.LOW_BAR_MODE:
				return RobotMap.LOW_BAR_DEFENSE_DRIVE_SPEED;
			case RobotMap.MOAT_MODE:
				return RobotMap.MOAT_DEFENSE_DRIVE_SPEED;
			case RobotMap.RAMPARTS_MODE:
				return RobotMap.RAMPARTS_DEFENSE_DRIVE_SPEED;
			case RobotMap.ROCK_WALL_MODE:
				return RobotMap.ROCK_WALL_DEFENSE_DRIVE_SPEED;
			case RobotMap.ROUGH_TERRAIN_MODE:
				return RobotMap.ROUGH_TERRAIN_DEFENSE_DRIVE_SPEED;
			case RobotMap.DO_NOTHING_MODE:
				return 0.0;
		}
		// This should never happen
		return 0.0;
	}

	public static int getDifferenceInPosition() {
		if (RobotMap.autonomousSecondPositionMode == 0 || RobotMap.autonomousFirstPositionMode == 0) {
			return 0;
		}
		return RobotMap.autonomousSecondPositionMode - RobotMap.autonomousFirstPositionMode;
		// Go right and turn clockwise is positive, go left and turn
		// counterclockwise is negative
		// This is the number of defense intervals (distance between two
		// adjacent defenses)
	}

}
