package ru.sbt.mipt.oop.homeparts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    @Autowired
    Signaling signaling;

    Collection<Room> rooms;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

   /* public void executeSignalingAction (){
        signaling.activate();
    }*/ ///добавить вызов методов сигнализации

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        action.start(this);
        for (Room room : rooms) {
            room.execute(action);
        }
    }

    public Signaling getSignaling() {
        return signaling;
    }

    public void setSignaling(Signaling signaling) {
        this.signaling = signaling;
    }
}
