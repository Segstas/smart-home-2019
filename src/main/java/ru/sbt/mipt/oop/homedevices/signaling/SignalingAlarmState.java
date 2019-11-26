package ru.sbt.mipt.oop.homedevices.signaling;

public class SignalingAlarmState extends SignalingState {
    public SignalingAlarmState(Signaling signaling) {
        super(signaling);
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (signaling.compareCodes(code)) {
            signaling.setState(new SignalingDeactivatedState(signaling));
        }
    }

    @Override
    public void enableAlarm() {
    }
}
