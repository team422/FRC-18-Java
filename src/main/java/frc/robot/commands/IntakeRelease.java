package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class IntakeRelease extends CommandBase {

    public IntakeRelease() {
        setName("IntakeRelease");
        addRequirements(Subsystems.intake);
    }

    @Override
    public void execute() {
        Subsystems.intake.release();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
