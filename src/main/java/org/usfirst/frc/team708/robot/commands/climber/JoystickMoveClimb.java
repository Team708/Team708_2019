package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickMoveClimb extends Command {
	
  	private double moveSpeed;

    public JoystickMoveClimb() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	moveSpeed = OI.operatorGamepad.getAxis(Gamepad.leftStick_Y);
    	if (moveSpeed >= Constants.MOVE_CLIMBER_TOLERANCE) {
    	    Robot.climber.manualMove(Constants.MOVE_CLIMBER_EXTEND);
    	}
    	else if (moveSpeed <= -Constants.MOVE_CLIMBER_TOLERANCE){
        Robot.climber.manualMove(Constants.MOVE_CLIMBER_RETRACT);
    	}
  }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
            	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}