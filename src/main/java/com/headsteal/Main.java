package com.headsteal;

import com.headsteal.listeners.HeadListeners;
import com.headsteal.obj.impl.active.*;
import com.headsteal.obj.impl.passive.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;
    private HeadsManager headsManager;
    @Override
    public void onEnable() {
        instance = this;
        headsManager = new HeadsManager();
        headsManager.addAbilities(
                new BatAbility(),
                new ChickenAbility(),
                new DolphinAbility(),
                new FrogAbility(),
                new GolemAbility(),
                new PiglinBruteAbility(),
                new PutterFishAbility(headsManager),
                new RavengerAbility(),
                new SalmonAbility(),
                new BlazeAbility(),
                new BreezeAbility(),
                new CreeperAbility(),
                new EnderDragonAbility(),
                new EndermanAbility(),
                new SpiderAbility(),
                new WardenAbility(),
                new WitherAbility());

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new HeadListeners(headsManager), this);
    }


    public HeadsManager getHeadsManager() {
        return headsManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Player killer = entity.getKiller();

        if (killer != null) {
            HeadItem head = new HeadItem(entity.getType());
            event.getDrops().add(head);
            //killer.getInventory().addItem(head);
        }
    }

}
