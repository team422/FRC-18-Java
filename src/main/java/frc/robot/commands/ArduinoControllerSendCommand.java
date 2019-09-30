package frc.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Subsystems;

public class ArduinoControllerSendCommand extends Command {

    public ArduinoControllerSendCommand() {
        super("ArduinoControllerSendCommand");
    }

    @Override
    public void initialize() {
        if (DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red) {
            Subsystems.arduino.sendCommand("0005356");
        } else {
            Subsystems.arduino.sendCommand("0005256");
        }
    }

    @Override
    public void execute() {

    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public void interrupted() {

    }

    @Override
    public void end() {

    }



}
