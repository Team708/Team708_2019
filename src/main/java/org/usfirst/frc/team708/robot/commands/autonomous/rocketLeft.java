package org.usfirst.frc.team708.robot.commands.autonomous;

import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToEncoderDistanceOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveStraightToBallOrTime;

import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToDegreesOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.DriveCurvatureToEncoderOrTime;
import org.usfirst.frc.team708.robot.commands.drivetrain.TurnToDegrees;
import org.usfirst.frc.team708.robot.commands.drivetrain.GearHigh;
import org.usfirst.frc.team708.robot.commands.drivetrain.Send;
import org.usfirst.frc.team708.robot.commands.visionProcessor.*;
import org.usfirst.frc.team708.robot.commands.intake.*;
import org.usfirst.frc.team708.robot.commands.elevator.*;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.shuffleboard.*;

public class rocketLeft extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  rocketLeft() {
        Shuffleboard.addEventMarker("in rocketLeft", "Start", EventImportance.kNormal);
        addSequential(new GearHigh());
        //Leaving HAB Lv. 2
        addSequential(new DriveCurvatureToEncoderOrTime(-1.0, .3, false, -200, 3));
        //Turn into the ROCKET
        addSequential(new DriveCurvatureToDegreesOrTime(.8, .9, true, 80, 2));
        //Place First Hatch
        addSequential(new FindRocketHatch());
            //Potentially move elevator
        addSequential(new DeployHatch());  
        //Turn away from ROCKET and acquire second HATCH
        addSequential(new DriveCurvatureToDegreesOrTime(-.8, -.5, false, -45, 2));
        addSequential(new FindRocketHatch());
        addSequential(new RetractHatch());
        //Turn to the ROCKET and place second HATCH
        addSequential(new DriveStraightToEncoderDistanceOrTime(-90, -1.0, 3));
        addSequential(new TurnToDegrees(.8, -180));  
        addSequential(new FindRocketHatch());
        addSequential(new DeployHatch());
        //Turn to and acquire CARGO  
        addSequential(new DriveStraightToEncoderDistanceOrTime(-80, -1.0, 3));
        addSequential(new TurnToDegrees(.8, -180));  
        addSequential(new DeployIntake());
        addParallel(new DriveStraightToBallOrTime(24, .8, 2));
        addSequential(new AutoIntakeBallInForTime(2));
        Shuffleboard.addEventMarker("in rocketLeft", "End", EventImportance.kNormal);
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
