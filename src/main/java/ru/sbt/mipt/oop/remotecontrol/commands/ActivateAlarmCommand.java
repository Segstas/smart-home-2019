package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class ActivateAlarmCommand implements Command {

    private SmartHome smartHome;

    ActivateAlarmCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }


    @Override
    public void execute() {
        this.smartHome.getSignaling().triggerAlert();
    }
}
