package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.eventtypes.SensorEventType;
import ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight;

import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.*;

public class EventProduserImplStub implements EventProduser {
    public SensorEvent getNextSensorEvent() {
        // pretend like we're getting the events from physical world, but here we're going to just generate some random events
        if (Math.random() < 0.05) return null; // null means end of event stream
        SensorEventType sensorEventType = SensorEventTypeDoorAndLight.values()[(int) (4 * Math.random())];
        String objectId = "" + ((int) (10 * Math.random()));
        if (sensorEventType == DOOR_OPEN || sensorEventType == DOOR_CLOSED) {
            return new SensorEventDoor(sensorEventType, objectId);
        }
        if (sensorEventType == LIGHT_ON || sensorEventType == LIGHT_OFF) {
            return new SensorEventLight(sensorEventType, objectId);
        }
        return null;
    }
}
