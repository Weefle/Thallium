package org.thallium.plugin;

import org.thallium.lua.LuaPlugin;
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

    public void disablePlugin(String name){
        getPlugin(name).stop();
    }

    public void enablePlugin(String name){
        getPlugin(name).start();
    }

    /** @deprecated **/
    public LuaPlugin getLuaPlugin(String name){
        return PluginLoader.nameToLuaPlugin.get(name);
    }
    /** @deprecated **/
    public LuaPlugin[] getLuaPlugins(){
        return PluginLoader.luaPlugins.toArray(new LuaPlugin[PluginLoader.luaPlugins.size()]);
    }
}
