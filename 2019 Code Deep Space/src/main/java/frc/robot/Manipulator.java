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
import edu.wpi.first.wpilibj.command.Subsystem;
 

  /**
  * Tyler- I found out that the 888's aren't working because we must calibrate 
  them first, I found the victor 884 users manual that frc says to use to 
  calibrate the 888's (its the vex page on the computer) I set up
  basic templates for two solenoids and a limit switch for the intake 
  as we can't calibrate at the moment. I also made a random class (Climber),
  and made an if statement regarding getting the solenoids when a button of xbox is used. 
  */

  public class Manipulator extends Subsystem { 
    private final int hatchStateID1 = 7;
    private final int hatchStateID2 = 2;
    private final int grasperID1 = 3;
    private final int grasperID2 = 4;
    private final int fourBarID1 = 5;
    private final int fourBarID2 = 6;
    private final int victor888ID = 0;
    private final int climbID1 = 0;
    private final int climbID2 = 1;
    private Victor victor888; 
    public static DoubleSolenoid hatchState;
    public static DoubleSolenoid grasper;
    public DoubleSolenoid fourBar;
    public DoubleSolenoid climber;
    private Robot robot;
    private static final double speedIn = 1;
    private static final double speedOut = -1;
    private static final double speedStop = .25;
    private int counter = 0;

    public enum IntakeWheelState{
      SPIN_IN,
      SPIN_OUT,
      SPIN_STOP
    }

    public enum HatchState {
      PULL_UP,
      PUSH_DOWN,
    }

    public enum GrasperState {
      FOLD_IN,
      FOLD_OUT,
    }

    public enum FourBarState {
      PULL_UP,
      LET_DOWN,
    }
    public enum ClimbState {
      CLIMB_UP,
      CLIMB_DOWN,
    }

    Manipulator(Robot robot) { 
      this.robot = robot;
      grasper = new DoubleSolenoid(0, hatchStateID1, hatchStateID2);
      fourBar = new DoubleSolenoid(0, grasperID1, grasperID2);
      hatchState = new DoubleSolenoid(0, fourBarID1, fourBarID2);
      climber = new DoubleSolenoid(10, climbID1, climbID2);

      victor888 = new Victor(victor888ID);
      
      
      
    } 
    
    public void update() {
      if (robot.intakeWheelState == IntakeWheelState.SPIN_IN) {
          victor888.set(speedIn);
      }
      else if (robot.intakeWheelState == IntakeWheelState.SPIN_OUT) {
        victor888.set(speedOut);
      }
      else if (robot.intakeWheelState == IntakeWheelState.SPIN_STOP) {
        if ( robot.hatchState == HatchState.PULL_UP) {
          victor888.set(speedStop);
        }
        else {
          victor888.stopMotor();
        }
      }
      if (robot.hatchState == HatchState.PULL_UP) {
        if (robot.grasperState == GrasperState.FOLD_OUT){
            hatchState.set(Value.kReverse);
        }
        else{
          robot.hatchState = HatchState.PUSH_DOWN;
          hatchState.set(Value.kForward);
        }
      }
      else if(robot.hatchState == HatchState.PUSH_DOWN){
        hatchState.set(Value.kForward);
      }
      if (robot.grasperState == GrasperState.FOLD_IN){
        if (robot.hatchState == HatchState.PUSH_DOWN){
          grasper.set(Value.kReverse);
        }
        else {
          robot.grasperState = GrasperState.FOLD_OUT;
          grasper.set(Value.kForward);
        }
      }
      else if (robot.grasperState == GrasperState.FOLD_OUT){
        grasper.set(Value.kForward);
      }
      if (robot.fourBarState == FourBarState.PULL_UP){
        fourBar.set(Value.kReverse);
      }
      else if (robot.fourBarState == FourBarState.LET_DOWN){
        fourBar.set(Value.kForward);
      }
      if (robot.climberState == ClimbState.CLIMB_UP) {
        climber.set(Value.kReverse);
      }
      else if (robot.climberState == ClimbState.CLIMB_DOWN) {
        climber.set(Value.kForward);
      }
      

    }

  @Override
  protected void initDefaultCommand() {

  }

 }

  
