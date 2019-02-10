package org.usfirst.frc.team708.robot.subsystems;

import org.usfirst.frc.team708.robot.Constants;
import org.usfirst.frc.team708.robot.RobotMap;
import org.usfirst.frc.team708.robot.commands.drivetrain.JoystickDrive;
import org.usfirst.frc.team708.robot.util.IRSensor;
import org.usfirst.frc.team708.robot.util.UltrasonicSensor;
import org.usfirst.frc.team708.robot.util.Math708;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import com.analog.adis16448.frc.ADIS16448_IMU;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.*;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This class is a drivetrain subsystem that uses PID to drive straight.
 * @author Nam Tran & Viet Tran
 */

public class Drivetrain extends PIDSubsystem {

	// private WPI_TalonSRX leftMaster, rightMaster;	// Motor Controllers
	// private WPI_VictorSPX	leftSlave1, leftSlave2, rightSlave2, rightSlave1;
	private CANSparkMax leftMaster, rightMaster, leftSlave1, rightSlave1;
	private CANEncoder encoderLeft;
	private CANEncoder encoderRight;
	
	// Variables specific for drivetrain PID loop
	private double moveSpeed = 0.0;
	private double pidOutput = 0.0;
	
	private double gearratio = Constants.DRIVETRAIN_GEAR_RATIO_LOW;
	
	private DifferentialDrive drivetrain;						// FRC provided drivetrain class
	private double distancePerPulse;
	private boolean gearHigh = false;
	// private ADXRS450_Gyro gyro;							// Gyro that is used for drift correction
	private ADIS16448_IMU gyro;
	
	private DoubleSolenoid gearShiftSolenoid;
	
	private IRSensor drivetrainIRSensor;					// IR Sensor for <=25inches
	private UltrasonicSensor drivetrainUltrasonicSensor;	// Sonar used for <=21feet
	private DigitalInput lineSensor;
	
	private boolean brake = true;		// Whether the talons should be in coast or brake mode
						// (this could be important if a jerky robot causes things to topple
	private boolean usePID = false;
	
    /**
     * Constructor
     */
    public Drivetrain() {
    	// Passes variables from this class into the superclass constructor
    	super("Drivetrain", Constants.Kp, Constants.Ki, Constants.Kd);
    	
    	// Initializes motor controllers with device IDs from RobotMap
		leftMaster = new CANSparkMax(RobotMap.drivetrainLeftMotorMaster, MotorType.kBrushless);
		leftSlave1 = new CANSparkMax(RobotMap.drivetrainLeftMotorSlave1, MotorType.kBrushless);
		rightMaster = new CANSparkMax(RobotMap.drivetrainRightMotorMaster, MotorType.kBrushless);
		rightSlave1 = new CANSparkMax(RobotMap.drivetrainRightMotorSlave1, MotorType.kBrushless);
		
		SpeedControllerGroup leftMotors = new SpeedControllerGroup(leftMaster, leftSlave1);
		SpeedControllerGroup rightMotors = new SpeedControllerGroup(rightMaster, rightSlave1);
		
		drivetrain = new DifferentialDrive(leftMotors, rightMotors);	// Initializes drivetrain class
		
		gyro = new ADIS16448_IMU();
		gyro.reset();
		
		encoderLeft = new CANEncoder(leftMaster);
		encoderRight = new CANEncoder(rightMaster);

		// Initializes the sensors
		distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) /
					                  	(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV * gearratio);
		// Sets the distance per pulse of the encoder to read distance properly
		resetEncoder();
		// encoderLeft.setDistancePerPulse(distancePerPulse);
		// encoderRight.setDistancePerPulse(distancePerPulse);
		lineSensor = new DigitalInput(RobotMap.lineSensor);
		gearShiftSolenoid = new DoubleSolenoid(RobotMap.driveShiftLow, RobotMap.driveShiftHigh);
    }
    /**
     * Initializes the default command for this subsystem
     */
    @Override
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new JoystickDrive());
    }
   
 
    /**
     * Drives the drivetrain using a forward-backward value and a rotation value
     * @param move
     * @param rotate
     */
    public void haloDrive(double move, double rotate, boolean usePID) {
    	// Checks whether drift correction is needed
    	
    	// Sets multiplier for max drive speed
    	move = move * Constants.DRIVE_MOTOR_MAX_SPEED;
    	rotate = rotate * Constants.ROTATE_MOTOR_MAX_SPEED;
    	
    	if (usePID) {
	    	if (rotate == 0.0 && move > 0.0) {
	    		// Enables the PID controller if it is not already
	    		if (!getPIDController().isEnabled()) {
	    			getPIDController().setPID(Constants.KpForward, Constants.KiForward, Constants.KdForward);
	    			getPIDController().reset();
	    			gyro.reset();
	    			enable();
	    			gyro.reset();
	    		}
	    		// Sets the forward move speed to the move parameter
	    		moveSpeed = move;
	    	} else if (rotate == 0.0 && move < 0.0){
	    		// Enables the PID controller if it is not already
	    		if (!getPIDController().isEnabled()) {
	    			getPIDController().setPID(Constants.KpBackward, Constants.KiBackward, Constants.KdBackward);
	    			getPIDController().reset();
	    			gyro.reset();
	    			enable();
	    			gyro.reset();
	    		}
	    		// Sets the forward move speed to the move parameter
	    		moveSpeed = move;
	    	} else {
	    		// Disables the PID controller if it enabled so the drivetrain can move freely
	    		if (getPIDController().isEnabled()) {
	    			getPIDController().reset();
	    		}
	    		drivetrain.arcadeDrive(move, rotate);
	    	}
    	} else {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnabled()) {
    			getPIDController().reset();
    		}
    		drivetrain.arcadeDrive(move, rotate);
    	}
    }
	
	// public void haloDrive(double move, double rotate) {
	// 	haloDrive(move, rotate, this.usePID);
	// }
		
	/**
	 * Drive the drivetrain using curvature drive
	 * @param xSpeed
	 * @param zRotation
	 * @param isQuickTurn
	 */
    public void curvatureDrive(double xSpeed, double zRotation, boolean isQuickTurn) {
    	drivetrain.curvatureDrive(xSpeed, zRotation, isQuickTurn);
    }
	    
    /**
     * Drives the drivetrain using a left motor(s) value and a right motor(s) value
     * @param left
     * @param right
     */
    public void tankDrive(double left, double right) {
    	// Checks whether drift correction is needed
    	if (Math.abs(left - right) < Constants.TANK_STICK_TOLERANCE && left != 0.0 && right != 0.0) {
    		// Enables the PID controller if it is not already
    		if (!getPIDController().isEnabled()) {
    			gyro.reset();
    			getPIDController().reset();
    			enable();
    		}
    		// Sets the forward move speed to the average of the two sticks
    		moveSpeed = ((left + right) / 2);
    	} else {
    		// Disables the PID controller if it enabled so the drivetrain can move freely
    		if (getPIDController().isEnabled()) {
    			disable();
    		}
    		drivetrain.tankDrive(left, right);
    	}
    }

	public boolean getUsePID() {
		return usePID;
	}
	
	public void setUsePID(boolean usePID) {
		this.usePID = usePID;
	}
    
    public void stop() {
    	leftMaster.set(Constants.DRIVE_MOTOR_OFF);
    	rightMaster.set(Constants.DRIVE_MOTOR_OFF);
    }
    
    /**
     * Gets the degrees that the gyro is reading
     * @return
     */
    public double getAngle() {
		//    return gyro.getAngle();
		   return  Math708.round(gyro.getAngleZ(),0);
	}
	
	public boolean isTiltingLeft() {
		if (gyro.getRoll() >= Constants.ROLL_MAX) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isTiltingRight() {
		if (gyro.getRoll() <= -Constants.PITCH_MAX) {
			return true;
		}
		else {
			return false;
		}
	}
	public boolean isTiltingForward() {
		if (gyro.getPitch() <= -Constants.PITCH_MAX) {
			return true;
		}
		else {
			return false;
		}
	}
  public boolean isTiltingBack() {
		if (gyro.getPitch() >= Constants.PITCH_MAX) {
			return true;
		}
		else {
			return false;
		}
	}	  
    /**
     * Resets the gyro reading
     */
    public void resetGyro() {
		gyro.reset();
    }
    
    public double rotateByGyro(double targetAngle, double tolerance) {
    	double difference = getAngle() - targetAngle;
 
    	if (Math708.isWithinThreshold(gyro.getAngle(), targetAngle, tolerance)) {
    		difference = 0.0;
    	}
    	
    	return difference / targetAngle;
    }
    
    public double getIRDistance() {
    	return drivetrainIRSensor.getAverageDistance();
    }
    
    public double getSonarDistance() {
    	return drivetrainUltrasonicSensor.getClippedAverageDistance();
    }
    
    /**
     * Returns the move speed of the robot needed to get to a certain IR distance reading.
     * This assumes that the IR sensor is in the front of the robot.
     * @param targetDistance
     * @return
     */
    public double moveByIR(double targetDistance, double minSpeed, double maxSpeed, double tolerance) {
    	double current_location = getIRDistance();
    	
    	double value = Math708.getClippedPercentError(current_location, targetDistance, minSpeed, maxSpeed);
    	
    	if (value <= 0.0 || ((Math.abs(current_location - targetDistance)) <= tolerance)) {
    		
    		return 0.0;
    	}
    	return value;
    }

    /**
     * Returns the move speed of the robot needed to get to a certain Sonar distance reading.
     * This assumes that the Sonar sensor is in the front of the robot.
     * @param targetDistance
     * @return
     */
    public double moveByUltrasonic(double targetDistance, double minSpeed, double maxSpeed, double tolerance) {
    	double value = Math708.getClippedPercentError(getSonarDistance(), targetDistance, minSpeed, maxSpeed);
    	
    	if (value <= 0.0 || ((Math.abs(getSonarDistance() - targetDistance)) <= tolerance)) {
    		return 0.0;
    	}
    	return value;
    }
    

    public void toggleBrakeMode() {
    	brake = !brake;
    	if (brake) {
			// leftMaster.setNeutralMode(NeutralMode.Brake);
    		// leftSlave1.setNeutralMode(NeutralMode.Brake);
    		// leftSlave2.setNeutralMode(NeutralMode.Brake);
    		// rightMaster.setNeutralMode(NeutralMode.Brake);
    		// rightSlave1.setNeutralMode(NeutralMode.Brake);
			// rightSlave2.setNeutralMode(NeutralMode.Brake);
			leftMaster.setIdleMode(IdleMode.kBrake);
			leftSlave1.setIdleMode(IdleMode.kBrake);
			rightMaster.setIdleMode(IdleMode.kBrake);
			rightSlave1.setIdleMode(IdleMode.kBrake);
    	} else {
    		// leftMaster.setNeutralMode(NeutralMode.Coast);
    		// leftSlave1.setNeutralMode(NeutralMode.Coast);
    		// leftSlave2.setNeutralMode(NeutralMode.Coast);
    		// rightMaster.setNeutralMode(NeutralMode.Coast);
    		// rightSlave1.setNeutralMode(NeutralMode.Coast);
			// rightSlave2.setNeutralMode(NeutralMode.Coast);
			leftMaster.setIdleMode(IdleMode.kCoast);
			leftSlave1.setIdleMode(IdleMode.kCoast);
			rightMaster.setIdleMode(IdleMode.kCoast);
			rightSlave1.setIdleMode(IdleMode.kCoast);
    	}
    }
    
    public void setBrakeMode(boolean setBrake) {
    	brake = setBrake;
    	if (brake) {
    		leftMaster.setIdleMode(IdleMode.kBrake);
			leftSlave1.setIdleMode(IdleMode.kBrake);
			rightMaster.setIdleMode(IdleMode.kBrake);
			rightSlave1.setIdleMode(IdleMode.kBrake);
    	} else {
    		leftMaster.setIdleMode(IdleMode.kCoast);
			leftSlave1.setIdleMode(IdleMode.kCoast);
			rightMaster.setIdleMode(IdleMode.kCoast);
			rightSlave1.setIdleMode(IdleMode.kCoast);
    	}
    }

    public void shiftGearHigh() {
		gearShiftSolenoid.set(DoubleSolenoid.Value.kForward);
		gearHigh = true;
		gearratio = Constants.DRIVETRAIN_GEAR_RATIO_HIGH;
		distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) /
					                   	(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV * gearratio);
    }
    
    public void shiftGearlow() {
		gearShiftSolenoid.set(DoubleSolenoid.Value.kReverse);
		gearHigh = false;
		gearratio = Constants.DRIVETRAIN_GEAR_RATIO_LOW;
		distancePerPulse = (Constants.DRIVETRAIN_WHEEL_DIAMETER * Math.PI) /
					                   	(Constants.DRIVETRAIN_ENCODER_PULSES_PER_REV * gearratio);
    }
        
    /**
     * Sets encoder direction depending on which side of the drivetrain it is on
     */

    // public void setEncoderReading2() {
    // 	encoder.setReverseDirection(!Constants.DRIVETRAIN_USE_LEFT_ENCODER);
    // }
    
    /**
     * 
     * @return Distance traveled since last encoder reset
     */
  	public double getEncoderDistanceLeft() {
  	 	return encoderLeft.getPosition() * distancePerPulse;
  	 }
    public double getEncoderDistanceRight() {
    	return encoderRight.getPosition() * distancePerPulse;
    }
    /**
     * Resets the encoder to 0.0
     */
    public void resetEncoder() {
		encoderLeft.setPosition(0.0);
		encoderRight.setPosition(0.0);
    }
     /**
     * Returns if the optical sensor detects the color white
     * @return
     */
  	public boolean isOnLine() {
   		return !lineSensor.get();
	}
	  
  	/**
     * Returns a process variable to the PIDSubsystem for correction
     */
    @Override
	protected double returnPIDInput() {
    	return getAngle();
    }
    
    /**
     * Performs actions using the robot to correct for any error using the outputed value
     */
    @Override
	protected void usePIDOutput(double output) {
        pidOutput = output;
        drivetrain.arcadeDrive(moveSpeed, -output);
    }
     
    
    /**
     * Sends data for this subsystem to the dashboard
     */
    public void sendToDashboard() {
    	if (Constants.DEBUG) {
			SmartDashboard.putNumber("DT Encoder Left Raw", encoderLeft.getPosition());		// Encoder raw count
			SmartDashboard.putNumber("DT Encoder Right Raw", encoderRight.getPosition());		// Encoder raw count
			SmartDashboard.putNumber("DT Encoder Left Inches", getEncoderDistanceLeft());		// Encoder raw count
			SmartDashboard.putNumber("DT Encoder Right Inches", getEncoderDistanceRight());		// Encoder raw count

	    SmartDashboard.putBoolean("Brake", brake);					// Brake or Coast
	    	
			SmartDashboard.putNumber("Gyro turn angle", getAngle());
			SmartDashboard.putNumber("Roll", Math708.round(gyro.getRoll(),0));
			SmartDashboard.putNumber("Pitch", Math708.round(gyro.getPitch(),0));
			SmartDashboard.putNumber("Yaw", Math708.round(gyro.getYaw(),0));
	  }
	}
}
