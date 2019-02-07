package org.usfirst.frc.team708.robot;

//import edu.wpi.first.wpilibj.DoubleSolenoid;
//import edu.wpi.first.wpilibj.Relay;

/**
 * Class containing all the code-related constants, so wiring and
 * gamepad controls are not included
 * @author omn0mn0m
 */
public final class Constants {

	/*
	 * General
	 */

	/*
	 * Drivetrain
	 */
	public static final double	DRIVE_MOTOR_MAX_SPEED 					= 1.0;  // 1.0
	public static final double	ROTATE_MOTOR_MAX_SPEED 					= 1.0;  // 1.0
	public static final double	TANK_STICK_TOLERANCE 					= .30;
	public static final int  DRIVETRAIN_WHEEL_DIAMETER 					= 6;  //4inch wheel * .56 gear ratio
	public static final double  DRIVETRAIN_ENCODER_PULSES_PER_REV	 	= 256.0;
	public static final boolean DRIVE_USE_SQUARED_INPUT 				= false;
	public static final boolean DRIVETRAIN_USE_LEFT_ENCODER				= true; // variable to determine which side encoder is on
	public static final double	VISION_ROTATE_MOTOR_SPEED				= 0.8;
	public static final double	DRIVE_MOTOR_OFF		 					= 0.0;
	public static final double	GRAYHILL_ENCODER_PULSES_PER_REVOLUTION 	= 256.0;
	public static final double	ENCODER_BOTTOM_POSITION 				= 0.0;
	public static final int		DT_HIGH_GEAR							= 1;
	public static final int		DT_LOW_GEAR								= 1;
			
	// PID Tuning parameters
	public static final double Kp = 0.0;		// Proportional gain
	public static final double Ki = 0.0;		// Integral gain
	public static final double Kd = 0.0;		// Derivative gain

	public static final double KpForward = 0.1;
	public static final double KiForward = 0.02;
	public static final double KdForward = 0.005;

	public static final double KpBackward = 0.1;
	public static final double KiBackward = 0.02;
	public static final double KdBackward = 0.005;
	
	public static final double pid_tolerance = 1;
	
	/*
	 * Elevator
	 */
	public static final double 	ELEVATOR_MOTOR_MIN_SPEED 		= .8;
	public static final double 	ELEVATOR_MOTOR_MAX_SPEED 		= 1.0;
	public static final double	ELE_DEADZONE 					= .6; 
	public static final double 	ELE_FORWARD 					= 1.0; 
	public static final double 	ELE_REVERSE 					= -1.0;
	public static final int	ELE_MAX							= 21000; //Change based on DESIGN (USE INCHES)
	public static final double	ELE_LV3							= 20000; //Change based on ROCKET (USE INCHES)
	public static final double	ELE_LV2							= 20000; //Change based on ROCKET (USE INCHES)
	public static final double	ELE_LV1							= 20000; //Change based on ROCKET (USE INCHES)
	public static final double	ELE_LV_DIFF						= 20000; //Change based on ROCKET (USE INCHES)
	public static final int ELE_ENC_STARTING_POSITION 			= 0;
	public static final int	ELE_TOLERANCE						= 1000;

	/*
	 * Intake
	 */
	public static final int	INTAKE_DEPLOY			= 1;
	public static final int	INTAKE_RETRACT			= 0;
	public static final int	HATCH_SECURE			= 1;
	public static final int	HATCH_RELEASE			= 0;
	public static final int	HATCH_EXTEND			= 1;
	public static final double BALL_FORWARD 		= 1.0;
	public static final double BALL_REVERSE 		= -0.8;
	public static final double BALL_OFF 			= 0.0;
	public static final double HATCH_FORWARD 		= 1.0;
	public static final double HATCH_REVERSE 		= -0.8;
	public static final double HATCH_OFF 			= 0.0;
	public static final double INTAKE_PULSE_TIME	= 0.5;

	/*
	 * Vision
	 */
	public static final double VISION_LED_ON				= 0.0;
	public static final double VISION_LED_OFF				= 1.0;
	public static final double VISION_LED_BLINK				= 2.0;
	public static final double VISION_PROCESSING_ON			= 0.0;
	public static final double VISION_PROCESSING_OFF		= 1.0;
	public static final double VISION_TARGET_NOT_FOUND		= 0.0;
	public static final double VISION_TARGET_FOUND			= 1.0;
	
	/*
	 * Smart Dashboard
	 */
	public static final double SEND_STATS_INTERVAL	= .5;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= false;
	   
}
