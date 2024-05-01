package com.headsteal.obj;

import org.bukkit.entity.Player;

public abstract class HeadAbility {

    protected abstract boolean isPassive();
    public abstract void apply(Player player);
    public abstract void remove(Player player);
    public abstract void onPlayerHit(Player attacker, Player target);

}
