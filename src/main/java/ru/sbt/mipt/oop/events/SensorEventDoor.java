package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventDoor extends SensorEvent {

    public SensorEventDoor(SensorEventType type, String objectId) {
        super(type, objectId);
    }


    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getType() +
                ", objectId='" + this.getObjectId() + '\'' +
                '}';
    }

   /* @Override
    public void performEvent(SmartHome smartHome) {
        // событие от двери
        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(this.getObjectId())) {
                    if (this.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                        // если мы получили событие о закрытие двери в холле - это значит, что была закрыта входная дверь.
                        // в этом случае мы хотим автоматически выключить свет во всем доме (это же умный дом!)
                        if (room.getName().equals("hall")) {
                            LightScenarios.allLightsOff(smartHome);
                        }
                    }
                }
            }
        }
    }*/

}

