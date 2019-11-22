package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// S'occupe du controle de tout les sensors(ultrasoniques)
// et retourne leur valeurs
public class SensorSubsystem extends Subsystem {
    // ultra Sensor;
    Spark Sensor;
    
    AnalogInput sensorArriereInput;
    AnalogInput sensorGaucheInput;
    AnalogInput sensorDroiteInput;


    public SensorSubsystem(){
      
        Sensor = new Spark(9);
      
        sensorArriereInput = new AnalogInput(0);
        sensorGaucheInput = new AnalogInput(1);
        sensorDroiteInput = new AnalogInput(2);
    }

    @Override
    public void initDefaultCommand(){
        // Rien comme d'habitude
    }

    public void updateSensor(){
        
        
    }

    public double getLeftValue()
    {
        Sensor.set(0.5);
        SmartDashboard.putNumber("sensorLeft", sensorGaucheInput.getAverageVoltage() * 147);
        return sensorGaucheInput.getAverageVoltage() * 147;
    }
    public double getRightValue()
    {
        Sensor.set(0.5);
        SmartDashboard.putNumber("sensorDroit", sensorDroiteInput.getAverageVoltage() * 147);
        return sensorDroiteInput.getAverageVoltage() * 147;
    }
    public double getBehindValue(){
        Sensor.set(0.5);
        SmartDashboard.putNumber("sensorArriere", sensorArriereInput.getAverageVoltage() * 147);
        return sensorArriereInput.getAverageVoltage() * 147;
    }
}
