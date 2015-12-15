package org.thallium.plugin.loader;


import net.minecraft.server.MinecraftServer;
import org.thallium.event.PluginStartEvent;
import org.thallium.plugin.MinecraftPlugin;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Loads plugins
 * @author PizzaCrust
 */
public class PluginLoader {
    public static ArrayList<MinecraftPlugin> plugins = new ArrayList<MinecraftPlugin>();
    public static HashMap<String, MinecraftPlugin> nameToPlugin = new HashMap<String, MinecraftPlugin>();

    public static void loadPluginInternally(final MinecraftPlugin minecraftPlugin){
        Thread pluginThread = new Thread(new Runnable() {
            public void run() {
                minecraftPlugin.start();
            }
        });
        pluginThread.setDaemon(true);
        minecraftPlugin.setThread(pluginThread);
        minecraftPlugin.start();
        MinecraftServer.getServer().getAPIHandler().getEventManager().callEvent(new PluginStartEvent(minecraftPlugin));
    }

}
