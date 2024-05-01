package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.block.Action;

public class CreeperAbility extends HeadAbility {
    public CreeperAbility() {
        super(EntityType.CREEPER);
    }

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

    }

    @Override
    public void onInteract(Player player, Action action) {
        if (!(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))) return;

        if(checkCooldown(player)) {
            player.sendMessage("Ability is on cooldown!");
            return;
        }

        TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
        tnt.setFuseTicks(40);
        tnt.setVelocity(player.getLocation().getDirection().multiply(1.5));
    }
}
