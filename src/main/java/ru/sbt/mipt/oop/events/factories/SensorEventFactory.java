package ru.sbt.mipt.oop.events.factories;

import ru.sbt.mipt.oop.events.SensorEvent;

public interface SensorEventFactory {
    SensorEvent generateSensorEvent(String objectID);
}
