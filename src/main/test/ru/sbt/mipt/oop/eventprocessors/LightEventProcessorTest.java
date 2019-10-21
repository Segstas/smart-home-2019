package ru.sbt.mipt.oop.eventprocessors;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventLight;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.LIGHT_OFF;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.LIGHT_ON;

class LightEventProcessorTest {
    private SmartHome smartHome;
    private EventProcessor processor;
    private List<String> turnedOnLights;
    private List<String> turnedOffLights;
    private Random randomGenerator;

    @BeforeEach
    void setUp() throws IOException {
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        smartHome = smartHomeReader.read();
        turnedOnLights = getLights(true);
        turnedOffLights = getLights(false);
        randomGenerator = new Random();
    }

    @Test
    void testTurnedOffLightTurnsOn() {
        int randInt = randomGenerator.nextInt(turnedOffLights.size());
        String lightId = turnedOffLights.get(randInt);
        SensorEvent event = new SensorEventLight(LIGHT_ON, lightId);

        processor.process(smartHome, event);
        turnedOffLights.remove(lightId);
        turnedOnLights.add(lightId);

        checkLightsCondition(turnedOnLights, true);
        checkLightsCondition(turnedOffLights, false);
    }
    
    private List<String> getLights(boolean condition) {
        List<String> lights = new ArrayList<>();
        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            if (light.isOn() == condition) {
                lights.add(light.getId());
            }
        });
        return lights;
    }

    private void checkLightsCondition(List<String> lightIds, boolean condition) {
        smartHome.execute(object -> {
            if (!(object instanceof Light)) return;
            Light light = (Light) object;
            if (!(lightIds.contains(light.getId()))) return;
            assertEquals(light.isOn(), condition);
        });
    }


}