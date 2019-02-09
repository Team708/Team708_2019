package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToCargo extends Command {
	
    public ElevatorToCargo() {
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
        if (Robot.intake.hasHatch())
        {
            if (Robot.elevator.getEncoderDistance() <= (Math.abs(Constants.ELE_CARGO_HATCH)-Constants.ELE_TOLERANCE))
            {
                Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_UP);
                return false;
            }
            else if  (Robot.elevator.getEncoderDistance() > (Math.abs(Constants.ELE_CARGO_HATCH)-Constants.ELE_TOLERANCE)) {
                Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_DOWN);
                return false;
            }
            else 
            {
                Robot.elevator.stop();
                return true;
            }
        } 
        else if ((Robot.intake.hasBall()))
        {
            if (Robot.elevator.getEncoderDistance() <= (Math.abs(Constants.ELE_CARGO_BALL)-Constants.ELE_TOLERANCE))
            {
                Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_UP);
                return false;
            }
            else if (Robot.elevator.getEncoderDistance() > (Math.abs(Constants.ELE_CARGO_BALL)-Constants.ELE_TOLERANCE)) 
            {
               Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_DOWN);
               return false;
            }
            else 
            {
                Robot.elevator.stop();
                return true;
            }
        }
        else 
        {
            Robot.elevator.stop();
            return true;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.elevator.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}