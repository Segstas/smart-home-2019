package ru.sbt.mipt.oop.homedevices.signaling;

public class SignalingDeactivatedState extends SignalingState {
    public SignalingDeactivatedState(Signaling signaling) {
        super(signaling);
    }

    @Override
    public void activate(String code) {
        signaling.setState(new SignalingActivatedState(signaling));
        signaling.setCode(code);
    }

    @Override
    public void deactivate(String code) {

    }

    @Override
    public void enableAlarm() {
        signaling.setState(new SignalingAlarmState(signaling));
    }
}
