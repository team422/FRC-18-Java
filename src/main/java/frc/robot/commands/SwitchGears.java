package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotMap;

/**
 * Toggles fast/slow mode for robot.
 */
public class SwitchGears extends CommandBase {

    public SwitchGears() {
        setName("SwitchGears");
    }

    public void initialize() {
        if (RobotMap.isFastMode) {
            // slow mode for robot
            RobotMap.speedCap = 0.6;
            RobotMap.rotationCap = 0.3;
        } else { 
            // toggles to fast mode for robot
            RobotMap.speedCap = 1;
            RobotMap.rotationCap = 0.7;
        }
        RobotMap.isFastMode = !RobotMap.isFastMode;
    }
    
    public boolean isFinished() {
        return true;
    }
    
}
