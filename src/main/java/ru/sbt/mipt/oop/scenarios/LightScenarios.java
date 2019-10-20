package ru.sbt.mipt.oop.scenarios;

import ru.sbt.mipt.oop.commandworkers.CommandSender;
import ru.sbt.mipt.oop.commandworkers.CommandType;
import ru.sbt.mipt.oop.commandworkers.SensorCommand;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;

public class LightScenarios implements Scenario {

    public static void allLightsOff(SmartHome smartHome) {
        for (Room homeRoom : smartHome.getRooms()) {
            for (Light light : homeRoom.getLights()) {
                light.setOn(false);
                CommandSender commandSender = new CommandSender();
                SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
                commandSender.sendCommand(command);
            }
        }
    }
}
