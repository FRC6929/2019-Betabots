/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
/*package frc.robot.subsystems;
 
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
 
/**
 * <h3>Gimbal subsystem</h3>
 * Takes input from the robot AHRS and arm position
 * sensors and counterracts the particle inertia
 * when the robot moves.
 * <p>
 * Requires an rX and a rY servo.
 * Requires the position of the mechanism on
 * the robot in m (center = 0, front -> +)
 * <p>
 * Requires tX and tY acceleration in m/s^2, as well
 * as rZ rotation in deg/s^2.
 *
 * @author  Sami Ghoul-Duclos
 * @version 0.1
 * @since   04/10/2019
 */
/*public class GimbalSubsystem extends Subsystem {
 
  Servo servoX;
  Servo servoY;
 
  //Calculations variables
  double xForce;
  double yForce;
  double weight;
 
  //XZ and YZ force vectors angles
  double xzVectorAngle;
  double yzVectorAngle;
 
  final double gConst = 9.80665;    //In m/s^2
  final double particleMass = 0.5;  //In kg
  final int servoRange = 180;       //In deg
 
  @Override
  public void initDefaultCommand() {
    servoX = new Servo(0);
    servoY = new Servo(1);
  }
 
  public void stabilise(double xAcc, double yAcc, double zRAcc, double armPos) {
   
    //Calculate X and Y translation force
    xForce = particleMass * (xAcc + (2*Math.PI*armPos * (zRAcc / 360)));
    yForce = particleMass * yAcc;
 
    //Find the vector angle
    weight = -particleMass * gConst;
    xzVectorAngle = (Math.atan2(weight, xForce)) * (360 / (2*Math.PI));
    yzVectorAngle = (Math.atan2(weight, yForce)) * (360 / (2*Math.PI));
 
    //Set the servo angle accordingly
    servoX.set((xzVectorAngle - 180)/180);
    servoY.set((yzVectorAngle - 180)/180);
  }
}*/