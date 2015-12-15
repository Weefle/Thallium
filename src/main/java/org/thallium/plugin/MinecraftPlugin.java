package org.thallium.plugin;

import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Represents a Minecraft Plugin
 * @author PizzaCrust
 */
public abstract class MinecraftPlugin {
    private String name;
    private String version;
    private Thread thread;

    public MinecraftPlugin(String name, String version){
        this.name = name;
        this.version = version;
    }

    Logger getLogger(){
        return LogManager.getLogger(name);
    }

    public abstract void onStart();

    public abstract void onEnd();

    public void setThread(Thread thread){
        this.thread = thread;
    }

    public void start(){
        Validate.notNull(thread);
        this.thread.start();
    }

    public void stop(){
        Validate.notNull(thread);
        onEnd();
        this.thread.stop();
    }

    public String getName(){
        return name;
    }

    public String getVersion(){
        return version;
    }
}
