package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.entity.*;
import org.bukkit.event.block.Action;

public class BreezeAbility extends HeadAbility {
    public BreezeAbility() {
        super(EntityType.BREEZE);
    }

    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Shoots a Wind Charge";
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
        if(!player.isSneaking()) return;


        if(!checkCooldown(player)) {
            player.sendMessage(ColorsUtil.translate.apply("&cAbility is on cooldown!"));
            return;
        }
        BreezeWindCharge windCharge = player.getWorld().spawn(player.getEyeLocation(), BreezeWindCharge.class);

        windCharge.setDirection(player.getLocation().getDirection());
        windCharge.setYield(3);
    }
}
