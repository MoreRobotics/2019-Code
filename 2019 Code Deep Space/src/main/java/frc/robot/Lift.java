/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * Add your docs here.
 */
public class Lift {
    final int botSwitchPin = -1;
    final int topSwitchPin = -1;  
    final int encoderPinA = -1;
    final int encoderPinB = -1;
    final int motor1ID  = -1;
    final int motor2ID = -1;

    final double maxSpeedUp = .5;
    final double maxSpeedDown = -.5;

    WPI_VictorSPX motor1;
    WPI_VictorSPX motor2;

    SpeedControllerGroup liftMotors;

    DigitalInput botSwitch;
    DigitalInput topSwitch;
    Encoder liftEncoder;


    Lift(){
        motor1 = new WPI_VictorSPX(motor1ID);
        motor2 = new WPI_VictorSPX(motor2ID);

        liftMotors = new SpeedControllerGroup(motor1, motor2);
        
        botSwitch = new DigitalInput(botSwitchPin);
        topSwitch = new DigitalInput(topSwitchPin);

        liftEncoder = new Encoder(encoderPinA, encoderPinB);
    }

    public void update() {

    }

    public void setStage(int stage) {
        switch(stage) {
            case 1: 
            break;
            default:
        }
    }


}
