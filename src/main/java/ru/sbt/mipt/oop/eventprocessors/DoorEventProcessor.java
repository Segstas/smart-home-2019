package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoor;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.scenarios.LightScenarios;

import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_OPEN;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_CLOSED;


public class DoorEventProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!(event.getType() == DOOR_OPEN || event.getType() == DOOR_CLOSED)) return;

        smartHome.execute(object -> {
            if (!(object instanceof Door)) {
                return ;
            }
            Door door = (Door) object;
            updateDoorState(door, event);

        });

    }

    private void updateDoorState(Door door, SensorEvent event) {
        if (door.getId().equals(event.getObjectId())) {
            if (event.getType() == DOOR_OPEN) {
                door.setOpen(true);
                System.out.println("Door " + door.getId() + " was opened.");
            } else {
                door.setOpen(false);
                System.out.println("Door " + door.getId() +  " was closed.");
                // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
            }
        }
    }



}
