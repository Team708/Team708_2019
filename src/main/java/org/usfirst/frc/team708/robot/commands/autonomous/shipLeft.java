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

public class shipLeft extends CommandGroup {
   
    // Called just before this Command runs the first time
    protected void initialize() {
    }
	
    public  shipLeft() {
        addSequential(new Send("in shipLeft - Start"));
        addSequential(new GearHigh());
        //Leaving HAB Lv. 2
        addSequential(new DriveStraightToEncoderDistanceOrTime(-190, -1, 3));
        addSequential(new TurnToDegrees(.7, -90));
        addSequential(new FindRocketHatch());
        addSequential(new DeployHatch());
        addSequential(new DriveCurvatureToDegreesOrTime(-0.7, 0.7, false, 100, 2));
        addSequential(new DriveStraightToEncoderDistanceOrTime(150, 1, 3));
        addSequential(new FindRocketHatch());
        addSequential(new RetractHatch());
        addSequential(new DriveCurvatureToEncoderOrTime(-1.0, 0.1, false, 160, 2));
        addSequential(new TurnToDegrees(.7, -100));
        addSequential(new FindRocketHatch());
        addSequential(new DeployHatch());
        //end
        
        addSequential(new Send("in shipLeft - End"));
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
