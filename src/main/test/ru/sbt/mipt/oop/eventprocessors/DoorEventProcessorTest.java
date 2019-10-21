package ru.sbt.mipt.oop.eventprocessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventDoor;
import ru.sbt.mipt.oop.eventtypes.SensorEventType;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_CLOSED;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.DOOR_OPEN;

public class DoorEventProcessorTest {


    private SmartHome smartHome;
    private EventProcessor processor;
    private List<String> openDoors;
    private List<String> closedDoors;
    private Random randomGenerator;

    @Before
    public void setUp() throws IOException {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        smartHome = smartHomeReader.read();
        openDoors = getDoors(true);
        closedDoors = getDoors(false);
        randomGenerator = new Random();
    }

    @Test
    void doorsClosingTest() {
        int randInt = randomGenerator.nextInt(closedDoors.size());
        String doorId = closedDoors.get(randInt);
        SensorEvent event = new SensorEventDoor(DOOR_OPEN, doorId);

        processor.process(smartHome,event);
        closedDoors.remove(doorId);
        openDoors.add(doorId);

        checkDoorsCondition(openDoors, true);
        checkDoorsCondition(closedDoors, false);
    }

    @Test
    void testOpenDoorCloses() {
        int randInt = randomGenerator.nextInt(openDoors.size());
        String doorId = openDoors.get(randInt);
        SensorEvent event = new SensorEventDoor(DOOR_CLOSED, doorId);

        processor.process(smartHome,event);
        openDoors.remove(doorId);
        closedDoors.add(doorId);

        checkDoorsCondition(openDoors, true);
        checkDoorsCondition(closedDoors, false);
    }

    private List<String> getDoors(boolean condition) {
        List<String> doors = new ArrayList<>();
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (door.isOpen() == condition) {
                doors.add(door.getId());
            }
        });
        return doors;
    }

    private void checkDoorsCondition(List<String> doorIds, boolean condition) {
        smartHome.execute((Actionable actionable) -> {
            if (!(actionable instanceof Door)) return;
            Door door = (Door) actionable;
            if (!(doorIds.contains(door.getId()))) return;
            assertEquals(door.isOpen(), condition);
        });
    }

}