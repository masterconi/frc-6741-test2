

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {


  private VictorSP leftMotor1 = new VictorSP(0 );
  private VictorSP leftMotor2 = new VictorSP(1);
  private VictorSP rightMotor1 = new VictorSP(2);
  private VictorSP rightMotor2 = new VictorSP(3);
  Encoder encoderL = new Encoder(0, 1);
  Encoder encoderR = new Encoder(2, 3 );
  
  private Joystick joy1 = new Joystick(0);


  private double startTime;



  @Override
  public void robotInit() {
    encoderL.setDistancePerPulse(1./256.);
    encoderR.setDistancePerPulse(1./256.);
  }


  @Override
  public void autonomousInit() {
    startTime = Timer.getFPGATimestamp();
  }

  @Override
  public void autonomousPeriodic() {
    double time = Timer.getFPGATimestamp();
    System.out.println(time - startTime);
    encoderR.getDistance();
    encoderL.getDistance();

    if (time - startTime < 3) {
      leftMotor1.set(0.6);
      leftMotor2.set(0.6);
      rightMotor1.set(-0.6);
      rightMotor2.set(-0.6);
    } else {
      leftMotor1.set(0);
      leftMotor2.set(0);
      rightMotor1.set(0);
      rightMotor2.set(0);

      SmartDashboard.putNumber("Left Drive Encoder", encoderL.getDistance());
      SmartDashboard.putNumber("Right Drive Encoder", encoderR.getDistance());
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    double speed = -joy1.getRawAxis(1) * 0.6;
    double turn = joy1.getRawAxis(4) * 0.3;

    double left = speed + turn;
    double right = speed - turn;

    leftMotor1.set(left);
    leftMotor2.set(left);
    rightMotor1.set(-right);
    rightMotor2.set(-right);
  }




  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    CameraServer.startAutomaticCapture();
  }

}