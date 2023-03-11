package org.example;

public class RemoteControl {

    Command[] onCommands = new Command[7];
    Command[] offCommands = new Command[7];

    public RemoteControl() {
        Command noCommand = new NoCommand();
        for (int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        onCommands[slot].execute();
    }

    public void offButtonWasPushed(int slot) {
        offCommands[slot].execute();
    }

    public String toString() {
        StringBuffer stringBuff = new StringBuffer();
        stringBuff.append("\n------ 리모컨 -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            stringBuff.append("[slot " + i + "]" + onCommands[i].getClass().getName()
                    + "   " + offCommands[i].getClass().getName() + "\n");
        }
        return stringBuff.toString();
    }

}
