/*------------------------------------------------------------------------
Cuivre et Or (c) 2019-2020
---------------============================================---------------
Vous pouvez voler. En plus c pas vraiment copyrighté ;)
--------------------------------------------------------------------------*/

//boo

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.AutoCommand;
import frc.robot.commands.BougerCommand;
import frc.robot.commands.CameraLightOff;
import frc.robot.commands.CameraLightOn;
import frc.robot.commands.GimbalCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.CameraSubsystem;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.GimbalSubsystem;
import frc.robot.subsystems.SensorSubsystem;
import frc.robot.subsystems.StabilisationSubsystem;

public class Robot extends TimedRobot {
  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static DriveTrainSubsystem m_drive = new DriveTrainSubsystem();
  public static GimbalSubsystem Gimbal = new GimbalSubsystem();
  public static StabilisationSubsystem Stabilisateur = new StabilisationSubsystem();
  public static OI m_oi;
  public static ArmSubsystem Brobot = new ArmSubsystem();
  public static SensorSubsystem Sensor = new SensorSubsystem();
  public static CameraSubsystem Camera = new CameraSubsystem();


  SendableChooser<Integer> m_chooser = new SendableChooser<>();
   
  Command BougerCommand;
  Command AutoCommand;
  Command GimbalCommand;
  Command CameraLightOn;
  Command CameraLightOff;
  // Fonction qui est executer quand le programme commence
  @Override
  public void robotInit() {
    // Constructeurs
    m_oi = new OI();
    BougerCommand = new BougerCommand();
    AutoCommand = new AutoCommand();
    GimbalCommand = new GimbalCommand();
    CameraLightOn = new CameraLightOn();
    CameraLightOff = new CameraLightOff();
    // Options
    m_chooser.addOption("Left", 1);
    m_chooser.addOption("Right", 2);
    SmartDashboard.putData("Auto mode", m_chooser);

    Thread a = new Thread(() -> {
      while(!Thread.interrupted())
      {
        // not stuck anymore !!
        m_drive.updateAccX();

      }
    });

    Thread b = new Thread(() -> {
      while(!Thread.interrupted())
      {
        // not stuck anymore !!
        m_drive.updateAccY();

      }
    });

    a.start();
    b.start();
  }

  // Fonction qui est executee regulierement lors du programme
  // pendant toute les phases  
  @Override
  public void robotPeriodic() {
  
  }

  // La fonction qui est executer quand tu est en train de rager
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  // Fonction qui est executer une fois lorsque le mode autonome commence  
  @Override
  public void autonomousInit() {
    //GimbalCommand.start();
    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
  }

  // Fonction appeller regulierement lors du mode autonome
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  AutoCommand.start();
  }

  // Fonction executer lors du debut du mode teleoperationelle
  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    
    
    
    AutoCommand.cancel();
  }

  // Function appeller periodiquement pendant la 
  // periode de teleoperation
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //GimbalCommand.start();  
    BougerCommand.start();
  
    if(m_oi.lightToggle()==true){
        CameraLightOn.start();
    }
    if(m_oi.lightToggle()==false){
        CameraLightOff.start();
    }
  
  }

  // Fonction bizzarre, personne ce que ca fais
  @Override
  public void testPeriodic() {}
}
