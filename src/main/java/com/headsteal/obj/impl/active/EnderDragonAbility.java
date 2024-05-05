package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

import java.util.Collection;

public class EnderDragonAbility extends HeadAbility {
    public EnderDragonAbility() {
        super(EntityType.ENDER_DRAGON);
    }

    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Shoots a dragon breath";
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
        if (!player.isSneaking()) return;

        if (!checkCooldown(player)) {
            player.sendMessage(ColorsUtil.translate.apply("&cAbility is on cooldown!"));
            return;
        }

        final int distance = 25;
        Vector direction = player.getEyeLocation().getDirection().normalize();

        for (int i = 0; i < distance; i++) {
            Location location = player.getEyeLocation().add(direction.multiply(i));
            player.getWorld().spawnParticle(Particle.DRAGON_BREATH, location, 20);

            Collection<Entity> nearbyEntities = location.getWorld().getNearbyEntities(location, 0.5, 0.5, 0.5);

            for (Entity entity : nearbyEntities) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;

                    target.damage(5.0);
                }
            }
        }
    }
}
