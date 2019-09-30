package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;

public class GuillotineHold extends Command {

    public GuillotineHold() {
        super("GuillotineHold");
        requires(Subsystems.guillotine);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        Subsystems.guillotine.hold();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void interrupted() {

    }


    @Override
    public void end() {

    }

}
