package ru.sbt.mipt.oop.homeparts;

import ru.sbt.mipt.oop.Action;
import ru.sbt.mipt.oop.Actionable;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;

import java.util.Collection;

public class Room implements Actionable {
    private Collection<Light> lights;
    private Collection<Door> doors;
    private String name;

    public Room(Collection<Light> lights, Collection<Door> doors, String name) {
        this.lights = lights;
        this.doors = doors;
        this.name = name;
    }


    public Collection<Light> getLights() {
        return lights;
    }

    public Collection<Door> getDoors() {
        return doors;
    }

    public String getName() {
        return name;
    }

    public boolean containsRightDoor(String id) {
        for (Door door : doors) {
            if (door.getId() == id) return true;
        }
        return false;
    }

    @Override
    public void execute(Action action) {
        action.start(this);
        for (Light light : lights) {
            light.execute(action);
        }
        for (Door door : doors) {
            door.execute(action);
        }
    }
}
