package org.thallium.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSender;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.commons.lang3.exception.ExceptionUtils;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A command that executes javascript code in-game
 * @author PizzaCrust
 */
public class CommandJS implements ICommand{
    public String getCommandName() {
        return "js";
    }

    public String getCommandUsage(CommandSender sender) {
        return "/js <code>";
    }

    public List<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("javascript");
        aliases.add("rhino");
        return aliases;
    }

    public void processCommand(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new ChatComponentText(EnumChatFormatting.RED + "Running code..."));
        ScriptEngine jsEngine = (new ScriptEngineManager()).getEngineByName("JavaScript");
        String str1 = Arrays.toString(args);
        str1 = str1.substring(1, str1.length()-1).replaceAll(",", "");
        try {
            jsEngine.eval(str1);
        } catch (ScriptException e) {
            sender.sendMessage(new ChatComponentText(EnumChatFormatting.RED + "Failed to issue code!"));
            String trace = ExceptionUtils.getStackTrace(e);
            sender.sendMessage(new ChatComponentText(EnumChatFormatting.RED + trace));
            return;
        }
        sender.sendMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Code executed successfully!"));
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
