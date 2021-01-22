package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class IntakeArmsStop extends CommandBase {

    public IntakeArmsStop() {
        setName("IntakeArmsOut");
        addRequirements(Subsystems.intake);
    }

    @Override
    public void execute() {
        Subsystems.intake.setArmsSpeed(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
