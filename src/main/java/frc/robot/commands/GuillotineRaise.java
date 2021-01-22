package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class GuillotineRaise extends CommandBase {

    public GuillotineRaise() {
        setName("GuillotineRaise");
        addRequirements(Subsystems.guillotine);
    }

    @Override
    public void execute() {
        Subsystems.guillotine.setLiftSpeed(0.9d);
        Subsystems.intake.release();
    }

    @Override
    public boolean isFinished() {
        return Subsystems.guillotine.getUpperSwitchValue();
    }

    @Override
    public void end(boolean interrupted) {
        Subsystems.guillotine.setLiftSpeed(0.0d);
    }

}
