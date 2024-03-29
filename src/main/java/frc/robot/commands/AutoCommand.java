/*
Cuivre et Or - 2019
*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class AutoCommand extends Command {
  public int etape;

  public double target;
  public int targetRot;
  
  public double speed;
  public double speedRot;
  
  public double position;
  public double positionRot; 
  public boolean goodAngle;

  public AutoCommand()
  {
    requires(Robot.m_drive);
  }

  @Override
  protected void initialize()
  {
    if(Robot.Chooser.getChooser() == "Left"){
      target = 153;
    }
    if(Robot.Chooser.getChooser() == "Right"){
      target = 137;
    }
    speed = 0;
    speedRot = 0; 
    
  }

  @Override
  protected void execute()
  {
  position = Robot.m_drive.XPosition();
    
  
    if(position < target - 1){
      speed = 1;
    }
    if(position > target - 2 && position < target + 2 && target > 101){
      speed = 0;
      Robot.Brobot.BrasExtended2();
      if(Robot.Brobot.getSwitch2()){
        Robot.Stabilisateur.depose();
        Timer.delay(0.5);
        //Robot.Brobot.BrasDefault();
        //target = 100;
      }
    }
    if(position > target + 2){
      speed = -1.5;
    }
    if(position > target - 2 && position < target + 2 && target == 100){
      speed = 0;
      //Robot.Brobot.BrasDefault();
    }
    
    SmartDashboard.putNumber("etape", etape);
    SmartDashboard.putNumber("positionRot", positionRot);
    SmartDashboard.putNumber("position", position);
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putBoolean("goodAngle", goodAngle);
    SmartDashboard.putNumber("Target", target);
    
      if(Robot.Chooser.getChooser() == "Right"){  
        if(position >= 0 && position < 40){
            speedRot = speed/10.7;
          }
        
        if(position >= 40 && position < 137){
          speedRot = speed/150;
        }
      }
      if(Robot.Chooser.getChooser() == "Left"){  
        if(position >= 0 && position < 59.5){
            speedRot = speed/-10.7 + speed/75;
          }
        
        if(position >= 58.5&& position < 154){
          speedRot = speed/150;
        }
      }
    
    // Fait bouger le robot
    Robot.m_drive.bougerAuto(0,speed/3,-speedRot); // side, forward,rotation
  
    

    
  }     
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
} 