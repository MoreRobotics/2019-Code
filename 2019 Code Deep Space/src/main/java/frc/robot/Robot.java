
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */

package frc.robot;

//import com.ctre.phoenix.motorcontrol.can.*;
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.InvertType;

//import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.Joystick.AxisType;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the Timed Robot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  //private final int victor8881Chn = 1;
  //private Victor victor8881 = new Victor(victor8881Chn);
  //private Lift lift;
  private DriveTrain driveTrain;
  private DriverControl driverControl;
  private XboxController xbox;
  public boolean solenoidPush;
  public Lift.liftState currentState;
  public Lift.liftState nextState;
  public Manipulator.IntakeWheelState intakeWheelState;
  public boolean solenoidShiftHigh;

  Robot(){
   // lift = new Lift();
    driverControl = new DriverControl(this);
    driveTrain = new DriveTrain(driverControl.getJoyLeft(), driverControl.getJoyRight());
  }
  



  


  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    System.out.println("1714");
    //SpeedControllerGroup motorsLeft = new SpeedControllerGroup(mytalon0, mytalon1);
    //SpeedControllerGroup motorsRight = new SpeedControllerGroup(mytalon2, mytalon3);
    //tankDrive = new DifferentialDrive(motorsLeft, motorsRight);
    //motorsLeft.setInverted(true);
    //motorsRight.setInverted(true);
    //sonic = new Ultrasonic(0,1);
    //sonic.setAutomaticMode(true);
    solenoidPush = false;
    solenoidShiftHigh = false;
    
  
  

  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // autoSelected = SmartDashboard.getString("Auto Selector",
    // defaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    System.out.println("RT-MWK06");
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  @Override
  public void teleopInit() {
    System.out.println();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    driverControl.update();
    driveTrain.update();
  }

  @Override
  public void testInit() {
    xbox = new XboxController(2); 
 
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testPeriodic() {  
    System.out.println(xbox.getTriggerAxis(GenericHID.Hand.kRight));

  }
  
}
