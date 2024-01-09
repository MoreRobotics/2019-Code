package frc.robot.commands;
import edu.wpi.first.wpilibj.command.*;
import frc.robot.Robot;
import frc.robot.Manipulator;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Victor;



public class ManipulatorStates extends Command{
        private Manipulator.HatchState hatchState;
        private Manipulator.GrasperState grasperState; 
    public ManipulatorStates(Manipulator.HatchState hatchState, Manipulator.GrasperState grasperState) {
        this.hatchState = hatchState;
        this.grasperState = grasperState;

     
        requires(Robot.manipulator);
      }


      @Override
      protected void initialize() {
        
      }

      @Override 
      protected boolean isFinished(){
        if (hatchState == Manipulator.HatchState.PULL_UP) {
            if (grasperState == Manipulator.GrasperState.FOLD_OUT){
                Manipulator.hatchState.set(Value.kReverse);
            }
            else{
              hatchState = Manipulator.HatchState.PUSH_DOWN;
              Manipulator.hatchState.set(Value.kForward);
            }
          }
          else if(hatchState == Manipulator.HatchState.PUSH_DOWN){
            Manipulator.hatchState.set(Value.kForward);
          }
          if (grasperState == Manipulator.GrasperState.FOLD_IN){
            if (hatchState == Manipulator.HatchState.PUSH_DOWN){
                Manipulator.grasper.set(Value.kReverse);
            }
            else {
              grasperState = Manipulator.GrasperState.FOLD_OUT;
              Manipulator.grasper.set(Value.kForward);
            }
          }
          else if (grasperState == Manipulator.GrasperState.FOLD_OUT){
            Manipulator.grasper.set(Value.kForward);
          }
      return true;
}

}