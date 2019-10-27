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
}
