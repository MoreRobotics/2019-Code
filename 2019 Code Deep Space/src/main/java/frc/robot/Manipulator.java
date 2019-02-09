/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * What we need to program:
 * 2 Solenoids for Pneumatics (boolean)
 * Victor 888 MotorController 
 * double Wheel speed 
 * Intake in and out
 * Stop for Wheels
 * Limit Switch In back of intake
 */

 package frc.robot;
  


 import edu.wpi.first.wpilibj.DigitalInput;
 import edu.wpi.first.wpilibj.DoubleSolenoid;
 import edu.wpi.first.wpilibj.Victor;
 import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
 

  /**
  * Tyler- I found out that the 888's aren't working because we must calibrate 
  them first, I found the victor 884 users manual that frc says to use to 
  calibrate the 888's (its the vex page on the computer) I set up
  basic templates for two solenoids and a limit switch for the intake 
  as we can't calibrate at the moment. I also made a random class (Climber),
  and made an if statement regarding getting the solenoids when a button of xbox is used. 
  */

  public class Manipulator {
    final int IRsensorID = 5; 
    final int solePneumatic1ID = 2;
    final int solePneumatic2ID = 3;
    final int victor888ID = 9;
    Victor victor888; 
    DigitalInput cargoDetect;
    DoubleSolenoid solePneumatics1;
    private Robot robot;
    private static final double speedIn = 1;
    private static final double speedOut = -1;

    public enum IntakeWheelState{
      SPIN_IN,
      SPIN_OUT,
      SPIN_STOP
    }

    Manipulator(Robot robot) { 
      this.robot = robot;
      solePneumatics1 = new DoubleSolenoid(solePneumatic1ID, solePneumatic2ID);
      cargoDetect = new DigitalInput(IRsensorID);
      victor888 = new Victor(victor888ID);
      
      
      
    } 
    
    public void update() {
      if (robot.intakeWheelState == IntakeWheelState.SPIN_IN) {
        if (cargoDetect.get()) {
          victor888.stopMotor();
        }
        else {
          victor888.set(speedIn);
        }
        
      }
      else if (robot.intakeWheelState == IntakeWheelState.SPIN_OUT) {
        victor888.set(speedOut);
      }
      else if (robot.intakeWheelState == IntakeWheelState.SPIN_STOP) {
        victor888.stopMotor();
      }
      if (robot.solenoidPush == true) {
        solePneumatics1.set(Value.kForward);
      }
      else {
        solePneumatics1.set(Value.kReverse);
      }

    } 

 }

  
