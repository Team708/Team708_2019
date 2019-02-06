package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.telescope.JoystickMoveTele;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {
	
private WPI_TalonSRX 	teleMotorMaster;
private WPI_VictorSPX	teleMotorSlave1, teleMotorSlave2;
public 	DigitalInput 	teleSensor;

public double teleDistancePerPulse;
    
public Climber() {
		climberLFMaster  	= new WPI_TalonSRX(RobotMap.ClimberLeftFrontMotorMaster);
		climberRFMaster  	= new WPI_TalonSRX(RobotMap.ClimberRightFrontMotorMaster);
		climberRearMaster  	= new WPI_TalonSRX(RobotMap.ClimberLeftRearMotorMaster);
		climberRearSlave  	= new WPI_TalonSRX(RobotMap.ClimberRightRearMotorSlave);
		
		climberRearSlave.follow(climberRearMaster);
		
		// define 6 sensors on motor controllers 
		// define 3 encoders on motor controlers
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new JoystickMoveClimber());
    }
	
	public void manualMove(double speed) {
		climberLFMaster.set(speed);
		climberRFMaster.set(speed);
		climberRearMaster.set(speed);
	}
	
	public void moveLFMotor(double speed) {
		climberLFMaster.set(speed);
	}
	public void moveRFMotor(double speed) {
		climberRFMaster.set(speed);
	}
	public void moveRearMotor(double speed) {
		climberRearMaster.set(speed);
	}
	
	public void stopAll(){
		climberLFMaster.stopMotor();
		climberRFMaster.stopMotor();
		climberRearMaster.stopMotor();
	}

	public void stopLF(){
		climberLFMaster.stopMotor();
	}

	public void stopRF(){
		climberRFMaster.stopMotor();
	}

	public void stopLF(){
		climberRearMaster.stopMotor();
	}
   
   public void setEncoderReading(int telelocation) {
	   teleMotorMaster.setSelectedSensorPosition(telelocation, 0, 10);
   }
   
	public double getEncoderLF() {
		return climberLFMaster.getSensorCollection().getQuadraturePosition();
    }   
	
	public double getEncoderRF() {
		return climberRFMaster.getSensorCollection().getQuadraturePosition();
	}

	public double getEncoderRear() {
		return climberRearMaster.getSensorCollection().getQuadraturePosition();
	}
	
   public void resetClimberEncoders() {
		climberLFMaster.setSelectedSensorPosition(0, 0, 0);
		climberRFMaster.setSelectedSensorPosition(0, 0, 0);
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

