/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class StabilisationSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Servo servoX;
  Servo servoY;
  public StabilisationSubsystem(){
  servoX = new Servo(8);
  servoY = new Servo(7);
  }
  @Override
  public void initDefaultCommand() {
  }
  public void stabiliseDefault(){
    servoX.set(0.4);
    servoY.set(0.5);
  }

  public void stabiliseAvant(){
    servoX.set(0.3);
    servoY.set(0.5);
  }
  public void stabiliseArriere(){
    servoX.set(0.5);
    servoY.set(0.5);
  }
  public void stabiliserGauche(){
    servoX.set(0.4);
    servoY.set(0.4);
  }
  public void stabiliserDroite(){
    servoX.set(0.4);
    servoY.set(0.6);
  }
  public void stabilise(){
    servoX.set(-Robot.m_drive.acc_x/10+0.4);
    servoY.set(Robot.m_drive.acc_y/10+0.5);
/*servoX.set(Robot.m_oi.getAxisX()+0.5);
servoY.set(Robot.m_oi.getAxisY()+0.4);*/

  }
  public void depose(){
    servoX.set(1);
  }

}
