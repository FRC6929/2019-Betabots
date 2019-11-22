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
  
  
  double vitesseXPrecedente;
  double vitesseYPrecedente;
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
    
    
    
  double vitesseX = Robot.m_drive.XSpeed();
  double vitesseY = Robot.m_drive.YSpeed();
  
    
  SmartDashboard.putNumber("vitesseXPrécédente", vitesseXPrecedente);
  double accelerationX = vitesseX - vitesseXPrecedente;
  vitesseXPrecedente = vitesseX;
  

  SmartDashboard.putNumber("vitesseYPrecedente", vitesseYPrecedente);
  double accelerationY = vitesseY - vitesseYPrecedente;
  vitesseYPrecedente = vitesseY;
    
    
    
    
    
    
    
    
    //On décide si la position que l'on veut est en angle ou en emplacement

    if (etape == 1 || etape == 3 || etape == 6){
      rawposition = Robot.m_drive.getEncoder();
    }
    if (etape == 2 || etape == 5 || etape == 7){
      rawposition = Robot.m_drive.getAngle();  
    }
  
    // On veut la valeur absolue de position
    
    position = Math.abs(rawposition);


    //On indique la distance souhaitée




// etape 1


    if (etape == 1){
      target = 37;


      if(position < 2){
        speed = 0.2;
      }
      
      else if(position >= 2 && position < target/2){
        speed = position / 8;
      }

      else if(position >= target/2 && position < target - 2){
        speed = ((target - position) / (position / 4));
      }

      else if(position >= target - 2 && position < target){
        speed = 0.2;
      }
      
      else if(position >= target){
        speed = 0;
        /*position = 0;
        target = 0;*/
        Robot.m_drive.reset();
        Robot.m_drive.resetAngle();
        etape ++;
      }     
      
      Robot.m_drive.bougerAuto(speed,0,0);


      }
    
    
    
    if (etape == 2){
      target = 90;
      
    
    
    
    
    } 


    if (etape == 3){
      target = 165;
    }
    
    if (etape == 6){
      target = 30;
    }
    
    
    
    if (etape == 5){
      target = 45;
    }
    if (etape == 7){
      target = 45;
    }  

    SmartDashboard.putNumber("pos", position);
    SmartDashboard.putNumber("target", target);
      //On calcule la vitesse à laquelle le robot devrait rouler

      if(etape == 1 || etape == 3 || etape == 5){
      if(position < 2){
        speed = 0.2;
      }
      
      else if(position >= 2 && position < target/2){
        speed = position / 8;
      }

      else if(position >= target/2 && position < target - 2){
        speed = ((target - position) / (position / 4));
      }

      else if(position >= target - 2 && position < target){
        speed = 0.2;
      }
      
      else if(position >= target){
        speed = 0;
        /*position = 0;
        target = 0;*/
        Robot.m_drive.reset();
        Robot.m_drive.resetAngle();
        etape ++;
      }     
    }
      
    
    if(etape == 2 || etape == 5 || etape == 7){
      if(position > target){
        speed = 0;
      }
      if(position < target){
        speed = 1; //etape++;
      }
    }
    
    
    
    
    if(speed > 1){
        speed = 1;
      }
      if(speed < 0.2){
        speed = 0.2;
      }
    SmartDashboard.putNumber("speed", speed);
    
    //On indique dans quel sens le robot devrait rouler
      if (etape == 1 || etape == 6){
        forward = speed;
        side = 0;
        rotate = 0;
        
      }
      if (etape == 3){
        forward = speed;
        side = /*(Robot.Sensor.getRightValue() - Robot.Sensor.getLeftValue())/200*/0;
        rotate = 0;
      }

      if(Robot.Chooser.getChooser() == "Left")
      if (etape == 2 || etape == 7){
        rotate = speed;
        forward = 0;
        side = 0;
      }
      
      if(etape == 5){
        rotate = -speed;
        forward = 0;
        side = 0;//Robot.Sensor.getRightValue()-Robot.Sensor.getLeftValue();
      }
      
      if(Robot.Chooser.getChooser() == "Right")
      if (etape == 2 || etape == 7){
        rotate = -speed;
        forward = 0;
        side = 0;
      }
      
      if(etape == 5){
        rotate = speed;
        forward = 0;
        side = 0;
      }
      
      if(etape != 4 && etape != 8){
      Robot.m_drive.bougerAuto(side, forward, rotate);
      //Robot.Stabilisateur.stabilise();
      }
      
      
      
      
      if(etape == 4 || etape == 8){
      double targetCount = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
      double targetX = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
      SmartDashboard.putNumber("xToTarget", targetX);

      if(targetCount == 1)
      {
        

        NetworkTable t = NetworkTableInstance.getDefault().getTable("limelight");
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

        if(etape == 4){
          /*if(ta < 25)
        {
          forward = 0.35;
        }
        if(ta > 25){
          forward = 0;
          etape++;
        }*/
        etape++;
        }
        
        if(etape == 8){
          if(ta < 50)
        {
          forward = 0.35/*.25*/;
        }
        if(ta > 50){
          forward = 0;
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


        
        rotate = targetX/54;
        
        Robot.m_drive.bougerAuto(side,forward,rotate);
      }
    }
    
  if(etape == 9){
    Robot.Stabilisateur.depose();
  }
  if(etape != 9){
      Robot.Stabilisateur.stabiliseX(-accelerationX * 20 + 0.55);
    }
    Robot.Stabilisateur.stabiliseY(accelerationY * 8 + 0.4);
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