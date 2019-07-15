package frc.robot.userinterface;

import frc.robot.RobotMap;

public class UserInterface {

    public static final Attack3 leftJoystick = new Attack3(RobotMap.leftJoystick);
    public static final Attack3 rightJoystick = new Attack3(RobotMap.rightJoystick);
    public static final XboxController operatorController = new XboxController(RobotMap.operatorXboxController);

}
