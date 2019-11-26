/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class CameraSubsystem extends Subsystem {  
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public CameraSubsystem(){
    
    
    
    //double yay = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  
    //SmartDashboard.putNumber("hoho", yay);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  public void isTarget(){
    double yay = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
  SmartDashboard.putNumber("hoho", yay);
  }
  public void Allume(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
  }
  public void Ferme(){
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
  }
  public double ta(){
    return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
  }
}
