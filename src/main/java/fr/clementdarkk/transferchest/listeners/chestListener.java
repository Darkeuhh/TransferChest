package fr.clementdarkk.transferchest.listeners;


import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import fr.clementdarkk.transferchest.main;

import java.io.IOException;

public class chestListener implements Listener {

    private static main main;

    public chestListener(main main){
        this.main = main;
    }


    private static Inventory inv;


    public static void openChest(Player player){
        if(inv==null || inv.getSize()!=main.getConfig().getInt("chest-size")){
            inv = Bukkit.createInventory(null, main.getConfig().getInt("chest-size")*9, "Public Chest");
        }
        FileConfiguration config = main.getConfig();
        for(int i = 0; i<config.getInt("chest-size")*9; i++){

            if(config.getItemStack("chest.slot"+i)!=null){
                inv.setItem(i, config.getItemStack("chest.slot"+i));
            }



        }
        if(config.getBoolean("chest-broadcast")==true) {
            Bukkit.broadcastMessage("§6" + player.getDisplayName() + " §avient d'ouvrir le coffre public");
        }

        player.openInventory(inv);


    }

    @EventHandler
    public void clickChest(InventoryClickEvent event){
        Inventory iv = event.getInventory();
        FileConfiguration config = main.getConfig();
        if(iv==inv){
            for(int i = 0; i<config.getInt("chest-size")*9; i++){
                if(iv.getItem(i)!=null){
                    config.set("chest.slot"+i, iv.getItem(i));
                }
            }
            main.setConfigFile(config);
            try {
                config.save(main.getConfigFile());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    @EventHandler
    public void exitChest(InventoryCloseEvent event){
        Inventory iv = event.getInventory();
        FileConfiguration config = main.getConfig();
        if(iv==inv){
            for(int i = 0; i<9; i++){
                if(iv.getItem(i)!=null){
                    config.set("chest.slot"+i, iv.getItem(i));
                }
            }
            main.setConfigFile(config);
            try {
                config.save(main.getConfigFile());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }


}
