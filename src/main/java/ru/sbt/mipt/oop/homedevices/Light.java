package ru.sbt.mipt.oop.homedevices;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;

public class Light implements Actionable {
    private final String id;
    private boolean isOn;

    public Light(String id, boolean isOn) {
        this.id = id;
        this.isOn = isOn;
    }

    public Light(String id) {
        this.id = id;
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    public String getId() {
        return id;
    }

    @Override
    public void execute(Action action) {
        action.start(this);

    }
}
