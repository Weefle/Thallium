package org.thallium;

import net.minecraft.command.ICommand;
import net.minecraft.command.ServerCommand;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thallium.handler.LanguageHandler;
import org.thallium.plugin.EventManager;
import org.thallium.plugin.PluginManager;

import javax.script.ScriptEngineManager;

/**
 * The main core of the API of Thallium
 * @author PizzaCrust
 */
public class ThalliumHandler {
    public static Logger apiLogger = LogManager.getLogger("Thallium");

    public EventManager getEventManager(){
        return EventManager.instance();
    }

    public MinecraftServer getServer(){
        return MinecraftServer.getServer();
    }

    public PluginManager getPluginManager(){
        return PluginManager.instance();
    }

    public void registerCommand(ICommand iCommand){
        ServerCommandManager.quenedCommands.add(iCommand);
    }

    public LanguageHandler getLangHandler(){
        return new LanguageHandler(new ScriptEngineManager());
    }
}
