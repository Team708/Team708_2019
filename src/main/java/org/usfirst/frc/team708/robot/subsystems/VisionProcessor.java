package org.usfirst.frc.team708.robot.subsystems;


import org.usfirst.frc.team708.robot.AutoConstants;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.util.Math708;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *	@author Viet Tran
 */
public class VisionProcessor extends Subsystem {
	
	public static Drivetrain 		drivetrain;
	
	private boolean isCentered 	= false;
	private boolean led 		= false;
	private boolean isAtY		= false;

//	Required Network Table Data 	
	private boolean seesTarget;	//Get from Network Table
	private double tv;
	private double xAngle;	//Get from Network Table
	private double yAngle;

//	Accessing the Limelight's Network Table	
	NetworkTableInstance 	limeLightInstance 	= NetworkTableInstance.getDefault();
	NetworkTable			limeLightTable		= limeLightInstance.getTable("/limelight");
	
//	Sweep function variables
//	private double gyroAngle = 0.0;
    	
	private double rotate			= 0.0;
	private double move				= 0.0;
	
//	Method for getting different data from a Network Table	
	public double getNTInfo(String tableInfo) {
		NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);		
		return limeLightEntry.getDouble(0);	
		 
	}
	
//	Method for setting different data into a Network Table    
	public void setNTInfo(String tableInfo, Double setValue) {
		NetworkTableEntry limeLightEntry = limeLightTable.getEntry(tableInfo);		
		limeLightEntry.setNumber(setValue);
	}
	
	public VisionProcessor() {
		super("Vision Processor");
	}

	public boolean seesTarget() {
		tv = getNTInfo("tv");
		if (tv != 0.0) {
			seesTarget = true;
		}
		else {
			seesTarget = false;
		}
		return seesTarget;
	}
	public boolean isCentered() {
	
		xAngle = getNTInfo("tx");
	
		if (Math.abs(xAngle) <= AutoConstants.X_THRESHOLD) {
			isCentered = true;
		}
		else {
			isCentered = false;
		}
		return isCentered;
	}
	public double getRotate() {	
		rotate=0.0;
		isCentered();
		if (seesTarget()) {
			if (!isCentered())	
			     if (xAngle > 0)
				     rotate = .5;
			     else 
			 	    rotate = -.5;
			else // centered
				rotate= 0.0;
		} 
		return rotate;
	}
	public void toggleLEDMode() {
		led = !led;
		if(led) {
			setNTInfo("ledMode", Constants.VISION_LED_ON);
		}
		else {
			setNTInfo("ledMode", Constants.VISION_LED_OFF);
		}
	}

	public boolean isAtY() {
	
		yAngle = getNTInfo("ty");
	
		if (Math.abs(yAngle) <= AutoConstants.Y_THRESHOLD) {
			isAtY = true;
		}
		else {
			isAtY = false;
		}
		return isAtY;
	}
	public double getMove() {	
		move=0.0;
		isAtY();
		if (seesTarget()) {
			if (!isAtY())	
			     if (yAngle < 0)
				     move = .7;
			     else 
			 	    move = -.7;
			else // centered
				move= 0.0;
		} 
		return move;
	}


	public void sendToDashboard() {		//Might have to rewrite public variables for the smart dashboard...
										//Future me problem		-Viet
		
		SmartDashboard.putBoolean("Is Centered", isCentered());
		SmartDashboard.putNumber("Displacement X", xAngle);
		SmartDashboard.putBoolean("Has Target", seesTarget());
		SmartDashboard.putNumber("TV", tv);
	}

    public void initDefaultCommand() {
		if (Constants.DEBUG) {
		}    	
    }
    
}

