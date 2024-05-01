package com.headsteal.obj.impl.passive;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrogAbility extends HeadAbility {
    public FrogAbility() {
        super(EntityType.FROG);
    }

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

    @Override
    public void onInteract(Player player, Action action) {

    }
}
