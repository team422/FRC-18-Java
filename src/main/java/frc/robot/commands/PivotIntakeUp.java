package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;

public class PivotIntakeUp extends Command {

    private boolean forTimeOnly;
    private double time;

    public PivotIntakeUp(boolean ForTimeOnly, double Time) {
        super("PivotIntakeUp");
        requires(Subsystems.intake);
        forTimeOnly = ForTimeOnly;
        time = Time;
        setTimeout(time);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Subsystems.intake.setPivotSpeed(0.6d);
    }

    @Override
    public boolean isFinished() {
        if (forTimeOnly) {
            return isTimedOut();
        } else {
            return (Subsystems.intake.getLowerSwitchValue() || isTimedOut());
        }
    }

    @Override
    public void interrupted() {

    }


    @Override
    public void end() {
        Subsystems.intake.setPivotSpeed(0.0d);
    }

}