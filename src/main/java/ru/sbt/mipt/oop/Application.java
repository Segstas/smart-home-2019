package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.Events.SensorEvent;
import ru.sbt.mipt.oop.HomeParts.SmartHome;
import ru.sbt.mipt.oop.IOHelpers.SmartHomeReader;
import ru.sbt.mipt.oop.IOHelpers.SmartHomeReaderJSON;

import java.io.IOException;

import static ru.sbt.mipt.oop.Events.EventProduser.getNextSensorEvent;

public class Application {
    SmartHomeReader smartHomeReader;
    SmartHome smartHome;

    public void main(String... args) throws IOException {
        // считываем состояние дома из файла
        this.smartHomeReader = new SmartHomeReaderJSON();
        this.smartHome = smartHomeReader.read();

        // начинаем цикл обработки событий
        SensorEvent event = getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            event.performEvent(smartHome);
            event = getNextSensorEvent();
        }
    }


}
