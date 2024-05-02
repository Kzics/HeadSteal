package com.headsteal.obj.impl.passive;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ChickenAbility extends HeadAbility {
    public ChickenAbility() {
        super(EntityType.CHICKEN);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Allows player to glide";
    }

    @Override
    public void apply(Player player) {
        PotionEffect effect = new PotionEffect(PotionEffectType.SLOW_FALLING, 1000000, 2, false, false, false);
        player.addPotionEffect(effect);
    }

    @Override
    public void remove(Player player) {
        player.removePotionEffect(PotionEffectType.SLOW_FALLING);
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }

    @Override
    public void onInteract(Player player, Action action) {

    }
}
