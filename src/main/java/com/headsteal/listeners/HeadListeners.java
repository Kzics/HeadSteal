package com.headsteal.listeners;

import com.headsteal.DeathAction;
import com.headsteal.HeadItem;
import com.headsteal.HeadsManager;
import com.headsteal.Main;
import com.headsteal.obj.HeadAbility;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

import java.sql.SQLException;
import java.time.Instant;
import java.util.UUID;

public class HeadListeners implements Listener {


    private final HeadsManager headsManager;
    public HeadListeners(HeadsManager headsManager) {
        this.headsManager = headsManager;
    }



    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event){
        if(!(event.getDamager() instanceof Player player)) return;
        if(!(event.getEntity() instanceof Player victim)) return;

        ItemStack helmet = player.getInventory().getHelmet();
        if(helmet == null) return;

        EntityType type = headsManager.isHead(helmet);
        if (type == null) return;

        HeadAbility ability = headsManager.getAbility(type);

        ability.onPlayerHit(player, victim);
    }

    @EventHandler
    public void onInteract(PlayerToggleSneakEvent event){
        ItemStack item = event.getPlayer().getInventory().getHelmet();
        if(item == null) return;
        if (item.getItemMeta() == null) return;

        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(HeadItem.headKey, PersistentDataType.STRING)) return;

        EntityType type = EntityType.valueOf(meta.getPersistentDataContainer().get(HeadItem.headKey, PersistentDataType.STRING));
        HeadAbility ability = headsManager.getAbility(type);

        ability.onInteract(event.getPlayer(), null);
    }

    @EventHandler
    public void onEquip(InventoryClickEvent event){
        if(event.getSlot() == 39){
            ItemStack item = event.getCursor();
            if(item != null && item.hasItemMeta()){
                ItemMeta meta = item.getItemMeta();
                if(meta.getPersistentDataContainer().has(HeadItem.headKey, PersistentDataType.STRING)){
                    System.out.println("equiped");
                    String abilityType = meta.getPersistentDataContainer().get(HeadItem.headKey, PersistentDataType.STRING);
                    HeadAbility ability = getAbility(EntityType.valueOf(abilityType));
                    if(ability != null){
                        ability.apply((Player) event.getWhoClicked());
                        System.out.println("applied" + abilityType);
                    }
                }
            }

            ItemStack oldItem = event.getCurrentItem();
            if(oldItem != null && oldItem.hasItemMeta()){
                ItemMeta oldMeta = oldItem.getItemMeta();
                if(oldMeta.getPersistentDataContainer().has(HeadItem.headKey, PersistentDataType.STRING)){
                    System.out.println("unequipped");
                    String abilityType = oldMeta.getPersistentDataContainer().get(HeadItem.headKey, PersistentDataType.STRING);
                    HeadAbility ability = getAbility(EntityType.valueOf(abilityType));
                    if(ability != null){
                        ability.remove((Player) event.getWhoClicked());
                    }
                }
            }
        }
    }

    @EventHandler
    public void WalkLava(PlayerMoveEvent evento) {
        Player player = evento.getPlayer();
        if(!headsManager.isStriderPlayer(player.getUniqueId())) return;

        Block abajoB = evento.getTo().getBlock().getRelative(BlockFace.DOWN);
        Material type = abajoB.getType();

        if (type == Material.LAVA) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setFlySpeed(0.25f);
        } else {
            player.setFlying(false);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event){
        ItemStack itemStack = event.getItemInHand();
        if(itemStack.getItemMeta() == null) return;

        ItemMeta meta = itemStack.getItemMeta();
        if(!meta.getPersistentDataContainer().has(new NamespacedKey(Main.instance,"dead"), PersistentDataType.STRING)) return;
        UUID uuid = UUID.fromString(meta.getPersistentDataContainer().get(new NamespacedKey(Main.instance,"dead"), PersistentDataType.STRING));

        try {
            if(Main.instance.getPlayerDeathDAO().isPlayerDead(uuid)){
                Main.instance.getPlayerDeathDAO().removePlayerDeath(uuid);

                Player player = Bukkit.getPlayer(uuid);
                player.teleport(event.getBlock().getLocation().add(0.5,1,0.5));
                player.setGameMode(GameMode.SURVIVAL);
                OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(uuid);

                Bukkit.getServer().getBannedPlayers().remove(offlinePlayer);
                player.sendMessage(ColorsUtil.translate.apply("&aPlayer has revived !"));
                player.getInventory().remove(itemStack);

                event.setCancelled(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
        meta.setDisplayName(ColorsUtil.translate.apply("&c" + event.getEntity().getName() + "'s Head"));

        meta.getPersistentDataContainer().set(new NamespacedKey(Main.instance,"dead"), PersistentDataType.STRING, event.getEntity().getUniqueId().toString());

        meta.setOwnerProfile(event.getEntity().getPlayerProfile());
        playerHead.setItemMeta(meta);

        event.getDrops().add(playerHead);

        DeathAction action = DeathAction.valueOf(Main.instance.getConfig().getString("death-action"));
        if(action.equals(DeathAction.SPECTATING)){
            event.getEntity().setGameMode(org.bukkit.GameMode.SPECTATOR);
        }else{
            event.getEntity().ban("You died", Instant.MAX, "You died");
        }
        try {
            Main.instance.getPlayerDeathDAO().insertPlayerDeath(event.getEntity().getUniqueId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HeadAbility getAbility(EntityType type){
        return headsManager.getAbility(type);

    }

}
