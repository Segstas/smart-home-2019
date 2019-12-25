package ru.sbt.mipt.oop.events.factories;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventDoor;
import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventDoorFactory implements SensorEventFactory {
    SensorEventType sensorEventType;

    public SensorEventDoorFactory(SensorEventType sensorEventType) {
        this.sensorEventType = sensorEventType;
    }

    @Override
    public SensorEvent generateSensorEvent(String objectID) {
        return new SensorEventDoor(sensorEventType,objectID);
    }
}
