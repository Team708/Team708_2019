package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToLevel1 extends Command {
	
    public ElevatorToLevel1() {
    	requires(Robot.elevator);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	}    	

    protected boolean isFinished()
    {   
        Robot.elevator.goToPosition(Constants.ELE_LVL1);
        if (Robot.elevator.getEncoderDistance() <= (Math.abs(Constants.ELE_LVL1)-Constants.ELE_TOLERANCE))
            return false;
        else if (Robot.elevator.getEncoderDistance() > (Math.abs(Constants.ELE_LVL1)+Constants.ELE_TOLERANCE))
            return false;
        else 
            return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}