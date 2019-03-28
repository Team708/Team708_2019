package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.visionProcessor.FindRocket;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level2CG;
import org.usfirst.frc.team708.robot.commands.driverAssist.FindFeederCG;


import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class rocketRightNearSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  rocketRightNearSide() {
        addSequential(new GearLow());

// Leave HAB2 and Square off position
        addSequential(new DriveStraightToEncoderDistanceOrTime(45, .6, 3.0));
        addSequential(new WaitCommand(.2));

        addSequential(new DriveStraightToEncoderDistanceOrTime(-16, -.6, 2.0));
        addSequential(new WaitCommand(.2));

        addSequential(new DriveCurvatureToEncoderOrTime(.6, 0.25, false, 65, 3.0));
        addSequential(new WaitCommand(.2));

        addSequential(new DriveCurvatureToEncoderOrTime(.6, -0.12, false, 18, 12.0));
        addSequential(new WaitCommand(0.2));

         addSequential(new Level2CG()); 
        
// Aquire 2nd HATCH from the FEEDER
        addSequential(new TurnToDegrees(.6, 130));
        addSequential(new WaitCommand(0.2));

        addSequential(new DriveStraightToEncoderDistanceOrTime(60, .6, 3.0));
        addSequential(new WaitCommand(0.2));

        addSequential(new FindFeederCG());

// Curve around the ROCKET and align with the far side    
        addSequential(new TurnToDegrees(.4, -1));
        addSequential(new DriveStraightToEncoderDistanceOrTime(-190, -.6, 4.0));
        
        // addSequential(new WaitCommand(0.2));

        // addSequential(new DriveCurvatureToEncoderOrTime(.6, .4, false, 30, 2.0));
        // addSequential(new WaitCommand(0.2));

// Place 2nd HATCH onto ROCKET
//     addSequential(new Level2CG()); 
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
