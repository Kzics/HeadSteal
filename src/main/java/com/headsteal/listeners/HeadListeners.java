package com.headsteal.listeners;

import com.headsteal.HeadItem;
import com.headsteal.HeadsManager;
import com.headsteal.obj.HeadAbility;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

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
    public void onInteract(PlayerInteractEvent event){
        ItemStack item = event.getPlayer().getInventory().getHelmet();
        if(item == null) return;
        if(item.getItemMeta() == null) return;

        ItemMeta meta = item.getItemMeta();
        if(!meta.getPersistentDataContainer().has(HeadItem.headKey, PersistentDataType.STRING)) return;

        EntityType type = EntityType.valueOf(meta.getPersistentDataContainer().get(HeadItem.headKey, PersistentDataType.STRING));
        HeadAbility ability = headsManager.getAbility(type);

        ability.onInteract(event.getPlayer(), event.getAction());

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
    public void onWalkOnLava(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(!headsManager.isStriderPlayer(player.getUniqueId())) return;

        Block block = player.getLocation().getBlock();

        if (block.getType() == Material.LAVA) {
            Vector velocity = player.getVelocity();
            velocity.setY(0.1);
            player.setVelocity(velocity);
        }
    }


    private HeadAbility getAbility(EntityType type){
        return headsManager.getAbility(type);

    }

}
