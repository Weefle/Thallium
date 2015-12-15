package org.thallium.event.types;

import org.thallium.plugin.MinecraftPlugin;

/**
 * Represents a type of a event involving plugins
 * @author PizzaCrust
 */
public interface PluginEvent extends Event{
    MinecraftPlugin getPlugin();
}
