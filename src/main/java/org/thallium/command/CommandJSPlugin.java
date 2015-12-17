package org.thallium.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.CommandSender;
import net.minecraft.command.ICommand;
import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import org.thallium.js.JavaScriptPlugin;
import org.thallium.plugin.loader.PluginLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A command that shows loaded JS plugins
 */
public class CommandJSPlugin implements ICommand{
    public String getCommandName() {
        return "jsplugins";
    }

    public String getCommandUsage(CommandSender sender) {
        return "/jsplugins";
    }

    public List<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("javascriptplugins");
        aliases.add("jspl");
        aliases.add("javascriptpl");
        return aliases;
    }

    public void processCommand(CommandSender sender, String[] args) throws CommandException {
        StringBuilder stringFactory = new StringBuilder();
        for (JavaScriptPlugin plugin : PluginLoader.jsPlugins){
            stringFactory.append(plugin.getName() + ", ");
        }
        String before = stringFactory.toString();
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
