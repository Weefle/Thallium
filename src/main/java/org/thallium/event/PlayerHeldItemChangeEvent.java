package org.thallium.event;

import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerMP;
import net.minecraft.item.ItemStack;
import org.thallium.event.types.PlayerEvent;

/**
 * A event called when a player's held item changes
 * @author PizzaCrust
 */
public class PlayerHeldItemChangeEvent implements PlayerEvent{
    private ItemStack item;
    private PlayerMP playerMP;
    private boolean cancelled;

    public PlayerHeldItemChangeEvent(ItemStack item, PlayerMP player){
        this.item = item;
        this.playerMP = player;
    }

    public PlayerMP getPlayer() {
        return playerMP;
    }

    public ItemStack getItemStack(){
        return item;
    }

    public void setCancelled(boolean flag) {
        this.cancelled = flag;
    }

    public boolean isCancelled() {
        return cancelled;
    }
}
