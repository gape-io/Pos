package me.joshmiquel.pos.util;

import org.bukkit.OfflinePlayer;

public class PlaceholderExpansion extends me.clip.placeholderapi.expansion.PlaceholderExpansion {


    @Override
    public String getIdentifier() {
        return "pos";
    }

    @Override
    public String getAuthor() {
        return "JoShMiQueL";
    }

    @Override
    public String getVersion() {
        return "1.0";
    }

    public String onRequest(OfflinePlayer player, String identifier) {

        // %pos_mined_blocks%
        if(identifier.equals("mined_blocks")) {

        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }


}
