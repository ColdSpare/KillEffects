package com.coldspare.killeffects;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class KillEffectManager {
    private HashMap<UUID, KillEffects.KillEffect> playerKillEffects;

    public KillEffectManager() {
        playerKillEffects = new HashMap<>();
    }

    public void setPlayerKillEffect(Player player, KillEffects.KillEffect effect) {
        playerKillEffects.put(player.getUniqueId(), effect);
    }

    public KillEffects.KillEffect getPlayerKillEffect(Player player) {
        return playerKillEffects.get(player.getUniqueId());
    }
}

