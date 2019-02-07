package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
//import org.team708.robot.commands.arm.JoystickMoveArm;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
/**
 * Subsystem that intakes balls
 */

public class Intake extends Subsystem {
	
	private WPI_TalonSRX 	ballMaster, hatchMaster;
	private WPI_VictorSPX   ballSlave;

	private Solenoid intakeSolenoid;
	private Solenoid beakSolenoid;
	private Solenoid hatchSolenoid;

	private boolean intakeRetracted	 = true;
	private boolean beakOpened			 = true;
	private boolean hatchRetracted	 = true;

	public DigitalInput 	ballSensor;
	public DigitalInput 	hatchSensor;


	public Intake() {
		ballMaster = new WPI_TalonSRX(RobotMap.ballIntakeMotorMaster);
		ballSlave  = new WPI_VictorSPX(RobotMap.ballIntakeMotorSlave1);
		hatchMaster = new WPI_TalonSRX(RobotMap.hatchIntakeeMotorMaster);

		intakeSolenoid = new Solenoid(RobotMap.intake);
		beakSolenoid = new Solenoid(RobotMap.beak);
		hatchSolenoid = new Solenoid(RobotMap.hatch);

		intakeSolenoid.set(intakeRetracted);
		beakSolenoid.set(beakOpened);
		hatchSolenoid.set(hatchRetracted);

		ballSensor 	= new DigitalInput(RobotMap.ballSensor);
		hatchSensor 	= new DigitalInput(RobotMap.hatchSensor);

		
		ballSlave.follow(ballMaster);
	}
	
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	
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
		
		if (!ballSensor.get()) {
			return (true);
	    }
		else {
			return (false);
		}
	}
	public boolean hasHatch() {
		
		if (!hatchSensor.get()) {
			return (true);
	    }
		else {
			return (false);
		}
	}
	
	public void ballStop(){
		ballMaster.set(Constants.BALL_OFF);
    	SmartDashboard.putNumber("In Stop Motor speed=", 0.0);
	}
	public void hatchStop(){
		hatchMaster.set(Constants.HATCH_OFF);
    	SmartDashboard.putNumber("In Stop Motor speed=", 0.0);
	}
 //Pneumatics
	  public void intakeRetract()
	    {
		  intakeRetracted = true;
		  intakeSolenoid.set(true);
			}
		public void intakeDeploy()
	    {
			intakeRetracted = false;
		  intakeSolenoid.set(false);
	    }
		public void beakOpen()
	    {
		  beakOpened = true;
		  beakSolenoid.set(true);
	    }
	  
	  public void beakClose()
	    {
		  beakOpened = false;
		  beakSolenoid.set(false);
	    }
    public void hatchRetract()
	    {
			hatchRetracted = true;
		  hatchSolenoid.set(true);
	    }
	  
	  public void hatchExtend()
	    {
			hatchRetracted = false;
		  hatchSolenoid.set(false);
	    }
    /**
     * Sends data about the subsystem to the Smart Dashboard
     */
    public void sendToDashboard() {
		if (Constants.DEBUG) {
		}
		// SmartDashboard.putBoolean("Has cube:", hasCube());
    }
    
    
}