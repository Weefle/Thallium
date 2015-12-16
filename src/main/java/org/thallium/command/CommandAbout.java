package org.thallium.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.CommandSender;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.List;

/**
 * A about command for Thallium
 * @author PizzaCrust
 */
public class CommandAbout implements ICommand{
    public String getCommandName() {
        return "about";
    }

    public String getCommandUsage(CommandSender sender) {
        return "/about";
    }

    public List<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("abt");
        return aliases;
    }

    public void processCommand(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new ChatComponentText("Thallium Server (implementing net.minecraft.server 1.8.8)"));
    }

    public boolean canCommandSenderUseCommand(CommandSender sender) {
        if(!(sender instanceof Player)){
            return true;
        }
        PlayerMP player = (PlayerMP) sender;
        return player.isOp();
    }

    public List<String> addTabCompletionOptions(CommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    public int compareTo(ICommand o) {
        return 0;
    }
}
