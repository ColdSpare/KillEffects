package com.coldspare.killeffects;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class KillEffectsCommand implements CommandExecutor {
    private KillEffects plugin;
    private KillEffectManager killEffectManager;
    private KillEffectsGUI gui;

    public KillEffectsCommand(KillEffects plugin, KillEffectManager killEffectManager, KillEffectsGUI gui) {
        this.plugin = plugin;
        this.killEffectManager = killEffectManager;
        this.gui = gui;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            gui.open(player);
            return true;
        }
        return false;
    }
}