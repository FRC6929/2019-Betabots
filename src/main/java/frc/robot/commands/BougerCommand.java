/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BougerCommand extends Command {
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
  if(Robot.m_oi.fieldSwitch()==false){
  Robot.m_drive.bouger(Robot.m_oi.getAxisY(), Robot.m_oi.getAxisX(), Robot.m_oi.getAxisZ());
    Robot.Gimbal.stabiliser(Robot.m_drive.getAccX(), Robot.m_drive.getAccY(), 0, 0);
  }
  if(Robot.m_oi.fieldSwitch()==true){
  Robot.m_drive.bougerField(Robot.m_oi.getAxisY(), Robot.m_oi.getAxisX(), Robot.m_oi.getAxisZ());
    //Robot.Gimbal.stabiliser(Robot.m_drive.getAccX(), Robot.m_drive.getAccY(), 0, 0);
  Robot.Stabilisateur.stabilise();
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
