package com.headsteal;

import com.headsteal.commands.OperatorCommand;
import com.headsteal.database.PlayerDeathDAO;
import com.headsteal.listeners.HeadListeners;
import com.headsteal.obj.impl.active.*;
import com.headsteal.obj.impl.passive.*;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.sql.SQLException;

public class Main extends JavaPlugin implements Listener {

    public static Main instance;
    private HeadsManager headsManager;
    private PlayerDeathDAO playerDeathDAO;
    @Override
    public void onEnable() {
        if(!getDataFolder().exists()) getDataFolder().mkdir();

        try {
            if(!new File(getDataFolder(), "config.yml").exists()) copyStreamToFile(getResource("config.yml"), new File(getDataFolder(), "config.yml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            playerDeathDAO = new PlayerDeathDAO("jdbc:h2:" + getDataFolder().getAbsolutePath() + "/player_deaths;TRACE_LEVEL_FILE=0;TRACE_LEVEL_SYSTEM_OUT=0");

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
                new WitherAbility(),
                new SlimeAbility(),
                new StriderAbility());
        getCommand("headop").setExecutor(new OperatorCommand(headsManager));

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new HeadListeners(headsManager), this);
    }


    public void copyStreamToFile(InputStream source, File destination) throws IOException {
        try (OutputStream out = new FileOutputStream(destination)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = source.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
        } finally {
            if (source != null) {
                source.close();
            }
        }
    }
    public HeadsManager getHeadsManager() {
        return headsManager;
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if(entity.getKiller() == null) return;

        Player killer = entity.getKiller();

        if (killer != null && headsManager.getAbilities().containsKey(entity.getType())) {
            HeadItem head = new HeadItem(entity.getType(),false);
            event.getDrops().add(head);
        }
    }

    public PlayerDeathDAO getPlayerDeathDAO() {
        return playerDeathDAO;
    }
}
