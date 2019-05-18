
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
import frc.robot.Lift.liftState;
//import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Ultrasonic;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.SendableCameraWrapper;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardContainer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the Timed Robot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Start Hatch";
  private static final String kCustomAuto = "Start Cargo";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  //private final int victor8881Chn = 1;
  //private Victor victor8881 = new Victor(victor8881Chn);

  private Lift lift;
  private DriveTrain driveTrain;
  private DriverControl driverControl;
  private Manipulator manipulator;
  private XboxController xbox;
  public Lift.liftState currentState;
  public Lift.liftState nextState;
  public Manipulator.IntakeWheelState intakeWheelState;
  public Manipulator.HatchState hatchState;
  public Manipulator.GrasperState grasperState;
  public Manipulator.FourBarState fourBarState;
  public Manipulator.ClimbState climberState;
  public boolean solenoidShiftHigh;
  CameraServer rPICameraServer;
  public int counter;
  UsbCamera cam;
  CameraServer camServer; 
  CameraServer rpiCam;
  public boolean compressorEnable;
  Compressor compressor;
 


  Robot(){
   // lift = new Lift();
    driverControl = new DriverControl(this);
    driveTrain = new DriveTrain(driverControl.getJoyLeft(), driverControl.getJoyRight(),this);
    manipulator = new Manipulator(this);
    lift = new Lift(driverControl.getXboxController());
    
    
    
    
  }
  
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Start Hatch", kDefaultAuto);
    m_chooser.addOption("Start Cargo", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);
    System.out.println("1714");

    //SpeedControllerGroup motorsLeft = new SpeedControllerGroup(mytalon0, mytalon1);
    //SpeedControllerGroup motorsRight = new SpeedControllerGroup(mytalon2, mytalon3);
    //tankDrive = new DifferentialDrive(motorsLeft, motorsRight);
    //motorsLeft.setInverted(true);
    //motorsRight.setInverted(true);
    //sonic = new Ultrasonic(0,1);
    //sonic.setAutomaticMode(true);
    grasperState = Manipulator.GrasperState.FOLD_OUT;
    hatchState = Manipulator.HatchState.PULL_UP;
    fourBarState = Manipulator.FourBarState.PULL_UP;
    climberState = Manipulator.ClimbState.CLIMB_UP;
    solenoidShiftHigh = false;
    driveTrain.solShifter.set(Value.kReverse);
    manipulator.hatchState.set(Value.kReverse);
    manipulator.grasper.set(Value.kForward);
    manipulator.fourBar.set(Value.kReverse);
    manipulator.climber.set(Value.kReverse);

    compressor.setClosedLoopControl(false);

    lift.init();
    
    camServer = CameraServer.getInstance();
    cam = camServer.startAutomaticCapture();
    cam.setResolution(144, 240);
    cam.setFPS(15);
    
    
    


    /*cameraStream.addDefault("Auto1", 1);
    cameraStream.addObject("Auto2", 2);
    Shuffleboard.putData("Autonomous routine", cameraStream);
    SendableChooser<Integer> cameraStream = new SendableChooser<>();
    //ShuffleboardContainer.add(SendableCameraWrapper.wrap(rPICameraServer.getVideo()));
    rPICameraServer.getInstance().getServer();
    */
  
  

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
    System.out.println("Auto selected: " + m_autoSelected);
    if ( m_autoSelected == kDefaultAuto) {
      hatchState = Manipulator.HatchState.PUSH_DOWN;
    }
    else {
      hatchState = Manipulator.HatchState.PULL_UP;
    }
    this.teleopInit();
  
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    /*driverControl.update();
    driveTrain.update();
    lift.setState(nextState);
    counter++;
    if(counter %5 == 0){
      lift.updateSmartDashboard();
    } */
    this.teleopPeriodic();
  }

  @Override
  public void teleopInit() {
    //System.out.println();
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    driverControl.update();
    driveTrain.update();
    manipulator.update();
    lift.setState(nextState);
    counter++;
    if(counter %50 == 0){
      lift.updateSmartDashboard();
    }
    if(compressorEnable)
    {
      compressor.setClosedLoopControl(true);
    }
    else
    {
      compressor.setClosedLoopControl(false);
    }
    
  }

  @Override
  public void testInit() {
    
 
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testPeriodic() { 


    if (driverControl.getXboxController().getAButton()){
      manipulator.fourBar.set(Value.kForward);
    }
    if (driverControl.getXboxController().getBButton()){
      manipulator.fourBar.set(Value.kReverse);
    }
    if (driverControl.getXboxController().getXButton()){
      manipulator.grasper.set(Value.kForward);
    }
    if (driverControl.getXboxController().getYButton()){
      manipulator.grasper.set(Value.kReverse);
    }
    if (driverControl.getXboxController().getBumper(GenericHID.Hand.kLeft)){
      manipulator.hatchState.set(Value.kForward);
    }
    if (driverControl.getXboxController().getBumper(GenericHID.Hand.kRight)){
      manipulator.hatchState.set(Value.kReverse);
    }


      
  }


}
  


