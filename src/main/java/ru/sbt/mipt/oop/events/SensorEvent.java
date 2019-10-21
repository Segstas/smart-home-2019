package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.eventtypes.SensorEventType;
import ru.sbt.mipt.oop.homeparts.SmartHome;

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

    public abstract String toString();

    ////public abstract void performEvent(SmartHome smartHome);
}
