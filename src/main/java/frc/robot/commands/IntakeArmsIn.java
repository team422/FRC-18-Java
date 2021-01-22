package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class IntakeArmsIn extends CommandBase {

    public IntakeArmsIn() {
        setName("IntakeArmsIn");
        addRequirements(Subsystems.intake);
    }

    @Override
    public void execute() {
        Subsystems.intake.setArmsSpeed(0.4d);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
