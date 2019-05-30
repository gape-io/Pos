package me.joshmiquel.pos.events;

import me.clip.placeholderapi.PlaceholderAPI;

import me.joshmiquel.pos.Main;
import net.md_5.bungee.api.chat.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class OnPosInChatEvent implements Listener {

    private Main plugin;
    public OnPosInChatEvent(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onWritePos(AsyncPlayerChatEvent e) {

        Player player = e.getPlayer();
        int XLocation = (int) player.getLocation().getX();
        int YLocation = (int) player.getLocation().getY();
        int ZLocation = (int) player.getLocation().getZ();
        String MessageSent = e.getMessage();

        String playernameSyntax = plugin.getConfig().getString("Syntax.playerNameComponent.playerName");
        String messageSyntax = plugin.getConfig().getString("Syntax.messageComponent.message");
        String toolTipSyntax = plugin.getConfig().getString("Syntax.tipComponent.tip");

        // We parse the placeholders using "setPlaceholders"
        playernameSyntax = PlaceholderAPI.setPlaceholders(player, playernameSyntax);
        messageSyntax = PlaceholderAPI.setPlaceholders(player, messageSyntax);
        toolTipSyntax = PlaceholderAPI.setPlaceholders(player, toolTipSyntax);

        TextComponent playerNameComponent = new TextComponent(playernameSyntax);
        playerNameComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Syntax.playerNameComponent.toolTip"))).create()));

        TextComponent messageComponent = new TextComponent(messageSyntax);
        messageComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Syntax.messageComponent.toolTip"))).create()));

        TextComponent toolTipComponent = new TextComponent(toolTipSyntax);
        // toolTipComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', "&3toolTipComponent")).create()));

        if (MessageSent.equals("%pos%")) {
            e.setCancelled(true);
            for(Player _player : Bukkit.getOnlinePlayers()) {
                _player.spigot().sendMessage(playerNameComponent, messageComponent, toolTipComponent);
            }
        }

        if (MessageSent.equals("%test%")) {
            e.setCancelled(true);
            for(Player _player : Bukkit.getOnlinePlayers()) {
                String text = plugin.getConfig().getString("Syntax.messageComponent.toolTip");
                _player.sendMessage(ChatColor.translateAlternateColorCodes('&', text));
            }
        }

        // player.spigot().sendMessage(ChatMessageType.ACTION_BAR, msg);
    }

}