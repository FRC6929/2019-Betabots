package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DriveTrainSubsystem extends Subsystem {
	CANSparkMax m_frontLeft;	
	CANSparkMax m_rearLeft;
	CANSparkMax m_frontRight;
    CANSparkMax m_rearRight;
    MecanumDrive m_mecanum;
    CANEncoder e_frontLeft;
    CANEncoder e_rearLeft;
    CANEncoder e_frontRight;
    CANEncoder e_rearRight;
    AHRS ahrs;
    double X_Acc;
    double Y_Acc;
    double defaultAngle;

public DriveTrainSubsystem(){
        m_frontLeft = new CANSparkMax(1,MotorType.kBrushless);
        m_rearLeft = new CANSparkMax(2,MotorType.kBrushless);        
        m_frontRight = new CANSparkMax(3, MotorType.kBrushless);
        m_rearRight = new CANSparkMax(4, MotorType.kBrushless);
        m_frontLeft.setIdleMode(IdleMode.kBrake);
        m_rearLeft.setIdleMode(IdleMode.kBrake);
        m_frontRight.setIdleMode(IdleMode.kBrake);
        m_rearRight.setIdleMode(IdleMode.kBrake);
        
        e_frontLeft = new CANEncoder(m_frontLeft);
        e_rearLeft = new CANEncoder(m_rearLeft);
        e_frontRight = new CANEncoder(m_frontRight);
        e_rearRight = new CANEncoder(m_rearRight);
        e_frontLeft.setPosition(0);
        e_rearLeft.setPosition(0);
        e_frontRight.setPosition(0);
        e_rearRight.setPosition(0);

        
        
        m_mecanum = new MecanumDrive(m_frontLeft, m_rearLeft, m_frontRight, m_rearRight);
        ahrs = new AHRS();

        ahrs.reset(); 
        
       
       
        X_Acc = 0;
        Y_Acc = 0;
    }

    @Override
    public void initDefaultCommand() {
        
        
    }
    
    
    
    public void reset(){
        e_frontLeft.setPosition(0);
        e_frontRight.setPosition(0);
        e_rearLeft.setPosition(0);
        e_rearRight.setPosition(0);
    }
    public void bouger(double y,double x,double z){
        m_mecanum.driveCartesian(-y*0.6*Robot.m_oi.Vitesse(), -x*0.6*Robot.m_oi.Vitesse(), -z*0.4);
        SmartDashboard.putNumber("Angle", ahrs.getAngle());
    }
    public void bougerField(double y,double x,double z){
        m_mecanum.driveCartesian(-y*0.6*Robot.m_oi.Vitesse(), -x*0.6*Robot.m_oi.Vitesse(), -z*0.4, ahrs.getAngle());
        SmartDashboard.putNumber("yeet",Math.round(ahrs.getVelocityX() * 100.0) / 100.0);
    }
    public void bougerAuto(double y,double x,double z){
        m_mecanum.driveCartesian(y*0.5, x*0.5, z*0.4);
        SmartDashboard.putNumber("Angle", ahrs.getAngle());
    
    }
    
    
    public double getEncoder(){
        SmartDashboard.putNumber("ENCODER", e_frontLeft.getPosition());
        return e_frontLeft.getPosition();
        
    }
    
    public double getVelocityX(){
        SmartDashboard.putNumber("XVel", ahrs.getVelocityX());
        return ahrs.getVelocityX()/2100;
    }

    public double getVelocityY(){
        SmartDashboard.putNumber("YVel", ahrs.getVelocityY());
        return ahrs.getVelocityY();
    }

    public double getAngle(){
        return ahrs.getAngle();
    }

    public void resetAngle(){
        ahrs.reset();
    }
    
    public double XPosition(){
        SmartDashboard.putNumber("Xposition", e_rearLeft.getPosition() - e_rearRight.getPosition() / 2);
        return e_rearLeft.getPosition() - e_rearRight.getPosition() / 2;
    }

    public double XSpeed(){
        
        SmartDashboard.putNumber("XSpeed", e_rearRight.getVelocity() - e_rearLeft.getVelocity());
        SmartDashboard.putNumber("SPFL", e_frontLeft.getVelocity());
        SmartDashboard.putNumber("SPFR", e_frontRight.getVelocity());
        SmartDashboard.putNumber("SPRL", e_rearLeft.getVelocity());
        SmartDashboard.putNumber("SPRR", e_rearRight.getVelocity());
        return (e_rearRight.getVelocity() - e_rearLeft.getVelocity())/4300;
    }
    public double YSpeed(){
        SmartDashboard.putNumber("YSpeed", e_frontLeft.getVelocity() - e_rearLeft.getVelocity());
        return (e_frontLeft.getVelocity() - e_rearLeft.getVelocity())/4300;
    }
    //int avg_rate = 50;

    //double[] average_g_x = new double[avg_rate];
    //double[] average_g_y = new double[avg_rate];
//
    //int i_x = 0; // Position dans l'array sur X
    //int i_y = 0; // Position dans l'array sur Y
//
    //public static double acc_x;
    //public static double acc_y;
//
    //static double fetch_x;
    //static double fetch_y;
//
    //public double getAccX()
    //{
    //    //SmartDashboard.putNumberArray("x", average_g_x);
    //    // System.out.println(acc_x);
    //    return acc_x;
    //}
//
    //public double getAccY()
    //{
    //    // SmartDashboard.putNumberArray("y", average_g_y);
    //    return acc_y;
    //}
//
    //public void updateAccX()
    //{
    //    double raw = ahrs.getRawAccelX();
//
    //    if(i_x == avg_rate - 1) {
    //        i_x = 0;
    //    }
    //    else{
    //        i_x++;
    //    }
//
    //    // System.out.println("Iterateur X:" + i_x);
//
    //    average_g_x[i_x] = raw;
//
    //    if(fetch_x >= 25)
    //    {
    //        double res = 0.0;
    //        fetch_x = 0;
    //        for(int i = 0; i < avg_rate; i++)
    //        {
    //            res += average_g_x[i];
    //        }
    //
    //        res = res / (avg_rate - 1);
    //        res = Math.round(res * 10.0) / 10.0;
    //        acc_x = res;
    //       // System.out.println("x:" + acc_x);
    //    }
    //    fetch_x ++;
    //}
//
    //public void updateAccY()
    //{
    //    double raw = ahrs.getRawAccelY();
//
    //    if(i_y == avg_rate - 1){
    //        i_y = 0;
    //    }
    //    else {
    //        i_y++;
    //    }
//
    //    average_g_y[i_y] = raw;
//
    //    if(fetch_y >= 25)
    //    {
    //        double y = 0.0;
    //        fetch_y = 0;
    //    for(int i = 0; i < avg_rate; i++)
    //    {
    //        y += average_g_y[i];
    //    }
//
    //    y = y / (avg_rate - 1);
    //    y = Math.round(y * 10.0) / 10.0;
    //    acc_y = y;
    //    //System.out.println("y:" + acc_y);
    //    }
    //    fetch_y++;
    //}
}