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
    public void apply(Player player) {
        PotionEffect effect = new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 3, false, false, false);
        player.getActivePotionEffects().add(effect);

    }

    @Override
    public void remove(Player player) {
        player.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);

    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }

    @Override
    public void onInteract(Player player, Action action) {
    }
}
