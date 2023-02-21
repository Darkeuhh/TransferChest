package fr.clementdarkk.transferchest;

import fr.clementdarkk.transferchest.commands.broadCastCommand;
import fr.clementdarkk.transferchest.commands.chestCommand;
import fr.clementdarkk.transferchest.commands.sizeCommand;
import fr.clementdarkk.transferchest.listeners.chestListener;
import org.bukkit.Server;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class main extends JavaPlugin {

    private File customConfigFile;
    private FileConfiguration customConfig;
    private Server server;

    @Override
    public void onEnable() {
        System.out.println("Plugin enable");
        createCustomConfig();



        getServer().getPluginManager().registerEvents(new chestListener(this), this);
        getCommand("chest").setExecutor(new chestCommand());
        getCommand("chestsize").setExecutor(new sizeCommand(this));
        getCommand("chestbroadcast").setExecutor((new broadCastCommand(this)));

    }

    public File getConfigFile() {
        return this.customConfigFile;
    }

    public void setConfigFile(FileConfiguration config) {
        this.customConfig = config;
    }

    public FileConfiguration getCustomConfig() {
        return this.customConfig;
    }

    private void createCustomConfig() {
        customConfigFile = new File(getDataFolder(), "config.yml");
        if (!customConfigFile.exists()) {
            customConfigFile.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }

        customConfig = new YamlConfiguration();
        try {
            customConfig.load(customConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
