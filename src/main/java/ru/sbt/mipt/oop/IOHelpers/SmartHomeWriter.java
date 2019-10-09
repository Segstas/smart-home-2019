package ru.sbt.mipt.oop.IOHelpers;

import ru.sbt.mipt.oop.HomeParts.SmartHome;

import java.io.IOException;

public interface SmartHomeWriter {
    public void write(SmartHome smartHome) throws IOException;
}
