package com.oxiwan.cmdrankplus.commands;

import com.oxiwan.cmdrankplus.CmdRankPlus;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final CmdRankPlus plugin;

    public MainCommand(CmdRankPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendHelpMessage(sender);
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("cmdrankplus.reload")) {
                sender.sendMessage(ChatColor.RED + "Vous n'avez pas la permission d'utiliser cette commande.");
                return true;
            }

            plugin.reloadPluginConfig();
            sender.sendMessage(ChatColor.GREEN + "La configuration a été rechargée avec succès !");
            return true;
        }

        sender.sendMessage(ChatColor.RED + "Usage : /cmdrankplus [help|reload]");
        return true;
    }

    private void sendHelpMessage(CommandSender sender) {
        sender.sendMessage(ChatColor.GOLD + "========== [CmdRankPlus by (OxiWan)] ==========");
        sender.sendMessage(ChatColor.YELLOW + "Comment fonctionne le plugin :");
        sender.sendMessage(ChatColor.GRAY + "Dans le fichier " + ChatColor.AQUA + "config.yml" + ChatColor.GRAY + ", vous pouvez :");
        sender.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Définir des commandes déclencheurs (ex: /shops, /menu).");
        sender.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.GRAY + "Associer des rangs à des actions spécifiques exécutées via ces commandes.");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Les commandes disponibles :");
        sender.sendMessage(ChatColor.GOLD + "- /cmdrankplus reload");
        sender.sendMessage(ChatColor.DARK_GRAY + "  - " + ChatColor.GRAY + "Recharge les rangs et les commandes associées (ex: /say {player}...).");
        sender.sendMessage(ChatColor.DARK_RED + "  ⚠ Note : Cela ne recharge pas les commandes déclencheurs (ex: /shops).");
        sender.sendMessage(ChatColor.DARK_RED + "  Pour ajouter de nouvelles commandes déclencheurs, redémarrez le serveur.");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Les permissions :");
        sender.sendMessage(ChatColor.GOLD + "- cmdrankplus.reload");
        sender.sendMessage(ChatColor.DARK_GRAY + "  - " + ChatColor.GRAY + "Permet de recharger la configuration du plugin (par défaut pour les OPs).");
        sender.sendMessage("");
        sender.sendMessage(ChatColor.GRAY + "Pour associer un rang à un utilisateur :");
        sender.sendMessage(ChatColor.DARK_GRAY + "- Ajoutez une permission comme "
                + ChatColor.AQUA + "cmdrankplus.rangX"
                + ChatColor.DARK_GRAY + " au groupe ou à l'utilisateur via LuckPerms.");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.YELLOW + "Placeholders disponibles :");
        sender.sendMessage(ChatColor.GOLD + "- {player}"
                + ChatColor.DARK_GRAY + ": Correspond au pseudo du joueur.");
        sender.sendMessage(ChatColor.GOLD + "- {rank}"
                + ChatColor.DARK_GRAY + ": Correspond au rang du joueur.");
        sender.sendMessage("");

        sender.sendMessage(ChatColor.GREEN
                + "**Le joueur peut exécuter une commande déclencheur X, et le plugin exécutera l'action associée à son rang X !**");
        sender.sendMessage(ChatColor.GOLD + "===================================");
    }
}