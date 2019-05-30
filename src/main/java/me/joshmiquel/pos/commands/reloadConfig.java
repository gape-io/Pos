package me.joshmiquel.pos.commands;

import me.joshmiquel.pos.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class reloadConfig implements CommandExecutor {

    private Main plugin;
    public reloadConfig(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(args[0].equals("reload")) {
            plugin.getCustomConfig().reload();
            plugin.getCustomPlayerstats().reload();
            sender.sendMessage("Config recargada");
        } else if (args[0].equals("")) {
            return false;
        }

        return false;
    }
}
