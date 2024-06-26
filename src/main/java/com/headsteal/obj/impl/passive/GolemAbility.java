package com.headsteal.obj.impl.passive;

import com.headsteal.obj.HeadAbility;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class GolemAbility extends HeadAbility {
    public GolemAbility() {
        super(EntityType.IRON_GOLEM);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Doubles player's health";
    }

    @Override
    public void apply(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        maxHealth.setBaseValue(maxHealth.getBaseValue() * 2);

    }

    @Override
    public void remove(Player player) {
        AttributeInstance maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        maxHealth.setBaseValue(maxHealth.getBaseValue() / 2);
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {

    }

    @Override
    public void onInteract(Player player, Action action) {

    }
}
