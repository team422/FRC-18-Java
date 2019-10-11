package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Subsystems;

public class DriveStraight extends Command {

    private double ticks;
    private boolean forward;
    private double speed;
    private double timeout;

    // private int covertToTicks(double inches)

    public DriveStraight(double inches, double Speed, boolean Forward, double Timeout) {
        super("DriveStraight");
        requires(Subsystems.driveBase);
        setTimeout(Timeout);
        ticks = convertToTicks(inches);
        forward = Forward;
        speed = Speed;
        // timeout = Timeout;
    }

    @Override
    public void initialize() {
        Subsystems.driveBase.zeroEncoderPosition();
        Subsystems.driveBase.zeroGyroAngle();
        SmartDashboard.putString("messy","EVERYTHING IS FINEE");
    }

    @Override
    public void execute() {
        double correction = Subsystems.driveBase.getGyroAngle();
        correction *= 0.075;
        correction += 1.0;
        System.out.print(timeSinceInitialized());
        if (forward) {
            Subsystems.driveBase.setMotors(-speed, -speed * correction);
        } else {
            Subsystems.driveBase.setMotors(speed * correction, speed);
        }
        // if (timeSinceInitialized() >= timeout){
        //     isFinished();
        //     SmartDashboard.putString("messy","EVERYTHING IS FINEE");
        // }

    }

    @Override
    public boolean isFinished() {
        int leftPosition = Math.abs(Subsystems.driveBase.getLeftPosition());
        int rightPosition = Math.abs(Subsystems.driveBase.getRightPosition());
        return (leftPosition > ticks) || (rightPosition > ticks) || isTimedOut();
        
        // if (timeSinceInitialized() >= timeout){
        //     return true;
        // }
        // else {
        //     SmartDashboard.putString("messy","EVERYTHING IS FINEE");
        //     return false;
            
        // }
    }

    @Override
    public void interrupted() {
        Subsystems.driveBase.setMotors(0,0);
    }


    @Override
    public void end() {
        Subsystems.driveBase.setMotors(0,0);
    }

    public double convertToTicks(double inches) {
        return (4096 / (6 * 3.1415926) * inches);
    }

}
