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
    AnalogInput sensorInput;


    public SensorSubsystem(){
      Sensor = new Spark(9);
        sensorInput = new AnalogInput(0);
    }

    @Override
    public void initDefaultCommand(){
        // Rien comme d'habitude
    }

    public void updateSensor(){
        Sensor.set(0.5);
    }

    public double getValue()
    {
        return sensorInput.getValue() / 3.6666;
    }
}
