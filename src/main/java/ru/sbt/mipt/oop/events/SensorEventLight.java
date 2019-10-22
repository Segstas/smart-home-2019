package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventLight extends SensorEvent {
    public SensorEventLight(SensorEventType type, String objectId) {
        super(type, objectId);
    }


    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getType() +
                ", objectId='" + this.getObjectId() + '\'' +
                '}';
    }

  /*  @Override
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
    }*/

}
