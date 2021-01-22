package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class GuillotineLower extends CommandBase {

    public GuillotineLower() {
        setName("GuillotineLower");
        addRequirements(Subsystems.guillotine);
    }

    @Override
    public void execute() {
        Subsystems.guillotine.setLiftSpeed(-0.4d);
    }

    @Override
    public boolean isFinished() {
        return Subsystems.guillotine.getLowerSwitchValue();
    }

    @Override
    public void end(boolean interrupt) {
        Subsystems.guillotine.setLiftSpeed(0.0d);
    }

}
