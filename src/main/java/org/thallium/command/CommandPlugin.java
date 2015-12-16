package org.thallium.command;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;
import org.thallium.plugin.MinecraftPlugin;
import org.thallium.plugin.loader.PluginLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * A plugins command for Thallium
 * @author PizzaCrust
 */
public class CommandPlugin implements ICommand{
    public String getCommandName() {
        return "plugins";
    }

    public String getCommandUsage(ICommandSender sender) {
        return "/plugins";
    }

    public List<String> getCommandAliases() {
        ArrayList<String> aliases = new ArrayList<String>();
        aliases.add("pl");
        aliases.add("plugin");
        return aliases;
    }

    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        StringBuilder builder = new StringBuilder();
        for (MinecraftPlugin plugin : PluginLoader.plugins){
            builder.append(plugin.getName() + " ,");
        }
        String before = builder.toString();
        String after = "Plugins: " + before;
        sender.addChatMessage(new ChatComponentText(after));
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
