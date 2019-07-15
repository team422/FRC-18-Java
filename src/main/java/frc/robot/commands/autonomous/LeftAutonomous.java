package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.commands.*;
import frc.robot.userinterface.*;

public class LeftAutonomous extends CommandGroup {

    public LeftAutonomous(String gameData, boolean scale) {
        addSequential(new GuillotineHold());
        addSequential(new IntakeGrab());
        if (gameData.charAt(1) == 'L' && scale) {
            // Single Cube Scale Auto
            addSequential(new DriveStraight(260.975f, 0.9f, true, 8.0f));
            addSequential(new Turn(94, 0.7f, 3.0f));
            addSequential(new IntakeRelease());
            addParallel(new DriveStraight(8, 0.5f, false, 0.6f));
            addSequential(new GuillotineRaise());
            addSequential(new DriveStraight(18, 0.8f, true, 1.5f));
            addSequential(new GuillotineKick());
            addSequential(new ArduinoControllerSendCommand());
            addSequential(new DriveStraight(16, 0.5f, false, 1.5f));
            addParallel(new GuillotineLower());
            addParallel(new PivotIntakeDown(0.75d));
        // } else if (gameData.charAt(1) == 'R' && scale && !UserInterface.launchpad.getSwitch2()) {
        //     // Opposite side scale auto
        //     // If switch 2 is up, disable this mode
            addSequential(new DriveStraight(190.438, 0.9, true, 5.0d));
            addSequential(new Turn(87.0f, 0.7, 5.0d));
            addSequential(new DriveStraight(140.625, 0.9, true, 5.0d));
            addSequential(new Turn(-98.0f, 0.7, 3.0d));
            addParallel(new IntakeRelease());
            addSequential(new GuillotineRaise());
            addSequential(new DriveStraight(52.057, 0.5, true, 0.9f));
            addSequential(new GuillotineKick());
            addSequential(new ArduinoControllerSendCommand());
            addSequential(new DriveStraight(16, 0.3f, false, 1.5f));
        } else if (gameData.charAt(0) == 'L') {
            // Left-side switch
            addSequential(new DriveStraight(114.275f, 0.9f, true, 6.0f));
            addSequential(new Turn(90.0f, 0.7f, 3.0f));
            addSequential(new DriveStraight(24.0f, 0.4f, true, 1.0f));
            addSequential(new GuillotineKick());
            addSequential(new PivotIntakeDown(0.2f));
            addSequential(new IntakeArmsOut());
            addSequential(new ArduinoControllerSendCommand());
            addSequential(new WaitCommand(4));
            addSequential(new IntakeArmsStop());
        } else {
            // Right-side switch
            addSequential(new DriveStraight(187.53, 0.9f, true, 8.0f));
            addSequential(new Turn(90.0f, 0.7f, 3.0f));
            addSequential(new DriveStraight(187.534, 0.9f, true, 1.5f));
            addSequential(new Turn(90, 0.7f, 3.0f));
            addSequential(new DriveStraight(42.0f, 0.3f, true, 1.0f));
            addSequential(new GuillotineKick());
            addSequential(new PivotIntakeDown(0.1f));
            addSequential(new IntakeArmsOut());
            addSequential(new ArduinoControllerSendCommand());
            addSequential(new WaitCommand(3));
            addSequential(new IntakeArmsStop());
        }
    }
}