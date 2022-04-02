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

        // Set file to a file called 'config.yml'
        File file = new File(getDataFolder() + File.separator + "config.yml");

        // Check if 'config.yml' does not exist
        if (!file.exists()){
            // Copy the lines from the provided config.yml under resources
            getConfig().options().copyDefaults(true);
            // Save the config
            saveDefaultConfig();
        // If the file does exist
        } else {
            // This method allows the use of comments in 'config.yml'
            // Reload the config
            reloadConfig();
            // Save the config after reloading it
            this.saveDefaultConfig();
        }

        try {
            // Get a command map which allows the use of commands without putting them into 'plugin.yml'

            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            // Make the command map accessible
            bukkitCommandMap.setAccessible(true);
            // Set variable to the command map
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
            // Register the commands
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
