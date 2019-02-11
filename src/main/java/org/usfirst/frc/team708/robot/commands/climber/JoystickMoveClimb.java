package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.util.Gamepad;
import edu.wpi.first.wpilibj.command.Command;

public class JoystickMoveClimb extends Command {
	
    private double moveSpeed;
    private double moveSpeedRoller;

    public JoystickMoveClimb() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
      moveSpeed = OI.climbingGamepad.getAxis(Gamepad.leftStick_Y);
      moveSpeedRoller = OI.climbingGamepad.getAxis(Gamepad.rightStick_X);

    	if (moveSpeed >= Constants.MOVE_CLIMBER_TOLERANCE) {
        Robot.climber.manualMove(Constants.MOVE_CLIMBER_EXTEND);
      }
      else if (moveSpeed <= -Constants.MOVE_CLIMBER_TOLERANCE){
        Robot.climber.manualMove(Constants.MOVE_CLIMBER_RETRACT);
      }
      else {
        Robot.climber.manualMove(0.0);
      }

      if (moveSpeedRoller >= Constants.MOVE_CLIMBER_TOLERANCE) {
        Robot.climber.moveRollerMotor(Constants.MOVE_CLIMBER_EXTEND);
      }
      else if (moveSpeedRoller <= -Constants.MOVE_CLIMBER_TOLERANCE){
        Robot.climber.moveRollerMotor(Constants.MOVE_CLIMBER_RETRACT);
      }
      else {
        Robot.climber.moveRollerMotor(0.0);
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