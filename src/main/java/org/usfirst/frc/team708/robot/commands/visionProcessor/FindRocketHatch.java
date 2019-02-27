package org.usfirst.frc.team708.robot.commands.visionProcessor;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
public class FindRocketHatch extends Command {
    
    private double maxTime      = 5.0;
    private boolean notAligned  = false;
    private double targetArea   = Constants.ROCKET_HATCH_TARGET_AREA;

    public FindRocketHatch() {
        this.setTimeout(maxTime);
    }
   
    // Called just before this Command runs the first time
    protected void initialize() {
        notAligned = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivetrain.setBrakeMode(true);
        Robot.visionProcessor.setNTInfo("pipeline", 1);
        Robot.visionProcessor.setNTInfo("led", Constants.VISION_LED_ON);
        if  (Robot.visionProcessor.seesTarget()) {
            if (!Robot.visionProcessor.isCentered()) {
            //    Robot.drivetrain.haloDrive(0.0, Robot.visionProcessor.getRotate(), false);
            Robot.drivetrain.curvatureDrive(Robot.visionProcessor.getMoveRocket(targetArea), 
                                                Robot.visionProcessor.getRotate(), false);
            }
            else {
                Robot.drivetrain.curvatureDrive(Robot.visionProcessor.getMoveRocket(targetArea), 0.0, false);
            }
        }
        else {
            notAligned = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Robot.visionProcessor.isCentered()) && (Robot.visionProcessor.isAtArea(targetArea))) || notAligned || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
