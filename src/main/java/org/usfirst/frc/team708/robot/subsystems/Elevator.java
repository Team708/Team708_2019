package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.elevator.JoystickMoveElevator;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.FeedbackDevice;
// import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import com.revrobotics.CANDigitalInput;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANDigitalInput.LimitSwitch;
import com.revrobotics.CANDigitalInput.LimitSwitchPolarity;
import com.revrobotics.CANSparkMaxLowLevel.*;

import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.SensorCollection;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


public class Elevator extends Subsystem {
	
	private CANSparkMax 			elevatorMotor;
	private CANEncoder 				elevatorEncoder;
	private CANPIDController 	PIDElevatorSpark;
	private CANDigitalInput 	upperLimit, lowerLimit;

    /**
      * Constructor
      */
	public Elevator() {

		elevatorMotor 		= new CANSparkMax(RobotMap.elevatorMotorMaster,MotorType.kBrushless);
   	PIDElevatorSpark 	= new CANPIDController(elevatorMotor);
		elevatorEncoder 	= new CANEncoder(elevatorMotor);
		
    upperLimit = new CANDigitalInput(elevatorMotor, LimitSwitch.kForward, LimitSwitchPolarity.kNormallyOpen);
    lowerLimit = new CANDigitalInput(elevatorMotor, LimitSwitch.kReverse, LimitSwitchPolarity.kNormallyOpen);
		
		upperLimit.enableLimitSwitch(true);
		lowerLimit.enableLimitSwitch(true);
		
		elevatorEncoder.setPosition(Constants.ELE_ENC_STARTING_POSITION);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
		setDefaultCommand(new JoystickMoveElevator());
	

    }
	
	public void manualMove(double speed) {
		elevatorMotor.set(speed);
	}
	
	public void moveMotor(double speed) {
		elevatorMotor.set(speed);
	}
	
	public void stop(){
		elevatorMotor.stopMotor();
	}
    
   public boolean isElevatorMin() {
		if (lowerLimit.get()) {
			elevatorEncoder.setPosition(Constants.ELE_ENC_MIN);
			return (true);
	    }
		else {
			return (false);
		}
	}
	public boolean isElevatorMax() {
		if (upperLimit.get()) {
			elevatorEncoder.setPosition(Constants.ELE_ENC_MAX);
			return (true);
	    }
		else {
			return (false);
		}
	}
	
  public double getEncoderDistance() {
     return elevatorEncoder.getPosition();
  }
   
  public void resetElevatorEncoder() {
		elevatorEncoder.setPosition(Constants.ELE_ENC_MIN);   }
	 
   public void sendToDashboard() {
    if (Constants.DEBUG) {
		}
			SmartDashboard.putBoolean("Elev Down:", 	lowerLimit.get());
   		SmartDashboard.putBoolean("Elev Up", 			upperLimit.get());	
			SmartDashboard.putNumber(	"Ele Distance", elevatorEncoder.getPosition());
	}
    
    
}

