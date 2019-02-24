package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Climber extends Subsystem {
	
private WPI_TalonSRX 	climberFrontMaster, climberRearMaster;
private WPI_TalonSRX	climberRoller;
private double distancePerPulse;

public boolean stage1  = true;
public boolean stage2  = false;
public boolean stage3  = false;
public boolean stage4  = false;
public boolean stage5  = false;
public boolean stage6  = false;
public boolean climbStarted = true;

public Climber() {
	climberFrontMaster		= new WPI_TalonSRX(RobotMap.ClimberLeftFrontMotor);
//	climberFrontSlave			= new WPI_TalonSRX(RobotMap.ClimberRightFrontMotor);
	climberRearMaster			= new WPI_TalonSRX(RobotMap.ClimberLeftRearMotor);
	climberRoller					= new WPI_TalonSRX(RobotMap.ClimberRollerMotor);
	
//	climberFrontSlave.follow(climberFrontMaster);

	//Encoders
	climberRoller.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
	distancePerPulse = (Constants.ROLLER_DIAMETER * Math.PI) /
												(Constants.ROLLER_ENCODER_COUNTS_PER_REV * Constants.ROLLER_GEAR_RATIO);
	
	//Brake
	climberRoller.setNeutralMode(NeutralMode.Brake);
	climberFrontMaster.setNeutralMode(NeutralMode.Brake);
//	climberFrontSlave.setNeutralMode(NeutralMode.Brake);
	climberRearMaster.setNeutralMode(NeutralMode.Brake);
}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new JoystickMoveClimber());
    }
	/**
	 * Motion methods for the climber/roller
	 * @param speed
	 */
	public void manualMove(double speed) {
		climberFrontMaster.set(speed);
		climberRearMaster.set(speed);
	}
	

	public void moveFrontMotor(double speed) {
		climberFrontMaster.set(speed);
	}

	public void moveRearMotor(double speed) {
		climberRearMaster.set(speed);
	}
	public void moveRollerMotor(double speed) {
		climberRoller.set(speed);
	}

	public void stopAll(){
		climberFrontMaster.stopMotor();
		climberRearMaster.stopMotor();
		climberRoller.stopMotor();

	}

	public void stopFront(){
		climberFrontMaster.stopMotor();
	}

	public void stopRear(){
		climberRearMaster.stopMotor();
	}

	public double getEncoderRoller() {
		return climberRoller.getSensorCollection().getQuadraturePosition() * distancePerPulse;
	}

	public void resetClimberRoller() {
		climberRoller.setSelectedSensorPosition(0, 0, 0);
	}

	// Limit Switch Methods
	public boolean upperLimitFront() {
		return climberFrontMaster.getSensorCollection().isFwdLimitSwitchClosed();
	}
	
	public boolean lowerLimitFront() {
		// return lowerLimitFL.get();
		return climberFrontMaster.getSensorCollection().isRevLimitSwitchClosed();
	}

	public boolean upperLimitRear() {
		return climberRearMaster.getSensorCollection().isFwdLimitSwitchClosed();
	}

	public boolean lowerLimitRear() {
		return climberRearMaster.getSensorCollection().isRevLimitSwitchClosed();
	}
  
	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Front Upper Limit", upperLimitFront());
		SmartDashboard.putBoolean("Front Lower Limit", lowerLimitFront());
		SmartDashboard.putBoolean("Rear Upper Limit",  upperLimitRear());
		SmartDashboard.putBoolean("Rear Lower Limit",  lowerLimitRear());	
		SmartDashboard.putNumber("Roller Encoder", getEncoderRoller());		//Encoder raw count
  } 
}
