package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends CommandBase {

    private double updatedSpeed = 0;
    private double updatedRotation = 0;
    private static final double maxChangeSpeed = 0.035d;
    private static final double maxChangeRotation = 0.2d;

    public TankDrive() {
        setName("TankDrive");
        addRequirements(Subsystems.driveBase);
    }

    @Override
    public void execute() {
        double speed;
        if (UserInterface.driverController.getRightJoystickY() < -.1) {
            speed = -Math.pow(UserInterface.driverController.getRightJoystickY(), 2);
        } else if (UserInterface.driverController.getRightJoystickY() > .1) {
            speed = Math.pow(UserInterface.driverController.getRightJoystickY(), 2);
        } else {
            speed = 0.0d;
        }
        double speedDifference = speed - updatedSpeed;
        if (speedDifference > maxChangeSpeed) {
            speed = updatedSpeed + maxChangeSpeed;

        } else if (speedDifference < -maxChangeSpeed) {
            speed = updatedSpeed - maxChangeSpeed;

        }
        speed *= RobotMap.speedCap;
        updatedSpeed = speed;

        double rotation;
        rotation = -Math.pow(UserInterface.driverController.getLeftJoystickX(), 3);
        double rotationDifference = rotation - updatedRotation;

        if (rotationDifference > maxChangeRotation) {
            rotation = updatedRotation + maxChangeRotation;

        } else if (rotationDifference < -maxChangeRotation) {
            rotation = updatedRotation - maxChangeRotation;

        }
        rotation *= RobotMap.rotationCap;
        updatedRotation = rotation;
        
        SmartDashboard.putNumber("Motor Speed", speed);
        SmartDashboard.putNumber("Rotation Speed", rotation);
        SmartDashboard.putNumber("Speed Difference", speedDifference);

        Subsystems.driveBase.cheesyDrive.curvatureDrive(rotation, speed, true);
    }

}