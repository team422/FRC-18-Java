package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class IntakeBox extends CommandGroup {

    public IntakeBox() {
        addSequential(new IntakeGrab()); // opens intake
        addSequential(new GuillotineKick()); // opens kicker
        addSequential(new PivotIntakeUp(false, 3.0d)); // pivots up and stops if beambreak is triggered
        addSequential(new PivotIntakeUp(true, 0.05d)); // in case not triggered, pivots up for that time only and does not rely on beambreak
        addSequential(new GuillotineHold()); // closes kicker
    }


}
