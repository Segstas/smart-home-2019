package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;


public class Application {
    private static final Logger logger = LogManager.getLogger(Application.class);


    public static void main(String... args) throws IOException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        EventHandler eventHandler =  context.getBean(EventHandler.class);
        eventHandler.handleEvent();
    }
}

