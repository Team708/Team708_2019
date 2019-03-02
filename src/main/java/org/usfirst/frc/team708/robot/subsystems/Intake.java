package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.intake.RetractIntake;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;


public class Intake extends Subsystem {
	
	private WPI_TalonSRX 	ballMaster;
//	private WPI_TalonSRX 	hatchMaster;
//	private WPI_VictorSPX	ballSlave;

	private Solenoid intakeSolenoid;
	private Solenoid hatchSolenoid;
	private Solenoid beakSolenoid;

	private boolean intakeDeployed	 = true;
	private boolean hatchRetracted	 = true;
	private boolean beakOpened		 = true;				

	public DigitalInput 	ballSensor;
	public DigitalInput 	hatchSensor;


	public Intake() {
		ballMaster	= new WPI_TalonSRX(RobotMap.ballIntakeMotorMaster);
		// ballSlave 	= new WPI_VictorSPX(RobotMap.ballIntakeMotorSlave1);

		intakeSolenoid 	= new Solenoid(RobotMap.intake);
		hatchSolenoid	= new Solenoid(RobotMap.hatch);
		beakSolenoid	= new Solenoid(RobotMap.beak);

		intakeSolenoid.set(intakeDeployed);
		hatchSolenoid.set(hatchRetracted);

		ballSensor 		= new DigitalInput(RobotMap.ballSensor);
		hatchSensor 	= new DigitalInput(RobotMap.hatchSensor);

		ballMaster.setNeutralMode(NeutralMode.Brake);

		// ballSlave.follow(ballMaster);
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
		ballMaster.set(-speed);
	}
	
	public void stopMotorHatch() {
		ballMaster.set(0.0);
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

 	//Pneumatics
	public void intakeRetract() {
		intakeDeployed = false;
		intakeSolenoid.set(intakeDeployed);
	}

	public void intakeDeploy() {
		intakeDeployed = true;
		intakeSolenoid.set(intakeDeployed);
	}

	public void intakeToggle() {
		intakeDeployed = !intakeDeployed;
		intakeSolenoid.set(intakeDeployed);
	}

	public void hatchRetract() {
		hatchRetracted = true;
		hatchSolenoid.set(hatchRetracted);
	}
	
	public void hatchExtend() {
		hatchRetracted = false;
		hatchSolenoid.set(hatchRetracted);
	}

	public void hatchToggle() {
		hatchRetracted = !hatchRetracted;
		hatchSolenoid.set(hatchRetracted);
	}
	public void beakOpen() {
		beakOpened = true;
		beakSolenoid.set(beakOpened);
	}
	
	public void beakClose() {
		beakOpened = false;
		beakSolenoid.set(beakOpened);
	}

	public void beakToggle() {
		beakOpened = !beakOpened;
		beakSolenoid.set(beakOpened);
	}

	public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		SmartDashboard.putBoolean("Has Hatch:", hasHatch());
		SmartDashboard.putBoolean("Has Ball:", hasBall());
		SmartDashboard.putBoolean("Intake Deployed", intakeDeployed);
		SmartDashboard.putBoolean("Beak Opened:", beakOpened);
		SmartDashboard.putBoolean("Hatch Retracted:", hatchRetracted);
	}  
}