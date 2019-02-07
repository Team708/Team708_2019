package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.arm.JoystickMoveElevator;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Elevator extends Subsystem {
	
	private WPI_TalonSRX 	elevatorMotorMaster;
//	private WPI_VictorSPX   armMotorSlave1;
//	public 	DigitalInput 	armSensor;

	public double armDistancePerPulse;
    /**
      * Constructor
      */
	public Elevator() {
		elevatorMotorMaster = new WPI_TalonSRX(RobotMap.elevatorMotorMaster);
		
		/* Peak Current and Duration must be exceeded before current limit is activated.
		 * When activated, current will be limited to Continuous Current.
		 * Set Peak Current params to 0 if desired behavior is to immediately current-limit. 
		 * (10 ms timeout)*/
		// elevatorMotorMaster.configPeakCurrentLimit(30, 10); /* 45 A */
		// elevatorMotorMaster.configPeakCurrentDuration(200, 10); /* 200ms */
		// elevatorMotorMaster.configContinuousCurrentLimit(25, 10); /* 40A */
		// elevatorMotorMaster.enableCurrentLimit(true); /* turn it on */

		elevatorMotorMaster.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		elevatorMotorMaster.setSelectedSensorPosition(Constants.ELE_ENC_STARTING_POSITION, 0, 0);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickMoveElevator());
    }
	
	public void manualMove(double speed) {
		elevatorMotorMaster.set(speed);
	}
	
	public void moveMotor(double speed) {
		elevatorMotorMaster.set(speed);
	}
	
	public void stop(){
		elevatorMotorMaster.stopMotor();
	}
    
   public boolean eleMin() {
		if (!elevatorMotorMaster.getSensorCollection().isRevLimitSwitchClosed()) {
//			armMotorMaster.getSensorCollection().setQuadraturePosition(0, 0);	
			elevatorMotorMaster.setSelectedSensorPosition(0, 0, 0);

			return (true);
	    }
		else {
			return (false);
		}
	}
	public boolean eleMax() {
		if (!elevatorMotorMaster.getSensorCollection().isFwdLimitSwitchClosed()) {
//			armMotorMaster.getSensorCollection().setQuadraturePosition(0, 0);	
			elevatorMotorMaster.setSelectedSensorPosition(Constants.ELE_MAX, 0, 0); //TBD

			return (true);
	    }
		else {
			return (false);
		}
	}
	
   
//	public double getAngle(){
//		return enc.get() * Constants.ARM_REVS_PER_TALON_REV; //Arm Angle = (# talon revs) * (arm revs/talon rev) 
//	   	return enc.getDistancePerPulse();
		
//		armMotorMaster.configSensorTerm
//		armMotorMaster.
		
	// 	return getEncoderDistance();
	// }
	
   public void setEncoderReading(int eleLocation) {
//	   armMotorMaster.getSensorCollection().setPulseWidthPosition(armlocation, 10);
	   elevatorMotorMaster.setSelectedSensorPosition(eleLocation, 0, 10);
   }
   
   public double getEncoderDistance() {
       return elevatorMotorMaster.getSensorCollection().getQuadraturePosition();
   }
   
   public void resetArmEncoder() {
		elevatorMotorMaster.setSelectedSensorPosition(0, 0, 0);
   }
   
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
    	
		if (Constants.DEBUG) {
		}
		// SmartDashboard.putBoolean("Arm Down:", armDown());
    	// SmartDashboard.putNumber("Arm Angle", getAngle());	// Encoder reading
//    	SmartDashboard.putNumber("Arm Distance", getEncoderDistance());	// Encoder reading
    }
    
    
}

