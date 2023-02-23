package fr.clementdarkk.transferchest.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import fr.clementdarkk.transferchest.main;
import fr.clementdarkk.transferchest.listeners.chestListener;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class sizeCommand implements CommandExecutor {

    private main main;

    public sizeCommand(main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {

        if(strings.length==1) {
            FileConfiguration config = main.getConfig();
            if(parseInt(strings[0])*9<55&&parseInt(strings[0])!=0){
                config.set("chest-size", parseInt(strings[0]));
                main.setConfigFile(config);
                try {
                    config.save(main.getConfigFile());
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                sender.sendMessage("§dTaille du coffre public modifiée à : §b"+parseInt(strings[0])*9+" slots");
                chestListener.changeSize();
            }
            else{
                sender.sendMessage("§cUtilisez : /chestsize <taille du coffre>");
                sender.sendMessage("§cLa taille du coffre doit être comprise entre 1 et 6");
            }
        }
        else{
            sender.sendMessage("§cUtilisez : /chestsize <taille du coffre>");
            sender.sendMessage("§cLa taille du coffre doit être comprise entre 1 et 6");
        }





        return false;
    }
}
