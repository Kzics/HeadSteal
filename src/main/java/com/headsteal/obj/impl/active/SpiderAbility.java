package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

public class SpiderAbility extends HeadAbility {
    public SpiderAbility() {
        super(EntityType.SPIDER);
    }

    @Override
    protected boolean isPassive() {
        return false;
    }

    @Override
    public String getDescription() {
        return "Creates cobwebs in front of you";
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

        if (checkCooldown(player)) {

            Location location = player.getEyeLocation();
            Vector direction = location.getDirection().normalize();

            Block targetBlock = location.add(direction.multiply(3)).getBlock();
            targetBlock.setType(Material.COBWEB);
            targetBlock.getRelative(BlockFace.NORTH).setType(Material.COBWEB);
            targetBlock.getRelative(BlockFace.EAST).setType(Material.COBWEB);
            targetBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).setType(Material.COBWEB);
        } else {
            player.sendMessage(ColorsUtil.translate.apply("&cAbility is on cooldown!"));

        }
    }

}
