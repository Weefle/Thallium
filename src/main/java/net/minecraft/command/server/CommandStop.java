package net.minecraft.command.server;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSender;
import net.minecraft.server.MinecraftServer;

public class CommandStop extends CommandBase
{
    /**
     * Gets the name of the command
     */
    public String getCommandName()
    {
        return "stop";
    }

    /**
     * Gets the usage string for the command.
     */
    public String getCommandUsage(CommandSender sender)
    {
        return "commands.stop.usage";
    }

    /**
     * Callback when the command is invoked
     */
    public void processCommand(CommandSender sender, String[] args) throws CommandException
    {
        if (MinecraftServer.getServer().worldServers != null)
        {
            notifyOperators(sender, this, "commands.stop.start", new Object[0]);
        }

        MinecraftServer.getServer().initiateShutdown();
    }
}
