package com.zpedroo.superpickaxe;

import com.zpedroo.superpickaxe.commands.SuperPickaxeCmd;
import com.zpedroo.superpickaxe.listeners.PlayerGeneralListeners;
import com.zpedroo.superpickaxe.utils.FileUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.List;

import static com.zpedroo.superpickaxe.utils.config.Settings.*;

public class SuperPickaxe extends JavaPlugin {

    public void onEnable() {
        new FileUtils(this);

        registerListeners();
        registerCommand(COMMAND, ALIASES, PERMISSION, PERMISSION_MESSAGE, new SuperPickaxeCmd());
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new PlayerGeneralListeners(), this);
    }

    private void registerCommand(String command, List<String> aliases, String permission, String permissionMessage, CommandExecutor executor) {
        try {
            Constructor<PluginCommand> constructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            constructor.setAccessible(true);

            PluginCommand pluginCmd = constructor.newInstance(command, this);
            pluginCmd.setAliases(aliases);
            pluginCmd.setExecutor(executor);
            pluginCmd.setPermission(permission);
            pluginCmd.setPermissionMessage(permissionMessage);

            Field field = Bukkit.getPluginManager().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            CommandMap commandMap = (CommandMap) field.get(Bukkit.getPluginManager());
            commandMap.register(getName().toLowerCase(), pluginCmd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}