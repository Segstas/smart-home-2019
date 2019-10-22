package ru.sbt.mipt.oop;

import ru.sbt.mipt.oop.eventprocessors.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.HallDoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.LightEventProcessor;
import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.EventProduserImplStub;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String... args) throws IOException {
        // считываем состояние дома из файла
        SmartHomeReader smartHomeReader = new SmartHomeReaderJSON();
        SmartHome smartHome = smartHomeReader.read();
        EventProduser eventProduser = new EventProduserImplStub();
        List<EventProcessor> processors =  creareEventProcessorList();

        // начинаем цикл обработки событий
        SensorEvent event = eventProduser.getNextSensorEvent();


        while (event != null){
            System.out.println("Got event: " + event);
            for (EventProcessor processor : processors) {
                processor.process(smartHome,event);
            }
            event = eventProduser.getNextSensorEvent();
        }
    }

    private static List<EventProcessor>  creareEventProcessorList() {
        List<EventProcessor> processors = new ArrayList<>();
        processors.add(new DoorEventProcessor());
        processors.add(new LightEventProcessor());
        processors.add(new HallDoorEventProcessor());
        return processors;
    }
}

