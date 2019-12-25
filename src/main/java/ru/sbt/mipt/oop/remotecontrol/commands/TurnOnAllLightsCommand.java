package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class TurnOnAllLightsCommand implements Command {
    private SmartHome smartHome;


    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        this.smartHome.execute((actionable) -> {
            if (!(actionable instanceof Light)) return;
            ((Light) actionable).setOn(true);
        });
    }

}
