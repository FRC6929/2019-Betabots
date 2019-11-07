/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
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
  double rawposition;
  
  double speed;
  
  //SendableChooser<Integer> auto_chooser = new SendableChooser<>();
  //int autoSide;
  
  public AutoCommand() {
    requires(Robot.m_drive);
    requires(Robot.Stabilisateur);
    requires(Robot.Chooser);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  
  
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  forward = 0;
  side = 0;
  rotate = 0;
  
  etape = 1;
  
  target = 5;
  speed = 0;
  
  Robot.m_drive.reset();
}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putNumber("etape", etape);
    
    
    //On décide si la position que l'on veut est en angle ou en emplacement

    if (etape == 1 || etape == 5){
      rawposition = Robot.m_drive.getEncoder();
    }
    if (etape == 2 || etape == 4 || etape == 6){
      rawposition = Robot.m_drive.getAngle();  
    }
  
    // On veut la valeur absolue de position
    
    position = Math.abs(rawposition);


    //On indique la distance souhaitée

    if (etape == 1){
      target = 37;
      }
    if (etape == 5){
      target = 30;
    }
    
    
    if (etape == 2){
      target = 90;
      } 
    if (etape == 4){
      target = 45;
    }
    if (etape == 6){
      target = 45;
    }  

    SmartDashboard.putNumber("pos", position);
    SmartDashboard.putNumber("target", target);
      //On calcule la vitesse à laquelle le robot devrait rouler

      if(position < 0.8){
        speed = 0.2;
      }
      
      else if(position < target/2){
        speed = position / 8;
      }

      else if(position < target - 0.8){
        speed = ((target - position) / (position / 4));
      }

      else if(position < target){
        speed = 0.2;
      }
      
      else if(position >= target){
        speed = 0;
        position = 0;
        target = 0;
        Robot.m_drive.reset();
        Robot.m_drive.resetAngle();
        etape ++;
      }     
      if(speed > 1){
        speed = 1;
      }
    
    
    //On indique dans quel sens le robot devrait rouler
      if (etape == 1 || etape == 5){
        forward = speed;
        side = 0;
        rotate = 0;
        SmartDashboard.putNumber("speed", speed);
      }
      

      if(Robot.Chooser.getChooser() == "Left")
      if (etape == 2 || etape == 6){
        rotate = speed;
        forward = 0;
        side = 0;
      }
      
      if(etape == 4){
        rotate = -speed;
        forward = 0;
        side = 0;
      }
      
      if(Robot.Chooser.getChooser() == "Right")
      if (etape == 2 || etape == 6){
        rotate = -speed;
        forward = 0;
        side = 0;
      }
      
      if(etape == 4){
        rotate = speed;
        forward = 0;
        side = 0;
      }
      
      if(etape != 3 && etape != 7){
      Robot.m_drive.bouger(side, forward, rotate);
      Robot.Stabilisateur.stabilise();
      }
      
      
      
      
      if(etape == 3 || etape == 7){
      double targetCount = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      double targetX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      SmartDashboard.putNumber("xToTarget", targetX);

      if(targetCount == 1)
      {
        double x = 0;
        double y = 0; 
        double z = 0;

        NetworkTable t = NetworkTableInstance.getDefault().getTable("limelight");
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        if(etape == 3){
          if(ta < 25)
        {
          x = 0.35/*.25*/;
        }
        if(ta > 25){
          x = 0;
          etape++;
        }
        }
        
        if(etape == 7){
          if(ta < 50)
        {
          x = 0.35/*.25*/;
        }
        if(ta > 50){
          x = 0;
          etape++;
        }
        }
        
        
        /*if(targetX > 0.1){
          z = 0.25;
        }
        if(targetX < -0.1)
        {
          z = -0.25;
        }*/
        
        z = targetX/54;
        
        Robot.m_drive.bouger(y,x,z);
      }
    }
    
  if(etape == 8){
    Robot.Stabilisateur.depose();
  }
  
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