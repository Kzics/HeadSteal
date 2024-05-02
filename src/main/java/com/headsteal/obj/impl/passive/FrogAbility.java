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
    public String getDescription() {
        return "Gives player Jump Boost Effect";
    }

    @Override
    public void apply(Player player) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.JUMP_BOOST, 1000000, 3);
        player.addPotionEffect(potionEffect);

        System.out.println("lol");
    }

    @Override
    public void remove(Player player) {
        player.removePotionEffect(PotionEffectType.JUMP_BOOST);
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {
        // Do nothing
    }

    @Override
    public void onInteract(Player player, Action action) {

    }
}
