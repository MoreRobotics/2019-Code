/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
//import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * Add your docs here.
 */
public class Lift {
    final int botSwitchPin = 1;
    //final int topSwitchPin = -1;  
    final int motor1ID = 7;
    final int motor2ID = 8;

    final double maxSpeedUp = .5;
    final double maxSpeedDown = -.5;
    double target;
    double liftEncoderMultiplier;
    public TalonSRX leftMotor;
    VictorSPX rightMotor;
    DigitalInput botSwitch;
    //DigitalInput topSwitch;
    XboxController xbox;
    final int maxEncoder = 700;
    public FeedbackDevice liftEncoder;
    StringBuilder _sb;
    Object motorOutput;
    
    
    public enum liftState{
        GROUND_LEVEL, 
        CARGO_LEVEL1, 
        CARGO_LEVEL2,
        CARGO_LEVEL3,
        HATCH_LEVEL1,
        HATCH_LEVEL2,
        HATCH_LEVEL3,
        MANUAL
    
    }
        liftState currentState;
        liftState nextState;


    
    Lift(XboxController xbox){ 
        leftMotor = new TalonSRX(motor1ID);
        rightMotor = new VictorSPX(motor2ID);
        leftMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        botSwitch = new DigitalInput(botSwitchPin);
        //topSwitch = new DigitalInput(topSwitchPin);
        liftEncoderMultiplier = 141;
        rightMotor.follow(leftMotor);
        leftMotor.setInverted(true);
        this.xbox = xbox;
        leftMotor.configMotionCruiseVelocity(15, 0);
        leftMotor.configMotionAcceleration(6, 0);
        StringBuilder _sb = new StringBuilder();

         

        //Use Github examples for motion magic
    }


/**
 * Either we set stage select as able to be switched between cargo
 * and hatches or we program each individual stage.
 * Important for mapping the Xbox controller.
 * We may want to code a button that returns to the first stage.
 * We still want to decide whether or not we will incorporate manual operations.
 * 
 */
 

    public void setState(liftState stage) {
        double moterOutput = leftMotor.getMotorOutputPercent();
        _sb.append(motorOutput);

        switch(stage) {
            case GROUND_LEVEL: 
            //Ground level
            //check encoder value, run motor up or down based on encoder value
            // all targets are measurements in cm. 
            // each level adds 71 (2ft 4 in) cm to the previous 
            target = 0;
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("GroundLevel " + target);
            //if (liftEncoder.get() < )
            
        
            //This will cause the motors to run until the target                       
            break;            
            default:
            case HATCH_LEVEL1:
            //Hatch level 1 (1ft 7in)
            target = 48 * liftEncoderMultiplier;
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("HatchLevel1 " + target);

            break;
            case CARGO_LEVEL1:
            //Cargo level 1 (2ft 3.5 in)
            target = 70 * liftEncoderMultiplier;
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("CargoLevel1 " + target);

            break;
            case HATCH_LEVEL2:
            //Hatch level 2 
            target = 119 * liftEncoderMultiplier; 
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("HatchLevel2 " + target);

            break;
            case CARGO_LEVEL2:
            //cargo level 2
            target = 141 * liftEncoderMultiplier;
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("CargoLevel2 " + target);

            break;
            case HATCH_LEVEL3:
            //Hatch level 3
            target = 190 * liftEncoderMultiplier;
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("HatchLevel3 " + target);

            break;
            case CARGO_LEVEL3:
            //Cargo level 3
            target = 212 * liftEncoderMultiplier;
            leftMotor.set(ControlMode.MotionMagic, target);
            System.out.println("CatchLevel3 " + target);

            break;
            case MANUAL:
            leftMotor.set(ControlMode.PercentOutput, xbox.getY(Hand.kLeft));
            /*if(!botSwitch.get() && xbox.getY(Hand.kLeft) < 0){
                leftMotor.set(ControlMode.PercentOutput,0);
                leftMotor.getSensorCollection().setQuadraturePosition(0, 0);
            }else if(leftMotor.getSensorCollection().getQuadraturePosition() > maxEncoder 
                && xbox.getY(Hand.kLeft) > 0){
                leftMotor.set(ControlMode.PercentOutput, 0);
            }else {
                leftMotor.set(ControlMode.PercentOutput, xbox.getY(Hand.kLeft));
            }*/
            
            if(!botSwitch.get()){
                leftMotor.getSensorCollection().setQuadraturePosition(0, 0);
                
            }
                //need to find the axis for the joystick
        }
    }


}
