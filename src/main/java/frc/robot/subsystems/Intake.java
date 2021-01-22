package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {

    private DoubleSolenoid grabber;
    private TalonSRX pivot;
    private TalonSRX leftArm;
    private TalonSRX rightArm;
    private DigitalInput upperSwitch;
    private DigitalInput lowerSwitch;
    private AnalogInput ultrasonic;


    public Intake() {
        setName("Intake");
        this.grabber = new DoubleSolenoid(RobotMap.intakeForward, RobotMap.intakeReverse);
        this.pivot = new TalonSRX(RobotMap.intakePivot);
        this.leftArm = new TalonSRX(RobotMap.intakeLeftArm);
        this.rightArm = new TalonSRX(RobotMap.intakeRightArm);
        this.upperSwitch = new DigitalInput(RobotMap.intakeUpperSwitch);
        this.lowerSwitch = new DigitalInput(RobotMap.intakeLowerSwitch);
        this.ultrasonic = new AnalogInput(RobotMap.intakeUltrasonic);
        leftArm.setInverted(true);
        // if(!RobotMap.COMP_BOT) {
        //     pivot.setInverted(true);
        // }
    }

    public void setArmsSpeed(double speed) {
        leftArm.set(ControlMode.PercentOutput, speed);
        rightArm.set(ControlMode.PercentOutput, speed);
    }

    public void grab() {
        grabber.set(DoubleSolenoid.Value.kForward);
    }

    public void release() {
        grabber.set(DoubleSolenoid.Value.kReverse);
    }

    public void setPivotSpeed(double speed) {
        pivot.set(ControlMode.PercentOutput, 0.0d);
        if (speed > 0 && !getLowerSwitchValue()) {
            pivot.set(ControlMode.PercentOutput, speed);
        } else if (speed < 0 && !getUpperSwitchValue()) {
            pivot.set(ControlMode.PercentOutput, speed);
        }
    }

    public boolean getUpperSwitchValue() {
        return !upperSwitch.get();
    }

    public boolean getLowerSwitchValue() {
        return !lowerSwitch.get();
    }

    public double getLeftArmCurrent() {
        return leftArm.getStatorCurrent();
    }

    public double getRightArmCurrent() {
        return rightArm.getStatorCurrent();
    }

    public double getUltrasonicDistance() {
        return ultrasonic.getAverageVoltage();
    }

}
