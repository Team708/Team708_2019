package org.usfirst.frc.team708.robot.commands.intakeCube;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.OI;
import org.usfirst.frc.team708.robot.Robot;
import org.usfirst.frc.team708.robot.RobotMap;
//import org.team708.robot.subsystems.*;
//import org.usfirst.frc.team708.robot.subsystems.Loader;


import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Command;



/**
 *@author James Alex Thomas Mikhael
 */
public class IntakeIn extends Command {

    public IntakeIn() {
//    	requires(Robot.intakeCube);
    }
    

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.intakeCube.hasBall())
    		Robot.intakeCube.stop();
    	else
         	Robot.intakeCube.moveMotor(Constants.INTAKE_FORWARD);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return false;		//Replace with intake "has" boolean later	-Viet
    }

    // Called once after isFinished returns true
    protected void end() {
     Robot.intakeCube.stop();

    }

    // Called when another command which requires one or more of the same
    // subsystems are scheduled to run
    protected void interrupted() {
    	end();
    }
}
