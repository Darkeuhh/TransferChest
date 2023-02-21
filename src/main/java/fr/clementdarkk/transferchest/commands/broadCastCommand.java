package fr.clementdarkk.transferchest.commands;

import fr.clementdarkk.transferchest.main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class broadCastCommand implements CommandExecutor {

    private main main;

    public broadCastCommand(main main) {
        this.main = main;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        FileConfiguration config = main.getConfig();
        if(config.getBoolean("chest-broadcast")==true){
            config.set("chest-broadcast", false);
            main.setConfigFile(config);
            try {
                config.save(main.getConfigFile());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sender.sendMessage("§dDiffusion du message d'ouverture du coffre §bdésactivée");
        }
        else{
            config.set("chest-broadcast", true);
            main.setConfigFile(config);
            try {
                config.save(main.getConfigFile());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            sender.sendMessage("§dDiffusion du message d'ouverture du coffre §bactivée");
        }

        return false;
    }
}
