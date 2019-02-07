/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;







/**
 * Add your docs here.
 */
public class DriverControl {
    Joystick joyLeft  = new Joystick(0);
    Joystick joyRight = new Joystick(3);
    XboxController driverController = new XboxController(0);
    XboxController operatorController = new XboxController(1);

    public void update(){
        if(Xbox.getRawAxis(-1) != 0) {
            nextState = liftState.MANUAL;     
        }else if(Xbox.getAButton()) {
            nextState = liftState.GROUND_LEVEL;
        }else if(Xbox.getXButton()) {
            if(Xbox.getBackButton()) {
                nextState = liftState.CARGO_LEVEL1;
            }else {
                nextState = liftState.HATCH_LEVEL1;
            }
        }else if(Xbox.getBButton()) {
            if(Xbox.getBackButton()) {
                nextState = liftState.CARGO_LEVEL2;
            }else {
                nextState = liftState.HATCH_LEVEL2;
            }
        }else if(Xbox.getYButton()) {
            if(Xbox.getBackButton()) {
                nextState = liftState.CARGO_LEVEL3;
            }else {
                nextState = liftState.HATCH_LEVEL3;
            }
        }   
        setState(nextState);
    }    
}
/**
 * Plannned button mapping
 * Riley Mackey 2/2/2019
 * 
 * Right Bumper- Intake (In)
 * Left Bumper- Intake (Out)
 * Right Trigger- Unassinged 
 * Left Trigger- Unassinged 
 * DPad- unassigned
 * JoyStick1(Left Side)- Manual Lift Movement
 * JoyStick2(Right Side)- Unassinged 
 * A Button- Stage 
 * B Button- Stage 
 * X Button- Stage
 * Y Button- Stage
 * Back button- Possibly stage level reset (Tyler) 
 * Start Button- Unassinged 
 * Need a button to return to first stage (got the bak button Tyler )
 * We want to map a button to stop wheel movement in the manipulator
 * Need solenoid button
 * 
 * A: Down/ Ground 
 * B: Level 2
 * X: Level 1 
 * Y: Level 3
 * IR sensor determines if it is a cargo or a hatch level based on if cargo
is loaded in the robot. 
*/

    




