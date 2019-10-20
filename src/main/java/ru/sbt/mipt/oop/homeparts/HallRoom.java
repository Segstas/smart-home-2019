package ru.sbt.mipt.oop.homeparts;

import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;

import java.util.Collection;

public class HallRoom extends Room {
    public HallRoom(Collection<Light> lights, Collection<Door> doors, String name) {
        super(lights, doors, name);
    }
}
