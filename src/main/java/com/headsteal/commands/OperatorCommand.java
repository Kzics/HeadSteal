package com.headsteal.commands;

import com.headsteal.HeadItem;
import com.headsteal.HeadsManager;
import com.headsteal.utils.ColorsUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class OperatorCommand implements CommandExecutor, TabCompleter {

    private HeadsManager headsManager;

    public OperatorCommand(HeadsManager headsManager) {
        this.headsManager = headsManager;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!commandSender.isOp()) return false;

        if(strings.length == 0){
            commandSender.sendMessage(ColorsUtil.translate.apply("&cUsage: /operator <ability>"));
            return false;
        }

        try {
            EntityType type = EntityType.valueOf(strings[0].toUpperCase());
            ((Player) commandSender).getInventory().addItem(new HeadItem(type,false));
            commandSender.sendMessage(ColorsUtil.translate.apply("&aYou have given yourself the ability: " + type.name()));
        }catch (Exception e){
            commandSender.sendMessage(ColorsUtil.translate.apply("&aInvalid Ability"));
            return false;
    }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            String input = args[0].toUpperCase();
            for (EntityType type : headsManager.getAbilities().keySet()) {
                if (type.name().startsWith(input)) {
                    completions.add(type.name());
                }
            }
        }
        return completions;
    }

}

