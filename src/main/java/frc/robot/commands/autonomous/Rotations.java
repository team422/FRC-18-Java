package frc.robot.commands.autonomous;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Rotations extends CommandGroup {

  public Rotations() {
    // *** Write your commands here! ***

    addSequential(new DriveStraight(10, 0.3, true, 2.4));
    addSequential(new Turn(90, 0.3, 2));
    addSequential(new DriveStraight(0, 0.3, true, 1.3));
    addSequential(new Turn(-90, 0.4, 2));
    addSequential(new DriveStraight(0, 0.3, true, 1.7));
    addSequential(new Turn(-90, 0.3, 2));
    addSequential(new DriveStraight(10, 0.3, true, 2.5));


  }

}