/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;




import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class AutoCommand extends Command {
  
  double forward;
  double side;
  double rotate;
  
  
  int etape;
  
  double target;
  double position;
  
  double angle;
  double speed;
  
  //SendableChooser<Integer> auto_chooser = new SendableChooser<>();
  //int autoSide;
  
  public AutoCommand() {
    requires(Robot.m_drive);
    requires(Robot.Gimbal);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  
  forward = 0;
  side = 0;
  rotate = 0;
  
  etape = 1;  
  
  target = 0;
  speed = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  
  
}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    // On veut la valeur absolue de position
    
    if(position < 0){
      position = -position;
    }
    
    //On décide si la position que l'on veut est en angle ou en emplacement

    if (etape == 1 || etape == 2 || etape == 4 || etape == 5){
      position = Robot.m_drive.getEncoder();
    }
    if (etape == 3){
      position = Robot.m_drive.getAngle();  
    }
  
  
    //On indique la distance souhaitée

    if (etape == 1){
      target = 37;
      }
    if (etape == 2){
      target = 15;
      } 
    if (etape == 3){
      target = 45;
    }
      

    SmartDashboard.putNumber("pos", position);
    SmartDashboard.putNumber("target", target);
      //On calcule la vitesse à laquelle le robot devrait rouler

      if(position < 0.8){
        speed = 0.2;
      }
      
      if(position < target/2 && position > 0.8){
        speed = position / 8;
      }

      else if(position >= target/2 && position < target){
        speed = ((target - position) / (position / 4));
      }

      else if(position >= target){
        speed = 0;
        
      }     
      if(speed > 1){
        speed = 1;
      }
    
    
    //On indique dans quel sens le robot devrait rouler
      if (etape == 1 || etape == 4){
        forward = speed;
        side = 0;
        rotate = 0;
        SmartDashboard.putNumber("speed", speed);
      }
      if (etape == 2 || etape == 5){
        side = speed;
        forward = 0;
        rotate = 0;
      }
      if (etape == 3){
        rotate = speed;
        forward = 0;
        side = 0;
      }
      
      
      Robot.m_drive.bouger(side, forward, rotate);
      Robot.Gimbal.stabiliser(Robot.m_drive.getAccX(), Robot.m_drive.getAccY(), 0, 0);
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
