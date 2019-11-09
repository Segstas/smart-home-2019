package ru.sbt.mipt.oop.homedevices.signaling;

public class SignalingActivatedState extends SignalingState {

    public SignalingActivatedState(Signaling signaling) {
        super(signaling);
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
        if (signaling.compareCodes(code)) {
            signaling.setState(new SignalingDeactivatedState(signaling));
        } else {
            this.enableAlarm();
        }
    }

    @Override
    public void enableAlarm() {
        signaling.setState(new SignalingAlarmState(signaling));
    }
}
