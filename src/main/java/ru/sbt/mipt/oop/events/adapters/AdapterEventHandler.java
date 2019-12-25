package ru.sbt.mipt.oop.events.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventUnknown;
import ru.sbt.mipt.oop.events.factories.SensorEventFactory;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class AdapterEventHandler implements EventHandler {


    private SmartHome smartHome;

    private Collection<EventProcessor> eventProcessors;

    private Map<String, SensorEventFactory> sensorEventFactoryMap = new HashMap<>();

    public AdapterEventHandler(SmartHome smartHome, Collection<EventProcessor> eventProcessors, Map<String, SensorEventFactory> sensorEventFactoryMap) {
        this.smartHome = smartHome;
        this.eventProcessors = eventProcessors;
        this.sensorEventFactoryMap = sensorEventFactoryMap;
    }

    private SensorEvent returnComparableEvent(CCSensorEvent event) {
        if (!sensorEventFactoryMap.containsKey(event.getEventType())) return new SensorEventUnknown();
        if (sensorEventFactoryMap.get(event.getEventType()) == null) return new SensorEventUnknown();
        return sensorEventFactoryMap.get(event.getEventType()).generateSensorEvent(event.getObjectId());
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        SensorEvent basicEvent = this.returnComparableEvent(event);
        System.out.println("Got event: " + basicEvent);
        for (EventProcessor processor : eventProcessors) {
            processor.process(smartHome, basicEvent);
        }
    }
}
