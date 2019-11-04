package ru.sbt.mipt.oop;

import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.sbt.mipt.oop.events.EventProduser;
import ru.sbt.mipt.oop.events.adapters.AdapterEventHandler;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;

@Configuration
public class SpringConfiguration {


    @Bean
    SmartHomeReader smartHomeReader() {
        return new SmartHomeReaderJSON();
    }
    @Bean
    Signaling signaling() {
        return new Signaling("1");
    }

    @Bean
    SmartHome smartHome() {
        return  smartHomeReader().readSmartHome();
    }

     @Bean
    EventHandler eventHandler() {
        return new AdapterEventHandler(smartHome());
    }
    }
