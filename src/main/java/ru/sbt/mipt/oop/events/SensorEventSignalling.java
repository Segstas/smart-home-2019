package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventSignalling extends SensorEvent {

    private String code;

    public SensorEventSignalling(SensorEventType type, String objectId, String code) {
        super(type, objectId);
        this.code = code;
    }

    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getType() +
                ", objectId='" + this.getObjectId() + '\'' +
                '}';
    }

    public String getCode() {
        return code;
    }
}