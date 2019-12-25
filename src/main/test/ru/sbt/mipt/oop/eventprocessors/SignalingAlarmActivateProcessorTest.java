package ru.sbt.mipt.oop.eventprocessors;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.mipt.oop.events.SensorEventSignalling;
import ru.sbt.mipt.oop.homedevices.signaling.Signaling;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingActivatedState;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingAlarmState;
import ru.sbt.mipt.oop.homedevices.signaling.SignalingDeactivatedState;
import ru.sbt.mipt.oop.homeparts.SmartHome;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static ru.sbt.mipt.oop.eventtypes.SensorEventType.ALARM_ACTIVATE;

public class SignalingAlarmActivateProcessorTest {
    SmartHome testHome;
    SignalingAlarmActivateProcessor signalingAlarmActivateProcessor;
    Signaling signaling;

    @Before
    public void setUp() {
        testHome = new SmartHome();
        signaling = new Signaling("1234");
        signalingAlarmActivateProcessor = new SignalingAlarmActivateProcessor();
    }

    @Test
    public void signallingActivateDeactivatedWithWrongCodeTest() {
        signaling.setState(new SignalingDeactivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_ACTIVATE, "0", "1234");
        signalingAlarmActivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingActivatedState.class));
    }

    @Test
    public void signallingActivateActivatedWithRightCodeTest() {
        signaling.setState(new SignalingActivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_ACTIVATE, "0", "1234");
        signalingAlarmActivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingActivatedState.class));
    }

    @Test
    public void signallingActivateActivatedWithWrongCodeTest() {
        signaling.setState(new SignalingActivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_ACTIVATE, "0", "wrong");
        signalingAlarmActivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingActivatedState.class));
    }

    @Test
    public void signallingActivateFromAlarmWithRightCodeTest() {
        signaling.setState(new SignalingAlarmState(signaling));
        testHome.setSignaling(signaling);

        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_ACTIVATE, "0", "1234");
        signalingAlarmActivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingAlarmState.class));
    }

    @Test
    public void signallingActivateFromAlarmWithWrongCodeTest() {
        signaling.setState(new SignalingAlarmState(signaling));
        testHome.setSignaling(signaling);

        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_ACTIVATE, "0", "wrong");
        signalingAlarmActivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingAlarmState.class));
    }
}