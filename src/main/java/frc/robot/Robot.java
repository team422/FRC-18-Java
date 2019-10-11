package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.commands.autonomous.*;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

public class Robot extends TimedRobot {

    private UsbCamera camera;
    private CommandGroup autonomous;
    private Command Rotations;

    public void robotInit() {
        Subsystems.compressor.start();
        UserInterface.operatorController.A.whenPressed(new IntakeGrab()); // intake arms close: A
        UserInterface.operatorController.B.whenPressed(new IntakeRelease()); // intake arms open: B
        UserInterface.operatorController.X.whenPressed(new GuillotineHold()); // kicker retracts: X
        UserInterface.operatorController.Y.whenPressed(new GuillotineKick()); // kicker kicks out: Y
        UserInterface.operatorController.START.whenPressed(new IntakeBox()); // entire programmed sequence: START
        camera = CameraServer.getInstance().startAutomaticCapture();
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

    public void disabledInit() {
        Subsystems.arduino.sendCommand("0001111");
    }

    public void autonomousInit() {
        Scheduler.getInstance().removeAll();
        Subsystems.arduino.sendCommand("0005551");
        // String gameData = DriverStation.getInstance().getGameSpecificMessage();
        // if (UserInterface.launchpad.getMultiSwitchLeft()) {
        //     autonomous = new LeftAutonomous(gameData, UserInterface.launchpad.getSwitch1());
        // } else if (UserInterface.launchpad.getMultiSwitchInactive()) {
        //     autonomous = new CenterAutonomous(gameData.charAt(0));
        // } else if (UserInterface.launchpad.getMultiSwitchRight()) {
        //     autonomous = new RightAutonomous(gameData, UserInterface.launchpad.getSwitch1());
        // }
        // autonomous.start();
        Rotations.start();
    }

    public void teleopInit() {
        // if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
        //     Subsystems.arduino.sendCommand("0003331");
        // } else {
        //     Subsystems.arduino.sendCommand("0002221");
        // }
        while (true) {
            Subsystems.arduino.sendCommand("0002290");
            Subsystems.arduino.sendCommand("0002785");
            Subsystems.arduino.sendCommand("0003264");
            Subsystems.arduino.sendCommand("0003744");
            Subsystems.arduino.sendCommand("0003953");
            Subsystems.arduino.sendCommand("0003922");
            Subsystems.arduino.sendCommand("0003892");
            Subsystems.arduino.sendCommand("0003862");
            Subsystems.arduino.sendCommand("0003337");
            Subsystems.arduino.sendCommand("0002827");
            Subsystems.arduino.sendCommand("0002317");
            Subsystems.arduino.sendCommand("0001823");
            Subsystems.arduino.sendCommand("0001087");
            Subsystems.arduino.sendCommand("0000607");
            Subsystems.arduino.sendCommand("0000383");
            Subsystems.arduino.sendCommand("0000174");
            Subsystems.arduino.sendCommand("0000205");
            Subsystems.arduino.sendCommand("0000234");
            Subsystems.arduino.sendCommand("0000760");
            Subsystems.arduino.sendCommand("0001270");
            Subsystems.arduino.sendCommand("0001780");
        }
       //  Scheduler.getInstance().removeAll();
    }

    public void disabledPeriodic() {
        printDataToSmartDashboard();
    }
    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
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
        Scheduler.getInstance().run();
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