package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.EventProduserImplStub;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;


public class Application {
    public static void main(String... args) {
        // считываем состояние дома из файла
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();

        SmartHome smartHome = smartHomeReader.readSmartHome();
        smartHome.setSignaling(new Signaling("1"));
        EventProduser eventProduser = new EventProduserImplStub();

        EventHandler eventHandler = new EventHandler(eventProduser, smartHome);
        eventHandler.handleEvent();

    }

}

