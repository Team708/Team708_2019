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
	public static final int  	DRIVETRAIN_WHEEL_DIAMETER 				= 6;  	//4inch wheel * .56 gear ratio
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
	public static final double 	ELEVATOR_MOTOR_UP		 	=  1.0;
	public static final double 	ELEVATOR_MOTOR_DOWN		 	= -1.0;
	public static final double	ELE_DEADZONE 				=   .6; 
	public static final int		ELE_MAX						= 400;
	public static final double	ELE_HATCH_LVL3				= 300; 
	public static final double	ELE_HATCH_LVL2				= 200; 
	public static final double	ELE_HATCH_LVL1				= 100; 
	public static final double	ELE_BALL_LVL1				= 150; 
	public static final double	ELE_BALL_LVL2				= 250; 
	public static final double	ELE_BALL_LVL3				= 350; 
	public static final double	ELE_LVL0					=  25; 
	public static final double	ELE_FEEDER					=  50; 
	public static final double	ELE_GROUND					=   0; 
	public static final double	ELE_CARGO_BALL				= 150; 
	public static final double	ELE_CARGO_HATCH				= 100; 
	public static final int ELE_ENC_STARTING_POSITION 		= 100;
	public static final int ELE_ENC_MAX				 		= 600;
	public static final int ELE_ENC_MIN				 		= 0;
	public static final int	ELE_TOLERANCE					= 10;

	/*
	 * Intake
	 */
	public static final int		HATCH_IN			=  1;
	public static final int		HATCH_OUT			= -1;
	public static final double 	HATCH_STOP 			=  0;

	public static final double BALL_IN 				=  1.0;
	public static final double BALL_OUT 			= -1.0;
	public static final double BALL_STOP 			=  0.0;

	public static final int		INTAKE_HATCH_IN		=  0;
	public static final int		INTAKE_HATCH_OUT	=  1;
	
	public static final int		INTAKE_BALL_IN		=  0;
	public static final int		INTAKE_BALL_OUT		=  1;

	/*
	 * Cimber
	 */
	public static final int		REAR_CLIMBER_ROLLER_DISTANCE	=  10;
	public static final int		MOVE_CLIMBER_FORWARD			=  18;
	public static final int		MOVE_CLIMBER_TOLERANCE			=  50;
	public static final double	MOVE_CLIMBER_EXTEND				=  1.0;
	public static final double	MOVE_CLIMBER_RETRACT			=  -1.0;

	/*
	 * Vision
	 */
	public static final double VISION_LED_ON				= 0.0;
	public static final double VISION_LED_OFF				= 1.0;
	public static final double VISION_LED_BLINK				= 2.0;
	public static final double VISION_PIPELINE_0			= 0.0;
	public static final double VISION_PIPELINE_1			= 1.0;
	public static final double VISION_TARGET_NOT_FOUND		= 0.0;
	public static final double VISION_TARGET_FOUND			= 1.0;
	
	/*
	 * Smart Dashboard
	 */
	public static final double SEND_STATS_INTERVAL	= .2;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= false;
	   
}
