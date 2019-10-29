package ru.sbt.mipt.oop.events.adapters;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.SensorEventsManager;
import ru.sbt.mipt.oop.EventHandler;
import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventDoor;
import ru.sbt.mipt.oop.events.SensorEventLight;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventType.*;

public class AdapterEventHandler extends EventHandler {
    SensorEventsManager sensorEventsManager = new SensorEventsManager();
    SensorEvent sensorEvent;

    public AdapterEventHandler(EventProduser eventProduser, SmartHome smartHome) {
        super(eventProduser, smartHome);
    }

    public AdapterEventHandler(SmartHome smartHome) {
        super(smartHome);
    }


    public void work() {
        sensorEventsManager.registerEventHandler(event -> {
            System.out.println(returnComparableEvent(event));
        });
        sensorEventsManager.start();
    }

    private SensorEvent returnComparableEvent(CCSensorEvent event) {
        switch (event.getEventType()) {
            case "LightIsOn":
                sensorEvent = new SensorEventLight(LIGHT_ON, event.getObjectId());
                break;
            case "LightIsOff":
                sensorEvent = new SensorEventLight(LIGHT_OFF, event.getObjectId());
                break;
            case "DoorIsOpen":
                sensorEvent = new SensorEventDoor(DOOR_OPEN, event.getObjectId());
                break;
            case "DoorIsClosed":
                sensorEvent = new SensorEventDoor(DOOR_CLOSED, event.getObjectId());
                break;
            case "DoorIsLocked":
                sensorEvent = new SensorEventDoor(DOOR_CLOSED, event.getObjectId());
                break;
            case "DoorIsUnlocked":
                sensorEvent = new SensorEventDoor(DOOR_OPEN, event.getObjectId());
                break;
        }
        return sensorEvent;
    }
}
