package me.hotpocket.rules;

import me.hotpocket.rules.commands.CMDReload;
import me.hotpocket.rules.commands.CMDRules;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Field;

public final class Rules extends JavaPlugin {

    private static Rules instance;

    @Override
    public void onEnable() {
        instance = this;
        File file = new File(getDataFolder() + File.separator + "config.yml");
        if (!file.exists()){
            getConfig().options().copyDefaults(true);
            saveDefaultConfig();
        } else {
            reloadConfig();
            this.saveDefaultConfig();
        }

        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            commandMap.register("Rules", new CMDRules("rules"));
            commandMap.register("Rules", new CMDReload("rules-reload"));
            Bukkit.getLogger().info("&aSuccessfully registered commands.");
        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().info("&cAn error occurred when registering commands.");
        }
        Bukkit.getLogger().info("[RULES] has been enabled!");
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[RULES] has been disabled!");
    }

    public static Rules getInstance() {
        return instance;
    }
}
