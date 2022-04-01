package me.hotpocket.rules.commands;

import me.hotpocket.rules.Rules;
import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CMDRules extends BukkitCommand {

    public CMDRules(String name) {
        super(name);
        this.description = "Send the rules.";
        this.usageMessage = "/rules";
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You cannot use this command.");
            return true;
        }
        Player player = (Player) sender;
        for(String rules : Rules.getInstance().getConfig().getStringList("rules")) {
            if(Rules.getInstance().getConfig().getConfigurationSection("centered").getBoolean("enabled")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', StringUtils.center(rules, Rules.getInstance().getConfig().getConfigurationSection("centered").getInt("chat-width"))));
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', rules));
            }
        }
        return true;
    }
}
