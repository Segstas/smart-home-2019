package ru.sbt.mipt.oop;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);


    public static void main(String... args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        EventHandler eventHandler = context.getBean(EventHandler.class);
        eventHandler.handleEvent();
    }
}

