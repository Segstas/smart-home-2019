package ru.sbt.mipt.oop.events.factories;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventLight;
import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventLightFactory implements SensorEventFactory {
    SensorEventType sensorEventType;

    public SensorEventLightFactory(SensorEventType sensorEventType) {
        this.sensorEventType = sensorEventType;
    }

    @Override
    public SensorEvent generateSensorEvent(String objectID) {
        return new SensorEventLight(sensorEventType, objectID);
    }
}
