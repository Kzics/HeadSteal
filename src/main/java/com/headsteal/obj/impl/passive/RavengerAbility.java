package com.headsteal.obj.impl.passive;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

public class RavengerAbility extends HeadAbility {
    public RavengerAbility() {
        super(EntityType.RAVAGER);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Knocks back player from 20 blocks on hit";
    }

    @Override
    public void apply(Player player) {

    }

    @Override
    public void remove(Player player) {

    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {
        Vector direction = target.getLocation().subtract(attacker.getLocation()).toVector();
        direction.normalize().multiply(20);
        target.setVelocity(direction);
    }


    @Override
    public void onInteract(Player player, Action action) {

    }
}
