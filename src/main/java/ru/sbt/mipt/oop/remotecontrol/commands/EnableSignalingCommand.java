package ru.sbt.mipt.oop.remotecontrol.commands;


import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class EnableSignalingCommand implements Command {
    private SmartHome smartHome;

    public void setCode(String code) {
        this.code = code;
    }

    private String code;

    public EnableSignalingCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        if (smartHome.getSignaling() != null)
            smartHome.getSignaling().activate(this.code);
    }
}
