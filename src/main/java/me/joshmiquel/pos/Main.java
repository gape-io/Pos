package me.joshmiquel.pos;

import me.joshmiquel.pos.commands.reloadConfig;
import me.joshmiquel.pos.events.OnPosInChatEvent;
import me.joshmiquel.pos.events.RegisterPlayerEvent;
import me.joshmiquel.pos.util.FileBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin {

    private FileBuilder config;
    private FileBuilder playerstats;

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new RegisterPlayerEvent(this), this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            /*
             * We register the EventListeneres here, when PlaceholderAPI is installed.
             * Since all events are in the main class (this class), we simply use "this"
             */
            Bukkit.getPluginManager().registerEvents(new OnPosInChatEvent(this), this);
            getCommand("pos").setExecutor(new reloadConfig(this));
        }else {
            throw new RuntimeException("Could not find PlaceholderAPI!! Plugin can not work without it!");
        }
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7-----[ &3SetUp Config &7]-----"));
        getServer().getConsoleSender().sendMessage("");
        setUpConfig();
        getServer().getConsoleSender().sendMessage("");
        setUpPlayerstats();
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&7--------------------------"));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    private void setUpConfig() {
        String fileName = "config.yml";

        config = new FileBuilder(this, fileName, fileName);

        getServer().getConsoleSender().sendMessage("[DEBUG] " + fileName + " loaded.");
    }

    private void setUpPlayerstats() {
        String fileName = "playerstats.yml";

        playerstats = new FileBuilder(this, fileName);

        getServer().getConsoleSender().sendMessage("[DEBUG] " + fileName + " loaded.");
    }

    public FileBuilder getCustomConfig() {
        return config;
    }

    public FileBuilder getCustomPlayerstats() {
        return playerstats;
    }

}
