package org.thallium.event;

import net.minecraft.entity.Entity;
import org.thallium.event.types.Event;

/**
 * A event called when a entity is added
 * @author PizzaCrust
 */
public class EntityAddedEvent implements Event{
    private Entity entity;
    private boolean cancelled;

    public EntityAddedEvent(Entity entityIn){
        this.entity = entityIn;
    }

    public Entity getEntity(){
        return this.entity;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
