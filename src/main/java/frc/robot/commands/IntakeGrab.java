package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class IntakeGrab extends CommandBase {

    public IntakeGrab() {
        setName("IntakeGrab");
        addRequirements(Subsystems.intake);
    }

    @Override
    public void execute() {
        Subsystems.intake.grab();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}

