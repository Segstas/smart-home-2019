package ru.sbt.mipt.oop.remotecontrol;

public class Button {
    private final String buttonName;
    private ActionListener actionListener;


    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    private Command command;

    public Button(String buttonName) {
        this.buttonName = buttonName;
    }

}
