package org.thallium.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.rcon.RConConsoleSource;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

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

    public String getCommandUsage(ICommandSender sender) {
        return "/about";
    }

    public List<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("abt");
        return aliases;
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        sender.addChatMessage(new ChatComponentText("Thallium Server (implementing net.minecraft.server 1.8.8)"));
    }

    public boolean canCommandSenderUseCommand(ICommandSender sender) {
        if(!(sender instanceof EntityPlayer)){
            return true;
        }
        EntityPlayerMP player = (EntityPlayerMP) sender;
        return player.isOp();
    }

    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        return null;
    }

    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    public int compareTo(ICommand o) {
        return 0;
    }
}
