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
        addSequential(new DriveStraightToEncoderDistanceOrTime(45, .6, 3.0));
        addSequential(new WaitCommand(1.0));

        addSequential(new DriveStraightToEncoderDistanceOrTime(-16, -.6, 2.0));
        addSequential(new WaitCommand(1.0));
        
        addSequential(new DriveCurvatureToEncoderOrTime(.6, -0.3, false, 65, 2.0));
        addSequential(new WaitCommand(1.0));

        addSequential(new Level1CG()); 
        
// Aquire 2nd HATCH from the FEEDER
    //  addSequential(new TurnToDegrees(-.7, -150));
    // addSequential(new DriveCurvatureToEncoderOrTime(.6, 0.2, false, 60, 2.0));
    //  addSequential(new FindFeederCG());

// Curve around the ROCKET and align with the far side    
    //     addSequential(new DriveCurvatureToEncoderOrTime(-.7, -.2, false, -60, 2.0));
    //     addSequential(new DriveCurvatureToDegreesOrTime(-.7, -.5, false, -160, 1.0));

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
