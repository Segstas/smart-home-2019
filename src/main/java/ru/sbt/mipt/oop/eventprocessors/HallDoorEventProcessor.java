package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.commandworkers.CommandSender;
import ru.sbt.mipt.oop.commandworkers.CommandType;
import ru.sbt.mipt.oop.commandworkers.SensorCommand;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventType.DOOR_CLOSED;

public class HallDoorEventProcessor implements EventProcessor {
    public void process(SmartHome smartHome, SensorEvent event) {
        smartHome.execute((Action) object -> {
            if (!(object instanceof Room)) {
                return;
            }
            Room room = (Room) object;
            if (isHallDoorEvent(event, room)) {
                turnOffLightInHome(smartHome);
            }

        });
    }

    private boolean isHallDoorEvent(SensorEvent event, Room room) {
        return (room.getName() == "hall" && event.getType() == DOOR_CLOSED && room.containsRightDoor(event.getObjectId()));
    }

    private void turnOffLightInHome(SmartHome smartHome) {
        smartHome.execute(smth -> {
            if (!(smth instanceof Light)) {
                return;
            }
            Light light = (Light) smth;
            light.setOn(false);
            System.out.println("All lights turning off");
            CommandSender commandSender = new CommandSender();
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        });
    }
}
