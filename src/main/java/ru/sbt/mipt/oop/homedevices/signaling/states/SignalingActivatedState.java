package ru.sbt.mipt.oop.homedevices.signaling.states;

import ru.sbt.mipt.oop.homedevices.signaling.Signaling;

public class SignalingActivatedState extends SignalingState {

    SignalingActivatedState(Signaling signaling) {
        super(signaling);
    }

    @Override
    public void activate(String code) {
    }

    @Override
    public void deactivate(String code) {
      if (signaling.codeСomparison(code)) {
          signaling.setState(new SignalingDeactivatedState(signaling));
      } else {
          this.enableAlarm();
      }
    }

    @Override
    public void enableAlarm() {
        signaling.setState(new SignalingAlarmState(signaling));
    }
}
