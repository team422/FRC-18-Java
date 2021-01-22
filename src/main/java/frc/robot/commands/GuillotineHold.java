package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class GuillotineHold extends CommandBase {

    public GuillotineHold() {
        setName("GuillotineHold");
        addRequirements(Subsystems.guillotine);
    }

    @Override
    public void execute() {
        Subsystems.guillotine.hold();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
