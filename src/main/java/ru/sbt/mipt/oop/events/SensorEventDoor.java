package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventDoor extends SensorEvent {

    public SensorEventDoor(SensorEventType type, String objectId) {
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

