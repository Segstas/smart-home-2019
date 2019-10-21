package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.commandworkers.CommandSender;
import ru.sbt.mipt.oop.commandworkers.CommandType;
import ru.sbt.mipt.oop.commandworkers.SensorCommand;
import ru.sbt.mipt.oop.eventtypes.SensorEventType;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeLight.LIGHT_ON;

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
