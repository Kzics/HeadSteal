package com.headsteal.obj.impl.passive;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class BatAbility extends HeadAbility {
    public BatAbility() {
        super(EntityType.BAT);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public void apply(Player player) {
        player.setAllowFlight(true);
    }

    @Override
    public void remove(Player player) {
        player.setAllowFlight(false);
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {
        // Do nothing
    }

    @Override
    public void onInteract(Player player, Action action) {

    }
}
