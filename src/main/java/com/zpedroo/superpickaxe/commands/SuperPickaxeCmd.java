package com.zpedroo.superpickaxe.commands;

import com.zpedroo.superpickaxe.utils.config.Messages;
import com.zpedroo.superpickaxe.utils.item.ItemUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SuperPickaxeCmd implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage(Messages.COMMAND_USAGE);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            sender.sendMessage(Messages.OFFLINE_PLAYER);
            return true;
        }

        int amount = 0;
        try {
            amount = Integer.parseInt(args[1]);
        } catch (NumberFormatException ex) {
            // ignore
        }

        if (amount <= 0) {
            sender.sendMessage(Messages.INVALID_AMOUNT);
            return true;
        }

        ItemStack item = ItemUtils.getItem();
        item.setAmount(amount);

        if (target.getInventory().firstEmpty() != -1) {
            target.getInventory().addItem(item);
        } else {
            target.getWorld().dropItemNaturally(target.getLocation(), item);
        }
        return false;
    }
}