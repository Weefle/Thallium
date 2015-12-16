package org.thallium.event;

import net.minecraft.entity.player.PlayerMP;
import net.minecraft.world.World;
import org.thallium.event.types.PlayerEvent;

/**
 * Called when a player joins the server
 * @author PizzaCrust
 */
public class PlayerJoinEvent implements PlayerEvent {
    private PlayerMP player;
    private World world;
    private boolean cancelled;

    public PlayerJoinEvent(PlayerMP player, World worldJoinedTo){
        this.player = player;
        this.world = worldJoinedTo;
        this.cancelled = false;
    }

    public PlayerMP getPlayer(){
        return this.player;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }
}
