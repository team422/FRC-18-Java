package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;

public class CenterAutonomous extends SequentialCommandGroup {

    public CenterAutonomous(char c) {
        addCommands(new GuillotineHold());
        addCommands(new IntakeGrab());
        if (c == 'L') {
            // Score on the left side on the front of the switch
            addCommands(new DriveStraight(10.968, 0.5, true).withTimeout(2));
            addCommands(new Turn(-50, 0.7).withTimeout(5));
            addCommands(new DriveStraight(54.877, 0.9, true).withTimeout(5));
            addCommands(new Turn(50, 0.7).withTimeout(5));
            addCommands(new DriveStraight(50, 0.9, true).withTimeout(1));
            addCommands(new GuillotineKick());
            addCommands(new PivotIntakeDown().withTimeout(0.3));
            addCommands(new WaitCommand(0.5));
            addCommands(new IntakeArmsOut());
            addCommands(new ArduinoControllerSendCommand());
            addCommands(new WaitCommand(1));
            addCommands(new IntakeArmsStop());
        } else if (c == 'R') {
            // Score on the right side on the front of the switch
            addCommands(new DriveStraight(10.968, 0.5, true).withTimeout(2.0));
            addCommands(new Turn(50, 0.7).withTimeout(5));
            addCommands(new DriveStraight(49.877, 0.9, true).withTimeout(5));
            addCommands(new Turn(-50, 0.7).withTimeout(5));
            addCommands(new DriveStraight(50, 0.9, true).withTimeout(1));
            addCommands(new GuillotineKick());
            addCommands(new PivotIntakeDown().withTimeout(0.3));
            addCommands(new WaitCommand(0.5f));
            addCommands(new IntakeArmsOut());
            addCommands(new ArduinoControllerSendCommand());
            addCommands(new WaitCommand(2));
            addCommands(new IntakeArmsStop());
        }
    }

}
