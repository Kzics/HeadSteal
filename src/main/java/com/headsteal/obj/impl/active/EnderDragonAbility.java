package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

public class EnderDragonAbility extends HeadAbility {
    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public void apply(Player player) {

    }

    @Override
    public void remove(Player player) {

    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }//Une f t i

    @Override
    public void onInteract(Player player, Action action) {
        if (!(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))) return;

        if(checkCooldown(player)) {
            player.sendMessage("Ability is on cooldown!");
            return;
        }
        final int distance = 10;
        Vector direction = player.getEyeLocation().getDirection().normalize();

        for (int i = 0; i < distance; i++) {
            Location location = player.getEyeLocation().add(direction.multiply(i));
            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, location, 10);
        }
    }

}