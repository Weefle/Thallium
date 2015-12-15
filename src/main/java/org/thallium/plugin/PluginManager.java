package org.thallium.plugin;

import org.thallium.plugin.loader.PluginLoader;

/**
 * Manages internal plugin handling such as disabling and enabling
 * @author PizzaCrust
 */
public class PluginManager {
    public PluginManager(){}

    public static PluginManager instance(){
        return new PluginManager();
    }

    /**
     * Gets all the loaded plugins
     * @return the loaded plugins
     */
    public MinecraftPlugin[] getPlugins(){
        return PluginLoader.plugins.toArray(new MinecraftPlugin[PluginLoader.plugins.size()]);
    }

    public MinecraftPlugin getPlugin(String name){
        return PluginLoader.nameToPlugin.get(name);
    }
}
