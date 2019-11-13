package ru.sbt.mipt.oop.eventprocessors;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.SensorEventSignalling;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static ru.sbt.mipt.oop.eventtypes.SensorEventType.ALARM_DEACTIVATE;

public class SignalingAlarmDeactivateProcessor implements EventProcessor {
    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (!(event.getType() == ALARM_DEACTIVATE)) return;
        smartHome.getSignaling().deactivate(isInstanseOfSignallingEventGetCode(event));
    }

    private String isInstanseOfSignallingEventGetCode(SensorEvent event) {
        return (event instanceof SensorEventSignalling ? ((SensorEventSignalling) event).getCode() : "");
    }
}
