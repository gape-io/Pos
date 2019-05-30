package me.joshmiquel.pos.events;

import me.joshmiquel.pos.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class RegisterPlaceholderEvents  implements Listener {

    private Main plugin;
    public RegisterPlaceholderEvents(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onPlayerMine() {

    }

}
