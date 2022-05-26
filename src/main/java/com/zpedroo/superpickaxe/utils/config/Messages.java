package com.zpedroo.superpickaxe.utils.config;

import com.zpedroo.superpickaxe.utils.FileUtils;
import com.zpedroo.superpickaxe.utils.color.Colorize;

public class Messages {

    public static final String COMMAND_USAGE = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.command-usage"));

    public static final String OFFLINE_PLAYER = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.offline-player"));

    public static final String INVALID_AMOUNT = Colorize.getColored(FileUtils.get().getString(FileUtils.Files.CONFIG, "Messages.invalid-amount"));
}