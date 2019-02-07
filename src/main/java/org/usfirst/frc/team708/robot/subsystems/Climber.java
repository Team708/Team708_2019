package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.elevator.JoystickMoveElevator;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	
private WPI_TalonSRX 	climberFLMaster, climberFRMaster, climberRearMaster;
private WPI_VictorSPX	climberRearSlave;
public 	DigitalInput 	teleSensor;

public double teleDistancePerPulse;
    
public Climber() {
		climberFLMaster  	= new WPI_TalonSRX(RobotMap.ClimberLeftFrontMotorMaster);
		climberFRMaster  	= new WPI_TalonSRX(RobotMap.ClimberRightFrontMotorMaster);
		climberRearMaster  	= new WPI_TalonSRX(RobotMap.ClimberLeftRearMotorMaster);
		climberRearSlave  	= new WPI_VictorSPX(RobotMap.ClimberRightRearMotorSlave);
		
		climberRearSlave.follow(climberRearMaster);
		
		// define 6 sensors on motor controllers 
		// define 3 encoders on motor controlers
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new JoystickMoveClimber());
    }
	
	public void manualMove(double speed) {
		climberFLMaster.set(speed);
		climberFRMaster.set(speed);
		climberRearMaster.set(speed);
	}
	
	public void moveLFMotor(double speed) {
		climberFLMaster.set(speed);
	}
	public void moveRFMotor(double speed) {
		climberFRMaster.set(speed);
	}
	public void moveRearMotor(double speed) {
		climberRearMaster.set(speed);
	}
	
	public void stopAll(){
		climberFLMaster.stopMotor();
		climberFRMaster.stopMotor();
		climberRearMaster.stopMotor();
	}

	public void stopFrontLeft(){
		climberFLMaster.stopMotor();
	}

	public void stopFrontRight(){
		climberFRMaster.stopMotor();
	}

	public void stopRear(){
		climberRearMaster.stopMotor();
	}
    
	public double getEncoderFL() {
		return climberFLMaster.getSensorCollection().getQuadraturePosition();
    }   
	
	public double getEncoderFR() {
		return climberFRMaster.getSensorCollection().getQuadraturePosition();
	}

	public double getEncoderRear() {
		return climberRearMaster.getSensorCollection().getQuadraturePosition();
	}
	
   public void resetClimberEncoders() {
		climberFLMaster.setSelectedSensorPosition(0, 0, 0);
		climberFRMaster.setSelectedSensorPosition(0, 0, 0);
		climberRearMaster.setSelectedSensorPosition(0, 0, 0);
}
   
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	
		if (Constants.DEBUG) {
		}
		// SmartDashboard.putBoolean("Extended Front Left", );
		// SmartDashboard.putBoolean("Extended Front Right", );
		// SmartDashboard.putBoolean("Extended Rear", );
		
		// SmartDashboard.putBoolean("Retracted Front Left", );
		// SmartDashboard.putBoolean("Retracted Front Right", );
		// SmartDashboard.putBoolean("Retracted Rear", );

    	// SmartDashboard.putNumber("Encoder Front Left", );
    	// SmartDashboard.putNumber("Encoder Front Right", );
    	// SmartDashboard.putNumber("Encoder Rear", );
    }
    
    
}

