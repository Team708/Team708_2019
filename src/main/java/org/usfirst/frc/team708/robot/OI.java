

package org.usfirst.frc.team708.robot;


import edu.wpi.first.wpilibj.buttons.*;
import org.usfirst.frc.team708.robot.util.Gamepad;
import org.usfirst.frc.team708.robot.util.triggers.*;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
// Gamepads
	public final static Gamepad driverGamepad 	= new Gamepad(RobotMap.driverGamepad);	// Driver gamepad
	public final static Gamepad operatorGamepad = new Gamepad(RobotMap.operatorGamepad);// Operator gamepad
	
// look in Gamepad.java for button constants
	
/*
 * Driver Button Assignment
 */

// Drivetrain Buttons
	private static final int BALL_OUT_BUTTON  	 		    = Gamepad.button_R_Shoulder;
	private static final int BALL_IN_BUTTON					= Gamepad.button_L_Shoulder;
	private static final int HOLDDRIVETRAINHIGH				= Gamepad.shoulderAxisLeft;;
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
	private static final int CLIMBER_BUTTON					= Gamepad.rightStick_Y;
	private static final int CLIMBER_FORWARD_BUTTON			= Gamepad.rightStick_X;

	private static final int SECURE_RELEASE_HATCH_BUTTON	= Gamepad.button_Back;
	private static final int DEPLOY_HATCH_BUTTON			= Gamepad.button_Start;

//	private static final int 								= Gamepad.button_RightStick;
//	private static final int INITIATE_CLIMB					= Gamepad.button_LeftStick;
	
	
/*
 * Driver Button Commands
 */
	public static final Button butterflyOn	= new JoystickButton(driverGamepad, BUTTERFLY_BUTTON);
	public static final Button highGearOn	= new JoystickButton(driverGamepad, HIGH_GEAR_BUTTON);
	public static final Button lowGearOn 	= new JoystickButton(driverGamepad, LOW_GEAR_BUTTON);
	public static final Button breakOn	 	= new JoystickButton(driverGamepad, BRAKE_BUTTON);
	public static final Button ledToggle	= new JoystickButton(driverGamepad, LED_BUTTON);
	public static final Button releaseCube	= new JoystickButton(driverGamepad, RELEASE_CUBE_BUTTON);

/*
 * Operator Button Commands
 */
	public static final Button squeezeCube		= new JoystickButton(operatorGamepad, SQUEEZE_CUBE_BUTTON);
	public static final Button intakeCubeOut	= new JoystickButton(operatorGamepad, INTAKE_CUBE_BUTTON_OUT);
	public static final Button intakeCubeIn		= new JoystickButton(operatorGamepad, INTAKE_CUBE_BUTTON_IN);
	
	public static final Trigger operateArmUp 	= new AxisUp(operatorGamepad, OPERATE_ARM_BUTTON);
	public static final Trigger operateArmDown	= new AxisDown(operatorGamepad, OPERATE_ARM_BUTTON);
	public static final Trigger operateTeleUp	= new AxisUp(operatorGamepad, OPERATE_TELESCOPE_BUTTON);
	public static final Trigger operateTeleDown	= new AxisDown(operatorGamepad, OPERATE_TELESCOPE_BUTTON);
	public static final Button armToGround		= new JoystickButton(operatorGamepad, ARM_UP_TO_GROUND_BUTTON);
	public static final Button armToSwitch		= new JoystickButton(operatorGamepad, ARM_UP_TO_SWITCH_BUTTON);
	public static final Button armToScale		= new JoystickButton(operatorGamepad, ARM_UP_TO_SCALE_BUTTON);
	public static final Button armToFeeder		= new JoystickButton(operatorGamepad, ARM_UP_TO_HUMAN_FEEDER_BUTTON);
	public static final Button climbLowGear		= new JoystickButton(operatorGamepad, CLIMB_LOW_GEAR_BUTTON);
	public static final Button climbHighGear	= new JoystickButton(operatorGamepad, CLIMB_HIGH_GEAR_BUTTON);


	public OI() {

		butterflyOn.whenPressed(new ActivateButterfly());
		
	//	highGearOn.whenPressed(new GearShift2());
		highGearOn.whenReleased(new GearShift2());
		highGearOn.whileHeld(new GearShift1());

	//	lowGearOn.whenPressed(new GearShift1());
		lowGearOn.whenReleased(new GearShift2());
		lowGearOn.whileHeld(new GearShift1());

		
		climbHighGear.whenPressed(new ShiftClimberLow());
		climbLowGear.whenPressed(new ShiftClimberHigh());

		breakOn.whenPressed(new ToggleBrakeMode());
		ledToggle.whenPressed(new ToggleLEDMode());
		
		releaseCube.whenPressed(new ReleaseCube());
		squeezeCube.whenPressed(new ReleaseCube());

		intakeCubeIn.whileHeld(new IntakeIn());
		intakeCubeOut.whileHeld(new IntakeOut());
//		operateArmDown.whileActive(new JoystickMoveArm());
//		operateArmUp.whileActive(new JoystickMoveArm());
//		operateTeleDown.whileActive(new JoystickMoveTele());
//		operateTeleUp.whileActive(new JoystickMoveTele());
		
// jame's version this works - start both when armToScale is pressed		
//		armToScale.whenPressed(new ControlArmToScale());
//		armToScale.whenPressed(new ControlTeleToScale()); //Test if armToScale works with two calls
//		armToGround.whenPressed(new ControlArmToGround());
//		armToSwitch.whenPressed(new ControlArmToSwitch());	
//		armToFeeder.whenPressed(new ControlArmToFeeder());
		
// sue's version - trying to call a command to do both things off the button press
//		armToGround.whenPressed(new MoveArmTeleToGroundCG());
//		armToSwitch.whenPressed(new MoveArmTeleToSwitchCG());
//		armToScale.whenPressed(new MoveArmTeleToScaleCG());		
//		armToFeeder.whenPressed(new MoveArmTeleToFeederCG());
		
		climbLowGear.whileActive(new ShiftClimberLow()); 
		climbHighGear.whileActive(new ShiftClimberHigh()); 

/*
 		.whileActive(new 
		.whenPressed(new
		.whileHeld(new
		.whenReleased(new 
*/
		}
}

