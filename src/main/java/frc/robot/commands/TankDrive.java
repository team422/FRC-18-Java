package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;

public class TankDrive extends Command {

    // private double leftSpeed = 0;
    // private double rightSpeed = 0;
    private double updatedSpeed = 0;
    private double updatedRotation = 0;
    private static final double maxChange = 0.06d;

    public TankDrive() {
        super("TankDrive");
        requires(Subsystems.driveBase);
    }

    @Override
    protected void execute() {
        // double left = UserInterface.driverController.getLeftJoystickY();
        // double leftDifference = left - leftSpeed;
        // if (leftDifference > maxChange) {
        //     left = leftSpeed + maxChange;
        // } else if (leftDifference < -maxChange) {
        //     left = leftSpeed - maxChange;
        // }
        // double right = -UserInterface.driverController.getRightJoystickY();
        // double rightDifference = right - rightSpeed;
        // if (rightDifference > maxChange) {
        //     right = rightSpeed + maxChange;
        // } else if (rightDifference < -maxChange) {
        //     right = rightSpeed - maxChange;
        // }
        // leftSpeed = left;
        // rightSpeed = right;
        // Subsystems.driveBase.splitDrive.tankDrive(0.5*left, 0.5*right, false);
        // Above tank drive has no squared inputs, may be good to test with Xbox controller
        double speed;
        if (UserInterface.driverController.getRightJoystickY() < -.1) {
            speed = -Math.pow(UserInterface.driverController.getRightJoystickY(), 2);
        } else if (UserInterface.driverController.getRightJoystickY() > .1) {
            speed = Math.pow(UserInterface.driverController.getRightJoystickY(), 2);
        } else {
            speed = 0.0d;
        }
        double speedDifference = speed - updatedSpeed;
        if (speedDifference > maxChange) {
            speed = updatedSpeed + maxChange;
        } else if (speedDifference < -maxChange) {
            speed = updatedSpeed - maxChange;
        }
        updatedSpeed = speed;
        double rotation;
        // if (UserInterface.driverController.getLeftJoystickX() < -.1) {
        //     rotation = Math.pow(UserInterface.driverController.getLeftJoystickX(), 2);
        // } else if (UserInterface.driverController.getLeftJoystickX() > .1) {
        //     rotation = -Math.pow(UserInterface.driverController.getLeftJoystickX(), 2);
        // } else {
        //     rotation = 0.0d;
        // }
        rotation = -Math.pow(UserInterface.driverController.getLeftJoystickX(), 3);
        double rotationDifference = rotation - updatedRotation;
        if (rotationDifference > maxChange) {
            rotation = updatedRotation + maxChange;
        } else if (speedDifference < -maxChange) {
            rotation = updatedRotation - maxChange;
        }
        updatedRotation = rotation;
        // Subsystems.driveBase.arcadeDrive.arcadeDrive(speed, rotation, false);
 
        Subsystems.driveBase.cheesyDrive.curvatureDrive(rotation, speed, true);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void interrupted() {
    }

    @Override
    protected void end() {
    }

}