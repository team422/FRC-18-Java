package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;
import frc.robot.userinterface.UserInterface;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends Command {

    private double updatedLeftSpeed = 0;
    private double updatedRightSpeed = 0;
    private static final double maxChangeSpeed = 0.2;
    private static final double speedCap = 0.8;

    public TankDrive() {
        super("TankDrive");
        requires(Subsystems.driveBase);
    }

    @Override
    protected void execute() {

        double leftSpeed = UserInterface.leftJoystick.getJoystickY();
        double rightSpeed = UserInterface.rightJoystick.getJoystickY();
        boolean rightTrigger = UserInterface.rightJoystick.TRIGGER.get();

        if (rightTrigger) {
            leftSpeed = rightSpeed;
        } 

        double speedDifferenceRight = rightSpeed - updatedRightSpeed;
        if (speedDifferenceRight > maxChangeSpeed) {
            rightSpeed = updatedRightSpeed + maxChangeSpeed;

        } else if (speedDifferenceRight < -maxChangeSpeed) {
            rightSpeed = updatedRightSpeed - maxChangeSpeed;
        }

        double speedDifferenceLeft = leftSpeed - updatedLeftSpeed;
        if (speedDifferenceLeft > maxChangeSpeed) {
            leftSpeed = updatedLeftSpeed + maxChangeSpeed;

        } else if (speedDifferenceLeft < -maxChangeSpeed) {
            leftSpeed = updatedLeftSpeed - maxChangeSpeed;
        }

        updatedLeftSpeed = leftSpeed;
        updatedRightSpeed = rightSpeed;

        Subsystems.driveBase.setMotors(leftSpeed * speedCap, rightSpeed * speedCap);

        SmartDashboard.putNumber("Right Speed", rightSpeed);
        SmartDashboard.putNumber("Left Speed", leftSpeed);
        SmartDashboard.putNumber("Speed Difference Right", speedDifferenceRight);
        SmartDashboard.putNumber("Speed Difference Left", speedDifferenceLeft);
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