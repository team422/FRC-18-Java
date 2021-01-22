package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class Guillotine extends SubsystemBase {

    private DoubleSolenoid kicker;
    private TalonSRX lift;
    private DigitalInput upperSwitch;
    private DigitalInput lowerSwitch;

    public Guillotine() {
        setName("Guillotine");
        this.kicker = new DoubleSolenoid(RobotMap.kickerForward, RobotMap.kickerReverse);
        this.lift = new TalonSRX(RobotMap.lift);
        this.upperSwitch = new DigitalInput(RobotMap.guillotineUpperSwitch);
        this.lowerSwitch = new DigitalInput(RobotMap.guillotineLowerSwitch);
    }

    public void hold() {
        kicker.set(DoubleSolenoid.Value.kForward);
    }

    public void kick() {
        kicker.set(DoubleSolenoid.Value.kReverse);
    }

    public void setLiftSpeed(double speed) {
        lift.set(ControlMode.PercentOutput, 0.0d);
        if (speed > 0 ) {
            lift.set(ControlMode.PercentOutput, 0.3d);
        }
        if (speed > 0 && !getUpperSwitchValue()) {
            lift.set(ControlMode.PercentOutput, speed);
        } else if (speed < 0 && !getLowerSwitchValue()) {
            lift.set(ControlMode.PercentOutput, speed);
        }
    }

    public boolean getUpperSwitchValue() {
        return !upperSwitch.get();
    }

    public boolean getLowerSwitchValue() {
        return !lowerSwitch.get();
    }

    public double getLiftPosition() {
        return lift.getSelectedSensorPosition(0);
    }

    public void zeroLiftPosition() {
        lift.setSelectedSensorPosition(0,0,1);
    }

}
