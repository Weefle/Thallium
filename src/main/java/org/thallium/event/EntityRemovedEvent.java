package org.thallium.event;

import net.minecraft.entity.Entity;
import org.thallium.event.types.Event;

/**
 * A event called when a entity gets removed
 * @author PizzaCrust
 */
public class EntityRemovedEvent implements Event{
    private boolean cancelled;
    private Entity entityIn;
    public EntityRemovedEvent(Entity entityIn){
        this.entityIn = entityIn;
    }
    public Entity getEntity(){
        return this.entityIn;
    }
    public void setCancelled(boolean flag) {
        cancelled = flag;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
