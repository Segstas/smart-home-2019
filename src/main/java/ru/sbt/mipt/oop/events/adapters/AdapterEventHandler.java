package ru.sbt.mipt.oop.events.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.beans.factory.annotation.Autowired;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.factories.SensorEventDoorFactory;
import ru.sbt.mipt.oop.events.factories.SensorEventFactory;
import ru.sbt.mipt.oop.events.factories.SensorEventLightFactory;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.sbt.mipt.oop.eventtypes.SensorEventType.*;

public class AdapterEventHandler implements EventHandler {


    @Autowired
    private SmartHome smartHome;
    @Autowired
    private List<EventProcessor> eventProcessors;

    private Map<String, SensorEventFactory> sensorEventFactoryMap = new HashMap<>();

    public AdapterEventHandler() {
        fillSensorEventFactory();
    }


    private void fillSensorEventFactory() {
        sensorEventFactoryMap.put("LightIsOn", new SensorEventLightFactory(LIGHT_ON));
        sensorEventFactoryMap.put("LightIsOff", new SensorEventLightFactory(LIGHT_OFF));
        sensorEventFactoryMap.put("DoorIsOpen", new SensorEventDoorFactory(DOOR_OPEN));
        sensorEventFactoryMap.put("DoorIsClosed", new SensorEventDoorFactory(DOOR_CLOSED));
        sensorEventFactoryMap.put("DoorIsLocked", new SensorEventDoorFactory(DOOR_CLOSED));
        sensorEventFactoryMap.put("DoorIsUnlocked", new SensorEventDoorFactory(DOOR_OPEN));

    }


    private SensorEvent returnComparableEvent(CCSensorEvent event) {
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
