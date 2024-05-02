package com.headsteal;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.List;
import java.util.function.UnaryOperator;

public class HeadItem extends ItemStack {

    public static NamespacedKey headKey = new NamespacedKey(Main.instance, "head");

    public HeadItem(EntityType mobType) {
        super(Material.PLAYER_HEAD);

        SkullMeta meta = (SkullMeta) this.getItemMeta();
        //PlayerProfile profile = Bukkit.createPlayerProfile(UUID.randomUUID());

        meta.setDisplayName(Main.instance.getConfig().getString("heads.name")
                .replace("{mob}", mobType.name().toLowerCase().replace("_", " ")));
        UnaryOperator<String> replaceMob = s -> s.replaceAll("\\{mob}", "")
                .replaceAll("\\{description}", Main.instance.getHeadsManager().getAbility(mobType).getDescription());

        List<String> lore = Main.instance.getConfig().getStringList("heads.lore");
        lore.replaceAll(replaceMob);

        meta.setLore(lore);

        meta.getPersistentDataContainer().set(headKey, PersistentDataType.STRING, mobType.name());

        this.setItemMeta(meta);
    }
}

