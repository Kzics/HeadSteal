package com.headsteal;

import com.headsteal.utils.ColorsUtil;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.function.UnaryOperator;

public class HeadItem extends ItemStack {

    public static NamespacedKey headKey = new NamespacedKey(Main.instance, "head");
    public static NamespacedKey operatorKey = new NamespacedKey(Main.instance, "operator");

    public HeadItem(EntityType mobType, boolean operator) {
        super(Material.PLAYER_HEAD);

        SkullMeta meta = (SkullMeta) this.getItemMeta();
        if(operator) meta.getPersistentDataContainer().set(operatorKey, PersistentDataType.STRING, "");

        meta.setDisplayName(ColorsUtil.translate.apply(Main.instance.getConfig().getString("heads.name")
                .replace("{mob}", mobType.name().toLowerCase().replace("_", " "))));

        UnaryOperator<String> replaceMob = s -> s.replaceAll("\\{mob}", "")
                .replaceAll("\\{description}", Main.instance.getHeadsManager().getAbility(mobType).getDescription());

        List<String> lore = ColorsUtil.translateList.apply(Main.instance.getConfig().getStringList("heads.lore"));
        lore.replaceAll(replaceMob);

        meta.setLore(lore);

        meta.getPersistentDataContainer().set(headKey, PersistentDataType.STRING, mobType.name());

        GameProfile profile = new GameProfile(UUID.randomUUID(), mobType.name().toUpperCase());

        profile.getProperties().put("textures", new Property("textures", HeadsTexture.valueOf(mobType.name()).getTexture()));

        try {
            Field profileField = meta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(meta, profile);

        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException error) {
            error.printStackTrace();
        }

        this.setItemMeta(meta);
    }

    private ItemStack createPlayerHead(String url) throws MalformedURLException {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);

        if(itemStack.getItemMeta() instanceof SkullMeta skullMeta){
            var playerProfile = Bukkit.createPlayerProfile(UUID.randomUUID());
            playerProfile.getTextures().setSkin(URI.create(url).toURL());
            skullMeta.setOwnerProfile(playerProfile);
            itemStack.setItemMeta(skullMeta);
        }
        return itemStack;
    }
}

