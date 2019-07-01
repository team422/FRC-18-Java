package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//DO NOT CHANGE OR ELSE WE WILL PURSUE YOU AND THROW ROCKS AND BRANDISH FIREY TORCHES

public class TankDrive extends Command {

    // private double leftSpeed = 0;
    // private double rightSpeed = 0;
    private double updatedSpeed = 0;
    private double updatedRotation = 0;
    private static final double maxChangeSpeed = 0.035d;
    private static final double maxChangeRotation = 0.2d;

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
        if (speedDifference > maxChangeSpeed) {
            speed = updatedSpeed + maxChangeSpeed;
            System.out.print("Ridsfon Speed");

        } else if (speedDifference < -maxChangeSpeed) {
            speed = updatedSpeed - maxChangeSpeed;
            System.out.print("tidsfon Speed");

        }
        speed *= 1;
        updatedSpeed = speed;

        // if (UserInterface.driverController.getLeftJoystickX() < -.1) {
        //     rotation = Math.pow(UserInterface.driverController.getLeftJoystickX(), 2);
        // } else if (UserInterface.driverController.getLeftJoystickX() > .1) {
        //     rotation = -Math.pow(UserInterface.driverController.getLeftJoystickX(), 2);
        // } else {
        //     rotation = 0.0d;
        // }
        double rotation;
        rotation = -Math.pow(UserInterface.driverController.getLeftJoystickX(), 3);
        double rotationDifference = rotation - updatedRotation;

        if (rotationDifference > maxChangeRotation) {
            rotation = updatedRotation + maxChangeRotation;
            System.out.print("Rotatidsfon Speed");

        } else if (rotationDifference < -maxChangeRotation) {
            rotation = updatedRotation - maxChangeRotation;
            System.out.print("Rotatidsfon Speed");

        }
        rotation *= 0.7;
        updatedRotation = rotation;
        // Subsystems.driveBase.arcadeDrive.arcadeDrive(speed, rotation, false);
        
        SmartDashboard.putNumber("Motor Speed", speed);
        SmartDashboard.putNumber("Rotation Speed", rotation);
        SmartDashboard.putNumber("Speed Difference", speedDifference);
        

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