package org.thallium.plugin.loader;


import net.minecraft.server.MinecraftServer;
import org.thallium.event.PluginStartEvent;
import org.thallium.plugin.MinecraftPlugin;

import java.io.File;
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
        if(!theClass.isAssignableFrom(MinecraftPlugin.class)){
            throw new Exception();
        }
        MinecraftPlugin plugin = MinecraftPlugin.class.cast(theClass);
        loadPluginInternally(plugin);
    }
}
