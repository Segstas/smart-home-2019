package ru.sbt.mipt.oop.homedevices.signaling;

public class Signaling {


    private String code;
    private String id;
    private SignalingState state;

    public Signaling(String code) {
        this.id = "Signaling";
        this.state = new SignalingDeactivatedState(this);
        this.code = code;
    }


    public boolean compareCodes(String receivedCode) {
        return receivedCode == code;
    }

    public SignalingState getState() {
        return state;
    }

    public void setState(SignalingState state) {
        this.state = state;
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

    void setCode(String code) {
        this.code = code;
    }
}
