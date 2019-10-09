package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.HomeDevices.Door;
import ru.sbt.mipt.oop.HomeDevices.Light;
import ru.sbt.mipt.oop.HomeParts.*;
import ru.sbt.mipt.oop.IOHelpers.SmartHomeWriter;
import ru.sbt.mipt.oop.IOHelpers.SmartHomeWriterJSON;

import java.io.IOException;
import java.util.Arrays;

public class HomeBuilder {
    SmartHomeWriter smartHomeWriter;

    public void main(String[] args) throws IOException {
        Room kitchen = new KitchenRoom(Arrays.asList(new Light("1", false), new Light("2", true)),
                Arrays.asList(new Door(false, "1")),
                "kitchen");
        Room bathroom = new BathroomRoom(Arrays.asList(new Light("3", true)),
                Arrays.asList(new Door(false, "2")),
                "bathroom");
        Room bedroom = new BathroomRoom(Arrays.asList(new Light("4", false), new Light("5", false), new Light("6", false)),
                Arrays.asList(new Door(true, "3")),
                "bedroom");
        Room hall = new HallRoom(Arrays.asList(new Light("7", false), new Light("8", false), new Light("9", false)),
                Arrays.asList(new Door(false, "4")),
                "hall");
        SmartHome smartHome = new SmartHome(Arrays.asList(kitchen, bathroom, bedroom, hall));

        smartHomeWriter = new SmartHomeWriterJSON();
        smartHomeWriter.write(smartHome);
    }

}
