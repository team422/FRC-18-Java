package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Subsystems;

public class ArduinoControllerSendCommand extends CommandBase {

    public ArduinoControllerSendCommand() {
        setName("ArduinoControllerSendCommand");
    }

    @Override
    public void initialize() {
        if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            Subsystems.arduino.sendCommand("0005356");
        } else {
            Subsystems.arduino.sendCommand("0005256");
        }
    }
}