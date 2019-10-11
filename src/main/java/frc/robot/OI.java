/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.Joystick;


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

  // Constructeur basique du IO
  public OI()
  {
    m_stick = new Joystick(0);
  
  }

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
  public double getAxisX()
  {
    double x = 0;
    x = m_stick.getRawAxis(1);
    x = filterAxis(x, 0.1);
    //if(x <0.1 && x>-0.1){x=0;}
    return x*x*x;
  }

  // Retourne l'axe vertical du Joystick
  public  double getAxisY()
  {
    double y = 0;
    y = m_stick.getRawAxis(0);
    y = filterAxis(y, 0.1);
    //if(y <0.1 && y>-0.1){y=0;}
    return y*y*y;
  }
  public  double getAxisZ()
  {
    double z = 0;
    z = m_stick.getRawAxis(2);
    if(z <0.4 && z>-0.4){z=0;}
    else if(z<-0.4){z=z+0.3;}
    else if(z>0.4){z=z-0.3;}
    return z*z*z;
  }
  
}
