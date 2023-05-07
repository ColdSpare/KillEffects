package com.coldspare.killeffects;

import org.bukkit.plugin.java.JavaPlugin;

public final class KillEffects extends JavaPlugin {

    @Override
    public void onEnable() {
        KillEffectManager killEffectManager = new KillEffectManager();
        KillEffectsGUI killEffectsGUI = new KillEffectsGUI(this, killEffectManager);
        getCommand("killeffects").setExecutor(new KillEffectsCommand(this, killEffectManager, killEffectsGUI));
        getServer().getPluginManager().registerEvents(killEffectsGUI, this);

        // Register the PlayerDeathListener
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(killEffectManager), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public enum KillEffect {
        VIP,
        MVP,
        ELITE,
        LEGEND,
        FAST,
        CUSTOM
    }

}
