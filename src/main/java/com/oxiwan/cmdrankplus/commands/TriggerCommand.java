package com.oxiwan.cmdrankplus.commands;

import com.oxiwan.cmdrankplus.CmdRankPlus;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TriggerCommand implements CommandExecutor {

    private final CmdRankPlus plugin;
    private final String category;

    public TriggerCommand(CmdRankPlus plugin, String category) {
        this.plugin = plugin;
        this.category = category;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cSeuls les joueurs peuvent exécuter cette commande.");
            return true;
        }

        Player player = (Player) sender;

        if (!plugin.getConfig().contains("commands." + category)) {
            player.sendMessage("§cAucune commande configurée pour '" + category + "'.");
            return true;
        }

        for (String rank : plugin.getConfig().getConfigurationSection("commands." + category).getKeys(false)) {
            if (player.hasPermission("cmdrankplus." + rank)) {
                String commandTemplate = plugin.getConfig().getString("commands." + category + "." + rank);

                if (commandTemplate != null) {
                    String finalCommand = commandTemplate.replace("{player}", player.getName()).replace("{rank}", rank);

                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), finalCommand);
                    return true;
                }
            }
        }

        player.sendMessage("§cVous n'avez pas la permission d'exécuter cette commande.");
        return true;
    }
}