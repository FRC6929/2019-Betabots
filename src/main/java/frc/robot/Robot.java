/*------------------------------------------------------------------------
Cuivre et Or (c) 2019-2020
---------------============================================---------------
Vous pouvez voler. En plus c pas vraiment copyrighté ;)
--------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


/* Classe ou tout se passe, le robot utilise
des commandes, on peut "demander"(venant de l'ordi pilote) de commencer 
l'execution d'une commande, mais l'execution de la commande
va etre fais du coter du roborio*/
public class Robot extends TimedRobot {
  //public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static DriveTrainSubsystem m_drive = new DriveTrainSubsystem();
  //public static GimbalSubsystem Gimbal = new GimbalSubsystem();
  //public static TestServo Servo = new TestServo();
  public static OI m_oi;
  
  //SendableChooser<Integer> m_chooser = new SendableChooser<>();
   
  Command BougerCommand;
  Command AutoCommand;
  //Command GimbalCommand;
  //Command TestServoC;
  // Fonction qui est executer quand le programme commence
  @Override
  public void robotInit() {
    // Constructeurs
    m_oi = new OI();
    BougerCommand = new BougerCommand();
    AutoCommand = new AutoCommand();
    //GimbalCommand = new GimbalCommand();
    //TestServoC = new TestServoC();
    // Options
    /*m_chooser.addOption("Left", 1);
    m_chooser.addOption("Right", 2);
    SmartDashboard.putData("Auto mode", m_chooser);*/
  
    
    
  }

  // Fonction qui est executer regulierement lors du programme
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
  //  Scheduler.getInstance().run();
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
    //GimbalCommand.start();  
    //TestServoC.start();
    
    //AutoCommand.cancel();
  }

  // Function appeller periodiquement pendant la 
  // periode de teleoperation
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    
    BougerCommand.start();
  }

  // Fonction bizzarre, personne ce que ca fais
  @Override
  public void testPeriodic() {}
}
