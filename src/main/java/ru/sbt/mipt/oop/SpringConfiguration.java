package ru.sbt.mipt.oop;

import com.coolcompany.smarthome.events.EventHandler;
import com.coolcompany.smarthome.events.SensorEventsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rc.RemoteControlRegistry;
import ru.sbt.mipt.oop.commandworkers.CommandSender;
import ru.sbt.mipt.oop.eventprocessors.EventProcessor;
import ru.sbt.mipt.oop.eventprocessors.SignalingAlarmActivateProcessor;
import ru.sbt.mipt.oop.eventprocessors.SignalingAlarmDeactivateProcessor;
import ru.sbt.mipt.oop.eventprocessors.basic.DoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.basic.HallDoorEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.basic.LightEventProcessor;
import ru.sbt.mipt.oop.eventprocessors.decorators.EventProcessorDecorator;
import ru.sbt.mipt.oop.events.adapters.AdapterEventHandler;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReader;
import ru.sbt.mipt.oop.iohelpers.SmartHomeReaderJSON;
import ru.sbt.mipt.oop.remotecontrol.Button;
import ru.sbt.mipt.oop.remotecontrol.RemoteController;
import ru.sbt.mipt.oop.remotecontrol.commands.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
public class SpringConfiguration {

    @Bean
    EventProcessor doorEventProcessor() {
        return new EventProcessorDecorator(new DoorEventProcessor());
    }

    @Bean
    EventProcessor lightEventProcessor() {
        return new EventProcessorDecorator(new LightEventProcessor());
    }

    @Bean
    EventProcessor hallDoorEventProcessor() {
        return new EventProcessorDecorator(new HallDoorEventProcessor(new CommandSender()));
    }

    @Bean
    EventProcessor signalingAlarmActivateProcessor() {
        return new EventProcessorDecorator(new SignalingAlarmActivateProcessor());
    }

    @Bean
    EventProcessor signalingAlarmDeactivateProcessor() {
        return new EventProcessorDecorator(new SignalingAlarmDeactivateProcessor());
    }

    @Bean
    Collection<EventProcessor> eventProcessors() {
        List<EventProcessor> processors = new ArrayList<>();
        processors.add(doorEventProcessor());
        processors.add(lightEventProcessor());
        processors.add(hallDoorEventProcessor());
        processors.add(signalingAlarmActivateProcessor());
        processors.add(signalingAlarmDeactivateProcessor());
        return processors;
    }

    @Bean
    SensorEventsManager sensorEventsManager() {
        SensorEventsManager sensorEventsManager = new SensorEventsManager();
        sensorEventsManager.registerEventHandler(eventHandler());
        return sensorEventsManager;
    }

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
        return smartHomeReader().readSmartHome();
    }

    @Bean
    EventHandler eventHandler() {
        return new AdapterEventHandler();
    }

    @Bean
    RemoteControlRegistry remoteControlRegistry() {
        RemoteControlRegistry remoteControlRegistry = new RemoteControlRegistry();
        remoteControlRegistry.registerRemoteControl(remoteController(), "Controller1");
        return remoteControlRegistry;
    }

    @Bean
    RemoteController remoteController() {
        RemoteController remoteController = new RemoteController();
        remoteController.setButton("A", new Button("A"));
        remoteController.setButton("B", new Button("B"));
        remoteController.setButton("C", new Button("C"));
        remoteController.setButton("D", new Button("D"));
        remoteController.setButton("1", new Button("1"));
        remoteController.setButton("2", new Button("2"));
        remoteController.setButton("3", new Button("3"));
        remoteController.setButton("4", new Button("4"));
        remoteController.setCommand("A" ,activateAlarmCommand());
        remoteController.setCommand("B",closeEntranceDoorCommand());
        remoteController.setCommand("C", enableSignalingCommand());
        remoteController.setCommand("D", turnOffLightsCommand());
        remoteController.setCommand("1", turnOnAllLightsCommand());
        remoteController.setCommand("2", turnOnLightInHallwayCommand());
        return new RemoteController();
    }

    @Bean
    ActivateAlarmCommand activateAlarmCommand() {
        return new ActivateAlarmCommand(smartHome());
    }

    @Bean
    CloseEntranceDoorCommand closeEntranceDoorCommand() {
        return new CloseEntranceDoorCommand(smartHome());
    }

    @Bean
    EnableSignalingCommand enableSignalingCommand() {
        return new EnableSignalingCommand(smartHome());
    }

    @Bean
    TurnOnLightInHallwayCommand turnOnLightInHallwayCommand() {
        return new TurnOnLightInHallwayCommand(smartHome());
    }

    @Bean
    TurnOnAllLightsCommand turnOnAllLightsCommand() {
        return new TurnOnAllLightsCommand(smartHome());
    }

    @Bean
    TurnOffLightsCommand turnOffLightsCommand() {
        return new TurnOffLightsCommand(smartHome());
    }
}
