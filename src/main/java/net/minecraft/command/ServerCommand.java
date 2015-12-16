package net.minecraft.command;

public class ServerCommand
{
    /** The command string. */
    public final String command;
    public final CommandSender sender;

    public ServerCommand(String input, CommandSender sender)
    {
        this.command = input;
        this.sender = sender;
    }
}
