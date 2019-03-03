package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import  org.usfirst.frc.team708.robot.commands.intake.*;
import  org.usfirst.frc.team708.robot.commands.elevator.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.shuffleboard.*;



import edu.wpi.first.wpilibj.command.Command;

/**
 * this does nothing
 */
public class FindFeeder extends CommandGroup {

    public FindFeeder() {
        // Use requires() here to declare subsystem dependencies
        addSequential(new CloseBeak());
        addSequential(new FindRocketHatch());
        addSequential(new ElevatorToLevel1());
        addParallel(new ElevatorHoldOriginal());
        addSequential(new DeployHatch());
        addSequential(new WaitCommand(1.0));
        addSequential(new OpenBeak());
        addSequential(new WaitCommand(1.0));
        addSequential(new RetractHatch());
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
