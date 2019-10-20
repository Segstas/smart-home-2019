package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.EventProduserImplStub;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;

import java.io.IOException;

public class Application {
    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        EventProduser eventProduser = new EventProduserImplStub();

        // начинаем цикл обработки событий
        SensorEvent event = eventProduser.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            event.performEvent(smartHome);
            event = eventProduser.getNextSensorEvent();
        }
    }
}
