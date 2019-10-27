package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.eventprocessors.basic.DoorEventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventLight;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_CLOSED;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_OPEN;

public class DoorEventProcessorTest {
    private SmartHome testHome;
    private EventProcessor lightEventProcessor = new DoorEventProcessor();

    @org.junit.jupiter.api.Test
    void openDoorClosingTest() {
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        doors1.add(door2);
        Collection<Light> lights1 = new ArrayList<>();
        Room room1 = new Room(lights1, doors1, "hall");
        testHome = new SmartHome();
        testHome.addRoom(room1);
        SensorEvent lightOffEvent = new SensorEventLight(DOOR_CLOSED, "1");
        lightEventProcessor.process(testHome, lightOffEvent);
        assertFalse(door1.isOpen());
    }

    @org.junit.jupiter.api.Test
    void closedDoorOpeningTesr() {
        Door door1 = new Door(true, "1");
        Door door2 = new Door(false, "2");
        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        doors1.add(door2);
        Collection<Light> lights1 = new ArrayList<>();
        Room room1 = new Room(lights1, doors1, "hall");
        testHome = new SmartHome();
        testHome.addRoom(room1);
        SensorEvent lightOffEvent = new SensorEventLight(DOOR_OPEN, "2");
        lightEventProcessor.process(testHome, lightOffEvent);
        assertTrue(door2.isOpen());

    }

}