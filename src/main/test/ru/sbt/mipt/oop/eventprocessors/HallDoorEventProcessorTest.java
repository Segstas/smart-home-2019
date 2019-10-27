package ru.sbt.mipt.oop.eventprocessors;

import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.eventprocessors.basic.HallDoorEventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventLight;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_CLOSED;

public class HallDoorEventProcessorTest {
    private SmartHome testHome;
    private EventProcessor hallDoorEventProcessor = new HallDoorEventProcessor();

    @Test
    void hallDoorClosingEventTest() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Light light4 = new Light("4", true);

        Door door1 = new Door(true, "1");
        Door door2 = new Door(true, "2");

        Collection<Light> lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        Collection<Light> lights2 = new ArrayList<>();
        lights2.add(light3);
        lights2.add(light4);

        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        Collection<Door> doors2 = new ArrayList<>();
        doors2.add(door2);

        Room room1 = new Room(lights1, doors1, "hall");
        Room room2 = new Room(lights2, doors2, "kitchen");

        testHome = new SmartHome();
        testHome.addRoom(room1);
        testHome.addRoom(room2);

        SensorEvent lightOffEvent = new SensorEventLight(DOOR_CLOSED, "1");
        hallDoorEventProcessor.process(testHome, lightOffEvent);

        for (Light light : lights1) {
            assertFalse(light.isOn());
        }
    }

    @Test
    void notHallDoorClosingEventTest() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Light light4 = new Light("4", true);

        Door door1 = new Door(true, "1");
        Door door2 = new Door(true, "2");

        Collection<Light> lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);


        Collection<Light> lights2 = new ArrayList<>();
        lights2.add(light3);
        lights2.add(light4);

        Light light3Test = new Light(light3.getId(), light3.isOn());
        Light light4Test = new Light(light4.getId(), light4.isOn());

        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        Collection<Door> doors2 = new ArrayList<>();
        doors2.add(door2);

        Room room1 = new Room(lights1, doors1, "kitchen");
        Room room2 = new Room(lights2, doors2, "hall");

        testHome = new SmartHome();
        testHome.addRoom(room1);
        testHome.addRoom(room2);

        SensorEvent lightOffEvent = new SensorEventLight(DOOR_CLOSED, "3");
        hallDoorEventProcessor.process(testHome, lightOffEvent);

        assertEquals(light3.isOn(), light3Test.isOn());
        assertEquals(light4.isOn(), light4Test.isOn());

    }
}