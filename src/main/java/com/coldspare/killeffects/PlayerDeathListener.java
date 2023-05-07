package com.coldspare.killeffects;

import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {
    private KillEffectManager killEffectManager;

    public PlayerDeathListener(KillEffectManager killEffectManager) {
        this.killEffectManager = killEffectManager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player killer = event.getEntity().getKiller();
        if (killer != null) {
            KillEffects.KillEffect effect = killEffectManager.getPlayerKillEffect(killer);
            if (effect != null) {
                Location location = event.getEntity().getLocation();
                World world = location.getWorld();
                switch (effect) {
                    case VIP:
                        Particle.DustOptions dustOptions = new Particle.DustOptions(Color.fromRGB(139, 69, 19), 1.5f);
                        world.spawnParticle(Particle.REDSTONE, location, 150, 1, 1, 1, dustOptions);
                        break;
                    case MVP:
                        world.spawnParticle(Particle.FALLING_DUST, location, 150, 1, 1, 1, Material.REDSTONE_BLOCK.createBlockData());
                        break;
                    case ELITE:
                        world.strikeLightningEffect(location);
                        break;
                    case LEGEND:
                        TNTPrimed tnt = (TNTPrimed) world.spawnEntity(location, EntityType.PRIMED_TNT);
                        tnt.setFuseTicks(10);
                        tnt.setYield(0);
                        break;
                    case FAST:
                        world.spawnParticle(Particle.EXPLOSION_NORMAL, location, 100);
                        world.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1.0f, 1.0f);
                        break;
                    case CUSTOM:
                        world.spawnParticle(Particle.TOTEM, location, 50);
                        break;
                }
            }
        }
    }

}
