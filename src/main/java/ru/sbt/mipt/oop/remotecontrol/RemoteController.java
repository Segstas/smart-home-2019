package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;

public class RemoteController implements RemoteControl {

    Button buttonA = new Button("A");
    Button buttonB = new Button("B");
    Button buttonC = new Button("C");
    Button buttonD = new Button("D");

    Button button1 = new Button("1");
    Button button2 = new Button("2");
    Button button3 = new Button("3");
    Button button4 = new Button("4");

    @Override
    public void onButtonPressed(String buttonCode, String rcId) {

    }
}
