package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class GuillotineKick extends CommandBase {

    public GuillotineKick() {
        setName("GuillotineKick");
        addRequirements(Subsystems.guillotine);
    }

    @Override
    public void execute() {
        Subsystems.guillotine.kick();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
