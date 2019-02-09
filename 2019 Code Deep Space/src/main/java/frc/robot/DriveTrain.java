/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


/**
 * Add your docs here.
 */
public class DriveTrain {
  private DifferentialDrive tankDrive; 
  private DoubleSolenoid solShifter;
  private Ultrasonic sonic1;
  private Ultrasonic sonic2;
  private WPI_TalonSRX talonLeft1;
  private WPI_TalonSRX talonLeft2;
  private WPI_TalonSRX talonLeft3;
  private WPI_TalonSRX talonRight1;
  private WPI_TalonSRX talonRight2;
  private WPI_TalonSRX talonRight3;
  private SpeedControllerGroup leftSide;
  private SpeedControllerGroup rightSide;
  private Joystick joyLeft;
  private Joystick joyRight;
  private Robot robot;
  private static final int solShifterPin1 = 0;
  private static final int solShifterPin2 = 1;
  private static final int sonic1PinOut = 9;
  private static final int sonic1PinIn = 8;
  private static final int sonic2PinOut = 7;
  private static final int sonic2PinIn = 6;
  

  DriveTrain(Joystick joyLeft, Joystick joyRight, Robot robot) {
    talonLeft1 = new WPI_TalonSRX(-1);
    talonLeft2 = new WPI_TalonSRX(-1);
    talonLeft3 = new WPI_TalonSRX(-1);
    talonRight1 = new WPI_TalonSRX(-1);
    talonRight2 = new WPI_TalonSRX(-1);
    talonRight3 = new WPI_TalonSRX(-1);
    leftSide = new SpeedControllerGroup(talonLeft1, talonLeft2, talonLeft3);
    rightSide = new SpeedControllerGroup(talonRight1, talonRight2, talonRight3);
    tankDrive = new DifferentialDrive(leftSide, rightSide);
    solShifter = new DoubleSolenoid (solShifterPin1, solShifterPin2);
    sonic1 = new Ultrasonic(sonic1PinOut, sonic1PinIn);
    sonic2 = new Ultrasonic(sonic2PinOut, sonic2PinIn);
    this.joyLeft = joyLeft;
    this.joyRight = joyRight;
    this.robot = robot;

    rightSide.setInverted(true);
    leftSide.setInverted(false);


  }

  public void update() {
    tankDrive.tankDrive(joyLeft.getY(), joyRight.getY());
    
    if (robot.solenoidShiftHigh == true) {
      solShifter.set(Value.kForward);
    }
    else {
      solShifter.set(Value.kReverse);
    }

  }
    
          



  




}