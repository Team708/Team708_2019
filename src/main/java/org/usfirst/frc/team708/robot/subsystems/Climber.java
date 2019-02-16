package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.elevator.JoystickMoveElevator;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import com.ctre.phoenix.CANifier;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import com.ctre.phoenix.motorcontrol.RemoteLimitSwitchSource;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Climber extends Subsystem {
	
// private CANSparkMax 	climberFLMaster, climberFRMaster, climberRearMaster, climberRearSlave;
private WPI_TalonSRX 	climberFrontMaster, climberFrontSlave, climberRearMaster, climberRearSlave;
private WPI_TalonSRX	climberRoller;
// private CANEncoder		climberFLEncoder, climberFREncoder, climberRearEncoder;
// private CANDigitalInput upperLimitFL, lowerLimitFL, upperLimitFR, lowerLimitFR, upperLimitRear, lowerLimitRear;
public boolean stage1  = true;
public boolean stage2  = false;
public boolean stage3  = false;
public boolean stage4  = false;
public boolean stage5  = false;
public boolean stage6  = false;
public boolean climbStarted = true;

public Climber() {

		climberFrontMaster		= new WPI_TalonSRX(RobotMap.ClimberLeftFrontMotor);
		climberFrontSlave			= new WPI_TalonSRX(RobotMap.ClimberRightFrontMotor);
		climberRearMaster			= new WPI_TalonSRX(RobotMap.ClimberLeftRearMotor);
		climberRoller					= new WPI_TalonSRX(RobotMap.ClimberRollerMotor);
		
		climberFrontSlave.follow(climberFrontMaster);

		//Encoders
		climberRoller.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		
		//Brake
    climberRoller.setNeutralMode(NeutralMode.Brake);
    climberFrontMaster.setNeutralMode(NeutralMode.Brake);
    climberFrontSlave.setNeutralMode(NeutralMode.Brake);
    climberRearMaster.setNeutralMode(NeutralMode.Brake);
    climberRearSlave.setNeutralMode(NeutralMode.Brake);

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
		return climberRoller.getSensorCollection().getQuadraturePosition();
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
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	
		if (Constants.DEBUG) {
			SmartDashboard.putBoolean("Front Upper Limit", upperLimitFront());
			SmartDashboard.putBoolean("Front Lower Limit", lowerLimitFront());
			SmartDashboard.putBoolean("Rear Upper Limit",  upperLimitRear());
			SmartDashboard.putBoolean("Rear Lower Limit",  lowerLimitRear());	
			SmartDashboard.putNumber("Roller Encoder", getEncoderRoller());		//Encoder raw count
		}

    } 
}
