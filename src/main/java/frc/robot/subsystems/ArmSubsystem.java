/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  CANSparkMax ArMotor;
  Encoder testEncoder;
  DigitalInput Switch;
  
  public ArmSubsystem(){
    ArMotor = new CANSparkMax(5, MotorType.kBrushed);
    testEncoder = new Encoder(0, 1);
    Switch = new DigitalInput(2); 
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  ArMotor.setIdleMode(IdleMode.kBrake);
  
  testEncoder.reset();
  }

  public void BrasExtended(){
    SmartDashboard.putNumber("encTest", testEncoder.getDistance());
    SmartDashboard.putBoolean("Extended", true);
    ArMotor.set(0.3);
        
    if(testEncoder.getDistance() < -400){
    ArMotor.set(0);
    }
  }
  public void BrasDefault(){  
    SmartDashboard.putNumber("encTest", testEncoder.getDistance());
    SmartDashboard.putBoolean("Extended", false);
    ArMotor.set(-0.25);
    
    if(testEncoder.getDistance() > 10){
      ArMotor.set(0);
    }

    if(Switch.get()){
      SmartDashboard.putBoolean("switch", true);
      

    }
    if(!Switch.get()){
      SmartDashboard.putBoolean("switch", false);
      ArMotor.set(0);
      testEncoder.reset();
    }  
  }
}
