package com.headsteal;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Skull;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

public class HeadItem extends ItemStack {

    public static NamespacedKey headKey = new NamespacedKey(Main.instance, "head");

    public HeadItem(EntityType mobType) {
        super(Material.PLAYER_HEAD);

        SkullMeta meta = (SkullMeta) this.getItemMeta();
        //PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());

        meta.getPersistentDataContainer().set(headKey, PersistentDataType.STRING, mobType.name());

        this.setItemMeta(meta);
    }
}

