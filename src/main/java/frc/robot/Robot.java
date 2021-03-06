package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.commands.autonomous.*;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

public class Robot extends TimedRobot {

    private UsbCamera camera;
    private CommandGroupBase autonomous;

    public void robotInit() {
        Subsystems.compressor.start();
        UserInterface.operatorController.A.whenPressed(new IntakeGrab()); // intake arms close: A
        UserInterface.operatorController.B.whenPressed(new IntakeRelease()); // intake arms open: B
        UserInterface.operatorController.X.whenPressed(new GuillotineHold()); // kicker retracts: X
        UserInterface.operatorController.Y.whenPressed(new GuillotineKick()); // kicker kicks out: Y
        UserInterface.operatorController.START.whenPressed(new IntakeBox()); // entire programmed sequence: START
        camera = CameraServer.getInstance().startAutomaticCapture();
        Subsystems.driveBase.setDefaultCommand(new TankDrive());
        Subsystems.arduino.sendCommand("0001111");
        Subsystems.guillotine.zeroLiftPosition();
    }

    // intake arms close: A
    // intake arms open: B
    // kicker retracts: X
    // kicker kicks out: Y
    // entire programmed sequence: START
    // Right trigger: intake wheels
    // Left trigger: outake wheels
    // Left joystick: rotation
    // Right joystick: forwards and backwards
    // Ensure that guillotine is completely down before beginning START
    // Operator RB: Toggle Slow/Fast Mode

    public void disabledInit() {
        Subsystems.arduino.sendCommand("0001111");
    }

    public void autonomousInit() {
        CommandScheduler.getInstance().cancelAll();
        Subsystems.arduino.sendCommand("0005551");
        String gameData = DriverStation.getInstance().getGameSpecificMessage();
        if (UserInterface.launchpad.getMultiSwitchLeft()) {
            autonomous = new LeftAutonomous(gameData, UserInterface.launchpad.getSwitch1());
        } else if (UserInterface.launchpad.getMultiSwitchInactive()) {
            autonomous = new CenterAutonomous(gameData.charAt(0));
        } else if (UserInterface.launchpad.getMultiSwitchRight()) {
            autonomous = new RightAutonomous(gameData, UserInterface.launchpad.getSwitch1());
        }
        autonomous.schedule();
    }

    public void teleopInit() {
        if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            Subsystems.arduino.sendCommand("0003331");
        } else {
            Subsystems.arduino.sendCommand("0002221");
        }
        CommandScheduler.getInstance().cancelAll();
    }

    public void disabledPeriodic() {
        printDataToSmartDashboard();
    }
    
    public void autonomousPeriodic() {
        CommandScheduler.getInstance().run();
        printDataToSmartDashboard();
    }

    public void teleopPeriodic() {
        Subsystems.guillotine.setLiftSpeed(0.0d);
        if (UserInterface.operatorController.getLeftJoystickY() < -.1d) {
            Subsystems.intake.release(); // if controller left joystick goes up, arms release
            Subsystems.guillotine.setLiftSpeed(1.0d);
        } else if (UserInterface.operatorController.getLeftJoystickY() > .1d) {
            Subsystems.guillotine.setLiftSpeed(-0.4d);
            if (Subsystems.guillotine.getLowerSwitchValue()) {
                Subsystems.guillotine.kick(); // when going down with the left joystick, if all the way down, kick cube
            }
        }
        Subsystems.intake.setArmsSpeed(0.0d);
        if (UserInterface.operatorController.getLeftTrigger() > 0.1d) {
            Subsystems.intake.setArmsSpeed(-0.9d);
        } else if (UserInterface.operatorController.getRightTrigger() > 0.1d) {
            Subsystems.intake.setArmsSpeed(0.5d);
        }
        Subsystems.intake.setPivotSpeed(0.0d);
        if (UserInterface.operatorController.getRightJoystickY() < -0.1d) {
            Subsystems.guillotine.kick(); // kicker open if intake pivot goes up
            Subsystems.intake.setPivotSpeed(0.6);
        } else if (UserInterface.operatorController.getRightJoystickY() > 0.1d) {
            Subsystems.intake.setPivotSpeed(-0.2);
        }
        CommandScheduler.getInstance().run();
        printDataToSmartDashboard();
    }

    private void printDataToSmartDashboard() {
        SmartDashboard.putNumber("Ultrasonic", Subsystems.intake.getUltrasonicDistance());
        SmartDashboard.putBoolean("Lift Upper Switch", Subsystems.guillotine.getUpperSwitchValue());
        SmartDashboard.putBoolean("Lift Lower Switch", Subsystems.guillotine.getLowerSwitchValue());
        SmartDashboard.putBoolean("Intake Upper Switch", Subsystems.intake.getUpperSwitchValue());
        SmartDashboard.putBoolean("Intake Lower Switch", Subsystems.intake.getLowerSwitchValue());
        SmartDashboard.putNumber("Guillotine Position", Subsystems.guillotine.getLiftPosition());
        SmartDashboard.putNumber("Left Encoder", Subsystems.driveBase.getLeftPosition());
        SmartDashboard.putNumber("Right Encoder", Subsystems.driveBase.getRightPosition());
        SmartDashboard.putNumber("Left Arm Current", Subsystems.intake.getLeftArmCurrent());
        SmartDashboard.putNumber("Right Arm Current", Subsystems.intake.getRightArmCurrent());
        SmartDashboard.putNumber("Xbox POV", UserInterface.operatorController.getPOVAngle());
        SmartDashboard.putNumber("Gyro Angle", Subsystems.driveBase.getGyroAngle());
    }
}