package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class PivotIntakeDown extends CommandBase {

    public PivotIntakeDown() {
        setName("PivotIntakeDown");
        addRequirements(Subsystems.intake);
    }

    @Override
    public void execute() {
        Subsystems.intake.setPivotSpeed(-0.1d);
    }

    @Override
    public boolean isFinished() {
        return Subsystems.intake.getUpperSwitchValue();
    }

    @Override
    public void end(boolean interrupted) {
        Subsystems.intake.setPivotSpeed(0.0d);
    }

}