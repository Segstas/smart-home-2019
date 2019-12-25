package ru.sbt.mipt.oop.remotecontrol.commands;

import ru.sbt.mipt.oop.eventprocessors.basic.HallDoorEventProcessor;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Command;

public class CloseEntranceDoorCommand implements Command {
    private SmartHome smartHome;

    public CloseEntranceDoorCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute((actionable) -> {
            if (!(actionable instanceof Room)) return;
            Room room = (Room) actionable;
            if (!(room.getName().equals("hall"))) return;
            room.execute((doorCloseActionable) -> {
                if (!(doorCloseActionable instanceof Door)) return;
                ((Door) doorCloseActionable).setOpen(false);
            });
        });
    }
}
