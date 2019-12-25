package ru.sbt.mipt.oop.eventprocessors.basic;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.commandworkers.CommandSender;
import ru.sbt.mipt.oop.commandworkers.CommandType;
import ru.sbt.mipt.oop.commandworkers.SensorCommand;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;

public class HallDoorEventProcessor implements EventProcessor {
    CommandSender commandSender;

    public HallDoorEventProcessor(CommandSender commandSender) {
        this.commandSender = commandSender;
    }

    public void process(SmartHome smartHome, SensorEvent event) {
        smartHome.execute((Action) object -> {
            if (!(object instanceof Room)) {
                return;
            }
            Room room = (Room) object;
            if (room.getName().equals("hall")) room.execute(o -> {
                if (!(o instanceof Door)) {
                    return;
                }
                Door door = (Door) o;
                if (event.getObjectId().equals(door.getId())) turnOffLightInHome(smartHome, commandSender);

            });
        });
    }


    private void turnOffLightInHome(SmartHome smartHome, CommandSender commandSender) {
        smartHome.execute(smth -> {
            if (!(smth instanceof Light)) {
                return;
            }
            Light light = (Light) smth;
            light.setOn(false);
            System.out.println("All lights turning off");
            SensorCommand command = new SensorCommand(CommandType.LIGHT_OFF, light.getId());
            commandSender.sendCommand(command);
        });
    }
}
