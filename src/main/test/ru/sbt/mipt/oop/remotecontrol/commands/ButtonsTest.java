package ru.sbt.mipt.oop.remotecontrol.commands;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import ru.sbt.mipt.oop.homedevices.Door;
import ru.sbt.mipt.oop.homedevices.Light;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingActivatedState;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingAlarmState;
import ru.sbt.mipt.oop.homeparts.Room;
import ru.sbt.mipt.oop.homeparts.SmartHome;
import ru.sbt.mipt.oop.remotecontrol.Button;
import ru.sbt.mipt.oop.remotecontrol.RemoteController;

import java.util.ArrayList;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ButtonsTest {
    private RemoteController remoteController;
    private SmartHome testHome;
    private Door door1;
    private Light light1;
    private Light light2;
    private Collection<Light> lights1;
    private Collection<Light> lights2;

    @Before
    public void setUp() {
        light1 = new Light("1", true);
        light2 = new Light("2", true);
        Light light3 = new Light("3", true);
        Light light4 = new Light("4", true);

        door1 = new Door(true, "1");
        Door door2 = new Door(true, "2");

        lights1 = new ArrayList<>();
        lights1.add(light1);
        lights1.add(light2);
        lights2 = new ArrayList<>();
        lights2.add(light3);
        lights2.add(light4);

        Collection<Door> doors1 = new ArrayList<>();
        doors1.add(door1);
        Collection<Door> doors2 = new ArrayList<>();
        doors2.add(door2);

        Room room1 = new Room(lights1, doors1, "hall");
        Room room2 = new Room(lights2, doors2, "kitchen");


        testHome = new SmartHome();
        testHome.addRoom(room1);
        testHome.addRoom(room2);
        Signaling signaling = new Signaling("1234");
        testHome.setSignaling(signaling);

        ActivateAlarmCommand activateAlarmCommand = new ActivateAlarmCommand(testHome);
        CloseEntranceDoorCommand closeEntranceDoorCommand = new CloseEntranceDoorCommand(testHome);
        EnableSignalingCommand enableSignalingCommand = new EnableSignalingCommand(testHome);
        enableSignalingCommand.setCode("1234");
        TurnOffLightsCommand turnOffLightsCommand = new TurnOffLightsCommand(testHome);
        TurnOnAllLightsCommand turnOnAllLightsCommand = new TurnOnAllLightsCommand(testHome);
        TurnOnLightInHallwayCommand turnOnLightInHallwayCommand = new TurnOnLightInHallwayCommand(testHome);

        remoteController = new RemoteController();
        remoteController.setButton("A", new Button("A"));
        remoteController.setButton("B", new Button("B"));
        remoteController.setButton("C", new Button("C"));
        remoteController.setButton("D", new Button("D"));
        remoteController.setButton("1", new Button("1"));
        remoteController.setButton("2", new Button("2"));
        remoteController.setButton("3", new Button("3"));
        remoteController.setButton("4", new Button("4"));
        remoteController.setCommand("A", activateAlarmCommand);
        remoteController.setCommand("B", closeEntranceDoorCommand);
        remoteController.setCommand("C", enableSignalingCommand);
        remoteController.setCommand("D", turnOffLightsCommand);
        remoteController.setCommand("1", turnOnAllLightsCommand);
        remoteController.setCommand("2", turnOnLightInHallwayCommand);
    }

    @Test
    public void executeButtonATest() {
        remoteController.onButtonPressed("A", "Controller1");
        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingAlarmState.class));
    }

    @Test
    public void executeButtonBTest() {
        remoteController.onButtonPressed("B", "Controller1");
        assertFalse(door1.isOpen());
    }

    @Test
    public void executeButtonCTest() {
        remoteController.onButtonPressed("C", "Controller1");
        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingActivatedState.class));
    }

    @Test
    public void executeButtonDTest() {
        remoteController.onButtonPressed("D", "Controller1");
        for (Light light : lights1) {
            Assertions.assertFalse(light.isOn());
        }
    }

    @Test
    public void executeButton1Test() {
        remoteController.onButtonPressed("1", "Controller1");
        for (Light light : lights1) {
            assertTrue(light.isOn());
        }
    }

    @Test
    public void executeButton2Test() {
        remoteController.onButtonPressed("2", "Controller1");
        assertTrue(light2.isOn());
        assertTrue(light2.isOn());
    }


}