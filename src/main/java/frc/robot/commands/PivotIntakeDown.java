package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;

public class PivotIntakeDown extends Command {

    private double time;

    public PivotIntakeDown(double Time) {
        super("PivotIntakeDown");
        requires(Subsystems.intake);
        time = Time;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Subsystems.intake.setPivotSpeed(-0.1d);
    }

    @Override
    public boolean isFinished() {
        if (time != 0.0d) {
            return isTimedOut();
        } else {
            return Subsystems.intake.getUpperSwitchValue();
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