package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.intake.RetractIntake;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
/**
 * Subsystem that intakes balls
 */

public class Intake extends Subsystem {
	
	private WPI_TalonSRX 		ballMaster, hatchMaster;
	private WPI_VictorSPX   ballSlave;

	private Solenoid intakeSolenoid;
	private Solenoid hatchSolenoid;

	private boolean intakeRetracted	 = true;
	private boolean hatchRetracted	 = true;

	public DigitalInput 	ballSensor;
	public DigitalInput 	hatchSensor;


	public Intake() {
		ballMaster	= new WPI_TalonSRX(RobotMap.ballIntakeMotorMaster);
		ballSlave 	= new WPI_VictorSPX(RobotMap.ballIntakeMotorSlave1);
		hatchMaster = new WPI_TalonSRX(RobotMap.hatchIntakeMotorMaster);

		intakeSolenoid = new Solenoid(RobotMap.intake);
		hatchSolenoid	 = new Solenoid(RobotMap.hatch);

		intakeSolenoid.set(intakeRetracted);
		hatchSolenoid.set(hatchRetracted);

		ballSensor 		= new DigitalInput(RobotMap.ballSensor);
		hatchSensor 	= new DigitalInput(RobotMap.hatchSensor);

		ballSlave.follow(ballMaster);
	}
	
	public void initDefaultCommand() {
	}
	
	public void moveMotorBall(double speed) {
		ballMaster.set(speed);
	}
	
	public void stopMotorBall() {
		ballMaster.set(0.0);
	}

	public void moveMotorHatch(double speed) {
		hatchMaster.set(speed);
	}
	
	public void stopMotorHatch() {
		hatchMaster.set(0.0);
	}
	
	public boolean hasBall() {		
		if (!ballSensor.get())
			return (true);	    
		else 
			return (false);		
	}

	public boolean hasHatch() {		
		if (!hatchSensor.get())
			return (true);	    
		else 
			return (false);		
	}
	
	public void ballStop() {
		ballMaster.set(Constants.BALL_STOP);
	}

	public void hatchStop() {
		hatchMaster.set(Constants.HATCH_STOP);
	}

 //Pneumatics
	public void intakeRetract() {
		intakeRetracted = true;
		intakeSolenoid.set(true);
	}

	public void intakeDeploy() {
		intakeRetracted = false;
		intakeSolenoid.set(false);
	}

	public void hatchRetract() {
		hatchRetracted = true;
		hatchSolenoid.set(true);
	}
	
	public void hatchExtend() {
		hatchRetracted = false;
		hatchSolenoid.set(false);
	}

	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Has Hatch:", hasHatch());
		SmartDashboard.putBoolean("Has Ball:", hasBall());
  }  
}