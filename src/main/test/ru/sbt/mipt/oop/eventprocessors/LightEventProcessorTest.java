package ru.sbt.mipt.oop.eventprocessors;


import org.junit.jupiter.api.Test;
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
import static ru.sbt.mipt.oop.eventtypes.SensorEventType.LIGHT_OFF;
import static ru.sbt.mipt.oop.eventtypes.SensorEventType.LIGHT_ON;

class LightEventProcessorTest {
    private SmartHome testHome;
    private EventProcessor lightEventProcessor = new LightEventProcessor();

    @Test
    void workingLightOffTest() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Collection<Light> lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        Collection<Door> doors1 = new ArrayList<>();
        Room room1 = new Room(lights1, doors1, "hall");
        testHome = new SmartHome();
        testHome.addRoom(room1);
        SensorEvent lightOffEvent = new SensorEventLight(LIGHT_OFF, "1");
        lightEventProcessor.process(testHome, lightOffEvent);
        assertFalse(light1.isOn());
    }

    @Test
    void notWorkingLightOnTest() {
        Light light1 = new Light("1", true);
        Light light2 = new Light("2", false);
        Collection<Light> lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        Collection<Door> doors1 = new ArrayList<>();
        Room room1 = new Room(lights1, doors1, "hall");
        testHome = new SmartHome();
        testHome.addRoom(room1);
        SensorEvent lightOffEvent = new SensorEventLight(LIGHT_ON, "2");
        lightEventProcessor.process(testHome, lightOffEvent);
        assertTrue(light2.isOn());

    }


}