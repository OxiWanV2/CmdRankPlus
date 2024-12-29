package com.oxiwan.cmdrankplus;

import com.oxiwan.cmdrankplus.commands.MainCommand;
import com.oxiwan.cmdrankplus.commands.TriggerCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.Map;

public class CmdRankPlus extends JavaPlugin {

    private CommandMap commandMap;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        initializeCommandMap();

        registerDynamicCommands();

        getCommand("cmdrankplus").setExecutor(new MainCommand(this));

        getLogger().info("CmdRankPlus activé !");
    }

    @Override
    public void onDisable() {
        getLogger().info("CmdRankPlus désactivé !");
    }

    private void initializeCommandMap() {
        try {
            Field field = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            field.setAccessible(true);
            commandMap = (CommandMap) field.get(Bukkit.getServer());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registerDynamicCommands() {
        if (commandMap == null) {
            getLogger().severe("Impossible d'accéder au CommandMap !");
            return;
        }

        Map<String, Object> commands = getConfig().getConfigurationSection("commands").getValues(false);
        for (String category : commands.keySet()) {
            registerCommand(category);
        }
    }

    private void registerCommand(String name) {
        BukkitCommand command = new BukkitCommand(name) {
            @Override
            public boolean execute(org.bukkit.command.CommandSender sender, String label, String[] args) {
                return new TriggerCommand(CmdRankPlus.this, name).onCommand(sender, this, label, args);
            }
        };

        command.setDescription("Commande dynamique pour " + name);
        command.setUsage("/" + name);
        commandMap.register(getName(), command);
    }

    public void reloadPluginConfig() {
        reloadConfig();
        registerDynamicCommands();
    }
}