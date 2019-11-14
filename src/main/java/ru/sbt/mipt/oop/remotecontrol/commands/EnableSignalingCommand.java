package ru.sbt.mipt.oop.remotecontrol.commands;


import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class EnableSignalingCommand implements Command {
    private SmartHome smartHome;
    private String code;

    public EnableSignalingCommand(SmartHome smartHome, String code) {
        this.smartHome = smartHome;
        this.code = code;
    }

    @Override
    public void execute() {
        if (smartHome.getSignaling() != null)
            smartHome.getSignaling().activate(this.code);
    }
}
