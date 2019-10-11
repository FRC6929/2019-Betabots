/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class BougerCommand extends Command {
  public BougerCommand(){
    requires(Robot.m_drive);
  }


  @Override
  protected void initialize() {
    System.out.println("Initialisation de la commande bouger");
  }

  @Override
  protected void execute() {
    Robot.m_drive.bouger(Robot.m_oi.getAxisY(),Robot.m_oi.getAxisX(),Robot.m_oi.getAxisZ());
  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
