package org.usfirst.frc.team708.robot.commands.visionProcessor;

import org.usfirst.frc.team708.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;
//
///**
// *
// */
public class FindHatch extends Command {
    
    private double maxTime = 5.0;

   public FindHatch() {
       this.setTimeout(maxTime);
   }
//
   // Called just before this Command runs the first time
   protected void initialize() {
   }

   // Called repeatedly when this Command is scheduled to run
   protected void execute() {
   // ADD BACK IN LATER	Robot.visionProcessor.processData();
   //	Robot.drivetrain.haloDrive(Robot.visionProcessor.getMove(0.5), Robot.visionProcessor.getRotate());
   }

   // Make this return true when this Command no longer needs to run execute()
   protected boolean isFinished() {
    return Robot.visionProcessor.isCentered() || isTimedOut();
}

   // Called once after isFinished returns true
   protected void end() {
   }

   // Called when another command which requires one or more of the same
   // subsystems is scheduled to run
   protected void interrupted() {
   }
}
