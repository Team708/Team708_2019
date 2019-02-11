package org.usfirst.frc.team708.robot;

import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;
import org.usfirst.frc.team708.robot.commands.climber.*;
import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.elevator.*;
import org.usfirst.frc.team708.robot.commands.intake.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
// Gamepads
	public final static Gamepad driverGamepad 	= new Gamepad(RobotMap.driverGamepad);	// Driver gamepad
	public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);// Operator gamepad
	public final static Gamepad climbingGamepad = new Gamepad(RobotMap.climbingGamepad);// Operator gamepad

// look in Gamepad.java for button constants
	
/*
 * Driver Button Assignment
 */

// Driver Buttons
	private static final int BALL_OUT_BUTTON  	 		    = Gamepad.button_R_Shoulder;
	private static final int BALL_IN_BUTTON					= Gamepad.button_L_Shoulder;
	private static final int HOLDGEARHIGH					= Gamepad.shoulderAxisLeft;;
//	private static final int 								= Gamepad.shoulderAxisRight;;
	private static final int SHIFT_DRIVETRAIN_HIGH_BUTTON 	= Gamepad.button_Y;
	private static final int DEPLOY_INTAKE_BUTTON			= Gamepad.button_B;
	private static final int SHIFT_DRIVETRAIN_LOW_BUTTON	= Gamepad.button_A;
	private static final int RETRACT_INTAKE_BUTTON			= Gamepad.button_X;

	private static final int TOGGLE_BRAKES_BUTTON			= Gamepad.button_Back;

/*
 * Operator Button Assignment
 */
	
	
//	private static final int 								= Gamepad.shoulderAxisLeft;;
//	private static final int 								= Gamepad.shoulderAxisRight;

	private static final int HATCH_IN_BUTTON				= Gamepad.button_L_Shoulder;
	private static final int HATCH_OUT_BUTTON				= Gamepad.button_R_Shoulder;

	private static final int CARGO_SHIP_BUTTON				= Gamepad.button_X;
	private static final int LEVEL_1_ROCKET_BUTTON			= Gamepad.button_A;
	private static final int LEVEL_2_ROCKET_BUTTON			= Gamepad.button_B;
	private static final int LEVEL_3_ROCKET_BUTTON			= Gamepad.button_Y;

	private static final int ELEVATOR_OVERIDE_BUTTON		= Gamepad.leftStick_Y;

// private static final int OPEN_BEAK_BUTTON				= Gamepad.button_Back;
// private static final int CLOSE_BEAK_BUTTON				= Gamepad.button_Start;
	private static final int FIND_HATCH_BUTTON				= Gamepad.button_Back;
	private static final int FIND_BALL_BUTTON				= Gamepad.button_Start;

	private static final int DEPLOY_HATCH					= Gamepad.button_RightStick;
	private static final int LEVEL_0_ELEV_BUTTON			= Gamepad.button_LeftStick;

	private static final int INITIATE_CLIMB					= Gamepad.button_A;	
	private static final int STOP_CLIMB						= Gamepad.button_B;	
//	private static final int OPENBUTTON1_BUTTON				= Gamepad.button_Back;
// 	private static final int OPENBUTTON2_BUTTON				= Gamepad.button_Start;
	private static final int CLIMBER_BUTTON					= Gamepad.leftStick_Y;
	private static final int CLIMBER_FORWARD_BUTTON			= Gamepad.rightStick_X;	
/*
 * Driver Button events
 */
	public static final Button ballOut			= new JoystickButton(driverGamepad, BALL_OUT_BUTTON);
	public static final Button ballIn			= new JoystickButton(driverGamepad, BALL_IN_BUTTON);
	public static final Button highGear		 	= new JoystickButton(driverGamepad, SHIFT_DRIVETRAIN_HIGH_BUTTON);
	public static final Button lowGear			= new JoystickButton(driverGamepad, SHIFT_DRIVETRAIN_LOW_BUTTON);
	public static final Button intakeOut		= new JoystickButton(driverGamepad, DEPLOY_INTAKE_BUTTON);
	public static final Button intakeIn			= new JoystickButton(driverGamepad, RETRACT_INTAKE_BUTTON);
	public static final Button toggleBrakes		= new JoystickButton(driverGamepad, TOGGLE_BRAKES_BUTTON);

	public static final Trigger highGearEngaged	= new AxisUp(driverGamepad, HOLDGEARHIGH);
	public static final Trigger highGearRelease	= new AxisDown(driverGamepad, HOLDGEARHIGH);
//	public static final Trigger lowGear			= new AxisUp(driverGamepad, HOLDGEARHIGH);
//	public static final Trigger lowGear			= new AxisDown(driverGamepad, HOLDGEARHIGH);

/*
 * Operator Button events
 */
	public static final Button hatchIn			= new JoystickButton(operatorGamepad, HATCH_IN_BUTTON);
	public static final Button hatchOut			= new JoystickButton(operatorGamepad, HATCH_OUT_BUTTON);
	public static final Button CargoShip		= new JoystickButton(operatorGamepad, CARGO_SHIP_BUTTON);
	public static final Button level1Rocket		= new JoystickButton(operatorGamepad, LEVEL_1_ROCKET_BUTTON);
	public static final Button level2Rocket		= new JoystickButton(operatorGamepad, LEVEL_2_ROCKET_BUTTON);
	public static final Button level3Rocket		= new JoystickButton(operatorGamepad, LEVEL_3_ROCKET_BUTTON);
	public static final Button findHatch		= new JoystickButton(operatorGamepad, FIND_HATCH_BUTTON);
	public static final Button findBall			= new JoystickButton(operatorGamepad, FIND_BALL_BUTTON);
	public static final Button deployHatch 		= new JoystickButton(operatorGamepad, DEPLOY_HATCH);
	public static final Button elevatorGround	= new JoystickButton(operatorGamepad, LEVEL_0_ELEV_BUTTON);

	public static final Trigger elevatorUp		= new AxisUp(operatorGamepad, ELEVATOR_OVERIDE_BUTTON);
	public static final Trigger elevatorDown	= new AxisDown(operatorGamepad, ELEVATOR_OVERIDE_BUTTON);


	public static final Button initiateClimb	= new JoystickButton(climbingGamepad, INITIATE_CLIMB);
	public static final Button stopClimb		= new JoystickButton(climbingGamepad, STOP_CLIMB);
	public static final Trigger climbUp			= new AxisUp(climbingGamepad, 	CLIMBER_BUTTON);
	public static final Trigger climbDown		= new AxisDown(climbingGamepad, CLIMBER_BUTTON);
	public static final Trigger climbForward	= new AxisUp(climbingGamepad, 	CLIMBER_FORWARD_BUTTON);
	public static final Trigger climbBack		= new AxisDown(climbingGamepad, CLIMBER_FORWARD_BUTTON);

	public OI() {

// Driver
		ballOut.whileHeld(new IntakeBallOut());
		ballIn.whileHeld(new IntakeBallIn());
		highGear.whenPressed(new GearHigh());
		lowGear.whenPressed(new GearLow());
		intakeIn.whenPressed(new DeployIntake());
		intakeOut.whenPressed(new RetractIntake());
		toggleBrakes.whenPressed(new ToggleBrakeMode());

	//  highGearHeld.whileActive(new GearHigh());	
		highGearEngaged.whileActive(new GearHigh());
		highGearEngaged.whileActive(new GearLow());
	//	lowGear.whileHeld(new GearHigh());
	//	lowGear.whenReleased(new GearLow());

//Operator

		hatchIn.whileHeld(new IntakeHatchIn());
		hatchOut.whileHeld(new IntakeHatchOut());
		CargoShip.whenPressed(new ElevatorToCargo());
		level1Rocket.whenPressed(new ElevatorToLevel1());
		level2Rocket.whenPressed(new ElevatorToLevel2());
		level3Rocket.whenPressed(new ElevatorToLevel3());
		findHatch.whenPressed(new FindHatch());
		findBall.whenPressed(new FindBall());
		deployHatch.whenPressed(new DeployHatch());
		elevatorGround.whenPressed(new ElevatorToLevel0());
				
		initiateClimb.whenPressed(new InitiateClimb());
		stopClimb.whenPressed(new ClimberStop());

		elevatorUp.whileActive(new JoystickMoveElevator());
		elevatorDown.whileActive(new JoystickMoveElevator());

		climbUp.whileActive(new JoystickMoveClimb());
		climbDown.whileActive(new JoystickMoveClimb());
		
		climbForward.whileActive(new JoystickMoveClimb()); 
		climbBack.whileActive(new JoystickMoveClimb()); 

/*
 		.whileActive(new 
		.whenPressed(new
		.whileHeld(new
		.whenReleased(new 
*/
		}
}

