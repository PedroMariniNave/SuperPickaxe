package com.zpedroo.superpickaxe.listeners;

import com.zpedroo.superpickaxe.utils.config.Settings;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_8_R3.CraftSound;
import org.bukkit.craftbukkit.v1_8_R3.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class PlayerGeneralListeners implements Listener {

    private Sound bedrockSound;

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType().equals(Material.AIR)) return;

        ItemStack item = event.getItem();
        NBTItem nbt = new NBTItem(item);
        if (!nbt.hasKey("SuperPickaxe")) return;

        Block block = event.getClickedBlock();
        if (block == null || !block.getType().equals(Material.BEDROCK)) return;
        if (block.getY() <= 1) return;

        Player player = event.getPlayer();

        block.breakNaturally();
        block.getWorld().playSound(block.getLocation(), getBedrockSound(block), 1f, 1f);
        item.setDurability((short) (item.getDurability() + Settings.DURABILITY_DAMAGE_PER_BLOCK));

        if (item.getDurability() >= item.getType().getMaxDurability()) {
            player.setItemInHand(new ItemStack(Material.AIR));
            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 1f, 1f);
        }
    }

    private Sound getBedrockSound(Block block) {
        if (bedrockSound == null) {
            try {
                for (Sound sound : Sound.values()) {
                    Field field = CraftSound.class.getDeclaredField("sounds");
                    field.setAccessible(true);

                    String[] sounds = (String[]) field.get(null);
                    Method getBlock = CraftBlock.class.getDeclaredMethod("getNMSBlock");
                    getBlock.setAccessible(true);
                    Object nmsBlock = getBlock.invoke(block);
                    net.minecraft.server.v1_8_R3.Block minecraftBlock = (net.minecraft.server.v1_8_R3.Block) nmsBlock;

                    if (minecraftBlock.stepSound.getBreakSound().equals(sounds[sound.ordinal()])) {
                        this.bedrockSound = sound;
                    }
                }
            } catch (Exception ex) {
                // ignore
            }
        }

        return bedrockSound;
    }
}