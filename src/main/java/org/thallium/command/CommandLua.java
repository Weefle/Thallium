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
 * A command that executes lua code at runtime.
 * @author PizzaCrust
 */
public class CommandLua implements ICommand{
    public String getCommandName() {
        return "lua";
    }

    public String getCommandUsage(CommandSender sender) {
        return "/lua <code>";
    }

    public List<String> getCommandAliases() {
        return new ArrayList<String>();
    }

    public void processCommand(CommandSender sender, String[] args) throws CommandException {
        sender.sendMessage(new ChatComponentText(EnumChatFormatting.RED + "Running code..."));
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine scriptEngine = manager.getEngineByName("luaj");
        String str1 = Arrays.toString(args);
        str1 = str1.substring(1, str1.length()-1).replaceAll(",", "");
        try {
            scriptEngine.eval(str1);
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
        PlayerMP playerMP = (PlayerMP) sender;
        return playerMP.isOp();
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
