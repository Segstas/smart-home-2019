package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class TurnOnLightInHallwayCommand implements Command {
    private final SmartHome smartHome;

    public TurnOnLightInHallwayCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute((actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;

            room.execute((lightOnActionable) -> {
                if (!(lightOnActionable instanceof Light)) return;
                ((Light) lightOnActionable).setOn(true);
            });
        });
    }
}
