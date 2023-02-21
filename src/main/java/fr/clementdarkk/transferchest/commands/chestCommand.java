package fr.clementdarkk.transferchest.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fr.clementdarkk.transferchest.listeners.chestListener;

public class chestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(sender instanceof Player){
            chestListener.openChest((Player) sender);
        }

        return false;
    }
}
