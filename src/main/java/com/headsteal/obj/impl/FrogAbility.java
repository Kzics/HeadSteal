package com.headsteal.obj.impl;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrogAbility extends HeadAbility {
    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public void apply(Player player) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.JUMP, 1000000, 3);
        player.getActivePotionEffects().add(potionEffect);
    }

    @Override
    public void remove(Player player) {
        player.removePotionEffect(PotionEffectType.JUMP);
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {
        // Do nothing
    }
}
