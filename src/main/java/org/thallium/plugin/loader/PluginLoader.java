package org.thallium.plugin.loader;


import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.jse.JsePlatform;
import org.thallium.event.PluginStartEvent;
import org.thallium.lua.LuaPlugin;
import org.thallium.lua.LuaPluginLogger;
import org.thallium.plugin.MinecraftPlugin;

import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Loads plugins
 * @author PizzaCrust
 */
public class PluginLoader {
    public static ArrayList<MinecraftPlugin> plugins = new ArrayList<MinecraftPlugin>();
    public static HashMap<String, MinecraftPlugin> nameToPlugin = new HashMap<String, MinecraftPlugin>();
    public static ArrayList<LuaPlugin> luaPlugins = new ArrayList<LuaPlugin>();
    public static HashMap<String, LuaPlugin> nameToLuaPlugin = new HashMap<String, LuaPlugin>();

    public static void loadPluginInternally(final MinecraftPlugin minecraftPlugin){
        Thread pluginThread = new Thread(new Runnable() {
            public void run() {
                minecraftPlugin.onStart();
            }
        });
        pluginThread.setDaemon(true);
        minecraftPlugin.setThread(pluginThread);
        PluginStartEvent event = new PluginStartEvent(minecraftPlugin);
        MinecraftServer.getServer().getAPIHandler().getEventManager().callEvent(event);
        if(event.isCancelled()){
            return;
        }
        minecraftPlugin.start();
        plugins.add(minecraftPlugin);
        nameToPlugin.put(minecraftPlugin.getName(), minecraftPlugin);
    }

    private static URLClassLoader getClassLoaderFromJar(File file) throws Exception{
        URL url = file.toURI().toURL();
        URL[] urls = new URL[]{ url };
        URLClassLoader loader = new URLClassLoader(urls);
        return loader;
    }

    public static void loadPluginExternal(File file) throws Exception {
        JarFile jarFile = new JarFile(file);
        JarEntry modProperties = jarFile.getJarEntry("mod.properties");
        InputStream stream = jarFile.getInputStream(modProperties);
        Properties properties = new Properties();
        properties.load(stream);
        String mainClassPath = properties.getProperty("main");
        URLClassLoader classLoader = getClassLoaderFromJar(file);
        Class theClass = classLoader.loadClass(mainClassPath);
        Object instance = theClass.newInstance();
        if(!(instance instanceof MinecraftPlugin)){
            throw new Exception();
        }
        MinecraftPlugin plugin = (MinecraftPlugin) instance;
        loadPluginInternally(plugin);
    }

    public static void loadLuaPlugin(final File file) throws Exception {
        ScriptEngineManager manager = new ScriptEngineManager();
        final ScriptEngine luaEngine = manager.getEngineByName("luaj");
        luaEngine.put("logger", new LuaPluginLogger(LogManager.getLogger(FilenameUtils.removeExtension(file.getName()))));
        Thread pluginThread = new Thread(new Runnable() {
            public void run() {
                try {
                    luaEngine.eval(new FileReader(file));
                } catch (ScriptException e) {
                    e.printStackTrace();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        pluginThread.setDaemon(true);
        String pluginName = FilenameUtils.removeExtension(file.getName());
        LuaPlugin luaPlugin = new LuaPlugin(pluginName, pluginThread);
        luaPlugin.getThread().start();
        luaPlugins.add(luaPlugin);
        nameToLuaPlugin.put(luaPlugin.getName(), luaPlugin);
    }
}
