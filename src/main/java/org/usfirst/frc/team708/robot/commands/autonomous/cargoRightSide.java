package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.driverAssist.Level1CG;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class cargoRightSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  cargoRightSide() {
        addSequential(new GearLow());

// Leave HAB2 and Square off position
        // addSequential(new DriveStraightToEncoderDistanceOrTime(45, .6, 3.0));
        // addSequential(new WaitCommand(.2));

        // addSequential(new DriveStraightToEncoderDistanceOrTime(-16, -.6, 2.0));
        // addSequential(new WaitCommand(.2));
        
        // addSequential(new DriveStraightToEncoderDistanceOrTime(220, .6, 4.0));
        // addSequential(new WaitCommand(0.2));

        // addSequential(new TurnToDegrees(.6, -80));
        // addSequential(new WaitCommand(0.2));

    //  addSequential(new Level1CG()); 
        
// Aquire 2nd HATCH from the FEEDER

    //  addSequential(new TurnToDegrees(.6, -80));
    //  addSequential(new WaitCommand(0.2));

    //  addSequential(new DriveStraightToEncoderDistanceOrTime(200, .6, 4.0));
    //  addSequential(new WaitCommand(0.2));

    //  addSequential(new FindFeederCG());

    // addSequential(new TurnToDegrees(.6, 150));
    // addSequential(new WaitCommand(0.2));

    // addSequential(new DriveStraightToEncoderDistanceOrTime(160, .6, 4.0));
    // addSequential(new WaitCommand(0.2));

    // addSequential(new DriveCurvatureToDegreesOrTime(.4, -.6, false, -80, 1.0));
    
    addSequential(new TurnToDegrees(.6, -1));
    addSequential(new WaitCommand(0.2));

    addSequential(new DriveStraightToEncoderDistanceOrTime(-180, -.6, 4.0));
    addSequential(new WaitCommand(0.2));

    addSequential(new DriveCurvatureToDegreesOrTime(.4, .6, false, 80, 1.0));



// Place HATCH onto Front Cargo Ship
    //     addSequential(new Level1CG()); 
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
