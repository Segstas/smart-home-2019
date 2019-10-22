package ru.sbt.mipt.oop.eventprocessors;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventDoor;
import ru.sbt.mipt.oop.events.SensorEventLight;
import ru.sbt.mipt.oop.eventtypes.SensorEventType;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.*;

public class DoorEventProcessorTest {
    private SmartHome testHome;
    private EventProcessor lightEventProcessor = new DoorEventProcessor();

    @org.junit.jupiter.api.Test

    void openDoorClosingTest() {
        Door door1 = new Door(true,"1");
        Door door2 = new Door(false, "2");
        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        doors1.add(door2);
        Collection<Light> lights1 = new ArrayList<>();
        Room room1 = new Room(lights1,doors1,"hall");
        testHome = new SmartHome();
        testHome.addRoom(room1);
        SensorEvent lightOffEvent = new SensorEventLight(DOOR_CLOSED,"1");
        lightEventProcessor.process(testHome,lightOffEvent);
        assertFalse(door1.isOpen());
    }

    @org.junit.jupiter.api.Test
    void closedDoorOpeningTesr() {
        Door door1 = new Door(true,"1");
        Door door2 = new Door(false, "2");
        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        doors1.add(door2);
        Collection<Light> lights1 = new ArrayList<>();
        Room room1 = new Room(lights1,doors1,"hall");
        testHome = new SmartHome();
        testHome.addRoom(room1);
        SensorEvent lightOffEvent = new SensorEventLight(DOOR_OPEN,"2");
        lightEventProcessor.process(testHome,lightOffEvent);
        assertTrue(door2.isOpen());

    }


}