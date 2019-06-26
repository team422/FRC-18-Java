package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;

public class ArduinoController {

    private SerialPort port;

    public ArduinoController() {
        this.port = new SerialPort(9600, SerialPort.Port.kUSB1);
    }

    public void sendCommand(String command) {
        port.writeString("!" + command);
    }

}
