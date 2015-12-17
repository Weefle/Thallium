package org.thallium.lua;

/**
 * Represents a lua plugin
 * @author PizzaCrust
 */
public class LuaPlugin {
    private String name;
    private Thread thread;

    public LuaPlugin(String name, Thread thread){
        this.name = name;
        this.thread = thread;
    }

    public String getName() {
        return name;
    }

    public Thread getThread() {
        return thread;
    }
}
