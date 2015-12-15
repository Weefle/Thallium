package org.thallium.event.types;

/**
 * Represents a event
 * @author PizzaCrust
 */
public interface Event {
    void setCancelled(boolean flag);

    boolean isCancelled();
}
