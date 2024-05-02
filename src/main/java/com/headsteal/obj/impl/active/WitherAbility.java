package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.WitherSkull;
import org.bukkit.event.block.Action;

public class WitherAbility extends HeadAbility {
    public WitherAbility() {
        super(EntityType.WITHER);
    }

    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Shoots a wither skull";
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

        if(!checkCooldown(player)) {
            player.sendMessage("Ability is on cooldown!");
            return;
        }

        WitherSkull witherSkull = player.launchProjectile(WitherSkull.class);
        witherSkull.setDirection(player.getLocation().getDirection());
        witherSkull.setYield(3);

    }
}
