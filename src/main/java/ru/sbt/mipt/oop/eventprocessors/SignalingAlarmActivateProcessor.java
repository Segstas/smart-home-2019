package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventSignalling;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventTypeDoorAndLight.ALARM_ACTIVATE;

public class SignalingAlarmActivateProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!(event.getType() == ALARM_ACTIVATE)) return;
        smartHome.getSignaling().activate(isInstanseOfSignallingEvent(event).getCode());
    }

    private SensorEventSignalling isInstanseOfSignallingEvent(SensorEvent event) {
        return (event instanceof SensorEventSignalling ? ((SensorEventSignalling) event) : null);
    }
}
