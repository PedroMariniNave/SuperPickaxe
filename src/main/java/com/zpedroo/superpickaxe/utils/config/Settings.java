package com.zpedroo.superpickaxe.utils.config;

import com.zpedroo.superpickaxe.utils.FileUtils;
import com.zpedroo.superpickaxe.utils.color.Colorize;

import java.util.List;

public class Settings {

    public static final String COMMAND = FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.command");

    public static final List<String> ALIASES = FileUtils.get().getStringList(FileUtils.Files.CONFIG, "Settings.aliases");

    public static final String PERMISSION = FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.permission");

    public static final String PERMISSION_MESSAGE = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Settings.permission-message"));

    public static final int DURABILITY_DAMAGE_PER_BLOCK = FileUtils.get().getInt(FileUtils.Files.CONFIG, "Settings.durability-damage-per-block");
}