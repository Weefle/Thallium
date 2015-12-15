package org.thallium;

import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.thallium.plugin.EventManager;
import org.thallium.plugin.PluginManager;

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
}
