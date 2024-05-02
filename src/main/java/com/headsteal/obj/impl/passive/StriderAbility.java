package com.headsteal.obj.impl.passive;

import com.headsteal.Main;
import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class StriderAbility extends HeadAbility {
    public StriderAbility() {
        super(EntityType.STRIDER);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public void apply(Player player) {
        Main.instance.getHeadsManager().addStriderPlayer(player.getUniqueId());

        PotionEffect potionEffect = new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 1000000, 3);
        player.addPotionEffect(potionEffect);
    }

    @Override
    public void remove(Player player) {
        Main.instance.getHeadsManager().removeStriderPlayer(player.getUniqueId());
        player.removePotionEffect(PotionEffectType.FIRE_RESISTANCE);
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }

    @Override
    public void onInteract(Player player, Action action) {

    }

    @Override
    public String getDescription() {
        return "Allows player to walk on lava";
    }
}
