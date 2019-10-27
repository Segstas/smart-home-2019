package ru.sbt.mipt.oop.homedevices.signaling.states;

import ru.sbt.mipt.oop.homedevices.signaling.Signaling;

public class SignalingDeactivatedState extends SignalingState {
    public SignalingDeactivatedState(Signaling signaling) {
        super(signaling);
    }

    @Override
    public void activate(String code) {
        if (signaling.codeСomparison(code)) {
            signaling.setState(new SignalingActivatedState(signaling));
        }
    }

    @Override
    public void deactivate(String code) {

    }

    @Override
    public void enableAlarm() {
    }
}
