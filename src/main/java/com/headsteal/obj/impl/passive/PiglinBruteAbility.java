package com.headsteal.obj.impl.passive;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PiglinBruteAbility extends HeadAbility {
    public PiglinBruteAbility() {
        super(EntityType.PIGLIN_BRUTE);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Gives player Strength 3 Effect";
    }

    @Override
    public void apply(Player player) {
        PotionEffect effect = new PotionEffect(PotionEffectType.STRENGTH, 1000000, 3, false, false, false);
        player.addPotionEffect(effect);

    }

    @Override
    public void remove(Player player) {
        player.removePotionEffect(PotionEffectType.STRENGTH);

    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }

    @Override
    public void onInteract(Player player, Action action) {
    }
}
