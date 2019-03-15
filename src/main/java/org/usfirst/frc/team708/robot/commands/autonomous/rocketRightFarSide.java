package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToBallOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearLow;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team708.robot.commands.intake.*;
import org.usfirst.frc.team708.robot.commands.elevator.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class rocketRightFarSide extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  rocketRightFarSide() {
        //Temporary AUTO
        // addSequential(new Send("in rocketRight - Start"));

        addSequential(new GearLow());

        addSequential(new DriveStraightToEncoderDistanceOrTime(-160, -.8, 2));
        addSequential(new WaitCommand(1.0));

        addSequential(new DriveCurvatureToEncoderOrTime(-1.0, 0.1, false, 140, 1));
        addSequential(new TurnToDegrees(.7, -50)); 
        addSequential(new WaitCommand(1.0));

        addSequential(new FindRocketHatch());
        addSequential(new ElevatorToLevel1());
        addSequential(new DriveStraightToEncoderDistanceOrTime(6, .6, 1));

        // addSequential(new WaitCommand(1.0));
        // addParallel(new ElevatorHoldOriginal());
        addSequential(new DeployGamePiece());
        addSequential(new WaitCommand(1.0));
        addSequential(new CloseBeak());
        addSequential(new WaitCommand(1.0));
        addSequential(new RetractHatch());
        addSequential(new DriveCurvatureToDegreesOrTime(-0.7, 0.3, false, 20, 1));

        // // //Leaving Rocket, Acquire 2nd HATCH
        // // addSequential(new DriveStraightToEncoderDistanceOrTime(200, 1.0, 3));
        // // addSequential(new FindRocketHatch());
        // // // addSequential(new RetractHatch());
        // // addSequential(new DeployHatch());
        // // addSequential(new WaitCommand(.5));
        // // addSequential(new OpenBeak());
        // // addSequential(new WaitCommand(.5));
        // // addSequential(new RetractHatch());
        // // //Leaving Feeder Station, Place 2nd HATCH
        // // addSequential(new DriveCurvatureToEncoderOrTime(-1.0, -0.1, false, -140, 3));
        // // addSequential(new TurnToDegrees(.7, -100));
        // // addSequential(new FindRocketHatch());
        // // addSequential(new ElevatorToLevel1());
        // // addParallel(new ElevatorHold());
        // // addSequential(new DeployGamePiece());
        // // addSequential(new CloseBeak());
        // // addSequential(new WaitCommand(.5));
        // // addSequential(new RetractHatch());
        // // addSequential(new DriveCurvatureToDegreesOrTime(-.7, -.2, false, -50, 3));
        // // addSequential(new DriveCurvatureToEncoderOrTime(1.0, 0.0, true, 120, 3));
        // // addSequential(new TurnToDegrees(.7, 90));
        // // addSequential(new FindRocketHatch());
        // // //end        
        // addSequential(new Send("in shipRight - End"));
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
