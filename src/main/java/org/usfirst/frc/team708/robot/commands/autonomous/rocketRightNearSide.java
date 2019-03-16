package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindRocketHatch;
import org.usfirst.frc.team708.robot.commands.intake.*;
import org.usfirst.frc.team708.robot.commands.elevator.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class rocketRightNearSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  rocketRightNearSide() {
        addSequential(new Send("in rocketRightNearSide - Start"));
        addSequential(new GearLow());
    // Leave HAB2 and Square off position
        addSequential(new DriveStraightToEncoderDistanceOrTime(-60, -.6, 2));
        addSequential(new DriveStraightToEncoderDistanceOrTime(12, .6, 1));
    // Look for the near-side ROCKET and place 1st HATCH
        addSequential(new DriveCurvatureToEncoderOrTime(-1.0, 0.2, false, 80, 2));
        addSequential(new TurnToDegrees(.7, -110)); 
        addSequential(new FindRocketHatch());
        addSequential(new ElevatorToLevel1());
        addSequential(new DriveStraightToEncoderDistanceOrTime(6, .6, 1));
        addSequential(new DeployGamePiece());
        addSequential(new CloseBeak());
        addSequential(new WaitCommand(1.0));
        addSequential(new RetractHatch());
        addSequential(new DriveCurvatureToDegreesOrTime(-0.7, -0.3, false, 15, 1));
    // Aquire 2nd HATCH from the FEEDER
        addSequential(new TurnToDegrees(.7, -180));
        addSequential(new FindRocketHatch());
        // addSequential(new ElevatorToLevel1());
        addSequential(new DriveStraightToEncoderDistanceOrTime(6, .4, 1.0));
        addSequential(new ExtendHatch());
        addSequential(new WaitCommand(1.0));
        addSequential(new OpenBeak());
        addSequential(new WaitCommand(1.0));
        addSequential(new RetractHatch());
        addSequential(new DriveStraightToEncoderDistanceOrTime(-10, -.4, 1.0));
    // Curve around the ROCKET and align with the far side    
        addSequential(new DriveCurvatureToEncoderOrTime(-.7, -.1, false, -120, 2));
        addSequential(new DriveCurvatureToEncoderOrTime(-.7, .4, false, -75, 2));
        addSequential(new TurnToDegrees(.7, -80));
    // Place 2nd HATCH onto ROCKET
        addSequential(new FindRocketHatch());
        // addSequential(new ElevatorToLevel1());
        addSequential(new DriveStraightToEncoderDistanceOrTime(6, .6, 1));
        addSequential(new DeployGamePiece());
        addSequential(new CloseBeak());
        addSequential(new WaitCommand(1.0));
        addSequential(new RetractHatch());
        addSequential(new DriveStraightToEncoderDistanceOrTime(-10, -.4, 1.0));
    // Turn and face the cargo ship    
        addSequential(new DriveCurvatureToDegreesOrTime(.7, .7, false, 100, 2));
        addSequential(new Send("in shipRightNearSide - End"));
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
