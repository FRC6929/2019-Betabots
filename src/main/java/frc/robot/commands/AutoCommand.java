/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
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
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  forward = 0;
  side = 0;
  rotate = 0;
  
  // Je sais pas pourquoi on reset 
  // le timer, mais les commentaires
  // c'est bien! - Maxence 2019
  //etapeTimer.reset();
  etape = 1;  
  
  target = 0;
  speed = 0;
  
  //SmartDashboard.putBoolean("auto", true);
  
  /*auto_chooser.addOption("Left", 1);
  auto_chooser.addOption("Right", 2);
  autoSide = auto_chooser.getSelected();
  SmartDashboard.putNumber("Side", autoSide);*/
}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
    //On décide si la position que l'on veut est en angle ou en emplacement

    if (etape == 1 || etape == 2 || etape == 4 || etape == 5){
      position = Robot.m_drive.getEncoder();
    }
    if (etape == 3){
      position = Robot.m_drive.getAngle();  
    }
  
  
    //On indique la distance souhaitée

    if (etape == 1){
      target = 10;
      }
    if (etape == 2){
      target = 15;
      } 
    if (etape == 3){
      target = 45;
    }
      


      //On calcule la vitesse à laquelle le robot devrait rouler

    if(position < target){
      if(position < target/2){
        speed = 1*position+0.1;
      }

      if(position > target/2 && position < target - 0.02){
        speed = -1*target - position;
      }

      if(position > target - 0.2){
        speed = 0;
      }     
    }
    
    //On indique dans quel sens le robot devrait rouler
      if (etape == 1 || etape == 4){
        forward = speed;
      }
      if (etape == 2 || etape == 5){
        side = speed;
      }
      if (etape == 3){
        rotate = speed;
      }
      Robot.m_drive.autoBouger(forward, side, rotate);
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
