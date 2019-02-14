package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.elevator.JoystickMoveElevator;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;


public class Climber extends Subsystem {
	
private CANSparkMax 	climberFLMaster, climberFRMaster, climberRearMaster, climberRearSlave;
private WPI_TalonSRX	climberRoller;
private CANEncoder		climberFLEncoder, climberFREncoder, climberRearEncoder;
private CANDigitalInput upperLimitFL, lowerLimitFL, upperLimitFR, lowerLimitFR, upperLimitRear, lowerLimitRear;
public boolean stage1  = true;
public boolean stage2  = false;
public boolean stage3  = false;
public boolean stage4  = false;
public boolean stage5  = false;
public boolean stage6  = false;
public boolean climbStarted = true;

public Climber() {

		climberFLMaster  	= new CANSparkMax(RobotMap.ClimberLeftFrontMotorMaster, MotorType.kBrushless);
		climberFRMaster  	= new CANSparkMax(RobotMap.ClimberRightFrontMotorMaster, MotorType.kBrushless);
		climberRearMaster  	= new CANSparkMax(RobotMap.ClimberLeftRearMotorMaster, MotorType.kBrushless);
	//	climberRearSlave  	= new CANSparkMax(RobotMap.ClimberRightRearMotorSlave, MotorType.kBrushless);
		climberRoller		= new WPI_TalonSRX(RobotMap.ClimberRollerMotor);
	// //	climberRearSlave.follow(climberRearMaster);
		climberFLEncoder	= new CANEncoder(climberFLMaster);
		climberFREncoder	= new CANEncoder(climberFRMaster);
		climberRearEncoder	= new CANEncoder(climberRearMaster);

		upperLimitFL = new CANDigitalInput(climberFLMaster, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);
		lowerLimitFL = new CANDigitalInput(climberFLMaster, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
		upperLimitFR = new CANDigitalInput(climberFRMaster, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);
		lowerLimitFR = new CANDigitalInput(climberFRMaster, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
		upperLimitRear = new CANDigitalInput(climberRearMaster, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);
		lowerLimitRear = new CANDigitalInput(climberRearMaster, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
		//Limit Switches
		upperLimitFL.enableLimitSwitch(true);
		lowerLimitFL.enableLimitSwitch(true);
		upperLimitFR.enableLimitSwitch(true);
		lowerLimitFR.enableLimitSwitch(true);
		upperLimitRear.enableLimitSwitch(true);
		lowerLimitRear.enableLimitSwitch(true);
		//Encoders
		climberRoller.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		//Brakes
		climberFLMaster.setIdleMode(IdleMode.kBrake);
		climberFRMaster.setIdleMode(IdleMode.kBrake);
		climberRearMaster.setIdleMode(IdleMode.kBrake);
    climberRoller.setNeutralMode(NeutralMode.Brake);

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
		climberFRMaster.set(speed);
		climberFLMaster.set(speed);
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
	public void moveRollerMotor(double speed) {
		climberRoller.set(speed);
	}
	public void stopAll(){
		climberFLMaster.stopMotor();
		climberFRMaster.stopMotor();
		climberRearMaster.stopMotor();
		climberRoller.stopMotor();

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

    // Encoders methods
	public double getEncoderFL() {
		return climberFLEncoder.getPosition();
    }   
	public double getEncoderFR() {
		return climberFREncoder.getPosition();
	}
	public double getEncoderRear() {
		return climberRearEncoder.getPosition();
	}
	public double getEncoderRoller() {
		return climberRoller.getSensorCollection().getQuadraturePosition();
	}
    public void resetClimberEncoders() {
		climberFLEncoder.setPosition(0.0);
		climberFREncoder.setPosition(0.0);
		climberRearEncoder.setPosition(0.0);
	}
	public void resetClimberRoller() {
		climberRoller.setSelectedSensorPosition(0, 0, 0);
	}

	// Limit Switch Methods
	public boolean upperLimitFL() {
		return upperLimitFL.get();
	}
	public boolean lowerLimitFL() {
		return lowerLimitFL.get();
	}
	public boolean upperLimitFR() {
		return upperLimitFR.get();
	}
	public boolean lowerLimitFR() {
		return lowerLimitFR.get();
	}
	public boolean upperLimitRear() {
		return upperLimitRear.get();
	}
	public boolean lowerLimitRear() {
		return lowerLimitRear.get();
	}
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	
		if (Constants.DEBUG) {
			SmartDashboard.putBoolean("FR Upper Limit", upperLimitFR.get());
			SmartDashboard.putBoolean("FR Lower Limit", lowerLimitFR.get());
			SmartDashboard.putBoolean("FL Upper Limit", upperLimitFL.get());
			SmartDashboard.putBoolean("FL Lower Limit", lowerLimitFL.get());
			SmartDashboard.putBoolean("Rear Upper Limit", upperLimitRear.get());
			SmartDashboard.putBoolean("Rear Lower Limit", lowerLimitRear.get());	
			SmartDashboard.putNumber("Roller Encoder", getEncoderRoller());		//Encoder raw count
		}

    } 
}

