package org.thallium.event;

import org.thallium.event.types.PluginEvent;
import org.thallium.plugin.MinecraftPlugin;

/**
 * Represents a event once a plugin starts
 * @author PizzaCrust
 */
public class PluginStartEvent implements PluginEvent{
    private MinecraftPlugin plugin;
    private boolean cancelled;

    public PluginStartEvent(MinecraftPlugin plugin){
        this.plugin = plugin;
        this.cancelled = false;
    }

    public MinecraftPlugin getPlugin() {
        return this.plugin;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
