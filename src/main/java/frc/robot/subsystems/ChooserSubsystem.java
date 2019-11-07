/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/



package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ChooserSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
String m_choosed;
SendableChooser<String> m_chooser;
  public ChooserSubsystem(){
m_chooser = new SendableChooser<>();
  
    //options de chooser

    m_chooser.addOption("Left", "Left");
    m_chooser.addOption("Right", "Right");
    SmartDashboard.putData("Auto Side", m_chooser);

    

}
  

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public String getChooser(){
    return m_chooser.getSelected();
  }

}
