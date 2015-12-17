package org.thallium.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSender;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import org.thallium.lua.LuaPlugin;
import org.thallium.plugin.loader.PluginLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that shows the names of the lua plugins
 * @author PizzaCrust
 */
public class CommandLuaPlugin implements ICommand{
    public String getCommandName() {
        return "luaplugins";
    }

    public String getCommandUsage(CommandSender sender) {
        return "/luaplugins";
    }

    public List<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("luapl");
        aliases.add("lpl");
        return aliases;
    }

    public void processCommand(CommandSender sender, String[] args) throws CommandException {
        StringBuilder builder = new StringBuilder();
        for (LuaPlugin plugin : PluginLoader.luaPlugins){
            builder.append(plugin.getName() + ", ");
        }
        String before = builder.toString();
        String after = "Plugins: " + before;
        sender.sendMessage(new ChatComponentText(after));
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
