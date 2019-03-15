package org.usfirst.frc.team708.robot.commands.elevator;

import org.usfirst.frc.team708.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class StartLevel0CG extends Command {
	
    public StartLevel0CG() {
    	// requires(Robot.elevator);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.elevator.lvl0CG = true;
    }    	
  
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }    	

    protected boolean isFinished() {
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