package ru.sbt.mipt.oop.homeparts;

import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;

import java.util.Collection;

public class BathroomRoom extends Room {
    public BathroomRoom(Collection<Light> lights, Collection<Door> doors, String name) {
        super(lights, doors, name);
    }
}
