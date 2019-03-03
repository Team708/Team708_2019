package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.elevator.JoystickMoveElevator;
// import org.usfirst.frc.team708.robot.commands.elevator.ElevatorHo/ld;

import java.lang.invoke.ConstantCallSite;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Elevator extends Subsystem {
	
	private CANSparkMax 			elevatorMotor;
	private CANEncoder 				elevatorEncoder;
	private CANDigitalInput 	upperLimit, lowerLimit;
	private CANPIDController	elevatorPIDController;
//	public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

	public double ele_position = 0.0;
	
    /**
      * Constructor
      */
	public Elevator() {

		elevatorMotor 		= new CANSparkMax(RobotMap.elevatorMotorMaster,MotorType.kBrushless);
		elevatorEncoder 	= new CANEncoder(elevatorMotor);
		
    upperLimit = new CANDigitalInput(elevatorMotor, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);
    lowerLimit = new CANDigitalInput(elevatorMotor, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
		
		upperLimit.enableLimitSwitch(true);
		lowerLimit.enableLimitSwitch(true);
		elevatorMotor.setIdleMode(IdleMode.kBrake);

		elevatorEncoder.setPosition(Constants.ELE_ENC_STARTING_POSITION);
		elevatorPIDController = elevatorMotor.getPIDController();
		elevatorPIDController.setP(Constants.ELE_P);
    elevatorPIDController.setI(Constants.ELE_I);
    elevatorPIDController.setD(Constants.ELE_D);
    elevatorPIDController.setIZone(Constants.ELE_Iz);
    elevatorPIDController.setFF(Constants.ELE_FF);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new JoystickMoveElevator());
  }
	
	public void manualMove(double speed) {
		elevatorMotor.set(speed);
	}
	
	public void moveMotor(double speed) {
		elevatorMotor.set(speed);
	}
	    
	public boolean isElevatorMin() {
		if (lowerLimit.get()) {
			elevatorEncoder.setPosition(Constants.ELE_ENC_MIN);
			return (true);
		}  
		else
			return (false);
	}

	public boolean isElevatorMax() {
//  if (upperLimit.get()) {
		if (elevatorEncoder.getPosition() > Constants.ELE_MAX) {
			return (true);
	    }
		else 
			return (false);
	}
	
  public double getEncoderDistance() {
     return elevatorEncoder.getPosition();
  }
   
  public void resetElevatorEncoder() {
		elevatorEncoder.setPosition(Constants.ELE_ENC_MIN);  
	}

	public void goToPosition(double elevatorLevel) {
		elevatorPIDController.setReference(elevatorLevel, ControlType.kPosition);
	}
	 
	public void sendToDashboard() {
    if (Constants.DEBUG) {
		}
			SmartDashboard.putBoolean("Elev Down:", 	lowerLimit.get());
   		SmartDashboard.putBoolean("Elev Up", 			upperLimit.get());	
			SmartDashboard.putNumber("Elev Distance", elevatorEncoder.getPosition());
			SmartDashboard.putNumber("Elev Set Positon", ele_position);
	}
}
