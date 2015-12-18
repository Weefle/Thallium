package org.thallium.event;

import net.minecraft.entity.Entity;
import org.thallium.event.types.Event;

/**
 * A event called when a entity moves
 * @author PizzaCrust
 */
public class EntityMoveEvent implements Event{
    private boolean cancelled;
    private Entity entity;

    public EntityMoveEvent(Entity entity){
        this.entity = entity;
    }

    public Entity getEntity(){
        return this.entity;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
