package ru.sbt.mipt.oop.homedevices.signaling.states;

import ru.sbt.mipt.oop.homedevices.signaling.Signaling;

public class SignalingAlarmState extends SignalingState {
    SignalingAlarmState(Signaling signaling) {
        super(signaling);
    }

    @Override
    public void activate(String code) {

    }

    @Override
    public void deactivate(String code) {
        if (signaling.codeСomparison(code)) {
            signaling.setState(new SignalingDeactivatedState(signaling));
        }
    }

    @Override
    public void enableAlarm() {
    }
}
