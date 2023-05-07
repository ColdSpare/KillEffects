package com.coldspare.killeffects;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KillEffectsGUI implements Listener {

    private KillEffects plugin;
    private KillEffectManager killEffectManager;

    public KillEffectsGUI(KillEffects plugin, KillEffectManager killEffectManager) {
        this.plugin = plugin;
        this.killEffectManager = killEffectManager;
    }

    private Inventory createInventory(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 9, ChatColor.BLUE + "Kill Effects");

        ItemStack vipItem = updateItemMeta(player, new ItemStack(Material.DIAMOND), "killeffect.vip", ChatColor.GOLD + "" + ChatColor.BOLD + "VIP", ChatColor.GRAY + "Equip for a dust kill effect");
        ItemStack mvpItem = updateItemMeta(player, new ItemStack(Material.EMERALD), "killeffect.mvp", ChatColor.AQUA + "" + ChatColor.BOLD + "MVP", ChatColor.GRAY + "Equip for a bloody kill effect");
        ItemStack eliteItem = updateItemMeta(player, new ItemStack(Material.NETHER_STAR), "killeffect.elite", ChatColor.GREEN + "" + ChatColor.BOLD + "ELITE", ChatColor.GRAY + "Equip for a smite kill effect");
        ItemStack legendItem = updateItemMeta(player, new ItemStack(Material.TNT), "killeffect.legend", ChatColor.RED + "" + ChatColor.BOLD + "LEGEND", ChatColor.GRAY + "Equip for a tnt kill effect");
        ItemStack fastItem = updateItemMeta(player, new ItemStack(Material.FEATHER), "killeffect.fast", ChatColor.YELLOW + "" + ChatColor.BOLD + "FAST", ChatColor.GRAY + "Equip for an explosion kill effect");
        ItemStack customItem = updateItemMeta(player, new ItemStack(Material.TOTEM_OF_UNDYING), "killeffect.custom", ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "CUSTOM", ChatColor.GRAY + "Equip for a totem pop kill effect");

        inventory.setItem(0, vipItem);
        inventory.setItem(1, mvpItem);
        inventory.setItem(2, eliteItem);
        inventory.setItem(3, legendItem);
        inventory.setItem(4, fastItem);
        inventory.setItem(5, customItem);

        return inventory;
    }

    public void open(Player player) {
        player.openInventory(createInventory(player));
    }

    private ItemStack updateItemMeta(Player player, ItemStack item, String permission, String displayName, String description) {
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(displayName);

        List<String> lore = new ArrayList<>();
        lore.add(description);

        if (player.hasPermission(permission)) {
            lore.add(ChatColor.GREEN + "Kill effect is unlocked");
        } else {
            lore.add(ChatColor.RED + "You need " + ChatColor.stripColor(displayName) + " or above to equip");
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Inventory clickedInventory = event.getClickedInventory();

        if (clickedInventory != null && event.getView().getTitle().equals(ChatColor.BLUE + "Kill Effects")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            ItemStack clickedItem = event.getCurrentItem();

            if (clickedItem != null) {
                String clickedDisplayName = clickedItem.getItemMeta().getDisplayName();
                if (clickedItem.getType() == Material.DIAMOND && clickedDisplayName.equals(ChatColor.GOLD + "" + ChatColor.BOLD + "VIP")) {
                    if (player.hasPermission("killeffect.vip")) {
                        killEffectManager.setPlayerKillEffect(player, KillEffects.KillEffect.VIP);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "You have equipped the VIP kill effect.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to equip this kill effect.");
                    }
                } else if (clickedItem.getType() == Material.EMERALD && clickedDisplayName.equals(ChatColor.AQUA + "" + ChatColor.BOLD + "MVP")) {
                    if (player.hasPermission("killeffect.mvp")) {
                        killEffectManager.setPlayerKillEffect(player, KillEffects.KillEffect.MVP);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "You have equipped the MVP kill effect.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to equip this kill effect.");
                    }
                } else if (clickedItem.getType() == Material.NETHER_STAR && clickedDisplayName.equals(ChatColor.GREEN + "" + ChatColor.BOLD + "ELITE")) {
                    if (player.hasPermission("killeffect.elite")) {
                        killEffectManager.setPlayerKillEffect(player, KillEffects.KillEffect.ELITE);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "You have equipped the Elite kill effect.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to equip this kill effect.");
                    }
                } else if (clickedItem.getType() == Material.TNT && clickedDisplayName.equals(ChatColor.RED + "" + ChatColor.BOLD + "LEGEND")) {
                    if (player.hasPermission("killeffect.legend")) {
                        killEffectManager.setPlayerKillEffect(player, KillEffects.KillEffect.LEGEND);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "You have equipped the Legend kill effect.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to equip this kill effect.");
                    }
                } else if (clickedItem.getType() == Material.FEATHER && clickedDisplayName.equals(ChatColor.YELLOW + "" + ChatColor.BOLD + "FAST")) {
                    if (player.hasPermission("killeffect.fast")) {
                        killEffectManager.setPlayerKillEffect(player, KillEffects.KillEffect.FAST);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "You have equipped the Fast kill effect.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to equip this kill effect.");
                    }
                } else if (clickedItem.getType() == Material.TOTEM_OF_UNDYING && clickedDisplayName.equals(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "CUSTOM")) {
                    if (player.hasPermission("killeffect.custom")) {
                        killEffectManager.setPlayerKillEffect(player, KillEffects.KillEffect.CUSTOM);
                        player.closeInventory();
                        player.sendMessage(ChatColor.GREEN + "You have equipped the Custom kill effect.");
                    } else {
                        player.sendMessage(ChatColor.RED + "You don't have permission to equip this kill effect.");
                    }
                }
            }
        }
    }
}
