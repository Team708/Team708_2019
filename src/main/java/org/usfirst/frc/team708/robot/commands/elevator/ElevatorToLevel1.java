package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ElevatorToLevel1 extends Command {
	
    public ElevatorToLevel1() {
    	// requires(Robot.elevator);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	}    	
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (Robot.intake.hasHatch())
            Robot.elevator.elev_position = Constants.ELEV_HATCH_LVL1;
        else if (Robot.intake.hasBall())
            Robot.elevator.elev_position = Constants.ELEV_BALL_LVL1;
        else 
            Robot.elevator.elev_position = Constants.ELEV_HATCH_LVL1;
    }    	

    protected boolean isFinished()
    {   
        Robot.drivetrain.resetEncoder(); 
        if (Robot.elevator.getEncoderDistance() <= (Robot.elevator.elev_position-Constants.ELEV_TOLERANCE))
            return false;
        else if (Robot.elevator.getEncoderDistance() > (Robot.elevator.elev_position+Constants.ELEV_TOLERANCE))
            return false;
        else 
            return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}