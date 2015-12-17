package org.thallium.js;

/**
 * Represents a javascript plugin
 * @author PizzaCrust
 */
public class JavaScriptPlugin {
    private String name;
    private Thread thread;

    public JavaScriptPlugin(String name, Thread thread){
        this.name = name;
        this.thread = thread;
    }

    public Thread getThread() {
        return thread;
    }

    public String getName() {
        return name;
    }
}
