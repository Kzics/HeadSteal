package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.EntityType;
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
        if (!(action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.LEFT_CLICK_AIR))) return;


        AttributeInstance attribute = player.getAttribute(Attribute.GENERIC_SCALE);
        if (attribute == null) return;

        if(action.equals(Action.RIGHT_CLICK_AIR)){
            attribute.setBaseValue(attribute.getBaseValue() + 1);
        }else{
            if(attribute.getBaseValue() > 1) attribute.setBaseValue(attribute.getBaseValue() - 1);
        }
    }
}
