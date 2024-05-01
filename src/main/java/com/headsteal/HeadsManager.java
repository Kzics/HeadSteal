package com.headsteal;

import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class HeadsManager {


    private final HashMap<EntityType, HeadAbility> abilities;

    public HeadsManager(){
        this.abilities = new HashMap<>();
    }

    public EntityType isHead(ItemStack itemStack){
        if(itemStack == null) return null;
        if(itemStack.getItemMeta() == null) return null;

        if(itemStack.getItemMeta().getPersistentDataContainer().has(HeadItem.headKey, PersistentDataType.STRING)){
            return EntityType.valueOf(itemStack.getItemMeta().getPersistentDataContainer().get(HeadItem.headKey, PersistentDataType.STRING));
        }
        return null;
    }

    public void addAbility(EntityType type, HeadAbility ability){
        abilities.put(type, ability);
    }

    public HeadAbility getAbility(EntityType type){
        return abilities.get(type);
    }
}
