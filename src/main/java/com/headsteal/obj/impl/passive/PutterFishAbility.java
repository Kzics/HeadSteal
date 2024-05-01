package com.headsteal.obj.impl.passive;

import com.headsteal.HeadsManager;
import com.headsteal.PassiveAbility;
import com.headsteal.obj.HeadAbility;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PutterFishAbility extends HeadAbility {

    private final HeadsManager headsManager;
    public PutterFishAbility(HeadsManager headsManager){
        super(EntityType.PUFFERFISH);
        this.headsManager = headsManager;
    }

    @Override
    protected boolean isPassive() {
        return true;
    }

    @Override
    public void apply(Player player) {

    }

    @Override
    public void remove(Player player) {
    }

    @Override
    public void onPlayerHit(Player attacker, Player target) {
        PotionEffect potionEffect = new PotionEffect(PotionEffectType.CONFUSION, 200, 2);
        target.getActivePotionEffects().add(potionEffect);
    }

    @Override
    public void onInteract(Player player, Action action) {

    }
}
