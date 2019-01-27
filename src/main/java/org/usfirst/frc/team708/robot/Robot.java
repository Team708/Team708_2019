//test jnp 1/12/2019 4:38

package org.usfirst.frc.team708.robot;

// import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
// import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
// import edu.wpi.first.wpilibj.drive.DifferentialDrive;
// import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode;
// import org.usfirst.frc.team708.robot.commands.intakeCube.*;
// import org.usfirst.frc.team708.robot.commands.drivetrain.*;
import edu.wpi.cscore.VideoMode;

import org.usfirst.frc.team708.robot.commands.autonomous.*;
import org.usfirst.frc.team708.robot.subsystems.Drivetrain;
import org.usfirst.frc.team708.robot.subsystems.Arm;
import org.usfirst.frc.team708.robot.subsystems.Telescope;
import org.usfirst.frc.team708.robot.subsystems.IntakeCube;
import org.usfirst.frc.team708.robot.subsystems.VisionProcessor;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsCube;
import org.usfirst.frc.team708.robot.subsystems.PneumaticsClimber;
// import org.usfirst.frc.team708.robot.commands.pneumatics.*;
import org.usfirst.frc.team708.robot.Constants;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 * 
 * @cleanup of vision & gamedata - 1/28 @11:24
 */
public class Robot extends TimedRobot {

    Timer statsTimer; // Timer used for Smart Dash statistics

    public static Drivetrain drivetrain;
    public static VisionProcessor visionProcessor;
    public static PneumaticsCube pneumaticsCube;
    public static PneumaticsClimber pneumaticsClimber;
    public static IntakeCube intakeCube;
    public static Arm arm;
    public static Telescope tele;
    public static OI oi;

    public String gameData;
    public String robotLocation;
    public String autoMode;

    public boolean climber = true;
    SendableChooser<Command> autonomousMode = new SendableChooser<>();
    Command autonomousCommand;
    Preferences prefs;

    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    public void robotInit() {

        statsTimer = new Timer(); // Initializes the timer for sending Smart Dashboard data
        statsTimer.start(); // Starts the timer for the Smart Dashboard

        // Subsystem Initialization

        drivetrain = new Drivetrain();
        intakeCube = new IntakeCube();
        pneumaticsCube = new PneumaticsCube();
        pneumaticsClimber = new PneumaticsClimber();
        visionProcessor = new VisionProcessor();
        arm = new Arm();
        tele = new Telescope();

        // visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_OFF);
        Robot.pneumaticsCube.IntakeOff(); /* intake open */ // Switch to Intakeoff during practice field

        sendDashboardSubsystems(); // Sends each subsystem's currently running command to the Smart Dashboard

        queueAutonomousModes(); // Adds autonomous modes to the selection box

        // This MUST BE LAST or a NullPointerException will be thrown
        oi = new OI(); // Initializes the OI

        UsbCamera camerafront = CameraServer.getInstance().startAutomaticCapture(0);
        camerafront.setResolution(320,240);
        camerafront.setFPS(20);


        UsbCamera cameraback = CameraServer.getInstance().startAutomaticCapture(1);
        cameraback.setResolution(320, 240);
        cameraback.setFPS(20);
    }

    /**
     * Runs periodically while the robot is enabled
     */
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
        prefs = Preferences.getInstance();
    }

    /**
     * Runs at the start of autonomous mode
     */
    public void autonomousInit() {
        // schedule the autonomous command

        visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_ON);
        visionProcessor.setNTInfo("camMode", Constants.VISION_PROCESSING_ON);

        gameData = DriverStation.getInstance().getGameSpecificMessage();
        drivetrain.setBrakeMode(true);

        // original dashboard code
        autonomousCommand = (Command) autonomousMode.getSelected();
        if (autonomousCommand != null)
            autonomousCommand.start();

       
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }

    /**
     * Runs when teleop mode initializes
     */
    public void teleopInit() {
        // This makes sure that the autonomous stops running when teleop starts running.
        // If you want the autonomous to continue until interrupted by another command,
        // remove this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
        visionProcessor.setNTInfo("ledMode", Constants.VISION_LED_OFF);
        visionProcessor.setNTInfo("camMode", Constants.VISION_PROCESSING_OFF);
        drivetrain.setBrakeMode(false);
        drivetrain.shiftGearReverse();
        drivetrain.setgear(false);
        drivetrain.resetGyro();

        Robot.pneumaticsClimber.forward(); /* high gear */
        Robot.pneumaticsCube.IntakeOn(); /* intake closed */
    }

    /**
     * This function is called when the disabled button is hit. You can use it to
     * reset subsystems before shutting down.
     */
    public void disabledInit() {
        // testing
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        sendStatistics();
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        sendStatistics();
    }

    /**
     * Sends data from each subsystem periodically as set by sendStatsIntervalSec
     */

    private void sendStatistics() {
        // if (statsTimer.get() >= Constants.SEND_STATS_INTERVAL) statsTimer.reset();
        drivetrain.sendToDashboard();
        intakeCube.sendToDashboard();
        visionProcessor.sendToDashboard();
        pneumaticsCube.sendToDashboard();
        pneumaticsClimber.sendToDashboard();
        arm.sendToDashboard();
        tele.sendToDashboard();
    }

    /**
     * Adds every autonomous mode to the selection box and adds the box to the Smart
     * Dashboard
     */
    private void queueAutonomousModes() {

        // autonomousMode.addOption
        autonomousMode.addOption("Do Nothing", new DoNothing());

        autonomousMode.addOption("Drive in Square", new DriveInSquare());
        autonomousMode.addOption("Drive encoder distance", new driveDistanceEncoder());
        autonomousMode.addOption("Curvature Drive", new driveCurvatureForTime());
        // autonomousMode.addOption("Left Robot Location", new Left_RobotLocation());
        // autonomousMode.addOption("Right Robot Location", new Right_RobotLocation());
        // autonomousMode.addOption("Left Switch Only RobotLocation", new
        // Left_SwitchOnly_RobotLocation());
        // autonomousMode.addOption("Right Switch Only RobotLocation", new
        // Left_SwitchOnly_RobotLocation());
        // autonomousMode.addOption("Center Switch Only RobotLocation", new
        // Center_SwitchOnly_RobotLocation());

        SmartDashboard.putData("Autonomous Selection", autonomousMode);
    }

    /**
     * Sends every subsystem to the Smart Dashboard
     */
    private void sendDashboardSubsystems() {
        SmartDashboard.putData(drivetrain);
        SmartDashboard.putData(intakeCube);
        SmartDashboard.putData(visionProcessor);
        SmartDashboard.putData(arm);
        SmartDashboard.putData(pneumaticsCube);
        SmartDashboard.putData(pneumaticsClimber);
        SmartDashboard.putData(tele);
    }
}
