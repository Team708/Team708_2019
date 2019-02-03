package org.usfirst.frc.team708.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 *  */
public class RobotMap {
	
// Gamepad USB ports

	public static final int driverGamepad   = 0;
	public static final int operatorGamepad = 1;
	public static final int climbingGamepad = 2;
	
// PWM Ports

//	public static final int 			 	= 0;
//	public static final int 			 	= 1;
//	public static final int  			 	= 2;
//	public static final int  				= 3;
//	public static final int  				= 4;
//	public static final int  				= 5;
//	public static final int  				= 6;
//	public static final int  				= 7;
//	public static final int  				= 8;
//	public static final int  				= 9;
	
// Motor Controler Device IDS

// Drivetrain
	public static final int drivetrainLeftMotorMaster  		= 11; 
	public static final int drivetrainLeftMotorSlave1  		= 12; 
	
	public static final int drivetrainRightMotorMaster  	= 14;
	public static final int drivetrainRightMotorSlave1	 	= 15;

// Elevator 
	public static final int elevatorMotorMaster	 			= 21;
	public static final int elevatorMotorSlave1	 			= 22;

// Ball Intake
	public static final int ballIntakeMotorMaster			= 31; 
	public static final int ballIntakeMotorSlave1			= 32; 
	
// Hatch Intake
	public static final int HatchIntakeeMotorMaster			= 41; 
	public static final int HatchIntakeMotorSlave			= 42; 

// Climber
	public static final int ClimberLeftFrontMotorMaster		= 51; 
	public static final int ClimberRightFrontMotorMaster	= 52; 
	public static final int ClimberLeftRearMotorMaster		= 53; 
	public static final int ClimberRightRearMotorSlave		= 54; 


// Digital IO
	public static final int drivetrainEncoderARight		= 0;  	
	public static final int drivetrainEncoderBRight		= 1;
	public static final int drivetrainEncoderALeft		= 2;
	public static final int drivetrainEncoderBLeft		= 3;
	public static final int elevatorEncoderA			= 4;
	public static final int elevatorEncoderB			= 5;	
// public static final int 								= 6;
// public static final int								= 7;	
// public static final int 					 			= 8;  
// public static final int 								= 9;
	
	
// RELAY
//	public static final int 			 	= 0;
//	public static final int 			 	= 1;
//	public static final int 			 	= 2;
//	public static final int 			 	= 3;
	
// Analog sensor IDs
//	public static final int 					= 0;
//	public static final int 					= 1;
//	public static final int 					= 2;
//	public static final int 					= 3;
	
// PCM Ports
	public static final int driveShiftLow		= 0; 	//Shifts Drivetrain to Low gear
    public static final int driveShiftHigh		= 1; 	//Shifts Drivetrain to High gear
    public static final int intake				= 2;	//actuate intake ball and hatch
	public static final int beak				= 3;	//Open close Beak
	public static final int hatch				= 4;	//actuate Hatch launcher
//	public static final int 					= 5;
//	public static final int 					= 6;
//	public static final int 			        = 7;
	
	
}
