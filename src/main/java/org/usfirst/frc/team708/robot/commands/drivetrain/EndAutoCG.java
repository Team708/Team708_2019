package org.usfirst.frc.team708.robot.commands.drivetrain;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EndAutoCG extends Command{
        
    public EndAutoCG() {    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.runningAuto = false;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
   }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}