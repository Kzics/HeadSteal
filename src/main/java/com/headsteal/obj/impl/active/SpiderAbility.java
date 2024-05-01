package com.headsteal.obj.impl.active;

import com.headsteal.obj.HeadAbility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.util.Vector;

public class SpiderAbility extends HeadAbility {
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
        if (action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) {
            if (checkCooldown(player)) {
                Location location = player.getEyeLocation();
                Vector direction = location.getDirection().normalize();

                Block targetBlock = location.add(direction.multiply(2)).getBlock();
                targetBlock.setType(Material.COBWEB);
                targetBlock.getRelative(BlockFace.NORTH).setType(Material.COBWEB);
                targetBlock.getRelative(BlockFace.EAST).setType(Material.COBWEB);
                targetBlock.getRelative(BlockFace.NORTH).getRelative(BlockFace.EAST).setType(Material.COBWEB);
            } else {
                player.sendMessage("Ability is on cooldown!");
            }
        }
    }

}
