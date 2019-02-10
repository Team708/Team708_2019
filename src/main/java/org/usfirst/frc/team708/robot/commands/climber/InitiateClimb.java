package org.usfirst.frc.team708.robot.commands.climber;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class InitiateClimb extends Command {

    private boolean stage1  = false;
    private boolean stage2  = false;
    private boolean stage3  = false;
    private boolean stage4  = false;
    private boolean stage5  = false;
    private boolean stage6  = false;

    public InitiateClimb() {  	
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
        requires(Robot.climber);
        Robot.climber.climbStarted = true;
        stage1 = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (stage1) {
            if (!Robot.climber.upperLimitFL()&&!Robot.climber.upperLimitFR()&&
                    !Robot.climber.upperLimitRear()){
                        //Robot balancedExtend 
                    }
                if (Robot.drivetrain.isBalanced()){
                    Robot.climber.moveLFMotor(Constants.MOVE_CLIMBER_EXTEND);
                    Robot.climber.moveRFMotor(Constants.MOVE_CLIMBER_EXTEND);
                    Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_EXTEND);
                }
                else if (Robot.drivetrain.isTiltingBack()){
                    Robot.climber.moveLFMotor(Constants.STOP_CLIMBER);
                    Robot.climber.moveRFMotor(Constants.STOP_CLIMBER);
                    Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_EXTEND);
                }
                else if (Robot.drivetrain.isTiltingRight()){
                    Robot.climber.moveLFMotor(Constants.STOP_CLIMBER);
                    Robot.climber.moveRFMotor(Constants.MOVE_CLIMBER_EXTEND);
                    Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_EXTEND);
                }
                else if (Robot.drivetrain.isTiltingLeft()){
                    Robot.climber.moveLFMotor(Constants.MOVE_CLIMBER_EXTEND);
                    Robot.climber.moveRFMotor(Constants.STOP_CLIMBER);
                    Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_EXTEND);
                }
                else if (Robot.drivetrain.isTiltingForward()){
                    Robot.climber.moveLFMotor(Constants.MOVE_CLIMBER_EXTEND);
                    Robot.climber.moveRFMotor(Constants.MOVE_CLIMBER_EXTEND);
                    Robot.climber.moveRearMotor(Constants.STOP_CLIMBER);
                }
            else {
                stage1 = false;
                stage2 = true;
                Robot.climber.resetClimberRoller();
            }
                }
        else if (stage2) {
            if (Robot.climber.getEncoderRear()<Constants.REAR_CLIMBER_ROLLER_FIRST_DISTANCE) {
                Robot.climber.moveRollerMotor(0.5);
            }
            else {
                stage2 = false;
                stage3 = true;
                Robot.climber.moveRollerMotor(0.0);
            }
        }
        else if (stage3) {
            if (!Robot.climber.lowerLimitFL()&&!Robot.climber.lowerLimitFR()) {
                Robot.climber.moveLFMotor(Constants.MOVE_CLIMBER_RETRACT);
                Robot.climber.moveRFMotor(Constants.MOVE_CLIMBER_RETRACT);
            }
            else {
                stage3 = false;
                stage4 = true;
                Robot.climber.resetClimberRoller();
                Robot.drivetrain.resetEncoder();
            }
        }
        else if (stage4) {
            if (Robot.climber.getEncoderRear()<Constants.REAR_CLIMBER_ROLLER_DISTANCE||
                    Robot.drivetrain.getEncoderDistanceLeft()<Constants.REAR_CLIMBER_ROLLER_DISTANCE_INCHES) {
                        Robot.climber.moveRollerMotor(0.5);
                        Robot.drivetrain.haloDrive(0.5, 0.0, false);
            }
            else {
                stage4 = false;
                stage5 = true;
                Robot.climber.moveRollerMotor(0.0);
                Robot.drivetrain.stop();
            }
        }
        else if (stage5) {
            if (!Robot.climber.lowerLimitRear()) {
                Robot.climber.moveRearMotor(Constants.MOVE_CLIMBER_RETRACT);
            }
             else {
                stage5 = false;
                stage6 = true;
                Robot.drivetrain.resetEncoder();
            }
        }
        else if (stage6) {
            if (Robot.drivetrain.getEncoderDistanceLeft()<Constants.REAR_CLIMBER_ROLLER_DISTANCE_FINAL) {
                Robot.drivetrain.haloDrive(0.5, 0.0, false);
            }
            else {
                stage6 = false;
                Robot.climber.climbStarted = false;
                Robot.drivetrain.stop();
            }
        }
        }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (Robot.climber.climbStarted) {
            return false;
        } 
        else {
            return true;
        }
    }

    // Called once after isFinished returns true
    protected void end() {
     Robot.climber.stopAll();

    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}