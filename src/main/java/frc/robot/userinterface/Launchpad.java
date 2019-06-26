package frc.robot.userinterface;

import edu.wpi.first.wpilibj.Joystick;

public class Launchpad {

    private Joystick joystick;

    public Launchpad(int port) {
        this.joystick = new Joystick(port);
    }

    public boolean getSwitch1() {
        return joystick.getRawButton(5);
    }

    public boolean getSwitch2() {
        return joystick.getRawButton(1);
    }

    public boolean getSwitch3() {
        return joystick.getRawButton(6);
    }

    public boolean getSwitch4() {
        return joystick.getRawButton(11);
    }

    public boolean getMultiSwitchLeft() {
        return joystick.getRawButton(9);
    }

    public boolean getMultiSwitchRight() {
        return joystick.getRawButton(12);
    }

    public boolean getMultiSwitchInactive() {
        return !getMultiSwitchLeft() && !getMultiSwitchRight();
    }

}
