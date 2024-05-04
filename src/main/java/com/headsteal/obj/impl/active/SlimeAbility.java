package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

public class SlimeAbility extends HeadAbility {
    public SlimeAbility() {
        super(EntityType.SLIME);
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public String getDescription() {
        return "Increases/Reduces player size";
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
        if (!player.isSneaking()) return;


        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_SCALE);
        if (attribute == null) return;

        attribute.setBaseValue(attribute.getBaseValue() + 1);
        if(attribute.getBaseValue() > 6) attribute.setBaseValue(1);
    }
}
