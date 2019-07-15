package frc.robot.userinterface;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Attack3 {

    private Joystick joystick;
    public final JoystickButton TRIGGER;

    public Attack3(int port) {
        this.joystick = new Joystick(port);
        this.TRIGGER = new JoystickButton(joystick, 1);
    }

    public double getJoystickY() { return joystick.getRawAxis(1); }
}
