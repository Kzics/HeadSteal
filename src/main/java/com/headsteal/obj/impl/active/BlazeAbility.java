package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class BlazeAbility extends HeadAbility {
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

        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setDirection(player.getLocation().getDirection());
        fireball.setYield(3);
    }
}
