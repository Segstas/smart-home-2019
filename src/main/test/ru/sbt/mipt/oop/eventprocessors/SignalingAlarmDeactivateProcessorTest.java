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
import static ru.sbt.mipt.oop.eventtypes.SensorEventType.ALARM_DEACTIVATE;

public class SignalingAlarmDeactivateProcessorTest {
    SmartHome testHome;
    SignalingAlarmDeactivateProcessor signalingAlarmDeactivateProcessor;
    Signaling signaling;

    @Before
    public void setUp() {
        testHome = new SmartHome();
        signaling = new Signaling("1234");
        signalingAlarmDeactivateProcessor = new SignalingAlarmDeactivateProcessor();
    }

    @Test
    public void signallingDeactivateDeactovatedWithRightCodeTest() {
        signaling.setState(new SignalingDeactivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_DEACTIVATE, "0", "1234");
        signalingAlarmDeactivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingDeactivatedState.class));
    }

    @Test
    public void signallingDeactivateDeactovatedWithWrongCodeTest() {
        signaling.setState(new SignalingDeactivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_DEACTIVATE, "0", "wrong");
        signalingAlarmDeactivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingDeactivatedState.class));
    }

    @Test
    public void signallingDeactivateActivatedWithRightCodeTest() {
        signaling.setState(new SignalingActivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_DEACTIVATE, "0", "1234");
        signalingAlarmDeactivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingDeactivatedState.class));
    }

    @Test
    public void signallingDeactivateActivatedWithWrongCodeTest() {
        signaling.setState(new SignalingActivatedState(signaling));
        testHome.setSignaling(signaling);
        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_DEACTIVATE, "0", "wrong");
        signalingAlarmDeactivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingAlarmState.class));
    }

    @Test
    public void signallingDeactivateFromAlarmWithRightCodeTest() {
        signaling.setState(new SignalingAlarmState(signaling));
        testHome.setSignaling(signaling);

        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_DEACTIVATE, "0", "1234");
        signalingAlarmDeactivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingDeactivatedState.class));
    }

    @Test
    public void signallingDectivateFromAlarmWithWrongCodeTest() {
        signaling.setState(new SignalingAlarmState(signaling));
        testHome.setSignaling(signaling);

        SensorEventSignalling sensorEventSignalling = new SensorEventSignalling(ALARM_DEACTIVATE, "0", "wrong");
        signalingAlarmDeactivateProcessor.process(testHome, sensorEventSignalling);

        assertThat(testHome.getSignaling().getState(), instanceOf(SignalingAlarmState.class));
    }
}