package org.usfirst.frc.team972.robot;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	// motors
	
	// Switch using real robot
	static Victor leftMotor = new Victor(0);
	static Victor rightMotor = new Victor(1);
//	public static Victor frontLeftMotor = new Victor(RobotMap.FRONT_LEFT_MOTOR_CAN_ID);
//	// public static Victor frontLeftMotor = new
//	// Victor(RobotMap.FRONT_LEFT_MOTOR_CAN_ID);
//	// NOTE: this is called a PID Reverse CAN Talon because PIDControllers
//	// cannot reverse output on their own.
//	// Therefore, we subclassed the Victor object and reversed all PID Outputs
//	// Normal outputs are NOT reversed on a PIDReverseVictor
//	// See source code for this subclass at bottom of Robot.java
//	public static Victor frontRightMotor = new Victor(RobotMap.FRONT_RIGHT_MOTOR_CAN_ID);
//	// public static PIDReverseVictor frontRightMotor = new
//	// PIDReverseVictor(RobotMap.FRONT_RIGHT_MOTOR_CAN_ID);
//	public static Victor backLeftMotor = new Victor(RobotMap.BACK_LEFT_MOTOR_CAN_ID);
//	public static Victor backRightMotor = new Victor(RobotMap.BACK_RIGHT_MOTOR_CAN_ID);

	public static RobotDrive botDrive = new RobotDrive(leftMotor, rightMotor);

//	public static RobotDrive botDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

	// encoders and PID
	public static Encoder rightDriveEncoder = new Encoder(RobotMap.RIGHT_DRIVE_ENCODER_DIO_A_PORT, RobotMap.RIGHT_DRIVE_ENCODER_DIO_B_PORT);
	public static Encoder leftDriveEncoder = new Encoder(RobotMap.LEFT_DRIVE_ENCODER_DIO_A_PORT, RobotMap.LEFT_DRIVE_ENCODER_DIO_B_PORT);

	// intake
	static AutonomousChooser autonomousChooserSystem = new AutonomousChooser();

	// speeds and multipliers
	double driveMultiplier = RobotMap.DEFAULT_DRIVE_MODE;
	double leftDriveSpeed = 0.0;
	double rightDriveSpeed = 0.0;
	// camera stuff

	static long autonomousDelayStartTime;
	
	// set distance variables
	boolean leftDistance = false;
	boolean goingSetDistance = false;
	
	public void robotInit() {
		autonomousChooserSystem.createChooser();
	}
	

	public void autonomousInit() {
		System.out.println("Autonomous Init");
		botDrive.setSafetyEnabled(false); // Prevents "output not updated enough" error message

		autonomousChooserSystem.checkChoices();
//		Autonomous.startAutonomous(this, autonomousChooserSystem);
		leftDriveEncoder.reset();
		rightDriveEncoder.reset();
		while(isEnabled()) {
			botDrive.tankDrive(0.3, -0.3); // 0.25 does not work, too slow
			// Left +, Right - = Turn Right; Left -, Right + = Turn Left
			// Encoder Values with plus/minus 0.3: le = 1161.000, re = -588.000
			SmartDashboard.putNumber("le", leftDriveEncoder.get());
			SmartDashboard.putNumber("re", rightDriveEncoder.get());
		}
		botDrive.setSafetyEnabled(false); // Prevents "output not updated
											// enough" error message
	}

	public static void printEverything() {
		// SmartDashboard.putNumber("Back Left Motor Speed",
		// backLeftMotor.get());
		// SmartDashboard.putNumber("Back Right Motor Speed",
		// backRightMotor.get());
		// SmartDashboard.putNumber("Front Left Motor Speed",
		// frontLeftMotor.get());
		// SmartDashboard.putNumber("Front Right Motor Speed",
		// frontRightMotor.get());
		SmartDashboard.putNumber("Left Encoder Value", leftDriveEncoder.get());
		SmartDashboard.putNumber("Right Encoder Value", rightDriveEncoder.get());
		SmartDashboard.putNumber("Left Encoder Rate", leftDriveEncoder.getRate());
		SmartDashboard.putNumber("Right Encoder Rate", rightDriveEncoder.getRate());
		try {
		} catch (Exception e) {
			System.out.println("Gyro failed: " + e);
		}
	}
	
	public void disabledInit() {
		RobotMap.autonomousMode = RobotMap.LOWER_OBSTACLE_MOTOR_MODE;
	}
}
