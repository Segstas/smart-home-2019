package ru.sbt.mipt.oop.homedevices.signaling;

import ru.sbt.mipt.oop.homedevices.signaling.Signaling;

public abstract class SignalingState {
    Signaling signaling;

    SignalingState(Signaling signaling) {
        this.signaling = signaling;
    }

    public abstract void activate(String code);

    public abstract void deactivate(String code);

    public abstract void enableAlarm();
}
