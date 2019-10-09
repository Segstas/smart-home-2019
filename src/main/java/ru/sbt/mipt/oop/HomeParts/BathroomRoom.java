package ru.sbt.mipt.oop.HomeParts;

import ru.sbt.mipt.oop.HomeDevices.Door;
import ru.sbt.mipt.oop.HomeDevices.Light;

import java.util.Collection;

public class BathroomRoom extends Room {
    public BathroomRoom(Collection<Light> lights, Collection<Door> doors, String name) {
        super(lights, doors, name);
    }
}
