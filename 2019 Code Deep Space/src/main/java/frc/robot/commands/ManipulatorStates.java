package frc.robot.commands;
import edu.wpi.first.wpilibj.command.*;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;



public class ManipulatorStates extends Command{
        
    public ManipulatorStates() {
        requires(Robot.manipulator);
      }
    
      @Override
      protected void initialize() {
        
      }

      @Override 
      protected boolean isFinished(){
      return true;
}

}