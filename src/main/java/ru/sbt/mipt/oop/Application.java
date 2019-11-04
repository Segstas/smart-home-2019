package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.sbt.mipt.oop.events.adapters.AdapterEventHandler;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;

import java.io.IOException;


public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);


    public static void main(String... args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        EventHandler eventHandler =  context.getBean(EventHandler.class);
        eventHandler.work();
    }
}

