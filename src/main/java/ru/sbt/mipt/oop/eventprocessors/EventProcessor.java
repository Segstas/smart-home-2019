package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeparts.SmartHome;

public interface EventProcessor {
    void process(SmartHome smartHome, SensorEvent event);
}