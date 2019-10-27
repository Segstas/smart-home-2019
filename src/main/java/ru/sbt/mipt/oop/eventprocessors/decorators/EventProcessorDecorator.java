package ru.sbt.mipt.oop.eventprocessors.decorators;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homedevices.signaling.states.SignalingActivatedState;
import ru.sbt.mipt.oop.homedevices.signaling.states.SignalingAlarmState;
import ru.sbt.mipt.oop.homeparts.SmartHome;


public class EventProcessorDecorator implements EventProcessor {
    private EventProcessor eventProcessor;

    public EventProcessorDecorator(EventProcessor source) {
        this.eventProcessor = source;
    }

    @Override
    public void process(SmartHome smartHome, SensorEvent event) {
        if (smartHome.getSignaling().getState() instanceof SignalingActivatedState) {
            smartHome.getSignaling().triggerAlert();
            System.out.println("Sending sms");
        }

        if (smartHome.getSignaling().getState() instanceof SignalingAlarmState) {
            System.out.println("Sending sms");
            return;
        }
        eventProcessor.process(smartHome, event);
    }
}
