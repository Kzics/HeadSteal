package com.headsteal.obj;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class HeadAbility {

    protected final EntityType entityType;
    public HeadAbility(EntityType entityType) {
        this.entityType = entityType;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    protected Map<UUID, Long> cooldowns = new HashMap<>();
    protected int cooldownTimeInSeconds = 5;

    protected abstract boolean isPassive();
    public abstract void apply(Player player);
    public abstract void remove(Player player);
    public abstract void onPlayerHit(Player attacker, Player target);
    public abstract void onInteract(Player player, Action action);
    public abstract String getDescription();

    protected boolean checkCooldown(Player player) {
        UUID playerId = player.getUniqueId();
        long currentTime = System.currentTimeMillis();

        if (cooldowns.containsKey(playerId) && cooldowns.get(playerId) > currentTime) {
            return false;
        }

        cooldowns.put(playerId, currentTime + cooldownTimeInSeconds * 1000L);
        return true;
    }




}
