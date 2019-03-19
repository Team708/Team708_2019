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
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level2CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.FindFeederCG;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class rocketLeftNearSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  rocketLeftNearSide() {
        addSequential(new Send("in rocketLeftNearSide - Start"));
        addSequential(new GearLow());
    // Leave HAB2 and Square off position
        addSequential(new DriveStraightToEncoderDistanceOrTime(60, .6, 3.0));
        addSequential(new DriveStraightToEncoderDistanceOrTime(-12, -.8, 1.0));
    // // Look for the near-side ROCKET and place 1st HATCH
        addSequential(new WaitCommand(1.0));
        addSequential(new DriveCurvatureToEncoderOrTime(.8, -0.25, false, 100, 4.0));
        // addSequential(new WaitCommand(1.0));
        // addSequential(new TurnToDegrees(.7, -180));
        addSequential(new Level2CG()); 
    // // Aquire 2nd HATCH from the FEEDER
        addSequential(new TurnToDegrees(.7, -150));
        addSequential(new FindFeederCG());
    // // Curve around the ROCKET and align with the far side    
        addSequential(new DriveCurvatureToEncoderOrTime(-.7, .1, false, -200, 2.0));
        addSequential(new DriveCurvatureToEncoderOrTime(-.7, -.4, false, -75, 2.0));
        addSequential(new TurnToDegrees(.7, 85));
    // // Place 2nd HATCH onto ROCKET
        addSequential(new Level2CG()); 
    // // Turn and face the cargo ship    
        addSequential(new TurnToDegrees(.7, -120));
        addSequential(new Send("in shipLeftNearSide - End"));
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
