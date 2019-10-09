package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.CommandWorkers.CommandSender;
import ru.sbt.mipt.oop.CommandWorkers.CommandType;
import ru.sbt.mipt.oop.CommandWorkers.SensorCommand;
import ru.sbt.mipt.oop.EventTypes.SensorEventType;
import ru.sbt.mipt.oop.HomeDevices.Light;
import ru.sbt.mipt.oop.HomeParts.Room;
import ru.sbt.mipt.oop.HomeParts.SmartHome;

import static ru.sbt.mipt.oop.EventTypes.SensorEventTypeLight.LIGHT_ON;

public class SensorEventLight extends SensorEvent {
    public SensorEventLight(SensorEventType type, String objectId) {
        super(type, objectId);
    }

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

    @Override
    public void performEvent(SmartHome smartHome) {
        for (Room room : smartHome.getRooms()) {
            for (Light light : room.getLights()) {
                if (light.getId().equals(this.getObjectId())) {
                    if (this.getType() == LIGHT_ON) {
                        light.setOn(true);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned on.");
                    } else {
                        light.setOn(false);
                        System.out.println("Light " + light.getId() + " in room " + room.getName() + " was turned off.");
                    }
                }
            }
        }
    }

}
