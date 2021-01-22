package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class PivotIntakeUp extends CommandBase {

    private boolean forTimeOnly;

    public PivotIntakeUp(boolean ForTimeOnly) {
        setName("PivotIntakeUp");
        addRequirements(Subsystems.intake);
        forTimeOnly = ForTimeOnly;
    }

    @Override
    public void execute() {
        Subsystems.intake.setPivotSpeed(0.6d);
    }

    @Override
    public boolean isFinished() {
        if (forTimeOnly) {
            return false;
        } else {
            return Subsystems.intake.getLowerSwitchValue();
        }
    }

    @Override
    public void end(boolean interrupted) {
        Subsystems.intake.setPivotSpeed(0.0d);
    }

}