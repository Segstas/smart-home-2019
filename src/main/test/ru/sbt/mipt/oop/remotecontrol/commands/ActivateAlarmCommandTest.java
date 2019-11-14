package ru.sbt.mipt.oop.remotecontrol.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homedevices.signaling.states.SignalingActivatedState;
import ru.sbt.mipt.oop.homedevices.signaling.states.SignalingAlarmState;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class ActivateAlarmCommandTest {

    @Test
    public void executeTest() {
        SmartHome testHome = new SmartHome();
        Signaling signaling = new Signaling("1234");
        testHome.setSignaling(signaling);
        ActivateAlarmCommand activateAlarmCommand = new ActivateAlarmCommand(testHome);
        activateAlarmCommand.execute();
        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingAlarmState.class));
    }
}