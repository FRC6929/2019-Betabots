/*
Cuivre et Or - 2019
*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;


public class AutoCommand extends Command {
  public int etape;

  public int target;
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
    
    

    target = 5;
    targetRot = 0;
    
    speed = 0;
    speedRot = 0;
    goodAngle = false;

    
  }

  @Override
  protected void execute()
  {
etape = 1;




    position = Robot.m_drive.XPosition();
    positionRot = Robot.m_drive.getAngle();
    // Check et mets a jour l'étape

    if(etape == 1 || etape == 3 || etape == 5){
      if(position >= target){
        speed = 0;
        speedRot = 0.3;
        Robot.m_drive.reset();
        Robot.m_drive.resetAngle();
        etape ++;
      }
    }else{
      if(goodAngle == true){
        speed = 0;
        speedRot = 0;
        Robot.m_drive.reset();
        Robot.m_drive.resetAngle();
        etape ++;
    }
    
    }
    
    // Fait quelquechose selon l'étape
    switch(etape)
    {
      // Etape initiale
      // Avance
      case 1:
      target = 500;
      targetRot = 0;
      
      break;

      // Tourne de 90*
      case 2:
      target = 0;
      targetRot = 84;
      break;

      // Avance longtemps
      case 3:
      target = 200;
      targetRot = 0;
      break;

      // Si tout les autres fonctionne pas
      case 4:
      targetRot = -45;
      break;
    }

    // Mets a jour la position

    // Check si on atteint la target & s'occupe de la courbe d'acceleration
    if(position < 100){
        speed = 1;
    }    
    else if(position >= 6 && position < target/2){
      speed = position / 100;
    }
    else if(position >= target/2 && position < target - 6){
      speed = ((target - position) / 100);
    }
    else if(position >= target - 90 && position < target){
      speed = 0.8;
    }
    
    /*if(speed > 1.5){
      speed = 1.5;
    }*/

    //Rotation
    speedRot = (targetRot - positionRot)/ 360;
    
    if(speedRot < 0.2 && goodAngle == false){
      speedRot = 0.3;
    }
    if(positionRot >= targetRot - 1 && positionRot <= targetRot + 1){
      goodAngle = true;
    } 
    else{
      goodAngle = false;
    }
    
    SmartDashboard.putNumber("etape", etape);
    SmartDashboard.putNumber("positionRot", positionRot);
    SmartDashboard.putNumber("position", position);
    SmartDashboard.putNumber("speed", speed);
    SmartDashboard.putBoolean("goodAngle", goodAngle);
    SmartDashboard.putNumber("Target", target);
    if(etape == 2 || etape == 4){
      speed = 0;
      //speedRot = 0.5;
    }else{

      if(position > 0 && position < 75){
        speedRot = speed/10.7;
      }
    
      if(position > 75.3 && position < 207 || position > 320 && position < 400){
        speedRot = speed/150;
      }
    
      if(position > 207 && position < 320){
        speedRot = speed/-20;
      }

      if(position > 400 && position < 480){
        speedRot = speed/-40;
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