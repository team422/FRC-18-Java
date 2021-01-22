package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.userinterface.*;

public class LeftAutonomous extends SequentialCommandGroup {

    public LeftAutonomous(String gameData, boolean scale) {
        addCommands(new GuillotineHold());
        addCommands(new IntakeGrab());
        if (gameData.charAt(1) == 'L' && scale) {
            // Single Cube Scale Auto
            addCommands(new DriveStraight(260.975f, 0.9f, true).withTimeout(8));
            addCommands(new Turn(94, 0.7f).withTimeout(3));
            addCommands(CommandGroupBase.parallel(new IntakeRelease(), new DriveStraight(8, 0.5f, false).withTimeout(0.6)));
            addCommands(new GuillotineRaise());
            addCommands(new DriveStraight(18, 0.8f, true).withTimeout(1.5));
            addCommands(new GuillotineKick());
            addCommands(new ArduinoControllerSendCommand());
            addCommands(CommandGroupBase.parallel(new DriveStraight(16, 0.5f, false).withTimeout(1.5), new GuillotineLower(), new PivotIntakeDown().withTimeout(0.75)));
        } else if (gameData.charAt(1) == 'R' && scale && !UserInterface.launchpad.getSwitch2()) {
            // Opposite side scale auto
            // If switch 2 is up, disable this mode
            addCommands(new DriveStraight(190.438, 0.9, true).withTimeout(5));
            addCommands(new Turn(87.0f, 0.7).withTimeout(5));
            addCommands(new DriveStraight(140.625, 0.9, true).withTimeout(5));
            addCommands(CommandGroupBase.parallel(new Turn(-98.0f, 0.7).withTimeout(3), new IntakeRelease()));
            addCommands(new GuillotineRaise());
            addCommands(new DriveStraight(52.057, 0.5, true).withTimeout(0.9));
            addCommands(new GuillotineKick());
            addCommands(new ArduinoControllerSendCommand());
            addCommands(new DriveStraight(16, 0.3f, false).withTimeout(1.5));
        } else if (gameData.charAt(0) == 'L') {
            // Left-side switch
            addCommands(new DriveStraight(114.275f, 0.9f, true).withTimeout(6));
            addCommands(new Turn(90.0f, 0.7f).withTimeout(3));
            addCommands(new DriveStraight(24.0f, 0.4f, true).withTimeout(1));
            addCommands(new GuillotineKick());
            addCommands(new PivotIntakeDown().withTimeout(0.2));
            addCommands(new IntakeArmsOut());
            addCommands(new ArduinoControllerSendCommand());
            addCommands(new WaitCommand(4));
            addCommands(new IntakeArmsStop());
        } else {
            // Right-side switch
            addCommands(new DriveStraight(187.53, 0.9f, true).withTimeout(8));
            addCommands(new Turn(90.0f, 0.7f).withTimeout(3));
            addCommands(new DriveStraight(187.534, 0.9f, true).withTimeout(1.5));
            addCommands(new Turn(90, 0.7f).withTimeout(3));
            addCommands(new DriveStraight(42.0f, 0.3f, true).withTimeout(1));
            addCommands(new GuillotineKick());
            addCommands(new PivotIntakeDown().withTimeout(0.1));
            addCommands(new IntakeArmsOut());
            addCommands(new ArduinoControllerSendCommand());
            addCommands(new WaitCommand(3));
            addCommands(new IntakeArmsStop());
        }
    }
}