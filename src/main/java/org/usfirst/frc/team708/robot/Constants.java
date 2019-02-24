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
	public static final double	TANK_STICK_TOLERANCE 					= .20;
	public static final int  	DRIVETRAIN_WHEEL_DIAMETER 				= 4; //4inch wheel * (LOW 10.44,  HIGH 4.87)
	public static final double  DRIVETRAIN_GEAR_RATIO_LOW				= 10.44;
	public static final double  DRIVETRAIN_GEAR_RATIO_HIGH				= 4.87;
	public static final double  DRIVETRAIN_ENCODER_PULSES_PER_REV	 	= 42.0;
	public static final boolean DRIVE_USE_SQUARED_INPUT 				= false;
	public static final boolean DRIVETRAIN_USE_LEFT_ENCODER				= true; // variable to determine which side encoder is on
	public static final double	DRIVE_MOTOR_OFF		 					= 0.0;
	public static final double	GRAYHILL_ENCODER_PULSES_PER_REVOLUTION 	= 256.0;
	public static final double	ENCODER_BOTTOM_POSITION 				= 0.0;
	public static final int		DT_HIGH_GEAR							= 1;
	public static final int		DT_LOW_GEAR								= 0;
	public static final double	PITCH_MAX								= 6.0;
	public static final double	ROLL_MAX								= 6.0;

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
	public static final double 	ELEVATOR_MOTOR_DOWN		 	=  -1.0;
	public static final double 	ELEVATOR_STOP 				= 0.0;
	public static final double	ELE_DEADZONE 				=  .5; 
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
	public static final int ELE_ENC_STARTING_POSITION 		= 0;
	public static final int ELE_ENC_MAX				 		= 600;
	public static final int ELE_ENC_MIN				 		= 0;
	public static final int	ELE_TOLERANCE					= 5;
	public static final double ELE_P						= 0.1;
	public static final double ELE_I						= 0.0001;
	public static final double ELE_D						= 1.0;
	public static final double ELE_Iz						= 0;
	public static final double ELE_FF						= 0;

	/*
	 * Intake
	 */
	public static final double	HATCH_IN			=  1.0;
	public static final double	HATCH_OUT			= -1.0;
	public static final double 	HATCH_STOP 			=  0.0;

	public static final double BALL_IN 				=  1.0;
	public static final double BALL_OUT 			= -1.0;
	public static final double BALL_STOP 			=  0.0;

	public static final int	INTAKE_HATCH_IN		=  0;
	public static final int	INTAKE_HATCH_OUT	=  1;
	
	public static final int	INTAKE_BALL_IN		=  0;
	public static final int	INTAKE_BALL_OUT		=  1;

	/*
	 * Cimber
	 */
	public static final int		REAR_CLIMBER_ROLLER_FIRST_DISTANCE	=  60;  //inches encoder value
	public static final int		REAR_CLIMBER_ROLLER_SECOND_DISTANCE	=  1000; //inches encoder value
	public static final int		REAR_CLIMBER_ROLLER_DISTANCE_INCHES	=  1000; //inches encoder value
	public static final int		REAR_CLIMBER_ROLLER_DISTANCE_FINAL	=  12; //inches encoder value
	public static final double 	CLIMBER_ROLLER_FORWARD				=  0.6;		
	public static final double 	CLIMBER_ROLLER_BACKWARD				=  -0.6;		

	public static final int		MOVE_CLIMBER_FORWARD				=  18;
	public static final double	MOVE_CLIMBER_TOLERANCE				=  .5;
	public static final double	MOVE_CLIMBER_EXTEND					=  -1.0;
	public static final double	MOVE_CLIMBER_RETRACT				=  1.0;
	public static final double	STOP_CLIMBER						= 0.0;

	public static final double ROLLER_GEAR_RATIO					= 16.0;	//16:1
	public static final double ROLLER_DIAMETER						= 2.5;	//Diameter in inches
	public static final int ROLLER_ENCODER_COUNTS_PER_REV			= 12;	//12 ticks, lol

	/*
	 * Vision
	 */
	public static final int VISION_LED_ON				= 0;
	public static final int VISION_LED_OFF				= 1;
	public static final int VISION_LED_BLINK			= 2;
	public static final int VISION_PIPELINE_0			= 0;
	public static final int VISION_PIPELINE_1			= 1;
	public static final int VISION_TARGET_NOT_FOUND		= 0;
	public static final int VISION_TARGET_FOUND			= 1;

	public static final double Y_TARGET 				= 190.0;		
	public static final double Y_THRESHOLD				=   6.0;		//Target angle in degrees
	public static final double X_THRESHOLD 				=   6.0;		//Target angle in degrees
	public static final double AREA_THRESHOLD			= 	6.0;
	public static final double ROCKET_HATCH_TARGET_AREA			=	20.0;
	public static final double ROCKET_CARGO_TARGET_AREA			=	20.0;


	public static final double VISION_MOVE			= .6;
	public static final double VISION_ROTATE		= .4;

	public static final int ALLIANCE_RED 	 	=	1;
	public static final int ALLIANCE_BLUE 	 	=	-1;
	/*
	 * Smart Dashboard
	 */
	public static final double SEND_STATS_INTERVAL	= .2;	// Interval for reporting in seconds
	public static final boolean DEBUG 				= true;
	   
}
