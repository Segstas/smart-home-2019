package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.eventtypes.SensorEventType;

public class SensorEventUnknown extends SensorEvent {

    public SensorEventUnknown() {
        super(SensorEventType.UNKNOWN, "");
    }

    public SensorEventUnknown(SensorEventType type, String objectId) {
      super(type,objectId);
    }


    @Override
    public String toString() {
        return "SensorEvent{" +
                "type=" + this.getType() +
                ", objectId='" + this.getObjectId() + '\'' +
                '}';
    }
}

