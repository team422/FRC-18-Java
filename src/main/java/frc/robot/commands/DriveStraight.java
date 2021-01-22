package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Subsystems;

public class DriveStraight extends CommandBase {

    private double ticks;
    private boolean forward;
    private double speed;

    public DriveStraight(double inches, double Speed, boolean Forward) {
        setName("DriveStraight");
        addRequirements(Subsystems.driveBase);
        ticks = convertToTicks(inches);
        forward = Forward;
        speed = Speed;
    }

    @Override
    public void initialize() {
        Subsystems.driveBase.zeroEncoderPosition();
        Subsystems.driveBase.zeroGyroAngle();
        SmartDashboard.putString("messy","EVERYTHING IS FINE");
    }

    @Override
    public void execute() {
        double correction = Subsystems.driveBase.getGyroAngle();
        correction *= 0.075;
        correction += 1.0;
        if (forward) {
            Subsystems.driveBase.setMotors(-speed, -speed * correction);
        } else {
            Subsystems.driveBase.setMotors(speed * correction, speed);
        }
    }

    @Override
    public boolean isFinished() {
        double leftPosition = Math.abs(Subsystems.driveBase.getLeftPosition());
        double rightPosition = Math.abs(Subsystems.driveBase.getRightPosition());
        return (leftPosition > ticks) || (rightPosition > ticks);
    }

    @Override
    public void end(boolean interrupted) {
        Subsystems.driveBase.setMotors(0,0);
    }

    public double convertToTicks(double inches) {
        return (4096 / (6 * 3.1415926) * inches);
    }

}
