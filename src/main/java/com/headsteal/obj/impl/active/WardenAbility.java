package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class WardenAbility extends HeadAbility {
    public WardenAbility() {
        super(EntityType.WARDEN);
    }

    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Shoots a sonic boom";
    }

    @Override
    public void apply(Player player) {

    }

    @Override
    public void remove(Player player) {

    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }

    @Override
    public void onInteract(Player player, Action action) {
        if (!player.isSneaking()) return;


        if (!checkCooldown(player)) {
            player.sendMessage(ColorsUtil.translate.apply("&cAbility is on cooldown!"));
            return;
        }
        Location start = player.getEyeLocation();
        Location end = start.clone().add(start.getDirection().multiply(10));
        Vector direction = end.clone().subtract(start).toVector().normalize();

        for (double i = 0; i < start.distance(end); i += 0.5) {
            Location point = start.clone().add(direction.clone().multiply(i));
            player.getWorld().spawnParticle(Particle.SONIC_BOOM, point, 1);

            double radius = 1.0;
            double damage = 5;
            for (Entity entity : point.getWorld().getNearbyEntities(point, radius, radius, radius)) {
                if (entity instanceof LivingEntity) {
                    if(entity.getUniqueId().equals(player.getUniqueId())) continue;

                    ((LivingEntity) entity).damage(damage);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 40, 2));
                }
            }
        }
    }
}
