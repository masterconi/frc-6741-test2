package frc.robot;
//gg
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.ADXL345_SPI.Axes;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends TimedRobot {


  private VictorSP leftMotor = new VictorSP(0 );
  private VictorSP rightMotor = new VictorSP(1);
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
      leftMotor.set(0.6);
      rightMotor.set(-0.6);
    } else {
      leftMotor.set(0);
      rightMotor.set(0);

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

    leftMotor.set(left);
    rightMotor.set(-right);
  }




  @Override
  public void testInit() {
  }

  @Override
  public void testPeriodic() {
    CameraServer.startAutomaticCapture();
  }

}