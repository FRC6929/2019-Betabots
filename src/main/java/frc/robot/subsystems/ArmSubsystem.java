/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  //CANSparkMax ArMotor;
  Encoder testEncoder;
  DigitalInput Switch;
  VictorSPX ArMotor;
  public ArmSubsystem(){
    //ArMotor = new CANSparkMax(5, MotorType.kBrushed);
    testEncoder = new Encoder(0, 1);
    Switch = new DigitalInput(2); 
    ArMotor = new VictorSPX(0);
    ArMotor.setInverted(true);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  //ArMotor.setIdleMode(IdleMode.kBrake);
  
  testEncoder.reset();
  }

  public void BrasExtended1(){
    SmartDashboard.putNumber("encTest", testEncoder.getDistance());
    SmartDashboard.putBoolean("Extended", true);
    
    if(testEncoder.getDistance() < -400){
    ArMotor.set(ControlMode.PercentOutput, 0);
    }
    else{
      ArMotor.set(ControlMode.PercentOutput, 0.3);
    }
  }
  public void BrasExtended2(){
    SmartDashboard.putNumber("encTest", testEncoder.getDistance());
    SmartDashboard.putBoolean("Extended", true);
    
        
    if(testEncoder.getDistance() < -500){
      ArMotor.set(ControlMode.PercentOutput, 0);
    }
    else{
      ArMotor.set(ControlMode.PercentOutput, 0.3);
    }
  }
  public void BrasDefault(){  
    SmartDashboard.putNumber("encTest", testEncoder.getDistance());
    SmartDashboard.putBoolean("Extended", false);
    
    
    if(testEncoder.getDistance() > 10){
      ArMotor.set(ControlMode.PercentOutput, 0);
    }
    
    if(!Switch.get()){
      SmartDashboard.putBoolean("switch", false);
      ArMotor.set(ControlMode.PercentOutput, 0);
      testEncoder.reset();
    }  
    if(Switch.get()){
      SmartDashboard.putBoolean("switch", true);
      
      if(testEncoder.getDistance() < -100){
        ArMotor.set(ControlMode.PercentOutput, -0.6
        );
      }
      if(testEncoder.getDistance() > -100 && testEncoder.getDistance() < 10){
        ArMotor.set(ControlMode.PercentOutput, -0.3);
      }
    }
  }
  public double getArmEncoder(){
      return testEncoder.getDistance();
  }
}
