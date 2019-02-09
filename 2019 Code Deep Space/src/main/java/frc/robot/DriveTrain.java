/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.util.WPILibVersion;

/**
 * Add your docs here.
 */
public class DriveTrain {
    private DifferentialDrive tankDrive; 
    private Solenoid solShifter;
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

    DriveTrain(Joystick joyLeft, Joystick joyRight) {
        talonLeft1 = new WPI_TalonSRX(-1);
        talonLeft2 = new WPI_TalonSRX(-1);
        talonLeft3 = new WPI_TalonSRX(-1);
        talonRight1 = new WPI_TalonSRX(-1);
        talonRight2 = new WPI_TalonSRX(-1);
        talonRight3 = new WPI_TalonSRX(-1);
        leftSide = new SpeedControllerGroup(talonLeft1, talonLeft2, talonLeft3);
        rightSide = new SpeedControllerGroup(talonRight1, talonRight2, talonRight3);
        tankDrive = new DifferentialDrive(leftSide, rightSide);
        solShifter = new Solenoid(-1);
        sonic1 = new Ultrasonic(-1, -1);
        sonic2 = new Ultrasonic(-1, -1);
        this.joyLeft = joyLeft;
        this.joyRight = joyRight;

        talonLeft2.follow(talonLeft1);
        talonLeft3.follow(talonLeft1);
        talonRight2.follow(talonRight1);
        talonRight3.follow(talonRight1);
        rightSide.setInverted(true);
        leftSide.setInverted(false);
        
    }

    public void update() {
        tankDrive.tankDrive(joyLeft.getY(), joyRight.getY());
        



    }




}
