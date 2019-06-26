package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.*;

public class CenterAutonomous extends CommandGroup{

    public CenterAutonomous(char c) {
        addSequential(new GuillotineHold());
        addSequential(new IntakeGrab());
        if (c == 'L') {
            // Score on the left side on the front of the switch
            addSequential(new DriveStraight(10.968, 0.5, true, 2.0d));
            addSequential(new Turn(-50, 0.7, 5));
            addSequential(new DriveStraight(54.877, 0.9, true, 5));
            addSequential(new Turn(50, 0.7, 5));
            addSequential(new DriveStraight(50, 0.9, true, 1.0d));
            addSequential(new GuillotineKick());
            addSequential(new PivotIntakeDown(0.3f));
            addSequential(new WaitCommand(0.5f));
            addSequential(new IntakeArmsOut());
            addSequential(new ArduinoControllerSendCommand());
            addSequential(new WaitCommand(1));
            addSequential(new IntakeArmsStop());
        } else if (c == 'R') {
            // Score on the right side on the front of the switch
            addSequential(new DriveStraight(10.968, 0.5, true, 2.0d));
            addSequential(new Turn(50, 0.7, 5));
            addSequential(new DriveStraight(49.877, 0.9, true, 5));
            addSequential(new Turn(-50, 0.7, 5));
            addSequential(new DriveStraight(50, 0.9, true, 1.0d));
            addSequential(new GuillotineKick());
            addSequential(new PivotIntakeDown(0.3f));
            addSequential(new WaitCommand(0.5f));
            addSequential(new IntakeArmsOut());
            addSequential(new ArduinoControllerSendCommand());
            addSequential(new WaitCommand(2));
            addSequential(new IntakeArmsStop());
        }
    }

}
