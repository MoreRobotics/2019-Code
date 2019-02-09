/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID;


/**
 * Add your docs here.
 */
public class DriverControl {
    private static final double triggerThreshold = 0.5;
    Robot robot;
    Joystick joyLeft  = new Joystick(0);
    Joystick joyRight = new Joystick(3);
    XboxController driverController = new XboxController(0);
    XboxController operatorController = new XboxController(1);
    DriverControl(Robot robot){
        this.robot = robot;
    }
    public void update(){
       
        if (operatorController.getRawAxis(-1) != 0) {
            robot.nextState = Lift.liftState.MANUAL;     
        }else if (operatorController.getAButton()) {
            robot.nextState = Lift.liftState.GROUND_LEVEL;
        }else if (operatorController.getXButton()) {
            if (operatorController.getBackButton()) {
                robot.nextState = Lift.liftState.CARGO_LEVEL1;
            }else {
                robot.nextState = Lift.liftState.HATCH_LEVEL1;
            }
        }else if (operatorController.getBButton()) {
            if (operatorController.getBackButton()) {
                robot.nextState = Lift.liftState.CARGO_LEVEL2;
            }else {
                robot.nextState = Lift.liftState.HATCH_LEVEL2;
            }
        }else if (operatorController.getYButton()) {
            if (operatorController.getBackButton()) {
                robot.nextState = Lift.liftState.CARGO_LEVEL3;
            }else {
                robot.nextState = Lift.liftState.HATCH_LEVEL3;
            }
        }   
        if (operatorController.getTriggerAxis(GenericHID.Hand.kRight) > triggerThreshold) {
            //DoubleSolenoid push
            robot.solenoidPush = true;
        }else if (operatorController.getTriggerAxis(GenericHID.Hand.kLeft) > triggerThreshold){
            //DoubleSolenoid retract
            robot.solenoidPush = false;
        }
        if (operatorController.getBumper(GenericHID.Hand.kRight)) {
            //Spin intake in
            robot.intakeWheelState = Manipulator.IntakeWheelState.SPIN_IN;
        }else if (operatorController.getBumper(GenericHID.Hand.kLeft)){
            //Spin intake out
            robot.intakeWheelState = Manipulator.IntakeWheelState.SPIN_OUT;
        }else { 
            //stop spin
            robot.intakeWheelState = Manipulator.IntakeWheelState.SPIN_STOP;
        }

        if (joyRight.getRawButton(3)) {
            //enable vision tracking
        }

        if (joyLeft.getRawButton(3)) {
            //shift solenoid into high shift
            robot.solenoidShiftHigh = true;
        }

        if (joyLeft.getRawButton(2)) {
            //shift solenoid into low shift
            robot.solenoidShiftHigh = false;
        }
    }


}    
/**
 * Plannned xbox controller button mapping
 * Riley Mackey 2/2/2019
 * 
 * Right Bumper- Intake (In)
 * Left Bumper- Intake (Out)
 * Right Trigger- Solenoid out?
 * Left Trigger- Solenoid in?
 * DPad- unassigned
 * JoyStick1(Left Side)- Manual Lift Movement (THIS ONE!)
 * JoyStick2(Right Side)- Unassinged 
 * A Button- Stage 
 * B Button- Stage 
 * X Button- Stage
 * Y Button- Stage
 * Back button- Possibly stage level reset (Tyler) 
 * Start Button- Unassinged 
 * Need a button to return to first stage (got the back button Tyler )
 * We want to map a button to stop wheel movement in the manipulator
 * Need solenoid button
 * 
 * A: Down/ Ground 
 * B: Level 2
 * X: Level 1 
 * Y: Level 3
 * IR sensor determines if it is a cargo or a hatch level based on if cargo
is loaded in the robot. 

Planned joystick control
Left joystick control left side motors
Right Joystick control Right side motors inverted
Right joystick button 3 enable vision tracking
Left joystick button 3 shiftHigh
Left joystick button 2 shiftLow
*/
