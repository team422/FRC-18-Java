package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class IntakeBox extends SequentialCommandGroup {
    public IntakeBox() {
        addCommands(new IntakeGrab()); // opens intake
        addCommands(new GuillotineKick()); // opens kicker
        addCommands(new PivotIntakeUp(false).withTimeout(3)); // pivots up and stops if beambreak is triggered
        addCommands(new PivotIntakeUp(true).withTimeout(0.05)); // in case not triggered, pivots up for that time only and does not rely on beambreak
        addCommands(new GuillotineHold()); // closes kicker
    }
}