package org.usfirst.frc.team708.robot.commands.driverAssist;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindRocketHatch;
import  org.usfirst.frc.team708.robot.commands.intake.CloseBeak;
import  org.usfirst.frc.team708.robot.commands.intake.OpenBeak;
import  org.usfirst.frc.team708.robot.commands.intake.ExtendHatch;
import  org.usfirst.frc.team708.robot.commands.intake.RetractHatch;
import  org.usfirst.frc.team708.robot.commands.elevator.ElevatorToLevel1;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;



import edu.wpi.first.wpilibj.command.Command;

/**
 * this does nothing
 */
public class FindFeederCG extends CommandGroup {

    public FindFeederCG() {
        // Use requires() here to declare subsystem dependencies
        
        addSequential(new CloseBeak());
        addSequential(new FindRocketHatch());
        addSequential(new ElevatorToLevel1());
        // addSequential(new WaitCommand(1.0));
        addSequential(new DriveStraightToEncoderDistanceOrTime(6, .4, 1.0));
        addSequential(new ExtendHatch());
        addSequential(new WaitCommand(1.0));
        addSequential(new OpenBeak());
        addSequential(new WaitCommand(1.0));
        addSequential(new RetractHatch());
        addSequential(new DriveStraightToEncoderDistanceOrTime(-10, -.4, 1.0));
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
