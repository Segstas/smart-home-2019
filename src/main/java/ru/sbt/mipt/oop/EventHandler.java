package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.basic.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.basic.HallDoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.basic.LightEventProcessor;
import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import java.util.ArrayList;
import java.util.List;

public class EventHandler {
    private EventProduser eventProduser;

    @Autowired
    private SmartHome smartHome;
    private List<EventProcessor> processors = creareEventProcessorList();


    protected EventHandler(EventProduser eventProduser, SmartHome smartHome) {
        this.eventProduser = eventProduser;
        this.smartHome = smartHome;
    }

    public EventHandler(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    private static List<EventProcessor> creareEventProcessorList() {
        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new DoorEventProcessor());
        processors.add(new LightEventProcessor());
        processors.add(new HallDoorEventProcessor());
        return processors;
    }

    public void handleEvent() {
        SensorEvent event = eventProduser.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventProcessor processor : processors) {
                processor.process(smartHome, event);
            }
            event = eventProduser.getNextSensorEvent();
        }
    }
}
