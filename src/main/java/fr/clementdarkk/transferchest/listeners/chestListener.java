package fr.clementdarkk.transferchest.listeners;


import jline.internal.Nullable;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import fr.clementdarkk.transferchest.main;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public class chestListener implements Listener {

    private static main main;

    public chestListener(main main){
        this.main = main;
    }


    private static Inventory inv;


    public static void openChest(Player player){
        if(inv==null ){
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
        if(event.getClick()== ClickType.DOUBLE_CLICK||event.getClick()== ClickType.DOUBLE_CLICK&&event.isShiftClick()){
            event.setCancelled(true);
        }

        Inventory iv = event.getInventory();
        FileConfiguration config = main.getConfig();
        ItemStack air = new ItemStack(Material.AIR, 0);
        if(iv==inv){
            for(int i = 0; i<config.getInt("chest-size")*9; i++){
                if(iv.getItem(i)!=null){
                    config.set("chest.slot"+i, iv.getItem(i));
                }
                else{
                    config.set("chest-slot"+i, air);
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
        ((Player)event.getPlayer()).updateInventory();
        Inventory iv = event.getInventory();
        FileConfiguration config = main.getConfig();
        ItemStack air = new ItemStack(Material.AIR, 0);

        if(iv==inv){
            for(int i = 0; i<iv.getSize(); i++){
                if(iv.getItem(i)!=null){
                    config.set("chest.slot"+i, iv.getItem(i));
                }
                else{
                    config.set("chest-slot"+i, air);
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


    public static void changeSize(){
        FileConfiguration config = main.getConfig();
        if(!inv.getViewers().isEmpty()){
            for(int i=0;i<=inv.getViewers().size();i++) {
                HumanEntity viewer = inv.getViewers().get(i);


                if (viewer instanceof Player) {
                    Player player = Bukkit.getPlayer(viewer.getName());
                    player.closeInventory();
                }
            }

        }

        inv = Bukkit.createInventory(null, config.getInt("chest-size")*9, "Public Chest");

        for(int i = 0; i<config.getInt("chest-size")*9; i++) {
            if (config.getItemStack("chest.slot" + i) != null) {
                inv.setItem(i, config.getItemStack("chest.slot" + i));
            }
        }

    }

}
