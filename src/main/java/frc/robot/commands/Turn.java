package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Subsystems;

public class Turn extends CommandBase {

    private double degrees;
    private double speed;
    private boolean isCorrecting = false;

    public Turn(double Degrees, double Speed) {
        setName("Turn");
        addRequirements(Subsystems.driveBase);
        degrees = Degrees;
        speed = Speed;
    }

    @Override
    public void initialize() {
        Subsystems.driveBase.zeroGyroAngle();
        Subsystems.driveBase.zeroEncoderPosition();
    }

    @Override
    public void execute() {
        if ((degrees > 0) && !isCorrecting) {
            // Turning to the right
            Subsystems.driveBase.setMotors(-speed, speed);
        } else if ((degrees < 0) && !isCorrecting) {
            // Turning to the left
            Subsystems.driveBase.setMotors(speed, -speed);
        } else if (degrees > 0) {
            // Turned to the right, but correcting to the left
            Subsystems.driveBase.setMotors(speed / 2, -speed / 2);
        } else {
            // Turned to the left, but correcting to the right
            Subsystems.driveBase.setMotors(-speed / 2, speed / 2);
        }
    }

    @Override
    public boolean isFinished() {
        double angle = Subsystems.driveBase.getGyroAngle();
        if (degrees > 0) {
            // Turning to the right
            if (!isCorrecting) {
                if (angle > degrees) {
                    isCorrecting = true;
                }
                return false;
            }
            return (angle < degrees);
        } else {
            // Turning to the left
            if (!isCorrecting) {
                if (angle < degrees) {
                    isCorrecting = true;
                }
                return false;
            }
            return (angle > degrees);
        }
    }

    @Override
    public void end(boolean interrupted) {
        Subsystems.driveBase.setMotors(0,0);
    }

}
