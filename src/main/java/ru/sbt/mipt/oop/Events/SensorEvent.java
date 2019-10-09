package ru.sbt.mipt.oop.Events;

import ru.sbt.mipt.oop.EventTypes.SensorEventType;
import ru.sbt.mipt.oop.HomeParts.SmartHome;

public abstract class SensorEvent {
    private final SensorEventType type;
    private final String objectId;

    public SensorEvent(SensorEventType type, String objectId) {
        this.type = type;
        this.objectId = objectId;
    }

    public SensorEventType getType() {
        return type;
    }

    public String getObjectId() {
        return objectId;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + type +
                ", objectId='" + objectId + '\'' +
                '}';
    }

    public abstract void performEvent(SmartHome smartHome);
}
