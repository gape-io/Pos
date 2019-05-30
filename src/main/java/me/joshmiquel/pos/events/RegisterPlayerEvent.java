package me.joshmiquel.pos.events;

import me.joshmiquel.pos.Main;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.*;

public class RegisterPlayerEvent implements Listener {

    private Main plugin;
    public RegisterPlayerEvent(Main plugin) {
        this.plugin = plugin;
    }

    boolean fileEmpty;
    boolean killedByCmd;

    @EventHandler
    void onPlayerJoin(PlayerJoinEvent e) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(plugin.getDataFolder() + "\\playerstats.yml"));
        if (reader.readLine() == null) {
            fileEmpty = true;
        }

        String[] stats = {"Mob-Kills", "Deaths", "Mined-Blocks", "Placed-Blocks", "Since-Last-Death", "Time-Played"};

        Player player = e.getPlayer();
        String uuid = player.getUniqueId().toString();
        int aux = 0;

        if (fileEmpty == true) {
            plugin.getCustomPlayerstats().set(uuid + ".name", player.getDisplayName());
            for (String stat : stats) {
                plugin.getCustomPlayerstats().set(uuid + "." + stat, 0);
            }
        } else {
            for (String stat : stats) {
                if (!plugin.getCustomPlayerstats().getConfigurationSection(uuid).getKeys(false).contains(stat)) {
                    plugin.getCustomPlayerstats().set(uuid + "." + stat, 0);
                }
                aux++;
            }
        }

        plugin.getCustomPlayerstats().save();
        plugin.getCustomPlayerstats().reload();

    }

    @EventHandler
    void onPlayerChat(PlayerCommandPreprocessEvent e) {

        String[] cmds = {"kill"};
        int aux = 0;

        String MessageSent = e.getMessage();

        for (String cmd : cmds) {
            if (MessageSent.contains("/" + cmd)) {
                killedByCmd = true;
            } else {
                killedByCmd = false;
            }

            if (MessageSent.contains(cmds[aux])) {
                e.getPlayer().sendMessage("El comando " + cmds[aux] + " existe en el array");
            }

            aux++;
        }

    }

    @EventHandler
    void onMobKill(EntityDeathEvent e) throws IOException {

        Entity killer = e.getEntity().getKiller();
        Entity entityKilled = e.getEntity();

        String killeruuid;

        int getMobKills;

        if (killer instanceof Player || killedByCmd == false) {

            killer = (Player) e.getEntity().getKiller();
            killeruuid = killer.getUniqueId().toString();

            if (entityKilled instanceof Mob) {
                getMobKills = plugin.getCustomPlayerstats().getInt(killeruuid + ".Mob-Kills");
                plugin.getCustomPlayerstats().set(killeruuid + ".Mob-Kills", getMobKills + 1);
            }

            plugin.getCustomPlayerstats().save();
            plugin.getCustomPlayerstats().reload();

        }

    }

    @EventHandler
    void onDeath(PlayerDeathEvent e) {

        Player playerDeath = e.getEntity().getPlayer();
        String playerDisplayname = playerDeath.getDisplayName();
        String playeruuid = playerDeath.getUniqueId().toString();

        int getDeaths = plugin.getCustomPlayerstats().getInt(playeruuid + ".Deaths");

        plugin.getCustomPlayerstats().set(playeruuid + ".Deaths", getDeaths + 1);

        plugin.getCustomPlayerstats().save();
        plugin.getCustomPlayerstats().reload();

    }

}
