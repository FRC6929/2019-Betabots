/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
  
  
  
  private static Joystick m_stick;
  private static Joystick m_stick2_electric_boogaloo;

  // Constructeur basique du IO
  public OI()
  {
    m_stick = new Joystick(0);
    m_stick2_electric_boogaloo = new Joystick(1); 
  }
public Boolean LightSwitch = true;
  public double filterAxis(double axis,double death)
  {
    if(axis < death && axis > -death)
    {
      return 0;
    }
    else {
      return axis;
    }
  }

  // Retourne l'axe horizontale du Joystick
  public boolean getDepose(){
    if(m_stick2_electric_boogaloo.getRawButton(1) == true){
      return true;
    }
    else{
      return false;
    }
  }
  
  public double getAxisY()
  {
    double x = 0;
    x = m_stick.getRawAxis(1);
    //x = filterAxis(x, 0.1);
    //if(x <0.1 && x>-0.1){x=0;}
    return x*x*x;
  }

  // Retourne l'axe vertical du Joystick
  public  double getAxisX()
  {
    double y = 0;
    y = m_stick.getRawAxis(0);
    //y = filterAxis(y, 0.1);
    //if(y <0.1 && y>-0.1){y=0;}
    return y*y*y/1.4;
  }
  public  double getAxisZ()
  {
    double z = 0;
    z = m_stick.getRawAxis(2);
    if(z <0.4 && z>-0.4){z=0;}
    else if(z<-0.4){z=z+0.3;}
    else if(z>0.4){z=z-0.3;}
    return z*z*z;
    
    /*
    if(m_stick.getRawButton(4)){
      return 1;
    }
    else if(m_stick.getRawButton(3)){
      return -1;
    }
    else{
      return 0;
    }
    */
  }
  public boolean droite(){
    return m_stick.getRawButtonPressed(8);
  }  
  public boolean gauche(){
    return m_stick.getRawButtonPressed(7);
  }
    boolean fieldToggle = true;
  public boolean fieldSwitch(){   
    if (m_stick.getRawButtonPressed(1)){
      fieldToggle=!fieldToggle;
    }
    return false;
  }
  
  boolean lightToggle(){
    
    /*if (m_stick.getRawButton(11)){*/
      LightSwitch = false;
    /*}
    if (m_stick.getRawButton(12)){
      LightSwitch = true;
    }
    SmartDashboard.putBoolean("switch", LightSwitch);*/
    return LightSwitch;
  }
  int BrasPosition = 1;
  public int BrasAngleToggle(){
    if(m_stick2_electric_boogaloo.getRawButton(11)){
      BrasPosition = 1;
    }
    if(m_stick2_electric_boogaloo.getRawButton(9)){
      BrasPosition = 2;
    }
    if(m_stick2_electric_boogaloo.getRawButton(7)){
      BrasPosition = 3;
    }

  return BrasPosition;
  }

  public double Vitesse(){
    return m_stick.getRawAxis(3)/-2+0.5;
  }
}
  