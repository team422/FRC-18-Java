package frc.robot.commands.autonomous;

import frc.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class Rotations extends CommandGroup {

  public Rotations() {
    // *** Write your commands here! ***

    addSequential(new DriveStraight(10, 0.3, true, 5));
  }

}