package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventDoor;
import ru.sbt.mipt.oop.events.SensorEventSignalling;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.LIGHT_OFF;
import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.LIGHT_ON;

public class SignalingAlarmActivateProcessor implements EventProcessor{
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!(event.getType() == ALARM_ACTIVATE)) return;
        smartHome.signaling.activate(isInstanseOfSignallingEvent(event).getCode());
    }

    private SensorEventSignalling isInstanseOfSignallingEvent(SensorEvent event) {
        return  (event instanceof SensorEventSignalling ? ((SensorEventSignalling) event) : null);
    }
}
