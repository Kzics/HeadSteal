package com.headsteal;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class HeadsManager {


    private final HashMap<EntityType, HeadAbility> abilities;
    private final List<UUID> striderPlayers;

    public HeadsManager(){
        this.abilities = new HashMap<>();
        this.striderPlayers = new ArrayList<>();
    }

    public void addStriderPlayer(UUID uuid){
        striderPlayers.add(uuid);
    }

    public void removeStriderPlayer(UUID uuid){
        striderPlayers.remove(uuid);
    }

    public boolean isStriderPlayer(UUID uuid){
        return striderPlayers.contains(uuid);
    }

    public EntityType isHead(ItemStack itemStack){
        if(itemStack == null) return null;
        if(itemStack.getItemMeta() == null) return null;

        if(itemStack.getItemMeta().getPersistentDataContainer().has(HeadItem.headKey, PersistentDataType.STRING)){
            return EntityType.valueOf(itemStack.getItemMeta().getPersistentDataContainer().get(HeadItem.headKey, PersistentDataType.STRING));
        }
        return null;
    }

    public void addAbilities(HeadAbility... abilities){
        for(HeadAbility ability : abilities){
            addAbility(ability.getEntityType(), ability);
        }
    }

    public HashMap<EntityType, HeadAbility> getAbilities() {
        return abilities;
    }

    public void addAbility(EntityType type, HeadAbility ability){
        abilities.put(type, ability);
    }

    public HeadAbility getAbility(EntityType type){
        return abilities.get(type);
    }
}
