package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.LIGHT_OFF;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.LIGHT_ON;

public class LightEventProcessor implements EventProcessor {

    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!(event.getType() == LIGHT_ON || event.getType() == LIGHT_OFF)) return;

        smartHome.execute(object -> {
            if (!(object instanceof Light)) {
                return;
            }
            Light light = (Light) object;
            updateLightState(light, event);

        });

    }

    private void updateLightState(Light light, SensorEvent event) {
        if (light.getId().equals(event.getObjectId())) {
            if (event.getType() == LIGHT_ON) {
                light.setOn(true);
                System.out.println("Light " + light.getId() + " was turned on.");
            } else {
                light.setOn(false);
                System.out.println("Light " + light.getId() + " was turned off.");
            }
        }
    }

}

