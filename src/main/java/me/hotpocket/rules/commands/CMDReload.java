package me.hotpocket.rules.commands;

import me.hotpocket.rules.Rules;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDReload extends BukkitCommand {

    public CMDReload(String name) {
        super(name);
        this.description = "Reloads the config.";
        this.usageMessage = "/rules-reload";
        this.setPermission("rules.reload");
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot use this command.");
            return true;
        }
        Player player = (Player) sender;
        Rules.getInstance().reloadConfig();
        Rules.getInstance().saveDefaultConfig();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d[RULES] &fSuccessfully reloaded the config."));
        return true;
    }
}