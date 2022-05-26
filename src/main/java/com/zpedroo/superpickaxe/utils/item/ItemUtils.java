package com.zpedroo.superpickaxe.utils.item;

import com.zpedroo.superpickaxe.utils.FileUtils;
import com.zpedroo.superpickaxe.utils.builder.ItemBuilder;
import de.tr7zw.nbtapi.NBTItem;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public class ItemUtils {

    private static final ItemStack ITEM = ItemBuilder.build(FileUtils.get().getFile(FileUtils.Files.CONFIG).getFileConfiguration(), "Item").build();

    public static ItemStack getItem() {
        NBTItem nbt = new NBTItem(ITEM.clone());
        nbt.addCompound("SuperPickaxe");

        return nbt.getItem();
    }
}