package org.usfirst.frc.team708.robot.commands.elevator;

import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ElevatorToLevel3 extends Command {
	
    public ElevatorToLevel3() {
    	requires(Robot.elevator);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// if(Robot.arm.getAngle() >= Constants.SCALE_HEIGHT - Constants.ARM_TOLERANCE)
    	// 	Robot.arm.moveMotor(Constants.ARM_REVERSE);
    	// else

    	}    	

    // Make this return true when this Command no longer needs to run execute()
    //Have a range in case the RIO can't get the angle exact.
    // if ((Robot.elevator.getEncoderDistance() >= Constants.SCALE_HEIGHT - Constants.ELE_TOLERANCE && 
    // 		      Robot.elevator.getEncoderDistance() <= Constants.SCALE_HEIGHT + Constants.ELE_TOLERANCE) )

    protected boolean isFinished()
    {
        if (Robot.intake.hasHatch())
        {
            if (Robot.elevator.getEncoderDistance() <= (Math.abs(Constants.ELE_HATCH_LVL3)-Constants.ELE_TOLERANCE))
            {
                Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_UP);
                return false;
            }
            else if  (Robot.elevator.getEncoderDistance() > (Math.abs(Constants.ELE_HATCH_LVL3)-Constants.ELE_TOLERANCE)) {
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
            if (Robot.elevator.getEncoderDistance() <= (Math.abs(Constants.ELE_BALL_LVL3)-Constants.ELE_TOLERANCE))
            {
                Robot.elevator.moveMotor(Constants.ELEVATOR_MOTOR_UP);
                return false;
            }
            else if (Robot.elevator.getEncoderDistance() > (Math.abs(Constants.ELE_BALL_LVL3)-Constants.ELE_TOLERANCE)) 
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