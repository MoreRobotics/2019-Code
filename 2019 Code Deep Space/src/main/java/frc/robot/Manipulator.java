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
    final int IRsensorID = -1; 
    final int solePneumatic1ID = -1;
    final int solePneumatic2ID = -1;
    final int Victor888ID = -1;
    Victor Victor888;
    XboxController Xbox; 
    DigitalInput cargoDetect;
    DoubleSolenoid solePneumatics;

    public enum IntakeWheelState{
      SPIN_IN,
      SPIN_OUT,
      SPIN_STOP
    }

    Manipulator() { 
      solePneumatics = new DoubleSolenoid(solePneumatic1ID, solePneumatic2ID);
      cargoDetect = new DigitalInput(IRsensorID);
      Victor888 = new Victor(Victor888ID);
      
      
    } 
    
    public void update() {
      if (cargoDetect.get())
      {
        Victor888.stopMotor();    
      }

      if(Xbox.getBumper(Hand.kLeft))
      {
        if (cargoDetect.get())
        {
        Victor888.set(0);
        }

        Victor888.set(-1);
      }
      if(Xbox.getBumper(Hand.kRight))
      {
        Victor888.set(1);
      }
    
      if (Xbox.getAButton())
      {
        solePneumatics.set(DoubleSolenoid.Value.kForward);
      }
      else 
      {
        solePneumatics.set(DoubleSolenoid.Value.kReverse);
      }

    }
      
      



      
  }

  
