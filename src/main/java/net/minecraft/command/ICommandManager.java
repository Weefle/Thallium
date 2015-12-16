package net.minecraft.command;

import java.util.List;
import java.util.Map;
import net.minecraft.util.BlockPos;

public interface ICommandManager
{
    int executeCommand(CommandSender sender, String rawCommand);

    List<String> getTabCompletionOptions(CommandSender sender, String input, BlockPos pos);

    List<ICommand> getPossibleCommands(CommandSender sender);

    Map<String, ICommand> getCommands();
}
