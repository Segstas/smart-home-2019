package ru.sbt.mipt.oop.homedevices.signaling;

import ru.sbt.mipt.oop.homedevices.signaling.states.SignalingDeactivatedState;
import ru.sbt.mipt.oop.homedevices.signaling.states.SignalingState;

public class Signaling {
    private SignalingState state;
    private final String code;

    public Signaling(String code) {
        this.state = new SignalingDeactivatedState(this);
        this.code = code;
    }


    public boolean codeСomparison(String сomparisСode) {
       return сomparisСode == code;
    }

    public void setState(SignalingState state) {
        this.state = state;
    }

    public SignalingState getState() {
        return state;
    }

    public void activate(String code) {
        state.activate(code);
    }

    public void deactivate(String code) {
        state.deactivate(code);
    }

    public void triggerAlert() {
        state.enableAlarm();
    }
}
