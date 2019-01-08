package org.usfirst.frc.team708.robot.commands.arm;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
//import org.team708.robot.subsystems.arm.*;
//import org.usfirst.frc.team708.robot.subsystems.Loader;
import org.usfirst.frc.team708.robot.util.Gamepad;

//import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ControlArmDown extends Command {

    public ControlArmDown() {
//    	requires(Robot.arm);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.arm.armDown()) {
        	Robot.arm.stop();
        	Robot.arm.resetArmEncoder();
        	return true;
    	}	
    	else
    	{
        	Robot.arm.moveMotor(Constants.ARM_REVERSE); 
    	    return false;
    	}
    }
    // Called once after isFinished returns true
    protected void end() {
     Robot.arm.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
