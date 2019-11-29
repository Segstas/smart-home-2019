package ru.sbt.mipt.oop.remotecontrol.commands;

import org.junit.Test;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingActivatedState;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingAlarmState;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.*;

public class EnableSignalingCommandTest {

    @Test
    public void executeTest() {
        SmartHome testHome = new SmartHome();
        Signaling signaling = new Signaling("1234");
        testHome.setSignaling(signaling);
        EnableSignalingCommand enableSignalingCommand = new EnableSignalingCommand(testHome);
        enableSignalingCommand.setCode("1234");
        enableSignalingCommand.execute();
        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingActivatedState.class));
    }
}
