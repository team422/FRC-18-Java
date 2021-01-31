package frc.robot;

public class RobotMap {

    /**
     *  Whether or not this is the competition bot
     *  Changing this variable will change all the ports accordingly.
     */
    public static final boolean compBot = true;

    /**
     * Various Ports
     */

    // Talon/Victor IDS2
    public static final int leftMasterMotor = (compBot) ? 1 : 40;
    public static final int leftFollower1 = (compBot) ? 2 : 44;
    public static final int leftFollower2 = (compBot) ? 3 : 21;
    public static final int rightMasterMotor = (compBot) ? 4 : 39;
    public static final int rightFollower1 = (compBot) ? 5 : 42;
    public static final int rightFollower2 = (compBot) ? 6 : 46;
    public static final int intakePivot = (compBot) ? 46 : 16;
    public static final int intakeLeftArm = (compBot) ? 9 : 12;
    public static final int intakeRightArm = (compBot) ? 10 : 35;
    public static final int lift = (compBot) ? 11 : 56;

    // Double Solenoid Values
    public static final int intakeForward = 5; // one of two intake ports
    public static final int intakeReverse = 3; // one of two intake ports
    public static final int kickerForward = (compBot) ? 4 : 2; // one of two kicker ports
    public static final int kickerReverse = (compBot) ? 2 : 4; // one of two kicker ports

    // Digital IO Ports
    public static final int intakeUpperSwitch = (compBot) ? 4 : 5; // switch at max intake up
    public static final int intakeLowerSwitch = (compBot) ? 5 : 4; // switch at rest, lowest point of pivot
    public static final int guillotineUpperSwitch = 2; // switch at max elevator up
    public static final int guillotineLowerSwitch = 3; // switch at rest, lowest point
//    public static final int BEAM_BRAKE = 6;

    // Analog IO Ports
    public static final int intakeUltrasonic = 3;

    // UI Ports
    public static final int operatorXboxController = 2;
    public static final int driverXboxController = 1;
    public static final int launchpad  = 0;



}