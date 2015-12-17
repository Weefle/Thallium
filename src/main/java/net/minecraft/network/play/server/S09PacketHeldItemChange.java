package net.minecraft.network.play.server;

import java.io.IOException;

import net.minecraft.entity.player.Player;
import net.minecraft.entity.player.PlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.server.MinecraftServer;
import org.thallium.event.PlayerHeldItemChangeEvent;

public class S09PacketHeldItemChange implements Packet<INetHandlerPlayClient>
{
    private int heldItemHotbarIndex;
    private ItemStack item;

    public S09PacketHeldItemChange()
    {
    }

    public S09PacketHeldItemChange(PlayerMP player, int hotbarIndexIn, ItemStack item)
    {
        PlayerHeldItemChangeEvent event = new PlayerHeldItemChangeEvent(item, player);
        MinecraftServer.getServer().getAPIHandler().getEventManager().callEvent(event);
        if(event.isCancelled()){
            return;
        }
        this.heldItemHotbarIndex = hotbarIndexIn;
        this.item = item;
    }

    /**
     * Reads the raw packet data from the data stream.
     */
    public void readPacketData(PacketBuffer buf) throws IOException
    {
        this.heldItemHotbarIndex = buf.readByte();
    }

    /**
     * Writes the raw packet data to the data stream.
     */
    public void writePacketData(PacketBuffer buf) throws IOException
    {
        buf.writeByte(this.heldItemHotbarIndex);
    }

    /**
     * Passes this Packet on to the NetHandler for processing.
     */
    public void processPacket(INetHandlerPlayClient handler)
    {
        handler.handleHeldItemChange(this);
    }
}
