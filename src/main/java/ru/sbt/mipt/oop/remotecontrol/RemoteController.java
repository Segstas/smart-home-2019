package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;

import java.util.HashMap;

public class RemoteController implements RemoteControl {

    private final HashMap<Button, Command> commands = new HashMap<>();

    private final HashMap<String, Button> buttons = new HashMap<>();

    public RemoteController() {
        this.buttons.put("A", new Button("A"));
        this.buttons.put("B", new Button("B"));
        this.buttons.put("C", new Button("C"));
        this.buttons.put("D", new Button("D"));
        this.buttons.put("1", new Button("1"));
        this.buttons.put("2", new Button("2"));
        this.buttons.put("3", new Button("3"));
        this.buttons.put("4", new Button("4"));
    }

    public void setCommand(String commandName, Command command) {
        if (!buttons.containsKey(commandName)) return;
        commands.put(buttons.get(commandName), command);
    }

    @Override
    public void onButtonPressed(String buttonName, String rcId) {
        if (!buttons.containsKey(buttonName)) return;
        Command command = commands.get(buttonName);
        if (command == null) return;
        command.execute();
    }


}
