package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;



public class DriveTrainSubsystem extends Subsystem {
	CANSparkMax m_frontLeft;	
	CANSparkMax m_rearLeft;
	CANSparkMax m_frontRight;
    CANSparkMax m_rearRight;
    MecanumDrive m_mecanum;
    CANEncoder e_frontLeft;
    AHRS ahrs;
    
    @Override
    public void initDefaultCommand() {
        // uhmmm
        m_frontLeft = new CANSparkMax(1,MotorType.kBrushless);
        m_rearLeft = new CANSparkMax(2,MotorType.kBrushless);        
        m_rearRight = new CANSparkMax(3, MotorType.kBrushless);
        m_frontRight = new CANSparkMax(4, MotorType.kBrushless);
        e_frontLeft = new CANEncoder(m_frontLeft);
        m_mecanum = new MecanumDrive(m_frontLeft, m_rearLeft, m_frontRight, m_rearRight);
        ahrs = new AHRS();
        
    }
    
    public void bouger(double y,double x,double z){
        m_mecanum.driveCartesian(y*0.5, x*0.5, -z*0.5, ahrs.getAngle());
    }
    public void autoBouger(double y, double x, double z){
        m_mecanum.driveCartesian(y*0.5, x*0.5, -z*0.5, ahrs.getAngle());
    }
    public double getEncoder(){
        return e_frontLeft.getPosition();
    }
    public double getAngle(){
        return ahrs.getAngle();
    }
    
}