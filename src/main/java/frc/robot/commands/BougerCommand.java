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

public class BougerCommand extends Command {
  double vitesseXPrecedente = 0;
  double vitesseYPrecedente = 0;
  public BougerCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  requires(Robot.m_drive);
    requires(Robot.Stabilisateur);
}

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  
  double vitesseX = Robot.m_drive.XSpeed();
  double vitesseY = Robot.m_drive.YSpeed();
  SmartDashboard.putNumber("x", vitesseX);
  SmartDashboard.putNumber("y", vitesseY);
  
    if(Robot.m_oi.fieldSwitch()==false){
  Robot.m_drive.bouger(Robot.m_oi.getAxisY(), Robot.m_oi.getAxisX(), Robot.m_oi.getAxisZ());    
  }
  if(Robot.m_oi.fieldSwitch()==true){
  Robot.m_drive.bougerField(Robot.m_oi.getAxisY(), Robot.m_oi.getAxisX(), Robot.m_oi.getAxisZ());

  }
/*
  if(Robot.m_oi.getAxisX() > x+0.3 || Robot.m_oi.getAxisX() < 0.3 && x > 0.3){
    Robot.Stabilisateur.stabiliseArriere();
  }
  if(Robot.m_oi.getAxisX() < x-0.3 || Robot.m_oi.getAxisX() > 0.3 && x < 0.3){
    Robot.Stabilisateur.stabiliseAvant();
  }
  if(Robot.m_oi.getAxisY() > y+0.3 || Robot.m_oi.getAxisY() < 0.3 && y > 0.3){
    Robot.Stabilisateur.stabiliseGauche();
  }
  if(Robot.m_oi.getAxisX() < y-0.3 || Robot.m_oi.getAxisY() > 0.3 && y < 0.3){
    Robot.Stabilisateur.stabiliseDroite();
  }
  */
  SmartDashboard.putNumber("vitesseXPrécédente", vitesseXPrecedente);
  double accelerationX = vitesseX - vitesseXPrecedente;
  vitesseXPrecedente = vitesseX;
  

  SmartDashboard.putNumber("vitesseYPrecedente", vitesseYPrecedente);
  double accelerationY = vitesseY - vitesseYPrecedente;
  vitesseYPrecedente = vitesseY;

  SmartDashboard.putNumber("AccelX", accelerationX);
  SmartDashboard.putNumber("AccelY", accelerationY);
  
  
  //Robot.Stabilisateur.stabilise();
  //Robot.Stabilisateur.stabiliseDefault();
  Robot.Sensor.getLeftValue();
  Robot.Sensor.getRightValue();
  Robot.Sensor.getBehindValue();
  Robot.Camera.isTarget();

  if(Robot.m_oi.getDepose() == true){
    Robot.Stabilisateur.depose();
  }
  else{
    Robot.Stabilisateur.stabiliseX(-accelerationX * 20 + 0.5);
  }
  Robot.Stabilisateur.stabiliseY(accelerationY * 8 + 0.4);
  
  
  if(Robot.m_oi.BrasAngleToggle() == true){
    Robot.Brobot.BrasDefault();
    SmartDashboard.putBoolean("positionBras", true);
  }
  if(Robot.m_oi.BrasAngleToggle() == false){
    Robot.Brobot.BrasExtended();
    SmartDashboard.putBoolean("positionBras", false);
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
