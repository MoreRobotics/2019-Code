
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
  TalonSRX mytalon0 = new TalonSRX(0);
  TalonSRX mytalon1 = new TalonSRX(1);
  TalonSRX mytalon2 = new TalonSRX(2);
  TalonSRX mytalon3 = new TalonSRX(3);
  Joystick joyLeft = new Joystick(0);
  Joystick joyRight = new Joystick(3);
  

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
    System.out.println("RT-MWK01");
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
    System.out.println("Coca-Cola");
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    System.out.println("Hello 1714");
  }

  @Override
  public void testInit() {
    System.out.println("Lowaemysh");

    mytalon0.set(ControlMode.PercentOutput, 0);
    mytalon1.set(ControlMode.PercentOutput, 0);
    mytalon2.set(ControlMode.PercentOutput, 0);
    mytalon3.set(ControlMode.PercentOutput, 0);

    mytalon1.follow(mytalon0); 
    mytalon0.setInverted(false);
    mytalon1.setInverted(InvertType.FollowMaster);

    mytalon3.follow(mytalon2);
    mytalon2.setInverted(true);
    mytalon3.setInverted(InvertType.FollowMaster);



    /*for(int i=0;i<10;i++) {

      System.out.println(i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        // 
        e.printStackTrace();
      }

    }*/
  }

  /**
   * This function is called periodically during test mode.
   */

  @Override
  public void testPeriodic() {
    System.out.println("Covfefe");
    /*mytalon0.set(ControlMode.PercentOutput, .2);
    mytalon1.set(ControlMode.PercentOutput, .2);
    mytalon2.set(ControlMode.PercentOutput, .5);
    mytalon3.set(ControlMode.PercentOutput, .5);*/

    double stick0 = joyLeft.getRawAxis(1) * -1;  
    System.out.println("stick:" + stick0);

    mytalon0.set(ControlMode.PercentOutput, stick0);

    double stick1 = joyRight.getRawAxis(1) * -1;
    System.out.println("stick:" + stick1);

    mytalon2.set(ControlMode.PercentOutput, stick1);

  }
}
