package com.headsteal.obj.impl;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.Player;

public class BatAbility extends HeadAbility {
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
}
