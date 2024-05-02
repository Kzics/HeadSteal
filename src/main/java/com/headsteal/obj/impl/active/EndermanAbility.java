package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

public class EndermanAbility extends HeadAbility {
    public EndermanAbility() {
        super(EntityType.ENDERMAN);
    }

    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Teleports you ramdomly in a range of 20 blocks";
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
        if(!player.isSneaking()) return;
        if(!action.equals(Action.RIGHT_CLICK_AIR)) return;

        if(!checkCooldown(player)) {
            player.sendMessage("Ability is on cooldown!");
            return;
        }
        World world = player.getWorld();
        Vector dir = player.getLocation().getDirection().normalize();
        Location teleportLocation = player.getLocation().add(dir.multiply(20));

        int highestY = world.getHighestBlockYAt(teleportLocation);
        teleportLocation.setY(highestY);

        player.teleport(teleportLocation);
        world.spawnParticle(Particle.PORTAL, teleportLocation, 100);
    }
}
