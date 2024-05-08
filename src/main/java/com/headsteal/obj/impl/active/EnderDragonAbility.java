package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;
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

        Location location = player.getEyeLocation();

        DragonFireball fireball = player.launchProjectile(DragonFireball.class, location.getDirection().multiply(2));
        fireball.setYield(3);
        fireball.setDirection(location.getDirection());
    }

}
