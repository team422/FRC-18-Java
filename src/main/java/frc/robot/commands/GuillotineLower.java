package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;

public class GuillotineLower extends Command {

    public GuillotineLower() {
        super("GuillotineLower");
        requires(Subsystems.guillotine);
    }

    @Override
    public void initialize() {

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
    public void interrupted() {
        Subsystems.guillotine.setLiftSpeed(0.0d);
    }

    @Override
    public void end() {
        Subsystems.guillotine.setLiftSpeed(0.0d);
    }

}
