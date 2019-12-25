package ru.sbt.mipt.oop.remotecontrol;

import rc.RemoteControl;

import java.util.HashMap;
import java.util.Map;

public class RemoteController implements RemoteControl {

    private final Map<Button, Command> commands = new HashMap<>();

    private final Map<String, Button> buttons = new HashMap<>();

    public RemoteController() {
    }


    public void setButton(String buttonName, Button button) {
       this.buttons.put(buttonName,button);
    }

    public void setCommand(String commandName, Command command) {
        if (!buttons.containsKey(commandName)) return;
        this.commands.put(buttons.get(commandName), command);
    }

    @Override
    public void onButtonPressed(String buttonName, String rcId) {
        if (!buttons.containsKey(buttonName)) return;
        Command command = commands.get(buttons.get(buttonName));
        if (command == null) return;
        command.execute();
    }


}
